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
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationRisk
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatExecutionIsolationTopic
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationDispatchIsolationResult
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
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import com.libertasprimordium.skald.security.commonProviderKatContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultProviderKatExecutionIsolationBoundaryTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndKatCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                SkaldVaultV1ProviderKatExecutionIsolationRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-kat-execution-isolation-boundary-v1",
            SkaldVaultV1ProviderKatExecutionIsolationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderKatExecutionIsolationPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerKatExecutionIsolationBoundaryModeled)
        assertTrue(evidence.providerKatExecutionIsolationStillDisabled)
        assertTrue(evidence.providerKatExecutionIsolationConfirmsNoKatExecutor)
        assertTrue(evidence.providerKatExecutionIsolationExcludesSkeletonKat)
        assertTrue(evidence.providerKatExecutionIsolationExcludesCandidateKat)
        assertTrue(evidence.providerKatExecutionIsolationExcludesRuntimeProviderKat)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotRegisterProvider)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotEnableProviderSelection)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotRunCrypto)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotRunKat)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotEnableCreation)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotEnableUnlock)
        assertTrue(evidence.providerKatExecutionIsolationDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun katTopicCoverageModelsEveryKatSurfaceAndKeepsExecutorsUnavailable() {
        val summary = SkaldVaultV1ProviderKatExecutionIsolationPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                SkaldVaultV1ProviderKatExecutionIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.topicRows.associateBy { it.topic }

        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationTopic.entries.toSet(), summary.topics)
        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationRisk.entries.toSet(), summary.risks)
        assertEquals(
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.entries.toSet(),
            summary.requiredProperties,
        )
        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderKatExecutionIsolationRedactionClass.entries.toSet(),
            summary.redactionClasses,
        )
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.confirmsNoKatExecutor)
        assertTrue(summary.blocksKatExecution)
        assertTrue(summary.blocksProviderSelection)

        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationTopic.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.evidenceOnly)
            assertFalse(row.katExecutorAvailable)
            assertFalse(row.runtimeKatAllowed)
            assertFalse(row.providerRuntimeInvoked)
            assertFalse(row.katEvidenceAuthorizesProduction)
            assertContains(row.statuses, SkaldVaultV1ProviderKatExecutionIsolationStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderKatExecutionIsolationStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderKatExecutionIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
        }
        assertFalse(rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatExecutionSurface).katExcluded)
        assertFalse(rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.DisabledProviderKatBehavior).katExcluded)
        assertFalse(rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatContractEvidence).katExcluded)
        assertFalse(rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.TestProviderKatHarnessEvidence).katExcluded)
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.PublicNonWalletVectorDocumentationEvidence)
                .katExcluded,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationTopic
                    .CanonicalHeaderHkdfHmacVectorDocumentationEvidence,
            ).katExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatExecutionSurface).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoProviderKatExecutor,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatContractEvidence).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.KatContractEvidenceNonAuthorizing,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.TestProviderKatHarnessEvidence).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestProviderKatHarnessTestOnly,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationTopic.PublicNonWalletVectorDocumentationEvidence,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.FactoryKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.FactoryKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.RegistryKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.RegistryKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.DispatchKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.DispatchKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.SkeletonKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.SkeletonKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.CandidateProviderKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidateKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.DependencyBuildKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.CandidatePackagingKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidatePackagingKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.InterfaceAuditKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.PromotionBlockerKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderOperationAuthorizationKatExcluded)
                .blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationTopic.PersistenceReadinessKatExcluded).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.PersistenceReadinessBlocked,
        )
    }

    @Test
    fun katRiskCoverageRejectsEveryExecutionAndPromotionRoute() {
        val evidence = blocked(
            SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                SkaldVaultV1ProviderKatExecutionIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.riskRows.associateBy { it.risk }

        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationRisk.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.rejected)
            assertFalse(row.accepted)
            assertFalse(row.canRunKat)
            assertFalse(row.canInvokeRuntime)
            assertFalse(row.canExecute)
            assertFalse(row.canChangeSelectability)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesNonDisabledProvider).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesSkeletonProvider).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.SkeletonKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesCandidateProvider).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidateKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderFactory).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderFactoryIsolationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderRegistry).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderRegistryIsolationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderDispatcher).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationDispatchIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRisk
                    .KatTreatsDependencyBuildEvidenceAsExecutableProvider,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsPublicVectorDocsAsProductionApproval)
                .blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRisk
                    .KatTreatsCanonicalDocsAsRuntimeKdfHkdfHmacExecution,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.CanonicalVectorDocumentationNonExecutable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsProviderHandles).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderHandlesRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsCryptoObjects).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.CryptoObjectsRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsByteMaterial).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatImportsTinkBouncyJavaxCrypto).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.TinkBouncyJavaxImportsForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatCallsSecureRandom).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.SecureRandomForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatRunsProviderOperations).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationExecutionForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatReachesRandomnessKdfAead).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatComputesHeaderCommitment).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.HeaderCommitmentUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEncryptsDecryptsRecords).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.RecordCryptoUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatWrapsUnwrapsKeys).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.KeyWrappingUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAuthorizesProviderSelection).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionAuthorizationUnavailable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAuthorizesProductionProviderSelectableTrue,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProductionProviderSelectableAuthorizationUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEnablesMainnet).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun requiredPropertyCoverageKeepsKatExecutorsAbsentAndNonAuthorizing() {
        val evidence = blocked(
            SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                SkaldVaultV1ProviderKatExecutionIsolationRequest.currentEvidence(),
            ),
        )
        val rows = evidence.requiredPropertyRows.associateBy { it.requiredProperty }

        assertEquals(SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.satisfiedForCurrentState)
            assertTrue(row.futureExplicitReviewRequired)
            assertFalse(row.katExecutorRuntimeAvailable)
            assertFalse(row.providerKatExecutionAllowed)
            assertContains(row.statuses, SkaldVaultV1ProviderKatExecutionIsolationStatus.KatExecutionIsolated)
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorExistsForNonDisabledProviders,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorReachableFromProviderSelection,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorReachableFromRegistryIsolation,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderRegistryIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorReachableFromFactoryIsolation,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderFactoryIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorReachableFromDispatchIsolation,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationDispatchIsolationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorReachableFromPublicVectorDocs,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorReachableFromCreationUnlockStorageReadiness,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.PersistenceReadinessBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.NoProviderKatExecutorAcceptsByteMaterial,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.NoProviderKatExecutorCanRunKdfAeadHkdfHmac,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorCanComputeHeaderCommitments,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.HeaderCommitmentUnavailable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                    .NoProviderKatExecutorCanConvertPublicVectorDocsIntoProductionReadiness,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.CanonicalVectorDocumentationNonExecutable,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride,
            ).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.UserConsentCannotOverride,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.MainnetDisabled).blockers,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled,
        )
    }

    @Test
    fun existingEvidenceAndKatDocsCanBeSuppliedButCannotAuthorizeRuntimeProviderUse() {
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
        val dispatch = blockedDispatch(
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
        val evidence = blocked(
            SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                SkaldVaultV1ProviderKatExecutionIsolationRequest.currentEvidence(
                    providerSelectionResult = selection,
                    providerOperationDispatchIsolationEvidence = dispatch,
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
                    providerKatContractEvidence = commonProviderKatContract(),
                    productionProviderAcceptanceEvidence = commonProductionProviderAcceptanceContract().assess(),
                    testProviderKatHarnessEvidence = external(
                        SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind
                            .TestProviderKatHarnessConcept,
                        "test-provider-kat-harness-concept",
                    ),
                    publicVectorDocumentationEvidence = external(
                        SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind
                            .PublicNonWalletVectorDocumentation,
                        "public-non-wallet-vector-docs",
                    ),
                    canonicalVectorDocumentationEvidence = external(
                        SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind
                            .CanonicalHeaderHkdfHmacVectorDocumentation,
                        "canonical-header-hkdf-hmac-vector-docs",
                    ),
                ),
            ),
        )

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerOperationDispatchIsolationEvidenceConsumed)
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
        assertTrue(evidence.providerKatContractEvidenceConsumed)
        assertTrue(evidence.productionProviderAcceptanceEvidenceConsumed)
        assertTrue(evidence.testProviderKatHarnessEvidenceConsumed)
        assertTrue(evidence.publicVectorDocumentationEvidenceConsumed)
        assertTrue(evidence.canonicalVectorDocumentationEvidenceConsumed)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationDispatchIsolationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderFactoryIsolationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderRegistryIsolationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.SkeletonKatExcluded)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidatePackagingKatExcluded)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.AuthorizationReadinessMatrixBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.UnlockAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.PersistenceReadinessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.KatContractEvidenceNonAuthorizing)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestProviderKatHarnessTestOnly)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.CanonicalVectorDocumentationNonExecutable)
        assertContains(evidence.blockers, SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyEvidenceStayRedacted() {
        val externalEvidence = external(
            SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind.TestProviderKatHarnessConcept,
            "safe-test-harness-evidence-id",
        )
        val request = SkaldVaultV1ProviderKatExecutionIsolationRequest.currentEvidence(
            testProviderKatHarnessEvidence = externalEvidence,
        )
        val result = SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "provider-handle",
            "kat-executor-object",
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
            "safe-test-harness-evidence-id",
        )

        forbiddenValues.forEach { raw ->
            assertFalse(externalEvidence.toString().contains(raw))
            assertFalse(request.toString().contains(raw))
            assertFalse(result.toString().contains(raw))
            assertFalse(evidence.toString().contains(raw))
            assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        }
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsKatExecutorObject)
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
        SkaldVaultV1ProviderKatExecutionIsolationTopic.entries.forEach { topic ->
            val evidence = blocked(
                SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderKatExecutionIsolationRequest.forTopic(topic),
                ),
            )

            assertEquals(listOf(topic), evidence.topicRows.map { it.topic })
            assertTrue(evidence.topicRows.single().modeled)
            assertFalse(evidence.topicRows.single().katExecutorAvailable)
            assertFalse(evidence.topicRows.single().runtimeKatAllowed)
            assertFalse(evidence.topicRows.single().providerRuntimeInvoked)
            assertFalse(evidence.topicRows.single().katEvidenceAuthorizesProduction)
            assertTrue(evidence.riskRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderKatExecutionIsolationRisk.entries.forEach { risk ->
            val evidence = blocked(
                SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderKatExecutionIsolationRequest.forRisk(risk),
                ),
            )

            assertEquals(listOf(risk), evidence.riskRows.map { it.risk })
            assertTrue(evidence.riskRows.single().rejected)
            assertFalse(evidence.riskRows.single().canRunKat)
            assertFalse(evidence.riskRows.single().canInvokeRuntime)
            assertFalse(evidence.riskRows.single().canExecute)
            assertTrue(evidence.topicRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.entries.forEach { requiredProperty ->
            val evidence = blocked(
                SkaldVaultV1ProviderKatExecutionIsolationPolicy.evaluate(
                    SkaldVaultV1ProviderKatExecutionIsolationRequest.forRequiredProperty(requiredProperty),
                ),
            )

            assertEquals(listOf(requiredProperty), evidence.requiredPropertyRows.map { it.requiredProperty })
            assertTrue(evidence.requiredPropertyRows.single().satisfiedForCurrentState)
            assertFalse(evidence.requiredPropertyRows.single().katExecutorRuntimeAvailable)
            assertFalse(evidence.requiredPropertyRows.single().providerKatExecutionAllowed)
            assertTrue(evidence.topicRows.isNotEmpty())
            assertTrue(evidence.riskRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
    }

    private fun external(
        kind: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind,
        safeEvidenceId: String,
    ) = SkaldVaultV1ProviderKatExecutionIsolationRequest.externalEvidence(
        kind = kind,
        safeEvidenceId = safeEvidenceId,
    )

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
        result: SkaldVaultV1ProviderKatExecutionIsolationResult<
            SkaldVaultV1ProviderKatExecutionIsolationEvidence,
        >,
    ): SkaldVaultV1ProviderKatExecutionIsolationEvidence =
        assertIs<
            SkaldVaultV1ProviderKatExecutionIsolationResult.Blocked<
                SkaldVaultV1ProviderKatExecutionIsolationEvidence,
            >,
        >(result).value

    private fun blockedDispatch(
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

    private fun assertDisabled(capability: SkaldVaultV1ProviderKatExecutionIsolationCapability) {
        assertTrue(capability.providerKatExecutionIsolationModeled)
        assertFalse(capability.providerKatExecutorExistsForNonDisabledProvider)
        assertFalse(capability.providerKatExecutorReachableFromSelection)
        assertFalse(capability.providerKatExecutorReachableFromRegistry)
        assertFalse(capability.providerKatExecutorReachableFromFactory)
        assertFalse(capability.providerKatExecutorReachableFromDispatch)
        assertFalse(capability.providerKatExecutorCanInvokeSkeleton)
        assertFalse(capability.providerKatExecutorCanInvokeCandidate)
        assertFalse(capability.providerKatExecutorCanInvokeProviderRuntime)
        assertFalse(capability.providerKatExecutorCanExposeProviderHandle)
        assertFalse(capability.providerKatExecutorCanExposeCryptoObject)
        assertFalse(capability.providerKatExecutorAcceptsByteMaterial)
        assertFalse(capability.providerKatExecutorCanExecuteProviderOperations)
        assertFalse(capability.providerKatExecutorCanUseRandomness)
        assertFalse(capability.providerKatExecutorCanRunKdf)
        assertFalse(capability.providerKatExecutorCanRunAead)
        assertFalse(capability.providerKatExecutorCanComputeHeaderCommitment)
        assertFalse(capability.providerKatExecutorCanEncryptRecords)
        assertFalse(capability.providerKatExecutorCanDecryptRecords)
        assertFalse(capability.providerKatExecutorCanWrapKeys)
        assertFalse(capability.providerKatExecutorCanAuthorizeProviderSelection)
        assertFalse(capability.providerKatExecutorCanAuthorizeProductionProviderSelectable)
        assertFalse(capability.providerKatExecutorCanCreateVault)
        assertFalse(capability.providerKatExecutorCanUnlockVault)
        assertFalse(capability.providerKatExecutorCanPersistVault)
        assertFalse(capability.providerKatExecutorCanEnableMainnet)
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

    private fun assertDisabled(evidence: SkaldVaultV1ProviderKatExecutionIsolationEvidence) {
        assertTrue(evidence.providerKatExecutionIsolationModeled)
        assertFalse(evidence.providerKatExecutorAdded)
        assertFalse(evidence.providerKatExecutorExistsForNonDisabledProvider)
        assertFalse(evidence.providerKatExecutorReachableFromSelection)
        assertFalse(evidence.providerKatExecutorReachableFromRegistry)
        assertFalse(evidence.providerKatExecutorReachableFromFactory)
        assertFalse(evidence.providerKatExecutorReachableFromDispatch)
        assertFalse(evidence.providerKatExecutorCanInvokeSkeleton)
        assertFalse(evidence.providerKatExecutorCanInvokeCandidate)
        assertFalse(evidence.providerKatExecutorCanInvokeProviderRuntime)
        assertFalse(evidence.providerKatExecutorCanExposeProviderHandle)
        assertFalse(evidence.providerKatExecutorCanExposeCryptoObject)
        assertFalse(evidence.providerKatExecutorAcceptsByteMaterial)
        assertFalse(evidence.providerKatExecutorCanExecuteProviderOperations)
        assertFalse(evidence.providerKatExecutorCanUseRandomness)
        assertFalse(evidence.providerKatExecutorCanRunKdf)
        assertFalse(evidence.providerKatExecutorCanRunAead)
        assertFalse(evidence.providerKatExecutorCanComputeHeaderCommitment)
        assertFalse(evidence.providerKatExecutorCanEncryptRecords)
        assertFalse(evidence.providerKatExecutorCanDecryptRecords)
        assertFalse(evidence.providerKatExecutorCanWrapKeys)
        assertFalse(evidence.providerKatExecutorCanAuthorizeProviderSelection)
        assertFalse(evidence.providerKatExecutorCanAuthorizeProductionProviderSelectable)
        assertFalse(evidence.providerKatExecutorCanCreateVault)
        assertFalse(evidence.providerKatExecutorCanUnlockVault)
        assertFalse(evidence.providerKatExecutorCanPersistVault)
        assertFalse(evidence.providerKatExecutorCanEnableMainnet)
        assertFalse(evidence.providerOperationDispatcherAdded)
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
