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
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderFactoryIsolationResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationRisk
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationTopic
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
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessResult
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

class VaultProviderOperationDispatchIsolationBoundaryTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndDispatchCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                SkaldVaultV1ProviderOperationDispatchIsolationRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-operation-dispatch-isolation-boundary-v1",
            SkaldVaultV1ProviderOperationDispatchIsolationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderOperationDispatchIsolationPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerOperationDispatchIsolationBoundaryModeled)
        assertTrue(evidence.providerOperationDispatchIsolationStillDisabled)
        assertTrue(evidence.providerOperationDispatchIsolationConfirmsNoDispatcher)
        assertTrue(evidence.providerOperationDispatchIsolationExcludesSkeletonDispatch)
        assertTrue(evidence.providerOperationDispatchIsolationExcludesCandidateDispatch)
        assertTrue(evidence.providerOperationDispatchIsolationExcludesRuntimeProviderDispatch)
        assertTrue(evidence.providerOperationDispatchIsolationDoesNotRegisterProvider)
        assertTrue(evidence.providerOperationDispatchIsolationDoesNotEnableProviderSelection)
        assertTrue(evidence.providerOperationDispatchIsolationDoesNotRunCrypto)
        assertTrue(evidence.providerOperationDispatchIsolationDoesNotEnableCreation)
        assertTrue(evidence.providerOperationDispatchIsolationDoesNotEnableUnlock)
        assertTrue(evidence.providerOperationDispatchIsolationDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun dispatchTopicCoverageModelsEveryDispatchSurfaceAndKeepsDispatchersUnavailable() {
        val summary = SkaldVaultV1ProviderOperationDispatchIsolationPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                SkaldVaultV1ProviderOperationDispatchIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.topicRows.associateBy { it.topic }

        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationTopic.entries.toSet(), summary.topics)
        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationRisk.entries.toSet(), summary.risks)
        assertEquals(
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.entries.toSet(),
            summary.requiredProperties,
        )
        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass.entries.toSet(),
            summary.redactionClasses,
        )
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.confirmsNoDispatcher)
        assertTrue(summary.blocksDispatch)
        assertTrue(summary.blocksProviderSelection)

        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationTopic.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.evidenceOnly)
            assertFalse(row.dispatcherAvailable)
            assertFalse(row.runtimeDispatchAllowed)
            assertFalse(row.providerRuntimeInvoked)
            assertContains(row.statuses, SkaldVaultV1ProviderOperationDispatchIsolationStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderOperationDispatchIsolationStatus.EvidenceOnly)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderOperationDispatchIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled)
        }
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationDispatchSurface)
                .dispatchExcluded,
        )
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.DisabledProviderDispatchBehavior)
                .dispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationDispatchSurface)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoProviderOperationDispatcher,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.FactoryDispatchExcluded).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.FactoryDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.RegistryDispatchExcluded).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RegistryDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.SkeletonDispatchExcluded).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.CandidateProviderDispatchExcluded)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidateDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.DependencyBuildDispatchExcluded)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.CandidatePackagingDispatchExcluded)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidatePackagingDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.InterfaceAuditDispatchExcluded)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.PromotionBlockerDispatchExcluded)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationTopic.AuthorizationReadinessMatrixDispatchExcluded,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationAuthorizationDispatchExcluded,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationTopic.PersistenceReadinessDispatchExcluded)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ReleaseReviewMissing,
        )
    }

    @Test
    fun dispatchRiskCoverageRejectsEveryProviderOperationRoute() {
        val evidence = blocked(
            SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                SkaldVaultV1ProviderOperationDispatchIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.riskRows.associateBy { it.risk }

        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationRisk.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.rejected)
            assertFalse(row.accepted)
            assertFalse(row.canDispatch)
            assertFalse(row.canInvokeRuntime)
            assertFalse(row.canExecute)
            assertFalse(row.canChangeSelectability)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesNonDisabledProvider)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesSkeletonProvider)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesCandidateProvider)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidateDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesProviderFactory)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderFactoryIsolationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesProviderRegistry)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderRegistryIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRisk
                    .DispatchTreatsDependencyBuildEvidenceAsExecutableOperation,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsProviderHandles)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderHandlesRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsCryptoObjects).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CryptoObjectsRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsByteMaterial).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchImportsTinkBouncyJavaxCrypto)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TinkBouncyJavaxImportsForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchCallsSecureRandom).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SecureRandomForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchRunsKats).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KatExecutionUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchCallsProviderOperations)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationExecutionForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchReachesRandomnessKdfAead)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchComputesHeaderCommitment)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.HeaderCommitmentUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEncryptsDecryptsRecords)
                .blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RecordCryptoUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchWrapsUnwrapsKeys).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KeyWrappingUnavailable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEnablesVaultCreationUnlockPersistence,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEnablesMainnet).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun requiredPropertyCoverageKeepsDispatchersAbsentAndUnreachable() {
        val evidence = blocked(
            SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                SkaldVaultV1ProviderOperationDispatchIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.requiredPropertyRows.associateBy { it.requiredProperty }

        assertEquals(SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.satisfiedForCurrentState)
            assertTrue(row.futureExplicitReviewRequired)
            assertFalse(row.dispatcherRuntimeAvailable)
            assertFalse(row.providerOperationDispatchAllowed)
            assertContains(row.statuses, SkaldVaultV1ProviderOperationDispatchIsolationStatus.DispatchIsolated)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherExistsForNonDisabledProviders,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherReachableFromProviderSelection,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherReachableFromRegistryIsolation,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderRegistryIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherReachableFromFactoryIsolation,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderFactoryIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherReachableFromSkeletonEvidence,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherReachableFromCreationUnlockStorageReadiness,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherAcceptsByteMaterial,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherCanRunKdfAeadHkdfHmac,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherCanComputeHeaderCommitments,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.HeaderCommitmentUnavailable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherCanEncryptDecryptRecords,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RecordCryptoUnavailable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .NoProviderOperationDispatcherCanWrapUnwrapKeys,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KeyWrappingUnavailable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                    .WarningUserConsentTestOnlyCannotOverride,
            ).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UserConsentCannotOverride,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.MainnetDisabled).blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun existingEvidenceCanBeSuppliedButCannotDispatchToProviderRuntime() {
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
        val providerOperation = providerOperationEvidence()
        val randomness = runtimeRandomnessEvidence()
        val kdf = kdfCalibrationEvidence()
        val secureStorage = secureStorageEvidence()
        val creation = creationAuthorizationEvidence()
        val unlock = unlockAuthorizationEvidence()
        val persistence = persistenceReadinessEvidence()
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
                    providerOperationEvidence = providerOperation,
                    runtimeRandomnessEvidence = randomness,
                    kdfCalibrationEvidence = kdf,
                    secureStorageEvidence = secureStorage,
                    creationAuthorizationEvidence = creation,
                    unlockAuthorizationEvidence = unlock,
                ),
            ),
        )
        val factory = blockedFactory(
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
                    providerOperationEvidence = providerOperation,
                    runtimeRandomnessEvidence = randomness,
                    kdfCalibrationEvidence = kdf,
                    secureStorageEvidence = secureStorage,
                    creationAuthorizationEvidence = creation,
                    unlockAuthorizationEvidence = unlock,
                ),
            ),
        )
        val evidence = blocked(
            SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                SkaldVaultV1ProviderOperationDispatchIsolationRequest.currentEvidence(
                    providerSelectionResult = selection,
                    providerFactoryIsolationEvidence = factory,
                    providerRegistryIsolationEvidence = registry,
                    nonSelectableSkeletonEvidence = skeleton,
                    providerInterfaceAuditEvidence = interfaceAudit,
                    providerSelectionPromotionEvidence = promotion,
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                    providerOperationEvidence = providerOperation,
                    runtimeRandomnessEvidence = randomness,
                    kdfCalibrationEvidence = kdf,
                    secureStorageEvidence = secureStorage,
                    creationAuthorizationEvidence = creation,
                    unlockAuthorizationEvidence = unlock,
                    persistenceReadinessEvidence = persistence,
                ),
            ),
        )

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerFactoryIsolationEvidenceConsumed)
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
        assertTrue(evidence.persistenceReadinessEvidenceConsumed)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderFactoryIsolationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderRegistryIsolationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidatePackagingDispatchExcluded,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UnlockAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UserConsentCannotOverride)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyEvidenceStayRedacted() {
        val request = SkaldVaultV1ProviderOperationDispatchIsolationRequest.forRisk(
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsProviderHandles,
        )
        val result = SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "provider-handle",
            "dispatcher-object",
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
        assertFalse(evidence.policyTokenEvidence.containsDispatcherObject)
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
        SkaldVaultV1ProviderOperationDispatchIsolationTopic.entries.forEach { topic ->
            val evidence = blocked(
                SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderOperationDispatchIsolationRequest.forTopic(topic),
                ),
            )

            assertEquals(listOf(topic), evidence.topicRows.map { it.topic })
            assertTrue(evidence.topicRows.single().modeled)
            assertFalse(evidence.topicRows.single().dispatcherAvailable)
            assertFalse(evidence.topicRows.single().runtimeDispatchAllowed)
            assertFalse(evidence.topicRows.single().providerRuntimeInvoked)
            assertTrue(evidence.riskRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderOperationDispatchIsolationRisk.entries.forEach { risk ->
            val evidence = blocked(
                SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderOperationDispatchIsolationRequest.forRisk(risk),
                ),
            )

            assertEquals(listOf(risk), evidence.riskRows.map { it.risk })
            assertTrue(evidence.riskRows.single().rejected)
            assertFalse(evidence.riskRows.single().canDispatch)
            assertFalse(evidence.riskRows.single().canInvokeRuntime)
            assertFalse(evidence.riskRows.single().canExecute)
            assertTrue(evidence.topicRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.entries.forEach { requiredProperty ->
            val evidence = blocked(
                SkaldVaultV1ProviderOperationDispatchIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderOperationDispatchIsolationRequest.forRequiredProperty(requiredProperty),
                ),
            )

            assertEquals(listOf(requiredProperty), evidence.requiredPropertyRows.map { it.requiredProperty })
            assertTrue(evidence.requiredPropertyRows.single().satisfiedForCurrentState)
            assertFalse(evidence.requiredPropertyRows.single().dispatcherRuntimeAvailable)
            assertFalse(evidence.requiredPropertyRows.single().providerOperationDispatchAllowed)
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

    private fun persistenceReadinessEvidence(): SkaldVaultV1VaultPersistenceReadinessEvidence =
        assertIs<
            SkaldVaultV1VaultPersistenceReadinessResult.Blocked<
                SkaldVaultV1VaultPersistenceReadinessEvidence,
            >,
        >(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.noEvidence(),
            ),
        ).value

    private fun blocked(
        result: SkaldVaultV1ProviderOperationDispatchIsolationResult<
            SkaldVaultV1ProviderOperationDispatchIsolationEvidence,
        >,
    ): SkaldVaultV1ProviderOperationDispatchIsolationEvidence =
        assertIs<
            SkaldVaultV1ProviderOperationDispatchIsolationResult.Blocked<
                SkaldVaultV1ProviderOperationDispatchIsolationEvidence,
            >,
        >(result).value

    private fun blockedFactory(
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

    private fun assertDisabled(capability: SkaldVaultV1ProviderOperationDispatchIsolationCapability) {
        assertTrue(capability.providerOperationDispatchIsolationModeled)
        assertFalse(capability.providerOperationDispatcherExistsForNonDisabledProvider)
        assertFalse(capability.providerOperationDispatcherReachableFromSelection)
        assertFalse(capability.providerOperationDispatcherReachableFromRegistry)
        assertFalse(capability.providerOperationDispatcherReachableFromFactory)
        assertFalse(capability.providerOperationDispatcherCanInvokeSkeleton)
        assertFalse(capability.providerOperationDispatcherCanInvokeCandidate)
        assertFalse(capability.providerOperationDispatcherCanInvokeProviderRuntime)
        assertFalse(capability.providerOperationDispatcherCanExposeProviderHandle)
        assertFalse(capability.providerOperationDispatcherCanExposeCryptoObject)
        assertFalse(capability.providerOperationDispatcherAcceptsByteMaterial)
        assertFalse(capability.providerOperationDispatcherCanExecuteProviderOperations)
        assertFalse(capability.providerOperationDispatcherCanRunKat)
        assertFalse(capability.providerOperationDispatcherCanUseRandomness)
        assertFalse(capability.providerOperationDispatcherCanRunKdf)
        assertFalse(capability.providerOperationDispatcherCanRunAead)
        assertFalse(capability.providerOperationDispatcherCanComputeHeaderCommitment)
        assertFalse(capability.providerOperationDispatcherCanEncryptRecords)
        assertFalse(capability.providerOperationDispatcherCanDecryptRecords)
        assertFalse(capability.providerOperationDispatcherCanWrapKeys)
        assertFalse(capability.providerOperationDispatcherCanCreateVault)
        assertFalse(capability.providerOperationDispatcherCanUnlockVault)
        assertFalse(capability.providerOperationDispatcherCanPersistVault)
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

    private fun assertDisabled(evidence: SkaldVaultV1ProviderOperationDispatchIsolationEvidence) {
        assertTrue(evidence.providerOperationDispatchIsolationModeled)
        assertFalse(evidence.providerOperationDispatcherAdded)
        assertFalse(evidence.providerOperationDispatcherExistsForNonDisabledProvider)
        assertFalse(evidence.providerOperationDispatcherReachableFromSelection)
        assertFalse(evidence.providerOperationDispatcherReachableFromRegistry)
        assertFalse(evidence.providerOperationDispatcherReachableFromFactory)
        assertFalse(evidence.providerOperationDispatcherCanInvokeSkeleton)
        assertFalse(evidence.providerOperationDispatcherCanInvokeCandidate)
        assertFalse(evidence.providerOperationDispatcherCanInvokeProviderRuntime)
        assertFalse(evidence.providerOperationDispatcherCanExposeProviderHandle)
        assertFalse(evidence.providerOperationDispatcherCanExposeCryptoObject)
        assertFalse(evidence.providerOperationDispatcherAcceptsByteMaterial)
        assertFalse(evidence.providerOperationDispatcherCanExecuteProviderOperations)
        assertFalse(evidence.providerOperationDispatcherCanRunKat)
        assertFalse(evidence.providerOperationDispatcherCanUseRandomness)
        assertFalse(evidence.providerOperationDispatcherCanRunKdf)
        assertFalse(evidence.providerOperationDispatcherCanRunAead)
        assertFalse(evidence.providerOperationDispatcherCanComputeHeaderCommitment)
        assertFalse(evidence.providerOperationDispatcherCanEncryptRecords)
        assertFalse(evidence.providerOperationDispatcherCanDecryptRecords)
        assertFalse(evidence.providerOperationDispatcherCanWrapKeys)
        assertFalse(evidence.providerOperationDispatcherCanCreateVault)
        assertFalse(evidence.providerOperationDispatcherCanUnlockVault)
        assertFalse(evidence.providerOperationDispatcherCanPersistVault)
        assertFalse(evidence.providerFactoryAdded)
        assertFalse(evidence.providerFactoryExistsForNonDisabledProvider)
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
        assertFalse(evidence.headerCommitmentExecutionAvailable)
        assertFalse(evidence.recordCryptoExecutionAvailable)
        assertFalse(evidence.keyWrappingAvailable)
        assertFalse(evidence.vaultCreationAvailable)
        assertFalse(evidence.vaultUnlockAvailable)
        assertFalse(evidence.vaultPersistenceAvailable)
        assertFalse(evidence.mainnetAvailable)
    }
}
