package com.libertasprimordium.skald

import java.io.File
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProductionBackendAdapterSourceGuardTest {
    @Test
    fun productionBackendAdapterModelsDoNotImportBdkOrClientApis() {
        val root = repositoryRoot()
        val files = boundaryFiles(root)
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSyncWithRevealedSpks\b"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Production backend boundary must remain BDK/client/process free: $offenders")
    }

    @Test
    fun endpointPolicyDoesNotAcceptCredentialFixtureStringsOrPublicDefaults() {
        val root = repositoryRoot()
        val combined = boundaryFiles(root).joinToString("\n") { it.readText() }
        val forbiddenLiteralPatterns = listOf(
            Regex("""(?i)DEMO_VALUE_DO_NOT_USE"""),
            Regex("""(?i)password\s*="""),
            Regex("""(?i)token\s*="""),
            Regex("""(?i)cookie\s*="""),
            Regex("""(?i)macaroon\s*="""),
            Regex("""(?i)nsec1[A-Za-z0-9]+"""),
            Regex("""(?i)\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b"""),
            Regex("""\b[0-9a-fA-F]{64}\b"""),
        )
        val defaultEndpointPatterns = listOf(
            Regex("""(?i)skald.*electrum"""),
            Regex("""(?i)skald.*esplora"""),
            Regex("""(?i)skald.*bitcoin.*core"""),
        )

        forbiddenLiteralPatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(combined), "Boundary contains forbidden fixture-like text.")
        }
        defaultEndpointPatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(combined), "Boundary appears to define a Skald-managed endpoint.")
        }
    }

    @Test
    fun commonProductionSourceDoesNotUseProductionNetworkingApis() {
        val root = repositoryRoot()
        val commonMain = File(root, "composeApp/src/commonMain")
        val forbiddenPatterns = listOf(
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
        )
        val offenders = commonMain
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(offenders.isEmpty(), "Common production source must not add networking/process APIs: $offenders")
    }

    @Test
    fun disabledSyncStatusUiFilesDoNotImportBdkOrClientApis() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/NodesScreen.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/components/BitcoinWalletSyncStatusUiModel.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSyncWithRevealedSpks\b"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Disabled sync status UI must remain BDK/client/process free: $offenders")
    }

    @Test
    fun recoveryPrivacyStatusFilesDoNotImportBdkOrClientApis() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/recovery/RecoverySyncStatusModels.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/privacy/PrivacySyncStatusModels.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/RecoveryScreen.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/OverviewScreen.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSyncWithRevealedSpks\b"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Recovery/Privacy sync status files must remain BDK/client/process/persistence free: $offenders")
    }

    @Test
    fun secureMetadataBoundaryDoesNotPersistOrImportWalletClients() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SecureMetadataStorage.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bjava\.io\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Secure metadata boundary must remain disabled and persistence/client free: $offenders")
    }

    @Test
    fun encryptedVaultReadinessModelsDoNotImplementCryptoOrStorage() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootSettingsPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxCustomRootValidationPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxRootResolutionPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootResolver.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageSafetyPreflightBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LockSessionLifecycleBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RedactionLeakageBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RuntimeRandomnessAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1KdfCalibrationAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1SecureStorageAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1UnlockAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1CreationAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1AuthorizationReadinessMatrix.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderCandidatePackagingBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderDependencyBuildBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderSelectionPromotionBlockers.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderInterfaceContractAudit.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1NonSelectableProviderSkeletonBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderRegistryIsolationGuard.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderFactoryIsolationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationDispatchIsolationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderKatExecutionIsolationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatDecisionGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatPrerequisiteAudit.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorContract.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespace.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinement.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationDecision.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAudit.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecision.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationContract.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuard.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockers.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverage.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuard.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationPlan.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security\.KeyStore"""),
            Regex("""import\s+java\.security\.SecureRandom"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+java\.util\.UUID"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""import\s+com\.ionspin"""),
            Regex("""import\s+com\.goterl"""),
            Regex("""\bCipher\("""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\bKeysetHandle\."""),
            Regex("""\bKeysetHandle\("""),
            Regex("""\bCleartextKeysetHandle\b"""),
            Regex("""\bKeysetManager\b"""),
            Regex("""\bJsonKeysetWriter\b"""),
            Regex("""\bBinaryKeysetWriter\b"""),
            Regex("""\bJsonKeysetReader\b"""),
            Regex("""\bBinaryKeysetReader\b"""),
            Regex("""\bTinkJsonProtoKeysetFormat\b"""),
            Regex("""\bTinkProtoKeysetFormat\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bStrongBoxUnavailableException\b"""),
            Regex("""\bMac\("""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHMac\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bHKDFParameters\b"""),
            Regex("""\bSHA256Digest\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bSecureRandom\("""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bUUID\b"""),
            Regex("""\bcurrentTimeMillis\b"""),
            Regex("""\bnanoTime\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bjava\.io\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Encrypted vault readiness must remain policy-only: $offenders")
    }

    @Test
    fun identityImplementationModelBoundariesRemainCoveredBySourceGuard() {
        val root = repositoryRoot()
        val sourceGuard = File(
            root,
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt",
        )
        val guardedFilesBlock = sourceGuard.readText()
            .substringAfter("fun encryptedVaultReadinessModelsDoNotImplementCryptoOrStorage()")
            .substringBefore("        val forbiddenPatterns")
        val requiredGuardedPaths = listOf(
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespace.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinement.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationDecision.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAudit.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecision.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationContract.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGate.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuard.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockers.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverage.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuard.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGate.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityImplementationPlan.kt",
        )
        val missing = requiredGuardedPaths.filterNot { it in guardedFilesBlock }

        assertTrue(
            missing.isEmpty(),
            "Identity implementation model boundaries must stay covered by the encrypted vault source guard: $missing",
        )
    }

    @Test
    fun testOnlyProviderIdentityMarkerDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityMarker",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity marker must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityInventoryDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityInventory",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity inventory must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProfileDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProfile",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity profile must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProfileValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProfileValidation",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity profile validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityReachabilityProofDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityReachabilityProof",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity reachability proof must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityCapabilityMatrixDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrix",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity capability matrix must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatFixtureScopeDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatFixtureScope",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity KAT fixture scope must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatFixtureCatalogDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalog",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity KAT fixture catalog must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatFixtureValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidation",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest inert provider identity KAT fixture validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatPublicVectorAdmissionDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmission",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity KAT public-vector admission gate must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatPublicVectorFixtureDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixture",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity KAT public vector fixture must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatPublicVectorValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidation",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity KAT public vector validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatCaseBindingDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatCaseBinding",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity KAT case binding must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityKatCaseBindingValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidation",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity KAT case-binding validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityExecutableKatAdmissionDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmission",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity executable KAT admission gate must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityExecutableMetadataKatDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity executable metadata KAT must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityExecutableMetadataKatValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity executable metadata KAT validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityExecutableMetadataKatSuiteReportDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity executable metadata KAT suite report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationKatAdmissionDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity provider-operation KAT admission gate must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationMetadataKatDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity provider-operation metadata KAT must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationMetadataKatValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity provider-operation metadata KAT validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationMetadataKatSuiteReportDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity provider-operation metadata KAT suite report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopKatAdmissionDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation KAT admission gate must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopKatDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation KAT must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopKatValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation KAT validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopKatSuiteReportDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation KAT suite report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopExecutionBoundaryDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation execution boundary must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation execution-boundary validation report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity no-op provider-operation execution-boundary suite report must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity synthetic provider-operation trace admission gate must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityProviderOperationSyntheticTraceDoesNotAppearInProductionRuntimeRoots() {
        val root = repositoryRoot()
        val runtimeRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val forbiddenTokens = listOf(
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidation",
            "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary",
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata",
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity",
            "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity",
        )
        val offenders = runtimeRoots
            .filter { it.exists() }
            .flatMap { runtimeRoot ->
                runtimeRoot.walkTopDown()
                    .filter { file -> file.isFile && file.extension in setOf("kt", "kts") }
                    .toList()
            }
            .filter { file ->
                val text = file.readText()
                forbiddenTokens.any { token -> token in text }
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "The commonTest provider identity synthetic provider-operation trace artifact must stay absent from production runtime roots: $offenders",
        )
    }

    @Test
    fun disabledVaultCryptoProviderBoundaryDoesNotImportProvidersOrStorage() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootSettingsPolicy.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+java\.util\.UUID"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""import\s+com\.ionspin"""),
            Regex("""import\s+com\.goterl"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bInsecureNonceXChaCha20Poly1305\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCleartextKeysetHandle\b"""),
            Regex("""\bKeysetManager\b"""),
            Regex("""\bJsonKeysetWriter\b"""),
            Regex("""\bBinaryKeysetWriter\b"""),
            Regex("""\bJsonKeysetReader\b"""),
            Regex("""\bBinaryKeysetReader\b"""),
            Regex("""\bTinkJsonProtoKeysetFormat\b"""),
            Regex("""\bTinkProtoKeysetFormat\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bStrongBoxUnavailableException\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHMac\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bHKDFParameters\b"""),
            Regex("""\bSHA256Digest\b"""),
            Regex("""\bSecureRandom\("""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bUUID\b"""),
            Regex("""\bcurrentTimeMillis\b"""),
            Regex("""\bnanoTime\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bjava\.io\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Disabled vault crypto provider boundary must stay provider/storage free: $offenders")
    }

    @Test
    fun providerDependencyBuildBoundaryDoesNotImportProvidersExecuteCryptoOrEnableSelection() {
        val root = repositoryRoot()
        val file = File(
            root,
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderDependencyBuildBoundary.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bSecureRandom\("""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""\bFile\("""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\b"""),
            Regex("""\bLogger\b"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(file.readText()) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider dependency build boundary must remain build-evidence-only: $offenders",
        )
    }

    @Test
    fun providerSelectionPromotionBlockersDoNotImportProvidersExecuteCryptoOrEnableSelection() {
        val root = repositoryRoot()
        val file = File(
            root,
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderSelectionPromotionBlockers.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bSecureRandom\("""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""class\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\("""),
            Regex("""\bFile\("""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\b"""),
            Regex("""\bLogger\b"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(file.readText()) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider selection promotion blockers must remain model-only: $offenders",
        )
    }

    @Test
    fun argon2idCalibrationPolicyDoesNotImportProvidersOrStorage() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/Argon2idCalibrationPolicy.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+com\.ionspin"""),
            Regex("""import\s+com\.goterl"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bArgon2Parameters\b"""),
            Regex("""\bInsecureNonceXChaCha20Poly1305\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bVaultCryptoProviderSelectionRegistry\b"""),
            Regex("""\bDisabledVaultCryptoProvider\b"""),
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bVaultManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bStorageIndex(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bVaultContainer(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bperformDowngrade\("""),
            Regex("""\bapplyDowngrade\("""),
            Regex("""\ballowDowngrade\("""),
            Regex("""\bdowngradeParameters\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\breencryptVault\("""),
            Regex("""\breEncryptVault\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bcurrentTimeMillis\b"""),
            Regex("""\bnanoTime\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bStrongBoxUnavailableException\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Argon2id calibration policy must stay provider/storage free: $offenders")
    }

    @Test
    fun vaultCryptoDependenciesArePinnedAndScopedToPlatformProbes() {
        val root = repositoryRoot()
        val catalog = File(root, "gradle/libs.versions.toml").readText()
        val build = File(root, "composeApp/build.gradle.kts").readText()
        val commonMainBlock = build.substringAfter("val commonMain by getting")
            .substringBefore("val androidMain by getting")

        assertTrue(catalog.contains("""tink = "1.21.0""""))
        assertTrue(catalog.contains("""bouncycastle = "1.84""""))
        assertTrue(catalog.contains("""androidx-test-ext-junit = "1.2.1""""))
        assertTrue(catalog.contains("""androidx-test-runner = "1.6.2""""))
        assertTrue(catalog.contains("""tink-android = { module = "com.google.crypto.tink:tink-android", version.ref = "tink" }"""))
        assertTrue(catalog.contains("""tink-jvm = { module = "com.google.crypto.tink:tink", version.ref = "tink" }"""))
        assertTrue(catalog.contains("""bouncycastle-provider = { module = "org.bouncycastle:bcprov-jdk18on", version.ref = "bouncycastle" }"""))
        assertTrue(catalog.contains("""androidx-test-ext-junit = { module = "androidx.test.ext:junit", version.ref = "androidx-test-ext-junit" }"""))
        assertTrue(catalog.contains("""androidx-test-runner = { module = "androidx.test:runner", version.ref = "androidx-test-runner" }"""))
        assertTrue(build.contains("implementation(libs.tink.android)"))
        assertTrue(build.contains("implementation(libs.tink.jvm)"))
        assertTrue(build.contains("implementation(libs.bouncycastle.provider)"))
        assertTrue(build.contains("val androidInstrumentedTest by getting"))
        assertTrue(build.contains("implementation(libs.androidx.test.ext.junit)"))
        assertTrue(build.contains("implementation(libs.androidx.test.runner)"))
        assertTrue(build.contains("""testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner""""))
        assertTrue(build.contains("""excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF""""))
        assertFalse(commonMainBlock.contains("libs.tink"))
        assertFalse(commonMainBlock.contains("libs.bouncycastle"))
        assertFalse(catalog.contains("lazysodium"))
        assertFalse(build.contains("libs.lazysodium"))
        assertFalse(build.contains("com.goterl"))
        assertFalse(build.contains("com.ionspin"))
    }

    @Test
    fun cryptoApiImportsStayConfinedToDependencyCompileProbes() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val allowedFiles = setOf(
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCryptoDependencyCompileProbe.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1Argon2idRootDerivation.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1HeaderCommitmentCrypto.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1RecordAead.kt",
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidArgon2idCalibrationProbeTest.kt",
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidKatValidationTest.kt",
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidRecordAeadBuildingBlockTest.kt",
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTinkRawKeyFeasibilityProbeTest.kt",
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTestProviderKatHarnessTest.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopVaultCryptoDependencyCompileProbe.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1Argon2idRootDerivation.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1HeaderCommitmentCrypto.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1RecordAead.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoArgon2idCalibrationProbeTest.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCanonicalHeaderHkdfHmacVectorTest.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoKnownAnswerVectorTest.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTinkRawKeyFeasibilityProbeTest.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTestProviderKatHarnessTest.kt",
        )
        val cryptoImportPattern = Regex(
            """import\s+(com\.google\.crypto\.tink|com\.goterl\.lazysodium|org\.bouncycastle|javax\.crypto|java\.security\.KeyStore)""",
        )
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file ->
                val relative = file.relativeTo(root).invariantSeparatorsPath
                relative !in allowedFiles && cryptoImportPattern.containsMatchIn(file.readText())
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(offenders.isEmpty(), "Crypto imports must stay confined to dependency compile probes: $offenders")
    }

    @Test
    fun tinkRecordAeadExecutionStaysInApprovedBuildingBlocks() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val allowedFiles = setOf(
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RecordAead.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCryptoDependencyCompileProbe.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1RecordAead.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopVaultCryptoDependencyCompileProbe.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1RecordAead.kt",
        )
        val tinkAeadPatterns = listOf(
            Regex("""import\s+com\.google\.crypto\.tink"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bSecretBytes\b"""),
            Regex("""\bInsecureSecretKeyAccess\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bRegistryConfiguration\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bskaldVaultV1TinkRecordAeadEncrypt\("""),
            Regex("""\bskaldVaultV1TinkRecordAeadDecrypt\("""),
        )
        val offenders = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative !in allowedFiles &&
                            tinkAeadPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            offenders.isEmpty(),
            "Tink record AEAD execution must stay in approved still-disabled building blocks: $offenders",
        )
    }

    @Test
    fun recordAeadBuildingBlocksDoNotPersistGenerateRandomKeysUseInternalApisOrLog() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RecordAead.kt"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1RecordAead.kt"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1RecordAead.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""com\.google\.crypto\.tink\.aead\.internal"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bCleartextKeysetHandle\b"""),
            Regex("""\bKeysetManager\b"""),
            Regex("""\bJsonKeysetWriter\b"""),
            Regex("""\bBinaryKeysetWriter\b"""),
            Regex("""\bJsonKeysetReader\b"""),
            Regex("""\bBinaryKeysetReader\b"""),
            Regex("""\bTinkJsonProtoKeysetFormat\b"""),
            Regex("""\bTinkProtoKeysetFormat\b"""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bgenerateEntryFromParameters\b"""),
            Regex("""\bwithRandomId\("""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "Record AEAD building blocks must not persist, log, generate random keys, or use internal APIs: $offenders",
        )
    }

    @Test
    fun tinkRawKeyProbeDoesNotUseForbiddenKeysetPersistenceGenerationOrInternalApis() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTinkRawKeyFeasibilityProbeTest.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTinkRawKeyFeasibilityProbeTest.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""com\.google\.crypto\.tink\.aead\.internal"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bCleartextKeysetHandle\b"""),
            Regex("""\bKeysetManager\b"""),
            Regex("""\bJsonKeysetWriter\b"""),
            Regex("""\bBinaryKeysetWriter\b"""),
            Regex("""\bJsonKeysetReader\b"""),
            Regex("""\bBinaryKeysetReader\b"""),
            Regex("""\bTinkJsonProtoKeysetFormat\b"""),
            Regex("""\bTinkProtoKeysetFormat\b"""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bgenerateEntryFromParameters\b"""),
            Regex("""\bwithRandomId\("""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "Tink raw-key probe must avoid keyset persistence, generation, internal APIs, and reflection: $offenders",
        )
    }

    @Test
    fun commonProductionSourceDoesNotUseTinkKeysetPersistenceGenerationInternalApisOrAeadExecution() {
        val root = repositoryRoot()
        val commonMain = File(root, "composeApp/src/commonMain")
        val forbiddenPatterns = listOf(
            Regex("""import\s+com\.google\.crypto\.tink"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bAeadFactory\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bSecretBytes\b"""),
            Regex("""\bInsecureSecretKeyAccess\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCleartextKeysetHandle\b"""),
            Regex("""\bKeysetManager\b"""),
            Regex("""\bJsonKeysetWriter\b"""),
            Regex("""\bBinaryKeysetWriter\b"""),
            Regex("""\bJsonKeysetReader\b"""),
            Regex("""\bBinaryKeysetReader\b"""),
            Regex("""\bTinkJsonProtoKeysetFormat\b"""),
            Regex("""\bTinkProtoKeysetFormat\b"""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bgenerateEntryFromParameters\b"""),
            Regex("""\bwithRandomId\("""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
        )
        val offenders = commonMain
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(
            offenders.isEmpty(),
            "Common production source must not use Tink keyset persistence/generation, internal APIs, or AEAD execution: $offenders",
        )
    }

    @Test
    fun hkdfHmacExecutionStaysInApprovedBuildingBlocksAndTests() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val allowedFiles = setOf(
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1HeaderCommitment.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1HeaderCommitmentCrypto.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1HeaderCommitmentCrypto.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+javax\.crypto"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHMac\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bHKDFParameters\b"""),
            Regex("""\bSHA256Digest\b"""),
            Regex("""\bhkdfSha256\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bskaldVaultV1HmacSha256\("""),
        )
        val offenders = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative !in allowedFiles &&
                            forbiddenPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            offenders.isEmpty(),
            "HKDF/HMAC/header-commitment execution must stay in approved building blocks/tests: $offenders",
        )
    }

    @Test
    fun argon2idExecutionStaysInApprovedBuildingBlocksAndTests() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val allowedFiles = setOf(
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1Argon2idRootDerivation.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1Argon2idRootDerivation.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1Argon2idRootDerivation.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""\bArgon2BytesGenerator\("""),
            Regex("""\bArgon2Parameters\.Builder\("""),
            Regex("""\bARGON2_id\b"""),
            Regex("""\bARGON2_VERSION_13\b"""),
            Regex("""\bwithMemoryAsKB\("""),
            Regex("""\bgenerateBytes\("""),
            Regex("""\bskaldVaultV1Argon2idRootMaterial\("""),
            Regex("""\bderiveRootMaterial\("""),
        )
        val offenders = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative !in allowedFiles &&
                            forbiddenPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            offenders.isEmpty(),
            "Argon2id execution must stay in approved building blocks/tests: $offenders",
        )
    }

    @Test
    fun passphraseNormalizationValidationStaysInApprovedBuildingBlocksAndTests() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val allowedFiles = setOf(
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicy.kt",
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt",
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1PassphrasePolicy.kt",
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1PassphrasePolicy.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.text\.Normalizer"""),
            Regex("""\bNormalizer\.normalize\("""),
            Regex("""\bskaldVaultV1NormalizeNfc\("""),
            Regex("""\bnormalizeAndEncode\("""),
            Regex("""\bSkaldVaultV1PassphrasePolicy\b"""),
        )
        val offenders = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative !in allowedFiles &&
                            forbiddenPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            offenders.isEmpty(),
            "Passphrase normalization/validation must stay in approved building blocks/tests: $offenders",
        )
    }

    @Test
    fun passphraseAndArgon2idBuildingBlocksDoNotLogPersistGenerateRandomnessOrUseAead() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicy.kt"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1PassphrasePolicy.kt"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1PassphrasePolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1Argon2idRootDerivation.kt"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1Argon2idRootDerivation.kt"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1Argon2idRootDerivation.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""import\s+com\.google\.crypto\.tink"""),
            Regex("""\bAead\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCleartextKeysetHandle\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "Passphrase/Argon2id building blocks must not log, persist, generate randomness, or use AEAD: $offenders",
        )
    }

    @Test
    fun stillDisabledProviderKatHarnessDoesNotExposeSelectionStorageManifestLoggingOrPersistence() {
        val root = repositoryRoot()
        val file = File(
            root,
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""\bVaultCryptoProviderSelectionRegistry\b"""),
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bVaultManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bStorageIndex(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bVaultContainer(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bFile\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(file.readText()) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Still-disabled provider KAT harness must not expose selection, storage, manifest, logging, or persistence: $offenders",
        )
    }

    @Test
    fun stillDisabledProviderFacadeDoesNotExposeSelectionStorageCryptoLoggingOrPersistence() {
        val root = repositoryRoot()
        val file = File(
            root,
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt",
        )
        val forbiddenPatterns = listOf(
            Regex("""\bVaultCryptoProviderSelectionRegistry\b"""),
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bVaultManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bStorageIndex(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bVaultContainer(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\breadRecord\("""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security\.SecureRandom"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\("""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bFile\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(file.readText()) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Still-disabled provider facade must remain metadata-only and disabled: $offenders",
        )
    }

    @Test
    fun providerSelectionRegistryDoesNotReferenceStillDisabledProviderFacade() {
        val root = repositoryRoot()
        val file = File(
            root,
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt",
        )
        val source = file.readText()

        assertFalse(source.contains("SkaldVaultV1StillDisabledProviderFacade"))
        assertFalse(source.contains("StillDisabledProviderFacade"))
    }

    @Test
    fun secureRandomImportsStayConfinedToApprovedRuntimeProbesAndBdkValidation() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val allowedFiles = setOf(
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidRuntimeRandomnessProviderProbeTest.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultRuntimeRandomnessProviderProbeTest.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestAddressDerivationValidation.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestElectrumScanAdapter.kt",
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestSeedWalletValidation.kt",
        )
        val importPattern = Regex("""import\s+java\.security\.SecureRandom""")
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file ->
                val relative = file.relativeTo(root).invariantSeparatorsPath
                relative !in allowedFiles && importPattern.containsMatchIn(file.readText())
            }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(offenders.isEmpty(), "SecureRandom imports must stay in approved test/probe files: $offenders")
    }

    @Test
    fun commonSecurityVaultCodeDoesNotUseForbiddenRandomApis() {
        val root = repositoryRoot()
        val securityRoot = File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security")
        val forbiddenPatterns = listOf(
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+java\.util\.UUID"""),
            Regex("""import\s+java\.security\.SecureRandom"""),
            Regex("""\bMath\.random\("""),
            Regex("""\bUUID\.randomUUID\("""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
        )
        val offenders = securityRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(
            offenders.isEmpty(),
            "Common security/vault code must not use forbidden language-level randomness APIs: $offenders",
        )
    }

    @Test
    fun providerKatAndStaleRecordContractsDoNotAddProviderManifestOrStorageWriters() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security"),
        )
        val forbiddenPatterns = listOf(
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bVaultManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bManifest(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bStorageIndex(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bVaultContainer(?:Reader|Writer|Repository|Store)\b"""),
            Regex("""\bVaultContainerParser\b"""),
            Regex("""\bparseVaultContainer\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bPath\("""),
            Regex("""\.resolve\("""),
            Regex("""\.toPath\("""),
            Regex("""\babsoluteFile\b"""),
            Regex("""\bcanonicalFile\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bFiles\.createTempFile\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""import\s+android\.content\.SharedPreferences"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
        )
        val offenders = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            offenders.isEmpty(),
            "Provider KAT/stale-record contracts must not add provider, manifest, container, or storage writers: $offenders",
        )
    }

    @Test
    fun containerManifestStorageContractDoesNotAddStorageImplementations() {
        val root = repositoryRoot()
        val files = listOf(
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootSettingsPolicy.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxCustomRootValidationPolicy.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxRootResolutionPolicy.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootResolver.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformPathConstructionBoundary.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageSafetyPreflightBoundary.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1DisabledStorageServiceFacade.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PersistenceReadinessGate.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LockSessionLifecycleBoundary.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RedactionLeakageBoundary.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt",
            ),
        )
        val forbiddenPatterns = listOf(
            Regex("""\bSelectableProductionVaultCryptoProvider\b"""),
            Regex("""\bProductionVaultCryptoProvider\b"""),
            Regex("""\b(class|object|interface)\s+VaultContainerParser\b"""),
            Regex("""\b(class|object|interface)\s+VaultContainerReader\b"""),
            Regex("""\b(class|object|interface)\s+VaultContainerWriter\b"""),
            Regex("""\bVaultContainerRepository\b"""),
            Regex("""\bManifestReader\b"""),
            Regex("""\bManifestWriter\b"""),
            Regex("""\bManifestRepository\b"""),
            Regex("""\bManifestStore\b"""),
            Regex("""\bStorageIndexReader\b"""),
            Regex("""\bStorageIndexWriter\b"""),
            Regex("""\bStorageIndexRepository\b"""),
            Regex("""\bparseVaultContainer\("""),
            Regex("""\bserializeVaultContainer\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bopenVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bFiles\.createTempFile\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bStrongBoxUnavailableException\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "Container/manifest/storage contract must not add unapproved parser/writer, storage, or secure-storage APIs: $offenders",
        )
    }

    @Test
    fun inMemoryVaultContainerParserWriterStaysInApprovedFileAndDoesNotPersistOrRunCrypto() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security"),
        )
        val approvedParserWriterFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ContainerFormat.kt"
        val parserWriterPatterns = listOf(
            Regex("""\bSkaldVaultV1ContainerFormat\b"""),
            Regex("""\bSkaldVaultV1ContainerResult\b"""),
            Regex("""\bSkaldVaultV1ContainerRecordEntry\b"""),
            Regex("""\bSkaldVaultV1ContainerManifestSection\b"""),
            Regex("""\bSkaldVaultV1ContainerManifestRecordState\b"""),
        )
        val misplacedParserWriter = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedParserWriterFile &&
                            parserWriterPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedParserWriter.isEmpty(),
            "Container parser/writer types must stay in the exact approved file: $misplacedParserWriter",
        )

        val source = File(root, approvedParserWriterFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bFile\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "In-memory container parser/writer must not add persistence, crypto execution, randomness, logging, or storage APIs: $offenders",
        )
    }

    @Test
    fun inMemoryManifestParserWriterStaysInApprovedFileAndDoesNotPersistOrRunCrypto() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security"),
        )
        val approvedParserWriterFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ManifestFormat.kt"
        val parserWriterPatterns = listOf(
            Regex("""\bSkaldVaultV1ManifestFormat\b"""),
            Regex("""\bSkaldVaultV1ManifestResult\b"""),
            Regex("""\bSkaldVaultV1ManifestRecordEntry\b"""),
            Regex("""\bSkaldVaultV1CandidateRecordDescriptor\b"""),
            Regex("""\bSkaldVaultV1StaleRecordDecision\b"""),
        )
        val misplacedParserWriter = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedParserWriterFile &&
                            parserWriterPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedParserWriter.isEmpty(),
            "Manifest parser/writer types must stay in the exact approved file: $misplacedParserWriter",
        )

        val source = File(root, approvedParserWriterFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bFile\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "In-memory manifest parser/writer must not add persistence, crypto execution, randomness, logging, or storage APIs: $offenders",
        )
    }

    @Test
    fun inMemoryStorageAtomicitySimulatorStaysInApprovedTestFileAndDoesNotPersistOrUsePlatformStorage() {
        val root = repositoryRoot()
        val sourceRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
            File(root, "composeApp/src/commonTest"),
        )
        val approvedSimulatorFile =
            "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultStorageAtomicitySimulatorTest.kt"
        val simulatorPatterns = listOf(
            Regex("""\bSkaldVaultV1InMemoryStorageAtomicitySimulator\b"""),
            Regex("""\bSkaldVaultV1SimulatedStorageState\b"""),
            Regex("""\bSkaldVaultV1StorageAtomicityRecoveryDecision\b"""),
            Regex("""\bSkaldVaultV1StorageAtomicitySimulationResult\b"""),
        )
        val misplacedSimulator = sourceRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedSimulatorFile &&
                            simulatorPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedSimulator.isEmpty(),
            "In-memory storage atomicity simulator types must stay in the exact approved test file: $misplacedSimulator",
        )

        val source = File(root, approvedSimulatorFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bFile\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bFiles\.createTempFile\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "In-memory storage atomicity simulator must not add platform storage, persistence, crypto execution, randomness, logging, or wrapping APIs: $offenders",
        )
    }

    @Test
    fun storageNamespacePathPolicyStaysInApprovedFileAndDoesNotConstructPathsOrUseStorage() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageNamespacePathPolicy.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageNamespacePathPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageNamespacePathResult\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageNamespacePathRejectionReason\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StoragePathSegment\b"""),
        )
        val misplacedPolicyDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedPolicyDefinitions.isEmpty(),
            "Storage namespace/path policy definitions must stay in the exact approved file: $misplacedPolicyDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bFile\("""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bFiles\.createTempFile\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Storage namespace/path policy must not add path construction, platform storage, persistence, crypto execution, randomness, logging, or wrapping APIs: $offenders",
        )
    }

    @Test
    fun storageLayoutPlanStaysInApprovedFileAndDoesNotConstructPlatformPathsOrUseStorage() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedLayoutFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageLayoutPlan.kt"
        val layoutDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageLayout"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LogicalStorageLocation\b"""),
        )
        val misplacedLayoutDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedLayoutFile &&
                            layoutDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedLayoutDefinitions.isEmpty(),
            "Storage layout plan definitions must stay in the exact approved file: $misplacedLayoutDefinitions",
        )

        val source = File(root, approvedLayoutFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\.Path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPaths\."""),
            Regex("""\bPath\("""),
            Regex("""\.resolve\("""),
            Regex("""\.toPath\("""),
            Regex("""\babsolutePath\b"""),
            Regex("""\bcanonicalPath\b"""),
            Regex("""\babsoluteFile\b"""),
            Regex("""\bcanonicalFile\b"""),
            Regex("""\.joinToString\("""),
            Regex("""\bmkdir\("""),
            Regex("""\bmkdirs\("""),
            Regex("""\bcreateDirectory\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadSymbolicLink\b"""),
            Regex("""\bgetPosixFilePermissions\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bFiles\.createTempFile\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bfilesDir\b"""),
            Regex("""\bnoBackupFilesDir\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Storage layout plan must not add platform roots, path construction, storage, persistence, crypto execution, randomness, logging, or wrapping APIs: $offenders",
        )
    }

    @Test
    fun pathContainmentPlannerStaysInApprovedFileAndDoesNotConstructPlatformPathsOrUseStorage() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPlannerFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PathContainmentPlanner.kt"
        val plannerDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PathContainment"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlannedArtifact"""),
        )
        val misplacedPlannerDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPlannerFile &&
                            plannerDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedPlannerDefinitions.isEmpty(),
            "Path-containment planner definitions must stay in the exact approved file: $misplacedPlannerDefinitions",
        )

        val source = File(root, approvedPlannerFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\.Path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPaths\."""),
            Regex("""\bPath\("""),
            Regex("""\.resolve\("""),
            Regex("""\.normalize\("""),
            Regex("""\.toPath\("""),
            Regex("""\babsolutePath\b"""),
            Regex("""\bcanonicalPath\b"""),
            Regex("""\babsoluteFile\b"""),
            Regex("""\bcanonicalFile\b"""),
            Regex("""\.joinToString\("""),
            Regex("""\bmkdir\("""),
            Regex("""\bmkdirs\("""),
            Regex("""\bcreateDirectory\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadSymbolicLink\b"""),
            Regex("""\bgetPosixFilePermissions\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bFileOutputStream\b"""),
            Regex("""\bFileInputStream\b"""),
            Regex("""\bRandomAccessFile\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bFiles\.createTempFile\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bjava\.nio\.file\.Files\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bfilesDir\b"""),
            Regex("""\bnoBackupFilesDir\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bSecretBytes\.randomBytes\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
            Regex("""com\.google\.crypto\.tink\.internal"""),
            Regex("""com\.google\.crypto\.tink\.subtle"""),
            Regex("""\bjava\.lang\.reflect\b"""),
            Regex("""\bClass\.forName\("""),
            Regex("""\bgetDeclared"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Path-containment planner must not add platform roots, path construction, storage, persistence, crypto execution, randomness, logging, or wrapping APIs: $offenders",
        )
    }

    @Test
    fun platformStorageRootContractDoesNotAddRootPathSymlinkPermissionOrDurabilityImplementations() {
        val root = repositoryRoot()
        val files = listOf(
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageNamespacePathPolicy.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageLayoutPlan.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PathContainmentPlanner.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootSettingsPolicy.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxCustomRootValidationPolicy.kt",
            ),
            File(
                root,
                "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxRootResolutionPolicy.kt",
            ),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io\.File"""),
            Regex("""import\s+java\.nio\.file"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""\bFile\("""),
            Regex("""\bPaths\."""),
            Regex("""\bPath\("""),
            Regex("""\.resolve\("""),
            Regex("""\.toPath\("""),
            Regex("""\babsolutePath\b"""),
            Regex("""\bcanonicalPath\b"""),
            Regex("""\babsoluteFile\b"""),
            Regex("""\bcanonicalFile\b"""),
            Regex("""\bmkdir\("""),
            Regex("""\bmkdirs\("""),
            Regex("""\bcreateDirectory\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadSymbolicLink\b"""),
            Regex("""\bgetPosixFilePermissions\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\bfilesDir\b"""),
            Regex("""\bnoBackupFilesDir\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bgetSharedPreferences\("""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\b(?:class|object|interface)\s+\w*(?:PlatformStorageRootResolver|StorageRootResolver|PathBuilder|PathConstructor|SymlinkChecker|PermissionChecker|DurabilityProbe)\b"""),
            Regex("""\b(?:class|object|interface)\s+\w*(?:WarningOnlyVaultPersistence|DurabilityWarningOverride|UserConsentDurabilityOverride|DurabilityOverride)\b"""),
            Regex("""\ballowWarningOnlyEncryptedVaultPersistence\("""),
            Regex("""\ballowPersistenceDespiteDurabilityFailure\("""),
            Regex("""\bcontinueWithDurabilityWarning\("""),
            Regex("""\boverrideDurabilityFailure\("""),
            Regex("""\buserConsentOverridesDurabilityFailure\("""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(
            offenders.isEmpty(),
            "Platform storage-root contract must not add root resolution, path construction, symlink, permission, durability, or storage APIs: $offenders",
        )
    }

    @Test
    fun platformRootSettingsPolicyStaysInApprovedFileAndDoesNotResolveRootsPersistSettingsOrUseKeyrings() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootSettingsPolicy.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlatformRootSettings"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Platform root settings policy definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content\.Context"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\.Path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\.Context\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPaths\."""),
            Regex("""\bPath\("""),
            Regex("""\.resolve\("""),
            Regex("""\.normalize\("""),
            Regex("""\.toPath\("""),
            Regex("""\babsolutePath\b"""),
            Regex("""\bcanonicalPath\b"""),
            Regex("""\babsoluteFile\b"""),
            Regex("""\bcanonicalFile\b"""),
            Regex("""\bmkdir\("""),
            Regex("""\bmkdirs\("""),
            Regex("""\bcreateDirectory\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bfilesDir\b"""),
            Regex("""\bnoBackupFilesDir\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bSystem\.getenv\("""),
            Regex("""\bgetenv\("""),
            Regex("""\buser\.home\b"""),
            Regex("""\bXDG_DATA_HOME\b"""),
            Regex("""\bHOME\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadSymbolicLink\b"""),
            Regex("""\bgetPosixFilePermissions\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWalletClient\b"""),
            Regex("""\bCredentialManager\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Platform root settings policy must not resolve roots, construct paths, persist settings, use storage, use keyrings/password managers, log, or add wrapping APIs: $offenders",
        )
    }

    @Test
    fun linuxCustomRootValidationPolicyStaysInApprovedFileAndDoesNotResolveRootsPersistSettingsOrUseStorage() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxCustomRootValidationPolicy.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LinuxCustomRoot"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Linux custom-root validation definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content\.Context"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\.Path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\.Context\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPaths\."""),
            Regex("""\bPath\("""),
            Regex("""\.resolve\("""),
            Regex("""\.normalize\("""),
            Regex("""\.toPath\("""),
            Regex("""\babsolutePath\b"""),
            Regex("""\bcanonicalPath\b"""),
            Regex("""\babsoluteFile\b"""),
            Regex("""\bcanonicalFile\b"""),
            Regex("""\bmkdir\("""),
            Regex("""\bmkdirs\("""),
            Regex("""\bcreateDirectory\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bfilesDir\b"""),
            Regex("""\bnoBackupFilesDir\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bopenFileOutput\("""),
            Regex("""\bopenFileInput\("""),
            Regex("""\bSystem\.getenv\("""),
            Regex("""\bgetenv\("""),
            Regex("""\buser\.home\b"""),
            Regex("""\bXDG_DATA_HOME\b"""),
            Regex("""\bHOME\b"""),
            Regex("""\bexpandTilde\b"""),
            Regex("""replaceFirst\(\s*["']~"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\.writeBytes\("""),
            Regex("""\.readBytes\("""),
            Regex("""\.writeText\("""),
            Regex("""\.readText\("""),
            Regex("""\.outputStream\("""),
            Regex("""\.inputStream\("""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadSymbolicLink\b"""),
            Regex("""\bgetPosixFilePermissions\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bFileChannel\b"""),
            Regex("""\.force\("""),
            Regex("""\bfsync\("""),
            Regex("""\bAtomicFile\b"""),
            Regex("""\bFiles\.move\b"""),
            Regex("""\bStandardCopyOption\b"""),
            Regex("""\.renameTo\("""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWalletClient\b"""),
            Regex("""\bCredentialManager\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bsetIsStrongBoxBacked\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprint\("""),
            Regex("""\bLog\."""),
            Regex("""\bLogger\b"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Linux custom-root validation policy must not resolve roots, construct paths, persist settings, use storage, use keyrings/password managers, log, or add wrapping APIs: $offenders",
        )
    }

    @Test
    fun linuxRootResolutionPolicyStaysInApprovedFileAndDoesNotResolveRootsPersistSettingsOrUseStorage() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxRootResolutionPolicy.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LinuxRootResolution"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LinuxRootToken\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Linux root-resolution policy definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex(""""HOME""""),
            Regex(""""XDG_DATA_HOME""""),
            Regex("""\bHOME\b"""),
            Regex("""\bXDG_DATA_HOME\b"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Linux root-resolution policy must not resolve roots, construct paths, persist settings, use storage, use keyrings/password managers, log, or add network/process APIs: $offenders",
        )
    }

    @Test
    fun platformRootResolverBoundaryStaysInApprovedFileAndDoesNotUseStorageSettingsOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootResolver.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlatformRootResolver"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlatformRootEvidenceToken\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LinuxRootResolverInputSnapshot\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Platform root resolver boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex(""""HOME""""),
            Regex(""""XDG_DATA_HOME""""),
            Regex("""\bHOME\b"""),
            Regex("""\bXDG_DATA_HOME\b"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Platform root resolver boundary must not use platform path, storage, settings, keyring/password-manager, crypto, BDK, network/process, or logging APIs: $offenders",
        )
    }

    @Test
    fun platformPathConstructionBoundaryStaysInApprovedFileAndDoesNotUseStorageSettingsOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformPathConstructionBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlatformPathConstruction"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlatformArtifactLocationToken\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PlannedPlatformArtifactLocation\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Platform path-construction boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Platform path-construction boundary must not use platform path, storage, settings, keyring/password-manager, crypto, BDK, network/process, or logging APIs: $offenders",
        )
    }

    @Test
    fun storageSafetyPreflightBoundaryStaysInApprovedFileAndDoesNotUseStorageSettingsOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageSafetyPreflightBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageSafetyPreflight"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageSafetyEvidence\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1StorageSafetyGateEvidence\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Storage safety preflight boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Storage safety preflight boundary must not use platform path, storage, settings, keyring/password-manager, crypto, BDK, network/process, or logging APIs: $offenders",
        )
    }

    @Test
    fun disabledStorageServiceFacadeStaysInApprovedFileAndDoesNotUseStorageSettingsOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1DisabledStorageServiceFacade.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultStorageService\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1DisabledStorageServiceFacade\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultStorageOperation"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultStorageRecordDescriptor\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultStoragePayloadPlaceholder\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Disabled storage service facade definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\blistRecords\("""),
            Regex("""\bdeleteRecord\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverInterruptedWrite\("""),
            Regex("""\bprepareAtomicWrite\("""),
            Regex("""\bcommitAtomicWrite\("""),
            Regex("""\brollbackAtomicWrite\("""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Disabled storage service facade must not use platform path, storage, settings, keyring/password-manager, crypto, BDK, network/process, logging, or storage-success APIs: $offenders",
        )
    }

    @Test
    fun persistenceReadinessGateStaysInApprovedFileAndDoesNotUseStorageSettingsOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PersistenceReadinessGate.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PersistenceReadinessGate\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultPersistenceReadiness"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultPersistenceGateEvidence\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Persistence readiness gate definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""readyForPersistence\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""manifestReadWriteAvailable\s*=\s*true"""),
            Regex("""storageIndexReadWriteAvailable\s*=\s*true"""),
            Regex("""recordReadWriteAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Persistence readiness gate must not use platform path, storage, settings, keyring/password-manager, crypto, BDK, network/process, logging, storage-success, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun lockSessionLifecycleBoundaryStaysInApprovedFileAndDoesNotUseSecretsStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LockSessionLifecycleBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LockSessionLifecycleBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1LockSessionLifecyclePolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultLockSession"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultSession"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Lock/session lifecycle boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bByteArray\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""unlockAvailable\s*=\s*true"""),
            Regex("""activeSessionAvailable\s*=\s*true"""),
            Regex("""decryptedKeyMaterialPresent\s*=\s*true"""),
            Regex("""passphraseAccepted\s*=\s*true"""),
            Regex("""passphraseStored\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""manifestReadWriteAvailable\s*=\s*true"""),
            Regex("""storageIndexReadWriteAvailable\s*=\s*true"""),
            Regex("""recordReadWriteAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Lock/session lifecycle boundary must not use passphrase, key, platform lifecycle, biometric, keyring/password-manager, platform path, storage, settings, crypto, BDK, network/process, logging, or success APIs: $offenders",
        )
    }

    @Test
    fun redactionLeakageBoundaryStaysInApprovedFileAndDoesNotUseLoggingSecretsStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RedactionLeakageBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1RedactionLeakageBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1RedactionLeakagePolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultRedaction"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultLeakage"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Redaction/leakage boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bandroid\.net\.Uri\b"""),
            Regex("""\bandroid\.content\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\b"""),
            Regex("""\bKeyStore\b"""),
            Regex("""\bPasswordManager\b"""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bByteArray\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bruntimeLoggingEnabled\s*=\s*true"""),
            Regex("""\bcrashReportingEnabled\s*=\s*true"""),
            Regex("""\banalyticsEnabled\s*=\s*true"""),
            Regex("""\bsupportExportEnabled\s*=\s*true"""),
            Regex("""\bsecretFingerprintingEnabled\s*=\s*true"""),
            Regex("""\bsecretHashingEnabled\s*=\s*true"""),
            Regex("""\brawSecretDisplayEnabled\s*=\s*true"""),
            Regex("""\bproviderSelectable\s*=\s*true"""),
            Regex("""\bvaultUnlockAvailable\s*=\s*true"""),
            Regex("""\bvaultPersistenceAvailable\s*=\s*true"""),
            Regex("""\bmainnetAvailable\s*=\s*true"""),
            Regex("""\bruntimeLoggingImplemented:\s*Boolean\s*=\s*true"""),
            Regex("""\bcrashReportingImplemented:\s*Boolean\s*=\s*true"""),
            Regex("""\banalyticsImplemented:\s*Boolean\s*=\s*true"""),
            Regex("""\bsupportExportImplemented:\s*Boolean\s*=\s*true"""),
            Regex("""\bsecretHashingImplemented:\s*Boolean\s*=\s*true"""),
            Regex("""\bsecretFingerprintingImplemented:\s*Boolean\s*=\s*true"""),
            Regex("""\bunlockReady:\s*Boolean\s*=\s*true"""),
            Regex("""\bproviderReady:\s*Boolean\s*=\s*true"""),
            Regex("""\bpersistenceReady:\s*Boolean\s*=\s*true"""),
            Regex("""\bmainnetReady:\s*Boolean\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Redaction/leakage boundary must not use logging, crash/analytics, secret hashing/fingerprinting, passphrase/key, platform lifecycle, keyring/password-manager, platform path, storage, settings, crypto, BDK, network/process, or success APIs: $offenders",
        )
    }

    @Test
    fun passphrasePolicyBoundaryStaysInApprovedFileAndDoesNotUseSecretsCryptoStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicyBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PassphrasePolicyBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1PassphrasePolicyGate\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultPassphrase"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Passphrase policy boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bByteArray\("""),
            Regex("""\bnormalizeAndEncode\("""),
            Regex("""\bskaldVaultV1NormalizeNfc\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""passphraseInputAccepted\s*=\s*true"""),
            Regex("""passphraseStored\s*=\s*true"""),
            Regex("""passphraseNormalized\s*=\s*true"""),
            Regex("""passphraseEncoded\s*=\s*true"""),
            Regex("""passphraseHashed\s*=\s*true"""),
            Regex("""passphraseFingerprintCreated\s*=\s*true"""),
            Regex("""passphraseLogged\s*=\s*true"""),
            Regex("""retryPolicyImplemented\s*=\s*true"""),
            Regex("""throttlePolicyImplemented\s*=\s*true"""),
            Regex("""lockoutPolicyImplemented\s*=\s*true"""),
            Regex("""clearStrategyImplemented\s*=\s*true"""),
            Regex("""biometricUnlockAvailable\s*=\s*true"""),
            Regex("""androidKeystoreWrappingAvailable\s*=\s*true"""),
            Regex("""osKeyringPassphraseStorageAvailable\s*=\s*true"""),
            Regex("""passwordManagerPassphraseStorageAvailable\s*=\s*true"""),
            Regex("""unlockAvailable\s*=\s*true"""),
            Regex("""activeSessionAvailable\s*=\s*true"""),
            Regex("""decryptedKeyMaterialPresent\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Passphrase policy boundary must not use passphrase/key, biometric, keyring/password-manager, platform path, storage, settings, crypto, hash/fingerprint, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun clearWipeStrategyBoundaryStaysInApprovedFileAndDoesNotUseZeroizationStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ClearWipeStrategyBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ClearWipeStrategyBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ClearWipeStrategyPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultClearWipe"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Clear/wipe strategy boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bzeroizationLibrary\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""actualClearImplemented\s*=\s*true"""),
            Regex("""actualZeroizationImplemented\s*=\s*true"""),
            Regex("""jvmZeroizationProven\s*=\s*true"""),
            Regex("""nativeZeroizationAvailable\s*=\s*true"""),
            Regex("""providerClearAvailable\s*=\s*true"""),
            Regex("""storageClearAvailable\s*=\s*true"""),
            Regex("""sessionInvalidationAvailable\s*=\s*true"""),
            Regex("""passphraseClearAvailable\s*=\s*true"""),
            Regex("""keyMaterialClearAvailable\s*=\s*true"""),
            Regex("""decryptedRecordClearAvailable\s*=\s*true"""),
            Regex("""diagnosticBufferClearAvailable\s*=\s*true"""),
            Regex("""unlockAvailable\s*=\s*true"""),
            Regex("""activeSessionAvailable\s*=\s*true"""),
            Regex("""decryptedKeyMaterialPresent\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Clear/wipe strategy boundary must not use zeroization, mutable secret-buffer, passphrase/key, biometric, keyring/password-manager, platform path, storage, settings, crypto, hash/fingerprint, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun migrationCorruptionBoundaryStaysInApprovedFileAndDoesNotUseStorageCryptoMigrationOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1MigrationCorruptionBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1MigrationCorruptionPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultMigrationCorruption"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Migration/corruption boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bByteArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bzeroizationLibrary\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\brewriteManifest\("""),
            Regex("""\brewriteStorageIndex\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""migrationAvailable\s*=\s*true"""),
            Regex("""migrationDryRunAvailable\s*=\s*true"""),
            Regex("""repairAvailable\s*=\s*true"""),
            Regex("""quarantineAvailable\s*=\s*true"""),
            Regex("""crashRecoveryAvailable\s*=\s*true"""),
            Regex("""rollbackProtectionAvailable\s*=\s*true"""),
            Regex("""realCorruptionDetectionAvailable\s*=\s*true"""),
            Regex("""realHeaderCommitmentVerificationAvailable\s*=\s*true"""),
            Regex("""realAeadAuthenticationAvailable\s*=\s*true"""),
            Regex("""recordReadAvailable\s*=\s*true"""),
            Regex("""recordWriteAvailable\s*=\s*true"""),
            Regex("""manifestReadWriteAvailable\s*=\s*true"""),
            Regex("""storageIndexReadWriteAvailable\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""storageServiceAvailable\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""unlockAvailable\s*=\s*true"""),
            Regex("""activeSessionAvailable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Migration/corruption boundary must not use storage bytes, migration/repair/quarantine execution, platform path, storage, settings, crypto, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun providerOperationAuthorizationBoundaryStaysInApprovedFileAndDoesNotUseCryptoProviderStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationAuthorizationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationAuthorizationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationAuthorizationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultProviderOperation"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider operation authorization boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessCheckAvailable\s*=\s*true"""),
            Regex("""entropyGenerationAvailable\s*=\s*true"""),
            Regex("""saltGenerationAvailable\s*=\s*true"""),
            Regex("""nonceGenerationAvailable\s*=\s*true"""),
            Regex("""keyGenerationAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""argon2idExecutionAvailable\s*=\s*true"""),
            Regex("""hkdfExecutionAvailable\s*=\s*true"""),
            Regex("""hmacExecutionAvailable\s*=\s*true"""),
            Regex("""headerCommitmentComputationAvailable\s*=\s*true"""),
            Regex("""headerCommitmentVerificationAvailable\s*=\s*true"""),
            Regex("""aeadEncryptAvailable\s*=\s*true"""),
            Regex("""aeadDecryptAvailable\s*=\s*true"""),
            Regex("""recordEncryptAvailable\s*=\s*true"""),
            Regex("""recordDecryptAvailable\s*=\s*true"""),
            Regex("""keyWrappingAvailable\s*=\s*true"""),
            Regex("""keyUnwrappingAvailable\s*=\s*true"""),
            Regex("""providerClearAvailable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider operation authorization boundary must not use provider operation, crypto, randomness, key-generation, platform path, storage, settings, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun runtimeRandomnessAuthorizationBoundaryStaysInApprovedFileAndDoesNotUseRandomCryptoProviderStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RuntimeRandomnessAuthorizationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1RuntimeRandomnessAuthorizationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1RuntimeRandomnessAuthorizationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultRuntimeRandomness"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultRandomness"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Runtime randomness authorization boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""randomnessOperationAuthorized\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""osCryptographicRandomnessAvailable\s*=\s*true"""),
            Regex("""providerRandomnessAvailable\s*=\s*true"""),
            Regex("""hardwareBackedEntropyAvailable\s*=\s*true"""),
            Regex("""androidOsCsprngAvailable\s*=\s*true"""),
            Regex("""androidHardwareBackedEntropyAvailable\s*=\s*true"""),
            Regex("""linuxOsCsprngAvailable\s*=\s*true"""),
            Regex("""linuxHardwareEntropyAvailable\s*=\s*true"""),
            Regex("""saltGenerationAvailable\s*=\s*true"""),
            Regex("""nonceGenerationAvailable\s*=\s*true"""),
            Regex("""keyGenerationEntropyAvailable\s*=\s*true"""),
            Regex("""kdfSaltAvailable\s*=\s*true"""),
            Regex("""aeadNonceAvailable\s*=\s*true"""),
            Regex("""recordNonceAvailable\s*=\s*true"""),
            Regex("""metadataNonceAvailable\s*=\s*true"""),
            Regex("""manifestNonceAvailable\s*=\s*true"""),
            Regex("""storageIndexNonceAvailable\s*=\s*true"""),
            Regex("""deterministicTestVectorRandomnessAvailable\s*=\s*true"""),
            Regex("""productionRuntimeRandomnessAvailable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Runtime randomness authorization boundary must not use randomness, crypto, provider operation, key-generation, platform path, storage, settings, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun kdfCalibrationAuthorizationBoundaryStaysInApprovedFileAndDoesNotUseKdfRandomCryptoProviderStorageOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1KdfCalibrationAuthorizationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1KdfCalibrationAuthorizationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1KdfCalibrationAuthorizationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultKdfCalibration"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultKdf"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "KDF calibration authorization boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bArgon2Parameters\b"""),
            Regex("""\bgenerateBytes\("""),
            Regex("""\bwithMemoryAsKB\("""),
            Regex("""\bexecuteKdf\("""),
            Regex("""\brunKdf\("""),
            Regex("""\brunArgon2id\("""),
            Regex("""\brunCalibration\("""),
            Regex("""\bexecuteCalibration\("""),
            Regex("""\brunBenchmark\("""),
            Regex("""\bprobeCpu\("""),
            Regex("""\bprobeMemory\("""),
            Regex("""\bnormalizeAndEncode\("""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""kdfCalibrationAuthorized\s*=\s*true"""),
            Regex("""kdfExecutionAuthorized\s*=\s*true"""),
            Regex("""argon2idExecutionAvailable\s*=\s*true"""),
            Regex("""argon2idCalibrationAvailable\s*=\s*true"""),
            Regex("""finalKdfParametersApproved\s*=\s*true"""),
            Regex("""androidCalibrationApproved\s*=\s*true"""),
            Regex("""linuxCalibrationApproved\s*=\s*true"""),
            Regex("""memoryCostApproved\s*=\s*true"""),
            Regex("""iterationCostApproved\s*=\s*true"""),
            Regex("""parallelismApproved\s*=\s*true"""),
            Regex("""saltLengthApproved\s*=\s*true"""),
            Regex("""outputLengthApproved\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""runtimeRandomnessAuthorized\s*=\s*true"""),
            Regex("""saltGenerationAvailable\s*=\s*true"""),
            Regex("""passphraseInputAccepted\s*=\s*true"""),
            Regex("""passphraseNormalized\s*=\s*true"""),
            Regex("""passphraseEncoded\s*=\s*true"""),
            Regex("""clearWipeApproved\s*=\s*true"""),
            Regex("""redactionApproved\s*=\s*true"""),
            Regex("""lockSessionApproved\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "KDF calibration authorization boundary must not use KDF, calibration, randomness, crypto, provider operation, platform path, storage, settings, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun secureStorageAuthorizationBoundaryStaysInApprovedFileAndDoesNotUseStorageKeyringPasswordManagerCryptoOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1SecureStorageAuthorizationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1SecureStorageAuthorizationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1SecureStorageAuthorizationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultSecureStorage"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Secure-storage authorization boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bstoreSecret\("""),
            Regex("""\bretrieveSecret\("""),
            Regex("""\bdeleteSecret\("""),
            Regex("""\brotateSecret\("""),
            Regex("""\bstoreMetadata\("""),
            Regex("""\bretrieveMetadata\("""),
            Regex("""\bexportBackupMaterial\("""),
            Regex("""\bimportBackupMaterial\("""),
            Regex("""\bmigrateSecureStorage\("""),
            Regex("""\bpurgeSecureStorage\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""secureStorageAuthorized\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""encryptedLocalVaultStorageAvailable\s*=\s*true"""),
            Regex("""osKeyringPrimaryStorageAvailable\s*=\s*true"""),
            Regex("""osKeyringOptionalWrappingAvailable\s*=\s*true"""),
            Regex("""passwordManagerPassphraseStorageAvailable\s*=\s*true"""),
            Regex("""androidKeystoreWrappingAvailable\s*=\s*true"""),
            Regex("""androidHardwareBackedWrappingAvailable\s*=\s*true"""),
            Regex("""linuxOptionalKeyWrappingAvailable\s*=\s*true"""),
            Regex("""settingsSecretStorageAvailable\s*=\s*true"""),
            Regex("""plaintextStorageAvailable\s*=\s*true"""),
            Regex("""storeSecretAvailable\s*=\s*true"""),
            Regex("""retrieveSecretAvailable\s*=\s*true"""),
            Regex("""deleteSecretAvailable\s*=\s*true"""),
            Regex("""wrapKeyAvailable\s*=\s*true"""),
            Regex("""unwrapKeyAvailable\s*=\s*true"""),
            Regex("""storeMetadataAvailable\s*=\s*true"""),
            Regex("""retrieveMetadataAvailable\s*=\s*true"""),
            Regex("""exportBackupMaterialAvailable\s*=\s*true"""),
            Regex("""importBackupMaterialAvailable\s*=\s*true"""),
            Regex("""secureStorageMigrationAvailable\s*=\s*true"""),
            Regex("""secureStoragePurgeAvailable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""runtimeRandomnessAuthorized\s*=\s*true"""),
            Regex("""kdfCalibrationAuthorized\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Secure-storage authorization boundary must not use storage, keyring/password-manager, Android wrapping, crypto, randomness, provider operation, platform path, settings, BDK, network/process, logging, unlock, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun unlockAuthorizationBoundaryStaysInApprovedFileAndDoesNotUseUnlockStorageCryptoProviderRandomnessOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1UnlockAuthorizationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1UnlockAuthorizationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1UnlockAuthorizationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultUnlockAuthorization"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultUnlockOperationKind\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultUnlockPurpose\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultUnlockCredentialClass\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultUnlockRequiredGate\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Unlock authorization boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bstoreSecret\("""),
            Regex("""\bretrieveSecret\("""),
            Regex("""\bdeleteSecret\("""),
            Regex("""\bstoreMetadata\("""),
            Regex("""\bretrieveMetadata\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\bacceptPassphrase\("""),
            Regex("""\bnormalizePassphrase\("""),
            Regex("""\bencodePassphrase\("""),
            Regex("""\bvalidateCredential\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bresumeSession\("""),
            Regex("""\brefreshSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\breadSecureStorage\("""),
            Regex("""\breadSecureMetadata\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\battemptUnlock\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""unlockAuthorized\s*=\s*true"""),
            Regex("""unlockAttemptAvailable\s*=\s*true"""),
            Regex("""passphraseInputAccepted\s*=\s*true"""),
            Regex("""pinInputAccepted\s*=\s*true"""),
            Regex("""biometricUnlockAvailable\s*=\s*true"""),
            Regex("""hardwareWrappedKeyUnlockAvailable\s*=\s*true"""),
            Regex("""osKeyringUnlockAvailable\s*=\s*true"""),
            Regex("""passwordManagerUnlockAvailable\s*=\s*true"""),
            Regex("""kdfCalibrationAuthorized\s*=\s*true"""),
            Regex("""runtimeRandomnessAuthorized\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""secureStorageAuthorized\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""encryptedLocalVaultStorageAvailable\s*=\s*true"""),
            Regex("""storageServiceAvailable\s*=\s*true"""),
            Regex("""migrationCorruptionApproved\s*=\s*true"""),
            Regex("""clearWipeApproved\s*=\s*true"""),
            Regex("""redactionApproved\s*=\s*true"""),
            Regex("""lockSessionApproved\s*=\s*true"""),
            Regex("""activeSessionAvailable\s*=\s*true"""),
            Regex("""decryptedKeyMaterialPresent\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Unlock authorization boundary must not use unlock, storage, keyring/password-manager, Android wrapping, crypto, randomness, provider operation, platform path, settings, BDK, network/process, logging, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun creationAuthorizationBoundaryStaysInApprovedFileAndDoesNotUseCreationUnlockStorageCryptoProviderRandomnessOrPlatformApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1CreationAuthorizationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1CreationAuthorizationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1CreationAuthorizationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultCreationAuthorization"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultCreationOperationKind\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultCreationPurpose\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultCreationInitializerClass\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1VaultCreationRequiredGate\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Creation authorization boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\.separator\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bPaths\."""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bdelete\("""),
            Regex("""\brename\("""),
            Regex("""\bcopy\("""),
            Regex("""\bmove\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bDataStore\b"""),
            Regex("""\bRoomDatabase\b"""),
            Regex("""\bSQLiteDatabase\b"""),
            Regex("""\bEnvironment\.getExternalStorageDirectory\b"""),
            Regex("""\bgetExternalFilesDir\("""),
            Regex("""\bStorageManager\b"""),
            Regex("""\bDocumentsContract\b"""),
            Regex("""\bACTION_OPEN_DOCUMENT_TREE\b"""),
            Regex("""\bMANAGE_EXTERNAL_STORAGE\b"""),
            Regex("""\bLifecycleObserver\b"""),
            Regex("""\bDefaultLifecycleObserver\b"""),
            Regex("""\bProcessLifecycleOwner\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\bAutofillManager\b"""),
            Regex("""\bGooglePasswordManager\b"""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bproviderClear\("""),
            Regex("""\bstorageClear\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bgenerateVaultKey\("""),
            Regex("""\bgenerateContainerId\("""),
            Regex("""\bgenerateRecordId\("""),
            Regex("""\bgenerateMetadataId\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bconstructAad\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bstoreSecret\("""),
            Regex("""\bretrieveSecret\("""),
            Regex("""\bdeleteSecret\("""),
            Regex("""\bstoreMetadata\("""),
            Regex("""\bretrieveMetadata\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateContainer\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateStorageIndex\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcreateSecureMetadata\("""),
            Regex("""\bcreateStorageNamespace\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\brollbackCreation\("""),
            Regex("""\bcleanupCreationFailure\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brunMigration\("""),
            Regex("""\brepairVault\("""),
            Regex("""\brepairStorage\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\brecoverRecord\("""),
            Regex("""\bacceptPassphrase\("""),
            Regex("""\bnormalizePassphrase\("""),
            Regex("""\bencodePassphrase\("""),
            Regex("""\bvalidateCredential\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bresumeSession\("""),
            Regex("""\brefreshSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\breadSecureStorage\("""),
            Regex("""\breadSecureMetadata\("""),
            Regex("""\bwriteSecureStorage\("""),
            Regex("""\bwriteSecureMetadata\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\battemptUnlock\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""creationAuthorized\s*=\s*true"""),
            Regex("""creationAttemptAvailable\s*=\s*true"""),
            Regex("""initialPassphraseAccepted\s*=\s*true"""),
            Regex("""initialKeyMaterialAvailable\s*=\s*true"""),
            Regex("""initialSaltGenerationAvailable\s*=\s*true"""),
            Regex("""initialNonceGenerationAvailable\s*=\s*true"""),
            Regex("""initialKdfExecutionAvailable\s*=\s*true"""),
            Regex("""initialProviderOperationAvailable\s*=\s*true"""),
            Regex("""headerCreationAvailable\s*=\s*true"""),
            Regex("""headerCommitmentAvailable\s*=\s*true"""),
            Regex("""containerCreationAvailable\s*=\s*true"""),
            Regex("""manifestCreationAvailable\s*=\s*true"""),
            Regex("""storageIndexCreationAvailable\s*=\s*true"""),
            Regex("""recordCreationAvailable\s*=\s*true"""),
            Regex("""secureMetadataCreationAvailable\s*=\s*true"""),
            Regex("""wrappedKeyStorageAvailable\s*=\s*true"""),
            Regex("""storageNamespaceCreationAvailable\s*=\s*true"""),
            Regex("""initialPersistenceCommitAvailable\s*=\s*true"""),
            Regex("""postCreateUnlockAvailable\s*=\s*true"""),
            Regex("""creationRollbackAvailable\s*=\s*true"""),
            Regex("""creationFailureCleanupAvailable\s*=\s*true"""),
            Regex("""unlockAuthorized\s*=\s*true"""),
            Regex("""kdfCalibrationAuthorized\s*=\s*true"""),
            Regex("""runtimeRandomnessAuthorized\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""secureStorageAuthorized\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""encryptedLocalVaultStorageAvailable\s*=\s*true"""),
            Regex("""storageServiceAvailable\s*=\s*true"""),
            Regex("""activeSessionAvailable\s*=\s*true"""),
            Regex("""decryptedKeyMaterialPresent\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelected\s*=\s*true"""),
            Regex("""providerCryptoAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Creation authorization boundary must not use creation, unlock, storage, keyring/password-manager, Android wrapping, crypto, randomness, provider operation, platform path, settings, BDK, network/process, logging, provider-selectable, or persistence-success APIs: $offenders",
        )
    }

    @Test
    fun authorizationReadinessMatrixStaysInApprovedFileAndDoesNotUseRuntimeImplementationApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1AuthorizationReadinessMatrix.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1AuthorizationReadinessMatrixBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1AuthorizationReadinessMatrixPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1AuthorizationReadinessCapability"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1AuthorizationReadinessBlocker"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Authorization/readiness matrix definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bstoreSecret\("""),
            Regex("""\bretrieveSecret\("""),
            Regex("""\bstoreMetadata\("""),
            Regex("""\bretrieveMetadata\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brepairVault\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\bacceptPassphrase\("""),
            Regex("""\bnormalizePassphrase\("""),
            Regex("""\bencodePassphrase\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""runtimeRandomnessReady\s*=\s*true"""),
            Regex("""kdfReady\s*=\s*true"""),
            Regex("""secureStorageReady\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""creationReady\s*=\s*true"""),
            Regex("""unlockReady\s*=\s*true"""),
            Regex("""activeSessionReady\s*=\s*true"""),
            Regex("""persistenceReady\s*=\s*true"""),
            Regex("""storageServiceAvailable\s*=\s*true"""),
            Regex("""vaultStorageAvailable\s*=\s*true"""),
            Regex("""manifestReadWriteAvailable\s*=\s*true"""),
            Regex("""storageIndexReadWriteAvailable\s*=\s*true"""),
            Regex("""recordReadWriteAvailable\s*=\s*true"""),
            Regex("""atomicWriteAvailable\s*=\s*true"""),
            Regex("""crashRecoveryAvailable\s*=\s*true"""),
            Regex("""migrationAvailable\s*=\s*true"""),
            Regex("""corruptionRecoveryAvailable\s*=\s*true"""),
            Regex("""rollbackProtectionAvailable\s*=\s*true"""),
            Regex("""clearWipeAvailable\s*=\s*true"""),
            Regex("""redactionSafeDiagnosticsAvailable\s*=\s*true"""),
            Regex("""passphraseInputAccepted\s*=\s*true"""),
            Regex("""passphraseRetryThrottleAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
            Regex("""mainnetReady\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Authorization/readiness matrix must not use provider, crypto, randomness, storage, platform, settings, BDK, network/process, logging, creation/unlock, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerCandidatePackagingBoundaryStaysInApprovedFileAndDoesNotUseProviderRuntimeOrDependencyActivationApis() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderCandidatePackagingBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderCandidatePackagingBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderCandidatePackagingPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderCandidateFamily\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderCandidateDependencyCategory\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderCandidateSourceSetPlacement\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider candidate packaging boundary definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bfill\(0"""),
            Regex("""\bArrays\.fill\b"""),
            Regex("""\bByteBuffer\.allocateDirect\b"""),
            Regex("""\bCleaner\b"""),
            Regex("""\bPhantomReference\b"""),
            Regex("""\bfinalize\("""),
            Regex("""\bzeroize\b"""),
            Regex("""\bProviderFactory\b"""),
            Regex("""\bProviderRegistry\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bproviderOperation\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bstoreSecret\("""),
            Regex("""\bretrieveSecret\("""),
            Regex("""\bstoreMetadata\("""),
            Regex("""\bretrieveMetadata\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brepairVault\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\bacceptPassphrase\("""),
            Regex("""\bnormalizePassphrase\("""),
            Regex("""\bencodePassphrase\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerCandidateImplemented\s*=\s*true"""),
            Regex("""providerDependencyActive\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""hkdfHmacExecutionAvailable\s*=\s*true"""),
            Regex("""headerCommitmentAvailable\s*=\s*true"""),
            Regex("""keyWrappingAvailable\s*=\s*true"""),
            Regex("""providerClearAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider candidate packaging boundary must not use provider runtime, dependency activation, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerInterfaceContractAuditStaysInApprovedFileAndDoesNotAcceptRuntimeMaterialOrEnableSelection() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderInterfaceContractAudit.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderInterfaceContractAuditBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderInterfaceContractAuditPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderInterfaceContractTopic\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderInterfaceContractRisk\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderInterfaceContractRequiredProperty\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderInterfaceContractCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider interface contract audit definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""import\s+java\.io"""),
            Regex("""import\s+java\.nio"""),
            Regex("""import\s+kotlin\.io\.path"""),
            Regex("""import\s+android\.content"""),
            Regex("""import\s+android\.net\.Uri"""),
            Regex("""import\s+androidx\.datastore"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\.toPath\("""),
            Regex("""\.toFile\("""),
            Regex("""\.absolute"""),
            Regex("""\.canonical"""),
            Regex("""\.normalize\("""),
            Regex("""\.resolve\("""),
            Regex("""\.relativize\("""),
            Regex("""\bexists\("""),
            Regex("""\bisDirectory\b"""),
            Regex("""\bisRegularFile\b"""),
            Regex("""\bisSymbolicLink\b"""),
            Regex("""\breadAttributes\b"""),
            Regex("""\bsetPosixFilePermissions\b"""),
            Regex("""\bcreateDirectories\b"""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\binputStream\b"""),
            Regex("""\boutputStream\b"""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""import\s+javax\.crypto"""),
            Regex("""import\s+java\.security"""),
            Regex("""import\s+java\.util\.Random"""),
            Regex("""import\s+kotlin\.random"""),
            Regex("""import\s+org\.bouncycastle"""),
            Regex("""import\s+com\.google\.crypto"""),
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bjava\.util\.Random\b"""),
            Regex("""\bkotlin\.random\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bHmacSHA256\b"""),
            Regex("""\bSHA-"""),
            Regex("""\bHKDFBytesGenerator\b"""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\bProviderFactory\b"""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bproviderOperation\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bstoreSecret\("""),
            Regex("""\bretrieveSecret\("""),
            Regex("""\bstoreMetadata\("""),
            Regex("""\bretrieveMetadata\("""),
            Regex("""\breadVaultContainer\("""),
            Regex("""\bwriteVaultContainer\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bmigrateVault\("""),
            Regex("""\brepairVault\("""),
            Regex("""\bquarantineRecord\("""),
            Regex("""\bacceptPassphrase\("""),
            Regex("""\bnormalizePassphrase\("""),
            Regex("""\bencodePassphrase\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bRpcClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerInterfaceAcceptsSecrets\s*=\s*true"""),
            Regex("""providerInterfaceAcceptsByteMaterial\s*=\s*true"""),
            Regex("""providerInterfaceAcceptsProviderHandles\s*=\s*true"""),
            Regex("""providerInterfaceAcceptsCryptoObjects\s*=\s*true"""),
            Regex("""providerInterfaceCanExecuteOperations\s*=\s*true"""),
            Regex("""providerInterfaceCanBypassAuthorization\s*=\s*true"""),
            Regex("""providerInterfaceCanSelectProvider\s*=\s*true"""),
            Regex("""providerInterfaceCanPromoteProvider\s*=\s*true"""),
            Regex("""providerInterfaceCanSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""providerImplementationAdded\s*=\s*true"""),
            Regex("""providerFactoryAdded\s*=\s*true"""),
            Regex("""providerRegistryEnabled\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider interface contract audit must not accept runtime material, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun nonSelectableProviderSkeletonBoundaryStaysModelOnlyAndCannotRegisterOrExecuteProviderRuntime() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1NonSelectableProviderSkeletonBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1NonSelectableProviderSkeletonBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1NonSelectableProviderSkeletonPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderSkeletonTopic\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderSkeletonOperationSurface\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderSkeletonCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Non-selectable provider skeleton definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\bProviderFactory\b"""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerSkeletonImplementsRuntimeProvider\s*=\s*true"""),
            Regex("""providerSkeletonRegistered\s*=\s*true"""),
            Regex("""providerSkeletonSelectable\s*=\s*true"""),
            Regex("""providerSkeletonInstantiableByRegistry\s*=\s*true"""),
            Regex("""providerSkeletonExecutesOperations\s*=\s*true"""),
            Regex("""providerSkeletonRunsKat\s*=\s*true"""),
            Regex("""providerSkeletonUsesRandomness\s*=\s*true"""),
            Regex("""providerSkeletonRunsKdf\s*=\s*true"""),
            Regex("""providerSkeletonRunsAead\s*=\s*true"""),
            Regex("""providerSkeletonWrapsKeys\s*=\s*true"""),
            Regex("""providerSkeletonCreatesVault\s*=\s*true"""),
            Regex("""providerSkeletonUnlocksVault\s*=\s*true"""),
            Regex("""providerSkeletonPersistsVault\s*=\s*true"""),
            Regex("""providerImplementationAdded\s*=\s*true"""),
            Regex("""providerFactoryAdded\s*=\s*true"""),
            Regex("""providerRegistryEnabled\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Non-selectable provider skeleton must not implement/instantiate/register/select providers, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerRegistryIsolationGuardStaysModelOnlyAndCannotRegisterOrReachProviderRuntime() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderRegistryIsolationGuard.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderRegistryIsolationGuardBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderRegistryIsolationGuardPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderRegistryIsolationTopic\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderRegistryIsolationRisk\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderRegistryIsolationCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider registry isolation guard definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\bProviderFactory\b"""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""registryContainsProviderSkeleton\s*=\s*true"""),
            Regex("""registryContainsCandidateProvider\s*=\s*true"""),
            Regex("""registryContainsProviderFactory\s*=\s*true"""),
            Regex("""registryCreatesProviderInstances\s*=\s*true"""),
            Regex("""registryCanSelectNonDisabledProvider\s*=\s*true"""),
            Regex("""registryCanSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""registryCanPromoteCandidate\s*=\s*true"""),
            Regex("""registryCanExecuteProviderOperations\s*=\s*true"""),
            Regex("""registryCanRunKat\s*=\s*true"""),
            Regex("""registryCanUseRandomness\s*=\s*true"""),
            Regex("""registryCanRunKdf\s*=\s*true"""),
            Regex("""registryCanRunAead\s*=\s*true"""),
            Regex("""registryCanWrapKeys\s*=\s*true"""),
            Regex("""registryCanCreateVault\s*=\s*true"""),
            Regex("""registryCanUnlockVault\s*=\s*true"""),
            Regex("""registryCanPersistVault\s*=\s*true"""),
            Regex("""providerFactoryAdded\s*=\s*true"""),
            Regex("""providerRegistryEnabled\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""hkdfHmacExecutionAvailable\s*=\s*true"""),
            Regex("""keyWrappingAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider registry isolation guard must not implement/instantiate/register/select providers, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerFactoryIsolationBoundaryStaysModelOnlyAndCannotConstructOrExposeProviderRuntime() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderFactoryIsolationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderFactoryIsolationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderFactoryIsolationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderFactoryIsolationTopic\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderFactoryIsolationRisk\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderFactoryIsolationCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider factory isolation definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerFactoryExistsForNonDisabledProvider\s*=\s*true"""),
            Regex("""providerFactoryReachableFromSelection\s*=\s*true"""),
            Regex("""providerFactoryReachableFromRegistry\s*=\s*true"""),
            Regex("""providerFactoryCanConstructSkeleton\s*=\s*true"""),
            Regex("""providerFactoryCanConstructCandidate\s*=\s*true"""),
            Regex("""providerFactoryCanConstructProviderRuntime\s*=\s*true"""),
            Regex("""providerFactoryCanExposeProviderHandle\s*=\s*true"""),
            Regex("""providerFactoryCanExposeCryptoObject\s*=\s*true"""),
            Regex("""providerFactoryAcceptsByteMaterial\s*=\s*true"""),
            Regex("""providerFactoryCanExecuteProviderOperations\s*=\s*true"""),
            Regex("""providerFactoryCanRunKat\s*=\s*true"""),
            Regex("""providerFactoryCanUseRandomness\s*=\s*true"""),
            Regex("""providerFactoryCanRunKdf\s*=\s*true"""),
            Regex("""providerFactoryCanRunAead\s*=\s*true"""),
            Regex("""providerFactoryCanWrapKeys\s*=\s*true"""),
            Regex("""providerFactoryCanCreateVault\s*=\s*true"""),
            Regex("""providerFactoryCanUnlockVault\s*=\s*true"""),
            Regex("""providerFactoryCanPersistVault\s*=\s*true"""),
            Regex("""providerFactoryAdded\s*=\s*true"""),
            Regex("""providerRegistryContainsCandidate\s*=\s*true"""),
            Regex("""providerRegistryContainsSkeleton\s*=\s*true"""),
            Regex("""providerRegistryEnabled\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""hkdfHmacExecutionAvailable\s*=\s*true"""),
            Regex("""keyWrappingAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider factory isolation boundary must not implement factories/providers, construct provider runtime, register/select providers, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerOperationDispatchIsolationBoundaryStaysModelOnlyAndCannotDispatchProviderOperations() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationDispatchIsolationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationDispatchIsolationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationDispatchIsolationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationDispatchIsolationTopic\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationDispatchIsolationRisk\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderOperationDispatchIsolationCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider operation dispatch isolation definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderDispatcher\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""\bdispatchProviderOperation\("""),
            Regex("""\bdispatchOperation\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerOperationDispatcherAdded\s*=\s*true"""),
            Regex("""providerOperationDispatcherExistsForNonDisabledProvider\s*=\s*true"""),
            Regex("""providerOperationDispatcherReachableFromSelection\s*=\s*true"""),
            Regex("""providerOperationDispatcherReachableFromRegistry\s*=\s*true"""),
            Regex("""providerOperationDispatcherReachableFromFactory\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanInvokeSkeleton\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanInvokeCandidate\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanInvokeProviderRuntime\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanExposeProviderHandle\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanExposeCryptoObject\s*=\s*true"""),
            Regex("""providerOperationDispatcherAcceptsByteMaterial\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanExecuteProviderOperations\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanRunKat\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanUseRandomness\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanRunKdf\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanRunAead\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanComputeHeaderCommitment\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanEncryptRecords\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanDecryptRecords\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanWrapKeys\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanCreateVault\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanUnlockVault\s*=\s*true"""),
            Regex("""providerOperationDispatcherCanPersistVault\s*=\s*true"""),
            Regex("""providerFactoryAdded\s*=\s*true"""),
            Regex("""providerFactoryExistsForNonDisabledProvider\s*=\s*true"""),
            Regex("""providerRegistryContainsCandidate\s*=\s*true"""),
            Regex("""providerRegistryContainsSkeleton\s*=\s*true"""),
            Regex("""providerRegistryEnabled\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""hkdfHmacExecutionAvailable\s*=\s*true"""),
            Regex("""headerCommitmentExecutionAvailable\s*=\s*true"""),
            Regex("""recordCryptoExecutionAvailable\s*=\s*true"""),
            Regex("""keyWrappingAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider operation dispatch isolation boundary must not implement dispatchers/factories/providers, dispatch provider runtime, register/select providers, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerKatExecutionIsolationBoundaryStaysModelOnlyAndCannotRunProviderKats() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderKatExecutionIsolationBoundary.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderKatExecutionIsolationBoundary\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderKatExecutionIsolationPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderKatExecutionIsolationTopic\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderKatExecutionIsolationRisk\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderKatExecutionIsolationCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider KAT execution isolation definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\s*\("""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\("""),
            Regex("""\bCharArray\("""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderDispatcher\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""\bdispatchProviderOperation\("""),
            Regex("""\bdispatchOperation\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeProviderCheck\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""providerKatExecutorAdded\s*=\s*true"""),
            Regex("""providerKatExecutorExistsForNonDisabledProvider\s*=\s*true"""),
            Regex("""providerKatExecutorReachableFromSelection\s*=\s*true"""),
            Regex("""providerKatExecutorReachableFromRegistry\s*=\s*true"""),
            Regex("""providerKatExecutorReachableFromFactory\s*=\s*true"""),
            Regex("""providerKatExecutorReachableFromDispatch\s*=\s*true"""),
            Regex("""providerKatExecutorCanInvokeSkeleton\s*=\s*true"""),
            Regex("""providerKatExecutorCanInvokeCandidate\s*=\s*true"""),
            Regex("""providerKatExecutorCanInvokeProviderRuntime\s*=\s*true"""),
            Regex("""providerKatExecutorCanExposeProviderHandle\s*=\s*true"""),
            Regex("""providerKatExecutorCanExposeCryptoObject\s*=\s*true"""),
            Regex("""providerKatExecutorAcceptsByteMaterial\s*=\s*true"""),
            Regex("""providerKatExecutorCanExecuteProviderOperations\s*=\s*true"""),
            Regex("""providerKatExecutorCanUseRandomness\s*=\s*true"""),
            Regex("""providerKatExecutorCanRunKdf\s*=\s*true"""),
            Regex("""providerKatExecutorCanRunAead\s*=\s*true"""),
            Regex("""providerKatExecutorCanComputeHeaderCommitment\s*=\s*true"""),
            Regex("""providerKatExecutorCanEncryptRecords\s*=\s*true"""),
            Regex("""providerKatExecutorCanDecryptRecords\s*=\s*true"""),
            Regex("""providerKatExecutorCanWrapKeys\s*=\s*true"""),
            Regex("""providerKatExecutorCanAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""providerKatExecutorCanAuthorizeProductionProviderSelectable\s*=\s*true"""),
            Regex("""providerKatExecutorCanCreateVault\s*=\s*true"""),
            Regex("""providerKatExecutorCanUnlockVault\s*=\s*true"""),
            Regex("""providerKatExecutorCanPersistVault\s*=\s*true"""),
            Regex("""providerKatExecutorCanEnableMainnet\s*=\s*true"""),
            Regex("""providerOperationDispatcherAdded\s*=\s*true"""),
            Regex("""providerFactoryAdded\s*=\s*true"""),
            Regex("""providerFactoryExistsForNonDisabledProvider\s*=\s*true"""),
            Regex("""providerRegistryContainsCandidate\s*=\s*true"""),
            Regex("""providerRegistryContainsSkeleton\s*=\s*true"""),
            Regex("""providerRegistryEnabled\s*=\s*true"""),
            Regex("""providerRuntimeInstantiable\s*=\s*true"""),
            Regex("""providerSelectable\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationAuthorized\s*=\s*true"""),
            Regex("""providerKatExecutionAvailable\s*=\s*true"""),
            Regex("""runtimeRandomnessAvailable\s*=\s*true"""),
            Regex("""kdfExecutionAvailable\s*=\s*true"""),
            Regex("""aeadExecutionAvailable\s*=\s*true"""),
            Regex("""hkdfHmacExecutionAvailable\s*=\s*true"""),
            Regex("""headerCommitmentExecutionAvailable\s*=\s*true"""),
            Regex("""recordCryptoExecutionAvailable\s*=\s*true"""),
            Regex("""keyWrappingAvailable\s*=\s*true"""),
            Regex("""vaultCreationAvailable\s*=\s*true"""),
            Regex("""vaultUnlockAvailable\s*=\s*true"""),
            Regex("""vaultPersistenceAvailable\s*=\s*true"""),
            Regex("""mainnetAvailable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider KAT execution isolation boundary must not implement KAT executors/dispatchers/factories/providers, run KATs, register/select providers, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerExecutableKatDecisionGateStaysModelOnlyAndCannotRunProviderKats() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatDecisionGate.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatDecisionGate\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatDecisionGatePolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatRequiredEvidence\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatFutureStage\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatDecisionCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider executable KAT decision-gate definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\.File\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bProviderHandle\("""),
            Regex("""\bCryptoObject\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderDispatcher\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""\bdispatchProviderOperation\("""),
            Regex("""\bdispatchOperation\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bproductionProviderStartupSelfTest\("""),
            Regex("""\breleaseValidationKat\("""),
            Regex("""\bruntimeProviderCheck\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""canIntroduceExecutableKatNow\s*=\s*true"""),
            Regex("""canIntroduceTestOnlyExecutableKatNow\s*=\s*true"""),
            Regex("""canIntroduceProductionExecutableKatNow\s*=\s*true"""),
            Regex("""canRunProviderOperations\s*=\s*true"""),
            Regex("""canRunRandomness\s*=\s*true"""),
            Regex("""canRunKdf\s*=\s*true"""),
            Regex("""canRunAead\s*=\s*true"""),
            Regex("""canRunHkdf\s*=\s*true"""),
            Regex("""canRunHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""executableKatAllowedNow\s*=\s*true"""),
            Regex("""testOnlyExecutableKatAllowedNow\s*=\s*true"""),
            Regex("""productionExecutableKatAllowedNow\s*=\s*true"""),
            Regex("""katResultCanAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""katResultCanSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""katResultCanAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""katResultCanAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""katResultCanAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""katResultCanAuthorizeMainnet\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""providerOperationsExecute\s*=\s*true"""),
            Regex("""randomnessExecutes\s*=\s*true"""),
            Regex("""kdfExecutes\s*=\s*true"""),
            Regex("""aeadExecutes\s*=\s*true"""),
            Regex("""hkdfExecutes\s*=\s*true"""),
            Regex("""hmacExecutes\s*=\s*true"""),
            Regex("""keyGenerationExecutes\s*=\s*true"""),
            Regex("""keysetStorageExecutes\s*=\s*true"""),
            Regex("""storageReadinessApproved\s*=\s*true"""),
            Regex("""secureSecretStorageAvailable\s*=\s*true"""),
            Regex("""secureMetadataStorageAvailable\s*=\s*true"""),
            Regex("""productionSyncEnabled\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider executable KAT decision gate must not implement KAT executors/dispatchers/factories/providers, run KATs, register/select providers, use provider runtime, crypto, randomness, storage, platform, settings, BDK, network/process, logging, byte-array material, or readiness-success APIs: $offenders",
        )
    }

    @Test
    fun providerExecutableKatPrerequisiteAuditStaysModelOnlyAndMaterialFree() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatPrerequisiteAudit.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatPrerequisiteAudit\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatPrerequisiteCategory\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Provider executable KAT prerequisite audit definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderDispatcher\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""\bdispatchProviderOperation\("""),
            Regex("""\bdispatchOperation\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bproductionProviderStartupSelfTest\("""),
            Regex("""\breleaseValidationKat\("""),
            Regex("""\bruntimeProviderCheck\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""canAuthorizeProviderExecution\s*=\s*true"""),
            Regex("""canAuthorizeExecutableKatPath\s*=\s*true"""),
            Regex("""canAuthorizeTestOnlyExecutableKatPath\s*=\s*true"""),
            Regex("""canAuthorizeProductionExecutableKatPath\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""executableProviderImplementationPresent\s*=\s*true"""),
            Regex("""executableProviderKatExecutorPresent\s*=\s*true"""),
            Regex("""modeledEvidenceCanAuthorizeExecution\s*=\s*true"""),
            Regex("""sourceSetConfinementAuthorizesExecution\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Provider executable KAT prerequisite audit must remain model-only, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and authorization-disabled: $offenders",
        )
    }

    @Test
    fun providerTestOnlyExecutableKatScopeDecisionStaysModelOnlyAndMaterialFree() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate\b"""),
            Regex("""\b(?:class|object|interface|enum class|sealed class|data class)\s+SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only executable KAT scope decision definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bBiometricPrompt\b"""),
            Regex("""\bAndroidKeyStore\b"""),
            Regex("""\bKeyGenParameterSpec\b"""),
            Regex("""\bCredentialManager\s*\("""),
            Regex("""\bKeyStore\s*\("""),
            Regex("""\blibsecret\b"""),
            Regex("""\bSecretService\b"""),
            Regex("""\bKWallet\b"""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bRandom\.Default\b"""),
            Regex("""\bMath\.random\b"""),
            Regex("""\bSystem\.currentTimeMillis\("""),
            Regex("""\bSystem\.nanoTime\("""),
            Regex("""\bUUID\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.authenticate\("""),
            Regex("""\.derive\("""),
            Regex("""\bverifyTag\("""),
            Regex("""\bgenerateNew\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderDispatcher\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderFactory\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderAdapter\b"""),
            Regex("""\bselectableProvider\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""\bdispatchProviderOperation\("""),
            Regex("""\bdispatchOperation\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bproviderOperation\("""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bproductionProviderStartupSelfTest\("""),
            Regex("""\breleaseValidationKat\("""),
            Regex("""\bruntimeProviderCheck\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\brandomnessHealthCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bcreateHeader\("""),
            Regex("""\bcreateManifest\("""),
            Regex("""\bcreateRecord\("""),
            Regex("""\bcommitInitialPersistence\("""),
            Regex("""\breadManifest\("""),
            Regex("""\bwriteManifest\("""),
            Regex("""\breadStorageIndex\("""),
            Regex("""\bwriteStorageIndex\("""),
            Regex("""\breadRecord\("""),
            Regex("""\bwriteRecord\("""),
            Regex("""\bcreateSession\("""),
            Regex("""\bcreateActiveSession\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""canImplementTestOnlyExecutorNow\s*=\s*true"""),
            Regex("""canRunTestOnlyProviderKatNow\s*=\s*true"""),
            Regex("""canRunProductionProviderKatNow\s*=\s*true"""),
            Regex("""canUseCommonMainExecution\s*=\s*true"""),
            Regex("""canUseAndroidMainExecution\s*=\s*true"""),
            Regex("""canUseDesktopMainExecution\s*=\s*true"""),
            Regex("""canUseDesktopTestExecutionNow\s*=\s*true"""),
            Regex("""canUseAndroidInstrumentedTestExecutionNow\s*=\s*true"""),
            Regex("""canExecuteProviderOperationsNow\s*=\s*true"""),
            Regex("""canExecuteRandomnessNow\s*=\s*true"""),
            Regex("""canExecuteKdfNow\s*=\s*true"""),
            Regex("""canExecuteAeadNow\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""currentBranchExecutionAuthorized\s*=\s*true"""),
            Regex("""testOnlyExecutorImplementationPresent\s*=\s*true"""),
            Regex("""productionExecutorAllowed\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only executable KAT scope decision must remain model-only, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and current-execution-disabled: $offenders",
        )
    }

    @Test
    fun testOnlyProviderKatExecutorContractStaysModelOnlyAndNonRunnable() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorContract.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorContractStatus\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorRole\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorInputClass\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorOperationClass\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorContractCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only provider KAT executor contract definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""android\."""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bprivate\b"""),
            Regex("""\bsecret\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\binterface\s+\w*Executor\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""fun\s+run\s*\("""),
            Regex("""fun\s+execute\s*\("""),
            Regex("""fun\s+encrypt\s*\("""),
            Regex("""fun\s+decrypt\s*\("""),
            Regex("""fun\s+derive\s*\("""),
            Regex("""fun\s+generate\s*\("""),
            Regex("""fun\s+wrap\s*\("""),
            Regex("""fun\s+unwrap\s*\("""),
            Regex("""executorImplemented\s*=\s*true"""),
            Regex("""executorCallable\s*=\s*true"""),
            Regex("""canImplementExecutorNow\s*=\s*true"""),
            Regex("""canRunExecutorNow\s*=\s*true"""),
            Regex("""canUseDesktopTestExecutionNow\s*=\s*true"""),
            Regex("""canUseAndroidInstrumentedTestExecutionNow\s*=\s*true"""),
            Regex("""canUseCommonMainExecution\s*=\s*true"""),
            Regex("""canUseAndroidMainExecution\s*=\s*true"""),
            Regex("""canUseDesktopMainExecution\s*=\s*true"""),
            Regex("""canAcceptRawMaterial\s*=\s*true"""),
            Regex("""canAcceptProviderHandles\s*=\s*true"""),
            Regex("""canAcceptCryptoObjects\s*=\s*true"""),
            Regex("""canExecuteProviderOperations\s*=\s*true"""),
            Regex("""canExecuteRandomness\s*=\s*true"""),
            Regex("""canExecuteKdf\s*=\s*true"""),
            Regex("""canExecuteAead\s*=\s*true"""),
            Regex("""canExecuteHkdf\s*=\s*true"""),
            Regex("""canExecuteHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""currentImplementationAuthorized\s*=\s*true"""),
            Regex("""currentAuthorized\s*=\s*true"""),
            Regex("""currentAccepted\s*=\s*true"""),
            Regex("""currentResultSurfaceAvailable\s*=\s*true"""),
            Regex("""currentExecutorResultCanAuthorize\s*=\s*true"""),
            Regex("""futureExecutorResultCanAuthorize\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only provider KAT executor contract must remain model-only, non-runnable, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and authorization-disabled: $offenders",
        )
    }

    @Test
    fun testOnlyProviderKatVectorCatalogStaysModelOnlyReferenceOnlyAndMaterialFree() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatVectorSourceClass\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatPositiveVectorClass\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatNegativeVectorClass\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only provider KAT vector catalog definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""android\."""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bprivate\b"""),
            Regex("""\bsecret\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\bkeyBytes\b"""),
            Regex("""\bnonceBytes\b"""),
            Regex("""\bsaltBytes\b"""),
            Regex("""\bplaintextBytes\b"""),
            Regex("""\bciphertextBytes\b"""),
            Regex("""\btagBytes\b"""),
            Regex("""\bexpectedBytes\b"""),
            Regex("""\bvectorHex\b"""),
            Regex("""\bexpectedHex\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\binterface\s+\w*Executor\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""fun\s+run\s*\("""),
            Regex("""fun\s+execute\s*\("""),
            Regex("""fun\s+encrypt\s*\("""),
            Regex("""fun\s+decrypt\s*\("""),
            Regex("""fun\s+derive\s*\("""),
            Regex("""fun\s+generate\s*\("""),
            Regex("""fun\s+wrap\s*\("""),
            Regex("""fun\s+unwrap\s*\("""),
            Regex("""rawVectorMaterialPresent\s*=\s*true"""),
            Regex("""executorImplemented\s*=\s*true"""),
            Regex("""executorCallable\s*=\s*true"""),
            Regex("""canRunVectorsNow\s*=\s*true"""),
            Regex("""canExecuteProviderOperations\s*=\s*true"""),
            Regex("""canExecuteRandomness\s*=\s*true"""),
            Regex("""canExecuteKdf\s*=\s*true"""),
            Regex("""canExecuteAead\s*=\s*true"""),
            Regex("""canExecuteHkdf\s*=\s*true"""),
            Regex("""canExecuteHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeSecureStorageSuccess\s*=\s*true"""),
            Regex("""canAuthorizeSecureMetadataSuccess\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeSigning\s*=\s*true"""),
            Regex("""canAuthorizeBroadcasting\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""currentExecutable\s*=\s*true"""),
            Regex("""canAuthorizeExecution\s*=\s*true"""),
            Regex("""canAuthorizeProduction\s*=\s*true"""),
            Regex("""currentAccepted\s*=\s*true"""),
            Regex("""payloadAllowed\s*=\s*true"""),
            Regex("""catalogEntryCanAuthorize\s*=\s*true"""),
            Regex("""currentCatalogCanAuthorize\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only provider KAT vector catalog must remain model-only, reference-only, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and authorization-disabled: $offenders",
        )
    }

    @Test
    fun testOnlyProviderKatExecutorReadinessGateStaysModelOnlyNonRunnableAndMaterialFree() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only provider KAT executor readiness gate definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""android\."""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bprivate\b"""),
            Regex("""\bsecret\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\bkeyBytes\b"""),
            Regex("""\bnonceBytes\b"""),
            Regex("""\bsaltBytes\b"""),
            Regex("""\bplaintextBytes\b"""),
            Regex("""\bciphertextBytes\b"""),
            Regex("""\btagBytes\b"""),
            Regex("""\bexpectedBytes\b"""),
            Regex("""\bvectorHex\b"""),
            Regex("""\bexpectedHex\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\binterface\s+\w*Executor\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""fun\s+run\s*\("""),
            Regex("""fun\s+execute\s*\("""),
            Regex("""fun\s+encrypt\s*\("""),
            Regex("""fun\s+decrypt\s*\("""),
            Regex("""fun\s+derive\s*\("""),
            Regex("""fun\s+generate\s*\("""),
            Regex("""fun\s+wrap\s*\("""),
            Regex("""fun\s+unwrap\s*\("""),
            Regex("""fun\s+dispatch\s*\("""),
            Regex("""fun\s+invoke\s*\("""),
            Regex("""fun\s+call\s*\("""),
            Regex("""fun\s+perform\s*\("""),
            Regex("""readyForExecutorImplementationNow\s*=\s*true"""),
            Regex("""executorImplementationAuthorizedNow\s*=\s*true"""),
            Regex("""executorCallableNow\s*=\s*true"""),
            Regex("""canAddRunnableInterfaceNow\s*=\s*true"""),
            Regex("""canUseDesktopTestExecutionNow\s*=\s*true"""),
            Regex("""canUseAndroidInstrumentedTestExecutionNow\s*=\s*true"""),
            Regex("""canUseCommonMainExecution\s*=\s*true"""),
            Regex("""canUseAndroidMainExecution\s*=\s*true"""),
            Regex("""canUseDesktopMainExecution\s*=\s*true"""),
            Regex("""canAcceptRawMaterial\s*=\s*true"""),
            Regex("""canAcceptProviderHandles\s*=\s*true"""),
            Regex("""canAcceptCryptoObjects\s*=\s*true"""),
            Regex("""canExecuteProviderOperations\s*=\s*true"""),
            Regex("""canExecuteRandomness\s*=\s*true"""),
            Regex("""canExecuteKdf\s*=\s*true"""),
            Regex("""canExecuteAead\s*=\s*true"""),
            Regex("""canExecuteHkdf\s*=\s*true"""),
            Regex("""canExecuteHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""implementationSufficient\s*=\s*true"""),
            Regex("""currentBranchAuthorizesImplementation\s*=\s*true"""),
            Regex("""implementationAuthorization\s*=\s*true"""),
            Regex("""productionAuthorization\s*=\s*true"""),
            Regex("""canAuthorizeProduction\s*=\s*true"""),
            Regex("""canAuthorizeCurrentImplementation\s*=\s*true"""),
            Regex("""completedInCurrentBranch\s*=\s*true"""),
            Regex("""sourceSetPolicySufficientForImplementation\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only provider KAT executor readiness gate must remain model-only, non-runnable, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and implementation-disabled: $offenders",
        )
    }

    @Test
    fun testOnlyProviderKatSourceSetConfinementStaysModelOnlyNonRunnableAndMaterialFree() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatSourceSetCategory\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatConfinementRule\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only provider KAT source-set confinement definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""android\."""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bprivate\b"""),
            Regex("""\bsecret\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\bkeyBytes\b"""),
            Regex("""\bnonceBytes\b"""),
            Regex("""\bsaltBytes\b"""),
            Regex("""\bplaintextBytes\b"""),
            Regex("""\bciphertextBytes\b"""),
            Regex("""\btagBytes\b"""),
            Regex("""\bexpectedBytes\b"""),
            Regex("""\bvectorHex\b"""),
            Regex("""\bexpectedHex\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\binterface\s+\w*Executor\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""fun\s+run\s*\("""),
            Regex("""fun\s+execute\s*\("""),
            Regex("""fun\s+encrypt\s*\("""),
            Regex("""fun\s+decrypt\s*\("""),
            Regex("""fun\s+derive\s*\("""),
            Regex("""fun\s+generate\s*\("""),
            Regex("""fun\s+wrap\s*\("""),
            Regex("""fun\s+unwrap\s*\("""),
            Regex("""fun\s+dispatch\s*\("""),
            Regex("""fun\s+invoke\s*\("""),
            Regex("""fun\s+call\s*\("""),
            Regex("""fun\s+perform\s*\("""),
            Regex("""fun\s+start\s*\("""),
            Regex("""fun\s+launch\s*\("""),
            Regex("""fun\s+test\s*\("""),
            Regex("""executorImplementationAuthorizedNow\s*=\s*true"""),
            Regex("""executorCallableNow\s*=\s*true"""),
            Regex("""canUseCommonMainExecution\s*=\s*true"""),
            Regex("""canUseAndroidMainExecution\s*=\s*true"""),
            Regex("""canUseDesktopMainExecution\s*=\s*true"""),
            Regex("""canUseCommonTestExecution\s*=\s*true"""),
            Regex("""canUseDesktopTestExecutionNow\s*=\s*true"""),
            Regex("""canUseAndroidInstrumentedTestExecutionNow\s*=\s*true"""),
            Regex("""canAddRunnableInterfaceNow\s*=\s*true"""),
            Regex("""canAddRawVectorMaterialNow\s*=\s*true"""),
            Regex("""canAcceptRawMaterial\s*=\s*true"""),
            Regex("""canAcceptProviderHandles\s*=\s*true"""),
            Regex("""canAcceptCryptoObjects\s*=\s*true"""),
            Regex("""canExecuteProviderOperations\s*=\s*true"""),
            Regex("""canExecuteRandomness\s*=\s*true"""),
            Regex("""canExecuteKdf\s*=\s*true"""),
            Regex("""canExecuteAead\s*=\s*true"""),
            Regex("""canExecuteHkdf\s*=\s*true"""),
            Regex("""canExecuteHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""currentImplementationAuthorized\s*=\s*true"""),
            Regex("""currentExecutionAuthorized\s*=\s*true"""),
            Regex("""sourceSetModelingAuthorizesImplementation\s*=\s*true"""),
            Regex("""currentBranchAuthorizesImplementation\s*=\s*true"""),
            Regex("""currentBranchCanAuthorizeImplementation\s*=\s*true"""),
            Regex("""sourceSetConfinementEvidenceCanAuthorize\s*=\s*true"""),
            Regex("""currentConfinementCanAuthorize\s*=\s*true"""),
            Regex("""currentSourcePresent\s*=\s*true"""),
            Regex("""canAuthorizeImplementation\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only provider KAT source-set confinement must remain model-only, non-runnable, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and implementation-disabled: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityDecisionStaysModelOnlyNonInstantiableAndMaterialFree() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityDecisionStatus\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityCategory\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityConstraint\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only provider identity decision definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""android\."""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bprivate\b"""),
            Regex("""\bsecret\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\bkeyBytes\b"""),
            Regex("""\bnonceBytes\b"""),
            Regex("""\bsaltBytes\b"""),
            Regex("""\bplaintextBytes\b"""),
            Regex("""\bciphertextBytes\b"""),
            Regex("""\btagBytes\b"""),
            Regex("""\bexpectedBytes\b"""),
            Regex("""\bvectorHex\b"""),
            Regex("""\bexpectedHex\b"""),
            Regex("""\bclass\s+TestOnlyProvider\b"""),
            Regex("""\bobject\s+TestOnlyProvider\b"""),
            Regex("""\bimplements\s+VaultCryptoProvider\b"""),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\binterface\s+\w*Executor\b"""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""fun\s+run\s*\("""),
            Regex("""fun\s+execute\s*\("""),
            Regex("""fun\s+encrypt\s*\("""),
            Regex("""fun\s+decrypt\s*\("""),
            Regex("""fun\s+derive\s*\("""),
            Regex("""fun\s+generate\s*\("""),
            Regex("""fun\s+wrap\s*\("""),
            Regex("""fun\s+unwrap\s*\("""),
            Regex("""fun\s+dispatch\s*\("""),
            Regex("""fun\s+invoke\s*\("""),
            Regex("""fun\s+call\s*\("""),
            Regex("""fun\s+perform\s*\("""),
            Regex("""fun\s+start\s*\("""),
            Regex("""fun\s+launch\s*\("""),
            Regex("""fun\s+test\s*\("""),
            Regex("""testOnlyProviderIdentityImplemented\s*=\s*true"""),
            Regex("""productionProviderIdentityImplemented\s*=\s*true"""),
            Regex("""providerFactoryAvailable\s*=\s*true"""),
            Regex("""providerDispatcherAvailable\s*=\s*true"""),
            Regex("""nonDisabledRegistryEntryAvailable\s*=\s*true"""),
            Regex("""executorTargetAvailable\s*=\s*true"""),
            Regex("""canImplementProviderNow\s*=\s*true"""),
            Regex("""canInstantiateProviderNow\s*=\s*true"""),
            Regex("""canRegisterProviderNow\s*=\s*true"""),
            Regex("""canDispatchProviderNow\s*=\s*true"""),
            Regex("""canTargetProviderWithExecutorNow\s*=\s*true"""),
            Regex("""canUseProviderForKatNow\s*=\s*true"""),
            Regex("""canUseProviderForVaultCreation\s*=\s*true"""),
            Regex("""canUseProviderForVaultUnlock\s*=\s*true"""),
            Regex("""canUseProviderForVaultPersistence\s*=\s*true"""),
            Regex("""canAcceptRawMaterial\s*=\s*true"""),
            Regex("""canAcceptProviderHandles\s*=\s*true"""),
            Regex("""canAcceptCryptoObjects\s*=\s*true"""),
            Regex("""canExecuteProviderOperations\s*=\s*true"""),
            Regex("""canExecuteRandomness\s*=\s*true"""),
            Regex("""canExecuteKdf\s*=\s*true"""),
            Regex("""canExecuteAead\s*=\s*true"""),
            Regex("""canExecuteHkdf\s*=\s*true"""),
            Regex("""canExecuteHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only provider identity decision must remain model-only, non-instantiable, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and authorization-disabled: $offenders",
        )
    }

    @Test
    fun testOnlyProviderIdentityIsolationGuardStaysLabelOnlyAndUnreachable() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        ).filter { it.exists() }
        val approvedPolicyFile =
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt"
        val policyDefinitionPatterns = listOf(
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityIsolationStatus\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityIsolationSurface\b"""),
            Regex("""\b(?:class|object|enum class|sealed class|data class)\s+SkaldVaultV1TestOnlyProviderIdentityIsolationCapability\b"""),
        )
        val misplacedDefinitions = productionRoots
            .flatMap { sourceRoot ->
                sourceRoot.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { file ->
                        val relative = file.relativeTo(root).invariantSeparatorsPath
                        relative != approvedPolicyFile &&
                            policyDefinitionPatterns.any { it.containsMatchIn(file.readText()) }
                    }
                    .map { it.relativeTo(root).invariantSeparatorsPath }
                    .toList()
            }

        assertTrue(
            misplacedDefinitions.isEmpty(),
            "Test-only provider identity isolation guard definitions must stay in the exact approved file: $misplacedDefinitions",
        )

        val source = File(root, approvedPolicyFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""^import\s+""", RegexOption.MULTILINE),
            Regex("""System\.getenv"""),
            Regex("""System\.getProperty"""),
            Regex("""android\."""),
            Regex("""\bjava\.io\b"""),
            Regex("""\bjava\.nio\.file\b"""),
            Regex("""\bkotlin\.io\.path\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bFile\("""),
            Regex("""\bPath\("""),
            Regex("""\bPath\.of\b"""),
            Regex("""\bPaths\.get\b"""),
            Regex("""\bFiles\."""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""javax\.crypto"""),
            Regex("""java\.security"""),
            Regex("""java\.util\.Random"""),
            Regex("""kotlin\.random"""),
            Regex("""org\.bouncycastle"""),
            Regex("""com\.google\.crypto"""),
            Regex("""org\.bitcoindevkit"""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bMessageDigest\b"""),
            Regex("""\bMac\.getInstance\("""),
            Regex("""\bArgon2BytesGenerator\b"""),
            Regex("""\bAeadConfig\b"""),
            Regex("""\bXChaCha20Poly1305Key\b"""),
            Regex("""\bKeysetHandle\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bSecretKey\b"""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\.encrypt\("""),
            Regex("""\.decrypt\("""),
            Regex("""\.derive\("""),
            Regex("""\bByteArray\b"""),
            Regex("""\bCharArray\b"""),
            Regex("""\bUByteArray\b"""),
            Regex("""\bplaintext\b"""),
            Regex("""\bciphertext\b"""),
            Regex("""\bnonce\b"""),
            Regex("""\bsalt\b"""),
            Regex("""\bpassphrase\b"""),
            Regex("""\bprivate\b"""),
            Regex("""\bsecret\b"""),
            Regex("""\bproviderHandle\b"""),
            Regex("""\bcryptoObject\b"""),
            Regex("""\bFilePath\b"""),
            Regex("""\bStorageHandle\b"""),
            Regex("""\bBackendHandle\b"""),
            Regex("""\bkeyBytes\b"""),
            Regex("""\bnonceBytes\b"""),
            Regex("""\bsaltBytes\b"""),
            Regex("""\bplaintextBytes\b"""),
            Regex("""\bciphertextBytes\b"""),
            Regex("""\btagBytes\b"""),
            Regex("""\bexpectedBytes\b"""),
            Regex("""\bvectorHex\b"""),
            Regex("""\bexpectedHex\b"""),
            Regex("""\bclass\s+TestOnlyProvider\b"""),
            Regex("""\bobject\s+TestOnlyProvider\b"""),
            Regex("""\bimplements\s+VaultCryptoProvider\b"""),
            Regex(""":\s*VaultCryptoProvider\b"""),
            Regex("""\bProviderFactory\s*\("""),
            Regex("""\bProviderDispatcher\s*\("""),
            Regex("""\bProviderRegistry\s*\("""),
            Regex("""\bExecutorTarget\s*\("""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKatExecutor\b"""),
            Regex("""\b(?:class|object|interface|data class)\s+\w*ProviderKATExecutor\b"""),
            Regex("""\bProviderKatExecutor\s*\("""),
            Regex("""\bProviderKATExecutor\s*\("""),
            Regex("""\binterface\s+\w*Executor\b"""),
            Regex("""\bregisterProvider\("""),
            Regex("""\bcreateProvider\("""),
            Regex("""\bconstructProvider\("""),
            Regex("""VaultCryptoProviderSelectionRegistry\.select"""),
            Regex("""\bvalidateKat\("""),
            Regex("""\brunProviderKat\("""),
            Regex("""\bexecuteProviderKat\("""),
            Regex("""\bproviderSelfTest\("""),
            Regex("""\bruntimeRandomnessCheck\("""),
            Regex("""\bgenerateEntropy\("""),
            Regex("""\bgenerateSalt\("""),
            Regex("""\bgenerateNonce\("""),
            Regex("""\bgenerateKey\("""),
            Regex("""\bderiveKey\("""),
            Regex("""\bcomputeHeaderCommitment\("""),
            Regex("""\bverifyHeaderCommitment\("""),
            Regex("""\bencryptRecord\("""),
            Regex("""\bdecryptRecord\("""),
            Regex("""\bwrapKey\("""),
            Regex("""\bunwrapKey\("""),
            Regex("""\bcreateVault\("""),
            Regex("""\bunlockVault\("""),
            Regex("""\bpersistVault\("""),
            Regex("""\bHttpClient\b"""),
            Regex("""\bURL\("""),
            Regex("""\bURI\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bLogger\b"""),
            Regex("""\bprintln\("""),
            Regex("""\bprintStackTrace\("""),
            Regex("""fun\s+run\s*\("""),
            Regex("""fun\s+execute\s*\("""),
            Regex("""fun\s+encrypt\s*\("""),
            Regex("""fun\s+decrypt\s*\("""),
            Regex("""fun\s+derive\s*\("""),
            Regex("""fun\s+generate\s*\("""),
            Regex("""fun\s+wrap\s*\("""),
            Regex("""fun\s+unwrap\s*\("""),
            Regex("""fun\s+dispatch\s*\("""),
            Regex("""fun\s+invoke\s*\("""),
            Regex("""fun\s+call\s*\("""),
            Regex("""fun\s+perform\s*\("""),
            Regex("""fun\s+start\s*\("""),
            Regex("""fun\s+launch\s*\("""),
            Regex("""fun\s+test\s*\("""),
            Regex("""testOnlyProviderIdentityImplemented\s*=\s*true"""),
            Regex("""productionProviderIdentityImplemented\s*=\s*true"""),
            Regex("""instantiableProviderIdentityAvailable\s*=\s*true"""),
            Regex("""registrySelectableIdentityAvailable\s*=\s*true"""),
            Regex("""factoryReachableIdentityAvailable\s*=\s*true"""),
            Regex("""dispatcherReachableIdentityAvailable\s*=\s*true"""),
            Regex("""executorTargetableIdentityAvailable\s*=\s*true"""),
            Regex("""vaultLifecycleReachableIdentityAvailable\s*=\s*true"""),
            Regex("""persistenceReachableIdentityAvailable\s*=\s*true"""),
            Regex("""canImplementProviderNow\s*=\s*true"""),
            Regex("""canInstantiateProviderNow\s*=\s*true"""),
            Regex("""canRegisterProviderNow\s*=\s*true"""),
            Regex("""canDispatchProviderNow\s*=\s*true"""),
            Regex("""canTargetProviderWithExecutorNow\s*=\s*true"""),
            Regex("""canUseProviderForKatNow\s*=\s*true"""),
            Regex("""canUseProviderForVaultCreation\s*=\s*true"""),
            Regex("""canUseProviderForVaultUnlock\s*=\s*true"""),
            Regex("""canUseProviderForVaultPersistence\s*=\s*true"""),
            Regex("""canAcceptRawMaterial\s*=\s*true"""),
            Regex("""canAcceptProviderHandles\s*=\s*true"""),
            Regex("""canAcceptCryptoObjects\s*=\s*true"""),
            Regex("""canExecuteProviderOperations\s*=\s*true"""),
            Regex("""canExecuteRandomness\s*=\s*true"""),
            Regex("""canExecuteKdf\s*=\s*true"""),
            Regex("""canExecuteAead\s*=\s*true"""),
            Regex("""canExecuteHkdf\s*=\s*true"""),
            Regex("""canExecuteHmac\s*=\s*true"""),
            Regex("""canGenerateKeys\s*=\s*true"""),
            Regex("""canStoreKeysets\s*=\s*true"""),
            Regex("""canAuthorizeProviderSelection\s*=\s*true"""),
            Regex("""canSetProductionProviderSelectable\s*=\s*true"""),
            Regex("""canAuthorizeVaultCreation\s*=\s*true"""),
            Regex("""canAuthorizeVaultUnlock\s*=\s*true"""),
            Regex("""canAuthorizeVaultPersistence\s*=\s*true"""),
            Regex("""canAuthorizeProductionSync\s*=\s*true"""),
            Regex("""canAuthorizeMainnet\s*=\s*true"""),
            Regex("""productionProviderSelectable\s*=\s*true"""),
            Regex("""mainnetEnabled\s*=\s*true"""),
        )
        val offenders = forbiddenPatterns
            .filter { it.containsMatchIn(source) }
            .map { it.pattern }

        assertTrue(
            offenders.isEmpty(),
            "Test-only provider identity isolation guard must remain label-only, unreachable, material-free, crypto-free, platform-free, storage-free, network-free, process-free, and authorization-disabled: $offenders",
        )
    }

    private fun boundaryFiles(root: File): List<File> =
        listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinBackendAdapterModels.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinBackendEndpointPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinBackendValidation.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinWalletSyncService.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SecureMetadataStorage.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/Argon2idCalibrationPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1DisabledStorageServiceFacade.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PersistenceReadinessGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LockSessionLifecycleBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RedactionLeakageBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicyBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ClearWipeStrategyBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RuntimeRandomnessAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1KdfCalibrationAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1SecureStorageAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1UnlockAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1CreationAuthorizationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1AuthorizationReadinessMatrix.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderCandidatePackagingBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderInterfaceContractAudit.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1NonSelectableProviderSkeletonBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderRegistryIsolationGuard.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderFactoryIsolationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationDispatchIsolationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderKatExecutionIsolationBoundary.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatDecisionGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatPrerequisiteAudit.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorContract.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt"),
        )

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
