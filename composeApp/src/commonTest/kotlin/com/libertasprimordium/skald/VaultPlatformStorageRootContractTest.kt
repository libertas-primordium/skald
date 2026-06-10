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
import com.libertasprimordium.skald.security.ProductionProviderDurabilityCapabilityRule
import com.libertasprimordium.skald.security.ProductionProviderPlatformStorageRootRule
import com.libertasprimordium.skald.security.ProductionProviderSafePathConstructionRule
import com.libertasprimordium.skald.security.ProductionProviderStorageFailureCategory
import com.libertasprimordium.skald.security.ProductionProviderStoragePermissionOwnershipRule
import com.libertasprimordium.skald.security.ProductionProviderSymlinkTraversalRule
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

class VaultPlatformStorageRootContractTest {
    private val contract = commonProductionProviderAcceptanceContract()

    @Test
    fun platformRootPathSymlinkPermissionAndDurabilityPolicyIdsAreStableAndModelOnly() {
        val policy = contract.containerManifestStorageContract

        assertEquals(
            "skald-vault-v1-platform-storage-root-policy-v1",
            policy.platformStorageRootPolicyId,
        )
        assertEquals(
            "skald-vault-v1-safe-path-construction-policy-v1",
            policy.safePathConstructionPolicyId,
        )
        assertEquals(
            "skald-vault-v1-symlink-traversal-policy-v1",
            policy.symlinkTraversalPolicyId,
        )
        assertEquals(
            "skald-vault-v1-storage-permission-ownership-policy-v1",
            policy.storagePermissionOwnershipPolicyId,
        )
        assertEquals(
            "skald-vault-v1-durability-capability-policy-v1",
            policy.durabilityCapabilityPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.platformStorageRootContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.safePathConstructionContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.symlinkTraversalContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.storagePermissionOwnershipContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.durabilityCapabilityContractStatus,
        )

        assertEquals(ProductionProviderPlatformStorageRootRule.entries.toSet(), policy.platformStorageRootRules)
        assertContains(
            policy.platformStorageRootRules,
            ProductionProviderPlatformStorageRootRule.AndroidAppPrivateInternalStorageRequired,
        )
        assertContains(
            policy.platformStorageRootRules,
            ProductionProviderPlatformStorageRootRule.AndroidExternalSharedStorageRejected,
        )
        assertContains(
            policy.platformStorageRootRules,
            ProductionProviderPlatformStorageRootRule.DesktopAppControlledUserDataLocationRequired,
        )
        assertContains(
            policy.platformStorageRootRules,
            ProductionProviderPlatformStorageRootRule.DesktopOsKeyringsNotPrimaryVaultStorage,
        )
        assertContains(
            policy.platformStorageRootRules,
            ProductionProviderPlatformStorageRootRule.SharedNoWalletLabelsOrNoteText,
        )
        assertContains(
            policy.platformStorageRootRules,
            ProductionProviderPlatformStorageRootRule.NoPlatformRootResolutionInThisBranch,
        )

        assertEquals(ProductionProviderSafePathConstructionRule.entries.toSet(), policy.safePathConstructionRules)
        assertContains(
            policy.safePathConstructionRules,
            ProductionProviderSafePathConstructionRule.ReviewedPlatformRootOnly,
        )
        assertContains(
            policy.safePathConstructionRules,
            ProductionProviderSafePathConstructionRule.JoinOnlyValidatedRelativeSegments,
        )
        assertContains(
            policy.safePathConstructionRules,
            ProductionProviderSafePathConstructionRule.FutureContainmentCheckUnderReviewedRootRequired,
        )
        assertContains(
            policy.safePathConstructionRules,
            ProductionProviderSafePathConstructionRule.NoActualPathConstructionInThisBranch,
        )

        assertEquals(ProductionProviderSymlinkTraversalRule.entries.toSet(), policy.symlinkTraversalRules)
        assertContains(
            policy.symlinkTraversalRules,
            ProductionProviderSymlinkTraversalRule.FailClosedIfSymlinkOrContainmentUnknown,
        )
        assertContains(
            policy.symlinkTraversalRules,
            ProductionProviderSymlinkTraversalRule.DoNotFollowAttackerControlledSymlinks,
        )
        assertContains(
            policy.symlinkTraversalRules,
            ProductionProviderSymlinkTraversalRule.NoSymlinkCheckImplementationInThisBranch,
        )

        assertEquals(
            ProductionProviderStoragePermissionOwnershipRule.entries.toSet(),
            policy.storagePermissionOwnershipRules,
        )
        assertContains(
            policy.storagePermissionOwnershipRules,
            ProductionProviderStoragePermissionOwnershipRule.NoWorldReadableOrSharedDirectories,
        )
        assertContains(
            policy.storagePermissionOwnershipRules,
            ProductionProviderStoragePermissionOwnershipRule.NoPermissionCheckImplementationInThisBranch,
        )

        assertEquals(ProductionProviderDurabilityCapabilityRule.entries.toSet(), policy.durabilityCapabilityRules)
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.AtomicReplaceCapabilityDocumented,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.NoDurabilityProbeImplementationInThisBranch,
        )
    }

    @Test
    fun platformRootFailureCategoriesAreModeledWithoutStorageImplementation() {
        val policy = contract.containerManifestStorageContract

        listOf(
            ProductionProviderStorageFailureCategory.PlatformRootUnavailable,
            ProductionProviderStorageFailureCategory.PlatformRootUnsafe,
            ProductionProviderStorageFailureCategory.PlatformRootUnreviewed,
            ProductionProviderStorageFailureCategory.PathConstructionUnsupported,
            ProductionProviderStorageFailureCategory.PathContainmentFailed,
            ProductionProviderStorageFailureCategory.PathSegmentRejected,
            ProductionProviderStorageFailureCategory.SymlinkStateUnknown,
            ProductionProviderStorageFailureCategory.SymlinkRejected,
            ProductionProviderStorageFailureCategory.PermissionStateUnknown,
            ProductionProviderStorageFailureCategory.UnsafePermissions,
            ProductionProviderStorageFailureCategory.DurabilityCapabilityUnknown,
            ProductionProviderStorageFailureCategory.DurabilityCapabilityInsufficient,
            ProductionProviderStorageFailureCategory.ExternalStorageRejected,
            ProductionProviderStorageFailureCategory.UserPathRejected,
        ).forEach { category ->
            assertContains(policy.storageFailureCategories, category)
        }

        assertFalse(policy.platformRootResolutionImplemented)
        assertFalse(policy.platformRootSelectionImplemented)
        assertFalse(policy.actualPathConstructionImplemented)
        assertFalse(policy.pathJoinImplementationAdded)
        assertFalse(policy.pathContainmentCheckImplementationAdded)
        assertFalse(policy.directoryCreationImplementationAdded)
        assertFalse(policy.symlinkCheckImplementationAdded)
        assertFalse(policy.permissionCheckImplementationAdded)
        assertFalse(policy.durabilityProbeImplementationAdded)
        assertFalse(policy.platformStorageImplementationAdded)
        assertFalse(policy.filesystemVaultStorageImplemented)
        assertFalse(policy.databaseVaultStorageImplemented)
        assertFalse(policy.manifestReadWriteImplemented)
        assertFalse(policy.storageIndexReadWriteImplemented)
        assertFalse(policy.vaultPersistenceImplemented)
    }

    @Test
    fun platformRootEvidenceStatesBlockProviderSelectabilityAndPersistence() {
        val gates = setOf(
            ProductionProviderAcceptanceGate.PlatformStorageRootContractApproved,
            ProductionProviderAcceptanceGate.SafePathConstructionContractApproved,
            ProductionProviderAcceptanceGate.SymlinkTraversalContractApproved,
            ProductionProviderAcceptanceGate.StoragePermissionOwnershipContractApproved,
            ProductionProviderAcceptanceGate.DurabilityCapabilityContractApproved,
        )
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

        gates.forEach { gate ->
            states.forEach { (state, blocker) ->
                val assessment = contract.assess(
                    ProductionProviderAcceptanceEvidence(
                        gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                            ProductionProviderAcceptanceEvidenceState.Satisfied
                        } + mapOf(gate to state),
                    ),
                )

                assertFalse(assessment.allRequiredGatesSatisfied, "$gate with $state must not satisfy gates")
                assertContains(assessment.blockers, blocker)
                assertFalse(assessment.productionProviderSelectable)
                assertFalse(assessment.productionPersistenceAllowed)
            }
        }

        val currentEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        gates.forEach { gate ->
            assertEquals(ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly, currentEvidence.stateFor(gate))
        }
    }

    @Test
    fun readinessDependencyAndSelectionRemainDisabledWithModelOnlyRootContracts() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PlatformStorageRootContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.SafePathConstructionContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.SymlinkTraversalContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StoragePermissionOwnershipContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.DurabilityCapabilityContractModeled],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformStorageRootContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.SafePathConstructionContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.SymlinkTraversalContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StoragePermissionOwnershipContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.DurabilityCapabilityContractModel)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.PlatformStorageRootImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.SafePathConstructionImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.SymlinkTraversalImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.StoragePermissionOwnershipImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.DurabilityCapabilityProbeImplementationMissing)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.PlatformStorageRootContractModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.SafePathConstructionContractModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.SymlinkTraversalContractModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.StoragePermissionOwnershipContractModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.DurabilityCapabilityContractModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.PlatformStorageRootImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.SafePathConstructionImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.SymlinkTraversalImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.StoragePermissionOwnershipImplementationMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.DurabilityCapabilityProbeImplementationMissing)
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertFalse(dependency.readyForVaultImplementation)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertFalse(selection.productionProviderSelectable)
    }
}
