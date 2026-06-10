package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderStorageLayoutPlanRule
import com.libertasprimordium.skald.security.SkaldVaultV1LogicalStorageLocation
import com.libertasprimordium.skald.security.SkaldVaultV1StorageIdentifierSource
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutArtifact
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlan
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlanPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathResult
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

class VaultStorageLayoutPlanTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()

    @Test
    fun storageLayoutPolicyIdIsStable() {
        assertEquals(
            "skald-vault-v1-storage-layout-plan-v1",
            SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1StorageLayoutPlanPolicy.POLICY_VERSION)
    }

    @Test
    fun fixedNonSecretVaultAndRecordIdsGenerateDeterministicRootlessLocations() {
        val plan = acceptedPlan(
            SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(fixedRecordId),
            ),
        )
        val vaultSegment = "vault_202122232425262728292a2b2c2d2e2f"
        val recordSegment = "record_404142434445464748494a4b4c4d4e4f"

        assertEquals(SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID, plan.policyId)
        assertEquals(SkaldVaultV1StorageNamespacePathPolicy.STORAGE_NAMESPACE_ID, plan.storageNamespaceId)
        assertEquals(SkaldVaultV1StorageNamespacePathPolicy.RECORD_NAMESPACE_ID, plan.recordNamespaceId)
        assertEquals(SkaldVaultV1StorageNamespacePathPolicy.MANIFEST_NAMESPACE_ID, plan.manifestNamespaceId)
        assertEquals(vaultSegment, plan.vaultSegment.value)

        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "container", "current"),
            plan.currentContainer.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "manifest", "manifest_v1"),
            plan.currentManifest.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "index", "index_v1"),
            plan.currentStorageIndex.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "records", recordSegment),
            plan.singleRecordLocation().segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "tmp", "container_pending"),
            plan.tempContainer.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "tmp", "manifest_pending"),
            plan.tempManifest.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "tmp", "index_pending"),
            plan.tempStorageIndex.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "quarantine", "pending"),
            plan.quarantineRoot.segmentValues,
        )
        assertEquals(
            listOf("skald-vault-v1", vaultSegment, "recovery", "recovery_v1"),
            plan.recoveryMetadata.segmentValues,
        )
    }

    @Test
    fun allGeneratedSegmentsAreSafeRelativeSegmentsOnly() {
        val plan = acceptedPlan(
            SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(fixedRecordId),
            ),
        )

        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.CurrentContainer)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.CurrentManifest)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.CurrentStorageIndex)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.Record)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.TempContainer)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.TempManifest)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.TempStorageIndex)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.QuarantineRoot)
        assertContains(plan.allLocations.map { it.artifact }, SkaldVaultV1StorageLayoutArtifact.RecoveryMetadata)

        plan.allLocations
            .flatMap { it.segmentValues }
            .forEach { segment ->
                assertTrue(segment.isNotEmpty(), "segment must not be empty")
                assertFalse(segment.startsWith("/"), "segment must not be absolute")
                assertFalse(segment.startsWith("\\"), "segment must not be absolute")
                assertFalse(segment.contains('/'), "segment must not contain slash")
                assertFalse(segment.contains('\\'), "segment must not contain backslash")
                assertFalse(segment == ".", "segment must not be dot")
                assertFalse(segment == "..", "segment must not be parent")
                assertFalse(segment.contains("wallet"), "segment must not include user labels")
                assertFalse(segment.contains("secret"), "segment must not include secret-looking text")
                assertTrue(
                    SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(segment) is
                        SkaldVaultV1StorageNamespacePathResult.Accepted,
                    "generated segment must pass namespace/path safe-segment validation: $segment",
                )
            }
    }

    @Test
    fun unsafeInputsFailClosedWithTypedReasons() {
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.planForVault(vaultId = ByteArray(15)),
            SkaldVaultV1StorageLayoutRejectionReason.InvalidVaultSegment,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(ByteArray(17)),
            ),
            SkaldVaultV1StorageLayoutRejectionReason.InvalidRecordSegment,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                policyId = "unsupported-layout-policy",
            ),
            SkaldVaultV1StorageLayoutRejectionReason.UnsupportedLayoutPolicy,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.validateArtifactSegment("artifact/with/slash"),
            SkaldVaultV1StorageLayoutRejectionReason.UnsafeSegmentRejected,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.validateArtifactSegment("/absolute"),
            SkaldVaultV1StorageLayoutRejectionReason.AbsolutePathRejected,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.validateArtifactSegment("record/../manifest"),
            SkaldVaultV1StorageLayoutRejectionReason.TraversalRejected,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.validateArtifactSegment(
                value = "wallet_label",
                source = SkaldVaultV1StorageIdentifierSource.UserControlledText,
            ),
            SkaldVaultV1StorageLayoutRejectionReason.UserControlledInputRejected,
        )
        assertRejected(
            SkaldVaultV1StorageLayoutPlanPolicy.validateArtifactSegment(
                value = "candidate_secret_material",
                source = SkaldVaultV1StorageIdentifierSource.SecretMaterialCandidate,
            ),
            SkaldVaultV1StorageLayoutRejectionReason.SecretMaterialRejected,
        )
    }

    @Test
    fun layoutEvidenceIsRepresentedButStillDoesNotEnablePersistenceOrSelection() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID, storageContract.storageLayoutPlanPolicyId)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, storageContract.storageLayoutPlanStatus)
        assertEquals(ProductionProviderStorageLayoutPlanRule.entries.toSet(), storageContract.storageLayoutPlanRules)
        assertTrue(storageContract.storageLayoutPlanImplemented)
        assertTrue(storageContract.storageLayoutLocationsRootless)
        assertTrue(storageContract.storageLayoutUsesSafeSegmentsOnly)
        assertFalse(storageContract.platformRootResolutionImplemented)
        assertFalse(storageContract.platformRootSelectionImplemented)
        assertFalse(storageContract.actualPathConstructionImplemented)
        assertFalse(storageContract.pathJoinImplementationAdded)
        assertFalse(storageContract.pathContainmentCheckImplementationAdded)
        assertFalse(storageContract.platformStorageImplementationAdded)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageLayoutPlanImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)

        listOf(
            ProductionProviderAcceptanceEvidenceState.Missing to ProductionProviderAcceptanceBlocker.MissingGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unknown to ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Failed to ProductionProviderAcceptanceBlocker.FailedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unsupported to
                ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        ).forEach { (state, blocker) ->
            val blocked = contract.assess(
                ProductionProviderAcceptanceEvidence(
                    gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                        ProductionProviderAcceptanceEvidenceState.Satisfied
                    } + mapOf(
                        ProductionProviderAcceptanceGate.StorageLayoutPlanImplementedAndTested to state,
                    ),
                ),
            )

            assertContains(blocked.blockers, blocker)
            assertFalse(blocked.productionProviderSelectable)
            assertFalse(blocked.productionPersistenceAllowed)
        }

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.StorageLayoutPlanImplementedAndTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageLayoutPlanBuildingBlock)
        assertFalse(readiness.productionPersistenceEnabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.StorageLayoutPlanImplementedTested,
        )
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun acceptedPlan(
        result: SkaldVaultV1StorageLayoutResult<SkaldVaultV1StorageLayoutPlan>,
    ): SkaldVaultV1StorageLayoutPlan =
        when (result) {
            is SkaldVaultV1StorageLayoutResult.Accepted -> result.value
            is SkaldVaultV1StorageLayoutResult.Rejected -> error(result.safeMessage)
        }

    private fun SkaldVaultV1StorageLayoutPlan.singleRecordLocation(): SkaldVaultV1LogicalStorageLocation {
        assertEquals(1, recordLocations.size)
        return recordLocations.single()
    }

    private fun assertRejected(
        result: SkaldVaultV1StorageLayoutResult<*>,
        expected: SkaldVaultV1StorageLayoutRejectionReason,
    ) {
        assertTrue(result is SkaldVaultV1StorageLayoutResult.Rejected)
        assertEquals(expected, result.reason)
        assertFalse(result.safeMessage.contains("wallet_label"))
        assertFalse(result.safeMessage.contains("candidate_secret_material"))
        assertFalse(result.safeMessage.contains("/absolute"))
    }
}
