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
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationGuardRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationGuardResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationRequiredProperty
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationRisk
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderRegistryIsolationTopic
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

class VaultProviderRegistryIsolationGuardTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndRegistryCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                SkaldVaultV1ProviderRegistryIsolationGuardRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-registry-isolation-guard-v1",
            SkaldVaultV1ProviderRegistryIsolationGuardPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderRegistryIsolationGuardPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderRegistryIsolationStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderRegistryIsolationSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerRegistryIsolationGuardModeled)
        assertTrue(evidence.providerRegistryIsolationGuardStillDisabled)
        assertTrue(evidence.providerRegistryIsolationConfirmsDisabledProviderOnly)
        assertTrue(evidence.providerRegistryIsolationExcludesSkeleton)
        assertTrue(evidence.providerRegistryIsolationExcludesCandidateProviders)
        assertTrue(evidence.providerRegistryIsolationExcludesFactories)
        assertTrue(evidence.providerRegistryIsolationDoesNotRegisterProvider)
        assertTrue(evidence.providerRegistryIsolationDoesNotEnableProviderSelection)
        assertTrue(evidence.providerRegistryIsolationDoesNotRunCrypto)
        assertTrue(evidence.providerRegistryIsolationDoesNotEnableCreation)
        assertTrue(evidence.providerRegistryIsolationDoesNotEnableUnlock)
        assertTrue(evidence.providerRegistryIsolationDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun registryTopicCoverageModelsEveryIsolationTopicAndKeepsCandidatesExcluded() {
        val summary = SkaldVaultV1ProviderRegistryIsolationGuardPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                SkaldVaultV1ProviderRegistryIsolationGuardRequest.currentEvidence(),
            ),
        )
        val rows = evidence.topicRows.associateBy { it.topic }

        assertEquals(SkaldVaultV1ProviderRegistryIsolationStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderRegistryIsolationTopic.entries.toSet(), summary.topics)
        assertEquals(SkaldVaultV1ProviderRegistryIsolationRisk.entries.toSet(), summary.risks)
        assertEquals(
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.entries.toSet(),
            summary.requiredProperties,
        )
        assertEquals(SkaldVaultV1ProviderRegistryIsolationBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderRegistryIsolationRedactionClass.entries.toSet(),
            summary.redactionClasses,
        )
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.disabledProviderOnly)
        assertTrue(summary.excludesSkeletonAndCandidates)
        assertTrue(summary.blocksRegistryPromotion)

        assertEquals(SkaldVaultV1ProviderRegistryIsolationTopic.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.evidenceOnly)
            assertFalse(row.nonDisabledSelectionAllowed)
            assertFalse(row.runtimeInstantiationAllowed)
            assertContains(row.statuses, SkaldVaultV1ProviderRegistryIsolationStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderRegistryIsolationStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
            assertContains(row.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled)
        }
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationTopic.ActiveProviderSelectionRegistry)
                .excludedFromRegistry,
        )
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationTopic.DisabledProviderSelected)
                .excludedFromRegistry,
        )
        assertTrue(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationTopic.DisabledProviderSelected)
                .registryEntryAllowed,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationTopic.NonSelectableSkeletonExcluded).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.CandidatePackagingEvidenceExcludedFromRegistry,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.DependencyBuildEvidenceExcludedFromRegistry,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.InterfaceAuditEvidenceExcludedFromRegistry,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.PromotionBlockerEvidenceExcludedFromRegistry,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.AuthorizationReadinessMatrixEvidenceExcludedFromRegistry,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.TestOnlyEvidenceExcludedFromProductionRegistry,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ReleaseReviewMissing,
        )
    }

    @Test
    fun registryRiskCoverageRejectsEveryNonDisabledProviderPath() {
        val evidence = blocked(
            SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                SkaldVaultV1ProviderRegistryIsolationGuardRequest.currentEvidence(),
            ),
        )
        val rows = evidence.riskRows.associateBy { it.risk }

        assertEquals(SkaldVaultV1ProviderRegistryIsolationRisk.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.rejected)
            assertFalse(row.accepted)
            assertFalse(row.canCreateRegistryEntry)
            assertFalse(row.canInstantiate)
            assertFalse(row.canExecute)
            assertFalse(row.canChangeSelectability)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.SkeletonReferencedBySelectionCode).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.CandidateFamilyReferencedBySelectionCode)
                .blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.DependencyBuildEvidenceTreatedAsRegistryEntry)
                .blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.PackagingEvidenceTreatedAsRegistryEntry)
                .blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.InterfaceAuditTreatedAsRegistryEntry).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.MatrixEvidenceTreatedAsRegistryEntry).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.ProductionProviderSelectableAccidentallyTrue)
                .blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProductionProviderSelectableFalse,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryContainsNonDisabledProviderId).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryCreatesProviderInstance).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderConstructorsExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryExposesProviderHandles).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryHandlesRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryExposesCryptoObjects).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.CryptoObjectsRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryCallsProviderOperations).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationExecutionForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryRunsKats).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.KatExecutionUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryReachesRandomnessKdfAead).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRisk.RegistryEnablesVaultCreationUnlockPersistence,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.CreationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRisk.RegistryEnablesMainnet).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun requiredPropertyCoverageKeepsCurrentRegistryDisabledOnly() {
        val evidence = blocked(
            SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                SkaldVaultV1ProviderRegistryIsolationGuardRequest.currentEvidence(),
            ),
        )
        val rows = evidence.requiredPropertyRows.associateBy { it.requiredProperty }

        assertEquals(SkaldVaultV1ProviderRegistryIsolationRequiredProperty.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.satisfiedForCurrentRegistry)
            assertTrue(row.futureExplicitReviewRequired)
            assertFalse(row.runtimeAvailable)
            assertFalse(row.nonDisabledSelectionAllowed)
            assertContains(row.statuses, SkaldVaultV1ProviderRegistryIsolationStatus.RegistryIsolated)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistrySelectsOnlyDisabledProvider,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoCandidateEntries,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoSkeletonEntries,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoProviderFactories,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.FactoriesExcludedFromRegistry,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryCannotCallKdfAeadHkdfHmac,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty
                    .CurrentRegistryCannotPromotePackagingSkeletonAuditMatrixEvidence,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderRegistryIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride,
            ).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.UserConsentCannotOverride,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderRegistryIsolationRequiredProperty.MainnetDisabled).blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun existingEvidenceCanBeSuppliedButCannotPromoteRegistryOrReachSkeleton() {
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
        val evidence = blocked(
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

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
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
        assertContains(evidence.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.SecureStorageAuthorizationBlocked,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.UnlockAuthorizationBlocked)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.UserConsentCannotOverride)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyEvidenceStayRedacted() {
        val request = SkaldVaultV1ProviderRegistryIsolationGuardRequest.forRisk(
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryExposesProviderHandles,
        )
        val result = SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "provider-handle",
            "registry-object",
            "factory-object",
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
        assertFalse(evidence.policyTokenEvidence.containsRegistryObject)
        assertFalse(evidence.policyTokenEvidence.containsFactoryObject)
        assertFalse(evidence.policyTokenEvidence.containsCryptoObject)
        assertFalse(evidence.policyTokenEvidence.containsByteMaterial)
        assertFalse(evidence.policyTokenEvidence.containsPathOrRootText)
        assertFalse(evidence.policyTokenEvidence.containsStorageIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsSecretMaterial)
    }

    @Test
    fun individualAuditRequestsStayBlockedAndScopedToRequestedRows() {
        SkaldVaultV1ProviderRegistryIsolationTopic.entries.forEach { topic ->
            val evidence = blocked(
                SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                    SkaldVaultV1ProviderRegistryIsolationGuardRequest.forTopic(topic),
                ),
            )

            assertEquals(listOf(topic), evidence.topicRows.map { it.topic })
            assertTrue(evidence.topicRows.single().modeled)
            assertFalse(evidence.topicRows.single().nonDisabledSelectionAllowed)
            assertFalse(evidence.topicRows.single().runtimeInstantiationAllowed)
            assertTrue(evidence.riskRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderRegistryIsolationRisk.entries.forEach { risk ->
            val evidence = blocked(
                SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                    SkaldVaultV1ProviderRegistryIsolationGuardRequest.forRisk(risk),
                ),
            )

            assertEquals(listOf(risk), evidence.riskRows.map { it.risk })
            assertTrue(evidence.riskRows.single().rejected)
            assertFalse(evidence.riskRows.single().canCreateRegistryEntry)
            assertFalse(evidence.riskRows.single().canExecute)
            assertTrue(evidence.topicRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderRegistryIsolationRequiredProperty.entries.forEach { requiredProperty ->
            val evidence = blocked(
                SkaldVaultV1ProviderRegistryIsolationGuardPolicy.evaluate(
                    SkaldVaultV1ProviderRegistryIsolationGuardRequest.forRequiredProperty(requiredProperty),
                ),
            )

            assertEquals(listOf(requiredProperty), evidence.requiredPropertyRows.map { it.requiredProperty })
            assertTrue(evidence.requiredPropertyRows.single().satisfiedForCurrentRegistry)
            assertFalse(evidence.requiredPropertyRows.single().runtimeAvailable)
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

    private fun assertDisabled(capability: SkaldVaultV1ProviderRegistryIsolationCapability) {
        assertTrue(capability.providerRegistryIsolationModeled)
        assertTrue(capability.registryContainsOnlyDisabledProvider)
        assertFalse(capability.registryContainsProviderSkeleton)
        assertFalse(capability.registryContainsCandidateProvider)
        assertFalse(capability.registryContainsProviderFactory)
        assertFalse(capability.registryCreatesProviderInstances)
        assertFalse(capability.registryCanSelectNonDisabledProvider)
        assertFalse(capability.registryCanSetProductionProviderSelectable)
        assertFalse(capability.registryCanPromoteCandidate)
        assertFalse(capability.registryCanExecuteProviderOperations)
        assertFalse(capability.registryCanRunKat)
        assertFalse(capability.registryCanUseRandomness)
        assertFalse(capability.registryCanRunKdf)
        assertFalse(capability.registryCanRunAead)
        assertFalse(capability.registryCanWrapKeys)
        assertFalse(capability.registryCanCreateVault)
        assertFalse(capability.registryCanUnlockVault)
        assertFalse(capability.registryCanPersistVault)
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

    private fun assertDisabled(evidence: SkaldVaultV1ProviderRegistryIsolationEvidence) {
        assertTrue(evidence.providerRegistryIsolationModeled)
        assertTrue(evidence.registryContainsOnlyDisabledProvider)
        assertFalse(evidence.registryContainsProviderSkeleton)
        assertFalse(evidence.registryContainsCandidateProvider)
        assertFalse(evidence.registryContainsProviderFactory)
        assertFalse(evidence.registryCreatesProviderInstances)
        assertFalse(evidence.registryCanSelectNonDisabledProvider)
        assertFalse(evidence.registryCanSetProductionProviderSelectable)
        assertFalse(evidence.registryCanPromoteCandidate)
        assertFalse(evidence.registryCanExecuteProviderOperations)
        assertFalse(evidence.registryCanRunKat)
        assertFalse(evidence.registryCanUseRandomness)
        assertFalse(evidence.registryCanRunKdf)
        assertFalse(evidence.registryCanRunAead)
        assertFalse(evidence.registryCanWrapKeys)
        assertFalse(evidence.registryCanCreateVault)
        assertFalse(evidence.registryCanUnlockVault)
        assertFalse(evidence.registryCanPersistVault)
        assertFalse(evidence.providerFactoryAdded)
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
