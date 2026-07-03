package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacement
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenReachability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityPlacementRule
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetRole
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentitySourceSetConfinementTest {
    @Test
    fun currentSourceSetConfinementEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-source-set-confinement-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.sourceSetConfinementModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.commonMainModelOnlyPolicyAllowed)
        assertTrue(evidence.docsMayDescribeBlockedPlacement)
        assertTrue(evidence.testsMayAssertBlockedPlacement)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentitySourceSetStatus.SourceSetConfinementModeled in evidence.statuses,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentitySourceSetStatus.StillDisabled in evidence.statuses)
        assertFalse(evidence.sourceSetEvidenceAuthorizesImplementation)
        assertFalse(evidence.sourceSetEvidenceAuthorizesProductionReachability)
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoTestOnlyProviderIdentityImplementation in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoProductionProviderIdentityImplementation in
                evidence.blockers,
        )
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenPriorEvidenceIsComposed() {
        val decision = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val isolation = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val namespace = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .evaluateSourceSetConfinement(
                SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                ),
            )
        val selected = VaultCryptoProviderSelectionRegistry.select()

        assertIs<DisabledVaultCryptoProvider>(selected.selectedProvider)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selected.selectedCandidateId)
        assertFalse(selected.productionProviderSelectable)
        assertTrue(evidence.priorIdentityDecisionEvidenceIncluded)
        assertTrue(evidence.priorIsolationEvidenceIncluded)
        assertTrue(evidence.priorNamespaceEvidenceIncluded)
        assertFalse(decision.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(isolation.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(namespace.disabledCapabilities.canUseAsProviderSelectionId)
        assertFalse(evidence.capabilities.canUseAsProviderSelectionId)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()

        assertAllCapabilityBooleansFalse(evidence.capabilities)
    }

    @Test
    fun allSourceSetCategoriesAreRepresentedExactlyOnce() {
        val rows = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .currentSourceSetConfinementEvidence()
            .sourceSetCategories

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.entries.toSet(),
            rows.map { it.category }.toSet(),
        )
    }

    @Test
    fun allPlacementRulesAreRepresentedExactlyOnce() {
        val rows = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .currentSourceSetConfinementEvidence()
            .placementRules

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityPlacementRule.entries.size, rows.size)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityPlacementRule.entries.toSet(), rows.map { it.rule }.toSet())
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.satisfiedForModelOnlyBoundary })
        assertTrue(rows.none { it.authorizesImplementation })
        assertTrue(rows.none { it.authorizesProductionReachability })
    }

    @Test
    fun allForbiddenPlacementsAreRepresentedExactlyOnce() {
        val rows = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .currentSourceSetConfinementEvidence()
            .forbiddenPlacements

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacement.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacement.entries.toSet(),
            rows.map { it.placement }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentPlacementPresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenReachabilityPathsAreRepresentedExactlyOnce() {
        val rows = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .currentSourceSetConfinementEvidence()
            .forbiddenReachabilityPaths

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityForbiddenReachability.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenReachability.entries.toSet(),
            rows.map { it.reachability }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentReachabilityPresent })
        assertTrue(rows.none { it.authorizesProductionReachability })
    }

    @Test
    fun commonMainModelPolicyAllowsOnlyModelPolicyEvidence() {
        val row = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.CommonMainModelPolicy)

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.ModelOnlyPolicySource, row.role)
        assertTrue(row.modelOnlyPolicyAllowed)
        assertFalse(row.futureReviewOnly)
        assertFalse(row.productionRuntimeForbidden)
        assertFalse(row.testOnlyIdentityImplementationPresent)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionReachability)
    }

    @Test
    fun androidMainAndDesktopMainProductionSourcesForbidTestOnlyIdentityImplementation() {
        val androidMain = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.AndroidMainProductionSource)
        val desktopMain = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DesktopMainProductionSource)

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.ProductionRuntimeSource, androidMain.role)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.ProductionRuntimeSource, desktopMain.role)
        assertTrue(androidMain.productionRuntimeForbidden)
        assertTrue(desktopMain.productionRuntimeForbidden)
        assertFalse(androidMain.testOnlyIdentityImplementationPresent)
        assertFalse(desktopMain.testOnlyIdentityImplementationPresent)
        assertFalse(androidMain.authorizesImplementation)
        assertFalse(desktopMain.authorizesImplementation)
    }

    @Test
    fun testSourcesAreFutureReviewOnlyAndDoNotAuthorizeProductionReachability() {
        val commonTest = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.CommonTestSource)
        val desktopTest = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DesktopTestSource)
        val instrumentedTest =
            sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.AndroidInstrumentedTestSource)

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.TestOnlySource, commonTest.role)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.TestOnlySource, desktopTest.role)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.InstrumentedTestOnlySource,
            instrumentedTest.role,
        )
        listOf(commonTest, desktopTest, instrumentedTest).forEach { row ->
            assertTrue(row.futureReviewOnly)
            assertFalse(row.testOnlyIdentityImplementationPresent)
            assertFalse(row.authorizesImplementation)
            assertFalse(row.authorizesProductionReachability)
        }
    }

    @Test
    fun buildScriptsDoNotActivateProviderIdentityDependencies() {
        val row = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.BuildScriptSource)

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.BuildConfigurationSource, row.role)
        assertTrue(row.productionRuntimeForbidden)
        assertFalse(row.testOnlyIdentityImplementationPresent)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionReachability)
    }

    @Test
    fun documentationMayOnlyDescribeBlockedPlacementAndDoesNotAuthorizeImplementation() {
        val row = sourceSetRow(SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DocumentationSource)
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.DocumentationOnlySource, row.role)
        assertTrue(evidence.docsMayDescribeBlockedPlacement)
        assertFalse(row.testOnlyIdentityImplementationPresent)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionReachability)
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilities() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .evaluateSourceSetConfinement(
                SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    commonMainImplementationClaimed = true,
                    androidMainImplementationClaimed = true,
                    desktopMainImplementationClaimed = true,
                    productionSourceAliasClaimed = true,
                    registryReachabilityClaimed = true,
                    factoryReachabilityClaimed = true,
                    dispatcherReachabilityClaimed = true,
                    executorTargetReachabilityClaimed = true,
                    persistenceReachabilityClaimed = true,
                    settingsReachabilityClaimed = true,
                    uiReachabilityClaimed = true,
                    mainnetReachabilityClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.WarningOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.TestOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.CommonMainImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.AndroidMainImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.DesktopMainImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProductionSourceAliasRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.RegistryReachabilityRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.FactoryReachabilityRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.DispatcherReachabilityRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ExecutorTargetReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.PersistenceReachabilityRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.SettingsReachabilityRejected in evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.UiReachabilityRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.MainnetReachabilityRejected in evidence.blockers,
        )
    }

    @Test
    fun priorIdentityDecisionEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .evaluateSourceSetConfinement(
                SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = false,
                    includePriorNamespaceEvidence = false,
                ),
            )
        val row = evidenceSourceRow(
            evidenceSource = SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.TestOnlyProviderIdentityDecision,
            request = evidence,
        )

        assertTrue(evidence.priorIdentityDecisionEvidenceIncluded)
        assertTrue(evidence.priorIdentityDecisionEvidenceNonAuthorizing)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionReachability)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorIdentityIsolationEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .evaluateSourceSetConfinement(
                SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
                    includePriorIdentityDecisionEvidence = false,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = false,
                ),
            )
        val row = evidenceSourceRow(
            evidenceSource =
                SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.TestOnlyProviderIdentityIsolationGuard,
            request = evidence,
        )

        assertTrue(evidence.priorIsolationEvidenceIncluded)
        assertTrue(evidence.priorIsolationEvidenceNonAuthorizing)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionReachability)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorSyntheticIdentityNamespaceEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .evaluateSourceSetConfinement(
                SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
                    includePriorIdentityDecisionEvidence = false,
                    includePriorIsolationEvidence = false,
                    includePriorNamespaceEvidence = true,
                ),
            )
        val row = evidenceSourceRow(
            evidenceSource = SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.SyntheticIdentityNamespace,
            request = evidence,
        )

        assertTrue(evidence.priorNamespaceEvidenceIncluded)
        assertTrue(evidence.priorNamespaceEvidenceNonAuthorizing)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionReachability)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
            commonMainImplementationClaimed = true,
            productionSourceAliasClaimed = true,
            registryReachabilityClaimed = true,
            persistenceReachabilityClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label = SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel("common-main").toString()

        val forbiddenTerms = listOf(
            "common-main",
            "source",
            "placement",
            "identity",
            "provider",
            "storage",
            "backend",
            "crypto",
            "path",
            "endpoint",
            "wallet",
            "descriptor",
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun sourceSetRow(category: SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory) =
        SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
            .currentSourceSetConfinementEvidence()
            .sourceSetCategories
            .single { it.category == category }

    private fun evidenceSourceRow(
        evidenceSource: SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource,
        request: com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementEvidence,
    ) = request.evidenceSources.single { it.source == evidenceSource }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentitySourceSetCapabilities,
    ) {
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
}
