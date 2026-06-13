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
            Regex("""\bSecureRandom\b"""),
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
            Regex("""\bSecureRandom\b"""),
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
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt"),
        )

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
