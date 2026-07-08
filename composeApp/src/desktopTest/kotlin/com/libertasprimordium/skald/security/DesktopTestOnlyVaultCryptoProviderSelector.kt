package com.libertasprimordium.skald.security

enum class DesktopTestOnlyProviderSelectionValidationScopeKind(val label: String) {
    PublicKatValidation("PUBLIC_KAT_VALIDATION"),
}

data class DesktopTestOnlyProviderSelectionValidationScope(
    val kind: DesktopTestOnlyProviderSelectionValidationScopeKind,
    val validationId: SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel,
) {
    override fun toString(): String =
        "DesktopTestOnlyProviderSelectionValidationScope(REDACTED, PUBLIC_KAT_VALIDATION)"
}

enum class DesktopTestOnlyProviderSelectionBlocker {
    ExplicitValidationScopeRequired,
}

data class DesktopTestOnlyProviderSelectionReport(
    val validationScopeRequired: Boolean,
    val validationScopePresent: Boolean,
    val testOnlyProviderSelectionForValidationEnabled: Boolean,
    val testOnlyProviderSelectedForPublicKat: Boolean,
    val desktopTestOnlyProviderSelected: Boolean,
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
        "DesktopTestOnlyProviderSelectionReport(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_SELECTION_VALIDATION_ONLY, " +
            "NO_PRODUCTION_SELECTION, NO_PROVIDER_CHOICE_PERSISTENCE, NO_PROVIDER_SELECTION_UI" +
            ")"
}

sealed class DesktopTestOnlyProviderSelectionResult {
    data class Selected(
        val selectedProviderHarness: DesktopTestOnlySelectedProviderPublicKatHarness,
        val selectionReport: DesktopTestOnlyProviderSelectionReport,
    ) : DesktopTestOnlyProviderSelectionResult() {
        override fun toString(): String =
            "DesktopTestOnlyProviderSelectionResult.Selected(REDACTED, PUBLIC_KAT_VALIDATION_ONLY)"
    }

    data class Blocked(
        val blockers: Set<DesktopTestOnlyProviderSelectionBlocker>,
        val selectionReport: DesktopTestOnlyProviderSelectionReport,
    ) : DesktopTestOnlyProviderSelectionResult() {
        override fun toString(): String =
            "DesktopTestOnlyProviderSelectionResult.Blocked(REDACTED, EXPLICIT_VALIDATION_SCOPE_REQUIRED)"
    }
}

class DesktopTestOnlySelectedProviderPublicKatHarness internal constructor(
    private val provider: DesktopTestOnlyVaultCryptoProvider,
    val selectionReport: DesktopTestOnlyProviderSelectionReport,
) {
    val selectedProviderKind: SkaldVaultV1TestOnlyExecutableProviderKind =
        provider.identity.providerKind

    fun validateKat(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> =
        provider.validateKat(request)

    override fun toString(): String =
        "DesktopTestOnlySelectedProviderPublicKatHarness(" +
            "REDACTED, TEST_SOURCE_ONLY, SELECTED_PROVIDER_PUBLIC_KAT_ONLY, " +
            "NO_PRODUCTION_SELECTION, NO_PROVIDER_HANDLE" +
            ")"
}

object DesktopTestOnlyVaultCryptoProviderSelector {
    fun publicKatValidationScope(): DesktopTestOnlyProviderSelectionValidationScope =
        DesktopTestOnlyProviderSelectionValidationScope(
            kind = DesktopTestOnlyProviderSelectionValidationScopeKind.PublicKatValidation,
            validationId = SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel(
                "skald-test-only-provider-selection-validation-v1",
            ),
        )

    fun selectForValidation(
        scope: DesktopTestOnlyProviderSelectionValidationScope?,
    ): DesktopTestOnlyProviderSelectionResult {
        val evidence =
            SkaldVaultV1TestOnlyProviderSelectionValidationPolicy.currentProviderSelectionValidation()
        val scopePresent = scope?.kind == DesktopTestOnlyProviderSelectionValidationScopeKind.PublicKatValidation
        val report = DesktopTestOnlyProviderSelectionReport(
            validationScopeRequired = true,
            validationScopePresent = scopePresent,
            testOnlyProviderSelectionForValidationEnabled =
                evidence.testOnlyProviderSelectionForValidationEnabled,
            testOnlyProviderSelectedForPublicKat = scopePresent,
            desktopTestOnlyProviderSelected = scopePresent,
            productionProviderSelectable = evidence.productionProviderSelectable,
            productionProviderSelectionEnabled = evidence.productionProviderSelectionEnabled,
            productionSelectionStillDisabledProviderOnly =
                evidence.productionSelectionStillDisabledProviderOnly,
            productionProviderImplementationPresent =
                evidence.productionProviderImplementationPresent,
            productionRegistryEntryPresent = evidence.productionRegistryEntryPresent,
            productionFactoryPresent = evidence.productionFactoryPresent,
            productionDispatcherPresent = evidence.productionDispatcherPresent,
            productionExecutorTargetPresent = evidence.productionExecutorTargetPresent,
            providerChoicePersisted = evidence.providerChoicePersisted,
            providerSelectionUiPresent = evidence.providerSelectionUiPresent,
            tracePayloadPresent = evidence.tracePayloadPresent,
            providerHandlesExposed = evidence.providerHandlesExposed,
        )

        if (!scopePresent) {
            return DesktopTestOnlyProviderSelectionResult.Blocked(
                blockers = setOf(DesktopTestOnlyProviderSelectionBlocker.ExplicitValidationScopeRequired),
                selectionReport = report,
            )
        }

        val provider = DesktopTestOnlyVaultCryptoProvider(
            executionMode = DesktopTestOnlyVaultCryptoProviderExecutionMode.ProviderLevelPublicKatExecution,
        )
        val harness = DesktopTestOnlySelectedProviderPublicKatHarness(
            provider = provider,
            selectionReport = report,
        )
        return DesktopTestOnlyProviderSelectionResult.Selected(
            selectedProviderHarness = harness,
            selectionReport = report,
        )
    }
}
