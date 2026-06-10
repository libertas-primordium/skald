package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderPlatformRootSettingsRule
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentPlanner
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentResult
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentRootToken
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootSettingsDecision
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootSettingsFailureReason
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

class VaultPlatformRootSettingsContractTest {
    private val acceptanceContract = commonProductionProviderAcceptanceContract()
    private val platformPolicy = SkaldVaultV1PlatformRootSettingsPolicy.currentContract()

    @Test
    fun platformRootSettingsPolicyIdsAreStable() {
        assertEquals("skald-vault-v1-platform-root-settings-policy-v1", platformPolicy.policyId)
        assertEquals(
            "skald-vault-v1-android-app-private-internal-root-policy-v1",
            platformPolicy.androidRootPolicyId,
        )
        assertEquals(
            "skald-vault-v1-linux-root-settings-policy-v1",
            platformPolicy.linuxRootSettingsPolicyId,
        )
        assertEquals(
            "skald-vault-v1-os-keyring-passphrase-policy-v1",
            platformPolicy.osKeyringPassphrasePolicyId,
        )
        assertEquals(
            "skald-vault-v1-password-manager-passphrase-policy-v1",
            platformPolicy.passwordManagerPassphrasePolicyId,
        )
        assertEquals(
            "skald-vault-v1-passphrase-first-vault-authority-policy-v1",
            platformPolicy.passphraseFirstPolicyId,
        )
        assertEquals("~/.local/share/", platformPolicy.linuxDefaultUserDataDirectoryConvention)
    }

    @Test
    fun androidPolicyRequiresAppPrivateInternalRootAndRejectsOtherRoots() {
        assertEquals(
            SkaldVaultV1PathContainmentRootToken.AndroidAppPrivateInternalRoot,
            platformPolicy.androidReviewedRootToken,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.AndroidAppPrivateInternalRootRequired,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.AndroidExternalStorageRejected,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.AndroidUserSelectedRootRejected,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.AndroidRootResolutionUnimplemented,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.AndroidBackupBehaviorUnreviewed,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.AndroidUninstallBehaviorUnreviewed,
        )
        assertFalse(platformPolicy.androidRootResolutionImplemented)

        assertRootRejected(
            SkaldVaultV1PathContainmentRootToken.AndroidExternalSharedRoot,
            SkaldVaultV1PathContainmentRejectionReason.ExternalStorageRootRejected,
        )
        assertRootRejected(
            SkaldVaultV1PathContainmentRootToken.UserSelectedPathRoot,
            SkaldVaultV1PathContainmentRejectionReason.UserPathRootRejected,
        )
    }

