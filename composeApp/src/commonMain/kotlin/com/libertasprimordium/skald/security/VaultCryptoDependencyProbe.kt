package com.libertasprimordium.skald.security

enum class VaultCryptoDependencyCandidate(val label: String) {
    TinkBouncyCastleSplit("Tink AEAD plus Bouncy Castle Argon2id"),
    LibsodiumKmp("Kotlin Multiplatform libsodium binding"),
    BouncyCastleOnly("Bouncy Castle provider only"),
    PlatformCryptoOnly("platform crypto only"),
    KotlinMultiplatformCrypto("other Kotlin Multiplatform crypto candidate"),
}

enum class VaultCryptoDependencyProbeStatus(val label: String) {
    SelectedForPackagingProbe("selected for packaging probe"),
    DeferredPendingReview("deferred pending review"),
    InsufficientAsPrimaryVaultStack("insufficient as primary vault stack"),
    RejectedAsDefaultVaultStack("rejected as default vault stack"),
}

enum class VaultCryptoDependencyCapability(val label: String) {
    Argon2idApiPresent("Argon2id API present"),
    ScryptApiPresent("scrypt API present"),
    XChaCha20Poly1305ApiPresent("XChaCha20-Poly1305 API present"),
    ChaCha20Poly1305ApiPresent("ChaCha20-Poly1305 API present"),
    AeadApiPresent("AEAD API present"),
    HkdfOrPrfApiPresent("HKDF or PRF API present"),
    DesktopKnownAnswerVectorsPass("desktop known-answer vectors pass"),
    KnownAnswerVectorReviewRequired("additional known-answer vector review required"),
    AndroidArtifactPinned("Android artifact pinned"),
    DesktopArtifactPinned("Linux desktop artifact pinned"),
    PureJvmNoNativeLibraries("pure JVM/Android artifacts; no native libraries"),
    NativeLibrariesRequired("native libraries required"),
    SplitProviderStack("KDF and AEAD come from separate providers"),
}

enum class VaultCryptoDependencyBlocker(val label: String) {
    DependencyReviewIncomplete("dependency review incomplete"),
    LicenseReviewIncomplete("license review incomplete"),
    KdfCalibrationMissing("KDF calibration missing"),
    KnownAnswerVectorTestsMissing("known-answer vector tests missing"),
    AndroidKnownAnswerVectorRuntimeMissing("Android known-answer vector runtime probe missing"),
    NativePackagingUnverified("native packaging unverified"),
    AndroidRuntimeProbeMissing("Android runtime probe missing"),
    DoesNotProvidePreferredAead("does not provide preferred XChaCha20-Poly1305 AEAD"),
    DoesNotProvideArgon2id("does not provide Argon2id"),
    RequiresSplitProviderDesign("requires split-provider design"),
    VaultImplementationStillDisabled("vault implementation still disabled"),
}

data class VaultCryptoDependencyProbeResult(
    val candidate: VaultCryptoDependencyCandidate,
    val status: VaultCryptoDependencyProbeStatus,
    val pinnedArtifacts: List<String>,
    val capabilities: Set<VaultCryptoDependencyCapability>,
    val blockers: Set<VaultCryptoDependencyBlocker>,
    val implementationEnabled: Boolean,
    val storageEnabled: Boolean,
    val productionPersistenceEnabled: Boolean,
    val mainnetEnabled: Boolean,
    val note: String,
) {
    val readyForVaultImplementation: Boolean
        get() = implementationEnabled &&
            storageEnabled &&
            productionPersistenceEnabled &&
            blockers.isEmpty()
}

