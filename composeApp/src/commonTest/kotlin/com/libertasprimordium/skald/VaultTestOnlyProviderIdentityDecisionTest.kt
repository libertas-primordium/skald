package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityConstraint
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidance
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityRedactionClass
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityDecisionTest {
    @Test
    fun identityDecisionIsModeledButStillDisabled() {
        val decision = currentIdentityDecision()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-decision-v1",
            SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.POLICY_VERSION)
        assertTrue(decision.modeledButStillDisabled)
        assertContains(
            decision.statuses,
            SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.IdentityDecisionModeled,
        )
        assertContains(decision.statuses, SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.StillDisabled)
        assertContains(
            decision.statuses,
            SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.NoTestOnlyProviderImplemented,
        )
        assertContains(decision.statuses, SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.NoExecutorTarget)
        assertContains(decision.statuses, SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.MainnetBlocked)
        assertTrue(decision.futureTestOnlyIdentityAllowedInPrinciple)
        assertFalse(decision.testOnlyProviderIdentityImplemented)
        assertFalse(decision.productionProviderIdentityImplemented)
        assertTrue(decision.currentSelectableIdentityDisabledProviderOnly)
        assertFalse(decision.productionProviderSelectable)
    }

    @Test
    fun noProviderIdentityOrExecutorTargetIsAvailableNow() {
        val capability = currentIdentityDecision().disabledCapabilities

        assertTrue(capability.identityDecisionModeled)
        assertFalse(capability.testOnlyProviderIdentityImplemented)
        assertFalse(capability.productionProviderIdentityImplemented)
        assertFalse(capability.providerFactoryAvailable)
        assertFalse(capability.providerDispatcherAvailable)
        assertFalse(capability.nonDisabledRegistryEntryAvailable)
        assertFalse(capability.executorTargetAvailable)
        assertFalse(capability.canImplementProviderNow)
        assertFalse(capability.canInstantiateProviderNow)
        assertFalse(capability.canRegisterProviderNow)
        assertFalse(capability.canDispatchProviderNow)
        assertFalse(capability.canTargetProviderWithExecutorNow)
        assertFalse(capability.canUseProviderForKatNow)
    }

    @Test
    fun currentSelectableIdentityRemainsDisabledProviderOnly() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(currentIdentityDecision().disabledCapabilities.canAuthorizeProviderSelection)
    }

    @Test
    fun futureTestOnlyIdentityCategoriesAreModeledButNotImplemented() {
        val categoryRows = currentIdentityDecision().identityCategoryRows.associateBy { it.category }

        listOf(
            SkaldVaultV1TestOnlyProviderIdentityCategory.FutureTestOnlyDeterministicProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityCategory.FutureTestOnlyRandomizedBehaviorProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityCategory.FutureTestOnlyPlatformRuntimeProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityCategory.FutureProductionCandidateProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityCategory.FutureAndroidWrappingProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityCategory.FutureLinuxPassphraseFirstProviderIdentity,
        ).forEach { category ->
            val row = requireNotNull(categoryRows[category])
            assertTrue(row.modeled)
            assertFalse(row.implementedNow)
            assertFalse(row.registrySelectable)
            assertFalse(row.productionSelectable)
            assertFalse(row.factoryAvailable)
            assertFalse(row.dispatcherAvailable)
            assertFalse(row.executorTargetAvailable)
            assertFalse(row.canUseForKatNow)
            assertFalse(row.authorizesProduction)
        }

        assertEquals(
            true,
            requireNotNull(categoryRows[SkaldVaultV1TestOnlyProviderIdentityCategory.DisabledProviderIdentity])
                .implementedNow,
        )
    }

    @Test
    fun futureTestOnlyIdentitiesCannotReachVaultLifecycleOrPersistence() {
        val decision = currentIdentityDecision()

        decision.identityCategoryRows
            .filter { it.category.testOnlyIdentity }
            .forEach { row ->
                assertFalse(row.canUseForVaultCreation)
                assertFalse(row.canUseForVaultUnlock)
                assertFalse(row.canUseForVaultPersistence)
            }
        assertFalse(decision.disabledCapabilities.canUseProviderForVaultCreation)
        assertFalse(decision.disabledCapabilities.canUseProviderForVaultUnlock)
        assertFalse(decision.disabledCapabilities.canUseProviderForVaultPersistence)
        assertFalse(decision.disabledCapabilities.canAuthorizeVaultCreation)
        assertFalse(decision.disabledCapabilities.canAuthorizeVaultUnlock)
        assertFalse(decision.disabledCapabilities.canAuthorizeVaultPersistence)
        assertTrue(decision.vaultLifecycleBlocked)
        assertTrue(decision.persistenceBlocked)
    }

    @Test
    fun identityEvidenceCannotAuthorizeProductionSelectionOrMainnet() {
        val decision = currentIdentityDecision()

        assertAuthorizationLimitBlocked(
            decision.authorizationLimitRows.associateBy { it.authorizationLimit },
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.ProviderSelection,
        )
        assertAuthorizationLimitBlocked(
            decision.authorizationLimitRows.associateBy { it.authorizationLimit },
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.ProductionProviderSelectableTrue,
        )
        assertAuthorizationLimitBlocked(
            decision.authorizationLimitRows.associateBy { it.authorizationLimit },
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.ProductionSync,
        )
        assertAuthorizationLimitBlocked(
            decision.authorizationLimitRows.associateBy { it.authorizationLimit },
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.Signing,
        )
        assertAuthorizationLimitBlocked(
            decision.authorizationLimitRows.associateBy { it.authorizationLimit },
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.Broadcasting,
        )
        assertAuthorizationLimitBlocked(
            decision.authorizationLimitRows.associateBy { it.authorizationLimit },
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.Mainnet,
        )
        assertFalse(decision.disabledCapabilities.canSetProductionProviderSelectable)
        assertFalse(decision.disabledCapabilities.canAuthorizeProductionSync)
        assertFalse(decision.disabledCapabilities.canAuthorizeMainnet)
        assertTrue(decision.productionSyncBlocked)
        assertTrue(decision.mainnetBlocked)
    }

    @Test
    fun secureStorageMetadataAndForbiddenLinkagesRemainBlocked() {
        val linkageRows = currentIdentityDecision().forbiddenLinkageRows.associateBy { it.linkage }

        listOf(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.SecureStorageSuccess,
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.SecureMetadataSuccess,
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.ProductionRegistry,
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.ProviderFactoryLinkage,
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.ProviderDispatcherLinkage,
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.ProductionSync,
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.Mainnet,
        ).forEach { linkage ->
            val row = requireNotNull(linkageRows[linkage])
            assertTrue(row.forbidden)
            assertFalse(row.currentLinkPresent)
            assertFalse(row.canAuthorize)
        }
    }

    @Test
    fun userConsentWarningOnlyAndTestOnlyEvidenceCannotPromoteIdentity() {
        val decision = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.evaluateIdentityDecision(
            SkaldVaultV1TestOnlyProviderIdentityDecisionRequest(
                userConsentOverrideRequested = true,
                warningOnlyEvidenceClaimed = true,
                testOnlyEvidenceClaimedAsProductionPromotion = true,
                releaseEvidenceClaimed = true,
            ),
        )

        assertContains(decision.blockers, SkaldVaultV1TestOnlyProviderIdentityBlocker.UserConsentCannotOverride)
        assertContains(decision.blockers, SkaldVaultV1TestOnlyProviderIdentityBlocker.WarningOnlyEvidenceNonAuthorizing)
        assertContains(decision.blockers, SkaldVaultV1TestOnlyProviderIdentityBlocker.TestOnlyEvidenceNonAuthorizing)
        assertContains(
            decision.blockers,
            SkaldVaultV1TestOnlyProviderIdentityBlocker.ProductionProviderAcceptanceIncomplete,
        )
        decision.decisionClassRows.forEach { row ->
            assertFalse(row.authorizesIdentityPromotion)
            assertFalse(row.authorizesCurrentImplementation)
        }
        assertContains(
            decision.decisionClassRows.map { it.decisionClass },
            SkaldVaultV1TestOnlyProviderIdentityDecisionClass.UserConsentCannotPromoteIdentity,
        )
        assertContains(
            decision.decisionClassRows.map { it.decisionClass },
            SkaldVaultV1TestOnlyProviderIdentityDecisionClass.WarningOnlyEvidenceCannotPromoteIdentity,
        )
        assertContains(
            decision.decisionClassRows.map { it.decisionClass },
            SkaldVaultV1TestOnlyProviderIdentityDecisionClass.TestOnlyEvidenceCannotPromoteProductionIdentity,
        )
    }

    @Test
    fun rawMaterialProviderReferencesAndCryptoReferencesRemainUnavailable() {
        val capability = currentIdentityDecision().disabledCapabilities

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
    }

    @Test
    fun constraintsEvidenceSourcesAndFutureGuidanceAreExplicitButNonAuthorizing() {
        val decision = currentIdentityDecision()

        assertContains(
            decision.constraintRows.map { it.constraint },
            SkaldVaultV1TestOnlyProviderIdentityConstraint.AbsentFromProductionRegistry,
        )
        assertContains(
            decision.constraintRows.map { it.constraint },
            SkaldVaultV1TestOnlyProviderIdentityConstraint.AbsentFromProviderFactory,
        )
        assertContains(
            decision.constraintRows.map { it.constraint },
            SkaldVaultV1TestOnlyProviderIdentityConstraint.AbsentFromProviderDispatcher,
        )
        decision.constraintRows.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.satisfiedForModelOnlyBoundary)
            assertTrue(row.futureImplementationReviewRequired)
            assertFalse(row.authorizesCurrentIdentity)
        }

        assertContains(
            decision.evidenceSourceRows.map { it.evidenceSource },
            SkaldVaultV1TestOnlyProviderIdentityEvidenceSource.TestOnlyKatSourceSetConfinementEvidence,
        )
        decision.evidenceSourceRows.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.nonAuthorizing)
            assertFalse(row.authorizesImplementation)
            assertFalse(row.authorizesProduction)
        }

        assertContains(
            decision.futureBranchGuidanceRows.map { it.guidance },
            SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidance.TestOnlyIdentityImplementationDecision,
        )
        decision.futureBranchGuidanceRows.forEach { row ->
            assertTrue(row.futureRequired)
            assertFalse(row.authorizesCurrentImplementation)
        }
    }

    @Test
    fun redactedOutputContainsOnlySafeClassesAndLabels() {
        val decision = currentIdentityDecision()
        val requestText = SkaldVaultV1TestOnlyProviderIdentityDecisionRequest(
            userConsentOverrideRequested = true,
        ).toString()

        assertContains(decision.redactionClasses, SkaldVaultV1TestOnlyProviderIdentityRedactionClass.PolicyIdsOnly)
        assertContains(decision.redactionClasses, SkaldVaultV1TestOnlyProviderIdentityRedactionClass.IdentityIdsOnly)
        assertContains(decision.redactionClasses, SkaldVaultV1TestOnlyProviderIdentityRedactionClass.NoRawMaterial)
        assertContains(decision.redactionClasses, SkaldVaultV1TestOnlyProviderIdentityRedactionClass.NoProviderReferences)
        assertContains(decision.redactionClasses, SkaldVaultV1TestOnlyProviderIdentityRedactionClass.NoCryptoReferences)
        assertContains(decision.redactionClasses, SkaldVaultV1TestOnlyProviderIdentityRedactionClass.NoLocationReferences)
        assertContains(requestText, "decisionId=redacted")
        assertContains(requestText, "rawMaterial=redacted")
        assertContains(requestText, "providerReference=redacted")
        assertContains(requestText, "cryptoReference=redacted")
        assertContains(requestText, "locationReference=redacted")
    }

    @Test
    fun identityCategoryModelingIsDistinctFromIdentityImplementation() {
        val decision = currentIdentityDecision()
        val capability = SkaldVaultV1TestOnlyProviderIdentityCapability.Current

        assertTrue(decision.identityCategoryRows.all { it.modeled })
        assertTrue(
            decision.identityCategoryRows
                .filter { it.category != SkaldVaultV1TestOnlyProviderIdentityCategory.DisabledProviderIdentity }
                .none { it.implementedNow },
        )
        assertTrue(decision.futureTestOnlyIdentityAllowedInPrinciple)
        assertFalse(decision.testOnlyProviderIdentityImplemented)
        assertFalse(capability.testOnlyProviderIdentityImplemented)
        assertFalse(capability.productionProviderIdentityImplemented)
    }

    private fun currentIdentityDecision() =
        SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()

    private fun assertAuthorizationLimitBlocked(
        rowsByLimit: Map<
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit,
            com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimitRow,
            >,
        limit: SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit,
    ) {
        val row = requireNotNull(rowsByLimit[limit])
        assertTrue(row.blocked)
        assertFalse(row.canAuthorize)
    }
}
