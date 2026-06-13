package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderCandidatePackagingBoundaryRule
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessBlockerCategory
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessBoundaryId
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessCapabilityId
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixRequest
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixResult
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1CreationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateDependencyCategory
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateFamily
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateImplementationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePlatformSupportClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateReviewRequirement
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidateSourceSetPlacement
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RuntimeRandomnessAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1SecureStorageAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1UnlockAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
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
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultProviderCandidatePackagingBoundaryTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndAllCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(
                SkaldVaultV1ProviderCandidatePackagingRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-candidate-packaging-boundary-v1",
            SkaldVaultV1ProviderCandidatePackagingPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderCandidatePackagingPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderCandidatePackagingStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderCandidatePackagingSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerCandidatePackagingBoundaryModeled)
        assertTrue(evidence.providerCandidatePackagingStillDisabled)
        assertTrue(evidence.providerCandidatePackagingDoesNotImplementProvider)
        assertTrue(evidence.providerCandidatePackagingDoesNotActivateDependencies)
        assertTrue(evidence.providerCandidatePackagingDoesNotEnableProviderSelection)
        assertTrue(evidence.providerCandidatePackagingDoesNotRunCrypto)
        assertTrue(evidence.providerCandidatePackagingDoesNotEnableCreation)
        assertTrue(evidence.providerCandidatePackagingDoesNotEnableUnlock)
        assertTrue(evidence.providerCandidatePackagingDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)

        evidence.candidateRows.forEach { row ->
            assertFalse(row.selectable)
            assertFalse(row.executable)
            assertFalse(row.productionAuthorized)
            assertContains(row.statuses, SkaldVaultV1ProviderCandidatePackagingStatus.NonSelectable)
            assertContains(row.statuses, SkaldVaultV1ProviderCandidatePackagingStatus.NonExecutable)
            assertContains(row.statuses, SkaldVaultV1ProviderCandidatePackagingStatus.OperationUnauthorized)
            assertContains(row.blockers, SkaldVaultV1ProviderCandidateBlocker.DisabledProviderSelection)
            assertContains(row.blockers, SkaldVaultV1ProviderCandidateBlocker.ProductionProviderSelectableFalse)
            assertContains(row.blockers, SkaldVaultV1ProviderCandidateBlocker.ProviderOperationAuthorizationBlocked)
            assertContains(row.blockers, SkaldVaultV1ProviderCandidateBlocker.WarningOnlyEvidenceCannotAuthorize)
            assertContains(row.blockers, SkaldVaultV1ProviderCandidateBlocker.UserConsentCannotOverride)
            assertContains(row.blockers, SkaldVaultV1ProviderCandidateBlocker.MainnetDisabled)
        }
    }

    @Test
    fun candidateFamiliesAreModeledAsFutureOnlyOrFailClosed() {
        val summary = SkaldVaultV1ProviderCandidatePackagingPolicy.currentPolicySummary()
        val rows = blocked(
            SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(
                SkaldVaultV1ProviderCandidatePackagingRequest.currentEvidence(),
            ),
        ).candidateRows.associateBy { it.family }

        assertEquals(SkaldVaultV1ProviderCandidateFamily.entries.toSet(), summary.candidateFamilies)
        assertEquals(SkaldVaultV1ProviderCandidateFamily.entries.toSet(), rows.keys)
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.FutureOnlyNotImplemented,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.TinkJvmProviderCandidate).implementationStatus,
        )
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.FutureOnlyNotImplemented,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.BouncyCastleJvmProviderCandidate).implementationStatus,
        )
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.WrapperOnlyFutureReview,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.AndroidKeystoreWrapperCandidate).implementationStatus,
        )
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.RandomnessSourceOnlyFutureReview,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.PlatformOsCsprngCandidate).implementationStatus,
        )
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.TestOnlyRejectedForProduction,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.TestOnlyDeterministicProviderCandidate)
                .implementationStatus,
        )
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.UnknownFailClosed,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.UnknownCandidate).implementationStatus,
        )
        assertEquals(
            SkaldVaultV1ProviderCandidateImplementationStatus.UnsupportedFailClosed,
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.UnsupportedCandidate).implementationStatus,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.AndroidKeystoreWrapperCandidate).blockers,
            SkaldVaultV1ProviderCandidateBlocker.PlatformWrapperReviewMissing,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.TestOnlyDeterministicProviderCandidate).blockers,
            SkaldVaultV1ProviderCandidateBlocker.TestOnlyEvidenceRejectedForProduction,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.UnknownCandidate).blockers,
            SkaldVaultV1ProviderCandidateBlocker.UnknownCandidateRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderCandidateFamily.UnsupportedCandidate).blockers,
            SkaldVaultV1ProviderCandidateBlocker.UnsupportedCandidateRejected,
        )
    }

    @Test
    fun dependencyCategoriesSourceSetPlacementsAndRequiredGatesAreComplete() {
        val summary = SkaldVaultV1ProviderCandidatePackagingPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(
                SkaldVaultV1ProviderCandidatePackagingRequest.sourceSetPlacementAudit(),
            ),
        )

        assertEquals(SkaldVaultV1ProviderCandidateDependencyCategory.entries.toSet(), summary.dependencyCategories)
        assertEquals(
            SkaldVaultV1ProviderCandidateSourceSetPlacement.entries.toSet(),
            summary.sourceSetPlacements,
        )
        assertEquals(SkaldVaultV1ProviderCandidateReviewRequirement.entries.toSet(), summary.requiredGates)
        assertEquals(SkaldVaultV1ProviderCandidateBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderCandidatePackagingSource.SourceSetPlacementAudit,
            evidence.source,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.CommonModelEvidenceOnly,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.CommonProductionExecutionForbidden,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.AndroidProductionExecutionForbiddenThisBranch,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.DesktopProductionExecutionForbiddenThisBranch,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.TestKatVectorScaffoldingFutureReviewedOnly,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.ProductionProviderExecutionForbidden,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderCandidateSourceSetPlacement.ProductionProviderSelectionForbidden,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.ProviderDependencyReviewed,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.ProviderOperationAuthorizationApproved,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.RuntimeRandomnessAuthorizationApproved,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.KdfCalibrationAuthorizationApproved,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.AuthorizationReadinessMatrixAllowsPromotion,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.LaterProductionProviderSelectableChangeRequired,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderCandidateReviewRequirement.MainnetReleaseReviewRequired,
        )
    }

    @Test
    fun typedBoundaryEvidenceCanBeSuppliedButCannotAuthorizePackagingPromotion() {
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val contract = commonProductionProviderAcceptanceContract()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val matrixEvidence = blockedMatrix(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        )
        val request = SkaldVaultV1ProviderCandidatePackagingRequest.currentEvidence(
            candidateFamily = SkaldVaultV1ProviderCandidateFamily.TinkJvmProviderCandidate,
            dependencyCategory =
                SkaldVaultV1ProviderCandidateDependencyCategory.DependencyRuntimeProductionCandidateFutureReview,
            sourceSetPlacement = SkaldVaultV1ProviderCandidateSourceSetPlacement.CommonModelEvidenceOnly,
            providerSelectionResult = providerSelection,
            providerOperationEvidence = providerOperationEvidence(),
            runtimeRandomnessEvidence = runtimeRandomnessEvidence(),
            kdfCalibrationEvidence = kdfCalibrationEvidence(),
            secureStorageEvidence = secureStorageEvidence(),
            creationAuthorizationEvidence = creationAuthorizationEvidence(),
            unlockAuthorizationEvidence = unlockAuthorizationEvidence(),
            authorizationReadinessMatrixEvidence = matrixEvidence,
            providerAcceptanceAssessment = contract.assess(),
            dependencyProbeResult = dependency,
        )
        val evidence = blocked(SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(request))

        assertEquals(listOf(SkaldVaultV1ProviderCandidateFamily.TinkJvmProviderCandidate), evidence.candidateRows.map { it.family })
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerOperationEvidenceConsumed)
        assertTrue(evidence.runtimeRandomnessEvidenceConsumed)
        assertTrue(evidence.kdfCalibrationEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.creationAuthorizationEvidenceConsumed)
        assertTrue(evidence.unlockAuthorizationEvidenceConsumed)
        assertTrue(evidence.authorizationReadinessMatrixEvidenceConsumed)
        assertTrue(evidence.providerAcceptanceEvidenceConsumed)
        assertTrue(evidence.dependencyProbeEvidenceConsumed)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(contract.assess().productionProviderSelectable)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.DisabledProviderSelection)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.ProductionProviderSelectableFalse)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.UnlockAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.AuthorizationReadinessMatrixBlocksPromotion)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.WarningOnlyEvidenceCannotAuthorize)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.TestOnlyEvidenceRejectedForProduction)
        assertContains(evidence.blockers, SkaldVaultV1ProviderCandidateBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun aggregateReadinessProviderAcceptanceDependencyAndMatrixRecordBoundaryAsStillDisabled() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val matrixRows = blockedMatrix(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        ).capabilityRows.associateBy { it.capabilityId }

        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.ProviderCandidatePackagingBoundaryStillDisabled)
        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.ProviderCandidatePackagingBoundaryBuildingBlock,
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.ProviderCandidatePackagingBoundaryImplementedAndTested
            ],
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.ProviderCandidatePackagingBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.ProviderCandidatePackagingFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.ProviderCandidatePackagingBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.ProviderCandidatePackagingRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.ProviderCandidatePackagingTestsMissing,
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.ProviderCandidatePackagingBoundaryImplementedAndTested),
        )
        assertEquals(
            SkaldVaultV1ProviderCandidatePackagingPolicy.POLICY_ID,
            storageContract.providerCandidatePackagingBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.providerCandidatePackagingBoundaryStatus,
        )
        assertEquals(
            ProductionProviderCandidatePackagingBoundaryRule.entries.toSet(),
            storageContract.providerCandidatePackagingBoundaryRules,
        )
        assertTrue(storageContract.providerCandidatePackagingBoundaryModeled)
        assertTrue(storageContract.providerCandidatePackagingStillDisabled)
        assertTrue(storageContract.providerCandidatePackagingDoesNotImplementProvider)
        assertTrue(storageContract.providerCandidatePackagingDoesNotActivateDependencies)
        assertTrue(storageContract.providerCandidatePackagingDoesNotEnableProviderSelection)
        assertTrue(storageContract.providerCandidatePackagingDoesNotRunCrypto)
        assertTrue(storageContract.providerCandidatePackagingDoesNotEnableCreation)
        assertTrue(storageContract.providerCandidatePackagingDoesNotEnableUnlock)
        assertTrue(storageContract.providerCandidatePackagingDoesNotEnablePersistence)
        assertFalse(contract.assess().productionProviderSelectable)
        assertTrace(
            matrixRows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.ProductionProviderSelectability,
            SkaldVaultV1AuthorizationReadinessBoundaryId.ProviderCandidatePackagingBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderCandidatePackagingStillDisabled,
        )
        assertContains(
            matrixRows.getValue(SkaldVaultV1AuthorizationReadinessCapabilityId.ProductionProviderSelectability)
                .requiredFutureEvidence,
            SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ProviderCandidatePackagingApproved,
        )
    }

    @Test
    fun requestResultAndPolicyTokenStayRedacted() {
        val request = SkaldVaultV1ProviderCandidatePackagingRequest.forCandidate(
            SkaldVaultV1ProviderCandidateFamily.AndroidKeystoreWrapperCandidate,
        )
        val result = SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "provider-handle",
            "crypto-object",
            "byte-material",
            "raw-path",
            "storage-id",
            "secret-value",
            "key-material",
            "ciphertext-material",
            "plaintext-material",
        )

        forbiddenValues.forEach { raw ->
            assertFalse(request.toString().contains(raw))
            assertFalse(result.toString().contains(raw))
            assertFalse(evidence.toString().contains(raw))
            assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        }
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsCryptoObject)
        assertFalse(evidence.policyTokenEvidence.containsByteMaterial)
        assertFalse(evidence.policyTokenEvidence.containsPathOrRootText)
        assertFalse(evidence.policyTokenEvidence.containsStorageIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsSecretMaterial)
    }

    @Test
    fun individualCandidateAuditReturnsOnlyBlockedRows() {
        SkaldVaultV1ProviderCandidateFamily.entries.forEach { family ->
            val evidence = blocked(
                SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(
                    SkaldVaultV1ProviderCandidatePackagingRequest.forCandidate(family),
                ),
            )

            assertEquals(listOf(family), evidence.candidateRows.map { it.family })
            assertFalse(evidence.candidateRows.single().selectable)
            assertFalse(evidence.candidateRows.single().executable)
            assertFalse(evidence.candidateRows.single().productionAuthorized)
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
    }

    private fun providerOperationEvidence(): SkaldVaultV1VaultProviderOperationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun runtimeRandomnessEvidence(): SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked<
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun kdfCalibrationEvidence(): SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Blocked<
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun secureStorageEvidence(): SkaldVaultV1VaultSecureStorageAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultSecureStorageAuthorizationResult.Blocked<
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun creationAuthorizationEvidence(): SkaldVaultV1VaultCreationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultCreationAuthorizationResult.Blocked<
                SkaldVaultV1VaultCreationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun unlockAuthorizationEvidence(): SkaldVaultV1VaultUnlockAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultUnlockAuthorizationResult.Blocked<
                SkaldVaultV1VaultUnlockAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun blocked(
        result: SkaldVaultV1ProviderCandidatePackagingResult<
            SkaldVaultV1ProviderCandidatePackagingEvidence,
        >,
    ): SkaldVaultV1ProviderCandidatePackagingEvidence =
        assertIs<
            SkaldVaultV1ProviderCandidatePackagingResult.Blocked<
                SkaldVaultV1ProviderCandidatePackagingEvidence,
            >,
        >(result).value

    private fun blockedMatrix(
        result: SkaldVaultV1AuthorizationReadinessMatrixResult<
            com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence,
        >,
    ): com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence =
        assertIs<
            SkaldVaultV1AuthorizationReadinessMatrixResult.Blocked<
                com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence,
            >,
        >(result).value

    private fun assertTrace(
        rows: Map<
            SkaldVaultV1AuthorizationReadinessCapabilityId,
            com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessCapabilityRow,
        >,
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        boundaryId: SkaldVaultV1AuthorizationReadinessBoundaryId,
        blocker: SkaldVaultV1AuthorizationReadinessBlockerCategory,
    ) {
        val row = requireNotNull(rows[capabilityId])
        assertContains(row.blockingBoundaries, boundaryId)
        assertContains(row.blockerCategories, blocker)
        assertFalse(row.currentlyReady)
        assertFalse(row.runtimeAvailable)
    }

    private fun assertDisabled(capability: SkaldVaultV1ProviderCandidateCapability) {
        assertFalse(capability.providerCandidateImplemented)
        assertFalse(capability.providerDependencyActive)
        assertFalse(capability.providerRuntimeInstantiable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelectable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerKatExecutionAvailable)
        assertFalse(capability.runtimeRandomnessAvailable)
        assertFalse(capability.kdfExecutionAvailable)
        assertFalse(capability.aeadExecutionAvailable)
        assertFalse(capability.hkdfHmacExecutionAvailable)
        assertFalse(capability.headerCommitmentAvailable)
        assertFalse(capability.keyWrappingAvailable)
        assertFalse(capability.providerClearAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertDisabled(evidence: SkaldVaultV1ProviderCandidatePackagingEvidence) {
        assertFalse(evidence.providerCandidateImplemented)
        assertFalse(evidence.providerDependencyActive)
        assertFalse(evidence.providerRuntimeInstantiable)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.providerKatExecutionAvailable)
        assertFalse(evidence.runtimeRandomnessAvailable)
        assertFalse(evidence.kdfExecutionAvailable)
        assertFalse(evidence.aeadExecutionAvailable)
        assertFalse(evidence.vaultCreationAvailable)
        assertFalse(evidence.vaultUnlockAvailable)
        assertFalse(evidence.vaultPersistenceAvailable)
        assertFalse(evidence.mainnetAvailable)
    }
}
