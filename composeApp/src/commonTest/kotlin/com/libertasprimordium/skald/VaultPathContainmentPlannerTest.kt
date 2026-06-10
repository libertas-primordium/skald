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
import com.libertasprimordium.skald.security.ProductionProviderPathContainmentPlannerRule
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentPlanner
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentProof
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentResult
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentRootToken
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentPlan
import com.libertasprimordium.skald.security.SkaldVaultV1PlannedArtifactKind
import com.libertasprimordium.skald.security.SkaldVaultV1StorageIdentifierSource
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlan
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlanPolicy
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

class VaultPathContainmentPlannerTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()

    @Test
    fun pathContainmentPlannerPolicyAndRootTokenIdsAreStable() {
        assertEquals("skald-vault-v1-path-containment-planner-v1", SkaldVaultV1PathContainmentPlanner.POLICY_ID)
        assertEquals(1, SkaldVaultV1PathContainmentPlanner.POLICY_VERSION)
        assertEquals(
            "android-app-private-internal-root-v1",
            SkaldVaultV1PathContainmentRootToken.AndroidAppPrivateInternalRoot.tokenId,
        )
        assertEquals(
            "desktop-app-controlled-user-data-root-v1",
            SkaldVaultV1PathContainmentRootToken.DesktopAppControlledUserDataRoot.tokenId,
        )
        assertEquals(
            "test-only-reviewed-root-v1",
            SkaldVaultV1PathContainmentRootToken.TestOnlyReviewedRoot.tokenId,
        )
    }

    @Test
    fun allowedReviewedRootTokensAreAcceptedAsTokensOnly() {
        val allowedTokens = listOf(
            SkaldVaultV1PathContainmentRootToken.AndroidAppPrivateInternalRoot,
            SkaldVaultV1PathContainmentRootToken.DesktopAppControlledUserDataRoot,
            SkaldVaultV1PathContainmentRootToken.TestOnlyReviewedRoot,
        )

        allowedTokens.forEach { token ->
            val plan = acceptedPlan(SkaldVaultV1PathContainmentPlanner.planForLayout(token, fixedLayout()))

            assertEquals(token, plan.rootToken)
            assertFalse(plan.isPlatformPathPlan)
            plan.plannedLocations.forEach { location ->
                assertEquals(token, location.rootToken)
                assertTrue(location.rootTokenBound)
                assertTrue(location.relativeSegmentsOnly)
                assertFalse(location.platformPathConstructed)
                assertFalse(location.realFilesystemContainmentChecked)
                assertFalse(location.symlinkChecked)
                assertFalse(location.permissionChecked)
                assertFalse(location.durabilityProbed)
                assertEquals(
                    SkaldVaultV1PathContainmentProof.RootTokenBoundRelativeSegments,
                    location.containmentProof,
                )
            }
        }
    }

    @Test
    fun unreviewedOrUnsafeRootTokensFailClosed() {
        assertRejectedRoot(null, SkaldVaultV1PathContainmentRejectionReason.ReviewedRootMissing)
        assertRejectedRoot(
            SkaldVaultV1PathContainmentRootToken.AndroidExternalSharedRoot,
            SkaldVaultV1PathContainmentRejectionReason.ExternalStorageRootRejected,
        )
        assertRejectedRoot(
            SkaldVaultV1PathContainmentRootToken.UserSelectedPathRoot,
            SkaldVaultV1PathContainmentRejectionReason.UserPathRootRejected,
        )
        assertRejectedRoot(
            SkaldVaultV1PathContainmentRootToken.UnknownRoot,
            SkaldVaultV1PathContainmentRejectionReason.ReviewedRootUnknown,
        )
        assertRejectedRoot(
            SkaldVaultV1PathContainmentRootToken.UnreviewedRoot,
            SkaldVaultV1PathContainmentRejectionReason.ReviewedRootRejected,
        )
        assertRejectedRoot(
            SkaldVaultV1PathContainmentRootToken.UnsafeRoot,
            SkaldVaultV1PathContainmentRejectionReason.ReviewedRootRejected,
        )
    }

    @Test
    fun layoutPlanLocationsConvertToDeterministicPlannedLocations() {
        val plan = acceptedPlan(
            SkaldVaultV1PathContainmentPlanner.planForLayout(
                rootToken = SkaldVaultV1PathContainmentRootToken.TestOnlyReviewedRoot,
                layoutPlan = fixedLayout(),
            ),
        )
        val vaultSegment = "vault_202122232425262728292a2b2c2d2e2f"
        val recordSegment = "record_404142434445464748494a4b4c4d4e4f"

        assertEquals(SkaldVaultV1PathContainmentPlanner.POLICY_ID, plan.plannerPolicyId)
        assertEquals(SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID, plan.storageLayoutPolicyId)
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.CurrentContainer,
            listOf("skald-vault-v1", vaultSegment, "container", "current"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.CurrentManifest,
            listOf("skald-vault-v1", vaultSegment, "manifest", "manifest_v1"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.CurrentStorageIndex,
            listOf("skald-vault-v1", vaultSegment, "index", "index_v1"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.RecordArtifact,
            listOf("skald-vault-v1", vaultSegment, "records", recordSegment),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.TempContainer,
            listOf("skald-vault-v1", vaultSegment, "tmp", "container_pending"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.TempManifest,
            listOf("skald-vault-v1", vaultSegment, "tmp", "manifest_pending"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.TempStorageIndex,
            listOf("skald-vault-v1", vaultSegment, "tmp", "index_pending"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.QuarantineRoot,
            listOf("skald-vault-v1", vaultSegment, "quarantine", "pending"),
        )
        assertPlannedLocation(
            plan,
            SkaldVaultV1PlannedArtifactKind.RecoveryMetadata,
            listOf("skald-vault-v1", vaultSegment, "recovery", "recovery_v1"),
        )

        plan.plannedLocations
            .flatMap { it.segmentValues }
            .forEach { segment ->
                assertFalse(segment.startsWith("/"))
                assertFalse(segment.startsWith("\\"))
                assertFalse(segment.contains('/'))
                assertFalse(segment.contains('\\'))
                assertFalse(segment == ".")
                assertFalse(segment == "..")
                assertFalse(segment.contains("wallet"))
                assertFalse(segment.contains("secret"))
                assertTrue(
                    SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(segment) is
                        SkaldVaultV1StorageNamespacePathResult.Accepted,
                    "planned segment must pass safe-segment validation: $segment",
                )
            }
    }

    @Test
    fun unsafeSegmentsFailClosedWithTypedReasons() {
        assertRejectedSegments(emptyList(), SkaldVaultV1PathContainmentRejectionReason.EmptySegmentList)
        assertRejectedSegments(listOf("/absolute"), SkaldVaultV1PathContainmentRejectionReason.AbsoluteSegmentRejected)
        assertRejectedSegments(listOf("."), SkaldVaultV1PathContainmentRejectionReason.DotSegmentRejected)
        assertRejectedSegments(listOf(".."), SkaldVaultV1PathContainmentRejectionReason.ParentSegmentRejected)
        assertRejectedSegments(listOf("with/slash"), SkaldVaultV1PathContainmentRejectionReason.SegmentContainsSeparator)
        assertRejectedSegments(
            listOf("with\\backslash"),
            SkaldVaultV1PathContainmentRejectionReason.SegmentContainsSeparator,
        )
        assertRejectedSegments(
            listOf("record/../manifest"),
            SkaldVaultV1PathContainmentRejectionReason.UnsafeSegmentRejected,
        )
        assertRejectedSegments(
            listOf("with\u0001control"),
            SkaldVaultV1PathContainmentRejectionReason.SegmentContainsControlCharacter,
        )
        assertRejectedSegments(
            listOf("with space"),
            SkaldVaultV1PathContainmentRejectionReason.SegmentContainsWhitespace,
        )
        assertRejectedSegments(
            listOf("with\u200bformat"),
            SkaldVaultV1PathContainmentRejectionReason.SegmentContainsInvisibleFormat,
        )
        assertRejectedSegments(
            listOf("unicode-é"),
            SkaldVaultV1PathContainmentRejectionReason.SegmentContainsNonAscii,
        )
        assertRejectedSegments(
            listOf("unsupported.period"),
            SkaldVaultV1PathContainmentRejectionReason.UnsafeSegmentRejected,
        )
        assertRejectedSegments(
            listOf("a".repeat(SkaldVaultV1StorageNamespacePathPolicy.MAX_SEGMENT_BYTES + 1)),
            SkaldVaultV1PathContainmentRejectionReason.SegmentTooLong,
        )
        assertRejectedSegments(
            listOf("wallet_label"),
            SkaldVaultV1PathContainmentRejectionReason.UserControlledLabelRejected,
            source = SkaldVaultV1StorageIdentifierSource.UserControlledText,
        )
        assertRejectedSegments(
            listOf("candidate_secret_material"),
            SkaldVaultV1PathContainmentRejectionReason.SecretMaterialRejected,
            source = SkaldVaultV1StorageIdentifierSource.SecretMaterialCandidate,
        )
        assertRejectedLocation(
            storageLayoutPolicyId = "unsupported-layout-policy",
            expectedReason = SkaldVaultV1PathContainmentRejectionReason.UnsupportedLayoutPolicy,
        )
    }

    @Test
    fun plannerEvidenceIsRepresentedButStillDoesNotEnablePersistenceOrSelection() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1PathContainmentPlanner.POLICY_ID, storageContract.pathContainmentPlannerPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.pathContainmentPlannerStatus,
        )
        assertEquals(
            ProductionProviderPathContainmentPlannerRule.entries.toSet(),
            storageContract.pathContainmentPlannerRules,
        )
        assertTrue(storageContract.reviewedRootTokenPolicyImplemented)
        assertTrue(storageContract.pathContainmentPlannerImplemented)
        assertTrue(storageContract.plannedLocationsRootTokenBound)
        assertFalse(storageContract.plannedLocationsArePlatformPaths)
        assertTrue(storageContract.plannedLocationsUseSafeSegmentsOnly)
        assertFalse(storageContract.platformRootResolutionImplemented)
        assertFalse(storageContract.platformRootSelectionImplemented)
        assertFalse(storageContract.actualPathConstructionImplemented)
        assertFalse(storageContract.pathJoinImplementationAdded)
        assertFalse(storageContract.pathContainmentCheckImplementationAdded)
        assertFalse(storageContract.symlinkCheckImplementationAdded)
        assertFalse(storageContract.permissionCheckImplementationAdded)
        assertFalse(storageContract.durabilityProbeImplementationAdded)
        assertFalse(storageContract.platformStorageImplementationAdded)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PathContainmentPlannerImplementedAndTested),
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
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly to
                ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        ).forEach { (state, blocker) ->
            val blocked = contract.assess(
                ProductionProviderAcceptanceEvidence(
                    gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                        ProductionProviderAcceptanceEvidenceState.Satisfied
                    } + mapOf(
                        ProductionProviderAcceptanceGate.PathContainmentPlannerImplementedAndTested to state,
                    ),
                ),
            )

            assertContains(blocked.blockers, blocker)
            assertFalse(blocked.productionProviderSelectable)
            assertFalse(blocked.productionPersistenceAllowed)
        }

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.PathContainmentPlannerImplementedAndTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.PathContainmentPlannerBuildingBlock)
        assertFalse(readiness.productionPersistenceEnabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.PathContainmentPlannerImplementedTested,
        )
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun fixedLayout(): SkaldVaultV1StorageLayoutPlan =
        when (
            val result = SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(fixedRecordId),
            )
        ) {
            is SkaldVaultV1StorageLayoutResult.Accepted -> result.value
            is SkaldVaultV1StorageLayoutResult.Rejected -> error("fixed non-secret layout rejected")
        }

    private fun acceptedPlan(
        result: SkaldVaultV1PathContainmentResult<SkaldVaultV1PathContainmentPlan>,
    ): SkaldVaultV1PathContainmentPlan =
        when (result) {
            is SkaldVaultV1PathContainmentResult.Accepted -> result.value
            is SkaldVaultV1PathContainmentResult.Rejected -> error("expected accepted plan, got ${result.reason}")
        }

    private fun assertPlannedLocation(
        plan: SkaldVaultV1PathContainmentPlan,
        artifactKind: SkaldVaultV1PlannedArtifactKind,
        expectedSegments: List<String>,
    ) {
        val location = plan.plannedLocations.single { it.artifactKind == artifactKind }

        assertEquals(SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID, location.storageLayoutPolicyId)
        assertEquals(expectedSegments, location.segmentValues)
    }

    private fun assertRejectedRoot(
        rootToken: SkaldVaultV1PathContainmentRootToken?,
        expectedReason: SkaldVaultV1PathContainmentRejectionReason,
    ) {
        val result = SkaldVaultV1PathContainmentPlanner.planForLayout(rootToken, fixedLayout())

        assertRejected(result, expectedReason)
    }

    private fun assertRejectedSegments(
        segments: List<String>,
        expectedReason: SkaldVaultV1PathContainmentRejectionReason,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.EncodedIdentifier,
    ) {
        assertRejectedLocation(expectedReason = expectedReason, segmentValues = segments, source = source)
    }

    private fun assertRejectedLocation(
        expectedReason: SkaldVaultV1PathContainmentRejectionReason,
        storageLayoutPolicyId: String = SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID,
        segmentValues: List<String> = listOf("skald-vault-v1"),
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.EncodedIdentifier,
    ) {
        val result = SkaldVaultV1PathContainmentPlanner.planFromSegmentValues(
            rootToken = SkaldVaultV1PathContainmentRootToken.TestOnlyReviewedRoot,
            storageLayoutPolicyId = storageLayoutPolicyId,
            artifactKind = SkaldVaultV1PlannedArtifactKind.CurrentContainer,
            segmentValues = segmentValues,
            segmentSource = source,
        )

        assertRejected(result, expectedReason)
    }

    private fun assertRejected(
        result: SkaldVaultV1PathContainmentResult<*>,
        expectedReason: SkaldVaultV1PathContainmentRejectionReason,
    ) {
        when (result) {
            is SkaldVaultV1PathContainmentResult.Accepted ->
                error("expected rejection $expectedReason, got ${result.value}")
            is SkaldVaultV1PathContainmentResult.Rejected ->
                assertEquals(expectedReason, result.reason)
        }
    }
}
