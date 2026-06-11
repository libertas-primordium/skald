package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphraseBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphraseEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphraseFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphraseNormalizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyCategory
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphraseRetryPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphraseThrottlePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionValueKind
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultPassphrasePolicyBoundaryTest {
    @Test
    fun defaultPassphrasePolicyIsBlockedAndFailClosed() {
        val result = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.summary(),
        )
        val evidence = result.blockedEvidence()
        val capability = evidence.capability

        assertFalse(result.passphraseInputAccepted)
        assertFalse(result.unlockAvailable)
        assertEquals(SkaldVaultV1VaultPassphrasePolicyDecision.BlockedFailClosed, evidence.decision)
        assertEquals(SkaldVaultV1VaultPassphrasePolicyStatus.InputNotAccepted, evidence.status)
        assertFalse(capability.passphraseInputAccepted)
        assertFalse(capability.passphraseStored)
        assertFalse(capability.passphraseHashed)
        assertFalse(capability.passphraseFingerprintCreated)
        assertFalse(capability.passphraseLogged)
        assertFalse(capability.unlockAvailable)
        assertFalse(capability.activeSessionAvailable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    @Test
    fun policyVocabularyIsModeledWithoutEnablingRuntimeHandling() {
        val summary = SkaldVaultV1PassphrasePolicyGate.currentPolicySummary()

        assertEquals(SkaldVaultV1PassphrasePolicyGate.POLICY_ID, summary.policyId)
        assertEquals(
            "unicode-nfc-utf8-no-controls-no-whitespace-v1",
            SkaldVaultV1VaultPassphraseNormalizationPolicy.UnicodeNfcRequired.policyId,
        )
        assertEquals(
            "unicode-nfc-utf8-no-controls-no-whitespace-v1",
            summary.encodingPolicy.policyId,
        )
        assertEquals(SkaldVaultV1VaultPassphraseRetryPolicy.RequiredFutureOnly, summary.retryPolicy)
        assertEquals(SkaldVaultV1VaultPassphraseThrottlePolicy.RequiredFutureOnly, summary.throttlePolicy)
        assertTrue(summary.stillDisabled)

        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseInputNotAccepted)
        assertContains(
            summary.policyCategories,
            SkaldVaultV1VaultPassphrasePolicyCategory.NormalizationPolicyIdentifierModeled,
        )
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.EncodingPolicyIdentifierModeled)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.MinimumPolicyFutureOnly)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.MaximumPolicyFutureOnly)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.ControlCharacterRejectionRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.WhitespaceHandlingRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.UnicodeNfcNormalizationRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.Utf8EncodingRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.RetryPolicyRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.ThrottlingPolicyRequired)
        assertContains(
            summary.policyCategories,
            SkaldVaultV1VaultPassphrasePolicyCategory.LockoutPolicyRequiredOrRejectedWithRationale,
        )
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.MemoryLifetimePolicyRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.ClearWipeStrategyRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.RedactionPolicyRequired)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.UiEntryPolicyAbsent)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseStorageForbidden)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.HashingFingerprintingForbidden)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.LoggingForbidden)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.BuildHistoryInclusionForbidden)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.CrashReportInclusionForbidden)
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.AnalyticsInclusionForbidden)
        assertContains(
            summary.policyCategories,
            SkaldVaultV1VaultPassphrasePolicyCategory.BiometricConvenienceUnlockFutureOnly,
        )
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.AndroidKeystoreWrappingFutureOnly)
        assertContains(
            summary.policyCategories,
            SkaldVaultV1VaultPassphrasePolicyCategory.OsKeyringPasswordManagerPassphraseStorageRejected,
        )
        assertContains(summary.policyCategories, SkaldVaultV1VaultPassphrasePolicyCategory.UnlockUnavailable)
    }

    @Test
    fun evidenceInteractionCannotEnablePassphraseUnlock() {
        val redactionEvidence = SkaldVaultV1RedactionLeakagePolicy.evaluate(
            SkaldVaultV1VaultRedactionRequest.classify(SkaldVaultV1VaultRedactionValueKind.Passphrase),
        ).classifiedEvidence()
        val persistenceEvidence = SkaldVaultV1PersistenceReadinessGate.evaluate(
            SkaldVaultV1VaultPersistenceReadinessRequest.noEvidence(),
        ).blockedEvidence()
        val lockEvidence = SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
            SkaldVaultV1VaultLockSessionRequest.noEvidence(),
        ).blockedEvidence()
        val result = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.fromEvidence(
                redactionEvidence = redactionEvidence,
                lockSessionEvidence = lockEvidence,
                persistenceReadinessEvidence = persistenceEvidence,
            ),
        )
        val evidence = result.blockedEvidence()

        assertEquals(SkaldVaultV1VaultRedactionDecision.Forbidden, redactionEvidence.decision)
        assertFalse(lockEvidence.capability.unlockAvailable)
        assertFalse(persistenceEvidence.capability.readyForPersistence)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.RedactionLeakage)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.LockSessionLifecycle)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.PersistenceReadiness)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.ProviderSelection)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.ProviderAcceptance)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.DisabledProviderFacade)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.DependencyProbe)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.SecureStorage)
        assertContains(evidence.evidenceSources, SkaldVaultV1VaultPassphraseEvidenceSource.SecureMetadata)
        assertTrue(evidence.redactionLeakageEvidenceConsumed)
        assertTrue(evidence.lockSessionEvidenceConsumed)
        assertTrue(evidence.persistenceReadinessEvidenceConsumed)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerAcceptanceEvidenceConsumed)
        assertTrue(evidence.disabledProviderFacadeEvidenceConsumed)
        assertTrue(evidence.dependencyProbeEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.secureMetadataEvidenceConsumed)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.ProviderSelectionDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.SecureSecretStorageUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.SecureMetadataStorageUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.KdfCalibrationNotFinal)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.ProviderKatsNotSelectable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.WarningOnlyEvidenceRejected)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.UserConsentOverrideRejected)
        assertContains(evidence.blockers, SkaldVaultV1VaultPassphraseBlocker.MainnetUnavailable)
        assertFalse(evidence.capability.unlockAvailable)
        assertFalse(evidence.capability.mainnetAvailable)

        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        assertFalse(providerSelection.decision.productionProviderSelectable)
    }

    @Test
    fun policyIdentifierRequestsDoNotExecuteNormalizationOrEncoding() {
        val normalization = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.normalizationPolicyIdentifier(),
        ).blockedEvidence()
        val encoding = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.encodingPolicyIdentifier(),
        ).blockedEvidence()

        assertEquals(SkaldVaultV1VaultPassphrasePolicyDecision.PolicyIdentifierModeled, normalization.decision)
        assertEquals(SkaldVaultV1VaultPassphrasePolicyStatus.PolicyIdentifierModeled, normalization.status)
        assertEquals(SkaldVaultV1VaultPassphrasePolicyDecision.PolicyIdentifierModeled, encoding.decision)
        assertEquals(SkaldVaultV1VaultPassphrasePolicyStatus.PolicyIdentifierModeled, encoding.status)
        assertFalse(normalization.policySummary.normalizationPolicy.executionAvailable)
        assertFalse(encoding.policySummary.encodingPolicy.executionAvailable)
        assertFalse(normalization.capability.passphraseNormalized)
        assertFalse(encoding.capability.passphraseEncoded)
    }

    @Test
    fun rejectionInputsAreRejectedWithoutRetainingRawValues() {
        assertRejected(null, SkaldVaultV1VaultPassphraseFailureReason.EmptyEvidenceRejected)
        assertRejected("", SkaldVaultV1VaultPassphraseFailureReason.EmptyEvidenceRejected)
        assertRejected("pass" + "phrase-fixture", SkaldVaultV1VaultPassphraseFailureReason.RawPassphraseInputRejected)
        assertRejected("pass" + "word-fixture", SkaldVaultV1VaultPassphraseFailureReason.RawPassphraseInputRejected)
        assertRejected("pin-fixture", SkaldVaultV1VaultPassphraseFailureReason.PinMaterialRejected)
        assertRejected("mne" + "monic-fixture", SkaldVaultV1VaultPassphraseFailureReason.MnemonicMaterialRejected)
        assertRejected("seed-fixture", SkaldVaultV1VaultPassphraseFailureReason.SeedMaterialRejected)
        assertRejected("bytearray-fixture", SkaldVaultV1VaultPassphraseFailureReason.ByteArrayLikeInputRejected)
        assertRejected("hash-fixture", SkaldVaultV1VaultPassphraseFailureReason.HashFingerprintEvidenceRejected)
        assertRejected("fingerprint-fixture", SkaldVaultV1VaultPassphraseFailureReason.HashFingerprintEvidenceRejected)
        assertRejected("biometric-fixture", SkaldVaultV1VaultPassphraseFailureReason.BiometricEvidenceRejected)
        assertRejected("raw-key-fixture", SkaldVaultV1VaultPassphraseFailureReason.KeyMaterialRejected)
        assertRejected("/vault-root-fixture", SkaldVaultV1VaultPassphraseFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root-fixture", SkaldVaultV1VaultPassphraseFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://vault.invalid", SkaldVaultV1VaultPassphraseFailureReason.LinkLikeInputRejected)
        assertRejected("File(vault-root-fixture)", SkaldVaultV1VaultPassphraseFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("path(vault-root-fixture)", SkaldVaultV1VaultPassphraseFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected)
        assertRejected("bc" + "1" + "q".repeat(24), SkaldVaultV1VaultPassphraseFailureReason.BitcoinAddressLikeEvidenceRejected)
        assertRejected("ns" + "ec1" + "q".repeat(32), SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rv" + "q".repeat(32), SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rv" + "q".repeat(32), SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected)
        assertRejected("K" + "1".repeat(50), SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultPassphraseFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultPassphraseFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun resultRenderingDoesNotExposeSensitiveLookingInputOrDerivedDiagnostics() {
        val rawCandidate = "pass" + "phrase-fixture"
        val request = SkaldVaultV1VaultPassphrasePolicyRequest.rawPassphraseCandidate(rawCandidate)
        val result = SkaldVaultV1PassphrasePolicyGate.evaluate(request)
        val evidence = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.summary(),
        ).blockedEvidence()

        assertFalse(request.toString().contains(rawCandidate))
        assertFalse(result.toString().contains(rawCandidate))
        assertFalse(evidence.toString().contains(rawCandidate))
        assertFalse(evidence.redactedPolicyMarker.toString().contains(rawCandidate))
        assertFalse(evidence.toString().contains("length="))
        assertFalse(evidence.toString().contains("prefix"))
        assertFalse(evidence.toString().contains("suffix"))
        assertFalse(evidence.toString().contains("hash="))
        assertFalse(evidence.toString().contains("fingerprint="))
        assertFalse(evidence.redactionEvidence.passphraseLengthRendered)
        assertFalse(evidence.redactionEvidence.passphrasePrefixSuffixRendered)
        assertFalse(evidence.redactionEvidence.passphraseHashRendered)
        assertFalse(evidence.redactionEvidence.passphraseFingerprintRendered)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        val capability = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.summary(),
        ).blockedEvidence().capability

        assertFalse(capability.passphraseInputAccepted)
        assertFalse(capability.passphraseStored)
        assertFalse(capability.passphraseNormalized)
        assertFalse(capability.passphraseEncoded)
        assertFalse(capability.passphraseHashed)
        assertFalse(capability.passphraseFingerprintCreated)
        assertFalse(capability.passphraseLogged)
        assertFalse(capability.passphraseIncludedInBuildHistory)
        assertFalse(capability.passphraseIncludedInCrashReport)
        assertFalse(capability.passphraseIncludedInAnalytics)
        assertFalse(capability.retryPolicyImplemented)
        assertFalse(capability.throttlePolicyImplemented)
        assertFalse(capability.lockoutPolicyImplemented)
        assertFalse(capability.clearStrategyImplemented)
        assertFalse(capability.biometricUnlockAvailable)
        assertFalse(capability.androidKeystoreWrappingAvailable)
        assertFalse(capability.osKeyringPassphraseStorageAvailable)
        assertFalse(capability.passwordManagerPassphraseStorageAvailable)
        assertFalse(capability.unlockAvailable)
        assertFalse(capability.activeSessionAvailable)
        assertFalse(capability.decryptedKeyMaterialPresent)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelected)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    @Test
    fun sourceGuardVocabularyRemainsFailClosed() {
        val sourceGuard = SkaldVaultV1PassphrasePolicyGate.evaluate(
            SkaldVaultV1VaultPassphrasePolicyRequest.fromEvidence(
                redactionEvidence = null,
                lockSessionEvidence = null,
                persistenceReadinessEvidence = null,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                providerFacadeMetadata = null,
                dependencyProbeResult = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
            ),
        ).blockedEvidence()

        assertFalse(sourceGuard.capability.passphraseInputAccepted)
        assertFalse(sourceGuard.capability.passphraseHashed)
        assertFalse(sourceGuard.capability.passphraseFingerprintCreated)
        assertFalse(sourceGuard.capability.providerSelectable)
        assertFalse(sourceGuard.capability.vaultPersistenceAvailable)
    }

    private fun assertRejected(
        candidate: String?,
        reason: SkaldVaultV1VaultPassphraseFailureReason,
    ) {
        val request = SkaldVaultV1VaultPassphrasePolicyRequest.rawPassphraseCandidate(candidate)
        val result = SkaldVaultV1PassphrasePolicyGate.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultPassphrasePolicyResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.passphraseInputAccepted)
        assertFalse(result.unlockAvailable)
        if (!candidate.isNullOrEmpty()) {
            assertFalse(request.toString().contains(candidate))
            assertFalse(result.toString().contains(candidate))
        }
    }

    private fun SkaldVaultV1VaultPassphrasePolicyResult<SkaldVaultV1VaultPassphrasePolicyEvidence>.blockedEvidence():
        SkaldVaultV1VaultPassphrasePolicyEvidence =
        assertIs<SkaldVaultV1VaultPassphrasePolicyResult.Blocked<SkaldVaultV1VaultPassphrasePolicyEvidence>>(this).value

    private fun SkaldVaultV1VaultRedactionResult<SkaldVaultV1VaultRedactionEvidence>.classifiedEvidence():
        SkaldVaultV1VaultRedactionEvidence =
        assertIs<SkaldVaultV1VaultRedactionResult.Classified<SkaldVaultV1VaultRedactionEvidence>>(this).value

    private fun SkaldVaultV1VaultPersistenceReadinessResult<SkaldVaultV1VaultPersistenceReadinessEvidence>.blockedEvidence():
        SkaldVaultV1VaultPersistenceReadinessEvidence =
        assertIs<
            SkaldVaultV1VaultPersistenceReadinessResult.Blocked<SkaldVaultV1VaultPersistenceReadinessEvidence>,
        >(this).value

    private fun SkaldVaultV1VaultLockSessionResult<SkaldVaultV1VaultLockSessionEvidence>.blockedEvidence():
        SkaldVaultV1VaultLockSessionEvidence =
        assertIs<SkaldVaultV1VaultLockSessionResult.Blocked<SkaldVaultV1VaultLockSessionEvidence>>(this).value
}
