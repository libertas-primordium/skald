package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixRequest
import com.libertasprimordium.skald.security.SkaldVaultV1AuthorizationReadinessMatrixResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderCandidatePackagingResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildCapability
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildRequest
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildSource
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyBuildStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyCandidateFamily
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencyDeclarationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderDependencySourceSetPlacement
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultProviderDependencyBuildBoundaryTest {
    @Test
    fun defaultStatusIsStillDisabledEvidenceOnlyAndRuntimeCapabilitiesRemainFalse() {
        val evidence = blocked(
            SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                SkaldVaultV1ProviderDependencyBuildRequest.currentEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-dependency-build-boundary-v1",
            SkaldVaultV1ProviderDependencyBuildPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderDependencyBuildPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1ProviderDependencyBuildStatus.StillDisabled, evidence.status)
        assertTrue(evidence.providerDependencyBuildSpikeModeled)
        assertTrue(evidence.providerDependencyBuildSpikeStillDisabled)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotImplementProvider)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotEnableProviderSelection)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotRunCrypto)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotRunKat)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotEnableCreation)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotEnableUnlock)
        assertTrue(evidence.providerDependencyBuildSpikeDoesNotEnablePersistence)
        assertTrue(evidence.providerDependencyDeclaredForBuildOnly)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)

        evidence.candidateRows.forEach { row ->
            assertTrue(row.buildEvidenceOnly)
            assertFalse(row.declaredByThisBranch)
            assertFalse(row.runtimeDeclaredByThisBranch)
            assertFalse(row.importedInProduction)
            assertFalse(row.importedInTests)
            assertFalse(row.executable)
            assertFalse(row.selectable)
            assertFalse(row.productionAuthorized)
            assertContains(row.statuses, SkaldVaultV1ProviderDependencyBuildStatus.StillDisabled)
            assertContains(row.statuses, SkaldVaultV1ProviderDependencyBuildStatus.EvidenceOnly)
            assertContains(row.statuses, SkaldVaultV1ProviderDependencyBuildStatus.NonExecutable)
            assertContains(row.statuses, SkaldVaultV1ProviderDependencyBuildStatus.NonSelectable)
            assertContains(row.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ProviderImplementationMissing)
            assertContains(row.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ProviderFactoryMissing)
            assertContains(row.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ProviderRuntimeNotInstantiable)
            assertContains(row.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ProviderRegistryUnchanged)
            assertContains(row.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.DisabledProviderSelection)
            assertContains(
                row.blockers,
                SkaldVaultV1ProviderDependencyBuildBlocker.ProductionProviderSelectableFalse,
            )
            assertContains(row.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.MainnetDisabled)
        }
    }

    @Test
    fun dependencyEvidenceRecordsExistingPlatformDeclarationsWithoutApprovingRuntimeUse() {
        val rows = blocked(
            SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                SkaldVaultV1ProviderDependencyBuildRequest.currentEvidence(),
            ),
        ).candidateRows.associateBy { it.family }

        val tink = rows.getValue(SkaldVaultV1ProviderDependencyCandidateFamily.TinkJvmCandidate)
        val bouncy = rows.getValue(SkaldVaultV1ProviderDependencyCandidateFamily.BouncyCastleJvmCandidate)

        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.SourceSetDependencyDeclaredOnly, tink.declarationStatus)
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.SourceSetDependencyDeclaredOnly, bouncy.declarationStatus)
        assertContains(tink.sourceSetPlacements, SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidPlatformDependencyDeclaredOnly)
        assertContains(tink.sourceSetPlacements, SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopPlatformDependencyDeclaredOnly)
        assertContains(tink.sourceSetPlacements, SkaldVaultV1ProviderDependencySourceSetPlacement.CommonProductionRuntimeImportsForbidden)
        assertContains(bouncy.sourceSetPlacements, SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidPlatformDependencyDeclaredOnly)
        assertContains(bouncy.sourceSetPlacements, SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopPlatformDependencyDeclaredOnly)
        assertContains(bouncy.sourceSetPlacements, SkaldVaultV1ProviderDependencySourceSetPlacement.CommonProductionRuntimeImportsForbidden)
        assertContains(tink.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ExistingSplitStackDeclarationsObserved)
        assertContains(bouncy.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ExistingSplitStackDeclarationsObserved)
        assertContains(tink.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.NoNewSingleDependencyAddedThisBranch)
        assertContains(bouncy.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.NoNewSingleDependencyAddedThisBranch)
        assertContains(tink.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ProviderDecisionIsSplitStackNotSingleFamily)
        assertContains(bouncy.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.ProviderDecisionIsSplitStackNotSingleFamily)
        assertFalse(tink.runtimeDeclaredByThisBranch)
        assertFalse(bouncy.runtimeDeclaredByThisBranch)
        assertFalse(tink.executable)
        assertFalse(bouncy.executable)
        assertFalse(tink.selectable)
        assertFalse(bouncy.selectable)
    }

    @Test
    fun candidateFamiliesDeclarationStatusesSourceSetsAndRequiredGatesAreComplete() {
        val summary = SkaldVaultV1ProviderDependencyBuildPolicy.currentPolicySummary()
        val evidence = blocked(
            SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                SkaldVaultV1ProviderDependencyBuildRequest.sourceSetPlacementAudit(),
            ),
        )
        val rows = evidence.candidateRows.associateBy { it.family }

        assertEquals(SkaldVaultV1ProviderDependencyCandidateFamily.entries.toSet(), summary.candidateFamilies)
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.entries.toSet(), summary.declarationStatuses)
        assertEquals(SkaldVaultV1ProviderDependencySourceSetPlacement.entries.toSet(), summary.sourceSetPlacements)
        assertEquals(SkaldVaultV1ProviderDependencyBuildGate.entries.toSet(), summary.requiredGates)
        assertEquals(SkaldVaultV1ProviderDependencyBuildBlocker.entries.toSet(), summary.blockers)
        assertEquals(SkaldVaultV1ProviderDependencyBuildSource.SourceSetPlacementAudit, evidence.source)
        assertTrue(summary.stillDisabled)
        assertTrue(summary.evidenceOnly)
        assertTrue(summary.noRuntimeProviderDependencyAddedThisBranch)
        assertTrue(summary.noCandidateSelectable)
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderDependencySourceSetPlacement.CommonModelEvidenceOnly,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderDependencySourceSetPlacement.CommonProductionRuntimeImportsForbidden,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidProductionExecutionForbidden,
        )
        assertContains(
            evidence.sourceSetPlacements,
            SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopProductionExecutionForbidden,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderDependencyBuildGate.ProviderOperationAuthorizationApproved,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderDependencyBuildGate.RuntimeRandomnessAuthorizationApproved,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderDependencyBuildGate.KdfCalibrationAuthorizationApproved,
        )
        assertContains(
            evidence.requiredGates,
            SkaldVaultV1ProviderDependencyBuildGate.AuthorizationReadinessMatrixAllowsPromotion,
        )
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.Absent, rows.getValue(
            SkaldVaultV1ProviderDependencyCandidateFamily.AndroidPlatformWrapperCandidate,
        ).declarationStatus)
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.Absent, rows.getValue(
            SkaldVaultV1ProviderDependencyCandidateFamily.OsCsprngPlatformCandidate,
        ).declarationStatus)
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.TestOnlyDeclaredOnly, rows.getValue(
            SkaldVaultV1ProviderDependencyCandidateFamily.TestOnlyDeterministicCandidate,
        ).declarationStatus)
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.Unknown, rows.getValue(
            SkaldVaultV1ProviderDependencyCandidateFamily.UnknownCandidate,
        ).declarationStatus)
        assertEquals(SkaldVaultV1ProviderDependencyDeclarationStatus.Forbidden, rows.getValue(
            SkaldVaultV1ProviderDependencyCandidateFamily.UnsupportedCandidate,
        ).declarationStatus)
    }

    @Test
    fun packagingMatrixDependencyProbeAndSelectionEvidenceCannotAuthorizePromotion() {
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
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val evidence = blocked(
            SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                SkaldVaultV1ProviderDependencyBuildRequest.currentEvidence(
                    candidateFamily = SkaldVaultV1ProviderDependencyCandidateFamily.TinkJvmCandidate,
                    declarationStatus = SkaldVaultV1ProviderDependencyDeclarationStatus.SourceSetDependencyDeclaredOnly,
                    sourceSetPlacement =
                        SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopPlatformDependencyDeclaredOnly,
                    providerCandidatePackagingEvidence = packaging,
                    authorizationReadinessMatrixEvidence = matrix,
                    dependencyProbeResult = dependency,
                    providerSelectionResult = providerSelection,
                ),
            ),
        )

        assertEquals(listOf(SkaldVaultV1ProviderDependencyCandidateFamily.TinkJvmCandidate), evidence.candidateRows.map { it.family })
        assertTrue(evidence.providerCandidatePackagingEvidenceConsumed)
        assertTrue(evidence.authorizationReadinessMatrixEvidenceConsumed)
        assertTrue(evidence.dependencyProbeEvidenceConsumed)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(packaging.providerCandidatePackagingStillDisabled)
        assertTrue(matrix.authorizationReadinessMatrixStillDisabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
        assertContains(evidence.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.DisabledProviderSelection)
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderDependencyBuildBlocker.ProductionProviderSelectableFalse,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderDependencyBuildBlocker.ProviderOperationAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderDependencyBuildBlocker.RuntimeRandomnessAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderDependencyBuildBlocker.KdfCalibrationAuthorizationBlocked,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1ProviderDependencyBuildBlocker.AuthorizationReadinessMatrixBlocksPromotion,
        )
        assertContains(evidence.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.WarningOnlyEvidenceCannotAuthorize)
        assertContains(evidence.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.TestOnlyEvidenceRejectedForProduction)
        assertContains(evidence.blockers, SkaldVaultV1ProviderDependencyBuildBlocker.MainnetDisabled)
        assertDisabled(evidence.capability)
        assertDisabled(evidence)
    }

    @Test
    fun requestResultAndPolicyTokenStayRedacted() {
        val request = SkaldVaultV1ProviderDependencyBuildRequest.forCandidate(
            SkaldVaultV1ProviderDependencyCandidateFamily.UnsupportedCandidate,
        )
        val result = SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(request)
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
            "ciphertext-material",
            "plaintext-material",
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
    fun individualCandidateAuditReturnsOnlyBlockedRows() {
        SkaldVaultV1ProviderDependencyCandidateFamily.entries.forEach { family ->
            val evidence = blocked(
                SkaldVaultV1ProviderDependencyBuildPolicy.evaluate(
                    SkaldVaultV1ProviderDependencyBuildRequest.forCandidate(family),
                ),
            )

            assertEquals(listOf(family), evidence.candidateRows.map { it.family })
            assertTrue(evidence.candidateRows.single().buildEvidenceOnly)
            assertFalse(evidence.candidateRows.single().executable)
            assertFalse(evidence.candidateRows.single().selectable)
            assertFalse(evidence.candidateRows.single().productionAuthorized)
            assertDisabled(evidence.capability)
            assertDisabled(evidence)
        }
    }

    private fun blocked(
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

    private fun assertDisabled(capability: SkaldVaultV1ProviderDependencyBuildCapability) {
        assertTrue(capability.preExistingProviderDependencyDeclarationsObserved)
        assertFalse(capability.newDependencyDeclaredThisBranch)
        assertFalse(capability.singleCandidateDependencyAddedThisBranch)
        assertTrue(capability.providerDependencyDeclaredForBuildOnly)
        assertFalse(capability.providerDependencyDeclaredForRuntime)
        assertFalse(capability.providerDependencyActivated)
        assertFalse(capability.providerDependencyImportedInProduction)
        assertFalse(capability.providerDependencyImportedInTests)
        assertFalse(capability.providerImplementationAdded)
        assertFalse(capability.providerFactoryAdded)
        assertFalse(capability.providerRuntimeInstantiable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelectable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerKatExecutionAvailable)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.runtimeRandomnessAvailable)
        assertFalse(capability.kdfExecutionAvailable)
        assertFalse(capability.aeadExecutionAvailable)
        assertFalse(capability.hkdfHmacExecutionAvailable)
        assertFalse(capability.keyWrappingAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertDisabled(evidence: SkaldVaultV1ProviderDependencyBuildEvidence) {
        assertTrue(evidence.providerDependencyDeclaredForBuildOnly)
        assertFalse(evidence.providerDependencyDeclaredForRuntime)
        assertFalse(evidence.providerDependencyActivated)
        assertFalse(evidence.providerDependencyImportedInProduction)
        assertFalse(evidence.providerDependencyImportedInTests)
        assertFalse(evidence.providerImplementationAdded)
        assertFalse(evidence.providerFactoryAdded)
        assertFalse(evidence.providerRuntimeInstantiable)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.providerKatExecutionAvailable)
        assertFalse(evidence.providerCryptoAvailable)
        assertFalse(evidence.runtimeRandomnessAvailable)
        assertFalse(evidence.kdfExecutionAvailable)
        assertFalse(evidence.aeadExecutionAvailable)
        assertFalse(evidence.hkdfHmacExecutionAvailable)
        assertFalse(evidence.keyWrappingAvailable)
        assertFalse(evidence.vaultCreationAvailable)
        assertFalse(evidence.vaultUnlockAvailable)
        assertFalse(evidence.vaultPersistenceAvailable)
        assertFalse(evidence.mainnetAvailable)
    }
}
