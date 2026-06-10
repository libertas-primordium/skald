package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderLinuxCustomRootValidationRule
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootCandidate
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootCandidateSource
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootSettingsPolicy
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultLinuxCustomRootValidationPolicyTest {
    @Test
    fun linuxCustomRootValidationPolicyIdAndDefaultRootPolicyAreStable() {
        val platformPolicy = SkaldVaultV1PlatformRootSettingsPolicy.currentContract()

        assertEquals(
            "skald-vault-v1-linux-custom-root-validation-policy-v1",
            SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_ID,
            platformPolicy.linuxCustomRootValidationPolicyId,
        )
        assertEquals("~/.local/share/", platformPolicy.linuxDefaultUserDataDirectoryConvention)
        assertTrue(platformPolicy.linuxXdgStyleUserDataModelDocumented)
        assertTrue(platformPolicy.linuxCustomRootValidationImplemented)
        assertFalse(platformPolicy.linuxRootResolutionImplemented)
        assertFalse(platformPolicy.settingsUiImplemented)
        assertFalse(platformPolicy.settingsPersistenceImplemented)
        assertFalse(platformPolicy.actualPathConstructionImplemented)
        assertFalse(platformPolicy.storageImplementationImplemented)
    }

    @Test
    fun acceptedCandidateIsStaticPolicyEvidenceOnly() {
        val candidate = assertAccepted("/home/skaldvault/vault_roots")

        assertEquals("/home/skaldvault/vault_roots", candidate.rawCandidate)
        assertEquals(SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_ID, candidate.validationPolicyId)
        assertTrue(candidate.acceptedAsStaticPolicyCandidate)
        assertFalse(candidate.resolvedOnThisDevice)
        assertFalse(candidate.pathExistsProven)
        assertFalse(candidate.containmentReviewed)
        assertFalse(candidate.symlinkReviewed)
        assertFalse(candidate.permissionReviewed)
        assertFalse(candidate.durabilityReviewed)
        assertFalse(candidate.settingsUiImplemented)
        assertFalse(candidate.settingsPersistenceImplemented)
        assertFalse(candidate.platformStorageImplemented)
        assertFalse(candidate.persistenceEnabled)
        assertEquals(
            setOf(
                SkaldVaultV1LinuxCustomRootValidationFailureReason.SettingsUiMissing,
                SkaldVaultV1LinuxCustomRootValidationFailureReason.SettingsPersistenceMissing,
                SkaldVaultV1LinuxCustomRootValidationFailureReason.RootResolutionMissing,
                SkaldVaultV1LinuxCustomRootValidationFailureReason.ContainmentReviewMissing,
                SkaldVaultV1LinuxCustomRootValidationFailureReason.SymlinkReviewMissing,
                SkaldVaultV1LinuxCustomRootValidationFailureReason.PermissionReviewMissing,
                SkaldVaultV1LinuxCustomRootValidationFailureReason.DurabilityReviewMissing,
            ),
            candidate.remainingBlockingFailureReasons,
        )
    }

    @Test
    fun unsafeCandidateRootsFailClosedWithTypedReasons() {
        val cases = mapOf(
            "" to SkaldVaultV1LinuxCustomRootValidationFailureReason.EmptyCustomRoot,
            "relative/vaults" to SkaldVaultV1LinuxCustomRootValidationFailureReason.RelativeCustomRootRejected,
            "~/vaults" to SkaldVaultV1LinuxCustomRootValidationFailureReason.TildeCustomRootRejected,
            "/" to SkaldVaultV1LinuxCustomRootValidationFailureReason.RootFilesystemRejected,
            "/tmp" to SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected,
            "/tmp/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected,
            "/var/tmp" to SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected,
            "/dev/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.SystemRootRejected,
            "/proc/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.SystemRootRejected,
            "/sys/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.SystemRootRejected,
            "/run/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.RuntimeRootRejected,
            "/mnt/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.RemovableMediaRootRejected,
            "/media/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.RemovableMediaRootRejected,
            "/home/../skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.PathTraversalRejected,
            "/home/%2e%2e/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.PathTraversalRejected,
            "/home//skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.EmptyPathSegmentRejected,
            "/home/./skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.PathTraversalRejected,
            "/home/   /skaldvault" to
                SkaldVaultV1LinuxCustomRootValidationFailureReason.WhitespaceOnlySegmentRejected,
            "/home/skald vault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.ContainsWhitespaceRejected,
            "/home/skald\u0007vault" to
                SkaldVaultV1LinuxCustomRootValidationFailureReason.ControlCharacterRejected,
            "/home/skald\u200bvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.InvisibleFormatRejected,
            "/home/skaldväult" to SkaldVaultV1LinuxCustomRootValidationFailureReason.UnsupportedCharacterRejected,
            "/home/skald\$vault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.UnsupportedCharacterRejected,
            "file:/home/skaldvault" to SkaldVaultV1LinuxCustomRootValidationFailureReason.UriLikePrefixRejected,
            "C:\\vaults" to SkaldVaultV1LinuxCustomRootValidationFailureReason.WindowsDrivePrefixRejected,
            "/home/passphrase-store" to SkaldVaultV1LinuxCustomRootValidationFailureReason.SecretMaterialRejected,
            "/home/0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef" to
                SkaldVaultV1LinuxCustomRootValidationFailureReason.SecretMaterialRejected,
            "/" + "a".repeat(SkaldVaultV1LinuxCustomRootValidationPolicy.MAX_CANDIDATE_BYTES) to
                SkaldVaultV1LinuxCustomRootValidationFailureReason.TooLong,
        )

        cases.forEach { (candidate, expectedReason) ->
            assertRejected(candidate, expectedReason)
        }
    }

    @Test
    fun labelsAndSecretMaterialSourcesAreRejectedBeforePathValidation() {
        assertRejected(
            candidate = "/home/skaldvault/vault_roots",
            expectedReason = SkaldVaultV1LinuxCustomRootValidationFailureReason.UserLabelRejected,
            source = SkaldVaultV1LinuxCustomRootCandidateSource.UserDisplayLabel,
        )
        assertRejected(
            candidate = "/home/skaldvault/vault_roots",
            expectedReason = SkaldVaultV1LinuxCustomRootValidationFailureReason.SecretMaterialRejected,
            source = SkaldVaultV1LinuxCustomRootCandidateSource.SecretMaterialCandidate,
        )
    }

    @Test
    fun validationEvidenceIsRepresentedButDoesNotEnablePersistenceOrProviderSelection() {
        val acceptance = commonProductionProviderAcceptanceContract()
        val storage = acceptance.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = acceptance.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_ID,
            storage.linuxCustomRootValidationPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.linuxCustomRootValidationPolicyStatus,
        )
        assertContains(
            storage.linuxCustomRootValidationRules,
            ProductionProviderLinuxCustomRootValidationRule.AcceptedCandidateDoesNotResolvePath,
        )
        assertTrue(storage.linuxCustomRootValidationImplemented)
        assertFalse(storage.linuxCustomRootUsable)
        assertFalse(storage.settingsUiImplemented)
        assertFalse(storage.settingsPersistenceImplemented)
        assertFalse(storage.platformRootResolutionImplemented)
        assertFalse(storage.actualPathConstructionImplemented)
        assertFalse(storage.platformStorageImplementationAdded)
        assertFalse(storage.vaultPersistenceImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.LinuxCustomRootValidationPolicyImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.LinuxCustomRootValidationPolicyBuildingBlock,
        )
        assertFalse(readiness.blockers.contains(EncryptedVaultBlockingIssue.LinuxCustomRootValidationMissing))
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.LinuxCustomRootValidationPolicyImplementedTested,
        )
        assertFalse(dependency.blockers.contains(VaultCryptoDependencyBlocker.LinuxCustomRootValidationMissing))
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertFalse(dependency.readyForVaultImplementation)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun assertAccepted(candidate: String): SkaldVaultV1LinuxCustomRootCandidate {
        val result = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(candidate)
        assertTrue(result is SkaldVaultV1LinuxCustomRootValidationResult.Accepted)
        return result.value
    }

    private fun assertRejected(
        candidate: String,
        expectedReason: SkaldVaultV1LinuxCustomRootValidationFailureReason,
        source: SkaldVaultV1LinuxCustomRootCandidateSource =
            SkaldVaultV1LinuxCustomRootCandidateSource.UserSuppliedRootString,
    ) {
        val result = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(
            candidate = candidate,
            source = source,
        )
        assertTrue(
            result is SkaldVaultV1LinuxCustomRootValidationResult.Rejected,
            "Candidate should be rejected: $candidate",
        )
        assertEquals(expectedReason, result.reason)
        assertEquals(expectedReason.label, result.safeMessage)
    }
}
