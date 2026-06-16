package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixRequest
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixResult
import com.libertasprimordium.skald.security.SkaldVaultV1CreationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonRequest
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationRequiredProperty
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationRisk
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationTopic
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationGuardRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationGuardResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionResult
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
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRequest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultProviderFactoryIsolationBoundaryTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndFactoryCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                SkaldVaultV1ProviderFactoryIsolationRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-factory-isolation-boundary-v1",
            SkaldVaultV1ProviderFactoryIsolationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderFactoryIsolationPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderFactoryIsolationStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderFactoryIsolationSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerFactoryIsolationBoundaryModeled)
        assertTrue(evidence.providerFactoryIsolationStillDisabled)
        assertTrue(evidence.providerFactoryIsolationConfirmsNoFactory)
        assertTrue(evidence.providerFactoryIsolationExcludesSkeletonConstruction)
        assertTrue(evidence.providerFactoryIsolationExcludesCandidateConstruction)
        assertTrue(evidence.providerFactoryIsolationExcludesRuntimeProviderConstruction)
        assertTrue(evidence.providerFactoryIsolationDoesNotRegisterProvider)
        assertTrue(evidence.providerFactoryIsolationDoesNotEnableProviderSelection)
        assertTrue(evidence.providerFactoryIsolationDoesNotRunCrypto)
        assertTrue(evidence.providerFactoryIsolationDoesNotEnableCreation)
        assertTrue(evidence.providerFactoryIsolationDoesNotEnableUnlock)
        assertTrue(evidence.providerFactoryIsolationDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun factoryTopicCoverageModelsEveryConstructionSurfaceAndKeepsFactoriesUnavailable() {
        val summary = SkaldVaultV1ProviderFactoryIsolationPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                SkaldVaultV1ProviderFactoryIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.topicRows.associateBy { it.topic }

        assertEquals(SkaldVaultV1ProviderFactoryIsolationStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderFactoryIsolationTopic.entries.toSet(), summary.topics)
        assertEquals(SkaldVaultV1ProviderFactoryIsolationRisk.entries.toSet(), summary.risks)
        assertEquals(
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.entries.toSet(),
            summary.requiredProperties,
        )
        assertEquals(SkaldVaultV1ProviderFactoryIsolationBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderFactoryIsolationRedactionClass.entries.toSet(),
            summary.redactionClasses,
        )
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.confirmsNoFactory)
        assertTrue(summary.blocksConstruction)
        assertTrue(summary.blocksProviderSelection)

        assertEquals(SkaldVaultV1ProviderFactoryIsolationTopic.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.evidenceOnly)
            assertFalse(row.factoryAvailable)
            assertFalse(row.runtimeConstructionAllowed)
            assertFalse(row.providerRuntimeExposed)
            assertContains(row.statuses, SkaldVaultV1ProviderFactoryIsolationStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderFactoryIsolationStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
            assertContains(row.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderFactoryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled)
        }
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.ActiveProviderFactorySurface)
                .constructionExcluded,
        )
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.DisabledProviderConstructionSurface)
                .constructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.ActiveProviderFactorySurface).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.NonSelectableSkeletonConstructionExcluded)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.CandidateProviderConstructionExcluded)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CandidateConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.DependencyBuildEvidenceConstructionExcluded)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.CandidatePackagingConstructionExcluded)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.InterfaceAuditConstructionExcluded).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.PromotionBlockerConstructionExcluded).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.AuthorizationReadinessMatrixConstructionExcluded)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.ProviderRegistryConstructionExcluded).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderRegistryIsolationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ReleaseReviewMissing,
        )
    }

    @Test
    fun factoryRiskCoverageRejectsEveryProviderConstructionPath() {
        val evidence = blocked(
            SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                SkaldVaultV1ProviderFactoryIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.riskRows.associateBy { it.risk }

        assertEquals(SkaldVaultV1ProviderFactoryIsolationRisk.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.rejected)
            assertFalse(row.accepted)
            assertFalse(row.canConstruct)
            assertFalse(row.canExposeRuntime)
            assertFalse(row.canExecute)
            assertFalse(row.canChangeSelectability)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesNonDisabledProvider).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesSkeletonProvider).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesCandidateProvider).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CandidateConstructionExcluded,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderFactoryIsolationRisk
                    .FactoryTreatsDependencyBuildEvidenceAsConstructionEvidence,
            ).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsPackagingEvidenceAsConstructionEvidence)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsInterfaceAuditEvidenceAsConstructionEvidence,
            ).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsProviderHandles).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderHandlesRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsCryptoObjects).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CryptoObjectsRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsByteMaterial).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryImportsTinkBouncyJavaxCrypto).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.TinkBouncyJavaxImportsForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCallsSecureRandom).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.SecureRandomForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryRunsKats).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.KatExecutionUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCallsProviderOperations).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationExecutionForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryReachesRandomnessKdfAead).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryWrapsKeys).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.KeyWrappingUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryEnablesVaultCreationUnlockPersistence)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CreationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRisk.FactoryEnablesMainnet).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun requiredPropertyCoverageKeepsFactoriesAbsentAndUnreachable() {
        val evidence = blocked(
            SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                SkaldVaultV1ProviderFactoryIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.requiredPropertyRows.associateBy { it.requiredProperty }

        assertEquals(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.satisfiedForCurrentState)
            assertTrue(row.futureExplicitReviewRequired)
            assertFalse(row.factoryRuntimeAvailable)
            assertFalse(row.nonDisabledConstructionAllowed)
            assertContains(row.statuses, SkaldVaultV1ProviderFactoryIsolationStatus.FactoryIsolated)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoNonDisabledProviderFactoryExists)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoNonDisabledProviderConstructorExists)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderConstructor,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryReachableFromProviderSelection,
            ).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryReachableFromRegistryIsolation,
            ).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderRegistryIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryReachableFromSkeletonEvidence,
            ).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryAcceptsByteMaterial)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryCanRunKdfAeadHkdfHmac)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderFactoryIsolationRequiredProperty
                    .NoProviderFactoryCanPromotePackagingSkeletonAuditMatrixEvidence,
            ).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride)
                .blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.UserConsentCannotOverride,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderFactoryIsolationRequiredProperty.MainnetDisabled).blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun existingEvidenceCanBeSuppliedButCannotConstructOrExposeProviderRuntime() {
        val selection = VaultCryptoProviderSelectionRegistry.select(
            VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
        )
        val dependencyBuild = blockedDependencyBuild(
            SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                SkaldVaultV1ProviderDependencyBuildRequest.currentEvidence(),
            ),
        )
        val packaging = blockedPackaging(
            SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(
                SkaldVaultV1ProviderCandidatePackagingRequest.currentEvidence(),
            ),
        )
        val matrix = blockedMatrix(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        )
        val interfaceAudit = blockedInterfaceAudit(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                ),
            ),
        )
        val promotion = blockedPromotion(
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                SkaldVaultV1ProviderSelectionPromotionRequest.currentEvidence(
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                ),
            ),
        )
        val skeleton = blockedSkeleton(
            SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                SkaldVaultV1NonSelectableProviderSkeletonRequest.currentEvidence(
                    providerInterfaceAuditEvidence = interfaceAudit,
                    providerSelectionPromotionEvidence = promotion,
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                ),
            ),
        )
        val registry = blockedRegistry(
            SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                SkaldVaultV1ProviderRegistryIsolationGuardRequest.currentEvidence(
                    providerSelectionResult = selection,
                    nonSelectableSkeletonEvidence = skeleton,
                    providerInterfaceAuditEvidence = interfaceAudit,
                    providerSelectionPromotionEvidence = promotion,
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                    providerOperationEvidence = providerOperationEvidence(),
                    runtimeRandomnessEvidence = runtimeRandomnessEvidence(),
                    kdfCalibrationEvidence = kdfCalibrationEvidence(),
                    secureStorageEvidence = secureStorageEvidence(),
                    creationAuthorizationEvidence = creationAuthorizationEvidence(),
                    unlockAuthorizationEvidence = unlockAuthorizationEvidence(),
                ),
            ),
        )
        val evidence = blocked(
            SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                SkaldVaultV1ProviderFactoryIsolationRequest.currentEvidence(
                    providerSelectionResult = selection,
                    providerRegistryIsolationEvidence = registry,
                    nonSelectableSkeletonEvidence = skeleton,
                    providerInterfaceAuditEvidence = interfaceAudit,
                    providerSelectionPromotionEvidence = promotion,
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                    providerOperationEvidence = providerOperationEvidence(),
                    runtimeRandomnessEvidence = runtimeRandomnessEvidence(),
                    kdfCalibrationEvidence = kdfCalibrationEvidence(),
                    secureStorageEvidence = secureStorageEvidence(),
                    creationAuthorizationEvidence = creationAuthorizationEvidence(),
                    unlockAuthorizationEvidence = unlockAuthorizationEvidence(),
                ),
            ),
        )

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerRegistryIsolationEvidenceConsumed)
        assertTrue(evidence.nonSelectableSkeletonEvidenceConsumed)
        assertTrue(evidence.providerInterfaceAuditEvidenceConsumed)
        assertTrue(evidence.providerSelectionPromotionEvidenceConsumed)
        assertTrue(evidence.dependencyBuildEvidenceConsumed)
        assertTrue(evidence.providerCandidatePackagingEvidenceConsumed)
        assertTrue(evidence.authorizationReadinessMatrixEvidenceConsumed)
        assertTrue(evidence.providerOperationEvidenceConsumed)
        assertTrue(evidence.runtimeRandomnessEvidenceConsumed)
        assertTrue(evidence.kdfCalibrationEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.creationAuthorizationEvidenceConsumed)
        assertTrue(evidence.unlockAuthorizationEvidenceConsumed)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderRegistryIsolationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.UnlockAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.UserConsentCannotOverride)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderFactoryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyEvidenceStayRedacted() {
        val request = SkaldVaultV1ProviderFactoryIsolationRequest.forRisk(
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsProviderHandles,
        )
        val result = SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "provider-handle",
            "factory-object",
            "registry-object",
            "crypto-object",
            "byte-material",
            "raw-path",
            "storage-id",
            "secret-value",
            "key-material",
            "random-bytes",
            "opaque-material-alpha",
            "opaque-material-beta",
        )

        forbiddenValues.forEach { raw ->
            assertFalse(request.toString().contains(raw))
            assertFalse(result.toString().contains(raw))
            assertFalse(evidence.toString().contains(raw))
            assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        }
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsFactoryObject)
        assertFalse(evidence.policyTokenEvidence.containsRegistryObject)
        assertFalse(evidence.policyTokenEvidence.containsCryptoObject)
        assertFalse(evidence.policyTokenEvidence.containsByteMaterial)
        assertFalse(evidence.policyTokenEvidence.containsPathOrRootText)
        assertFalse(evidence.policyTokenEvidence.containsStorageIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsSecretMaterial)
    }

    @Test
    fun individualAuditRequestsStayBlockedAndScopedToRequestedRows() {
        SkaldVaultV1ProviderFactoryIsolationTopic.entries.forEach { topic ->
            val evidence = blocked(
                SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderFactoryIsolationRequest.forTopic(topic),
                ),
            )

            assertEquals(listOf(topic), evidence.topicRows.map { it.topic })
            assertTrue(evidence.topicRows.single().modeled)
            assertFalse(evidence.topicRows.single().factoryAvailable)
            assertFalse(evidence.topicRows.single().runtimeConstructionAllowed)
            assertFalse(evidence.topicRows.single().providerRuntimeExposed)
            assertTrue(evidence.riskRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderFactoryIsolationRisk.entries.forEach { risk ->
            val evidence = blocked(
                SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderFactoryIsolationRequest.forRisk(risk),
                ),
            )

            assertEquals(listOf(risk), evidence.riskRows.map { it.risk })
            assertTrue(evidence.riskRows.single().rejected)
            assertFalse(evidence.riskRows.single().canConstruct)
            assertFalse(evidence.riskRows.single().canExposeRuntime)
            assertFalse(evidence.riskRows.single().canExecute)
            assertTrue(evidence.topicRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderFactoryIsolationRequiredProperty.entries.forEach { requiredProperty ->
            val evidence = blocked(
                SkaldVaultV1ProviderFactoryIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderFactoryIsolationRequest.forRequiredProperty(requiredProperty),
                ),
            )

            assertEquals(listOf(requiredProperty), evidence.requiredPropertyRows.map { it.requiredProperty })
            assertTrue(evidence.requiredPropertyRows.single().satisfiedForCurrentState)
            assertFalse(evidence.requiredPropertyRows.single().factoryRuntimeAvailable)
            assertFalse(evidence.requiredPropertyRows.single().nonDisabledConstructionAllowed)
            assertTrue(evidence.topicRows.isNotEmpty())
            assertTrue(evidence.riskRows.isNotEmpty())
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
        result: SkaldVaultV1ProviderFactoryIsolationResult<
            SkaldVaultV1ProviderFactoryIsolationEvidence,
        >,
    ): SkaldVaultV1ProviderFactoryIsolationEvidence =
        assertIs<
            SkaldVaultV1ProviderFactoryIsolationResult.Blocked<
                SkaldVaultV1ProviderFactoryIsolationEvidence,
            >,
        >(result).value

    private fun blockedRegistry(
        result: SkaldVaultV1ProviderRegistryIsolationGuardResult<
            SkaldVaultV1ProviderRegistryIsolationEvidence,
        >,
    ): SkaldVaultV1ProviderRegistryIsolationEvidence =
        assertIs<
            SkaldVaultV1ProviderRegistryIsolationGuardResult.Blocked<
                SkaldVaultV1ProviderRegistryIsolationEvidence,
            >,
        >(result).value

    private fun blockedDependencyBuild(
        result: SkaldVaultV1ProviderDependencyBuildResult<
            SkaldVaultV1ProviderDependencyBuildEvidence,
        >,
    ): SkaldVaultV1ProviderDependencyBuildEvidence =
        assertIs<
            SkaldVaultV1ProviderDependencyBuildResult.Blocked<
                SkaldVaultV1ProviderDependencyBuildEvidence,
            >,
        >(result).value

    private fun blockedPackaging(
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
            SkaldVaultV1AuthorizationReadinessMatrixEvidence,
        >,
    ): SkaldVaultV1AuthorizationReadinessMatrixEvidence =
        assertIs<
            SkaldVaultV1AuthorizationReadinessMatrixResult.Blocked<
                SkaldVaultV1AuthorizationReadinessMatrixEvidence,
            >,
        >(result).value

    private fun blockedInterfaceAudit(
        result: SkaldVaultV1ProviderInterfaceContractAuditResult<
            SkaldVaultV1ProviderInterfaceContractAuditEvidence,
        >,
    ): SkaldVaultV1ProviderInterfaceContractAuditEvidence =
        assertIs<
            SkaldVaultV1ProviderInterfaceContractAuditResult.Blocked<
                SkaldVaultV1ProviderInterfaceContractAuditEvidence,
            >,
        >(result).value

    private fun blockedPromotion(
        result: SkaldVaultV1ProviderSelectionPromotionResult<
            SkaldVaultV1ProviderSelectionPromotionEvidence,
        >,
    ): SkaldVaultV1ProviderSelectionPromotionEvidence =
        assertIs<
            SkaldVaultV1ProviderSelectionPromotionResult.Blocked<
                SkaldVaultV1ProviderSelectionPromotionEvidence,
            >,
        >(result).value

    private fun blockedSkeleton(
        result: SkaldVaultV1NonSelectableProviderSkeletonResult<
            SkaldVaultV1NonSelectableProviderSkeletonEvidence,
        >,
    ): SkaldVaultV1NonSelectableProviderSkeletonEvidence =
        assertIs<
            SkaldVaultV1NonSelectableProviderSkeletonResult.Blocked<
                SkaldVaultV1NonSelectableProviderSkeletonEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1ProviderFactoryIsolationCapability) {
        assertTrue(capability.providerFactoryIsolationModeled)
        assertFalse(capability.providerFactoryExistsForNonDisabledProvider)
        assertFalse(capability.providerFactoryReachableFromSelection)
        assertFalse(capability.providerFactoryReachableFromRegistry)
        assertFalse(capability.providerFactoryCanConstructSkeleton)
        assertFalse(capability.providerFactoryCanConstructCandidate)
        assertFalse(capability.providerFactoryCanConstructProviderRuntime)
        assertFalse(capability.providerFactoryCanExposeProviderHandle)
        assertFalse(capability.providerFactoryCanExposeCryptoObject)
        assertFalse(capability.providerFactoryAcceptsByteMaterial)
        assertFalse(capability.providerFactoryCanExecuteProviderOperations)
        assertFalse(capability.providerFactoryCanRunKat)
        assertFalse(capability.providerFactoryCanUseRandomness)
        assertFalse(capability.providerFactoryCanRunKdf)
        assertFalse(capability.providerFactoryCanRunAead)
        assertFalse(capability.providerFactoryCanWrapKeys)
        assertFalse(capability.providerFactoryCanCreateVault)
        assertFalse(capability.providerFactoryCanUnlockVault)
        assertFalse(capability.providerFactoryCanPersistVault)
        assertFalse(capability.providerFactoryAdded)
        assertFalse(capability.providerRegistryEnabled)
        assertFalse(capability.providerRuntimeInstantiable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelectable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerKatExecutionAvailable)
        assertFalse(capability.runtimeRandomnessAvailable)
        assertFalse(capability.kdfExecutionAvailable)
        assertFalse(capability.aeadExecutionAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertDisabled(evidence: SkaldVaultV1ProviderFactoryIsolationEvidence) {
        assertTrue(evidence.providerFactoryIsolationModeled)
        assertFalse(evidence.providerFactoryExistsForNonDisabledProvider)
        assertFalse(evidence.providerFactoryReachableFromSelection)
        assertFalse(evidence.providerFactoryReachableFromRegistry)
        assertFalse(evidence.providerFactoryCanConstructSkeleton)
        assertFalse(evidence.providerFactoryCanConstructCandidate)
        assertFalse(evidence.providerFactoryCanConstructProviderRuntime)
        assertFalse(evidence.providerFactoryCanExposeProviderHandle)
        assertFalse(evidence.providerFactoryCanExposeCryptoObject)
        assertFalse(evidence.providerFactoryAcceptsByteMaterial)
        assertFalse(evidence.providerFactoryCanExecuteProviderOperations)
        assertFalse(evidence.providerFactoryCanRunKat)
        assertFalse(evidence.providerFactoryCanUseRandomness)
        assertFalse(evidence.providerFactoryCanRunKdf)
        assertFalse(evidence.providerFactoryCanRunAead)
        assertFalse(evidence.providerFactoryCanWrapKeys)
        assertFalse(evidence.providerFactoryCanCreateVault)
        assertFalse(evidence.providerFactoryCanUnlockVault)
        assertFalse(evidence.providerFactoryCanPersistVault)
        assertFalse(evidence.providerFactoryAdded)
        assertFalse(evidence.providerRegistryContainsCandidate)
        assertFalse(evidence.providerRegistryContainsSkeleton)
        assertFalse(evidence.providerRegistryEnabled)
        assertFalse(evidence.providerRuntimeInstantiable)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.providerKatExecutionAvailable)
        assertFalse(evidence.runtimeRandomnessAvailable)
        assertFalse(evidence.kdfExecutionAvailable)
        assertFalse(evidence.aeadExecutionAvailable)
        assertFalse(evidence.hkdfHmacExecutionAvailable)
        assertFalse(evidence.keyWrappingAvailable)
        assertFalse(evidence.vaultCreationAvailable)
        assertFalse(evidence.vaultUnlockAvailable)
        assertFalse(evidence.vaultPersistenceAvailable)
        assertFalse(evidence.mainnetAvailable)
    }
}
