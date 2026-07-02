package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorContractCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorContractRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorContractResult
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorInputClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorOperationClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorRole
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderKatExecutorContractTest {
    @Test
    fun contractIsModeledButExecutorIsNotImplementedOrCallable() {
        val evidence = currentContract()

        assertEquals(
            "skald-vault-v1-test-only-provider-kat-executor-contract-v1",
            SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.StillDisabled, evidence.status)
        assertTrue(evidence.executorContractModeled)
        assertFalse(evidence.executorImplemented)
        assertFalse(evidence.executorCallable)
        assertFalse(evidence.currentExecutionAuthorized)
        assertTrue(evidence.productionExecutorForbidden)
        assertContains(evidence.currentRoles, SkaldVaultV1TestOnlyProviderKatExecutorRole.ContractOnlyModel)
        assertFalse(evidence.currentRoles.contains(SkaldVaultV1TestOnlyProviderKatExecutorRole.FutureDesktopTestExecutor))
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ContractModeled)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.StillDisabled)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ExecutorNotImplemented)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ExecutorSurfaceUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface)
        assertDisabled(evidence.disabledCapabilities)
    }

    @Test
    fun allCurrentExecutionAndAuthorizationCapabilitiesRemainFalse() {
        val capability = currentContract().disabledCapabilities

        assertTrue(capability.executorContractModeled)
        assertFalse(capability.executorImplemented)
        assertFalse(capability.executorCallable)
        assertFalse(capability.canImplementExecutorNow)
        assertFalse(capability.canRunExecutorNow)
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

    @Test
    fun sourceSetContractForbidsProductionAndDoesNotAuthorizeTestExecutionNow() {
        val rows = currentContract().sourceSetRows.associateBy { it.sourceSetCategory }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory.DesktopTestOnlyFutureReviewRequired,
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory
                .AndroidInstrumentedTestOnlyFutureReviewRequired,
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory.CommonTestModelAssertionsOnly,
        ).forEach { category ->
            val row = rows.getValue(category)
            assertTrue(row.futureReviewRequired)
            assertFalse(row.currentImplementationAuthorized)
            assertFalse(row.executorImplementationForbidden)
            assertContains(row.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface)
        }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory
                .CommonMainForbiddenForExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory
                .AndroidMainForbiddenForExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory
                .DesktopMainForbiddenForExecutorImplementation,
        ).forEach { category ->
            val row = rows.getValue(category)
            assertFalse(row.currentImplementationAuthorized)
            assertTrue(row.executorImplementationForbidden)
            assertContains(row.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderSelectionDisabledProviderOnly)
        }
    }

    @Test
    fun inputClassesAllowOnlyFutureLabelsAndReferencesAndRejectMaterialOrHandles() {
        val rows = currentContract().inputClassRows.associateBy { it.inputClass }
        val allowed = setOf(
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.PublicNonWalletKatVectorReference,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.CanonicalSkaldNonWalletVectorReference,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.SyntheticTestOnlyMaterialReference,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.DeterministicTestOnlyProviderIdentity,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.TestOnlyOperationLabel,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RedactedExpectedOutcomeLabel,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.PlatformRuntimeEvidenceLabel,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.SourceSetEvidenceLabel,
        )

        allowed.forEach { inputClass ->
            val row = rows.getValue(inputClass)
            assertTrue(row.futureAllowedAsReferenceOnly)
            assertFalse(row.currentAccepted)
            assertFalse(row.forbidden)
        }

        SkaldVaultV1TestOnlyProviderKatExecutorInputClass.entries
            .filter { it !in allowed }
            .forEach { inputClass ->
                val row = rows.getValue(inputClass)
                assertFalse(row.futureAllowedAsReferenceOnly)
                assertFalse(row.currentAccepted)
                assertTrue(row.forbidden)
            }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawCredentialPhrase,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawPin,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawSeed,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawMnemonic,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawDescriptor,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.XprvTprvWif,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.NostrNsec,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawKey,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawRandomnessValue,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawDerivationInput,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawClearPayload,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.RawEncryptedPayload,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.ProviderReference,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.CryptoReference,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.FileLocation,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.StorageReference,
            SkaldVaultV1TestOnlyProviderKatExecutorInputClass.BackendReference,
        ).forEach { inputClass ->
            assertTrue(rows.getValue(inputClass).forbidden)
        }
    }

    @Test
    fun futureOperationClassesAreTestOnlyAndProductionOperationsRemainForbidden() {
        val rows = currentContract().operationClassRows.associateBy { it.operationClass }
        val futureTestOnly = setOf(
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.PositivePublicNonWalletKat,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.NegativePublicNonWalletKat,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.DeterministicSkaldCanonicalVectorKat,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.TestOnlyRandomizedAeadBehaviorCheck,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.TestOnlyProviderSelfTestRoutingCheck,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.TestOnlyRedactionBehaviorCheck,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.TestOnlyStorageSeparationAssertion,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.TestOnlyPlatformRuntimeCheck,
        )

        futureTestOnly.forEach { operationClass ->
            val row = rows.getValue(operationClass)
            assertTrue(row.futureTestOnlyAllowedAsCategory)
            assertFalse(row.currentAuthorized)
            assertFalse(row.productionForbidden)
            assertContains(row.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing)
        }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionProviderOperation,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionKdf,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionAead,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionHkdf,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionHmac,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionKeyGeneration,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionKeyWrapping,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.ProductionKeysetStorage,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.VaultCreation,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.VaultUnlock,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.VaultPersistence,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.SecureStorageWrite,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.SecureMetadataWrite,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.WalletSync,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.Signing,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.Broadcasting,
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.MainnetValidation,
        ).forEach { operationClass ->
            val row = rows.getValue(operationClass)
            assertFalse(row.futureTestOnlyAllowedAsCategory)
            assertFalse(row.currentAuthorized)
            assertTrue(row.productionForbidden)
            assertContains(row.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderOperationAuthorizationBlocked)
        }
    }

    @Test
    fun resultPolicyIsRedactedAndCannotAuthorizeProduction() {
        val evidence = currentContract()
        val rules = evidence.resultPolicyRows.associateBy { it.rule }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MayExposePassFailStatus,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MayExposeVectorId,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MayExposeOperationClass,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MayExposePlatformClass,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MayExposeRedactedDiagnosticCode,
        ).forEach { rule ->
            val row = rules.getValue(rule)
            assertTrue(row.futureResultMayExpose)
            assertFalse(row.currentResultSurfaceAvailable)
            assertFalse(row.canAuthorizeProduction)
        }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeRawInput,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeRawOutput,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeDerivedKeyMaterial,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeProviderReferences,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeCryptoReferences,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeFilesystemLocations,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeWalletMetadata,
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.MustNotExposeBackendMetadata,
        ).forEach { rule ->
            val row = rules.getValue(rule)
            assertFalse(row.futureResultMayExpose)
            assertTrue(row.futureResultMustNotExpose)
            assertFalse(row.currentResultSurfaceAvailable)
            assertFalse(row.canAuthorizeProduction)
        }

        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoRawMaterial)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoProviderReferences)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoCryptoReferences)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoFilesystemLocations)
    }

    @Test
    fun authorizationLimitsBlockSelectionVaultLifecycleSyncAndMainnet() {
        val evidence = currentContract()
        val rows = evidence.authorizationLimitRows.associateBy { it.limit }

        SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.entries.forEach { limit ->
            val row = rows.getValue(limit)
            assertFalse(row.futureExecutorResultCanAuthorize)
            assertFalse(row.currentExecutorResultCanAuthorize)
            assertContains(row.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing)
        }

        listOf(
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.ProviderSelection,
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.ProductionProviderSelectableTrue,
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.VaultCreation,
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.VaultUnlock,
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.VaultPersistence,
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.ProductionSync,
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.Mainnet,
        ).forEach { limit ->
            assertFalse(rows.getValue(limit).futureExecutorResultCanAuthorize)
        }

        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertTrue(evidence.vaultLifecycleBlocked)
        assertTrue(evidence.persistenceBlocked)
        assertTrue(evidence.productionSyncBlocked)
        assertTrue(evidence.mainnetBlocked)
    }

    @Test
    fun warningOnlyEvidenceAndUserConsentCannotOverrideHardGates() {
        val request = SkaldVaultV1TestOnlyProviderKatExecutorContractRequest(
            includeFutureCategories = true,
            warningOnlyEvidenceClaimed = true,
            userConsentOverrideRequested = true,
            releaseEvidenceClaimed = true,
            safeContractId = "raw-path-provider-handle-crypto-object-marker",
        )
        val evidence = blocked(SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy.evaluateContract(request))

        assertFalse(evidence.executorCallable)
        assertFalse(evidence.currentExecutionAuthorized)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(evidence.disabledCapabilities.canSetProductionProviderSelectable)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultCreation)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultUnlock)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultPersistence)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProductionSync)
        assertFalse(evidence.disabledCapabilities.canAuthorizeMainnet)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.WarningOnlyEvidenceNonAuthorizing)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatExecutorBlocker.MainnetDisabled)
    }

    @Test
    fun providerSelectionStillReturnsOnlyDisabledProvider() {
        val evidence = currentContract()
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
    fun redactedOutputContainsNoRawPayloadPathProviderHandleOrCryptoObject() {
        val request = SkaldVaultV1TestOnlyProviderKatExecutorContractRequest.currentContract(
            safeContractId = "payload-raw-path-provider-handle-crypto-object-marker",
        )
        val evidence = blocked(SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy.evaluateContract(request))
        val rendered = request.toString() + evidence.redactionClasses.joinToString { it.name }

        assertFalse(rendered.contains("payload-raw-path-provider-handle-crypto-object-marker"))
        assertFalse(rendered.contains("/tmp/"))
        assertFalse(rendered.contains("provider handle"))
        assertFalse(rendered.contains("crypto object"))
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.SafeIdsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoRawMaterial)
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoProviderReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoCryptoReferences,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.NoFilesystemLocations,
        )
    }

    @Test
    fun contractDoesNotExposeRunnableExecutorSemantics() {
        val summary = SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy.currentPolicySummary()
        val evidence = currentContract()

        assertContains(summary.roles, SkaldVaultV1TestOnlyProviderKatExecutorRole.ContractOnlyModel)
        assertFalse(evidence.executorImplemented)
        assertFalse(evidence.executorCallable)
        assertFalse(evidence.disabledCapabilities.canRunExecutorNow)
        assertFalse(evidence.roleRows.any { it.role == SkaldVaultV1TestOnlyProviderKatExecutorRole.FutureDesktopTestExecutor && it.currentImplementationAuthorized })
        assertFalse(evidence.roleRows.any { it.role == SkaldVaultV1TestOnlyProviderKatExecutorRole.FutureAndroidInstrumentedTestExecutor && it.currentImplementationAuthorized })
    }

    private fun currentContract(): SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence =
        blocked(
            SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy.evaluateContract(
                SkaldVaultV1TestOnlyProviderKatExecutorContractRequest.currentContract(),
            ),
        )

    private fun blocked(
        result: SkaldVaultV1TestOnlyProviderKatExecutorContractResult<
            SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence,
        >,
    ): SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence =
        when (result) {
            is SkaldVaultV1TestOnlyProviderKatExecutorContractResult.Blocked -> result.value
        }

    private fun assertDisabled(capability: SkaldVaultV1TestOnlyProviderKatExecutorContractCapability) {
        assertTrue(capability.executorContractModeled)
        assertFalse(capability.executorImplemented)
        assertFalse(capability.executorCallable)
        assertFalse(capability.canImplementExecutorNow)
        assertFalse(capability.canRunExecutorNow)
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
