package com.libertasprimordium.skald

/**
 * Test-side ownership checks for this explicitly reviewed prototype graph.
 * Complements the compiler's actual source graph and final artifact inventory;
 * neither this source inventory nor the existing lexer is a Kotlin/Gradle sandbox.
 */
internal object PrototypeQuarantineBoundary {
    const val COMPATIBILITY_PATH =
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PrototypeKdfCompatibility.kt"

    val movedPaths: Map<String, String> = linkedMapOf(
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ContainerFormat.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ContainerFormat.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ManifestFormat.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ManifestFormat.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1HeaderCommitment.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1HeaderCommitment.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RecordAead.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RecordAead.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1Argon2idRootDerivation.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1Argon2idRootDerivation.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicy.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicy.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt",
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultWorkingParser.kt" to
            "composeApp/src/prototypeTestSupport/kotlin/com/libertasprimordium/skald/security/EncryptedVaultWorkingParser.kt",
        "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1Argon2idRootDerivation.kt" to
            "composeApp/src/androidPrototypeTestSupport/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1Argon2idRootDerivation.kt",
        "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1HeaderCommitmentCrypto.kt" to
            "composeApp/src/androidPrototypeTestSupport/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1HeaderCommitmentCrypto.kt",
        "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1PassphrasePolicy.kt" to
            "composeApp/src/androidPrototypeTestSupport/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1PassphrasePolicy.kt",
        "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1RecordAead.kt" to
            "composeApp/src/androidPrototypeTestSupport/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1RecordAead.kt",
        "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1Argon2idRootDerivation.kt" to
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1Argon2idRootDerivation.kt",
        "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1HeaderCommitmentCrypto.kt" to
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1HeaderCommitmentCrypto.kt",
        "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1PassphrasePolicy.kt" to
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1PassphrasePolicy.kt",
        "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1RecordAead.kt" to
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1RecordAead.kt",
    )

