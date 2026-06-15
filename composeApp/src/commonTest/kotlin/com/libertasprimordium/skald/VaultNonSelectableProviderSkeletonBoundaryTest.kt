package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixRequest
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixResult
import com.libertasprimordium.skald.security.SkaldVaultV1CreationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonRequest
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonResult
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonSource
import com.libertasprimordium.skald.security.SkaldVaultV1NonSelectableProviderSkeletonStatus
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
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSelectionPromotionResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonDependencyVisibility
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonFamily
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonOperationSurface
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonSourceSetPlacement
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderSkeletonTopic
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
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultNonSelectableProviderSkeletonBoundaryTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndSkeletonCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                SkaldVaultV1NonSelectableProviderSkeletonRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-non-selectable-provider-skeleton-boundary-v1",
            SkaldVaultV1NonSelectableProviderSkeletonPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1NonSelectableProviderSkeletonPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1NonSelectableProviderSkeletonStatus.StillDisabled, evidence.status)
        assertEquals(SkaldVaultV1NonSelectableProviderSkeletonSource.CurrentSkeletonEvidence, evidence.source)
        assertEquals(
            SkaldVaultV1ProviderSkeletonFamily.TinkBouncyCastleSplitFutureSkeleton,
            evidence.family,
        )
        assertTrue(evidence.nonSelectableProviderSkeletonBoundaryModeled)
        assertTrue(evidence.nonSelectableProviderSkeletonStillDisabled)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotImplementExecutableProvider)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotAddFactory)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotRegisterProvider)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotEnableProviderSelection)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotRunCrypto)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotRunKat)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotEnableCreation)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotEnableUnlock)
        assertTrue(evidence.nonSelectableProviderSkeletonDoesNotEnablePersistence)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun skeletonTopicCoverageModelsEveryRequiredSurfaceAndKeepsRegistryUnavailable() {
        val summary = SkaldVaultV1NonSelectableProviderSkeletonPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                SkaldVaultV1NonSelectableProviderSkeletonRequest.currentEvidence(),
            ),
        )
        val rows = evidence.topicRows.associateBy { it.topic }

        assertEquals(SkaldVaultV1NonSelectableProviderSkeletonStatus.entries.toSet(), summary.statuses)
        assertEquals(SkaldVaultV1ProviderSkeletonFamily.entries.toSet(), summary.families)
        assertEquals(
            SkaldVaultV1ProviderSkeletonSourceSetPlacement.entries.toSet(),
            summary.sourceSetPlacements,
        )
        assertEquals(
            SkaldVaultV1ProviderSkeletonDependencyVisibility.entries.toSet(),
            summary.dependencyVisibility,
        )
        assertEquals(SkaldVaultV1ProviderSkeletonTopic.entries.toSet(), summary.topics)
        assertEquals(
            SkaldVaultV1ProviderSkeletonOperationSurface.entries.toSet(),
            summary.operationSurfaces,
        )
        assertEquals(SkaldVaultV1ProviderSkeletonBlocker.entries.toSet(), summary.blockers)
        assertEquals(SkaldVaultV1ProviderSkeletonRedactionClass.entries.toSet(), summary.redactionClasses)
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.compileShapeOnly)
        assertTrue(summary.nonSelectable)

        assertEquals(SkaldVaultV1ProviderSkeletonTopic.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.evidenceOnly)
            assertFalse(row.runtimeAvailable)
            assertFalse(row.selectable)
            assertFalse(row.registered)
            assertFalse(row.instantiableByRegistry)
            assertTrue(row.requiredFutureEvidence.isNotEmpty())
            assertContains(row.statuses, SkaldVaultV1NonSelectableProviderSkeletonStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1NonSelectableProviderSkeletonStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderSkeletonBlocker.WarningOnlyEvidenceCannotAuthorize)
            assertContains(row.blockers, SkaldVaultV1ProviderSkeletonBlocker.UserConsentCannotOverride)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderSkeletonBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderSkeletonBlocker.MainnetDisabled)
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonTopic.ProviderImplementationClassStatus).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.RuntimeProviderImplementationForbidden,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonTopic.FactoryClassStatus).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.FactoryMissing,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonTopic.ProviderRegistryStatus).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.RegistryDisabled,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonTopic.ProviderSelectableStatus).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.ProductionProviderSelectableFalse,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonTopic.ReadinessMatrixStatus).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.AuthorizationReadinessMatrixBlocked,
        )
    }

    @Test
    fun everyOperationSurfaceIsModeledAndDisabled() {
        val evidence = blocked(
            SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                SkaldVaultV1NonSelectableProviderSkeletonRequest.currentEvidence(),
            ),
        )
        val rows = evidence.operationSurfaceRows.associateBy { it.operationSurface }

        assertEquals(SkaldVaultV1ProviderSkeletonOperationSurface.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.modeled)
            assertTrue(row.disabled)
            assertFalse(row.executable)
            assertFalse(row.authorized)
            assertFalse(row.productionRuntimeAvailable)
            assertContains(row.statuses, SkaldVaultV1NonSelectableProviderSkeletonStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1NonSelectableProviderSkeletonStatus.EvidenceOnly)
            assertContains(row.blockers, SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked)
        }
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.ProviderKat).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.KatExecutionUnavailable,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.RuntimeRandomnessCheck).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.SaltGeneration).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.KdfArgon2id).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.HkdfExtractExpand).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.Hmac).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.AeadEncrypt).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.KeyWrap).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.SecureStorageAuthorizationBlocked,
        )
        assertContains(
            rows.getValue(SkaldVaultV1ProviderSkeletonOperationSurface.ProviderClearDispose).blockers,
            SkaldVaultV1ProviderSkeletonBlocker.OperationSurfaceDisabled,
        )
    }

    @Test
    fun existingEvidenceCanBeSuppliedButCannotAuthorizeSkeletonSelectionOrExecution() {
        val interfaceAudit = blockedInterfaceAudit(
            SkaldVaultV1ProviderInterfaceContractAuditPolicy.evaluate(
                SkaldVaultV1ProviderInterfaceContractAuditRequest.currentEvidence(),
            ),
        )
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
        val evidence = blocked(
            SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                SkaldVaultV1NonSelectableProviderSkeletonRequest.currentEvidence(
                    providerInterfaceAuditEvidence = interfaceAudit,
                    providerSelectionPromotionEvidence = promotion,
                    dependencyBuildEvidence = dependencyBuild,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                    providerOperationEvidence = providerOperationEvidence(),
                    runtimeRandomnessEvidence = runtimeRandomnessEvidence(),
                    kdfCalibrationEvidence = kdfCalibrationEvidence(),
                    secureStorageEvidence = secureStorageEvidence(),
                    creationAuthorizationEvidence = creationAuthorizationEvidence(),
                    unlockAuthorizationEvidence = unlockAuthorizationEvidence(),
                ),
            ),
        )
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(evidence.providerInterfaceAuditEvidenceConsumed)
        assertTrue(evidence.providerSelectionPromotionEvidenceConsumed)
        assertTrue(evidence.dependencyBuildEvidenceConsumed)
        assertTrue(evidence.providerCandidatePackagingEvidenceConsumed)
        assertTrue(evidence.authorizationReadinessMatrixEvidenceConsumed)
        assertTrue(evidence.providerOperationEvidenceConsumed)
        assertTrue(evidence.runtimeRandomnessEvidenceConsumed)
        assertTrue(evidence.kdfCalibrationEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.creationAuthorizationEvidenceConsumed)
        assertTrue(evidence.unlockAuthorizationEvidenceConsumed)
        assertContains(promotion.blockers, SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.ProviderInterfaceAuditRequired)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.ProviderSelectionPromotionBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.ProviderDependencyBuildStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.ProviderCandidatePackagingStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.AuthorizationReadinessMatrixBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.CreationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1ProviderSkeletonBlocker.UnlockAuthorizationBlocked)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyEvidenceStayRedacted() {
        val request = SkaldVaultV1NonSelectableProviderSkeletonRequest.forOperationSurface(
            SkaldVaultV1ProviderSkeletonOperationSurface.RecordDecrypt,
        )
        val result = SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(request)
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
            assertFalse(evidence.redactedPolicyEvidence.toString().contains(raw))
        }
        assertFalse(evidence.redactedPolicyEvidence.containsProviderHandle)
        assertFalse(evidence.redactedPolicyEvidence.containsCryptoObject)
        assertFalse(evidence.redactedPolicyEvidence.containsByteMaterial)
        assertFalse(evidence.redactedPolicyEvidence.containsPathOrRootText)
        assertFalse(evidence.redactedPolicyEvidence.containsStorageIdentifier)
        assertFalse(evidence.redactedPolicyEvidence.containsSecretMaterial)
    }

    @Test
    fun individualAuditRequestsStayBlockedAndScopedToRequestedRows() {
        SkaldVaultV1ProviderSkeletonTopic.entries.forEach { topic ->
            val evidence = blocked(
                SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                    SkaldVaultV1NonSelectableProviderSkeletonRequest.forTopic(topic),
                ),
            )

            assertEquals(listOf(topic), evidence.topicRows.map { it.topic })
            assertTrue(evidence.topicRows.single().modeled)
            assertFalse(evidence.topicRows.single().runtimeAvailable)
            assertFalse(evidence.topicRows.single().selectable)
            assertTrue(evidence.operationSurfaceRows.isNotEmpty())
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
        SkaldVaultV1ProviderSkeletonOperationSurface.entries.forEach { operationSurface ->
            val evidence = blocked(
                SkaldVaultV1NonSelectableProviderSkeletonPolicy.evaluate(
                    SkaldVaultV1NonSelectableProviderSkeletonRequest.forOperationSurface(operationSurface),
                ),
            )

            assertEquals(listOf(operationSurface), evidence.operationSurfaceRows.map { it.operationSurface })
            assertTrue(evidence.operationSurfaceRows.single().disabled)
            assertFalse(evidence.operationSurfaceRows.single().executable)
            assertTrue(evidence.topicRows.isNotEmpty())
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
        result: SkaldVaultV1NonSelectableProviderSkeletonResult<
            SkaldVaultV1NonSelectableProviderSkeletonEvidence,
        >,
    ): SkaldVaultV1NonSelectableProviderSkeletonEvidence =
        assertIs<
            SkaldVaultV1NonSelectableProviderSkeletonResult.Blocked<
                SkaldVaultV1NonSelectableProviderSkeletonEvidence,
            >,
        >(result).value

    private fun blockedInterfaceAudit(
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

    private fun assertDisabled(capability: SkaldVaultV1ProviderSkeletonCapability) {
        assertTrue(capability.providerSkeletonModeled)
        assertFalse(capability.providerSkeletonImplementsRuntimeProvider)
        assertFalse(capability.providerSkeletonRegistered)
        assertFalse(capability.providerSkeletonSelectable)
        assertFalse(capability.providerSkeletonInstantiableByRegistry)
        assertFalse(capability.providerSkeletonExecutesOperations)
        assertFalse(capability.providerSkeletonRunsKat)
        assertFalse(capability.providerSkeletonUsesRandomness)
        assertFalse(capability.providerSkeletonRunsKdf)
        assertFalse(capability.providerSkeletonRunsAead)
        assertFalse(capability.providerSkeletonWrapsKeys)
        assertFalse(capability.providerSkeletonCreatesVault)
        assertFalse(capability.providerSkeletonUnlocksVault)
        assertFalse(capability.providerSkeletonPersistsVault)
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

    private fun assertDisabled(evidence: SkaldVaultV1NonSelectableProviderSkeletonEvidence) {
        assertTrue(evidence.providerSkeletonModeled)
        assertFalse(evidence.providerSkeletonImplementsRuntimeProvider)
        assertFalse(evidence.providerSkeletonRegistered)
        assertFalse(evidence.providerSkeletonSelectable)
        assertFalse(evidence.providerSkeletonInstantiableByRegistry)
        assertFalse(evidence.providerSkeletonExecutesOperations)
        assertFalse(evidence.providerSkeletonRunsKat)
        assertFalse(evidence.providerSkeletonUsesRandomness)
        assertFalse(evidence.providerSkeletonRunsKdf)
        assertFalse(evidence.providerSkeletonRunsAead)
        assertFalse(evidence.providerSkeletonWrapsKeys)
        assertFalse(evidence.providerSkeletonCreatesVault)
        assertFalse(evidence.providerSkeletonUnlocksVault)
        assertFalse(evidence.providerSkeletonPersistsVault)
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
