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
import com.libertasprimordium.skald.security.ProductionProviderDurabilityFailClosedCondition
import com.libertasprimordium.skald.security.ProductionProviderStorageFailureCategory
import com.libertasprimordium.skald.security.ProductionProviderWarningOnlyDurabilityRule
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

class VaultDurabilityFailClosedPolicyTest {
    private val contract = commonProductionProviderAcceptanceContract()

    @Test
    fun durabilityFailClosedPolicyIdsAreStableAndModelOnly() {
        val policy = contract.containerManifestStorageContract

        assertEquals(
            "skald-vault-v1-durability-fail-closed-policy-v1",
            policy.durabilityFailClosedPolicyId,
        )
        assertEquals(
            "skald-vault-v1-warning-only-durability-rejection-policy-v1",
            policy.warningOnlyDurabilityRejectionPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.durabilityFailClosedPolicyStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.warningOnlyDurabilityRejectionStatus,
        )
        assertEquals(
            ProductionProviderDurabilityFailClosedCondition.entries.toSet(),
            policy.durabilityFailClosedConditions,
        )
        assertEquals(
            ProductionProviderWarningOnlyDurabilityRule.entries.toSet(),
            policy.warningOnlyDurabilityRules,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.RequiredDurabilityFailuresBlockEncryptedVaultPersistence,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.WarningOnlyEncryptedVaultPersistenceRejected,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.EquivalentSafeStrategyRequiresHumanReview,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.UserConsentCannotOverrideDurabilityFailure,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.AndroidDurabilityRequiresReviewBeforePersistence,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.DesktopDurabilityRequiresReviewBeforePersistence,
        )

        assertFalse(policy.warningOnlyEncryptedVaultPersistenceAllowed)
        assertFalse(policy.userConsentDurabilityOverrideAllowed)
        assertFalse(policy.equivalentSafeDurabilityStrategyApproved)
        assertFalse(policy.androidDurabilityReviewed)
        assertFalse(policy.desktopDurabilityReviewed)
        assertFalse(policy.durabilityProbeImplementationAdded)
        assertFalse(policy.durabilitySyncImplementationAdded)
        assertFalse(policy.atomicReplaceImplementationAdded)
        assertFalse(policy.vaultPersistenceImplemented)
    }

    @Test
    fun requiredDurabilityFailuresAreModeledAsFailClosedCategories() {
        val policy = contract.containerManifestStorageContract
        val requiredFailureCategories = listOf(
            ProductionProviderStorageFailureCategory.DurabilityCapabilityUnknown,
            ProductionProviderStorageFailureCategory.DurabilityCapabilityInsufficient,
            ProductionProviderStorageFailureCategory.DurabilitySyncUnsupported,
            ProductionProviderStorageFailureCategory.DurabilitySyncFailed,
            ProductionProviderStorageFailureCategory.AtomicReplaceUnsupported,
            ProductionProviderStorageFailureCategory.AtomicReplaceFailed,
            ProductionProviderStorageFailureCategory.PlatformRootUnreviewed,
            ProductionProviderStorageFailureCategory.PlatformRootUnsafe,
            ProductionProviderStorageFailureCategory.PermissionStateUnknown,
            ProductionProviderStorageFailureCategory.UnsafePermissions,
            ProductionProviderStorageFailureCategory.UnknownStorageState,
            ProductionProviderStorageFailureCategory.WarningOnlyDurabilityRejected,
            ProductionProviderStorageFailureCategory.UserConsentDurabilityOverrideRejected,
            ProductionProviderStorageFailureCategory.EquivalentSafeStrategyUnreviewed,
        )

        requiredFailureCategories.forEach { category ->
            assertContains(policy.storageFailureCategories, category)
        }
        ProductionProviderDurabilityFailClosedCondition.entries.forEach { condition ->
            assertContains(policy.durabilityFailClosedConditions, condition)
        }
        ProductionProviderWarningOnlyDurabilityRule.entries.forEach { rule ->
            assertContains(policy.warningOnlyDurabilityRules, rule)
        }
    }

    @Test
    fun durabilityGatesBlockSelectabilityForMissingUnknownFailedUnsupportedAndModelOnlyEvidence() {
        val gates = setOf(
            ProductionProviderAcceptanceGate.DurabilityCapabilityContractApproved,
            ProductionProviderAcceptanceGate.DurabilityFailClosedPolicyApproved,
            ProductionProviderAcceptanceGate.WarningOnlyDurabilityPersistenceRejected,
            ProductionProviderAcceptanceGate.PlatformStorageRootContractApproved,
            ProductionProviderAcceptanceGate.StoragePermissionOwnershipContractApproved,
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
            ProductionProviderAcceptanceEvidenceState.ApprovedForFutureImplementation to
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
    }

    @Test
    fun currentDurabilityFailClosedEvidenceIsModelOnlyAndStillBlocksPersistence() {
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.DurabilityCapabilityContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.DurabilityFailClosedPolicyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.WarningOnlyDurabilityPersistenceRejected),
        )
        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun readinessDependencyAndSelectionExposePolicyButRemainDisabled() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.DurabilityFailClosedPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.WarningOnlyDurabilityPersistenceRejected],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.DurabilityFailClosedPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.WarningOnlyDurabilityRejectionModel)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.DurabilityFailClosedRuntimeEvidenceMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.WarningOnlyDurabilityPersistenceRejected)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.DurabilityFailClosedPolicyModeled)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.WarningOnlyDurabilityRejectionModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.DurabilityFailClosedRuntimeEvidenceMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.WarningOnlyDurabilityPersistenceRejected)
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertFalse(dependency.readyForVaultImplementation)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertFalse(selection.productionProviderSelectable)
    }
}
