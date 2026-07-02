package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionGateEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionGateRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionGateResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionGateSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatDecisionGateStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatExternalEvidenceKind
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatFutureStage
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderExecutableKatRequiredEvidence
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultProviderExecutableKatDecisionGateTest {
    @Test
    fun defaultDecisionIsModeledButBlocked() {
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(
                SkaldVaultV1ProviderExecutableKatDecisionGateRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-executable-kat-decision-gate-v1",
            SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderExecutableKatDecisionGateSource.CurrentTypedEvidence, evidence.source)
        assertEquals(SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate, evidence.currentStage)
        assertTrue(evidence.decisionGateModeled)
        assertTrue(evidence.futureEvidenceListDefined)
        assertContains(evidence.statuses, SkaldVaultV1ProviderExecutableKatDecisionGateStatus.DecisionGateModeled)
        assertContains(evidence.statuses, SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ExecutableKatNotAuthorized,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderExecutableKatDecisionBlocker.ModelEvidenceOnly)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor)
        assertDisabled(evidence.capability)
        assertBlockedEvidence(evidence)
    }

    @Test
    fun allProviderKatExecutionCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(
                SkaldVaultV1ProviderExecutableKatDecisionGateRequest.currentEvidence(),
            ),
        )

        assertFalse(evidence.capability.canIntroduceExecutableKatNow)
        assertFalse(evidence.capability.canIntroduceTestOnlyExecutableKatNow)
        assertFalse(evidence.capability.canIntroduceProductionExecutableKatNow)
        assertFalse(evidence.capability.canRunProviderOperations)
        assertFalse(evidence.capability.canRunRandomness)
        assertFalse(evidence.capability.canRunKdf)
        assertFalse(evidence.capability.canRunAead)
        assertFalse(evidence.capability.canRunHkdf)
        assertFalse(evidence.capability.canRunHmac)
        assertFalse(evidence.capability.canGenerateKeys)
        assertFalse(evidence.capability.canStoreKeysets)
        assertFalse(evidence.capability.canAuthorizeProviderSelection)
        assertFalse(evidence.capability.canSetProductionProviderSelectable)
        assertFalse(evidence.capability.canAuthorizeVaultCreation)
        assertFalse(evidence.capability.canAuthorizeVaultUnlock)
        assertFalse(evidence.capability.canAuthorizeVaultPersistence)
        assertFalse(evidence.capability.canAuthorizeMainnet)

        assertFalse(evidence.executableKatAllowedNow)
        assertFalse(evidence.testOnlyExecutableKatAllowedNow)
        assertFalse(evidence.productionExecutableKatAllowedNow)
        assertFalse(evidence.katResultCanAuthorizeProviderSelection)
        assertFalse(evidence.katResultCanSetProductionProviderSelectable)
        assertFalse(evidence.katResultCanAuthorizeVaultCreation)
        assertFalse(evidence.katResultCanAuthorizeVaultUnlock)
        assertFalse(evidence.katResultCanAuthorizeVaultPersistence)
        assertFalse(evidence.katResultCanAuthorizeMainnet)
        assertFalse(evidence.providerOperationsExecute)
        assertFalse(evidence.randomnessExecutes)
        assertFalse(evidence.kdfExecutes)
        assertFalse(evidence.aeadExecutes)
        assertFalse(evidence.hkdfExecutes)
        assertFalse(evidence.hmacExecutes)
        assertFalse(evidence.keyGenerationExecutes)
        assertFalse(evidence.keysetStorageExecutes)
    }

    @Test
    fun evidenceClassesCannotAuthorizeExecutableOrProductionProviderKats() {
        val expectations = mapOf(
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.DependencyLevelDesktopAndroidKatEvidence to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.DependencyLevelKatsCannotAuthorizeProviderKats,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.PublicVectorDocumentation to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.PublicVectorDocumentationCannotAuthorizeExecution,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.TestOnlyProviderKatHarnessEvidence to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.TestOnlyHarnessCannotAuthorizeProduction,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.WarningOnlyEvidence to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.WarningOnlyEvidenceCannotAuthorize,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.UserConsentOverride to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.UserConsentCannotOverrideMissingHardGates,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ReleaseOrMainnetEvidence to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.MainnetDisabled,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderImplementationEvidence to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderKatExecutorEvidence to
                SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor,
        )

        expectations.forEach { (kind, blocker) ->
            val evidence = blocked(
                SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(
                    SkaldVaultV1ProviderExecutableKatDecisionGateRequest.forExternalEvidence(
                        kind = kind,
                        externalEvidence = listOf(
                            SkaldVaultV1ProviderExecutableKatDecisionGateRequest.externalEvidence(
                                kind = kind,
                                safeEvidenceId = "redacted-evidence-$kind",
                                evidenceOnly = true,
                                productionAuthorizingClaimed = true,
                                userConsentOverrideRequested =
                                    kind == SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.UserConsentOverride,
                            ),
                        ),
                    ),
                ),
            )
            val row = evidence.externalEvidenceRows.single()

            assertEquals(kind, row.kind)
            assertTrue(row.evidenceOnly)
            assertTrue(row.rejectedForExecutableKatAuthorization)
            assertTrue(row.rejectedForProductionAuthorization)
            assertFalse(row.canAuthorizeProviderSelection)
            assertFalse(row.canSetProductionProviderSelectable)
            assertFalse(row.canAuthorizeVaultCreationUnlockPersistence)
            assertFalse(row.canAuthorizeMainnet)
            assertContains(row.blockers, blocker)
            assertBlockedEvidence(evidence)
        }
    }

    @Test
    fun releaseAndMainnetEvidenceCannotBypassDisabledProviderSelection() {
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(
                SkaldVaultV1ProviderExecutableKatDecisionGateRequest.forExternalEvidence(
                    SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ReleaseOrMainnetEvidence,
                ),
            ),
        )
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertContains(evidence.blockers, SkaldVaultV1ProviderExecutableKatDecisionBlocker.MainnetDisabled)
        assertContains(evidence.blockers, SkaldVaultV1ProviderExecutableKatDecisionBlocker.ReleaseReviewMissing)
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
    fun modelEnumeratesFutureRequiredEvidenceWithoutSatisfyingOrEnablingIt() {
        val summary = SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(
                SkaldVaultV1ProviderExecutableKatDecisionGateRequest.currentEvidence(),
            ),
        )
        val rows = evidence.requiredEvidenceRows.associateBy { it.evidence }

        assertEquals(SkaldVaultV1ProviderExecutableKatRequiredEvidence.entries.toSet(), summary.requiredEvidence)
        assertEquals(SkaldVaultV1ProviderExecutableKatRequiredEvidence.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.futureRequiredBeforeTestOnlyExecutableKat)
            assertFalse(row.currentlySatisfied)
            assertFalse(row.canAuthorizeExecutableKatNow)
            assertFalse(row.canAuthorizeProduction)
            assertEquals(SkaldVaultV1ProviderExecutableKatRedactionClass.EvidenceClassNamesOnly, row.redactionClass)
            assertContains(row.statuses, SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled)
            assertContains(
                row.statuses,
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ExecutableKatNotAuthorized,
            )
            assertTrue(row.blockers.isNotEmpty())
        }
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderImplementationExistsButRemainsNonProduction,
            ).blockers,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderLevelPositiveKatsDefined)
                .blockers,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderExecutableKatRequiredEvidence.SecureStorageBoundaryReviewed)
                .blockers,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.SecureStorageDisabled,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderExecutableKatRequiredEvidence.SecureMetadataBoundaryReviewed)
                .blockers,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.SecureMetadataStorageDisabled,
        )
    }

    @Test
    fun futureStagesRemainModeledButDoNotAdvanceCurrentState() {
        val summary = SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(
                SkaldVaultV1ProviderExecutableKatDecisionGateRequest.currentEvidence(),
            ),
        )
        val rows = evidence.stageRows.associateBy { it.stage }

        assertEquals(SkaldVaultV1ProviderExecutableKatFutureStage.entries.toSet(), summary.futureStages)
        assertEquals(SkaldVaultV1ProviderExecutableKatFutureStage.entries.toSet(), rows.keys)
        assertEquals(SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate, evidence.currentStage)
        rows.values.forEach { row ->
            assertFalse(row.executableKatPresent)
            assertFalse(row.testOnlyExecutableKatAllowed)
            assertFalse(row.productionExecutableKatAllowed)
            assertFalse(row.canAuthorizeProviderSelection)
            assertFalse(row.canAuthorizeStorageOrVaultLifecycle)
            assertFalse(row.canAuthorizeMainnet)
        }
        assertFalse(rows.getValue(SkaldVaultV1ProviderExecutableKatFutureStage.NoExecutableKat).currentStage)
        assertTrue(rows.getValue(SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate).currentStage)
        assertContains(
            rows.getValue(SkaldVaultV1ProviderExecutableKatFutureStage.TestOnlyDeterministicProviderKatPrototype)
                .statuses,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.TestOnlyExecutableKatNotAuthorized,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderExecutableKatFutureStage.ProductionProviderStartupSelfTest).statuses,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ProductionExecutableKatNotAuthorized,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderExecutableKatFutureStage.MainnetReleaseValidation).blockers,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.MainnetDisabled,
        )
    }

    @Test
    fun redactedEvidenceOutputDoesNotExposeRawPayloadsKeysPathsOrHandles() {
        val rawTokens = listOf(
            "raw-payload-token",
            "raw-key-token",
            "raw-provider-handle-token",
            "raw-crypto-object-token",
            "raw-filesystem-path-token",
            "raw-backend-handle-token",
        )
        val externalEvidence = SkaldVaultV1ProviderExecutableKatDecisionGateRequest.externalEvidence(
            kind = SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.UserConsentOverride,
            safeEvidenceId = rawTokens.joinToString(separator = "|"),
            evidenceOnly = false,
            productionAuthorizingClaimed = true,
            userConsentOverrideRequested = true,
        )
        val request = SkaldVaultV1ProviderExecutableKatDecisionGateRequest.currentEvidence(
            externalEvidence = listOf(externalEvidence),
        )
        val evidence = blocked(SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.evaluate(request))
        val rendered = listOf(
            externalEvidence.toString(),
            request.toString(),
            evidence.policyTokenEvidence.toString(),
            evidence.toString(),
        ).joinToString(separator = "\n")

        rawTokens.forEach { token ->
            assertFalse(token in rendered, "Rendered decision evidence leaked raw token: $token")
        }
        assertFalse(evidence.policyTokenEvidence.containsRawMaterial)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsCryptoObject)
        assertFalse(evidence.policyTokenEvidence.containsDiagnosticPayload)
        assertFalse(evidence.policyTokenEvidence.containsFilesystemPath)
        assertFalse(evidence.policyTokenEvidence.containsBackendHandle)
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderExecutableKatRedactionClass.NoRawMaterial)
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderExecutableKatRedactionClass.NoProviderHandles)
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderExecutableKatRedactionClass.NoCryptoObjects)
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderExecutableKatRedactionClass.NoFilesystemPaths)
        assertContains(evidence.redactionClasses, SkaldVaultV1ProviderExecutableKatRedactionClass.NoBackendHandles)
    }

    @Test
    fun currentPolicySummaryKeepsEveryGateClosed() {
        val summary = SkaldVaultV1ProviderExecutableKatDecisionGatePolicy.currentPolicySummary()

        assertEquals(SkaldVaultV1ProviderExecutableKatDecisionGateStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderExecutableKatDecisionBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.entries.toSet(),
            summary.externalEvidenceKinds,
        )
        assertEquals(SkaldVaultV1ProviderExecutableKatRedactionClass.entries.toSet(), summary.redactionClasses)
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.blocksExecutableKat)
        assertTrue(summary.blocksProductionKat)
        assertTrue(summary.blocksProviderSelection)
        assertTrue(summary.blocksVaultCreationUnlockPersistence)
        assertTrue(summary.blocksMainnet)
        assertDisabled(summary.capability)
    }

    private fun blocked(
        result: SkaldVaultV1ProviderExecutableKatDecisionGateResult<
            SkaldVaultV1ProviderExecutableKatDecisionGateEvidence,
        >,
    ): SkaldVaultV1ProviderExecutableKatDecisionGateEvidence =
        assertIs<SkaldVaultV1ProviderExecutableKatDecisionGateResult.Blocked<
            SkaldVaultV1ProviderExecutableKatDecisionGateEvidence,
        >>(result).value

    private fun assertDisabled(capability: SkaldVaultV1ProviderExecutableKatDecisionCapability) {
        assertTrue(capability.decisionGateModeled)
        assertTrue(capability.futureEvidenceListDefined)
        assertFalse(capability.canIntroduceExecutableKatNow)
        assertFalse(capability.canIntroduceTestOnlyExecutableKatNow)
        assertFalse(capability.canIntroduceProductionExecutableKatNow)
        assertFalse(capability.canRunProviderOperations)
        assertFalse(capability.canRunRandomness)
        assertFalse(capability.canRunKdf)
        assertFalse(capability.canRunAead)
        assertFalse(capability.canRunHkdf)
        assertFalse(capability.canRunHmac)
        assertFalse(capability.canGenerateKeys)
        assertFalse(capability.canStoreKeysets)
        assertFalse(capability.canAuthorizeProviderSelection)
        assertFalse(capability.canSetProductionProviderSelectable)
        assertFalse(capability.canAuthorizeVaultCreation)
        assertFalse(capability.canAuthorizeVaultUnlock)
        assertFalse(capability.canAuthorizeVaultPersistence)
        assertFalse(capability.canAuthorizeMainnet)
    }

    private fun assertBlockedEvidence(evidence: SkaldVaultV1ProviderExecutableKatDecisionGateEvidence) {
        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.executableKatAllowedNow)
        assertFalse(evidence.testOnlyExecutableKatAllowedNow)
        assertFalse(evidence.productionExecutableKatAllowedNow)
        assertFalse(evidence.katResultCanAuthorizeProviderSelection)
        assertFalse(evidence.katResultCanSetProductionProviderSelectable)
        assertFalse(evidence.katResultCanAuthorizeVaultCreation)
        assertFalse(evidence.katResultCanAuthorizeVaultUnlock)
        assertFalse(evidence.katResultCanAuthorizeVaultPersistence)
        assertFalse(evidence.katResultCanAuthorizeMainnet)
        assertFalse(evidence.providerOperationsExecute)
        assertFalse(evidence.randomnessExecutes)
        assertFalse(evidence.kdfExecutes)
        assertFalse(evidence.aeadExecutes)
        assertFalse(evidence.hkdfExecutes)
        assertFalse(evidence.hmacExecutes)
        assertFalse(evidence.keyGenerationExecutes)
        assertFalse(evidence.keysetStorageExecutes)
        assertFalse(evidence.storageReadinessApproved)
        assertFalse(evidence.secureSecretStorageAvailable)
        assertFalse(evidence.secureMetadataStorageAvailable)
        assertFalse(evidence.productionSyncEnabled)
        assertFalse(evidence.mainnetEnabled)
    }
}
