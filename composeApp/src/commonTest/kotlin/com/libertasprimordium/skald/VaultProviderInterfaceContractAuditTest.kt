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
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractAuditStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractRequiredProperty
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractRisk
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderInterfaceContractTopic
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionResult
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

class VaultProviderInterfaceContractAuditTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndProviderInterfaceCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-interface-contract-audit-v1",
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderInterfaceContractAuditPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderInterfaceContractAuditStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1ProviderInterfaceContractAuditSource.CurrentTypedEvidence, evidence.source)
        assertTrue(evidence.providerInterfaceContractAuditModeled)
        assertTrue(evidence.providerInterfaceContractAuditStillDisabled)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotImplementProvider)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotAcceptSecrets)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotAcceptByteMaterial)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotAcceptProviderHandles)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotRunCrypto)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotEnableProviderSelection)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotEnableCreation)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotEnableUnlock)
        assertTrue(evidence.providerInterfaceContractAuditDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun interfaceTopicCoverageModelsEveryRequiredBoundaryAndKeepsRuntimeUnavailable() {
        val summary = SkaldVaultV1ProviderInterfaceContractAuditPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(),
            ),
        )
        val rows = evidence.topicRows.associateBy { it.topic }

        assertEquals(SkaldVaultV1ProviderInterfaceContractAuditStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderInterfaceContractTopic.entries.toSet(), summary.topics)
        assertEquals(SkaldVaultV1ProviderInterfaceContractRisk.entries.toSet(), summary.risks)
        assertEquals(
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.entries.toSet(),
            summary.requiredProperties,
        )
        assertEquals(SkaldVaultV1ProviderInterfaceContractBlocker.entries.toSet(), summary.blockers)
        assertEquals(
            SkaldVaultV1ProviderInterfaceContractRedactionClass.entries.toSet(),
            summary.redactionClasses,
        )
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.providerNeutral)
        assertTrue(summary.rejectsRuntimeMaterial)
        assertTrue(summary.blocksExecution)
        assertTrue(summary.blocksProviderSelection)

        assertEquals(SkaldVaultV1ProviderInterfaceContractTopic.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.providerNeutral)
            assertTrue(row.evidenceOnly)
            assertFalse(row.runtimeAvailable)
            assertFalse(row.selectable)
            assertTrue(row.operationAuthorizationRequired)
            assertTrue(row.promotionBlocked)
            assertTrue(row.readinessMatrixRequired)
            assertTrue(row.blockers.isNotEmpty())
            assertTrue(row.requiredProperties.isNotEmpty())
            assertContains(row.statuses, SkaldVaultV1ProviderInterfaceContractAuditStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderInterfaceContractAuditStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.WarningOnlyEvidenceCannotAuthorize)
            assertContains(row.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderInterfaceContractBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.MainnetDisabled)
        }
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.ProviderSelectionRegistryBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionLockedToDisabledProvider,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.ProviderSelectionRegistryBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.ProviderOperationAuthorizationBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.ProviderDependencyBuildEvidenceBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderDependencyBuildStillDisabled,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.ProviderCandidatePackagingBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderCandidatePackagingStillDisabled,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.ProviderPromotionBlockerBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractTopic.AuthorizationReadinessMatrixBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked,
        )
    }

    @Test
    fun contractRiskCoverageRejectsEveryUnsafeProviderInterfacePath() {
        val evidence = blocked(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(),
            ),
        )
        val rows = evidence.riskRows.associateBy { it.risk }

        assertEquals(SkaldVaultV1ProviderInterfaceContractRisk.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.rejected)
            assertFalse(row.accepted)
            assertFalse(row.canExecute)
            assertFalse(row.canBypassAuthorization)
            assertFalse(row.canChangeSelectability)
            assertFalse(row.exposesDiagnostics)
            assertContains(row.statuses, SkaldVaultV1ProviderInterfaceContractAuditStatus.MaterialRejected)
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderInterfaceContractRisk.AcceptsRealKeyMaterial).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.SecretMaterialRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderInterfaceContractRisk.AcceptsRandomSaltNonceMaterial).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderInterfaceContractRisk.AcceptsProviderHandles).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderHandlesRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderInterfaceContractRisk.AcceptsPlatformCryptoObjects).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.CryptoObjectsRejected,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderInterfaceContractRisk.CanExecuteOperationsDirectly).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderInterfaceContractRisk.CanMakeProviderSelectable).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRisk.CanIntroducePlatformCryptoImportsInCommonSource,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.CommonSourcePlatformCryptoImportsForbidden,
        )
    }

    @Test
    fun requiredPropertiesRemainSatisfiedOnlyForCurrentTypedEvidenceAndRequireFutureRuntimeGates() {
        val evidence = blocked(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(),
            ),
        )
        val rows = evidence.requiredPropertyRows.associateBy { it.requiredProperty }

        assertEquals(SkaldVaultV1ProviderInterfaceContractRequiredProperty.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.satisfiedForCurrentModel)
            assertTrue(row.futureRuntimeGateRequired)
            assertFalse(row.runtimeAvailable)
            assertFalse(row.selectable)
            assertContains(row.statuses, SkaldVaultV1ProviderInterfaceContractAuditStatus.StillDisabled)
        }
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoByteMaterialAccepted,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ByteMaterialRejected,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoProviderOperationExecutableFromModelBoundary,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRequiredProperty.PromotionBlockersPreventBuildEvidenceSelectability,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRequiredProperty.ReadinessMatrixPreventsWarningUserConsentTestOnlyPromotion,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProviderSelectionLockedToDisabledProvider,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionLockedToDisabledProvider,
        )
        assertContains(
            rows.getValue(
                SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProductionProviderSelectableFalse,
            ).blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse,
        )
    }

    @Test
    fun existingEvidenceCanBeSuppliedButCannotAuthorizeInterfaceExecutionOrPromotion() {
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
        val promotion = blockedPromotion(
            SkaldVaultV1ProviderSelectionPromotionBlockersPolicy.evaluate(
                SkaldVaultV1ProviderSelectionPromotionRequest.currentEvidence(
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                ),
            ),
        )
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val contract = commonProductionProviderAcceptanceContract()
        val dependencyProbe = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val evidence = blocked(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(
                    providerSelectionPromotionEvidence = promotion,
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

        assertTrue(evidence.providerSelectionPromotionEvidenceConsumed)
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
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(contract.assess().productionProviderSelectable)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.ProviderDependencyBuildStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.ProviderCandidatePackagingStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.UnlockAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.WarningOnlyEvidenceCannotAuthorize)
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.UserConsentCannotOverride)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderInterfaceContractBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderInterfaceContractBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyTokenStayRedacted() {
        val request = SkaldVaultV1ProviderInterfaceContractAuditRequest.forRisk(
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsProviderHandles,
        )
        val result = SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(request)
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
    fun individualAuditRequestsStayBlockedAndScopedToRequestedRows() {
        SkaldVaultV1ProviderInterfaceContractTopic.entries.forEach { topic ->
            val evidence = blocked(
                SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                    SkaldVaultV1ProviderInterfaceContractAuditRequest.forTopic(topic),
                ),
            )

            assertEquals(listOf(topic), evidence.topicRows.map { it.topic })
            assertFalse(evidence.topicRows.single().runtimeAvailable)
            assertFalse(evidence.topicRows.single().selectable)
            assertTrue(evidence.riskRows.isNotEmpty())
            assertTrue(evidence.requiredPropertyRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderInterfaceContractRisk.entries.forEach { risk ->
            val evidence = blocked(
                SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                    SkaldVaultV1ProviderInterfaceContractAuditRequest.forRisk(risk),
                ),
            )

            assertEquals(listOf(risk), evidence.riskRows.map { it.risk })
            assertTrue(evidence.riskRows.single().rejected)
            assertFalse(evidence.riskRows.single().accepted)
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderInterfaceContractRequiredProperty.entries.forEach { property ->
            val evidence = blocked(
                SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                    SkaldVaultV1ProviderInterfaceContractAuditRequest.forRequiredProperty(property),
                ),
            )

            assertEquals(listOf(property), evidence.requiredPropertyRows.map { it.requiredProperty })
            assertTrue(evidence.requiredPropertyRows.single().satisfiedForCurrentModel)
            assertFalse(evidence.requiredPropertyRows.single().runtimeAvailable)
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
        result: SkaldVaultV1ProviderInterfaceContractAuditResult<
            SkaldVaultV1ProviderInterfaceContractAuditEvidence,
        >,
    ): SkaldVaultV1ProviderInterfaceContractAuditEvidence =
        assertIs<
            SkaldVaultV1ProviderInterfaceContractAuditResult.Blocked<
                SkaldVaultV1ProviderInterfaceContractAuditEvidence,
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

    private fun blockedPromotion(
        result: SkaldVaultV1ProviderSelectionPromotionResult<
            SkaldVaultV1ProviderSelectionPromotionEvidence,
        >,
    ): SkaldVaultV1ProviderSelectionPromotionEvidence =
        assertIs<
            SkaldVaultV1ProviderSelectionPromotionResult.Blocked<
                SkaldVaultV1ProviderSelectionPromotionEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1ProviderInterfaceContractCapability) {
        assertTrue(capability.providerInterfaceAuditedForSelection)
        assertFalse(capability.providerInterfaceAcceptsSecrets)
        assertFalse(capability.providerInterfaceAcceptsByteMaterial)
        assertFalse(capability.providerInterfaceAcceptsProviderHandles)
        assertFalse(capability.providerInterfaceAcceptsCryptoObjects)
        assertFalse(capability.providerInterfaceCanExecuteOperations)
        assertFalse(capability.providerInterfaceCanBypassAuthorization)
        assertFalse(capability.providerInterfaceCanSelectProvider)
        assertFalse(capability.providerInterfaceCanPromoteProvider)
        assertFalse(capability.providerInterfaceCanSetProductionProviderSelectable)
        assertFalse(capability.providerImplementationAdded)
        assertFalse(capability.providerFactoryAdded)
        assertFalse(capability.providerRegistryEnabled)
        assertFalse(capability.providerRuntimeInstantiable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelectable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerKatExecutionAvailable)
        assertFalse(capability.runtimeRandomnessAvailable)
        assertFalse(capability.kdfExecutionAvailable)
        assertFalse(capability.aeadExecutionAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertDisabled(evidence: SkaldVaultV1ProviderInterfaceContractAuditEvidence) {
        assertTrue(evidence.providerInterfaceAuditedForSelection)
        assertFalse(evidence.providerInterfaceAcceptsSecrets)
        assertFalse(evidence.providerInterfaceAcceptsByteMaterial)
        assertFalse(evidence.providerInterfaceAcceptsProviderHandles)
        assertFalse(evidence.providerInterfaceAcceptsCryptoObjects)
        assertFalse(evidence.providerInterfaceCanExecuteOperations)
        assertFalse(evidence.providerInterfaceCanBypassAuthorization)
        assertFalse(evidence.providerInterfaceCanSelectProvider)
        assertFalse(evidence.providerInterfaceCanPromoteProvider)
        assertFalse(evidence.providerInterfaceCanSetProductionProviderSelectable)
        assertFalse(evidence.providerImplementationAdded)
        assertFalse(evidence.providerFactoryAdded)
        assertFalse(evidence.providerRegistryEnabled)
        assertFalse(evidence.providerRuntimeInstantiable)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.providerKatExecutionAvailable)
        assertFalse(evidence.runtimeRandomnessAvailable)
        assertFalse(evidence.kdfExecutionAvailable)
        assertFalse(evidence.aeadExecutionAvailable)
        assertFalse(evidence.vaultCreationAvailable)
        assertFalse(evidence.vaultUnlockAvailable)
        assertFalse(evidence.vaultPersistenceAvailable)
        assertFalse(evidence.mainnetAvailable)
    }
}