    // Exact top-level declarations, platform expect/actuals, generated file-facade names,
    // and distinctive fixture methods. Nested classes remain owned by forbidden parents.
    // The two inert KDF types are deliberately absent; the compatibility body is checked below.
    val forbiddenIdentifiers: Set<String> = setOf(
        "AndroidSkaldVaultV1Argon2idRootDerivationKt",
        "AndroidSkaldVaultV1HeaderCommitmentCryptoKt",
        "AndroidSkaldVaultV1PassphrasePolicyKt",
        "AndroidSkaldVaultV1RecordAeadKt",
        "DesktopSkaldVaultV1Argon2idRootDerivationKt",
        "DesktopSkaldVaultV1HeaderCommitmentCryptoKt",
        "DesktopSkaldVaultV1PassphrasePolicyKt",
        "DesktopSkaldVaultV1RecordAeadKt",
        "EncryptedVaultWorkingParser",
        "EncryptedVaultWorkingParserBlocker",
        "EncryptedVaultWorkingParserEvidence",
        "EncryptedVaultWorkingParserKind",
        "EncryptedVaultWorkingParserKt",
        "EncryptedVaultWorkingParserPolicyLabel",
        "EncryptedVaultWorkingParserRedactedDiagnostics",
        "EncryptedVaultWorkingParserRequest",
        "EncryptedVaultWorkingParserResult",
        "EncryptedVaultWorkingParserSafeLabel",
        "EncryptedVaultWorkingParserSourceSet",
        "EncryptedVaultWorkingParserStatus",
        "EncryptedVaultWorkingParserSyntheticClassification",
        "SkaldVaultV1Argon2idRootDerivation",
        "SkaldVaultV1Argon2idRootDerivationKt",
        "SkaldVaultV1Argon2idRootDerivationRejectionReason",
        "SkaldVaultV1Argon2idRootDerivationResult",
        "SkaldVaultV1CandidateRecordDescriptor",
        "SkaldVaultV1CanonicalHeader",
        "SkaldVaultV1Container",
        "SkaldVaultV1ContainerFormat",
        "SkaldVaultV1ContainerFormatKt",
        "SkaldVaultV1ContainerManifestRecordState",
        "SkaldVaultV1ContainerManifestSection",
        "SkaldVaultV1ContainerRecordEntry",
        "SkaldVaultV1ContainerRejectionReason",
        "SkaldVaultV1ContainerResult",
        "SkaldVaultV1ExpandedKeys",
        "SkaldVaultV1HeaderCommitment",
        "SkaldVaultV1HeaderCommitmentKt",
        "SkaldVaultV1HeaderCommitmentRejectionReason",
        "SkaldVaultV1HeaderCommitmentResult",
        "SkaldVaultV1KeyPurpose",
        "SkaldVaultV1Manifest",
        "SkaldVaultV1ManifestFormat",
        "SkaldVaultV1ManifestFormatKt",
        "SkaldVaultV1ManifestRecordEntry",
        "SkaldVaultV1ManifestRejectionReason",
        "SkaldVaultV1ManifestResult",
        "SkaldVaultV1NormalizedPassphrase",
        "SkaldVaultV1PassphrasePolicy",
        "SkaldVaultV1PassphrasePolicyKt",
        "SkaldVaultV1PassphrasePolicyResult",
        "SkaldVaultV1PassphraseRejectionReason",
        "SkaldVaultV1ProviderKatEvidence",
        "SkaldVaultV1ProviderKatExpectedVectors",
        "SkaldVaultV1ProviderKatFailureReason",
        "SkaldVaultV1ProviderKatFixtures",
        "SkaldVaultV1ProviderKatHarnessResult",
        "SkaldVaultV1ProviderKatRecordAeadAdapter",
        "SkaldVaultV1ProviderKatRecordAeadBuildingBlock",
        "SkaldVaultV1ProviderKatRequest",
        "SkaldVaultV1ProviderKatStage",
        "SkaldVaultV1RecordAadContext",
        "SkaldVaultV1RecordAead",
        "SkaldVaultV1RecordAeadKt",
        "SkaldVaultV1RecordAeadRejectionReason",
        "SkaldVaultV1RecordAeadResult",
        "SkaldVaultV1RecordCiphertext",
        "SkaldVaultV1RecordPlaintext",
        "SkaldVaultV1RecordType",
        "SkaldVaultV1RootMaterial",
        "SkaldVaultV1StaleRecordDecision",
        "SkaldVaultV1StaleRecordDecisionKind",
        "SkaldVaultV1StillDisabledProviderKatHarness",
        "SkaldVaultV1StillDisabledProviderKatHarnessKt",
        "skaldVaultV1Argon2idRootMaterial",
        "skaldVaultV1HmacSha256",
        "skaldVaultV1NormalizeNfc",
        "skaldVaultV1RecordAeadPrimitive",
        "skaldVaultV1TinkRecordAeadDecrypt",
        "skaldVaultV1TinkRecordAeadEncrypt",
        "vectorFixtureContainer",
        "vectorFixtureManifest",
        "vectorFixtureHeader",
        "vectorFixtureAadContext",
    )

    fun acceptsProductionSource(path: String, source: String): Boolean {
        val scanned = VaultSourceLexicalScan.scan(source)
        if (scanned !is VaultSourceLexicalScan.Accepted) return false
        val identifiers = Regex("""\b[A-Za-z_][A-Za-z0-9_]*\b""").findAll(scanned.code)
        if (identifiers.any { it.value in forbiddenIdentifiers }) return false
        return path != COMPATIBILITY_PATH || acceptsCompatibilityCode(scanned.code)
    }

    private fun acceptsCompatibilityCode(code: String): Boolean {
        var remaining = compact(code)
        for (declaration in compatibilityDeclarations) {
            val scanned = VaultSourceLexicalScan.scan(declaration)
            if (scanned !is VaultSourceLexicalScan.Accepted) return false
            val expected = compact(scanned.code)
            if (remaining.split(expected).size != 2) return false
            remaining = remaining.replace(expected, "")
        }
        return remaining.isEmpty()
    }

    private fun compact(value: String): String = value.filterNot(Char::isWhitespace)

