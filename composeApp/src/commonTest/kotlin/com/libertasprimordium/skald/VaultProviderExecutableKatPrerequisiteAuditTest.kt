package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteCategory
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultProviderExecutableKatPrerequisiteAuditTest {
    @Test
    fun currentAuditIsModeledButBlocked() {
        val evidence = currentAudit()

        assertEquals(
            "skald-vault-v1-provider-executable-kat-prerequisite-audit-v1",
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.BlockedFailClosed, evidence.status)
        assertContains(evidence.statuses, SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.AuditModeled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.PrerequisitesClassified,
        )
        assertContains(evidence.statuses, SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.StillDisabled)
        assertContains(evidence.statuses, SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.EvidenceOnly)
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.ExecutableKatIntroductionBlocked,
        )
        assertTrue(evidence.auditModeled)
        assertTrue(evidence.blockedFailClosed)
        assertTrue(evidence.evidenceOnly)
        assertFalse(evidence.modeledEvidenceCanAuthorizeExecution)
        assertAllAuthorizationFalse(evidence)
    }

    @Test
    fun everyPrerequisiteHasAClassification() {
        val evidence = currentAudit()
        val findings = evidence.prerequisiteFindings.associateBy { it.category }

        assertEquals(SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.entries.toSet(), findings.keys)
        findings.values.forEach { finding ->
            assertContains(
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.entries.toSet(),
                finding.classification,
            )
            assertFalse(finding.authorizesExecution)
            assertAllAuthorizationFalse(finding.authorizationStatus)
            assertTrue(finding.futureRequiredBeforeTestOnlyExecutableKat)
            assertEquals(
                SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.EvidenceClassNamesOnly,
                finding.redactionClass,
            )
        }
    }

    @Test
    fun existingEvidenceSourcesRemainNonAuthorizing() {
        val evidence = currentAudit()

        assertSourceNonAuthorizing(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ExecutableKatDecisionGate,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
        )
        assertSourceNonAuthorizing(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatExecutionIsolation,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
        )
        assertSourceNonAuthorizing(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.DependencyLevelKatEvidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
        )
        assertSourceNonAuthorizing(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.PublicVectorDocumentation,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
        )
        assertSourceNonAuthorizing(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.TestProviderKatHarness,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.TestOnlyEvidenceExists,
        )
    }

    @Test
    fun providerImplementationAndKatExecutorAreMissing() {
        val evidence = currentAudit()
        val implementation = finding(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderImplementationExists,
        )
        val stillNonProduction = finding(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderImplementationIsStillNonProduction,
        )
        val katIsolation = finding(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderKatExecutionIsolationReviewed,
        )

        assertFalse(evidence.executableProviderImplementationPresent)
        assertFalse(evidence.executableProviderKatExecutorPresent)
        assertEquals(
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.FutureRequiredEvidenceMissing,
            implementation.classification,
        )
        assertFalse(implementation.currentEvidenceExists)
        assertFalse(stillNonProduction.currentEvidenceExists)
        assertContains(
            implementation.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
        )
        assertContains(
            katIsolation.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderKatExecutor,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
        )
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnly() {
        val evidence = currentAudit()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderSelectionDisabledProviderOnly,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProductionProviderSelectableFalse,
        )
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(selection.candidates.none { it.productionSelectable })
    }

    @Test
    fun noPrerequisiteAuthorizesExecutionOrVaultLifecycle() {
        val evidence = currentAudit()

        evidence.prerequisiteFindings.forEach { finding ->
            assertAllAuthorizationFalse(finding.authorizationStatus)
            assertFalse(finding.authorizationStatus.canAuthorizeProviderExecution)
            assertFalse(finding.authorizationStatus.canAuthorizeExecutableKatPath)
            assertFalse(finding.authorizationStatus.canAuthorizeTestOnlyExecutableKatPath)
            assertFalse(finding.authorizationStatus.canAuthorizeProductionExecutableKatPath)
            assertFalse(finding.authorizationStatus.canAuthorizeVaultCreation)
            assertFalse(finding.authorizationStatus.canAuthorizeVaultUnlock)
            assertFalse(finding.authorizationStatus.canAuthorizeVaultPersistence)
            assertFalse(finding.authorizationStatus.canAuthorizeProductionSync)
            assertFalse(finding.authorizationStatus.canAuthorizeMainnet)
        }
        assertAllAuthorizationFalse(evidence)
    }

    @Test
    fun warningConsentReleaseAndMainnetClaimsRemainBlocked() {
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy.evaluate(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest(
                    categories = SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.entries.toSet(),
                    sources = SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.entries.toSet(),
                    warningOnlyEvidenceClaimed = true,
                    userConsentOverrideRequested = true,
                    releaseMainnetEvidenceClaimed = true,
                    safeAuditId = "runtime-payload-value-not-rendered",
                ),
            ),
        )

        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.WarningOnlyEvidenceCannotAuthorize,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.UserConsentCannotOverrideMissingHardGates,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ReleaseReviewMissing)
        assertContains(evidence.blockers, SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.MainnetDisabled)
        assertAllAuthorizationFalse(evidence)
    }

    @Test
    fun futureRequiredWorkIsPresentAndExplicitWithoutEnablingIt() {
        val evidence = currentAudit()
        val futureWork = evidence.futureRequiredWork.associateBy { it.category }

        assertEquals(SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.entries.toSet(), futureWork.keys)
        futureWork.values.forEach { item ->
            assertTrue(item.requiredBeforeTestOnlyExecutableKat)
            assertTrue(item.requiredBeforeProductionExecutableKat)
            assertFalse(item.authorizesCurrentExecution)
            assertTrue(item.blockers.isNotEmpty())
            assertTrue(item.safeWorkId.startsWith("future-work-"))
        }
        assertContains(
            futureWork.getValue(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderImplementationExists,
            ).blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
        )
        assertContains(
            futureWork.getValue(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelPositiveKatsDefined,
            ).blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
        )
    }

    @Test
    fun redactedOutputAndClassesDoNotCarryMaterial() {
        val request = SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest.currentEvidence(
            safeAuditId = "payload-key-folder-provider-crypto-marker",
        )
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy.evaluate(request),
        )
        val rendered = request.toString() + evidence.redactionClasses.joinToString { it.name }

        assertFalse(rendered.contains("payload-key-folder-provider-crypto-marker"))
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.PolicyIdsOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.EvidenceClassNamesOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.NoRawMaterial,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.NoFilesystemLocations,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.NoProviderReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.NoCryptoReferences,
        )
    }

    @Test
    fun sourceSetConfinementIsRepresentedButDoesNotApproveExecution() {
        val evidence = currentAudit()
        val sourceSetConfinement = finding(
            evidence,
            SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.SourceSetConfinementReviewed,
        )

        assertEquals(
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
            sourceSetConfinement.classification,
        )
        assertContains(
            sourceSetConfinement.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SourceSetConfinementNotExecutableProviderReviewed,
        )
        assertFalse(evidence.sourceSetConfinementAuthorizesExecution)
        assertFalse(sourceSetConfinement.authorizationStatus.canAuthorizeProviderExecution)
    }

    @Test
    fun modeledEvidenceExistsIsDistinctFromExecutionAuthorization() {
        val evidence = currentAudit()
        val modeledFindings = evidence.prerequisiteFindings.filter {
            it.classification ==
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists
        }

        assertTrue(modeledFindings.isNotEmpty())
        modeledFindings.forEach { finding ->
            assertTrue(finding.currentEvidenceExists)
            assertFalse(finding.authorizesExecution)
            assertAllAuthorizationFalse(finding.authorizationStatus)
        }
    }

    @Test
    fun auditSummaryEnumeratesModelUniverseWithoutAuthorization() {
        val summary = SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy.currentPolicySummary()

        assertEquals(SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.entries.toSet(), summary.categories)
        assertEquals(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.entries.toSet(), summary.sources)
        assertEquals(
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.entries.toSet(),
            summary.classifications,
        )
        assertContains(
            summary.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
        )
        assertContains(
            summary.blockers,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderKatExecutor,
        )
        assertAllAuthorizationFalse(summary.authorizationStatus)
    }

    fun currentAudit(): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence =
        blocked(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy.evaluate())

    fun blocked(
        result: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult<
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
        >,
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence =
        assertIs<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult.Blocked<
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
        >>(result).value

    fun finding(
        evidence: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
        category: SkaldVaultV1ProviderExecutableKatPrerequisiteCategory,
    ) = evidence.prerequisiteFindings.single { it.category == category }

    fun assertSourceNonAuthorizing(
        evidence: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
        source: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource,
        classification: SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification,
    ) {
        val finding = evidence.sourceFindings.single { it.source == source }

        assertEquals(classification, finding.classification)
        assertTrue(finding.evidenceOnly)
        assertAllAuthorizationFalse(finding.authorizationStatus)
    }

    fun assertAllAuthorizationFalse(evidence: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence) {
        assertAllAuthorizationFalse(evidence.authorizationStatus)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProviderExecution)
        assertFalse(evidence.disabledCapabilities.canAuthorizeExecutableKatPath)
        assertFalse(evidence.disabledCapabilities.canAuthorizeTestOnlyExecutableKatPath)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProductionExecutableKatPath)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(evidence.disabledCapabilities.canSetProductionProviderSelectable)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultCreation)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultUnlock)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultPersistence)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProductionSync)
        assertFalse(evidence.disabledCapabilities.canAuthorizeMainnet)
        assertFalse(evidence.productionProviderSelectable)
    }

    fun assertAllAuthorizationFalse(
        authorizationStatus: SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus,
    ) {
        assertFalse(authorizationStatus.anyAuthorizationGranted)
        assertFalse(authorizationStatus.canAuthorizeProviderExecution)
        assertFalse(authorizationStatus.canAuthorizeExecutableKatPath)
        assertFalse(authorizationStatus.canAuthorizeTestOnlyExecutableKatPath)
        assertFalse(authorizationStatus.canAuthorizeProductionExecutableKatPath)
        assertFalse(authorizationStatus.canAuthorizeProviderSelection)
        assertFalse(authorizationStatus.canSetProductionProviderSelectable)
        assertFalse(authorizationStatus.canAuthorizeVaultCreation)
        assertFalse(authorizationStatus.canAuthorizeVaultUnlock)
        assertFalse(authorizationStatus.canAuthorizeVaultPersistence)
        assertFalse(authorizationStatus.canAuthorizeProductionSync)
        assertFalse(authorizationStatus.canAuthorizeMainnet)
    }
}
