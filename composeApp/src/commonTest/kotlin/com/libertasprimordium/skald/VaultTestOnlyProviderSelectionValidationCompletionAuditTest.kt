package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderSelectionValidationCompletionAuditTest {
    private fun audit() =
        SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditPolicy
            .currentProviderSelectionValidationCompletionAudit()

    @Test
    fun providerSelectionValidationCompletionAuditExistsAsCommonTestEvidence() {
        val audit = audit()

        assertEquals(1, audit.completionAuditVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditKind
                .TestOnlyProviderSelectionValidationCompletionAudit,
            audit.completionAuditKind,
        )
        assertEquals(
            "TEST_ONLY_PROVIDER_SELECTION_VALIDATION_COMPLETION_AUDIT",
            audit.completionAuditKind.label,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSourceSet.CommonTestOnly,
            audit.sourceSet,
        )
        assertTrue(audit.testOnlyProviderSelectionValidationCompletionAuditPassed)
        assertTrue(audit.testOnlyProviderSelectionValidationCompletionAuditPassedIsAuditEvidenceOnly)
        assertEquals(0, audit.failureLabels.size)
        assertEquals(0, audit.blockerCount)
        assertEquals(0, audit.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditCheck.entries.size,
            audit.completionCheckCount,
        )
        SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditCheck.entries.forEach { check ->
            assertContains(audit.completionChecks, check)
        }
    }

    @Test
    fun completionAuditReadsUpstreamEvidenceChain() {
        val audit = audit()

        assertTrue(audit.executableScopeAdmissionPresent)
        assertTrue(audit.executableScopeAdmissionPassed)
        assertTrue(audit.executableProviderImplementationPresent)
        assertTrue(audit.executableProviderImplementationTestSourceOnly)
        assertTrue(audit.providerLevelPublicKatEvidencePresent)
        assertTrue(audit.providerLevelPublicKatExecutionPassed)
        assertTrue(audit.providerSelectionValidationPresent)
        assertTrue(audit.providerSelectionValidationPassed)
        assertTrue(audit.testOnlyProviderSelectionValidationPassedIsValidationEvidenceOnly)
    }

    @Test
    fun completionAuditRecordsDesktopAndAndroidSelectedProviderKatResults() {
        val audit = audit()

        assertTrue(audit.desktopSelectedProviderKdfPublicKatPassed)
        assertTrue(audit.desktopSelectedProviderAeadPublicKatPassed)
        assertTrue(audit.androidSelectedProviderKdfPublicKatPassed)
        assertTrue(audit.androidSelectedProviderAeadPublicKatPassed)
        assertTrue(audit.explicitValidationScopeRequired)
        assertTrue(audit.testOnlyProviderSelectionForValidationEnabled)
        assertTrue(audit.testOnlyProviderSelectedForPublicKat)
        assertTrue(audit.testOnlyProviderSelectionForValidationEnabledIsNotProductionSelectionAuthorization)
        assertTrue(audit.testOnlyProviderSelectedForPublicKatIsNotProductionProviderSelection)
        assertTrue(audit.selectedProviderKdfPublicKatPassedIsNotProductionKdfAuthorization)
        assertTrue(audit.selectedProviderAeadPublicKatPassedIsNotProductionAeadAuthorization)
    }

    @Test
    fun completionAuditKeepsProductionProviderSelectionDisabledProviderOnly() {
        val audit = audit()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(audit.productionProviderSelectionEnabled)
        assertFalse(audit.productionProviderSelectable)
        assertTrue(audit.productionSelectionStillDisabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun completionAuditAddsNoProductionProviderSurfaceOrProviderChoiceSurface() {
        val audit = audit()

        assertFalse(audit.productionProviderImplementationPresent)
        assertFalse(audit.productionRegistryEntryPresent)
        assertFalse(audit.productionFactoryPresent)
        assertFalse(audit.productionDispatcherPresent)
        assertFalse(audit.productionExecutorTargetPresent)
        assertFalse(audit.providerChoicePersisted)
        assertFalse(audit.providerSelectionUiPresent)
        assertTrue(audit.selectedProviderKatSuccessIsNotProductionProviderImplementationAuthorization)
        assertTrue(audit.futureProductionProviderSelectionRequiresSeparatePass)
    }

    @Test
    fun completionAuditAddsNoTracePayloadHandlesStorageSyncSigningUiEndpointOrMainnet() {
        val audit = audit()

        assertFalse(audit.tracePayloadPresent)
        assertFalse(audit.providerHandlesExposed)
        assertFalse(audit.selectedProviderKatOutputLogged)
        assertFalse(audit.vaultPersistencePresent)
        assertFalse(audit.secureStorageSuccessPathPresent)
        assertFalse(audit.secureMetadataSuccessPathPresent)
        assertFalse(audit.productionSyncPresent)
        assertFalse(audit.signingBroadcastingPresent)
        assertFalse(audit.uiPresent)
        assertFalse(audit.endpointPresent)
        assertFalse(audit.mainnetPresent)
        assertTrue(audit.selectedProviderKatSuccessIsNotVaultPersistenceAuthorization)
        assertTrue(audit.futureVaultPersistenceRequiresSeparatePass)
    }

    @Test
    fun completionAuditKeepsSourceMaterialCorpusBoundaries() {
        val audit = audit()

        assertTrue(audit.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(audit.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun completionAuditOutputIsRedactedAndMaterialFree() {
        val audit = audit()
        val output = listOf(
            audit.toString(),
            audit.completionAuditId.toString(),
            audit.displayLabel.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "ByteArray",
            "UByteArray",
            "CharArray",
            "passphrase",
            "mnemonic",
            "seed phrase",
            "private key",
            "xprv",
            "tprv",
            "WIF",
            "nsec",
            "ciphertext",
            "plaintext",
            "nonce",
            "tag",
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
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_TEST_ONLY")
        assertContains(output, "PROVIDER_SELECTION_VALIDATION_COMPLETION_AUDIT_ONLY")
        assertContains(output, "SELECTED_PROVIDER_PUBLIC_KAT_COMPLETE")
        assertContains(output, "NO_PRODUCTION_SELECTION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
