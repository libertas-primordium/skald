package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixRequest
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixResult
import com.libertasprimordium.skald.security.SkaldVaultV1CreationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionCandidateFamily
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionRequiredGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionStage
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1RuntimeRandomnessAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1SecureStorageAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1UnlockAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationResult
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
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

class VaultProviderSelectionPromotionBlockersTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndPromotionCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                SkaldVaultV1ProviderSelectionPromotionRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-selection-promotion-blockers-v1",
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderSelectionPromotionStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderSelectionPromotionSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerSelectionPromotionBlockersModeled)
        assertTrue(evidence.providerSelectionPromotionBlockersStillDisabled)
        assertTrue(evidence.providerSelectionPromotionBlocksImplementation)
        assertTrue(evidence.providerSelectionPromotionBlocksFactory)
        assertTrue(evidence.providerSelectionPromotionBlocksRegistry)
        assertTrue(evidence.providerSelectionPromotionBlocksProviderSelection)
        assertTrue(evidence.providerSelectionPromotionBlocksProductionProviderSelectable)
        assertTrue(evidence.providerSelectionPromotionDoesNotRunCrypto)
        assertTrue(evidence.providerSelectionPromotionDoesNotEnableCreation)
        assertTrue(evidence.providerSelectionPromotionDoesNotEnableUnlock)
        assertTrue(evidence.providerSelectionPromotionDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)

        evidence.stageRows.forEach { row ->
            assertTrue(row.evidenceOnly)
            assertFalse(row.promotionAllowed)
            assertFalse(row.providerRuntimeAvailable)
            assertFalse(row.productionSelectable)
            assertFalse(row.operationAuthorized)
            assertContains(row.statuses, SkaldVaultV1ProviderSelectionPromotionStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderSelectionPromotionStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.WarningOnlyEvidenceCannotAuthorize)
            assertContains(row.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderSelectionPromotionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.MainnetDisabled)
        }
    }

    @Test
    fun promotionStagesAreCompleteAndEveryPostBuildStageIsBlocked() {
        val summary = SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                SkaldVaultV1ProviderSelectionPromotionRequest.promotionGateAudit(),
            ),
        )
        val rows = evidence.stageRows.associateBy { it.stage }

        assertEquals(SkaldVaultV1ProviderSelectionPromotionStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderSelectionPromotionStage.entries.toSet(), summary.stages)
        assertEquals(SkaldVaultV1ProviderSelectionPromotionRequiredGate.entries.toSet(), summary.requiredGates)
        assertEquals(SkaldVaultV1ProviderSelectionPromotionBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderSelectionPromotionRedactionClass.entries.toSet(),
            summary.redactionClasses,
        )
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.blocksImplementation)
        assertTrue(summary.blocksFactory)
        assertTrue(summary.blocksRegistry)
        assertTrue(summary.blocksProviderSelection)
        assertTrue(summary.blocksProductionProviderSelectable)

        assertEquals(SkaldVaultV1ProviderSelectionPromotionStage.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.blockers.isNotEmpty())
            assertTrue(row.requiredGates.isNotEmpty())
            assertFalse(row.promotionAllowed)
            assertFalse(row.productionSelectable)
        }
        SkaldVaultV1ProviderSelectionPromotionStage.entries
            .filterNot {
                it == SkaldVaultV1ProviderSelectionPromotionStage.CandidateDescribed ||
                    it == SkaldVaultV1ProviderSelectionPromotionStage.DependencyDeclaredBuildOnly
            }
            .forEach { stage ->
                assertFalse(rows.getValue(stage).buildEvidenceOnly)
                assertContains(rows.getValue(stage).statuses, SkaldVaultV1ProviderSelectionPromotionStatus.PromotionBlocked)
            }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionStage.ProductionSelectable).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionProviderSelectableFalse,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionStage.ProviderOperationAuthorizationApproved).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionStage.KatApproved).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.KatContractNotApproved,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionStage.KatApproved).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.KatExecutionUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionStage.MainnetApproved).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.MainnetDisabled,
        )
    }

    @Test
    fun candidateFamiliesRemainFutureOnlyNonSelectableOrFailClosed() {
        val evidence = blocked(
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                SkaldVaultV1ProviderSelectionPromotionRequest.currentEvidence(),
            ),
        )
        val rows = evidence.candidateRows.associateBy { it.family }

        assertEquals(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.candidateDescribed)
            assertFalse(row.implementationPresent)
            assertFalse(row.implementationEnabled)
            assertFalse(row.factoryPresent)
            assertFalse(row.factoryEnabled)
            assertFalse(row.registryEnabled)
            assertFalse(row.testScopeSelectable)
            assertFalse(row.productionSelectable)
            assertFalse(row.providerSelectable)
            assertFalse(row.productionProviderSelectable)
            assertFalse(row.operationAuthorized)
            assertContains(row.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider)
            assertContains(row.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionProviderSelectableFalse)
            assertContains(row.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.AuthorizationReadinessMatrixBlocked)
        }

        val disabled = rows.getValue(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.DisabledProvider)
        assertTrue(disabled.selectedByCurrentRegistry)
        assertTrue(disabled.disabledBoundaryOnly)
        assertContains(disabled.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.ImplementationDisabled)

        val tink = rows.getValue(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.TinkJvmCandidate)
        val bouncy = rows.getValue(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.BouncyCastleJvmCandidate)
        assertTrue(tink.providerCandidateOnly)
        assertTrue(bouncy.providerCandidateOnly)
        assertTrue(tink.dependencyBuildEvidencePresent)
        assertTrue(bouncy.dependencyBuildEvidencePresent)
        assertContains(tink.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildOnly)
        assertContains(bouncy.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildOnly)

        val android = rows.getValue(
            SkaldVaultV1ProviderSelectionPromotionCandidateFamily.AndroidKeystoreWrapperCandidate,
        )
        val osRandomness = rows.getValue(
            SkaldVaultV1ProviderSelectionPromotionCandidateFamily.PlatformOsCsprngCandidate,
        )
        assertTrue(android.wrapperOnly)
        assertFalse(android.providerCandidateOnly)
        assertTrue(osRandomness.randomnessSourceOnly)
        assertFalse(osRandomness.providerCandidateOnly)
        assertContains(android.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.SecureStorageAuthorizationBlocked)
        assertContains(osRandomness.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.RuntimeRandomnessBlocked)

        val testOnly = rows.getValue(
            SkaldVaultV1ProviderSelectionPromotionCandidateFamily.TestOnlyDeterministicCandidate,
        )
        assertTrue(testOnly.testOnly)
        assertContains(
            testOnly.blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.UnknownCandidate).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.CandidateEvidenceAbsent,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.UnsupportedCandidate).blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.CandidateEvidenceAbsent,
        )
    }

    @Test
    fun existingEvidenceCanBeSuppliedButCannotAuthorizeProviderPromotion() {
        val dependencyBuild = blockedDependencyBuild(
            SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                SkaldVaultV1ProviderDependencyBuildRequest.currentEvidence(),
            ),
        )
        val packaging = blockedPackaging(
            SkaldVaultV1ProviderCandidatePackagingPolicy.evaluate(
                SkaldVaultV1ProviderCandidatePackagingRequest.currentEvidence(),
            ),
        )
        val matrix = blockedMatrix(
            SkaldVaultV1AuthorizationReadinessMatrixPolicy.evaluate(
                SkaldVaultV1AuthorizationReadinessMatrixRequest.boundaryTrace(),
            ),
        )
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val contract = commonProductionProviderAcceptanceContract()
        val dependencyProbe = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val evidence = blocked(
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                SkaldVaultV1ProviderSelectionPromotionRequest.currentEvidence(
                    candidateFamily = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.TinkJvmCandidate,
                    stage = SkaldVaultV1ProviderSelectionPromotionStage.ProductionSelectable,
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                    providerSelectionResult = providerSelection,
                    providerAcceptanceAssessment = contract.assess(),
                    dependencyProbeResult = dependencyProbe,
                    providerOperationEvidence = providerOperationEvidence(),
                    runtimeRandomnessEvidence = runtimeRandomnessEvidence(),
                    kdfCalibrationEvidence = kdfCalibrationEvidence(),
                    secureStorageEvidence = secureStorageEvidence(),
                    creationAuthorizationEvidence = creationAuthorizationEvidence(),
                    unlockAuthorizationEvidence = unlockAuthorizationEvidence(),
                ),
            ),
        )

        assertEquals(listOf(SkaldVaultV1ProviderSelectionPromotionCandidateFamily.TinkJvmCandidate), evidence.candidateRows.map { it.family })
        assertEquals(listOf(SkaldVaultV1ProviderSelectionPromotionStage.ProductionSelectable), evidence.stageRows.map { it.stage })
        assertTrue(evidence.dependencyBuildEvidenceConsumed)
        assertTrue(evidence.providerCandidatePackagingEvidenceConsumed)
        assertTrue(evidence.authorizationReadinessMatrixEvidenceConsumed)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerAcceptanceEvidenceConsumed)
        assertTrue(evidence.dependencyProbeEvidenceConsumed)
        assertTrue(evidence.providerOperationEvidenceConsumed)
        assertTrue(evidence.runtimeRandomnessEvidenceConsumed)
        assertTrue(evidence.kdfCalibrationEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.creationAuthorizationEvidenceConsumed)
        assertTrue(evidence.unlockAuthorizationEvidenceConsumed)
        assertTrue(dependencyBuild.providerDependencyBuildSpikeStillDisabled)
        assertTrue(packaging.providerCandidatePackagingStillDisabled)
        assertTrue(matrix.authorizationReadinessMatrixStillDisabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(contract.assess().productionProviderSelectable)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildBoundaryStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderCandidatePackagingBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.AuthorizationReadinessMatrixBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.RuntimeRandomnessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.KdfCalibrationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.UnlockAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.WarningOnlyEvidenceCannotAuthorize)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.UserConsentCannotOverride)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderSelectionPromotionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyTokenStayRedacted() {
        val request = SkaldVaultV1ProviderSelectionPromotionRequest.forCandidate(
            SkaldVaultV1ProviderSelectionPromotionCandidateFamily.AndroidKeystoreWrapperCandidate,
        )
        val result = SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(request)
        val evidence = blocked(result)
        val forbiddenValues = listOf(
            "provider-handle",
            "crypto-object",
            "byte-material",
            "raw-path",
            "storage-id",
            "secret-value",
            "key-material",
            "random-bytes",
            "opaque-material-alpha",
            "opaque-material-beta",
        )

        forbiddenValues.forEach { raw ->
            assertFalse(request.toString().contains(raw))
            assertFalse(result.toString().contains(raw))
            assertFalse(evidence.toString().contains(raw))
            assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        }
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsCryptoObject)
        assertFalse(evidence.policyTokenEvidence.containsByteMaterial)
        assertFalse(evidence.policyTokenEvidence.containsPathOrRootText)
        assertFalse(evidence.policyTokenEvidence.containsStorageIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsSecretMaterial)
    }

    @Test
    fun individualAuditsReturnOnlyBlockedRows() {
        SkaldVaultV1ProviderSelectionPromotionCandidateFamily.entries.forEach { family ->
            val evidence = blocked(
                SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                    SkaldVaultV1ProviderSelectionPromotionRequest.forCandidate(family),
                ),
            )

            assertEquals(listOf(family), evidence.candidateRows.map { it.family })
            assertFalse(evidence.candidateRows.single().providerSelectable)
            assertFalse(evidence.candidateRows.single().productionSelectable)
            assertFalse(evidence.candidateRows.single().operationAuthorized)
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderSelectionPromotionStage.entries.forEach { stage ->
            val evidence = blocked(
                SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                    SkaldVaultV1ProviderSelectionPromotionRequest.forStage(stage),
                ),
            )

            assertEquals(listOf(stage), evidence.stageRows.map { it.stage })
            assertFalse(evidence.stageRows.single().promotionAllowed)
            assertFalse(evidence.stageRows.single().providerRuntimeAvailable)
            assertFalse(evidence.stageRows.single().operationAuthorized)
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
    }

    private fun providerOperationEvidence(): SkaldVaultV1VaultProviderOperationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun runtimeRandomnessEvidence(): SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked<
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun kdfCalibrationEvidence(): SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Blocked<
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun secureStorageEvidence(): SkaldVaultV1VaultSecureStorageAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultSecureStorageAuthorizationResult.Blocked<
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun creationAuthorizationEvidence(): SkaldVaultV1VaultCreationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultCreationAuthorizationResult.Blocked<
                SkaldVaultV1VaultCreationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun unlockAuthorizationEvidence(): SkaldVaultV1VaultUnlockAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultUnlockAuthorizationResult.Blocked<
                SkaldVaultV1VaultUnlockAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.noEvidence(),
            ),
        ).value

    private fun blocked(
        result: SkaldVaultV1ProviderSelectionPromotionResult<
            SkaldVaultV1ProviderSelectionPromotionEvidence,
        >,
    ): SkaldVaultV1ProviderSelectionPromotionEvidence =
        assertIs<
            SkaldVaultV1ProviderSelectionPromotionResult.Blocked<
                SkaldVaultV1ProviderSelectionPromotionEvidence,
            >,
        >(result).value

    private fun blockedDependencyBuild(
        result: SkaldVaultV1ProviderDependencyBuildResult<
            SkaldVaultV1ProviderDependencyBuildEvidence,
        >,
    ): SkaldVaultV1ProviderDependencyBuildEvidence =
        assertIs<
            SkaldVaultV1ProviderDependencyBuildResult.Blocked<
                SkaldVaultV1ProviderDependencyBuildEvidence,
            >,
        >(result).value

    private fun blockedPackaging(
        result: SkaldVaultV1ProviderCandidatePackagingResult<
            SkaldVaultV1ProviderCandidatePackagingEvidence,
        >,
    ): SkaldVaultV1ProviderCandidatePackagingEvidence =
        assertIs<
            SkaldVaultV1ProviderCandidatePackagingResult.Blocked<
                SkaldVaultV1ProviderCandidatePackagingEvidence,
            >,
        >(result).value

    private fun blockedMatrix(
        result: SkaldVaultV1AuthorizationReadinessMatrixResult<
            SkaldVaultV1AuthorizationReadinessMatrixEvidence,
        >,
    ): SkaldVaultV1AuthorizationReadinessMatrixEvidence =
        assertIs<
            SkaldVaultV1AuthorizationReadinessMatrixResult.Blocked<
                SkaldVaultV1AuthorizationReadinessMatrixEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1ProviderSelectionPromotionCapability) {
        assertTrue(capability.candidateDescribed)
        assertTrue(capability.dependencyBuildEvidencePresent)
        assertFalse(capability.dependencyReviewed)
        assertFalse(capability.sourceSetPlacementReviewed)
        assertFalse(capability.implementationPresent)
        assertFalse(capability.implementationEnabled)
        assertFalse(capability.factoryPresent)
        assertFalse(capability.factoryEnabled)
        assertFalse(capability.registryEnabled)
        assertFalse(capability.testScopeSelectable)
        assertFalse(capability.productionSelectable)
        assertFalse(capability.productionProviderSelectable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerKatApproved)
        assertFalse(capability.providerKatExecutionAvailable)
        assertFalse(capability.runtimeRandomnessAuthorized)
        assertFalse(capability.kdfProviderSupportApproved)
        assertFalse(capability.aeadProviderSupportApproved)
        assertFalse(capability.keyWrappingSupportApproved)
        assertFalse(capability.creationIntegrationApproved)
        assertFalse(capability.unlockIntegrationApproved)
        assertFalse(capability.persistenceIntegrationApproved)
        assertFalse(capability.releaseValidationApproved)
        assertFalse(capability.mainnetApproved)
    }

    private fun assertDisabled(evidence: SkaldVaultV1ProviderSelectionPromotionEvidence) {
        assertTrue(evidence.candidateDescribed)
        assertTrue(evidence.dependencyBuildEvidencePresent)
        assertFalse(evidence.dependencyReviewed)
        assertFalse(evidence.sourceSetPlacementReviewed)
        assertFalse(evidence.providerImplementationAdded)
        assertFalse(evidence.implementationPresent)
        assertFalse(evidence.implementationEnabled)
        assertFalse(evidence.providerFactoryAdded)
        assertFalse(evidence.factoryPresent)
        assertFalse(evidence.factoryEnabled)
        assertFalse(evidence.providerRegistryEnabled)
        assertFalse(evidence.registryEnabled)
        assertFalse(evidence.providerRuntimeInstantiable)
        assertFalse(evidence.testScopeSelectable)
        assertFalse(evidence.productionSelectable)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.providerKatApproved)
        assertFalse(evidence.providerKatExecutionAvailable)
        assertFalse(evidence.runtimeRandomnessAvailable)
        assertFalse(evidence.runtimeRandomnessAuthorized)
        assertFalse(evidence.kdfExecutionAvailable)
        assertFalse(evidence.kdfProviderSupportApproved)
        assertFalse(evidence.aeadExecutionAvailable)
        assertFalse(evidence.aeadProviderSupportApproved)
        assertFalse(evidence.hkdfHmacExecutionAvailable)
        assertFalse(evidence.keyWrappingAvailable)
        assertFalse(evidence.keyWrappingSupportApproved)
        assertFalse(evidence.vaultCreationAvailable)
        assertFalse(evidence.creationIntegrationApproved)
        assertFalse(evidence.vaultUnlockAvailable)
        assertFalse(evidence.unlockIntegrationApproved)
        assertFalse(evidence.vaultPersistenceAvailable)
        assertFalse(evidence.persistenceIntegrationApproved)
        assertFalse(evidence.releaseValidationApproved)
        assertFalse(evidence.mainnetAvailable)
        assertFalse(evidence.mainnetApproved)
    }
}