    // An explicit material-free declaration grammar, not a source-generated whitelist or hash.
    // Defaults/enum labels/data-class behavior also have runtime compatibility assertions.
    private val compatibilityDeclarations = listOf(
        "package com.libertasprimordium.skald.security",
        """
        enum class SkaldVaultV1Argon2idType(val label: String) {
            Argon2id("Argon2id"),
            Argon2i("Argon2i"),
            Argon2d("Argon2d"),
        }
        """,
        """
        data class SkaldVaultV1Argon2idParameters(
            val type: SkaldVaultV1Argon2idType = SkaldVaultV1Argon2idType.Argon2id,
            val version: Int = SkaldVaultV1PrototypeKdfCompatibility.ARGON2_VERSION_19,
            val memoryKiB: Int = SkaldVaultV1PrototypeKdfCompatibility.MEMORY_KIB,
            val iterations: Int = SkaldVaultV1PrototypeKdfCompatibility.ITERATIONS,
            val parallelism: Int = SkaldVaultV1PrototypeKdfCompatibility.PARALLELISM,
            val outputBytes: Int = SkaldVaultV1PrototypeKdfCompatibility.OUTPUT_BYTES,
        )
        """,
        """
        object SkaldVaultV1PrototypeKdfCompatibility {
            const val ARGON2_VERSION_19 = 19
            const val MEMORY_KIB = 65_536
            const val ITERATIONS = 3
            const val PARALLELISM = 1
            const val OUTPUT_BYTES = 64
        }
        """,
    )

    fun acceptsBuildConfiguration(source: String): Boolean {
        val scanned = VaultSourceLexicalScan.scan(source)
        if (scanned !is VaultSourceLexicalScan.Accepted) return false
        val openings = Regex("""\bsourceSets\s*\{""").findAll(scanned.code).toList()
        if (openings.size != 1) return false
        val start = openings.single().range.first
        val openingBrace = scanned.code.indexOf('{', start)
        var depth = 1
        var end = openingBrace + 1
        while (end < scanned.code.length && depth > 0) {
            when (scanned.code[end]) { '{' -> depth++; '}' -> depth-- }
            end++
        }
        if (depth != 0) return false
        // Deliberately exact per-line DSL, including literal directory ownership.
        // A future graph change needs review, not an automatic inventory refresh.
        if (reviewedLines(source.substring(start, end)) != reviewedLines(reviewedSourceSets)) return false
        var outside = source.removeRange(start, end)
        for (binding in reviewedAndroidResourceBindings) {
            if (outside.split(binding).size != 2) return false
            outside = outside.replace(binding, "")
        }
        val remainder = VaultSourceLexicalScan.scan(outside)
        if (remainder !is VaultSourceLexicalScan.Accepted) return false
        // The two existing Gradle imports contain the namespace segment api, not api(...) wiring.
        // Remove only those exact declarations; aliases or additional statements are not hidden.
        val executableRemainder = Regex(
            """(?m)^\s*import\s+org\.gradle\.api\.tasks\.testing\.(?:Test|logging\.TestLogEvent)\s*$""",
        ).replace(remainder.code, "")
        return Regex("""\b[A-Za-z_][A-Za-z0-9_]*\b""").findAll(executableRemainder)
            .none { it.value in unreviewedGraphBindings }
    }

    private fun reviewedLines(value: String): List<String> = value.lines().map(String::trim).filter(String::isNotEmpty)

    // Source-path/classpath attachment outside the exact block needs separate review.
    // This bounded DSL guard does not claim to interpret arbitrary Gradle code.
    private val unreviewedGraphBindings = setOf(
        "sourceSets", "dependsOn", "srcDir", "srcDirs", "srcFile", "srcFiles", "setSrcDirs",
        "source", "setSource", "setFrom", "fileTree", "files", "compilations", "associateWith",
        "compileClasspath", "runtimeClasspath", "classpath", "implementation", "api", "runtimeOnly",
        "prototypeTestSupport", "androidPrototypeTestSupport", "apply",
    )
    private val reviewedAndroidResourceBindings = listOf(
        """sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")""",
        """sourceSets["main"].res.srcDirs("src/androidMain/res")""",
    )
    private val reviewedSourceSets = """
sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.bouncycastle.provider)
                implementation(libs.tink.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.bouncycastle.provider)
                implementation(libs.tink.jvm)
            }
        }

        val commonTest by getting {
            // Shared helper declarations only; no production source set owns this directory.
            kotlin.srcDir("src/prototypeTestSupport/kotlin")
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidUnitTest by getting {
            kotlin.srcDir("src/androidPrototypeTestSupport/kotlin")
        }

        // A common ancestor for device-test expect declarations, without the common test suite.
        val prototypeTestSupport by creating

        val androidInstrumentedTest by getting {
            // Share helpers, not the common @Test suite, with device tests.
            dependsOn(prototypeTestSupport)
            kotlin.srcDir("src/androidPrototypeTestSupport/kotlin")
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.runner)
            }
        }
    }
    """
}
