package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DesktopTestOnlyProviderSelectionBlocker
import com.libertasprimordium.skald.security.DesktopTestOnlyProviderSelectionResult
import com.libertasprimordium.skald.security.DesktopTestOnlyVaultCryptoProviderSelector
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderKind
import com.libertasprimordium.skald.security.VaultCryptoAssociatedDataContext
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderKatEvidence
import com.libertasprimordium.skald.security.VaultCryptoProviderKatExecutionScope
import com.libertasprimordium.skald.security.VaultCryptoProviderKatRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderKatVectorId
import com.libertasprimordium.skald.security.VaultCryptoProviderResult
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoRecordPurpose
import com.libertasprimordium.skald.security.commonProviderKatContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlySelectedProviderPublicKatExecutionTest {
    @Test
    fun desktopTestOnlyProviderSelectionRequiresExplicitValidationScope() {
        val result = DesktopTestOnlyVaultCryptoProviderSelector.selectForValidation(scope = null)
        val blocked = assertIs<DesktopTestOnlyProviderSelectionResult.Blocked>(result)

        assertContains(blocked.blockers, DesktopTestOnlyProviderSelectionBlocker.ExplicitValidationScopeRequired)
        assertTrue(blocked.selectionReport.validationScopeRequired)
        assertFalse(blocked.selectionReport.validationScopePresent)
        assertFalse(blocked.selectionReport.testOnlyProviderSelectedForPublicKat)
        assertFalse(blocked.selectionReport.desktopTestOnlyProviderSelected)
        assertFalse(blocked.selectionReport.productionProviderSelectable)
        assertFalse(blocked.selectionReport.productionProviderSelectionEnabled)
    }

    @Test
    fun desktopTestOnlySelectorSelectsTestOnlyProviderForValidationOnly() {
        val result = select()

        assertTrue(result.selectionReport.validationScopeRequired)
        assertTrue(result.selectionReport.validationScopePresent)
        assertTrue(result.selectionReport.testOnlyProviderSelectionForValidationEnabled)
        assertTrue(result.selectionReport.testOnlyProviderSelectedForPublicKat)
        assertTrue(result.selectionReport.desktopTestOnlyProviderSelected)
        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderKind.TestOnlyExecutablePublicKatProvider,
            result.selectedProviderHarness.selectedProviderKind,
        )
        assertFalse(result.selectionReport.productionProviderSelectable)
        assertFalse(result.selectionReport.productionProviderSelectionEnabled)
        assertTrue(result.selectionReport.productionSelectionStillDisabledProviderOnly)
    }

    @Test
    fun desktopSelectedProviderKdfPublicKatPassesThroughTestOnlyProvider() {
        val result = select()
        val kat = result.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53),
        )

        kat.assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
    }

    @Test
    fun desktopSelectedProviderAeadPublicKatPassesThroughTestOnlyProvider() {
        val result = select()
        val kat = result.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        )

        kat.assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
    }

    @Test
    fun desktopSelectedProviderKatsDoNotChangeProductionSelection() {
        val result = select()
        val productionSelection = VaultCryptoProviderSelectionRegistry.select()

        result.selectedProviderHarness.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))
            .assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
        result.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        ).assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, productionSelection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, productionSelection.decision)
        assertTrue(productionSelection.selectedProviderIsDisabled)
        assertTrue(productionSelection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(productionSelection.productionProviderSelectable)
        assertFalse(result.selectionReport.productionProviderImplementationPresent)
        assertFalse(result.selectionReport.productionRegistryEntryPresent)
        assertFalse(result.selectionReport.productionFactoryPresent)
        assertFalse(result.selectionReport.productionDispatcherPresent)
        assertFalse(result.selectionReport.productionExecutorTargetPresent)
    }

    @Test
    fun desktopSelectedProviderValidationPersistsNoChoiceAndAddsNoUiTraceOrHandles() {
        val result = select()

        assertFalse(result.selectionReport.providerChoicePersisted)
        assertFalse(result.selectionReport.providerSelectionUiPresent)
        assertFalse(result.selectionReport.tracePayloadPresent)
        assertFalse(result.selectionReport.providerHandlesExposed)
    }

    @Test
    fun desktopSelectedProviderOutputIsRedactedAndMaterialFree() {
        val result = select()
        val kdf = result.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53),
        )
        val aead = result.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        )
        val output = listOf(
            result.toString(),
            result.selectionReport.toString(),
            result.selectedProviderHarness.toString(),
            kdf.toString(),
            aead.toString(),
            DesktopTestOnlyVaultCryptoProviderSelector.publicKatValidationScope().toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "0d640df5",
            "bd6d179d",
            "80818283",
            "40414243",
            "4c616469",
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
            "wpkh(",
            "tr(",
            "xpub",
            "xprv",
            "tprv",
            "psbt",
            "nsec",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "TEST_SOURCE_ONLY")
        assertContains(output, "PROVIDER_SELECTION_VALIDATION_ONLY")
        assertContains(output, "NO_PRODUCTION_SELECTION")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    private fun select(): DesktopTestOnlyProviderSelectionResult.Selected {
        val scope = DesktopTestOnlyVaultCryptoProviderSelector.publicKatValidationScope()
        return assertIs<DesktopTestOnlyProviderSelectionResult.Selected>(
            DesktopTestOnlyVaultCryptoProviderSelector.selectForValidation(scope),
        )
    }

    private fun katRequest(vectorId: VaultCryptoProviderKatVectorId): VaultCryptoProviderKatRequest =
        VaultCryptoProviderKatRequest(
            requirement = commonProviderKatContract().requirements.single { it.vectorId == vectorId },
            context = context,
        )

    private fun VaultCryptoProviderResult<VaultCryptoProviderKatEvidence>.assertKatSuccess(
        vectorId: VaultCryptoProviderKatVectorId,
    ) {
        val success = assertIs<VaultCryptoProviderResult.Success<VaultCryptoProviderKatEvidence>>(this)
        assertEquals(vectorId, success.value.vectorId)
        assertEquals(VaultCryptoProviderKatExecutionScope.TestHarnessOnly, success.value.executionScope)
    }

    private companion object {
        val context = VaultCryptoAssociatedDataContext(
            containerVersion = 1,
            recordPurpose = VaultCryptoRecordPurpose.ProviderKatTestRecord,
            recordSchemaVersion = 1,
            keyVersion = 1,
        )
    }
}