    @Test
    fun linuxDefaultAndFutureCustomRootSettingsRemainUnimplemented() {
        assertEquals(
            SkaldVaultV1PathContainmentRootToken.DesktopAppControlledUserDataRoot,
            platformPolicy.linuxReviewedRootToken,
        )
        assertTrue(platformPolicy.linuxXdgStyleUserDataModelDocumented)
        assertTrue(platformPolicy.linuxCustomRootSettingsPlanned)
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.LinuxDefaultUserDataRootPolicy,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.LinuxLocalShareFallbackPolicy,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.LinuxXdgStyleUserDataLocationFutureOnly,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.LinuxCustomRootSettingsPlanned,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.LinuxCustomRootUnimplemented,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.LinuxCustomRootUnreviewed,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.UserConfiguredRootRequiresValidation,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.LinuxCustomRootRequiresContainmentReview,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.LinuxCustomRootRequiresSymlinkReview,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.LinuxCustomRootRequiresPermissionReview,
        )
        assertContains(
            platformPolicy.blockingFailureReasons,
            SkaldVaultV1PlatformRootSettingsFailureReason.LinuxCustomRootRequiresDurabilityReview,
        )
        assertFalse(platformPolicy.linuxRootResolutionImplemented)
        assertFalse(platformPolicy.settingsUiImplemented)
        assertFalse(platformPolicy.settingsPersistenceImplemented)
        assertFalse(platformPolicy.actualPathConstructionImplemented)
        assertFalse(platformPolicy.storageImplementationImplemented)
    }

    @Test
    fun keyringPasswordManagerAndPassphraseFirstPolicyIsExplicit() {
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.OsKeyringPrimaryStorageRejected,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.OsKeyringPassphraseStorageRejected,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.PasswordManagerIntegrationRejected,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.PassphraseFirstRequired,
        )
        assertContains(
            platformPolicy.decisions,
            SkaldVaultV1PlatformRootSettingsDecision.ExternalPasswordManagersOutsideSkald,
        )
        assertFalse(platformPolicy.osKeyringPrimaryStorageAllowed)
        assertFalse(platformPolicy.osKeyringPassphraseStorageAllowed)
        assertFalse(platformPolicy.passwordManagerIntegrationAllowed)
        assertTrue(platformPolicy.passphraseFirstDefault)
        assertFalse(platformPolicy.androidWrappingImplemented)
    }

    @Test
    fun acceptanceEvidenceRepresentsSettingsPolicyButStillBlocksPersistenceAndSelection() {
        val storagePolicy = acceptanceContract.containerManifestStorageContract
        val currentEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = acceptanceContract.assess(currentEvidence)

        assertEquals(SkaldVaultV1PlatformRootSettingsPolicy.POLICY_ID, storagePolicy.platformRootSettingsPolicyId)
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.ANDROID_ROOT_POLICY_ID,
            storagePolicy.androidRootPolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.LINUX_ROOT_SETTINGS_POLICY_ID,
            storagePolicy.linuxRootSettingsPolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.OS_KEYRING_PASSPHRASE_POLICY_ID,
            storagePolicy.osKeyringPassphrasePolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.PASSWORD_MANAGER_PASSPHRASE_POLICY_ID,
            storagePolicy.passwordManagerPassphrasePolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.PASSPHRASE_FIRST_POLICY_ID,
            storagePolicy.passphraseFirstPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            storagePolicy.platformRootSettingsPolicyStatus,
        )
        assertEquals(ProductionProviderPlatformRootSettingsRule.entries.toSet(), storagePolicy.platformRootSettingsRules)
        assertTrue(storagePolicy.androidAppPrivateInternalRootPolicyModeled)
        assertTrue(storagePolicy.androidExternalStorageRejected)
        assertTrue(storagePolicy.androidUserSelectedRootRejected)
        assertFalse(storagePolicy.androidRootResolutionImplemented)
        assertFalse(storagePolicy.androidBackupBehaviorReviewed)
        assertFalse(storagePolicy.androidUninstallBehaviorReviewed)
        assertTrue(storagePolicy.linuxDefaultUserDataRootPolicyModeled)
        assertTrue(storagePolicy.linuxLocalShareFallbackModeled)
        assertTrue(storagePolicy.linuxCustomRootSettingsContractModeled)
        assertFalse(storagePolicy.linuxCustomRootUsable)
        assertFalse(storagePolicy.linuxCustomRootValidationImplemented)
        assertFalse(storagePolicy.settingsUiImplemented)
        assertFalse(storagePolicy.settingsPersistenceImplemented)
        assertTrue(storagePolicy.osKeyringPrimaryStorageRejected)
        assertTrue(storagePolicy.osKeyringPassphraseStorageRejected)
        assertTrue(storagePolicy.passwordManagerIntegrationRejected)
        assertTrue(storagePolicy.passphraseFirstDefaultModeled)
        assertFalse(storagePolicy.platformRootResolutionImplemented)
        assertFalse(storagePolicy.actualPathConstructionImplemented)
        assertFalse(storagePolicy.platformStorageImplementationAdded)
        assertFalse(storagePolicy.vaultPersistenceImplemented)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            currentEvidence.stateFor(ProductionProviderAcceptanceGate.PlatformRootSettingsPolicyApproved),
        )
        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun settingsPolicyGateBlocksForMissingUnknownFailedUnsupportedAndModelOnlyEvidence() {
        val states = listOf(
            ProductionProviderAcceptanceEvidenceState.Missing to
                ProductionProviderAcceptanceBlocker.MissingGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unknown to
                ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Failed to
                ProductionProviderAcceptanceBlocker.FailedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unsupported to
                ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly to
                ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )

        states.forEach { (state, blocker) ->
            val assessment = acceptanceContract.assess(
                ProductionProviderAcceptanceEvidence(
                    gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                        ProductionProviderAcceptanceEvidenceState.Satisfied
                    } + mapOf(ProductionProviderAcceptanceGate.PlatformRootSettingsPolicyApproved to state),
                ),
            )

            assertFalse(assessment.allRequiredGatesSatisfied)
            assertContains(assessment.blockers, blocker)
            assertFalse(assessment.productionProviderSelectable)
            assertFalse(assessment.productionPersistenceAllowed)
        }
    }

    @Test
    fun readinessDependencyAndProviderSelectionRemainDisabled() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PlatformRootSettingsPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.AndroidAppPrivateRootPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.LinuxRootSettingsPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.OsKeyringPassphraseStorageRejected],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PasswordManagerIntegrationRejected],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PassphraseFirstDefaultModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.DisabledByPolicy,
            readiness.requirementStatuses[EncryptedVaultRequirement.SettingsUiAbsent],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.DisabledByPolicy,
            readiness.requirementStatuses[EncryptedVaultRequirement.SettingsPersistenceAbsent],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformRootSettingsPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AndroidAppPrivateRootPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.LinuxRootSettingsPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.OsKeyringPassphraseRejectionModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PasswordManagerIntegrationRejectionModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PassphraseFirstDefaultModel)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.PlatformRootSettingsImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.SettingsUiMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.SettingsPersistenceMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.LinuxCustomRootValidationMissing)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.PlatformRootSettingsPolicyModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.AndroidAppPrivateRootPolicyModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.LinuxRootSettingsPolicyModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.OsKeyringPassphraseStorageRejected)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.PasswordManagerIntegrationRejected)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.PassphraseFirstVaultAuthorityModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.PlatformRootSettingsImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.SettingsUiImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.SettingsPersistenceImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.LinuxCustomRootValidationMissing)
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertFalse(dependency.readyForVaultImplementation)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun assertRootRejected(
        token: SkaldVaultV1PathContainmentRootToken,
        reason: SkaldVaultV1PathContainmentRejectionReason,
    ) {
        val result = SkaldVaultV1PathContainmentPlanner.validateRootToken(token)
        assertTrue(result is SkaldVaultV1PathContainmentResult.Rejected)
        assertEquals(reason, result.reason)
    }
}
