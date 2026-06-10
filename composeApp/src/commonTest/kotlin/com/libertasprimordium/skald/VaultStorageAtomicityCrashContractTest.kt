package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderAtomicWritePhase
import com.libertasprimordium.skald.security.ProductionProviderAtomicWriteRequirement
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderCrashRecoveryFailClosedState
import com.libertasprimordium.skald.security.ProductionProviderStorageBoundaryAllowedBytes
import com.libertasprimordium.skald.security.ProductionProviderStorageBoundaryForbiddenMaterial
import com.libertasprimordium.skald.security.ProductionProviderStorageBoundaryRequirement
import com.libertasprimordium.skald.security.ProductionProviderStorageFailureCategory
import com.libertasprimordium.skald.security.ProductionProviderStorageNamespacePathRule
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultStorageAtomicityCrashContractTest {
    private val contract = commonProductionProviderAcceptanceContract()

    @Test
    fun storageAtomicityCrashContractsAreModelOnlyAndDoNotImplementStorage() {
        val policy = contract.containerManifestStorageContract

        assertEquals(
            "skald-vault-v1-platform-storage-boundary-policy-v1",
            policy.platformStorageBoundaryPolicyId,
        )
        assertEquals("skald-vault-v1-atomic-write-strategy-policy-v1", policy.atomicWritePolicyId)
        assertEquals("skald-vault-v1-crash-recovery-policy-v1", policy.crashRecoveryPolicyId)
        assertEquals(
            "skald-vault-v1-storage-interruption-test-policy-v1",
            policy.interruptionTestPolicyId,
        )
        assertEquals(
            "skald-vault-v1-storage-failure-model-policy-v1",
            policy.storageFailureModelPolicyId,
        )
        assertEquals(
            "skald-vault-v1-storage-namespace-path-hygiene-policy-v1",
            policy.storageNamespacePathPolicyId,
        )

        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.platformStorageBoundaryContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.atomicWriteContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.crashRecoveryContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.interruptionTestContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.storageFailureModelStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.storageNamespacePathHygieneStatus,
        )

        assertContains(
            policy.storageBoundaryAllowedBytes,
            ProductionProviderStorageBoundaryAllowedBytes.EncryptedContainerBytes,
        )
        assertContains(
            policy.storageBoundaryAllowedBytes,
            ProductionProviderStorageBoundaryAllowedBytes.ManifestBytes,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.Passphrases,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.HkdfSubkeys,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.WalletSeedMaterial,
        )
        assertContains(
            policy.storageBoundaryRequirements,
            ProductionProviderStorageBoundaryRequirement.FailClosedOnUnknownState,
        )
        assertContains(
            policy.storageBoundaryRequirements,
            ProductionProviderStorageBoundaryRequirement.NoStorageImplementationInThisBranch,
        )
        assertContains(policy.atomicWritePhases, ProductionProviderAtomicWritePhase.DuringTempContainerWrite)
        assertContains(
            policy.atomicWritePhases,
            ProductionProviderAtomicWritePhase.AfterCommittingManifestBeforeCleanup,
        )
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.NeverAcceptManifestPointingToMissingMalformedData,
        )
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.NoAtomicWriteImplementationInThisBranch,
        )
        assertContains(
            policy.crashRecoveryFailClosedStates,
            ProductionProviderCrashRecoveryFailClosedState.ManifestReferencesMissingRecordOrContainerData,
        )
        assertContains(
            policy.crashRecoveryFailClosedStates,
            ProductionProviderCrashRecoveryFailClosedState.UnknownRecoveryState,
        )
        assertContains(policy.storageFailureCategories, ProductionProviderStorageFailureCategory.WriteFailed)
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.AtomicReplaceUnsupported,
        )
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.UnknownStorageState,
        )
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.NoAbsoluteUserSuppliedPaths,
        )
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.NoPathConstructionInThisBranch,
        )

        assertFalse(policy.platformStorageImplementationAdded)
        assertFalse(policy.filesystemVaultStorageImplemented)
        assertFalse(policy.databaseVaultStorageImplemented)
        assertFalse(policy.dataStoreVaultStorageImplemented)
        assertFalse(policy.sharedPreferencesVaultStorageImplemented)
        assertFalse(policy.manifestReadWriteImplemented)
        assertFalse(policy.storageIndexReadWriteImplemented)
        assertFalse(policy.tempFileImplementationAdded)
        assertFalse(policy.journalImplementationAdded)
        assertFalse(policy.atomicReplaceImplementationAdded)
        assertFalse(policy.durabilitySyncImplementationAdded)
        assertFalse(policy.crashRecoveryImplementationAdded)
        assertFalse(policy.interruptionTestRuntimeHooksAdded)
        assertFalse(policy.storageFailureRuntimeMappingImplemented)
        assertFalse(policy.storagePathConstructionImplemented)
        assertFalse(policy.atomicWriteRecoveryImplementationAdded)
        assertFalse(policy.vaultPersistenceImplemented)
    }

    @Test
    fun storageContractEvidenceStatesBlockProviderSelectabilityAndPersistence() {
        val gates = setOf(
            ProductionProviderAcceptanceGate.StoragePolicyContractApproved,
            ProductionProviderAcceptanceGate.PlatformStorageBoundaryContractApproved,
            ProductionProviderAcceptanceGate.AtomicityCrashRecoveryContractApproved,
            ProductionProviderAcceptanceGate.AtomicWriteStrategyContractApproved,
            ProductionProviderAcceptanceGate.CrashRecoveryContractApproved,
            ProductionProviderAcceptanceGate.StorageInterruptionTestContractApproved,
            ProductionProviderAcceptanceGate.StorageFailureModelContractApproved,
            ProductionProviderAcceptanceGate.StorageNamespacePathHygieneContractApproved,
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
    }

    @Test
    fun readinessAndDependencyProbeKeepStorageImplementationBlocked() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformStorageBoundaryContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AtomicWriteStrategyContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.CrashRecoveryContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageInterruptionTestContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageFailureModelContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageNamespacePathHygieneContractModel)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.PlatformStorageImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.AtomicWriteImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.CrashRecoveryImplementationMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.StorageInterruptionTestsMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.StorageFailureRuntimeMappingMissing)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.StorageNamespacePathImplementationMissing)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.PlatformStorageBoundaryContractModeled,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AtomicWriteStrategyContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.CrashRecoveryContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageInterruptionTestContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageFailureModelContractModeled)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.StorageNamespacePathHygieneContractModeled,
        )
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.PlatformStorageImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.AtomicWriteImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.CrashRecoveryImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StorageInterruptionTestsMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StorageFailureRuntimeMappingMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StorageNamespacePathImplementationMissing)
        assertFalse(selected.storageEnabled)
        assertFalse(selected.productionPersistenceEnabled)
        assertFalse(selected.readyForVaultImplementation)
    }
}
