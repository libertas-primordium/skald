package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatConfinementRule
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetDecision
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderKatSourceSetConfinementTest {
    @Test
    fun confinementBoundaryIsModeledButStillDisabled() {
        val evidence = currentConfinement()

        assertEquals(
            "skald-vault-v1-test-only-provider-kat-source-set-confinement-v1",
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.StillDisabled, evidence.status)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ConfinementBoundaryModeled,
        )
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.StillDisabled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ExecutorImplementationUnauthorized,
        )
        assertTrue(evidence.confinementModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.sourceSetCategoryModeled)
        assertFalse(evidence.sourceSetModelingAuthorizesImplementation)
        assertFalse(evidence.executorImplementationAuthorizedNow)
        assertFalse(evidence.executorCallableNow)
        assertDisabled(evidence.disabledCapabilities)
    }

    @Test
    fun productionSourceSetsAreForbiddenAndCommonTestIsModelOnly() {
        val rows = currentConfinement().sourceSetRows.associateBy { it.category }

        listOf(
            SkaldVaultV1TestOnlyProviderKatSourceSetCategory.CommonMainProductionSource,
            SkaldVaultV1TestOnlyProviderKatSourceSetCategory.AndroidMainProductionSource,
            SkaldVaultV1TestOnlyProviderKatSourceSetCategory.DesktopMainProductionSource,
        ).forEach { category ->
            val row = rows.getValue(category)
            assertTrue(row.productionForbidden)
            assertTrue(row.rawMaterialForbidden)
            assertTrue(row.currentImplementationAuthorized.not())
            assertFalse(row.currentExecutionAuthorized)
            assertTrue(row.decisions.contains(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.ProductionSourceForbidden))
            assertContains(
                row.blockers,
                SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionProviderSelectableFalse,
            )
        }

        val commonTest = rows.getValue(SkaldVaultV1TestOnlyProviderKatSourceSetCategory.CommonTestModelOnlyAssertions)
        assertTrue(commonTest.modelOnly)
        assertFalse(commonTest.currentExecutionAuthorized)
        assertFalse(commonTest.currentImplementationAuthorized)
        assertContains(commonTest.decisions, SkaldVaultV1TestOnlyProviderKatSourceSetDecision.CommonTestAssertionsAllowed)
        assertContains(
            commonTest.decisions,
            SkaldVaultV1TestOnlyProviderKatSourceSetDecision.NotCurrentImplementationAuthorization,
        )
    }

    @Test
    fun futureTestSourceSetsRequireReviewAndAreNotAuthorizedNow() {
        val rows = currentConfinement().sourceSetRows.associateBy { it.category }

        listOf(
            SkaldVaultV1TestOnlyProviderKatSourceSetCategory.DesktopTestFutureExecutorCandidate,
            SkaldVaultV1TestOnlyProviderKatSourceSetCategory.AndroidInstrumentedTestFutureExecutorCandidate,
        ).forEach { category ->
            val row = rows.getValue(category)
            assertTrue(row.futureReviewRequired)
            assertFalse(row.currentImplementationAuthorized)
            assertFalse(row.currentExecutionAuthorized)
            assertFalse(row.productionForbidden)
            assertContains(
                row.blockers,
                SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NoExecutorImplementation,
            )
        }

        assertContains(
            rows.getValue(SkaldVaultV1TestOnlyProviderKatSourceSetCategory.DesktopTestFutureExecutorCandidate)
                .decisions,
            SkaldVaultV1TestOnlyProviderKatSourceSetDecision.DesktopTestFutureReviewRequired,
        )
        assertContains(
            rows.getValue(SkaldVaultV1TestOnlyProviderKatSourceSetCategory.AndroidInstrumentedTestFutureExecutorCandidate)
                .decisions,
            SkaldVaultV1TestOnlyProviderKatSourceSetDecision.AndroidInstrumentedTestFutureReviewRequired,
        )
    }

    @Test
    fun docsResourcesAndPackagingRemainNonAuthorizing() {
        val evidence = currentConfinement()
        val rows = evidence.sourceSetRows.associateBy { it.category }

        val docs = rows.getValue(SkaldVaultV1TestOnlyProviderKatSourceSetCategory.DocsNonAuthorizingEvidence)
        assertTrue(evidence.docsEvidenceOnly)
        assertTrue(docs.evidenceOnly)
        assertFalse(docs.currentImplementationAuthorized)
        assertFalse(docs.currentExecutionAuthorized)
        assertContains(docs.decisions, SkaldVaultV1TestOnlyProviderKatSourceSetDecision.DocsEvidenceOnly)

        val resources = rows.getValue(SkaldVaultV1TestOnlyProviderKatSourceSetCategory.ResourcesForbiddenForRawMaterial)
        assertTrue(evidence.resourcesRawMaterialForbidden)
        assertTrue(resources.rawMaterialForbidden)
        assertContains(resources.decisions, SkaldVaultV1TestOnlyProviderKatSourceSetDecision.RawFixtureResourcesForbidden)

        val packaging = rows.getValue(SkaldVaultV1TestOnlyProviderKatSourceSetCategory.PackagingOutputsForbidden)
        assertTrue(evidence.packagingOutputsNonAuthorizing)
        assertTrue(packaging.runtimeArtifactNonAuthorizing)
        assertFalse(packaging.currentExecutionAuthorized)
        assertContains(
            packaging.decisions,
            SkaldVaultV1TestOnlyProviderKatSourceSetDecision.RuntimeOutputArtifactsNonAuthorizing,
        )
    }

    @Test
    fun confinementRulesAndForbiddenProductionTokensAreListedWithoutAuthorization() {
        val evidence = currentConfinement()
        val rules = evidence.confinementRuleRows.associateBy { it.rule }
        val tokens = evidence.forbiddenProductionTokenRows.associateBy { it.tokenClass }

        assertEquals(SkaldVaultV1TestOnlyProviderKatConfinementRule.entries.toSet(), rules.keys)
        assertEquals(SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.entries.toSet(), tokens.keys)

        rules.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.currentBranchSatisfiedForModelOnlyEvidence)
            assertFalse(row.currentBranchAuthorizesImplementation)
            assertTrue(row.safeRuleId.value.startsWith("confinement-rule-"))
        }
        tokens.values.forEach { row ->
            assertTrue(row.productionSourceSetForbidden)
            assertFalse(row.currentSourcePresent)
            assertFalse(row.canAuthorizeImplementation)
            assertTrue(row.safeTokenId.value.startsWith("forbidden-token-"))
        }

        assertContains(rules.keys, SkaldVaultV1TestOnlyProviderKatConfinementRule.FutureExecutorAbsentFromCommonMain)
        assertContains(
            rules.keys,
            SkaldVaultV1TestOnlyProviderKatConfinementRule
                .FutureExecutorMayBeConsideredOnlyInDesktopTestAfterExplicitBranchApproval,
        )
        assertContains(
            rules.keys,
            SkaldVaultV1TestOnlyProviderKatConfinementRule.FutureRawMaterialMustNotLiveInProductionResources,
        )
        assertContains(
            tokens.keys,
            SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.ProviderKatExecutorImplementation,
        )
        assertContains(tokens.keys, SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.RunnableExecutorInterface)
        assertContains(tokens.keys, SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.RawVectorMaterial)
        assertContains(tokens.keys, SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.MainnetHook)
    }

    @Test
    fun sourceGuardRequirementsAreExplicitAndNonAuthorizing() {
        val rows = currentConfinement().sourceGuardRequirementRows.associateBy { it.requirement }

        assertEquals(SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.requiredForFutureExecutorBranch)
            assertFalse(row.currentBranchCanAuthorizeImplementation)
            assertContains(row.blockers, SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceGuardsStillRequired)
            assertTrue(row.safeGuardId.value.startsWith("source-guard-"))
        }

        assertContains(
            rows.keys,
            SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.CryptoImportsAbsentFromCommonMainSecurityBoundaries,
        )
        assertContains(rows.keys, SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.RunnableExecutorMethodNamesAbsent)
        assertContains(rows.keys, SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.RawVectorFieldNamesAbsent)
        assertContains(rows.keys, SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.ProviderSelectionRemainsDisabled)
        assertContains(
            rows.keys,
            SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.ProductionProviderSelectableFalseAsserted,
        )
    }

    @Test
    fun authorizationLimitsBlockExecutionSelectionVaultSyncAndMainnet() {
        val evidence = currentConfinement()
        val rows = evidence.authorizationLimitRows.associateBy { it.limit }

        assertEquals(SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertFalse(row.sourceSetConfinementEvidenceCanAuthorize)
            assertFalse(row.currentConfinementCanAuthorize)
            assertContains(
                row.blockers,
                SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceSetConfinementModeledOnly,
            )
        }

        listOf(
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.ExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.ExecutorExecution,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.ProviderOperationExecution,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.ProviderSelection,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.ProductionProviderSelectableTrue,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.VaultCreation,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.VaultUnlock,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.VaultPersistence,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.SecureStorageSuccess,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.SecureMetadataSuccess,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.ProductionSync,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.Signing,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.Broadcasting,
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.Mainnet,
        ).forEach { limit ->
            assertFalse(rows.getValue(limit).currentConfinementCanAuthorize)
        }

        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertTrue(evidence.vaultLifecycleBlocked)
        assertTrue(evidence.persistenceBlocked)
        assertTrue(evidence.productionSyncBlocked)
        assertTrue(evidence.mainnetBlocked)
    }

    @Test
    fun allDisabledCapabilitiesRemainFalseExceptConfinementModeled() {
        assertDisabled(currentConfinement().disabledCapabilities)
    }

    @Test
    fun warningOnlyEvidenceUserConsentAndReleaseEvidenceCannotOverride() {
        val evidence = blocked(
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy.evaluateConfinement(
                SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest(
                    includeSourceSetEvidence = true,
                    warningOnlyEvidenceClaimed = true,
                    userConsentOverrideRequested = true,
                    releaseEvidenceClaimed = true,
                    safeConfinementId = "raw-location-provider-reference-crypto-reference-payload-marker",
                ),
            ),
        )

        assertFalse(evidence.executorImplementationAuthorizedNow)
        assertFalse(evidence.executorCallableNow)
        assertFalse(evidence.sourceSetModelingAuthorizesImplementation)
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.WarningOnlyEvidenceNonAuthorizing,
        )
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.MainnetDisabled)
    }

    @Test
    fun redactedOutputContainsOnlyLabelsAndSafeIds() {
        val evidence = currentConfinement()
        val request = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest.currentConfinement(
            safeConfinementId = "raw-location-provider-reference-crypto-reference-payload-marker",
        )
        val rendered = request.toString()

        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.PolicyIdsOnly)
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.ConfinementIdsOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.SourceSetLabelsOnly,
        )
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.NoRawMaterial)
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.NoProviderReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.NoCryptoReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.NoLocationReferences,
        )
        assertFalse(rendered.contains("raw-location-provider-reference-crypto-reference-payload-marker"))
        assertFalse(rendered.contains("/tmp/"))
        assertFalse(rendered.contains("provider handle"))
        assertFalse(rendered.contains("crypto object"))
        assertTrue(rendered.contains("safeConfinementId=<redacted>"))
        assertTrue(rendered.contains("providerReference=<redacted>"))
        assertTrue(rendered.contains("cryptoReference=<redacted>"))
        assertTrue(rendered.contains("locationReference=<redacted>"))
    }

    @Test
    fun providerSelectionStillReturnsOnlyDisabledProvider() {
        val evidence = currentConfinement()
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
    fun blockedResultDistinguishesModeledSourceSetEvidenceFromImplementationAuthorization() {
        val result = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy.evaluateConfinement()

        assertIs<
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult.Blocked<
                SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence,
            >,
        >(result)
        assertTrue(result.value.confinementModeled)
        assertFalse(result.value.sourceSetModelingAuthorizesImplementation)
        assertFalse(result.value.executorImplementationAuthorizedNow)
        assertContains(
            result.value.blockers,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ExecutorImplementationBranchNotAuthorized,
        )
    }

    private fun currentConfinement(): SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence =
        blocked(
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy.evaluateConfinement(
                SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest.currentConfinement(),
            ),
        )

    private fun blocked(
        result: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult<
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence,
        >,
    ): SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence =
        when (result) {
            is SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult.Blocked -> result.value
        }

    private fun assertDisabled(capability: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability) {
        assertTrue(capability.confinementModeled)
        assertFalse(capability.executorImplementationAuthorizedNow)
        assertFalse(capability.executorCallableNow)
        assertFalse(capability.canUseCommonMainExecution)
        assertFalse(capability.canUseAndroidMainExecution)
        assertFalse(capability.canUseDesktopMainExecution)
        assertFalse(capability.canUseCommonTestExecution)
        assertFalse(capability.canUseDesktopTestExecutionNow)
        assertFalse(capability.canUseAndroidInstrumentedTestExecutionNow)
        assertFalse(capability.canAddRunnableInterfaceNow)
        assertFalse(capability.canAddRawVectorMaterialNow)
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
