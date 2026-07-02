package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultProviderTestOnlyExecutableKatScopeDecisionTest {
    @Test
    fun scopeDecisionIsModeledButStillDisabled() {
        val evidence = currentDecision()

        assertEquals(
            "skald-vault-v1-provider-test-only-executable-kat-scope-decision-v1",
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.StillDisabled, evidence.status)
        assertTrue(evidence.scopeDecisionModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.futureTestOnlyPathAllowedInPrinciple)
        assertTrue(evidence.futureTestOnlyPathRequiresLaterBranch)
        assertFalse(evidence.currentBranchExecutionAuthorized)
        assertFalse(evidence.testOnlyExecutorImplementationPresent)
        assertFalse(evidence.productionExecutorAllowed)
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.ScopeDecisionModeled,
        )
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.StillDisabled,
        )
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus
                .FutureTestOnlyPathPermittedInPrinciple,
        )
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.CurrentBranchExecutionNotAuthorized,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.NoTestOnlyExecutorImplementationInThisBranch,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ExecutableKatImplementationDeferred,
        )
        assertDisabled(evidence.disabledCapabilities)
    }

    @Test
    fun futureScopeIsSeparateFromCurrentAuthorization() {
        val evidence = currentDecision()
        val sourceRows = evidence.sourceSetRows.associateBy { it.category }

        assertTrue(evidence.futureTestOnlyPathAllowedInPrinciple)
        assertTrue(evidence.disabledCapabilities.futureTestOnlyPathAllowedInPrinciple)
        assertTrue(evidence.disabledCapabilities.futurePermissionRequiresLaterBranch)
        assertFalse(evidence.disabledCapabilities.canImplementTestOnlyExecutorNow)
        assertFalse(evidence.disabledCapabilities.canRunTestOnlyProviderKatNow)
        assertFalse(evidence.currentBranchExecutionAuthorized)

        listOf(
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.DesktopTestOnly,
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.AndroidInstrumentedTestOnly,
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.CommonTestModelAssertionsOnly,
        ).forEach { category ->
            val row = sourceRows.getValue(category)
            assertTrue(row.futureScopeAllowedInPrinciple)
            assertFalse(row.currentExecutionAuthorized)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.NoTestOnlyExecutorImplementationInThisBranch,
            )
        }
    }

    @Test
    fun allCurrentExecutionAndAuthorizationCapabilitiesRemainFalse() {
        val capability = currentDecision().disabledCapabilities

        assertFalse(capability.canImplementTestOnlyExecutorNow)
        assertFalse(capability.canRunTestOnlyProviderKatNow)
        assertFalse(capability.canRunProductionProviderKatNow)
        assertFalse(capability.canUseCommonMainExecution)
        assertFalse(capability.canUseAndroidMainExecution)
        assertFalse(capability.canUseDesktopMainExecution)
        assertFalse(capability.canUseDesktopTestExecutionNow)
        assertFalse(capability.canUseAndroidInstrumentedTestExecutionNow)
        assertFalse(capability.canExecuteProviderOperationsNow)
        assertFalse(capability.canExecuteRandomnessNow)
        assertFalse(capability.canExecuteKdfNow)
        assertFalse(capability.canExecuteAeadNow)
        assertFalse(capability.canAuthorizeProviderSelection)
        assertFalse(capability.canSetProductionProviderSelectable)
        assertFalse(capability.canAuthorizeVaultCreation)
        assertFalse(capability.canAuthorizeVaultUnlock)
        assertFalse(capability.canAuthorizeVaultPersistence)
        assertFalse(capability.canAuthorizeProductionSync)
        assertFalse(capability.canAuthorizeMainnet)
    }

    @Test
    fun productionSourceSetsAreForbiddenAndTestSourceSetsDoNotExecuteNow() {
        val rows = currentDecision().sourceSetRows.associateBy { it.category }

        listOf(
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.ProductionCommonMainForbidden,
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.ProductionAndroidMainForbidden,
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.ProductionDesktopMainForbidden,
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory
                .BuildScriptsForbiddenExceptExplicitDependencyDeclarations,
        ).forEach { category ->
            val row = rows.getValue(category)
            assertFalse(row.futureScopeAllowedInPrinciple)
            assertFalse(row.currentExecutionAuthorized)
            assertTrue(row.productionSourceForbidden)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProductionProviderSelectableFalse,
            )
        }

        assertFalse(
            rows.getValue(SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.DesktopTestOnly)
                .currentExecutionAuthorized,
        )
        assertFalse(
            rows.getValue(SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.AndroidInstrumentedTestOnly)
                .currentExecutionAuthorized,
        )
    }

    @Test
    fun operationScopeAllowsOnlyFutureTestEvidenceAndForbidsProductionUse() {
        val rows = currentDecision().operationRows.associateBy { it.category }
        val futureOnly = setOf(
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.PublicNonWalletPositiveVectors,
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.PublicNonWalletNegativeVectors,
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.FixedDeterministicProviderVectors,
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory
                .RandomizedBehavioralAeadChecksWithTestOnlyGeneratedMaterial,
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.RedactionAssertions,
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.ProviderSelfTestRoutingAssertions,
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.PlatformRuntimeChecksInTestSourceSetsOnly,
        )

        futureOnly.forEach { category ->
            val row = rows.getValue(category)
            assertTrue(row.futureTestOnlyAllowedInPrinciple)
            assertFalse(row.currentExecutionAuthorized)
            assertFalse(row.productionAllowed)
            assertFalse(row.forbiddenEvenForTestOnly)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing,
            )
        }

        SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.entries
            .filter { it !in futureOnly }
            .forEach { category ->
                val row = rows.getValue(category)
                assertFalse(row.futureTestOnlyAllowedInPrinciple)
                assertFalse(row.currentExecutionAuthorized)
                assertFalse(row.productionAllowed)
                assertTrue(row.forbiddenEvenForTestOnly)
            }

        assertTrue(
            rows.getValue(SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.RealWalletMaterial)
                .forbiddenEvenForTestOnly,
        )
        assertTrue(
            rows.getValue(SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.Mainnet)
                .forbiddenEvenForTestOnly,
        )
    }

    @Test
    fun testOnlyWarningConsentAndReleaseEvidenceCannotAuthorizeProduction() {
        val evidence = blocked(
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy.evaluate(
                SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest(
                    includeFutureScope = true,
                    warningOnlyEvidenceClaimed = true,
                    userConsentOverrideRequested = true,
                    releaseMainnetEvidenceClaimed = true,
                    safeDecisionId = "runtime-payload-not-rendered",
                ),
            ),
        )

        assertFalse(evidence.testOnlyKatResultsCanAuthorizeProductionProviderSelection)
        assertFalse(evidence.testOnlyKatResultsCanSetProductionProviderSelectable)
        assertFalse(evidence.testOnlyKatResultsCanAuthorizeVaultLifecycle)
        assertFalse(evidence.testOnlyKatResultsCanAuthorizeProductionSync)
        assertFalse(evidence.testOnlyKatResultsCanAuthorizeMainnet)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.WarningOnlyEvidenceNonAuthorizing,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.MainnetDisabled)
        assertDisabled(evidence.disabledCapabilities)
    }

    @Test
    fun vaultLifecycleProductionSyncAndMainnetStayBlocked() {
        val evidence = currentDecision()

        assertTrue(evidence.vaultCreationBlocked)
        assertTrue(evidence.vaultUnlockBlocked)
        assertTrue(evidence.vaultPersistenceBlocked)
        assertTrue(evidence.productionSyncBlocked)
        assertTrue(evidence.mainnetBlocked)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultCreation)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultUnlock)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultPersistence)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProductionSync)
        assertFalse(evidence.disabledCapabilities.canAuthorizeMainnet)
    }

    @Test
    fun materialRulesAllowOnlyFuturePublicNonWalletAndSyntheticTestClasses() {
        val rows = currentDecision().materialRuleRows.associateBy { it.materialClass }
        val futureAllowed = rows.values
            .filter { it.futureTestOnlyAllowedInPrinciple }
            .map { it.materialClass }
            .toSet()

        assertEquals(
            setOf(
                SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.PublicNonWalletVectors,
                SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.ExplicitTestOnlyGeneratedMaterial,
                SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.DeterministicNonWalletFixtures,
                SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RedactedDiagnostics,
                SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.SyntheticSafeIds,
            ),
            futureAllowed,
        )
        rows.values.forEach { row ->
            assertFalse(row.currentExecutionAuthorized)
            assertFalse(row.productionAllowed)
        }
        listOf(
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.UserWalletMaterial,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RealSeeds,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RealMnemonics,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RealDescriptors,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RealXprvTprvWifMaterial,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.NostrNsecValues,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.LightningCredentials,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.CashuProofs,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.BackendCredentials,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RealWalletLabels,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.RealUtxoLabels,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.TransactionNotes,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.BackendObservationMetadata,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.SecureMetadataRecords,
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.ProductionVaultRecords,
        ).forEach { materialClass ->
            assertFalse(rows.getValue(materialClass).futureTestOnlyAllowedInPrinciple)
        }
    }

    @Test
    fun futureEvidenceGatesAreListedButDoNotAuthorizeExecution() {
        val summary = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy.currentPolicySummary()
        val evidence = currentDecision()
        val gates = evidence.evidenceGateRows.associateBy { it.gate }

        assertEquals(SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.entries.toSet(), summary.evidenceGates)
        assertEquals(SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.entries.toSet(), gates.keys)
        gates.values.forEach { row ->
            assertTrue(row.requiredBeforeFutureExecutor)
            assertFalse(row.authorizesCurrentExecution)
            assertFalse(row.authorizesProduction)
            assertTrue(row.safeGateId.startsWith("scope-gate-"))
        }
        assertEquals(
            SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.PartiallyModeled,
            gates.getValue(
                SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.SourceSetConfinementPolicyComplete,
            ).classification,
        )
        assertFalse(
            gates.getValue(
                SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.SourceSetConfinementPolicyComplete,
            ).authorizesCurrentExecution,
        )
        assertEquals(
            SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.FutureRequired,
            gates.getValue(SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.TestOnlyExecutorDesignReviewed)
                .classification,
        )
        assertTrue(evidence.futureRequiredWork.isNotEmpty())
        evidence.futureRequiredWork.forEach { item ->
            assertTrue(item.requiredBeforeAnyExecutorBranch)
            assertTrue(item.requiredBeforeProductionProviderSelection)
            assertFalse(item.authorizesCurrentExecution)
        }
    }

    @Test
    fun modeledEvidenceExistsButExecutionIsNotAuthorized() {
        val gates = currentDecision().evidenceGateRows.associateBy { it.gate }
        val prerequisiteAudit = gates.getValue(
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.PrerequisiteAuditComplete,
        )
        val selectionStillDisabled = gates.getValue(
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.ProviderSelectionStillDisabledProviderOnly,
        )
        val nonAuthorizingResults = gates.getValue(
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.TestResultNonAuthorizingPolicyComplete,
        )

        assertTrue(prerequisiteAudit.currentEvidenceExists)
        assertFalse(prerequisiteAudit.authorizesCurrentExecution)
        assertFalse(prerequisiteAudit.authorizesProduction)
        assertTrue(selectionStillDisabled.currentEvidenceExists)
        assertFalse(selectionStillDisabled.authorizesCurrentExecution)
        assertTrue(nonAuthorizingResults.currentEvidenceExists)
        assertFalse(nonAuthorizingResults.authorizesCurrentExecution)
    }

    @Test
    fun providerSelectionStillReturnsOnlyDisabledProvider() {
        val evidence = currentDecision()
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
    fun redactedOutputContainsNoPayloadPathsProviderReferencesOrCryptoReferences() {
        val request = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest.currentScope(
            safeDecisionId = "payload-raw-path-provider-handle-crypto-object-marker",
        )
        val evidence = blocked(SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy.evaluate(request))
        val rendered = request.toString() + evidence.redactionClasses.joinToString { it.name }

        assertFalse(rendered.contains("payload-raw-path-provider-handle-crypto-object-marker"))
        assertFalse(rendered.contains("/tmp/"))
        assertFalse(rendered.contains("provider handle"))
        assertFalse(rendered.contains("crypto object"))
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.PolicyIdsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.SafeIdsOnly)
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.NoRawMaterial,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.NoProviderReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.NoCryptoReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.NoFilesystemLocations,
        )
    }

    private fun currentDecision(): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence =
        blocked(
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy.evaluate(
                SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest.currentScope(),
            ),
        )

    private fun blocked(
        result: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult<
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence,
        >,
    ): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence =
        when (result) {
            is SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult.Blocked -> result.value
        }

    private fun assertDisabled(capability: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability) {
        assertFalse(capability.canImplementTestOnlyExecutorNow)
        assertFalse(capability.canRunTestOnlyProviderKatNow)
        assertFalse(capability.canRunProductionProviderKatNow)
        assertFalse(capability.canUseCommonMainExecution)
        assertFalse(capability.canUseAndroidMainExecution)
        assertFalse(capability.canUseDesktopMainExecution)
        assertFalse(capability.canUseDesktopTestExecutionNow)
        assertFalse(capability.canUseAndroidInstrumentedTestExecutionNow)
        assertFalse(capability.canExecuteProviderOperationsNow)
        assertFalse(capability.canExecuteRandomnessNow)
        assertFalse(capability.canExecuteKdfNow)
        assertFalse(capability.canExecuteAeadNow)
        assertFalse(capability.canAuthorizeProviderSelection)
        assertFalse(capability.canSetProductionProviderSelectable)
        assertFalse(capability.canAuthorizeVaultCreation)
        assertFalse(capability.canAuthorizeVaultUnlock)
        assertFalse(capability.canAuthorizeVaultPersistence)
        assertFalse(capability.canAuthorizeProductionSync)
        assertFalse(capability.canAuthorizeMainnet)
    }
}
