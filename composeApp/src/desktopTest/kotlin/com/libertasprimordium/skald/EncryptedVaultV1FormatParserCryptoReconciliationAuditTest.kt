package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalog
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParser
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserRequest
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserStatus
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserSyntheticClassification
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerFormat
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerResult
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestFormat
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestResult
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class EncryptedVaultV1FormatParserCryptoReconciliationAuditTest {
    @Test
    fun auditStatusRequiresASeparateHumanArchitectureDecision() {
        val audit = docsSource(AUDIT_DOCUMENT_PATH)

        assertTrue(audit.contains(AUDIT_STATUS))
        assertTrue(audit.contains("reconciliation-audit-only", ignoreCase = true))
        assertTrue(audit.contains("production behavior was not changed", ignoreCase = true))
        assertTrue(audit.contains("no canonical architecture was selected", ignoreCase = true))

        CROSS_LINK_DOCUMENT_PATHS.forEach { path ->
            assertTrue(
                docsSource(path).contains(AUDIT_DOCUMENT_NAME),
                "$path must link to the reconciliation audit without rewriting historical claims.",
            )
        }
    }

    @Test
    fun historicalAuditRecordsTheSeparateHumanResolutionWithoutChangingItsOutcome() {
        val audit = docsSource(AUDIT_DOCUMENT_PATH)

        assertTrue(audit.contains(AUDIT_STATUS))
        assertTrue(audit.contains(CANONICAL_DECISION_DOCUMENT_NAME))
        assertTrue(audit.contains(CANONICAL_DECISION_STATUS))
        assertTrue(audit.contains("human", ignoreCase = true))
        assertTrue(audit.contains("resolved", ignoreCase = true))
    }

    @Test
    fun canonicalArchitectureDecisionIsOneCommonMainPolicySurfaceWithoutExecutionOrMaterial() {
        val definitionFiles = SourceGuardCorpus.productionRuntimeSourceFiles.filter { file ->
            CANONICAL_DECISION_POLICY_DECLARATION.containsMatchIn(SourceGuardCorpus.text(file))
        }
        assertEquals(
            listOf(CANONICAL_DECISION_PATH),
            definitionFiles.map(SourceGuardCorpus::relativePath),
        )

        val source = productionSource(CANONICAL_DECISION_PATH)
        val forbiddenPatterns = linkedMapOf(
            "raw byte-array type" to Regex("""\bU?ByteArray\b"""),
            "byte-array fixture construction" to Regex("""\b(?:u?byteArrayOf|encodeToByteArray|decodeToString)\s*\("""),
            "hex fixture" to Regex("""\b[0-9a-fA-F]{64,}\b"""),
            "hex byte literal" to Regex("""\b0[xX][0-9a-fA-F]{2,}\b"""),
            "field-id table" to Regex("""\b(?:FIELD_ID_|fieldIds?\s*=|numericFieldIds?\s*=)"""),
            "file or path API" to Regex("""\b(?:File|Path|Paths|pathOf)\s*\("""),
            "file operation" to Regex("""\.(?:readBytes|writeBytes|readText|writeText|delete|mkdirs?)\s*\("""),
            "parser or serializer declaration" to Regex("""\bfun\s+(?:parse|serialize|deserialize|encode|decode)\s*\("""),
            "crypto execution declaration" to Regex("""\bfun\s+(?:derive|wrap|unwrap|encrypt|decrypt|authenticate|generate)\w*\s*\("""),
            "prototype call" to Regex("""\bSkaldVaultV1(?:ContainerFormat|ManifestFormat|HeaderCommitment|RecordAead)\s*\."""),
            "synthetic parser call" to Regex("""\bEncryptedVaultWorkingParser\s*\."""),
            "provider selection call" to Regex("""\bVaultCryptoProviderSelectionRegistry\s*\.\s*select\s*\("""),
            "provider operation call" to Regex("""\bVaultCryptoProvider\s*\."""),
            "registry, factory, dispatcher, service, repository, adapter, or UI declaration" to
                Regex("""\b(?:class|object|interface)\s+\w*(?:Registry|Factory|Dispatcher|Service|Repository|Adapter|UiAction)\b"""),
        )
        forbiddenPatterns.forEach { (description, pattern) ->
            assertFalse(pattern.containsMatchIn(source), "Canonical decision source contains $description.")
        }

        val forbiddenImports = listOf(
            "java.io",
            "java.nio",
            "kotlin.io",
            "android.",
            "androidx.",
            "com.google.crypto.tink",
            "org.bouncycastle",
            "javax.crypto",
            "java.security",
            "org.bitcoindevkit",
            "bdk",
        )
        forbiddenImports.forEach { forbidden ->
            assertFalse(
                Regex("""(?m)^\s*import\s+.*${Regex.escape(forbidden)}""").containsMatchIn(source),
                "Canonical decision source imports forbidden execution dependency: $forbidden",
            )
        }

        val stringLiterals = KOTLIN_STRING_LITERAL.findAll(source).map { match -> match.groupValues[1] }.toList()
        assertTrue(stringLiterals.none { literal ->
            literal.contains('/') ||
                literal.contains('\\') ||
                Regex("""(?i)\.(?:bin|dat|db|json|cbor|vault|tmp|bak)$""").containsMatchIn(literal)
        }, "Canonical decision source must not define concrete file names or paths.")
    }

    @Test
    fun canonicalArchitectureDecisionHasNoExternalProductionCallSite() {
        val references = SourceGuardCorpus.productionRuntimeSourceFiles.filter { file ->
            SourceGuardCorpus.relativePath(file) != CANONICAL_DECISION_PATH &&
                SourceGuardCorpus.text(file).contains(CANONICAL_DECISION_PRODUCER_CALL)
        }
        assertTrue(references.isEmpty(), "Canonical decision producer must not become a production call site.")

        val protectedProductionPaths = listOf(
            CONTAINER_FORMAT_PATH,
            MANIFEST_FORMAT_PATH,
            HEADER_COMMITMENT_PATH,
            RECORD_AEAD_PATH,
            WORKING_PARSER_PATH,
            PROVIDER_SELECTION_PATH,
        )
        protectedProductionPaths.forEach { path ->
            assertFalse(
                productionSource(path).contains("EncryptedVaultV1CanonicalArchitecture"),
                "$path must remain unchanged and independent of the architecture policy model.",
            )
        }
    }

    @Test
    fun productionCompiledParserSerializerAndSyntheticClassifierCountsAreExact() {
        val container = productionSource(CONTAINER_FORMAT_PATH)
        val manifest = productionSource(MANIFEST_FORMAT_PATH)
        val syntheticParser = productionSource(WORKING_PARSER_PATH)

        val rawByteParserDeclarations =
            RAW_BYTE_PARSE_DECLARATION.findAll(container).count() +
                RAW_BYTE_PARSE_DECLARATION.findAll(manifest).count()
        val syntheticClassifierDeclarations =
            SYNTHETIC_PARSE_DECLARATION.findAll(syntheticParser).count()
        val rawByteSerializerDeclarations =
            SERIALIZE_DECLARATION.findAll(container).count() +
                SERIALIZE_DECLARATION.findAll(manifest).count()

        assertEquals(2, rawByteParserDeclarations)
        assertEquals(1, syntheticClassifierDeclarations)
        assertEquals(2, rawByteSerializerDeclarations)

        EXPECTED_PRODUCTION_OBJECT_DECLARATIONS.forEach { (declaration, expectedPath) ->
            val definitionFiles = SourceGuardCorpus.productionRuntimeSourceFiles.filter { file ->
                Regex("""\bobject\s+${Regex.escape(declaration)}\b""")
                    .containsMatchIn(SourceGuardCorpus.text(file))
            }
            assertEquals(
                listOf(expectedPath),
                definitionFiles.map(SourceGuardCorpus::relativePath),
                "$declaration must have one production-compiled declaration in its audited source set.",
            )
        }
    }

    @Test
    fun cryptoExecutionDeclarationCountsAndPlatformPrimitivePlacementAreExact() {
        val argon2 = productionSource(ARGON2_PATH)
        val header = productionSource(HEADER_COMMITMENT_PATH)
        val recordAead = productionSource(RECORD_AEAD_PATH)
        val katHarness = productionSource(KAT_HARNESS_PATH)

        val corePublicCryptoExecutionDeclarations =
            functionDeclarationCount(argon2, "deriveRootMaterial") +
                functionDeclarationCount(header, "expandRootMaterial") +
                functionDeclarationCount(header, "expandRootMaterialForPurpose") +
                functionDeclarationCount(header, "computeHeaderCommitment") +
                functionDeclarationCount(header, "verifyHeaderCommitment") +
                functionDeclarationCount(recordAead, "encryptRecord") +
                functionDeclarationCount(recordAead, "decryptRecord")
        val katAdapterAndHarnessExecutionApiSurfaces =
            functionDeclarationCount(katHarness, "sealRecord") +
                functionDeclarationCount(katHarness, "openRecord") +
                functionDeclarationCount(katHarness, "runProviderLevelKat")
        val katBuildingBlockOverrideDeclarations =
            overrideFunctionDeclarationCount(katHarness, "sealRecord") +
                overrideFunctionDeclarationCount(katHarness, "openRecord")

        // Overloads are distinct public callable declarations: 2 Argon2id derivation,
        // 5 header/HKDF/HMAC, and 2 record-AEAD declarations.
        assertEquals(9, corePublicCryptoExecutionDeclarations)
        assertEquals(3, katAdapterAndHarnessExecutionApiSurfaces)
        assertEquals(2, katBuildingBlockOverrideDeclarations)
        assertEquals(12, corePublicCryptoExecutionDeclarations + katAdapterAndHarnessExecutionApiSurfaces)
        assertEquals(
            14,
            corePublicCryptoExecutionDeclarations +
                katAdapterAndHarnessExecutionApiSurfaces +
                katBuildingBlockOverrideDeclarations,
        )

        val commonExpectDeclarations = PRIMITIVE_NAMES.sumOf { primitiveName ->
            SourceGuardCorpus.commonMainFiles.sumOf { file ->
                expectDeclaration(primitiveName).findAll(SourceGuardCorpus.text(file)).count()
            }
        }
        val androidActualDeclarations = PRIMITIVE_NAMES.sumOf { primitiveName ->
            SourceGuardCorpus.androidMainFiles.sumOf { file ->
                actualDeclaration(primitiveName).findAll(SourceGuardCorpus.text(file)).count()
            }
        }
        val desktopActualDeclarations = PRIMITIVE_NAMES.sumOf { primitiveName ->
            SourceGuardCorpus.desktopMainFiles.sumOf { file ->
                actualDeclaration(primitiveName).findAll(SourceGuardCorpus.text(file)).count()
            }
        }

        assertEquals(4, commonExpectDeclarations)
        assertEquals(4, androidActualDeclarations)
        assertEquals(4, desktopActualDeclarations)
        assertEquals(8, androidActualDeclarations + desktopActualDeclarations)
    }

    @Test
    fun namedProductionCallSitesAreConfinedToTheExistingKatHarness() {
        val expectedCounts = linkedMapOf(
            "SkaldVaultV1ContainerFormat.serialize" to 0,
            "SkaldVaultV1ContainerFormat.parse" to 0,
            "SkaldVaultV1ManifestFormat.serialize" to 0,
            "SkaldVaultV1ManifestFormat.parse" to 0,
            "EncryptedVaultWorkingParser.parse" to 0,
            "SkaldVaultV1HeaderCommitment.expandRootMaterial" to 1,
            "SkaldVaultV1HeaderCommitment.computeHeaderCommitment" to 0,
            "SkaldVaultV1HeaderCommitment.verifyHeaderCommitment" to 1,
            "SkaldVaultV1RecordAead.encryptRecord" to 1,
            "SkaldVaultV1RecordAead.decryptRecord" to 1,
        )

        expectedCounts.forEach { (namedCall, expectedCount) ->
            val callSites = SourceGuardCorpus.productionRuntimeSourceFiles.flatMap { file ->
                List(SourceGuardCorpus.text(file).literalCount(namedCall)) {
                    SourceGuardCorpus.relativePath(file)
                }
            }
            assertEquals(expectedCount, callSites.size, "Unexpected production call-site count for $namedCall.")
            if (expectedCount > 0) {
                assertEquals(setOf(KAT_HARNESS_PATH), callSites.toSet())
            }
        }

        val appIntegrationCallSites = SourceGuardCorpus.productionRuntimeSourceFiles.filter { file ->
            val path = SourceGuardCorpus.relativePath(file)
            path != KAT_HARNESS_PATH &&
                ALL_AUDITED_NAMED_CALLS.any { namedCall ->
                    SourceGuardCorpus.text(file).contains(namedCall)
                }
        }
        assertTrue(
            appIntegrationCallSites.isEmpty(),
            "Audited byte/crypto entry points must have no repository, storage, UI, sync, backend, or provider integration call site.",
        )
    }

    @Test
    fun fixtureAndSyntheticCatalogPlacementRemainFactuallyDistinct() {
        val commonFixtureEvidence = linkedMapOf(
            CONTAINER_FORMAT_PATH to listOf("fun vectorFixtureContainer", "FIXTURE_HEADER_COMMITMENT_TAG"),
            MANIFEST_FORMAT_PATH to listOf("fun vectorFixtureManifest", "FIXTURE_HEADER_COMMITMENT_CONTEXT"),
            HEADER_COMMITMENT_PATH to listOf("fun vectorFixtureHeader"),
            RECORD_AEAD_PATH to listOf("VECTOR_RECORD_AEAD_KEY", "VECTOR_PLAINTEXT", "fun vectorFixtureAadContext"),
            KAT_HARNESS_PATH to listOf("object SkaldVaultV1ProviderKatFixtures"),
        )
        commonFixtureEvidence.forEach { (path, evidence) ->
            val source = productionSource(path)
            evidence.forEach { expected -> assertTrue(source.contains(expected), "$path is missing $expected") }
        }

        val catalogDefinitionFiles = SourceGuardCorpus.testSourceFiles.filter { file ->
            CATALOG_DECLARATION.containsMatchIn(SourceGuardCorpus.text(file))
        }
        assertEquals(
            listOf(SYNTHETIC_CATALOG_PATH),
            catalogDefinitionFiles.map(SourceGuardCorpus::relativePath),
        )
        assertTrue(
            SourceGuardCorpus.productionRuntimeSourceFiles.none { file ->
                CATALOG_DECLARATION.containsMatchIn(SourceGuardCorpus.text(file))
            },
        )
    }

    @Test
    fun earlierDirectCryptoLineDoesNotFlowThroughProviderSelection() {
        val directCryptoFiles = DIRECT_CRYPTO_PATHS.map { path -> productionFile(path) }
        val providerReferences = directCryptoFiles.filter { file ->
            SourceGuardCorpus.text(file).contains("VaultCryptoProvider")
        }
        assertTrue(providerReferences.isEmpty(), "Earlier direct crypto entry points must be inventoried as provider-boundary bypasses.")

        val selection = VaultCryptoProviderSelectionRegistry.select()
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun earlierBinaryFormatsAndNewerSyntheticCatalogDoNotOverlap() {
        EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures.forEach { fixture ->
            val bytes = fixture.bytesForDisabledScaffoldRequestOnly()
            assertIs<SkaldVaultV1ContainerResult.Rejected>(SkaldVaultV1ContainerFormat.parse(bytes))
            assertIs<SkaldVaultV1ManifestResult.Rejected>(SkaldVaultV1ManifestFormat.parse(bytes))
        }

        val serializedContainer = assertIs<SkaldVaultV1ContainerResult.Accepted<ByteArray>>(
            SkaldVaultV1ContainerFormat.serialize(SkaldVaultV1ContainerFormat.vectorFixtureContainer()),
        ).value
        val serializedManifest = assertIs<SkaldVaultV1ManifestResult.Accepted<ByteArray>>(
            SkaldVaultV1ManifestFormat.serialize(SkaldVaultV1ManifestFormat.vectorFixtureManifest()),
        ).value

        listOf(serializedContainer, serializedManifest).forEach { bytes ->
            val result = EncryptedVaultWorkingParser.parse(
                EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(bytes),
            )
            assertEquals(EncryptedVaultWorkingParserStatus.FailedClosed, result.status)
            assertFalse(result.accepted)
            assertEquals(EncryptedVaultWorkingParserSyntheticClassification.NotClassified, result.classification)
            assertEquals(0, result.producedByteCount)
            assertFalse(result.inputBytesExposed)
            assertFalse(result.sectionBytesExposed)
            assertFalse(result.parsedPayloadByteArraysCreated)
        }
    }

    private fun productionFile(path: String) =
        SourceGuardCorpus.productionRuntimeSourceFiles.single { file ->
            SourceGuardCorpus.relativePath(file) == path
        }

    private fun productionSource(path: String): String =
        SourceGuardCorpus.text(productionFile(path))

    private fun docsSource(path: String): String =
        SourceGuardCorpus.docsFiles
            .single { file -> SourceGuardCorpus.relativePath(file) == path }
            .let(SourceGuardCorpus::text)

    private fun functionDeclarationCount(source: String, functionName: String): Int =
        Regex("""(?m)^[ \t]*(?:public[ \t]+)?fun[ \t]+${Regex.escape(functionName)}[ \t]*\(""")
            .findAll(source)
            .count()

    private fun overrideFunctionDeclarationCount(source: String, functionName: String): Int =
        Regex("""(?m)^[ \t]*override[ \t]+fun[ \t]+${Regex.escape(functionName)}[ \t]*\(""")
            .findAll(source)
            .count()

    private fun expectDeclaration(functionName: String): Regex =
        Regex("""(?m)^[ \t]*internal[ \t]+expect[ \t]+fun[ \t]+${Regex.escape(functionName)}[ \t]*\(""")

    private fun actualDeclaration(functionName: String): Regex =
        Regex("""(?m)^[ \t]*internal[ \t]+actual[ \t]+fun[ \t]+${Regex.escape(functionName)}[ \t]*\(""")

    private fun String.literalCount(literal: String): Int {
        var count = 0
        var startIndex = 0
        while (true) {
            val matchIndex = indexOf(literal, startIndex)
            if (matchIndex < 0) return count
            count += 1
            startIndex = matchIndex + literal.length
        }
    }

    private companion object {
        const val AUDIT_STATUS = "INVENTORY_COMPLETE_DESIGN_DECISION_REQUIRED"
        const val AUDIT_DOCUMENT_NAME =
            "ENCRYPTED_LOCAL_VAULT_V1_FORMAT_PARSER_CRYPTO_RECONCILIATION_AUDIT.md"
        const val AUDIT_DOCUMENT_PATH = "docs/$AUDIT_DOCUMENT_NAME"
        const val CANONICAL_DECISION_DOCUMENT_NAME =
            "ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION.md"
        const val CANONICAL_DECISION_STATUS =
            "CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED"

        const val SECURITY_ROOT =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security"
        const val ANDROID_SECURITY_ROOT =
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security"
        const val DESKTOP_SECURITY_ROOT =
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security"
        const val CONTAINER_FORMAT_PATH = "$SECURITY_ROOT/SkaldVaultV1ContainerFormat.kt"
        const val MANIFEST_FORMAT_PATH = "$SECURITY_ROOT/SkaldVaultV1ManifestFormat.kt"
        const val HEADER_COMMITMENT_PATH = "$SECURITY_ROOT/SkaldVaultV1HeaderCommitment.kt"
        const val RECORD_AEAD_PATH = "$SECURITY_ROOT/SkaldVaultV1RecordAead.kt"
        const val ARGON2_PATH = "$SECURITY_ROOT/SkaldVaultV1Argon2idRootDerivation.kt"
        const val WORKING_PARSER_PATH = "$SECURITY_ROOT/EncryptedVaultWorkingParser.kt"
        const val PROVIDER_SELECTION_PATH = "$SECURITY_ROOT/VaultCryptoProviderSelection.kt"
        const val KAT_HARNESS_PATH = "$SECURITY_ROOT/SkaldVaultV1StillDisabledProviderKatHarness.kt"
        const val CANONICAL_DECISION_PATH =
            "$SECURITY_ROOT/EncryptedVaultV1CanonicalArchitectureDecision.kt"
        const val SYNTHETIC_CATALOG_PATH =
            "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/security/" +
                "EncryptedVaultParserWriterSyntheticVectorCatalog.kt"

        val RAW_BYTE_PARSE_DECLARATION =
            Regex("""(?m)^[ \t]*fun[ \t]+parse[ \t]*\([ \t]*bytes:[ \t]*ByteArray[ \t]*\)""")
        val SYNTHETIC_PARSE_DECLARATION =
            Regex(
                """(?m)^[ \t]*fun[ \t]+parse[ \t]*\([ \t]*request:[ \t]*""" +
                    """EncryptedVaultWorkingParserRequest[ \t]*\)""",
            )
        val SERIALIZE_DECLARATION =
            Regex("""(?m)^[ \t]*fun[ \t]+serialize[ \t]*\(""")
        val CATALOG_DECLARATION =
            Regex("""(?m)^[ \t]*object[ \t]+EncryptedVaultParserWriterSyntheticVectorCatalog[ \t]*\{""")
        val CANONICAL_DECISION_POLICY_DECLARATION =
            Regex("""(?m)^[ \t]*object[ \t]+EncryptedVaultV1CanonicalArchitecturePolicy[ \t]*\{""")
        const val CANONICAL_DECISION_PRODUCER_CALL =
            "EncryptedVaultV1CanonicalArchitecturePolicy.currentDecision"
        val KOTLIN_STRING_LITERAL = Regex("""\"([^\"\\]*(?:\\.[^\"\\]*)*)\"""")

        val EXPECTED_PRODUCTION_OBJECT_DECLARATIONS = linkedMapOf(
            "SkaldVaultV1ContainerFormat" to CONTAINER_FORMAT_PATH,
            "SkaldVaultV1ManifestFormat" to MANIFEST_FORMAT_PATH,
            "SkaldVaultV1HeaderCommitment" to HEADER_COMMITMENT_PATH,
            "SkaldVaultV1RecordAead" to RECORD_AEAD_PATH,
            "EncryptedVaultWorkingParser" to WORKING_PARSER_PATH,
            "VaultCryptoProviderSelectionRegistry" to PROVIDER_SELECTION_PATH,
        )

        val PRIMITIVE_NAMES = listOf(
            "skaldVaultV1Argon2idRootMaterial",
            "skaldVaultV1HmacSha256",
            "skaldVaultV1TinkRecordAeadEncrypt",
            "skaldVaultV1TinkRecordAeadDecrypt",
        )

        val DIRECT_CRYPTO_PATHS = listOf(
            ARGON2_PATH,
            HEADER_COMMITMENT_PATH,
            RECORD_AEAD_PATH,
            "$ANDROID_SECURITY_ROOT/AndroidSkaldVaultV1Argon2idRootDerivation.kt",
            "$ANDROID_SECURITY_ROOT/AndroidSkaldVaultV1HeaderCommitmentCrypto.kt",
            "$ANDROID_SECURITY_ROOT/AndroidSkaldVaultV1RecordAead.kt",
            "$DESKTOP_SECURITY_ROOT/DesktopSkaldVaultV1Argon2idRootDerivation.kt",
            "$DESKTOP_SECURITY_ROOT/DesktopSkaldVaultV1HeaderCommitmentCrypto.kt",
            "$DESKTOP_SECURITY_ROOT/DesktopSkaldVaultV1RecordAead.kt",
        )

        val ALL_AUDITED_NAMED_CALLS = listOf(
            "SkaldVaultV1ContainerFormat.serialize",
            "SkaldVaultV1ContainerFormat.parse",
            "SkaldVaultV1ManifestFormat.serialize",
            "SkaldVaultV1ManifestFormat.parse",
            "EncryptedVaultWorkingParser.parse",
            "SkaldVaultV1HeaderCommitment.expandRootMaterial",
            "SkaldVaultV1HeaderCommitment.computeHeaderCommitment",
            "SkaldVaultV1HeaderCommitment.verifyHeaderCommitment",
            "SkaldVaultV1RecordAead.encryptRecord",
            "SkaldVaultV1RecordAead.decryptRecord",
        )

        val CROSS_LINK_DOCUMENT_PATHS = listOf(
            "README.md",
            "docs/ENCRYPTED_LOCAL_VAULT_DESIGN.md",
            "docs/ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md",
            "docs/ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION.md",
            "docs/ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION.md",
            "docs/ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_VALIDATION_COMPLETION_AUDIT.md",
            "docs/ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_STRICT_MATCH_REDACTION_CORRECTION.md",
            "docs/ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md",
            "docs/ENCRYPTED_VAULT_READINESS_POLICY.md",
            "docs/SECURE_STORAGE_DESIGN.md",
            "docs/SECURE_METADATA_BOUNDARY.md",
        )
    }
}
