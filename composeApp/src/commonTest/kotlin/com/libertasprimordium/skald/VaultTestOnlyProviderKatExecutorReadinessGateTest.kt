package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderKatExecutorReadinessGateTest {
    @Test
    fun readinessGateIsModeledButStillDisabledAndBlocked() {
        val evidence = currentReadiness()

        assertEquals(
            "skald-vault-v1-test-only-provider-kat-executor-readiness-gate-v1",
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.StillDisabled, evidence.status)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ReadinessGateModeled)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.StillDisabled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.NotReadyForExecutorImplementation,
        )
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ExecutorImplementationDeferred,
        )
        assertTrue(evidence.readinessGateModeled)
        assertFalse(evidence.readyForExecutorImplementationNow)
        assertFalse(evidence.executorImplementationAuthorizedNow)
        assertFalse(evidence.executorCallableNow)
        assertFalse(evidence.canAddRunnableInterfaceNow)
        assertDisabled(evidence.disabledCapabilities)
    }

    @Test
    fun everyRequirementIsClassifiedButNotImplementationAuthorization() {
        val rows = currentReadiness().requirementRows.associateBy { it.requirement }

        assertEquals(SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertContains(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.entries.toSet(),
                row.classification,
            )
            assertFalse(row.implementationSufficient)
            assertFalse(row.currentBranchAuthorizesImplementation)
            assertFalse(row.canAuthorizeProduction)
            assertTrue(row.modeledEvidenceExists)
            assertTrue(row.safeLabel.value.isNotBlank())
        }
    }

    @Test
    fun priorEvidenceSourcesAreClassifiedWithoutImplementationAuthorization() {
        val rows = currentReadiness().evidenceSourceRows.associateBy { it.evidenceSource }

        assertEquals(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.entries.toSet(), rows.keys)
        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutableKatDecisionGate,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.PrerequisiteAudit,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.TestOnlyScopeDecision,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog,
        ).forEach { source ->
            val row = rows.getValue(source)
            assertEquals(SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly, row.classification)
            assertTrue(row.modeledEvidenceExists)
            assertFalse(row.implementationAuthorization)
            assertFalse(row.productionAuthorization)
            assertContains(
                row.blockers,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NoRunnableExecutorSurface,
            )
        }

        assertFalse(rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.RuntimeRandomnessPolicy)
            .implementationAuthorization)
        assertFalse(rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.SecureStorageBoundary)
            .implementationAuthorization)
    }

    @Test
    fun modeledRequirementsRemainNotSufficientForExecutorImplementation() {
        val evidence = currentReadiness()
        val rows = evidence.requirementRows.associateBy { it.requirement }

        assertTrue(evidence.sourceSetPolicyModeled)
        assertFalse(evidence.sourceSetPolicySufficientForImplementation)
        assertEquals(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.NotSufficientForImplementation,
            rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.SourceSetAllowlistFinalized)
                .classification,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.NotSufficientForImplementation,
            rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProductionSourceSetDenylistFinalized)
                .classification,
        )
        assertContains(
            rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.SourceSetAllowlistFinalized).blockers,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.SourceSetImplementationReviewMissing,
        )
        assertFalse(rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ExecutorContractExists)
            .implementationSufficient)
        assertFalse(rows.getValue(SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.VectorCatalogExists)
            .implementationSufficient)
    }

    @Test
    fun vectorRedactionMaterialAndResultRulesAreModeledButNonAuthorizing() {
        val evidence = currentReadiness()
        val rows = evidence.requirementRows.associateBy { it.requirement }

        assertTrue(evidence.futureInputCategoriesModeled)
        assertTrue(evidence.futureOperationCategoriesModeled)
        assertTrue(evidence.vectorReferencesCataloged)
        assertTrue(evidence.negativeCasesCataloged)
        assertTrue(evidence.redactionRulesCataloged)
        assertTrue(evidence.materialRestrictionsCataloged)
        assertTrue(evidence.resultNonAuthorizationRulesModeled)

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.FutureAllowedInputLabelsFinalized,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ForbiddenInputLabelsFinalized,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.FutureAllowedOperationLabelsFinalized,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ForbiddenOperationLabelsFinalized,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PositiveVectorReferencesCataloged,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.NegativeVectorReferencesCataloged,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.RedactionVectorReferencesCataloged,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PlatformCheckReferencesCataloged,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProvenancePolicyCataloged,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.NoRawVectorMaterialInCommonMain,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.NoWalletLikeFixtures,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ResultRedactionPolicyCataloged,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ResultNonAuthorizationPolicyCataloged,
        ).forEach { requirement ->
            val row = rows.getValue(requirement)
            assertFalse(row.implementationSufficient)
            assertFalse(row.currentBranchAuthorizesImplementation)
            assertFalse(row.canAuthorizeProduction)
        }
    }

    @Test
    fun allExecutionAndAuthorizationCapabilitiesRemainFalse() {
        assertDisabled(currentReadiness().disabledCapabilities)
    }

    @Test
    fun providerSelectionVaultLifecyclePersistenceSyncAndMainnetRemainHardGates() {
        val evidence = currentReadiness()

        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertTrue(evidence.vaultLifecycleBlocked)
        assertTrue(evidence.persistenceBlocked)
        assertTrue(evidence.productionSyncBlocked)
        assertTrue(evidence.mainnetBlocked)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderSelectionDisabledProviderOnly)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionProviderSelectableFalse)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.VaultLifecycleDisabled)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.PersistenceDisabled)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionSyncDisabled)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.MainnetDisabled)
    }

    @Test
    fun warningOnlyEvidenceUserConsentAndReleaseEvidenceCannotOverride() {
        val evidence = blocked(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy.evaluateReadiness(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest(
                    includePriorModelEvidence = true,
                    warningOnlyEvidenceClaimed = true,
                    userConsentOverrideRequested = true,
                    releaseEvidenceClaimed = true,
                    safeReadinessId = "raw-path-provider-handle-crypto-object-payload-marker",
                ),
            ),
        )

        assertFalse(evidence.executorImplementationAuthorizedNow)
        assertFalse(evidence.readyForExecutorImplementationNow)
        assertFalse(evidence.executorCallableNow)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.WarningOnlyEvidenceNonAuthorizing)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.MainnetDisabled)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionSyncDisabled)
    }

    @Test
    fun futureBranchGuidanceIsExplicitAndDoesNotAuthorizeCurrentImplementation() {
        val rows = currentReadiness().futureBranchGuidance.associateBy { it.guidance }

        assertEquals(SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.requiredBeforeExecutorImplementationBranch)
            assertFalse(row.completedInCurrentBranch)
            assertFalse(row.canAuthorizeCurrentImplementation)
            assertTrue(row.safeLabel.value.isNotBlank())
        }
        assertContains(
            rows.keys,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance.SourceSetSpecificExecutorDesign,
        )
        assertContains(
            rows.keys,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance.NoWalletMaterialFixtureGuard,
        )
    }

    @Test
    fun redactedOutputUsesLabelsAndSafeIdsOnly() {
        val evidence = currentReadiness()
        val request = SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest.currentReadiness(
            safeReadinessId = "raw-path-provider-handle-crypto-object-payload-marker",
        )
        val rendered = request.toString()

        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.PolicyIdsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.ReadinessIdsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.RequirementLabelsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.BlockerLabelsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.NoRawMaterial)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.NoProviderReferences)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.NoCryptoReferences)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.NoFileLocations)
        assertFalse(rendered.contains("raw-path-provider-handle-crypto-object-payload-marker"))
        assertFalse(rendered.contains("/tmp/"))
        assertFalse(rendered.contains("provider handle"))
        assertFalse(rendered.contains("crypto object"))
        assertTrue(rendered.contains("safeReadinessId=<redacted>"))
        assertTrue(rendered.contains("providerReference=<redacted>"))
        assertTrue(rendered.contains("cryptoReference=<redacted>"))
        assertTrue(rendered.contains("fileLocation=<redacted>"))
    }

    @Test
    fun providerSelectionStillReturnsOnlyDisabledProvider() {
        val evidence = currentReadiness()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(selection.candidates.none { it.productionSelectable })
        assertFalse(selection.selectedProvider.statusReport.canDeriveKeys)
        assertFalse(selection.selectedProvider.statusReport.canEncryptRecords)
        assertFalse(selection.selectedProvider.statusReport.productionPersistenceEnabled)
        assertFalse(selection.selectedProvider.statusReport.mainnetEnabled)
    }

    @Test
    fun blockedResultWrapperDistinguishesModeledEvidenceFromImplementationAuthorization() {
        val result = SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy.evaluateReadiness()

        assertIs<SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult.Blocked<SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence>>(
            result,
        )
        assertTrue(result.value.readinessGateModeled)
        assertFalse(result.value.readyForExecutorImplementationNow)
        assertFalse(result.value.executorImplementationAuthorizedNow)
        assertContains(
            result.value.blockers,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ExecutorImplementationBranchNotAuthorized,
        )
    }

    private fun currentReadiness(): SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence =
        blocked(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy.evaluateReadiness(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest.currentReadiness(),
            ),
        )

    private fun blocked(
        result: SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult<
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence,
        >,
    ): SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence =
        when (result) {
            is SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult.Blocked -> result.value
        }

    private fun assertDisabled(capability: SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability) {
        assertTrue(capability.readinessGateModeled)
        assertFalse(capability.readyForExecutorImplementationNow)
        assertFalse(capability.executorImplementationAuthorizedNow)
        assertFalse(capability.executorCallableNow)
        assertFalse(capability.canAddRunnableInterfaceNow)
        assertFalse(capability.canUseDesktopTestExecutionNow)
        assertFalse(capability.canUseAndroidInstrumentedTestExecutionNow)
        assertFalse(capability.canUseCommonMainExecution)
        assertFalse(capability.canUseAndroidMainExecution)
        assertFalse(capability.canUseDesktopMainExecution)
        assertFalse(capability.canAcceptRawMaterial)
        assertFalse(capability.canAcceptProviderHandles)
        assertFalse(capability.canAcceptCryptoObjects)
        assertFalse(capability.canExecuteProviderOperations)
        assertFalse(capability.canExecuteRandomness)
        assertFalse(capability.canExecuteKdf)
        assertFalse(capability.canExecuteAead)
        assertFalse(capability.canExecuteHkdf)
        assertFalse(capability.canExecuteHmac)
        assertFalse(capability.canGenerateKeys)
        assertFalse(capability.canStoreKeysets)
        assertFalse(capability.canAuthorizeProviderSelection)
        assertFalse(capability.canSetProductionProviderSelectable)
        assertFalse(capability.canAuthorizeVaultCreation)
        assertFalse(capability.canAuthorizeVaultUnlock)
        assertFalse(capability.canAuthorizeVaultPersistence)
        assertFalse(capability.canAuthorizeProductionSync)
        assertFalse(capability.canAuthorizeMainnet)
    }
}
