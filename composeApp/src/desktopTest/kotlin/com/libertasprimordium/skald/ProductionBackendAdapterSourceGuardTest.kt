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
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt"),
        )

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
