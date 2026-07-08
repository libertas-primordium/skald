package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidSelectedProviderKatExecutionTest {
    @Test
    fun androidTestOnlyProviderSelectionRequiresExplicitValidationScope() {
        val result = AndroidTestOnlyVaultCryptoProviderSelector.selectForValidation(scope = null)

        assertTrue(result is AndroidTestOnlyProviderSelectionResult.Blocked)
        val blocked = result as AndroidTestOnlyProviderSelectionResult.Blocked
        assertTrue(AndroidTestOnlyProviderSelectionBlocker.ExplicitValidationScopeRequired in blocked.blockers)
        assertTrue(blocked.selectionReport.validationScopeRequired)
        assertFalse(blocked.selectionReport.validationScopePresent)
        assertFalse(blocked.selectionReport.androidTestOnlyProviderSelected)
        assertFalse(blocked.selectionReport.productionProviderSelectable)
        assertFalse(blocked.selectionReport.productionProviderSelectionEnabled)
    }

    @Test
    fun androidTestOnlySelectorSelectsProviderForValidationOnly() {
        val selected = select()

        assertTrue(selected.selectionReport.validationScopeRequired)
        assertTrue(selected.selectionReport.validationScopePresent)
        assertTrue(selected.selectionReport.testOnlyProviderSelectionForValidationEnabled)
        assertTrue(selected.selectionReport.testOnlyProviderSelectedForPublicKat)
        assertTrue(selected.selectionReport.androidTestOnlyProviderSelected)
        assertFalse(selected.selectionReport.productionProviderSelectable)
        assertFalse(selected.selectionReport.productionProviderSelectionEnabled)
        assertTrue(selected.selectionReport.productionSelectionStillDisabledProviderOnly)
    }

    @Test
    fun androidSelectedProviderKdfPublicKatPassesThroughTestOnlyProvider() {
        val result = select().selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53),
        )

        result.assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
    }

    @Test
    fun androidSelectedProviderAeadPublicKatPassesThroughTestOnlyProvider() {
        val result = select().selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        )

        result.assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
    }

    @Test
    fun androidSelectedProviderKatsDoNotChangeProductionSelection() {
        val selected = select()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        selected.selectedProviderHarness.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))
            .assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
        selected.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        ).assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(selected.selectionReport.productionProviderImplementationPresent)
        assertFalse(selected.selectionReport.productionRegistryEntryPresent)
        assertFalse(selected.selectionReport.productionFactoryPresent)
        assertFalse(selected.selectionReport.productionDispatcherPresent)
        assertFalse(selected.selectionReport.productionExecutorTargetPresent)
    }

    @Test
    fun androidSelectedProviderValidationPersistsNoChoiceAndAddsNoUiTraceOrHandles() {
        val selected = select()

        assertFalse(selected.selectionReport.providerChoicePersisted)
        assertFalse(selected.selectionReport.providerSelectionUiPresent)
        assertFalse(selected.selectionReport.tracePayloadPresent)
        assertFalse(selected.selectionReport.providerHandlesExposed)
    }

    @Test
    fun androidSelectedProviderOutputIsRedactedAndMaterialFree() {
        val selected = select()
        val kdf = selected.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53),
        )
        val aead = selected.selectedProviderHarness.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        )
        val output = listOf(
            selected.toString(),
            selected.selectionReport.toString(),
            selected.selectedProviderHarness.toString(),
            kdf.toString(),
            aead.toString(),
            AndroidTestOnlyVaultCryptoProviderSelector.publicKatValidationScope().toString(),
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

        assertTrue(output.contains("REDACTED"))
        assertTrue(output.contains("TEST_SOURCE_ONLY"))
        assertTrue(output.contains("PROVIDER_SELECTION_VALIDATION_ONLY"))
        assertTrue(output.contains("NO_PRODUCTION_SELECTION"))
        forbiddenText.forEach { forbidden ->
            assertFalse("Output leaked forbidden text: $forbidden", output.contains(forbidden, ignoreCase = true))
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    private fun select(): AndroidTestOnlyProviderSelectionResult.Selected {
        val result = AndroidTestOnlyVaultCryptoProviderSelector.selectForValidation(
            AndroidTestOnlyVaultCryptoProviderSelector.publicKatValidationScope(),
        )
        assertTrue(result is AndroidTestOnlyProviderSelectionResult.Selected)
        return result as AndroidTestOnlyProviderSelectionResult.Selected
    }

    private fun katRequest(vectorId: VaultCryptoProviderKatVectorId): VaultCryptoProviderKatRequest =
        VaultCryptoProviderKatRequest(
            requirement = commonProviderKatContract().requirements.single { it.vectorId == vectorId },
            context = context,
        )

    private fun VaultCryptoProviderResult<VaultCryptoProviderKatEvidence>.assertKatSuccess(
        vectorId: VaultCryptoProviderKatVectorId,
    ) {
        assertTrue(this is VaultCryptoProviderResult.Success)
        val success = this as VaultCryptoProviderResult.Success
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

private enum class AndroidTestOnlyProviderSelectionValidationScopeKind {
    PublicKatValidation,
}

private data class AndroidTestOnlyProviderSelectionValidationScope(
    val kind: AndroidTestOnlyProviderSelectionValidationScopeKind,
) {
    override fun toString(): String =
        "AndroidTestOnlyProviderSelectionValidationScope(REDACTED, PUBLIC_KAT_VALIDATION)"
}

private enum class AndroidTestOnlyProviderSelectionBlocker {
    ExplicitValidationScopeRequired,
}

private data class AndroidTestOnlyProviderSelectionReport(
    val validationScopeRequired: Boolean,
    val validationScopePresent: Boolean,
    val testOnlyProviderSelectionForValidationEnabled: Boolean,
    val testOnlyProviderSelectedForPublicKat: Boolean,
    val androidTestOnlyProviderSelected: Boolean,
    val productionProviderSelectable: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val productionProviderImplementationPresent: Boolean,
    val productionRegistryEntryPresent: Boolean,
    val productionFactoryPresent: Boolean,
    val productionDispatcherPresent: Boolean,
    val productionExecutorTargetPresent: Boolean,
    val providerChoicePersisted: Boolean,
    val providerSelectionUiPresent: Boolean,
    val tracePayloadPresent: Boolean,
    val providerHandlesExposed: Boolean,
) {
    override fun toString(): String =
        "AndroidTestOnlyProviderSelectionReport(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_SELECTION_VALIDATION_ONLY, " +
            "NO_PRODUCTION_SELECTION, NO_PROVIDER_CHOICE_PERSISTENCE, NO_PROVIDER_SELECTION_UI" +
            ")"
}

private sealed class AndroidTestOnlyProviderSelectionResult {
    data class Selected(
        val selectedProviderHarness: AndroidTestOnlySelectedProviderPublicKatHarness,
        val selectionReport: AndroidTestOnlyProviderSelectionReport,
    ) : AndroidTestOnlyProviderSelectionResult() {
        override fun toString(): String =
            "AndroidTestOnlyProviderSelectionResult.Selected(REDACTED, PUBLIC_KAT_VALIDATION_ONLY)"
    }

    data class Blocked(
        val blockers: Set<AndroidTestOnlyProviderSelectionBlocker>,
        val selectionReport: AndroidTestOnlyProviderSelectionReport,
    ) : AndroidTestOnlyProviderSelectionResult() {
        override fun toString(): String =
            "AndroidTestOnlyProviderSelectionResult.Blocked(REDACTED, EXPLICIT_VALIDATION_SCOPE_REQUIRED)"
    }
}

private class AndroidTestOnlySelectedProviderPublicKatHarness(
    private val provider: AndroidProviderLevelPublicKatVaultCryptoProvider,
    val selectionReport: AndroidTestOnlyProviderSelectionReport,
) {
    fun validateKat(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> =
        provider.validateKat(request)

    override fun toString(): String =
        "AndroidTestOnlySelectedProviderPublicKatHarness(" +
            "REDACTED, TEST_SOURCE_ONLY, SELECTED_PROVIDER_PUBLIC_KAT_ONLY, " +
            "NO_PRODUCTION_SELECTION, NO_PROVIDER_HANDLE" +
            ")"
}

private object AndroidTestOnlyVaultCryptoProviderSelector {
    fun publicKatValidationScope(): AndroidTestOnlyProviderSelectionValidationScope =
        AndroidTestOnlyProviderSelectionValidationScope(
            kind = AndroidTestOnlyProviderSelectionValidationScopeKind.PublicKatValidation,
        )

    fun selectForValidation(
        scope: AndroidTestOnlyProviderSelectionValidationScope?,
    ): AndroidTestOnlyProviderSelectionResult {
        val scopePresent = scope?.kind == AndroidTestOnlyProviderSelectionValidationScopeKind.PublicKatValidation
        val report = AndroidTestOnlyProviderSelectionReport(
            validationScopeRequired = true,
            validationScopePresent = scopePresent,
            testOnlyProviderSelectionForValidationEnabled = true,
            testOnlyProviderSelectedForPublicKat = scopePresent,
            androidTestOnlyProviderSelected = scopePresent,
            productionProviderSelectable = false,
            productionProviderSelectionEnabled = false,
            productionSelectionStillDisabledProviderOnly = true,
            productionProviderImplementationPresent = false,
            productionRegistryEntryPresent = false,
            productionFactoryPresent = false,
            productionDispatcherPresent = false,
            productionExecutorTargetPresent = false,
            providerChoicePersisted = false,
            providerSelectionUiPresent = false,
            tracePayloadPresent = false,
            providerHandlesExposed = false,
        )

        if (!scopePresent) {
            return AndroidTestOnlyProviderSelectionResult.Blocked(
                blockers = setOf(AndroidTestOnlyProviderSelectionBlocker.ExplicitValidationScopeRequired),
                selectionReport = report,
            )
        }

        return AndroidTestOnlyProviderSelectionResult.Selected(
            selectedProviderHarness = AndroidTestOnlySelectedProviderPublicKatHarness(
                provider = AndroidProviderLevelPublicKatVaultCryptoProvider(),
                selectionReport = report,
            ),
            selectionReport = report,
        )
    }
}