object VaultCryptoDependencyProbeCatalog {
    fun currentSpikeResults(): List<VaultCryptoDependencyProbeResult> =
        listOf(
            VaultCryptoDependencyProbeResult(
                candidate = VaultCryptoDependencyCandidate.TinkBouncyCastleSplit,
                status = VaultCryptoDependencyProbeStatus.SelectedForPackagingProbe,
                pinnedArtifacts = listOf(
                    "com.google.crypto.tink:tink-android:1.21.0",
                    "com.google.crypto.tink:tink:1.21.0",
                    "org.bouncycastle:bcprov-jdk18on:1.84",
                ),
                capabilities = setOf(
                    VaultCryptoDependencyCapability.Argon2idApiPresent,
                    VaultCryptoDependencyCapability.XChaCha20Poly1305ApiPresent,
                    VaultCryptoDependencyCapability.ChaCha20Poly1305ApiPresent,
                    VaultCryptoDependencyCapability.AeadApiPresent,
                    VaultCryptoDependencyCapability.HkdfOrPrfApiPresent,
                    VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass,
                    VaultCryptoDependencyCapability.KnownAnswerVectorReviewRequired,
                    VaultCryptoDependencyCapability.AndroidArtifactPinned,
                    VaultCryptoDependencyCapability.DesktopArtifactPinned,
                    VaultCryptoDependencyCapability.PureJvmNoNativeLibraries,
                    VaultCryptoDependencyCapability.SplitProviderStack,
                ),
                blockers = setOf(
                    VaultCryptoDependencyBlocker.DependencyReviewIncomplete,
                    VaultCryptoDependencyBlocker.LicenseReviewIncomplete,
                    VaultCryptoDependencyBlocker.KdfCalibrationMissing,
                    VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeMissing,
                    VaultCryptoDependencyBlocker.AndroidRuntimeProbeMissing,
                    VaultCryptoDependencyBlocker.RequiresSplitProviderDesign,
                    VaultCryptoDependencyBlocker.VaultImplementationStillDisabled,
                ),
                implementationEnabled = false,
                storageEnabled = false,
                productionPersistenceEnabled = false,
                mainnetEnabled = false,
                note = "Selected only for Android/Linux compile and packaging feasibility plus desktop public KAT validation. It is not wired into vault storage.",
            ),
            VaultCryptoDependencyProbeResult(
                candidate = VaultCryptoDependencyCandidate.LibsodiumKmp,
                status = VaultCryptoDependencyProbeStatus.DeferredPendingReview,
                pinnedArtifacts = emptyList(),
                capabilities = setOf(
                    VaultCryptoDependencyCapability.Argon2idApiPresent,
                    VaultCryptoDependencyCapability.ScryptApiPresent,
                    VaultCryptoDependencyCapability.XChaCha20Poly1305ApiPresent,
                    VaultCryptoDependencyCapability.NativeLibrariesRequired,
                ),
                blockers = setOf(
                    VaultCryptoDependencyBlocker.DependencyReviewIncomplete,
                    VaultCryptoDependencyBlocker.LicenseReviewIncomplete,
                    VaultCryptoDependencyBlocker.NativePackagingUnverified,
                    VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing,
                    VaultCryptoDependencyBlocker.VaultImplementationStillDisabled,
                ),
                implementationEnabled = false,
                storageEnabled = false,
                productionPersistenceEnabled = false,
                mainnetEnabled = false,
                note = "Still attractive as one primitive family, but native library packaging and Kotlin compatibility need a separate spike.",
            ),
            VaultCryptoDependencyProbeResult(
                candidate = VaultCryptoDependencyCandidate.BouncyCastleOnly,
                status = VaultCryptoDependencyProbeStatus.InsufficientAsPrimaryVaultStack,
                pinnedArtifacts = listOf("org.bouncycastle:bcprov-jdk18on:1.84"),
                capabilities = setOf(
                    VaultCryptoDependencyCapability.Argon2idApiPresent,
                    VaultCryptoDependencyCapability.ChaCha20Poly1305ApiPresent,
                    VaultCryptoDependencyCapability.AeadApiPresent,
                    VaultCryptoDependencyCapability.PureJvmNoNativeLibraries,
                ),
                blockers = setOf(
                    VaultCryptoDependencyBlocker.DoesNotProvidePreferredAead,
                    VaultCryptoDependencyBlocker.KdfCalibrationMissing,
                    VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing,
                    VaultCryptoDependencyBlocker.VaultImplementationStillDisabled,
                ),
                implementationEnabled = false,
                storageEnabled = false,
                productionPersistenceEnabled = false,
                mainnetEnabled = false,
                note = "Useful Argon2id provider candidate, but not enough alone for the preferred XChaCha record envelope.",
            ),
            VaultCryptoDependencyProbeResult(
                candidate = VaultCryptoDependencyCandidate.PlatformCryptoOnly,
                status = VaultCryptoDependencyProbeStatus.RejectedAsDefaultVaultStack,
                pinnedArtifacts = emptyList(),
                capabilities = setOf(VaultCryptoDependencyCapability.AeadApiPresent),
                blockers = setOf(
                    VaultCryptoDependencyBlocker.DoesNotProvideArgon2id,
                    VaultCryptoDependencyBlocker.DoesNotProvidePreferredAead,
                    VaultCryptoDependencyBlocker.VaultImplementationStillDisabled,
                ),
                implementationEnabled = false,
                storageEnabled = false,
                productionPersistenceEnabled = false,
                mainnetEnabled = false,
                note = "Rejected as the default vault stack because it pushes toward PBKDF2/AES-GCM-only storage.",
            ),
            VaultCryptoDependencyProbeResult(
                candidate = VaultCryptoDependencyCandidate.KotlinMultiplatformCrypto,
                status = VaultCryptoDependencyProbeStatus.DeferredPendingReview,
                pinnedArtifacts = emptyList(),
                capabilities = emptySet(),
                blockers = setOf(
                    VaultCryptoDependencyBlocker.DependencyReviewIncomplete,
                    VaultCryptoDependencyBlocker.NativePackagingUnverified,
                    VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing,
                    VaultCryptoDependencyBlocker.VaultImplementationStillDisabled,
                ),
                implementationEnabled = false,
                storageEnabled = false,
                productionPersistenceEnabled = false,
                mainnetEnabled = false,
                note = "Deferred unless a clearly maintained KMP stack proves Android/Linux packaging and the preferred primitive set.",
            ),
        )
}
