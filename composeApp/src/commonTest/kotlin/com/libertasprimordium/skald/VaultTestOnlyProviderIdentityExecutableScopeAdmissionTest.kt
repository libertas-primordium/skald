package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
import com.libertasprimordium.skald.security.commonDisabledVaultCryptoProviderStatus
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityExecutableScopeAdmissionTest {
    private fun admission() =
        SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy
            .currentProviderIdentityExecutableScopeAdmission()

    @Test
    fun executableScopeAdmissionIsPresentAndCommonTestOnly() {
        val admission = admission()

        assertEquals(1, admission.admissionVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionKind
                .TestOnlyProviderIdentityExecutableScopeAdmission,
            admission.admissionKind,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSourceSet.CommonTest,
            admission.sourceSet,
        )
        assertTrue(admission.executableScopeAdmissionPassed)
        assertTrue(admission.executableScopeAdmissionPassedIsCommonTestOnlyEvidence)
        assertEquals(0, admission.failureLabels.size)
        assertEquals(0, admission.blockerCount)
        assertEquals(0, admission.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionCheck.entries.size,
            admission.admissionCheckCount,
        )
    }

    @Test
    fun executableScopeAdmissionReadsTransitionGateAndDescriptorCompletionAuditEvidence() {
        val admission = admission()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val descriptorCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
                .currentProviderIdentityDescriptorCompletionAudit()

        assertTrue(admission.transitionGatePresent)
        assertTrue(admission.transitionGateHumanReviewReady)
        assertEquals(transitionGate.reviewReadyForHumanDecision, admission.transitionGateHumanReviewReady)
        assertTrue(admission.descriptorCompletionAuditPresent)
        assertTrue(admission.descriptorCompletionAuditPassed)
        assertTrue(admission.descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly)
        assertEquals(
            descriptorCompletionAudit.descriptorCompletionAuditPassed,
            admission.descriptorCompletionAuditPassed,
        )
        assertTrue(admission.descriptorChainComplete)
        assertEquals(descriptorCompletionAudit.descriptorChainComplete, admission.descriptorChainComplete)
    }

    @Test
    fun executableScopeAdmissionSeesPriorDependencyKatAndArgon2idProbeEvidenceOnly() {
        val admission = admission()
        val dependencyEvidence =
            VaultCryptoDependencyProbeCatalog.currentSpikeResults()
                .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val argon2idPolicy = commonArgon2idCalibrationPolicy()

        assertTrue(admission.dependencyKatDesktopPassedEvidencePresent)
        assertTrue(VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass in dependencyEvidence.capabilities)
        assertTrue(admission.dependencyKatDesktopPassedEvidenceIsPriorEvidenceOnly)
        assertTrue(admission.dependencyKatAndroidPassedEvidencePresent)
        assertTrue(VaultCryptoDependencyCapability.AndroidKnownAnswerVectorsPass in dependencyEvidence.capabilities)
        assertTrue(admission.dependencyKatAndroidPassedEvidenceIsPriorEvidenceOnly)
        assertTrue(admission.argon2idCalibrationProbeEvidencePresent)
        assertFalse(argon2idPolicy.productionKdfEnabled)
        assertTrue(admission.argon2idCalibrationProbeEvidenceIsPriorProbeEvidenceOnly)
    }

    @Test
    fun executableScopeAdmissionKeepsDisabledVaultCryptoProviderBoundaryOnly() {
        val admission = admission()
        val disabledStatus = commonDisabledVaultCryptoProviderStatus()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(admission.disabledVaultCryptoProviderBoundaryPresent)
        assertEquals(VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly, disabledStatus.implementationStatus)
        assertFalse(disabledStatus.implementationStatus.canExecuteCrypto)
        assertFalse(disabledStatus.canDeriveKeys)
        assertFalse(disabledStatus.canEncryptRecords)
        assertFalse(disabledStatus.canDecryptRecords)
        assertFalse(admission.providerSelectionEnabled)
        assertFalse(admission.productionProviderSelectable)
        assertTrue(admission.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun executableScopeAdmissionRecordsOnlyFutureTestSourcePublicKatScope() {
        val admission = admission()

        assertTrue(admission.testOnlyExecutableProviderImplementationAdmitted)
        assertTrue(admission.publicKatProviderImplementationScopeAdmitted)
        assertTrue(admission.plannedExecutableProviderCanCoverKdf)
        assertTrue(admission.plannedExecutableProviderCanCoverAead)
        assertTrue(admission.plannedExecutableProviderScopeIsPublicKatOnly)
        assertTrue(admission.plannedProviderImplementationMustRemainTestSourceOnly)
        assertTrue(admission.futureProviderLevelKatExecutionRequiresSeparatePass)
        assertTrue(admission.futureProviderSelectionEnablementRequiresSeparatePass)
        assertTrue(admission.testOnlyExecutableProviderImplementationAdmittedIsFutureScopeOnly)
        assertTrue(admission.publicKatProviderImplementationScopeAdmittedIsFutureScopeOnly)
        assertTrue(admission.plannedExecutableProviderCanCoverKdfIsFutureScopeOnly)
        assertTrue(admission.plannedExecutableProviderCanCoverAeadIsFutureScopeOnly)
        assertTrue(admission.plannedExecutableProviderCanCoverKdfIsNotKdfExecution)
        assertTrue(admission.plannedExecutableProviderCanCoverAeadIsNotAeadExecution)
        assertTrue(admission.futureProviderLevelKatExecutionRequiresSeparatePassBlocksThisBranchKatExecution)
        assertTrue(admission.futureProviderSelectionEnablementRequiresSeparatePassBlocksThisBranchSelection)
    }

    @Test
    fun executableScopeAdmissionAddsNoProviderImplementationExecutionOrKatExecutor() {
        val admission = admission()

        assertFalse(admission.implementationInThisPass)
        assertFalse(admission.providerImplementationPresent)
        assertFalse(admission.executableProviderImplementationPresent)
        assertFalse(admission.executableProviderImplementedInProductionSource)
        assertFalse(admission.providerOperationExecuted)
        assertFalse(admission.cryptoExecuted)
        assertFalse(admission.kdfExecuted)
        assertFalse(admission.aeadExecuted)
        assertFalse(admission.katRunnerPresent)
        assertFalse(admission.katExecutorPresent)
        assertFalse(admission.providerLevelKatExecuted)
        assertFalse(admission.providerRegistryEntryPresent)
        assertFalse(admission.providerFactoryPresent)
        assertFalse(admission.providerDispatcherPresent)
        assertFalse(admission.executorTargetPresent)
    }

    @Test
    fun executableScopeAdmissionAddsNoVaultStorageSyncSigningUiEndpointMainnetOrTracePath() {
        val admission = admission()

        assertFalse(admission.vaultLifecyclePresent)
        assertFalse(admission.vaultPersistencePresent)
        assertFalse(admission.secureSecretStorageSuccessPresent)
        assertFalse(admission.secureMetadataStorageSuccessPresent)
        assertFalse(admission.productionSyncPresent)
        assertFalse(admission.signingBroadcastingPresent)
        assertFalse(admission.uiPresent)
        assertFalse(admission.endpointPresent)
        assertFalse(admission.mainnetPresent)
        assertFalse(admission.tracePayloadPresent)
        assertFalse(admission.rawKatMaterialPresent)
        assertFalse(admission.publicVectorBytesPresent)
        assertFalse(admission.publicVectorHexPresent)
    }

    @Test
    fun executableScopeAdmissionIsNotProductionExecutionSelectionKatStorageOrMainnetAuthorization() {
        val admission = admission()

        assertTrue(admission.executableScopeAdmissionPassed)
        assertTrue(admission.testOnlyExecutableProviderImplementationAdmitted)
        assertTrue(admission.publicKatProviderImplementationScopeAdmitted)
        assertTrue(admission.plannedExecutableProviderCanCoverKdf)
        assertTrue(admission.plannedExecutableProviderCanCoverAead)
        assertFalse(admission.implementationAuthorizationPresent)
        assertFalse(admission.productionAuthorizationPresent)
        assertFalse(admission.providerSelectionAuthorizationPresent)
        assertFalse(admission.productionProviderImplementationAuthorized)
        assertFalse(admission.productionProviderOperationExecutionAuthorized)
        assertFalse(admission.productionProviderOperationAuthorizationPresent)
        assertFalse(admission.productionCryptoExecutionAuthorized)
        assertFalse(admission.productionCryptoAuthorizationPresent)
        assertFalse(admission.productionVaultPersistenceAuthorized)
        assertFalse(admission.productionSyncAuthorized)
        assertFalse(admission.signingBroadcastingAuthorized)
        assertFalse(admission.katRunnerAuthorizationPresent)
        assertFalse(admission.katExecutorAuthorizationPresent)
        assertFalse(admission.vaultPersistenceAuthorizationPresent)
        assertFalse(admission.syncAuthorizationPresent)
        assertFalse(admission.signingBroadcastingAuthorizationPresent)
        assertFalse(admission.uiAuthorizationPresent)
        assertFalse(admission.endpointAuthorizationPresent)
        assertFalse(admission.mainnetAuthorizationPresent)
    }

    @Test
    fun executableScopeAdmissionOutputIsRedactedAndMaterialFree() {
        val admission = admission()
        val descriptorCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
                .currentProviderIdentityDescriptorCompletionAudit()
        val output = listOf(
            admission.toString(),
            admission.admissionId.toString(),
            admission.displayLabel.toString(),
            descriptorCompletionAudit.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "ByteArray",
            "UByteArray",
            "CharArray",
            "passphrase",
            "mnemonic",
            "seed phrase",
            "private key",
            "nsec",
            "ciphertext",
            "plaintext",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "public vector bytes",
            "public vector hex",
            "wpkh(",
            "tr(",
            "xpub",
            "xprv",
            "tprv",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_TEST_ONLY")
        assertContains(output, "EXECUTABLE_SCOPE_ADMISSION_ONLY")
        assertContains(output, "PUBLIC_KAT_SCOPE_ONLY")
        assertContains(output, "FUTURE_TEST_SOURCE_ONLY")
        assertContains(output, "NO_PROVIDER_IMPLEMENTATION")
        assertContains(output, "NO_KDF_AEAD_EXECUTION")
        assertContains(output, "NO_PROVIDER_SELECTION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    @Test
    fun executableScopeAdmissionKeepsNormalSourceMaterialCorpusBoundaries() {
        val admission = admission()

        assertTrue(admission.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(admission.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
        assertTrue(admission.docsReadmeUseDedicatedCorpus)
    }
}
