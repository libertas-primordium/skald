package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityEscapeRisk
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimitRow
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationRule
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationSurface
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityIsolationGuardTest {
    @Test
    fun isolationGuardIsModeledButStillDisabled() {
        val evidence = currentIsolation()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-isolation-guard-v1",
            SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.POLICY_VERSION)
        assertTrue(evidence.modeledButStillDisabled)
        assertTrue(evidence.isolationGuardModeled)
        assertTrue(evidence.stillDisabled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.IsolationGuardModeled,
        )
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.StillDisabled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.IdentityCategoriesIsolated,
        )
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.MainnetBlocked)
    }

    @Test
    fun identityCategoriesAreLabelOnlyAndUnimplemented() {
        val evidence = currentIsolation()

        assertTrue(evidence.identityCategoryIsLabelOnly)
        evidence.identityCategoryRows.forEach { row ->
            assertTrue(row.labelOnly)
            assertTrue(row.isolated)
            assertFalse(row.implementedNow)
            assertFalse(row.instantiable)
            assertFalse(row.registrySelectable)
            assertFalse(row.factoryReachable)
            assertFalse(row.dispatcherReachable)
            assertFalse(row.executorTargetable)
            assertFalse(row.vaultLifecycleReachable)
            assertFalse(row.persistenceReachable)
            assertFalse(row.authorizesProduction)
            assertFalse(row.authorizesMainnet)
        }
        assertContains(
            evidence.identityCategoryRows.map { it.category },
            SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory
                .FutureTestOnlyDeterministicIdentityIsolated,
        )
        assertContains(
            evidence.identityCategoryRows.map { it.category },
            SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory
                .FutureTestOnlyRandomizedBehaviorIdentityIsolated,
        )
        assertContains(
            evidence.identityCategoryRows.map { it.category },
            SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory
                .FutureTestOnlyPlatformRuntimeIdentityIsolated,
        )
    }

    @Test
    fun noProviderIdentityIsAvailableThroughAnyImplementationSurface() {
        val capability = currentIsolation().disabledCapabilities

        assertTrue(capability.isolationGuardModeled)
        assertTrue(capability.identityCategoryIsLabelOnly)
        assertFalse(capability.testOnlyProviderIdentityImplemented)
        assertFalse(capability.productionProviderIdentityImplemented)
        assertFalse(capability.instantiableProviderIdentityAvailable)
        assertFalse(capability.registrySelectableIdentityAvailable)
        assertFalse(capability.factoryReachableIdentityAvailable)
        assertFalse(capability.dispatcherReachableIdentityAvailable)
        assertFalse(capability.executorTargetableIdentityAvailable)
        assertFalse(capability.vaultLifecycleReachableIdentityAvailable)
        assertFalse(capability.persistenceReachableIdentityAvailable)
        assertFalse(capability.canImplementProviderNow)
        assertFalse(capability.canInstantiateProviderNow)
        assertFalse(capability.canRegisterProviderNow)
        assertFalse(capability.canDispatchProviderNow)
        assertFalse(capability.canTargetProviderWithExecutorNow)
        assertFalse(capability.canUseProviderForKatNow)
    }

    @Test
    fun isolationSurfacesAreModeledAndUnreachable() {
        val evidence = currentIsolation()
        val surfaces = evidence.surfaceRows.associateBy { it.surface }

        listOf(
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ProviderSelectionRegistrySurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ProviderFactorySurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ProviderDispatcherSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ProviderOperationAuthorizationSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ProviderKatExecutorSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ExecutorTargetCatalogSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.VaultCreationAuthorizationSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.VaultUnlockAuthorizationSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.VaultPersistenceReadinessSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.SecureStorageSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.SecureMetadataStorageSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.ProductionSyncServiceSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.WalletDomainServicesSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.BdkAdapterPathsSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.SettingsCodecsSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.AppUiSurface,
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.MainnetPolicySurface,
        ).forEach { surface ->
            val row = requireNotNull(surfaces[surface])
            assertTrue(row.isolated)
            assertFalse(row.currentReachable)
            assertFalse(row.authorizesIdentityImplementation)
            assertFalse(row.authorizesProductionSelection)
        }

        assertFalse(evidence.secureStorageReachable)
        assertFalse(evidence.secureMetadataReachable)
        assertFalse(evidence.productionSyncReachable)
        assertFalse(evidence.walletServicesReachable)
        assertFalse(evidence.bdkAdapterReachable)
        assertFalse(evidence.settingsCodecsReachable)
        assertFalse(evidence.appUiReachable)
    }

    @Test
    fun escapeRisksAreModeledAndBlocked() {
        val rows = currentIsolation().escapeRiskRows.associateBy { it.risk }

        listOf(
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesFactoryProduct,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesDispatcherTarget,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesExecutorTargetRisk,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesProviderOperationTarget,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesVaultCreationDependency,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesUnlockDependency,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesPersistenceDependency,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesSecureStorageDependency,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesSecureMetadataDependency,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesProductionSyncDependency,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesSettingsState,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityBecomesUiState,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityReachesBdkAdapter,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityReachesMainnetPolicy,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityCarriesProviderReference,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityCarriesCryptoReference,
            SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.IdentityCarriesRawMaterial,
        ).forEach { risk ->
            val row = requireNotNull(rows[risk])
            assertTrue(row.modeled)
            assertFalse(row.currentRiskPresent)
            assertTrue(row.blocked)
            assertFalse(row.canAuthorize)
        }
    }

    @Test
    fun rawMaterialProviderReferencesAndCryptoReferencesRemainUnavailable() {
        val capability = currentIsolation().disabledCapabilities

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
    fun authorizationCapabilitiesRemainFalse() {
        val evidence = currentIsolation()
        val capability = evidence.disabledCapabilities

        assertFalse(capability.canAuthorizeProviderSelection)
        assertFalse(capability.canSetProductionProviderSelectable)
        assertFalse(capability.canAuthorizeVaultCreation)
        assertFalse(capability.canAuthorizeVaultUnlock)
        assertFalse(capability.canAuthorizeVaultPersistence)
        assertFalse(capability.canAuthorizeProductionSync)
        assertFalse(capability.canAuthorizeMainnet)
        assertFalse(evidence.productionProviderSelectable)
        assertTrue(evidence.vaultLifecycleBlocked)
        assertTrue(evidence.persistenceBlocked)
        assertTrue(evidence.productionSyncBlocked)
        assertTrue(evidence.mainnetBlocked)
    }

    @Test
    fun authorizationLimitsBlockEveryEscapePath() {
        val rows = currentIsolation().authorizationLimitRows.associateBy { it.limit }

        SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.entries.forEach { limit ->
            val row = requireNotNull(rows[limit])
            assertTrue(row.blocked)
            assertFalse(row.canAuthorize)
        }
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.ProviderImplementation,
        )
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.ProviderRegistryEntry,
        )
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.ExecutorTargetAuthorization,
        )
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.VaultCreation,
        )
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.VaultUnlock,
        )
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.VaultPersistence,
        )
        assertAuthorizationLimitBlocked(
            rows,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.Mainnet,
        )
    }

    @Test
    fun userConsentWarningOnlyAndTestOnlyEvidenceCannotPromoteIdentity() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.evaluateIsolation(
            SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest(
                userConsentOverrideRequested = true,
                warningOnlyEvidenceClaimed = true,
                testOnlyEvidenceClaimedAsProductionPromotion = true,
                releaseEvidenceClaimed = true,
            ),
        )

        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.UserConsentCannotOverride)
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.WarningOnlyEvidenceNonAuthorizing,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.TestOnlyEvidenceNonAuthorizing,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.ProductionProviderAcceptanceIncomplete,
        )
        assertFalse(evidence.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(evidence.disabledCapabilities.canSetProductionProviderSelectable)
    }

    @Test
    fun futureBranchGuidanceIsExplicitButNonAuthorizing() {
        val evidence = currentIsolation()

        assertContains(
            evidence.futureBranchGuidanceRows.map { it.guidance },
            SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance
                .ExplicitIdentityImplementationDecision,
        )
        assertContains(
            evidence.futureBranchGuidanceRows.map { it.guidance },
            SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance.RegistryExclusionProof,
        )
        assertContains(
            evidence.futureBranchGuidanceRows.map { it.guidance },
            SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance.MainnetNonAuthorizationReview,
        )
        evidence.futureBranchGuidanceRows.forEach { row ->
            assertTrue(row.futureRequired)
            assertFalse(row.authorizesCurrentImplementation)
        }
    }

    @Test
    fun isolationRulesAndEvidenceSourcesAreNonAuthorizing() {
        val evidence = currentIsolation()

        assertContains(
            evidence.ruleRows.map { it.rule },
            SkaldVaultV1TestOnlyProviderIdentityIsolationRule.IdentityCategoryIsLabelOnly,
        )
        assertContains(
            evidence.ruleRows.map { it.rule },
            SkaldVaultV1TestOnlyProviderIdentityIsolationRule.IdentityCategoryIsNotRegistrySelectable,
        )
        assertContains(
            evidence.ruleRows.map { it.rule },
            SkaldVaultV1TestOnlyProviderIdentityIsolationRule.IdentityCategoryCannotAuthorizeProduction,
        )
        evidence.ruleRows.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.satisfiedForModelOnlyBoundary)
            assertFalse(row.authorizesImplementation)
        }

        evidence.evidenceSourceRows.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.nonAuthorizing)
            assertFalse(row.authorizesImplementation)
            assertFalse(row.authorizesProduction)
        }
    }

    @Test
    fun redactedOutputContainsOnlySafeClassesAndLabels() {
        val evidence = currentIsolation()
        val requestText = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest(
            userConsentOverrideRequested = true,
        ).toString()

        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass.PolicyIdsOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass.IdentityCategoryLabelsOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass.NoRawMaterial,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass.NoProviderReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass.NoCryptoReferences,
        )
        assertContains(requestText, "guardId=redacted")
        assertContains(requestText, "rawMaterial=redacted")
        assertContains(requestText, "providerReference=redacted")
        assertContains(requestText, "cryptoReference=redacted")
        assertContains(requestText, "storageReference=redacted")
        assertContains(requestText, "backendReference=redacted")
    }

    @Test
    fun providerSelectionStillSelectsOnlyDisabledProvider() {
        val selection = VaultCryptoProviderSelectionRegistry.select()
        val evidence = currentIsolation()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProviderSelection)
    }

    @Test
    fun isolationEvidenceIsDistinctFromIdentityImplementation() {
        val evidence = currentIsolation()

        assertTrue(evidence.identityCategoryRows.all { it.labelOnly && it.isolated })
        assertFalse(evidence.testOnlyProviderIdentityImplemented)
        assertFalse(evidence.productionProviderIdentityImplemented)
        assertFalse(evidence.instantiableProviderIdentityAvailable)
        assertFalse(evidence.registrySelectableIdentityAvailable)
        assertFalse(evidence.factoryReachableIdentityAvailable)
        assertFalse(evidence.dispatcherReachableIdentityAvailable)
        assertFalse(evidence.executorTargetableIdentityAvailable)
        assertFalse(evidence.vaultLifecycleReachableIdentityAvailable)
        assertFalse(evidence.persistenceReachableIdentityAvailable)
        assertTrue(evidence.evidenceSourceRows.all { it.nonAuthorizing })
        assertTrue(evidence.authorizationLimitRows.all { it.blocked && !it.canAuthorize })
    }

    private fun currentIsolation() =
        SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()

    private fun assertAuthorizationLimitBlocked(
        rowsByLimit: Map<
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit,
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimitRow,
            >,
        limit: SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit,
    ) {
        val row = requireNotNull(rowsByLimit[limit])
        assertTrue(row.blocked)
        assertFalse(row.canAuthorize)
    }
}
