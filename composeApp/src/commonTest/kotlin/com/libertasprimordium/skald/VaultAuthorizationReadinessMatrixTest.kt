package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderAuthorizationReadinessMatrixRule
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessBlockerCategory
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessBoundaryId
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessCapabilityId
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixCapability
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixRequest
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixResult
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixStatus
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultAuthorizationReadinessMatrixTest {
    @Test
    fun defaultMatrixIsStillDisabledEvidenceOnlyAndAllCapabilitiesRemainBlocked() {
        val evidence = blocked(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-authorization-readiness-matrix-v1",
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1AuthorizationReadinessMatrixPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1AuthorizationReadinessMatrixStatus.BlockedFailClosed, evidence.status)
        assertTrue(evidence.authorizationReadinessMatrixModeled)
        assertTrue(evidence.authorizationReadinessMatrixStillDisabled)
        assertTrue(evidence.authorizationReadinessMatrixBlocksAllRuntimeCapabilities)
        assertTrue(evidence.authorizationReadinessMatrixDoesNotEnableCreation)
        assertTrue(evidence.authorizationReadinessMatrixDoesNotEnableUnlock)
        assertTrue(evidence.authorizationReadinessMatrixDoesNotEnablePersistence)
        assertTrue(evidence.authorizationReadinessMatrixDoesNotEnableProviderSelection)
        assertTrue(evidence.authorizationReadinessMatrixFailureVocabularyModeled)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.runtimeRandomnessReady)
        assertFalse(evidence.kdfReady)
        assertFalse(evidence.secureStorageReady)
        assertFalse(evidence.creationReady)
        assertFalse(evidence.unlockReady)
        assertFalse(evidence.activeSessionReady)
        assertFalse(evidence.persistenceReady)
        assertFalse(evidence.vaultStorageAvailable)
        assertFalse(evidence.mainnetReady)
        assertDisabled(evidence.capability)

        evidence.capabilityRows.forEach { row ->
            assertFalse(row.currentlyReady)
            assertFalse(row.runtimeAvailable)
            assertFalse(row.userConsentCanOverride)
            assertFalse(row.warningOnlyEvidenceCanAuthorize)
            assertFalse(row.testOnlyEvidenceCanAuthorizeProduction)
            assertFalse(row.mainnetAllowed)
            assertContains(row.currentStatuses, SkaldVaultV1AuthorizationReadinessMatrixStatus.BlockedFailClosed)
        }
    }

    @Test
    fun capabilityVocabularyIsCompleteAndEachCapabilityHasBlockingEvidence() {
        val summary = SkaldVaultV1AuthorizationReadinessMatrixPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        )

        assertEquals(SkaldVaultV1AuthorizationReadinessCapabilityId.entries.toSet(), summary.capabilityIds)
        assertEquals(SkaldVaultV1AuthorizationReadinessMatrixStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1AuthorizationReadinessBoundaryId.entries.toSet(), summary.boundaryIds)
        assertEquals(SkaldVaultV1AuthorizationReadinessBlockerCategory.entries.toSet(), summary.blockerCategories)
        assertEquals(
            SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.entries.toSet(),
            summary.requiredFutureEvidence,
        )
        assertEquals(SkaldVaultV1AuthorizationReadinessRedactionClass.entries.toSet(), summary.redactionClasses)
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.allProductionRuntimeCapabilitiesBlocked)

        val represented = evidence.capabilityRows.map { it.capabilityId }.toSet()
        assertEquals(SkaldVaultV1AuthorizationReadinessCapabilityId.entries.toSet(), represented)

        evidence.capabilityRows.forEach { row ->
            assertTrue(row.blockingBoundaries.isNotEmpty())
            assertTrue(row.blockerCategories.isNotEmpty())
            assertTrue(row.requiredFutureEvidence.isNotEmpty())
            assertTrue(row.prohibitedAccidentalReadinessFlags.isNotEmpty())
            assertContains(
                row.blockerCategories,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.WarningOnlyEvidenceCannotAuthorize,
            )
            assertContains(
                row.blockerCategories,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.UserConsentCannotOverride,
            )
            assertContains(
                row.blockerCategories,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.TestOnlyEvidenceRejectedForProduction,
            )
            assertContains(row.blockerCategories, SkaldVaultV1AuthorizationReadinessBlockerCategory.MainnetDisabled)
        }
    }

    @Test
    fun boundaryTraceabilityPointsCapabilitiesAtOwningDisabledBoundaries() {
        val rows = blocked(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        ).capabilityRows.associateBy { it.capabilityId }

        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.ProductionProviderSelectability,
            SkaldVaultV1AuthorizationReadinessBoundaryId.ProviderSelectionBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.DisabledProviderSelection,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.ProviderOperationExecution,
            SkaldVaultV1AuthorizationReadinessBoundaryId.ProviderOperationAuthorizationBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderOperationAuthorizationBlocked,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.RuntimeRandomnessAuthorization,
            SkaldVaultV1AuthorizationReadinessBoundaryId.RuntimeRandomnessAuthorizationBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.RuntimeRandomnessAuthorizationBlocked,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.KdfCalibrationFinalParameterApproval,
            SkaldVaultV1AuthorizationReadinessBoundaryId.KdfCalibrationAuthorizationBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.KdfCalibrationAuthorizationBlocked,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.SecureSecretStorage,
            SkaldVaultV1AuthorizationReadinessBoundaryId.SecureStorageAuthorizationBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureSecretStorageUnavailable,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.SecureMetadataStorage,
            SkaldVaultV1AuthorizationReadinessBoundaryId.SecureMetadataBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureMetadataStorageUnavailable,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.VaultCreation,
            SkaldVaultV1AuthorizationReadinessBoundaryId.CreationAuthorizationBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderOperationAuthorizationBlocked,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.VaultUnlock,
            SkaldVaultV1AuthorizationReadinessBoundaryId.UnlockAuthorizationBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.LockSessionLifecycleUnavailable,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.ActiveSession,
            SkaldVaultV1AuthorizationReadinessBoundaryId.LockSessionLifecycleBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.LockSessionLifecycleUnavailable,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.VaultPersistence,
            SkaldVaultV1AuthorizationReadinessBoundaryId.PersistenceReadinessGate,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.Migration,
            SkaldVaultV1AuthorizationReadinessBoundaryId.MigrationCorruptionBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.MigrationCorruptionModelOnly,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.ClearWipeImplementation,
            SkaldVaultV1AuthorizationReadinessBoundaryId.ClearWipeStrategyBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.ClearWipeStrategyModelOnly,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.RedactionSafeDiagnostics,
            SkaldVaultV1AuthorizationReadinessBoundaryId.RedactionLeakageBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.RedactionLeakageModelOnly,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.AndroidLifecycleReadiness,
            SkaldVaultV1AuthorizationReadinessBoundaryId.AndroidPolicy,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.AndroidLifecycleReviewMissing,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.LinuxRootReadiness,
            SkaldVaultV1AuthorizationReadinessBoundaryId.LinuxRootResolutionPolicy,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.RootPathEvidenceModelOnly,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.WalletSync,
            SkaldVaultV1AuthorizationReadinessBoundaryId.WalletOutOfScopeBoundary,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.WalletSyncSigningOutOfScope,
        )
        assertTrace(
            rows,
            SkaldVaultV1AuthorizationReadinessCapabilityId.Mainnet,
            SkaldVaultV1AuthorizationReadinessBoundaryId.MainnetPolicy,
            SkaldVaultV1AuthorizationReadinessBlockerCategory.MainnetDisabled,
        )
    }

    @Test
    fun warningUserConsentAndTestOnlyEvidenceCannotAuthorizeProductionRuntime() {
        val evidence = blocked(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        )

        evidence.capabilityRows.forEach { row ->
            assertFalse(row.warningOnlyEvidenceCanAuthorize)
            assertFalse(row.userConsentCanOverride)
            assertFalse(row.testOnlyEvidenceCanAuthorizeProduction)
            assertContains(
                row.currentStatuses,
                SkaldVaultV1AuthorizationReadinessMatrixStatus.WarningOnlyCannotAuthorize,
            )
            assertContains(
                row.currentStatuses,
                SkaldVaultV1AuthorizationReadinessMatrixStatus.UserConsentCannotOverride,
            )
            assertContains(
                row.currentStatuses,
                SkaldVaultV1AuthorizationReadinessMatrixStatus.TestOnlyRejectedForProduction,
            )
        }
    }

    @Test
    fun mainnetProviderSelectionAndUnderlyingReadinessRemainBlocked() {
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val contract = commonProductionProviderAcceptanceContract()
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val evidence = blocked(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.currentEvidence(
                    providerSelectionResult = providerSelection,
                    providerAcceptanceAssessment = contract.assess(),
                    dependencyProbeResult = dependency,
                    encryptedVaultReadiness = readiness,
                ),
            ),
        )

        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerAcceptanceEvidenceConsumed)
        assertTrue(evidence.dependencyProbeEvidenceConsumed)
        assertTrue(evidence.encryptedVaultReadinessEvidenceConsumed)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.AuthorizationReadinessMatrixStillDisabled)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AuthorizationReadinessMatrixBuildingBlock)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.AuthorizationReadinessMatrixImplementedAndTested
            ],
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.AuthorizationReadinessMatrixImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.AuthorizationReadinessMatrixFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.AuthorizationReadinessMatrixStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.AuthorizationReadinessMatrixRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.AuthorizationReadinessMatrixTestsMissing,
        )
        assertFalse(evidence.capability.mainnetAvailable)
        assertFalse(evidence.capability.providerSelectable)
        assertFalse(evidence.capability.productionProviderSelectable)
    }

    @Test
    fun providerAcceptanceContractRecordsMatrixWithoutProviderSelectability() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.AuthorizationReadinessMatrixImplementedAndTested),
        )
        assertEquals(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.POLICY_ID,
            storageContract.authorizationReadinessMatrixPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.authorizationReadinessMatrixStatus,
        )
        assertEquals(
            ProductionProviderAuthorizationReadinessMatrixRule.entries.toSet(),
            storageContract.authorizationReadinessMatrixRules,
        )
        assertTrue(storageContract.authorizationReadinessMatrixModeled)
        assertTrue(storageContract.authorizationReadinessMatrixStillDisabled)
        assertTrue(storageContract.authorizationReadinessMatrixBlocksAllRuntimeCapabilities)
        assertTrue(storageContract.authorizationReadinessMatrixDoesNotEnableCreation)
        assertTrue(storageContract.authorizationReadinessMatrixDoesNotEnableUnlock)
        assertTrue(storageContract.authorizationReadinessMatrixDoesNotEnablePersistence)
        assertTrue(storageContract.authorizationReadinessMatrixDoesNotEnableProviderSelection)
        assertTrue(storageContract.authorizationReadinessMatrixFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertFalse(contract.assess().productionProviderSelectable)
        assertFalse(contract.assess().productionPersistenceAllowed)
    }

    @Test
    fun renderingAndPolicyTokenRemainRedactedAndDoNotExposeMaterial() {
        val request = SkaldVaultV1AuthorizationReadinessMatrixRequest.forCapability(
            SkaldVaultV1AuthorizationReadinessCapabilityId.VaultUnlock,
        )
        val result = SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "hunter2",
            "pin-1234",
            "biometric-result",
            "key-bytes",
            "wrapped-key-bytes",
            "secure-storage-handle",
            "os-keyring-handle",
            "password-manager-entry",
            "kdf-input",
            "salt-bytes",
            "nonce-bytes",
            "random-bytes",
            "record-id-123",
            "provider-handle",
            "/private/root",
        )

        forbiddenValues.forEach { raw ->
            assertFalse(request.toString().contains(raw))
            assertFalse(result.toString().contains(raw))
            assertFalse(evidence.toString().contains(raw))
            assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        }
        assertFalse(evidence.policyTokenEvidence.containsSecretMaterial)
        assertFalse(evidence.policyTokenEvidence.containsPathOrRootText)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsByteMaterial)
    }

    @Test
    fun individualCapabilityAuditStillReturnsBlockedRowsOnly() {
        SkaldVaultV1AuthorizationReadinessCapabilityId.entries.forEach { capabilityId ->
            val evidence = blocked(
                SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                    SkaldVaultV1AuthorizationReadinessMatrixRequest.forCapability(capabilityId),
                ),
            )

            assertEquals(listOf(capabilityId), evidence.capabilityRows.map { it.capabilityId })
            assertFalse(evidence.capabilityRows.single().currentlyReady)
            assertFalse(evidence.capabilityRows.single().runtimeAvailable)
            assertFalse(evidence.capabilityRows.single().mainnetAllowed)
            assertDisabled(evidence.capability)
        }
    }

    private fun blocked(
        result: SkaldVaultV1AuthorizationReadinessMatrixResult<
            com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence,
        >,
    ): com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence =
        assertIs<
            SkaldVaultV1AuthorizationReadinessMatrixResult.Blocked<
                com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence,
            >,
        >(result).value

    private fun assertTrace(
        rows: Map<SkaldVaultV1AuthorizationReadinessCapabilityId, com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessCapabilityRow>,
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        boundaryId: SkaldVaultV1AuthorizationReadinessBoundaryId,
        blocker: SkaldVaultV1AuthorizationReadinessBlockerCategory,
    ) {
        val row = requireNotNull(rows[capabilityId])
        assertContains(row.blockingBoundaries, boundaryId)
        assertContains(row.blockerCategories, blocker)
        assertFalse(row.currentlyReady)
        assertFalse(row.runtimeAvailable)
    }

    private fun assertDisabled(capability: SkaldVaultV1AuthorizationReadinessMatrixCapability) {
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelectable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.runtimeRandomnessReady)
        assertFalse(capability.kdfReady)
        assertFalse(capability.secureStorageReady)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.creationReady)
        assertFalse(capability.unlockReady)
        assertFalse(capability.activeSessionReady)
        assertFalse(capability.persistenceReady)
        assertFalse(capability.storageServiceAvailable)
        assertFalse(capability.vaultStorageAvailable)
        assertFalse(capability.manifestReadWriteAvailable)
        assertFalse(capability.storageIndexReadWriteAvailable)
        assertFalse(capability.recordReadWriteAvailable)
        assertFalse(capability.atomicWriteAvailable)
        assertFalse(capability.crashRecoveryAvailable)
        assertFalse(capability.migrationAvailable)
        assertFalse(capability.corruptionRecoveryAvailable)
        assertFalse(capability.rollbackProtectionAvailable)
        assertFalse(capability.clearWipeAvailable)
        assertFalse(capability.redactionSafeDiagnosticsAvailable)
        assertFalse(capability.passphraseInputAccepted)
        assertFalse(capability.passphraseRetryThrottleAvailable)
        assertFalse(capability.androidAppPrivateStorageReady)
        assertFalse(capability.androidLifecycleReady)
        assertFalse(capability.androidKeystoreWrappingAvailable)
        assertFalse(capability.linuxRootReady)
        assertFalse(capability.linuxOptionalWrappingAvailable)
        assertFalse(capability.bdkPersistenceAvailable)
        assertFalse(capability.walletSyncAvailable)
        assertFalse(capability.signingAvailable)
        assertFalse(capability.broadcastingAvailable)
        assertFalse(capability.torTransportAvailable)
        assertFalse(capability.nostrParsingAvailable)
        assertFalse(capability.mainnetAvailable)
    }
}
