package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityImplementationRedactionGuardTest {
    @Test
    fun currentRedactionGuardEvidenceIsModeledAndStillDisabled() {
        val evidence = currentEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-redaction-guard-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.redactionGuardModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.safeOutputsOnly)
        assertTrue(evidence.redactionSurfacesModeled)
        assertTrue(evidence.sensitiveReferenceClassesModeled)
        assertTrue(evidence.allowedOutputClassesModeled)
        assertTrue(evidence.forbiddenOutputClassesModeled)
        assertTrue(evidence.forbiddenLeakagePathsModeled)
        assertTrue(evidence.docsMayDescribeRedaction)
        assertTrue(evidence.testsMayAssertBlockedLeakage)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardStatus.RedactionGuardModeled in
                evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardStatus.StillDisabled in
                evidence.statuses,
        )
        assertFalse(evidence.capabilities.redactionGuardAuthorizesImplementation)
    }

    @Test
    fun currentRedactionOutcomeIsModeledBlockedSafeAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.CurrentRedactionGuardModeled in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.RedactionReviewRequired in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.SafeOutputsOnly in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.LeakageBlocked in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.ImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.MainnetNotAuthorized in outcomes,
        )
    }

    @Test
    fun allRedactionSurfacesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().redactionSurfaceRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface.entries.toSet(),
            rows.map { it.surface }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.safeOutputOnly })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allSensitiveReferenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().sensitiveReferenceClassRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClass.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClass.entries.toSet(),
            rows.map { it.referenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.rawOutputAllowed })
        assertTrue(rows.none { it.leakagePresent })
    }

    @Test
    fun allAllowedOutputClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().allowedOutputClassRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClass.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClass.entries.toSet(),
            rows.map { it.outputClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.safeForCurrentEvidence })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenOutputClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenOutputClassRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClass.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClass.entries.toSet(),
            rows.map { it.outputClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.present })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenLeakagePathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenLeakagePathRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePath.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePath.entries.toSet(),
            rows.map { it.leakagePath }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.leakagePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenComposedWithPriorEvidence() {
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
        assertFalse(evidence.capabilities.redactionGuardAuthorizesImplementation)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(evidence.capabilities.promotionBlockersAuthorizeProductionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        assertAllCapabilityBooleansFalse(currentEvidence().capabilities)
    }

    @Test
    fun allCurrentLeakageBooleansRemainFalse() {
        assertAllLeakageBooleansFalse(currentEvidence())
    }

    @Test
    fun allowedTrueBooleansAreOnlyNonAuthorizingRedactionEvidence() {
        val evidence = currentEvidence()

        assertTrue(evidence.redactionGuardModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.safeOutputsOnly)
        assertTrue(evidence.redactionSurfacesModeled)
        assertTrue(evidence.sensitiveReferenceClassesModeled)
        assertTrue(evidence.allowedOutputClassesModeled)
        assertTrue(evidence.forbiddenOutputClassesModeled)
        assertTrue(evidence.forbiddenLeakagePathsModeled)
        assertTrue(evidence.docsMayDescribeRedaction)
        assertTrue(evidence.testsMayAssertBlockedLeakage)
        assertTrue(evidence.redactionSurfaceRows.none { it.authorizesImplementation })
        assertTrue(evidence.allowedOutputClassRows.none { it.authorizesImplementation })
        assertTrue(evidence.forbiddenOutputClassRows.none { it.present })
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllLeakageBooleansFalse(evidence)
    }

    @Test
    fun allPriorEvidenceSourcesRemainNonAuthorizingWhenComposed() {
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIdentityDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIsolation = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeNamespace = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .SyntheticIdentityNamespace,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceSet = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePrerequisiteAudit = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeScopeDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationContract = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationContract,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeReadinessGate = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationReadinessGate,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeRuntimeLinkageGuard = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePromotionBlockers = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationPromotionBlockers,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceGuardCoverage = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .TestOnlyProviderIdentityImplementationSourceGuardCoverage,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeRedactionBoundary = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                .VaultRedactionLeakageBoundary,
        )
    }

    @Test
    fun falsePositiveOutputAndAuthorizationClaimsAddBlockersWithoutChangingCapabilitiesOrLeakage() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy.evaluateRedactionGuard(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest(
                userConsentOverrideRequested = true,
                warningOnlyEvidenceClaimed = true,
                testOnlyEvidenceClaimedAsProductionPromotion = true,
                releaseEvidenceClaimed = true,
                rawIdentityOutputClaimed = true,
                rawSafeIdOutputClaimed = true,
                rawProviderReferenceOutputClaimed = true,
                rawCryptoReferenceOutputClaimed = true,
                rawStorageReferenceOutputClaimed = true,
                rawBackendReferenceOutputClaimed = true,
                rawEndpointReferenceOutputClaimed = true,
                rawWalletReferenceOutputClaimed = true,
                rawSourceLocationOutputClaimed = true,
                rawFutureApprovalOutputClaimed = true,
                rawImplementationPayloadOutputClaimed = true,
                rawRuntimeLinkagePayloadOutputClaimed = true,
                rawPromotionPayloadOutputClaimed = true,
                rawSourceGuardPayloadOutputClaimed = true,
                secretHashOrFingerprintOutputClaimed = true,
                crashReportOutputClaimed = true,
                analyticsOutputClaimed = true,
                supportExportOutputClaimed = true,
                redactionEvidenceClaimedAsImplementationAuthorization = true,
                providerImplementationClaimed = true,
                providerSelectionClaimed = true,
                productionProviderSelectableClaimed = true,
                promotionClaimed = true,
                mainnetReachabilityClaimed = true,
            ),
        )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllLeakageBooleansFalse(evidence)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.RawIdentityOutputRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.RawSafeIdOutputRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawProviderReferenceOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawCryptoReferenceOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawStorageReferenceOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawBackendReferenceOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawEndpointReferenceOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawWalletReferenceOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawSourceLocationOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawFutureApprovalOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawImplementationPayloadOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawRuntimeLinkagePayloadOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawPromotionPayloadOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RawSourceGuardPayloadOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .SecretHashOrFingerprintOutputRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.CrashReportOutputRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.AnalyticsOutputRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.SupportExportOutputRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RedactionEvidenceAuthorizationRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .ProviderImplementationClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.ProviderSelectionClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .ProductionProviderSelectableClaimRejected in evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.PromotionClaimRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.MainnetReachabilityClaimRejected in
                evidence.blockers,
        )
    }

    @Test
    fun requestStringAndSafeLabelsDoNotExposeSensitiveOrDiagnosticReferences() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest(
            rawIdentityOutputClaimed = true,
            rawSafeIdOutputClaimed = true,
            rawProviderReferenceOutputClaimed = true,
            rawCryptoReferenceOutputClaimed = true,
            rawStorageReferenceOutputClaimed = true,
            rawBackendReferenceOutputClaimed = true,
            rawEndpointReferenceOutputClaimed = true,
            rawWalletReferenceOutputClaimed = true,
            rawSourceLocationOutputClaimed = true,
            rawFutureApprovalOutputClaimed = true,
            rawImplementationPayloadOutputClaimed = true,
            rawRuntimeLinkagePayloadOutputClaimed = true,
            rawPromotionPayloadOutputClaimed = true,
            rawSourceGuardPayloadOutputClaimed = true,
            secretHashOrFingerprintOutputClaimed = true,
            crashReportOutputClaimed = true,
            analyticsOutputClaimed = true,
            supportExportOutputClaimed = true,
            redactionEvidenceClaimedAsImplementationAuthorization = true,
        ).toString()
        val label = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel(
            "redaction-guard-future-review",
        ).toString()

        val forbiddenTerms = listOf(
            "redaction-guard-future-review",
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
            "redaction payload",
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
    fun redactionGuardModelsForbiddenImportScanForNewCommonMainFile() {
        val evidence = currentEvidence()
        val surface = evidence.redactionSurfaceRows.single {
            it.surface == SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface.SourceGuardSummaryOutput
        }
        val sourceGuardEvidence = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource.SourceGuardEvidence
        }

        assertTrue(surface.modeled)
        assertTrue(surface.safeOutputOnly)
        assertFalse(surface.authorizesImplementation)
        assertTrue(sourceGuardEvidence.included)
        assertTrue(sourceGuardEvidence.nonAuthorizing)
        assertFalse(sourceGuardEvidence.authorizesImplementation)
        assertFalse(evidence.rawDiagnosticPayloadOutputPresent)
        assertFalse(evidence.capabilities.canExecuteAead)
        assertFalse(evidence.capabilities.canUseForBdkWalletState)
        assertFalse(evidence.capabilities.canUseForSettingsCodec)
        assertFalse(evidence.capabilities.canUseForMainnet)
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy.currentRedactionGuardEvidence()

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
        includeRedactionBoundary: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy.evaluateRedactionGuard(
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest(
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
            includePriorRedactionBoundaryEvidence = includeRedactionBoundary,
        ),
    )

    private fun assertPriorEvidenceNonAuthorizing(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence,
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource,
    ) {
        val row = evidence.evidenceSourceRows.single { it.evidenceSource == source }

        assertTrue(row.included)
        assertTrue(row.modeled)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllLeakageBooleansFalse(evidence)
    }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardCapabilities,
    ) {
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

    private fun assertAllLeakageBooleansFalse(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence,
    ) {
        assertFalse(evidence.rawIdentityOutputPresent)
        assertFalse(evidence.rawSafeIdOutputPresent)
        assertFalse(evidence.rawProviderReferenceOutputPresent)
        assertFalse(evidence.rawCryptoReferenceOutputPresent)
        assertFalse(evidence.rawStorageReferenceOutputPresent)
        assertFalse(evidence.rawBackendReferenceOutputPresent)
        assertFalse(evidence.rawEndpointReferenceOutputPresent)
        assertFalse(evidence.rawWalletReferenceOutputPresent)
        assertFalse(evidence.rawSourceLocationOutputPresent)
        assertFalse(evidence.rawFutureApprovalPayloadOutputPresent)
        assertFalse(evidence.rawImplementationPayloadOutputPresent)
        assertFalse(evidence.rawPrerequisitePayloadOutputPresent)
        assertFalse(evidence.rawScopePayloadOutputPresent)
        assertFalse(evidence.rawContractPayloadOutputPresent)
        assertFalse(evidence.rawReadinessPayloadOutputPresent)
        assertFalse(evidence.rawRuntimeLinkagePayloadOutputPresent)
        assertFalse(evidence.rawPromotionPayloadOutputPresent)
        assertFalse(evidence.rawSourceGuardPayloadOutputPresent)
        assertFalse(evidence.rawDiagnosticPayloadOutputPresent)
        assertFalse(evidence.secretHashOrFingerprintOutputPresent)
        assertFalse(evidence.crashReportOutputPresent)
        assertFalse(evidence.analyticsOutputPresent)
        assertFalse(evidence.supportExportOutputPresent)
    }
}
