package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSection
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcut
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTarget
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityImplementationAdmissionGateTest {
    @Test
    fun currentAdmissionGateEvidenceIsModeledAndStillDisabled() {
        val evidence = currentEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-admission-gate-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.admissionGateModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.admissionDependenciesModeled)
        assertTrue(evidence.admissionChecksModeled)
        assertTrue(evidence.forbiddenAdmissionTargetsModeled)
        assertTrue(evidence.forbiddenPromotionPathsModeled)
        assertTrue(evidence.docsMayDescribeAdmission)
        assertTrue(evidence.testsMayAssertBlockedAdmission)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionStatus.AdmissionGateModeled in
                evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionStatus.StillDisabled in evidence.statuses,
        )
        assertFalse(evidence.capabilities.admissionGateAuthorizesImplementation)
        assertFalse(evidence.currentAdmissionGranted)
    }

    @Test
    fun currentAdmissionOutcomeIsDeniedReviewRequiredIncompleteAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.CurrentAdmissionDenied in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.AdmissionReviewRequired in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.DependenciesIncomplete in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.ImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.MainnetNotAuthorized in outcomes,
        )
    }

    @Test
    fun allAdmissionSectionsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().admissionSectionRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSection.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSection.entries.toSet(),
            rows.map { it.section }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.currentAdmissionGranted })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allAdmissionDependenciesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().admissionDependencyRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.entries.toSet(),
            rows.map { it.dependency }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.included })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allAdmissionChecksAreRepresentedExactlyOnceAndNonAuthorizing() {
        val rows = currentEvidence().admissionCheckRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.entries.toSet(),
            rows.map { it.check }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.currentEvidenceSatisfied })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenAdmissionShortcutsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenAdmissionShortcutRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcut.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcut.entries.toSet(),
            rows.map { it.shortcut }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.authorizesAdmission })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenAdmissionTargetsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenAdmissionTargetRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTarget.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTarget.entries.toSet(),
            rows.map { it.target }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.admittedNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.admittedNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenComposedWithAllPriorEvidence() {
        val decision = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val isolation = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val namespace = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val sourceSet =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()
        val implementationDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()
        val prerequisiteAudit =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy.currentPrerequisiteAuditEvidence()
        val scopeDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.currentScopeDecisionEvidence()
        val implementationContract =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy.currentImplementationContractEvidence()
        val readinessGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy.currentReadinessGateEvidence()
        val runtimeLinkage =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
                .currentRuntimeLinkageGuardEvidence()
        val promotionBlockers =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy.currentPromotionBlockersEvidence()
        val sourceGuardCoverage =
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
                .currentSourceGuardCoverageEvidence()
        val redactionGuard =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy.currentRedactionGuardEvidence()
        val redactionBoundarySummary = SkaldVaultV1RedactionLeakagePolicy.summary()
        val evidence = currentEvidence()
        val selected = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selected.selectedCandidateId)
        assertTrue(selected.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selected.selectedProvider)
        assertFalse(selected.productionProviderSelectable)
        assertTrue(redactionBoundarySummary.stillDisabled)
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(prerequisiteAudit.capabilities.prerequisiteAuditAuthorizesImplementation)
        assertFalse(scopeDecision.capabilities.scopeDecisionAuthorizesImplementation)
        assertFalse(implementationContract.capabilities.implementationContractAuthorizesImplementation)
        assertFalse(readinessGate.capabilities.readinessGateAuthorizesImplementation)
        assertFalse(runtimeLinkage.capabilities.runtimeLinkageGuardAuthorizesImplementation)
        assertFalse(promotionBlockers.capabilities.promotionBlockersAuthorizeProviderSelection)
        assertFalse(sourceGuardCoverage.capabilities.sourceGuardCoverageAuthorizesImplementation)
        assertFalse(redactionGuard.capabilities.redactionGuardAuthorizesImplementation)
        assertFalse(evidence.capabilities.admissionGateAuthorizesImplementation)
        assertAllAdmissionBooleansFalse(evidence)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(evidence.capabilities.promotionBlockersAuthorizeProductionProviderSelectable)
        assertFalse(evidence.currentProductionProviderSelectableAdmitted)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        assertAllCapabilityBooleansFalse(currentEvidence().capabilities)
    }

    @Test
    fun allCurrentAdmissionBooleansRemainFalse() {
        assertAllAdmissionBooleansFalse(currentEvidence())
    }

    @Test
    fun allowedTrueBooleansAreOnlyNonAuthorizingAdmissionEvidence() {
        val evidence = currentEvidence()

        assertTrue(evidence.admissionGateModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.admissionDependenciesModeled)
        assertTrue(evidence.admissionChecksModeled)
        assertTrue(evidence.forbiddenAdmissionTargetsModeled)
        assertTrue(evidence.forbiddenPromotionPathsModeled)
        assertTrue(evidence.docsMayDescribeAdmission)
        assertTrue(evidence.testsMayAssertBlockedAdmission)
        assertTrue(evidence.admissionSectionRows.none { it.authorizesImplementation })
        assertTrue(evidence.admissionDependencyRows.none { it.authorizesImplementation })
        assertTrue(evidence.admissionCheckRows.none { it.authorizesImplementation })
        assertTrue(evidence.forbiddenAdmissionTargetRows.none { it.admittedNow })
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllAdmissionBooleansFalse(evidence)
    }

    @Test
    fun everyPriorBoundaryEvidenceRemainsNonAuthorizingWhenComposed() {
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIdentityDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIsolation = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeNamespace = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .SyntheticIdentityNamespace,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceSet = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePrerequisiteAudit = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeScopeDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationContract = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationContract,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeReadinessGate = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationReadinessGate,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeRuntimeLinkageGuard = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePromotionBlockers = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationPromotionBlockers,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceGuardCoverage = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationSourceGuardCoverage,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeRedactionGuard = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationRedactionGuard,
        )
    }

    @Test
    fun falsePositiveAdmissionAndReachabilityClaimsAddBlockersWithoutChangingCapabilitiesOrAdmissionBooleans() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy.evaluateAdmissionGate(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest(
                userConsentOverrideRequested = true,
                warningOnlyEvidenceClaimed = true,
                testOnlyEvidenceClaimedAsProductionPromotion = true,
                releaseEvidenceClaimed = true,
                admissionClaimed = true,
                admissionClaimedAsImplementationAuthorization = true,
                futureBranchApprovalClaimed = true,
                implementationClaimed = true,
                productionIdentityClaimed = true,
                providerSelectionClaimed = true,
                productionProviderSelectableClaimed = true,
                registryEntryClaimed = true,
                factoryReachabilityClaimed = true,
                dispatcherReachabilityClaimed = true,
                executorTargetClaimed = true,
                providerKatExecutorClaimed = true,
                providerOperationClaimed = true,
                cryptoExecutionClaimed = true,
                vaultPersistenceClaimed = true,
                productionSyncClaimed = true,
                settingsPersistenceClaimed = true,
                uiSurfaceClaimed = true,
                signingBroadcastingClaimed = true,
                publicEndpointClaimed = true,
                mainnetReachabilityClaimed = true,
            ),
        )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllAdmissionBooleansFalse(evidence)
        listOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.AdmissionClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.AdmissionAuthorizationClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.FutureBranchApprovalClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ImplementationClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProductionIdentityClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProviderSelectionClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                .ProductionProviderSelectableClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.RegistryEntryClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.FactoryReachabilityClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.DispatcherReachabilityClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ExecutorTargetClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProviderKatExecutorClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProviderOperationClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.CryptoExecutionClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.VaultPersistenceClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProductionSyncClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.SettingsPersistenceClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.UiSurfaceClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.SigningBroadcastingClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.PublicEndpointClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.MainnetReachabilityClaimRejected,
        ).forEach { blocker ->
            assertTrue(blocker in evidence.blockers, "missing blocker $blocker")
        }
    }

    @Test
    fun requestStringAndSafeLabelsDoNotExposeSensitiveAdmissionOrDiagnosticReferences() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest(
            admissionClaimed = true,
            admissionClaimedAsImplementationAuthorization = true,
            futureBranchApprovalClaimed = true,
            implementationClaimed = true,
            providerSelectionClaimed = true,
            productionProviderSelectableClaimed = true,
            registryEntryClaimed = true,
            factoryReachabilityClaimed = true,
            dispatcherReachabilityClaimed = true,
            executorTargetClaimed = true,
            providerKatExecutorClaimed = true,
            providerOperationClaimed = true,
            cryptoExecutionClaimed = true,
            vaultPersistenceClaimed = true,
            productionSyncClaimed = true,
            settingsPersistenceClaimed = true,
            uiSurfaceClaimed = true,
            signingBroadcastingClaimed = true,
            publicEndpointClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel(
            "admission-gate-future-review",
        ).toString()

        val forbiddenTerms = listOf(
            "admission-gate-future-review",
            "candidate",
            "raw material",
            "identity",
            "provider",
            "storage",
            "backend",
            "crypto",
            "path",
            "file",
            "endpoint",
            "wallet",
            "descriptor",
            "source location",
            "future approval",
            "prerequisite",
            "scope",
            "contract",
            "readiness",
            "runtime linkage",
            "promotion",
            "source guard",
            "redaction",
            "admission",
            "admission payload",
            "diagnostic payload",
            "hash",
            "fingerprint",
            "crash report",
            "analytics",
            "support export",
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    @Test
    fun admissionGateModelsForbiddenImportScanForNewCommonMainFile() {
        val evidence = currentEvidence()
        val sourceGuardEvidence = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource.SourceGuardEvidence
        }
        val sourceGuardDependency = evidence.admissionDependencyRows.single {
            it.dependency == SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.SourceGuardCoverage
        }

        assertTrue(sourceGuardEvidence.included)
        assertTrue(sourceGuardEvidence.nonAuthorizing)
        assertFalse(sourceGuardEvidence.authorizesImplementation)
        assertTrue(sourceGuardDependency.included)
        assertTrue(sourceGuardDependency.nonAuthorizing)
        assertFalse(evidence.capabilities.canExecuteAead)
        assertFalse(evidence.capabilities.canUseForBdkWalletState)
        assertFalse(evidence.capabilities.canUseForSettingsCodec)
        assertFalse(evidence.capabilities.canUseForMainnet)
        assertAllAdmissionBooleansFalse(evidence)
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy.currentAdmissionGateEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean = false,
        includeIsolation: Boolean = false,
        includeNamespace: Boolean = false,
        includeSourceSet: Boolean = false,
        includeImplementationDecision: Boolean = false,
        includePrerequisiteAudit: Boolean = false,
        includeScopeDecision: Boolean = false,
        includeImplementationContract: Boolean = false,
        includeReadinessGate: Boolean = false,
        includeRuntimeLinkageGuard: Boolean = false,
        includePromotionBlockers: Boolean = false,
        includeSourceGuardCoverage: Boolean = false,
        includeRedactionGuard: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy.evaluateAdmissionGate(
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
            includePriorPrerequisiteAuditEvidence = includePrerequisiteAudit,
            includePriorScopeDecisionEvidence = includeScopeDecision,
            includePriorImplementationContractEvidence = includeImplementationContract,
            includePriorReadinessGateEvidence = includeReadinessGate,
            includePriorRuntimeLinkageGuardEvidence = includeRuntimeLinkageGuard,
            includePriorPromotionBlockersEvidence = includePromotionBlockers,
            includePriorSourceGuardCoverageEvidence = includeSourceGuardCoverage,
            includePriorRedactionGuardEvidence = includeRedactionGuard,
        ),
    )

    private fun assertPriorEvidenceNonAuthorizing(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence,
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource,
    ) {
        val row = evidence.evidenceSourceRows.single { it.evidenceSource == source }

        assertTrue(row.included)
        assertTrue(row.modeled)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllAdmissionBooleansFalse(evidence)
    }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateCapabilities,
    ) {
        assertFalse(capabilities.admissionGateAuthorizesImplementation)
        assertFalse(capabilities.redactionGuardAuthorizesImplementation)
        assertFalse(capabilities.sourceGuardCoverageAuthorizesImplementation)
        assertFalse(capabilities.promotionBlockersAuthorizeImplementation)
        assertFalse(capabilities.promotionBlockersAuthorizeProductionIdentity)
        assertFalse(capabilities.promotionBlockersAuthorizeProviderSelection)
        assertFalse(capabilities.promotionBlockersAuthorizeProductionProviderSelectable)
        assertFalse(capabilities.runtimeLinkageGuardAuthorizesImplementation)
        assertFalse(capabilities.readinessGateAuthorizesImplementation)
        assertFalse(capabilities.implementationContractAuthorizesImplementation)
        assertFalse(capabilities.scopeDecisionAuthorizesImplementation)
        assertFalse(capabilities.prerequisiteAuditAuthorizesImplementation)
        assertFalse(capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(capabilities.productionIdentityImplementationPresent)
        assertFalse(capabilities.canImplementProviderNow)
        assertFalse(capabilities.canInstantiateProviderNow)
        assertFalse(capabilities.canRegisterProviderNow)
        assertFalse(capabilities.canUseAsProviderSelectionId)
        assertFalse(capabilities.canUseAsRegistryKey)
        assertFalse(capabilities.canUseAsFactoryInput)
        assertFalse(capabilities.canUseAsDispatcherInput)
        assertFalse(capabilities.canUseAsExecutorTarget)
        assertFalse(capabilities.canUseForProviderKatExecutor)
        assertFalse(capabilities.canExecuteProviderOperations)
        assertFalse(capabilities.canExecuteRandomness)
        assertFalse(capabilities.canExecuteKdf)
        assertFalse(capabilities.canExecuteHkdf)
        assertFalse(capabilities.canExecuteHmac)
        assertFalse(capabilities.canExecuteAead)
        assertFalse(capabilities.canGenerateKeys)
        assertFalse(capabilities.canStoreKeysets)
        assertFalse(capabilities.canUseForVaultCreation)
        assertFalse(capabilities.canUseForVaultUnlock)
        assertFalse(capabilities.canUseForVaultSession)
        assertFalse(capabilities.canUseForVaultPersistence)
        assertFalse(capabilities.canUseForSecureStorage)
        assertFalse(capabilities.canUseForSecureMetadataStorage)
        assertFalse(capabilities.canUseForStorageNamespace)
        assertFalse(capabilities.canUseForStoragePath)
        assertFalse(capabilities.canUseForManifestReadWrite)
        assertFalse(capabilities.canUseForMigration)
        assertFalse(capabilities.canUseForProductionSync)
        assertFalse(capabilities.canUseForBackendClient)
        assertFalse(capabilities.canUseForBdkWalletState)
        assertFalse(capabilities.canUseForSettingsCodec)
        assertFalse(capabilities.canUseForUiSurface)
        assertFalse(capabilities.canUseForSigning)
        assertFalse(capabilities.canUseForBroadcasting)
        assertFalse(capabilities.canUseForTorTransport)
        assertFalse(capabilities.canUseForNostrParsing)
        assertFalse(capabilities.canUseForPublicEndpointDefault)
        assertFalse(capabilities.canUseForMainnet)
        assertFalse(capabilities.productionProviderSelectable)
    }

    private fun assertAllAdmissionBooleansFalse(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence,
    ) {
        assertFalse(evidence.currentAdmissionGranted)
        assertFalse(evidence.currentFutureBranchApproved)
        assertFalse(evidence.currentImplementationAdmitted)
        assertFalse(evidence.currentProviderSelectionAdmitted)
        assertFalse(evidence.currentProductionProviderSelectableAdmitted)
        assertFalse(evidence.currentRuntimeLinkageAdmitted)
        assertFalse(evidence.currentPromotionAdmitted)
        assertFalse(evidence.currentVaultPersistenceAdmitted)
        assertFalse(evidence.currentProductionSyncAdmitted)
        assertFalse(evidence.currentSigningBroadcastingAdmitted)
        assertFalse(evidence.currentUiSurfaceAdmitted)
        assertFalse(evidence.currentPublicEndpointAdmitted)
        assertFalse(evidence.currentMainnetAdmitted)
    }
}
