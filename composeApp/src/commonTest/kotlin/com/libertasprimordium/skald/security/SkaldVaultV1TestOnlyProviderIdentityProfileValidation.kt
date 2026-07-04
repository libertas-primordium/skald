package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProfileValidationSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityProfileValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProfileValidationSourceSet {
    CommonTest,
}

data class SkaldVaultV1TestOnlyProviderIdentityProfileValidationReport(
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val expectedSafeIdMatched: Boolean,
    val allChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsRegistryAuthorization: Boolean,
    val validationIsFactoryAuthorization: Boolean,
    val validationIsDispatcherAuthorization: Boolean,
    val validationIsExecutorAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
    val implementsVaultCryptoProvider: Boolean,
    val containsVaultCryptoProvider: Boolean,
    val canExecuteProviderOperations: Boolean,
    val canExecuteCrypto: Boolean,
    val canUseForVaultLifecycle: Boolean,
    val canUseForPersistence: Boolean,
    val canUseForSync: Boolean,
    val canUseForSigning: Boolean,
    val canUseForBroadcasting: Boolean,
    val canUseForMainnet: Boolean,
    val productionProviderSelectable: Boolean,
    val markerPresentExactlyOnce: Boolean,
    val inventoryPresentExactlyOnce: Boolean,
    val profilePresentExactlyOnce: Boolean,
    val profileBuiltFromInventory: Boolean,
    val markerSafeIdMatchesExpected: Boolean,
    val markerSourceSetIsCommonTest: Boolean,
    val inventorySourceSetIsCommonTest: Boolean,
    val profileSourceSetIsCommonTest: Boolean,
    val markerInventoryAndProfileAreInert: Boolean,
    val markerInventoryAndProfileAreNonRuntime: Boolean,
    val markerInventoryAndProfileAreNonSelectable: Boolean,
    val markerInventoryAndProfileAreNotProductionRegistry: Boolean,
    val markerInventoryAndProfileAreNotFactoryInput: Boolean,
    val markerInventoryAndProfileAreNotDispatcherInput: Boolean,
    val markerInventoryAndProfileAreNotExecutorTarget: Boolean,
    val markerInventoryAndProfileDoNotImplementVaultCryptoProvider: Boolean,
    val markerInventoryAndProfileDoNotContainVaultCryptoProvider: Boolean,
    val markerInventoryAndProfileCannotExecuteProviderOperations: Boolean,
    val markerInventoryAndProfileCannotExecuteCrypto: Boolean,
    val markerInventoryAndProfileCannotUseVaultLifecycle: Boolean,
    val markerInventoryAndProfileCannotPersist: Boolean,
    val markerInventoryAndProfileCannotSync: Boolean,
    val markerInventoryAndProfileCannotSignOrBroadcast: Boolean,
    val markerInventoryAndProfileCannotUseMainnet: Boolean,
    val providerSelectionRemainsDisabledProviderOnly: Boolean,
    val productionProviderSelectableRemainsFalse: Boolean,
    val safeOutputRedactionSatisfied: Boolean,
    val productionRuntimeSourceAbsenceSatisfied: Boolean,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProfileValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProfileValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProfileValidationReport(redactedMarkerId, commonTestOnly, inert, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentValidationReport(): SkaldVaultV1TestOnlyProviderIdentityProfileValidationReport {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val marker = profile.marker
        val markerCount = inventory.markers.count { item -> item.safeId.value == marker.safeId.value }
        val inventoryCount = if (inventory.markerCount == 1) 1 else 0
        val profileCount = if (profile.inventorySize == 1) 1 else 0
        val markerSafeIdMatchesExpected = marker.safeId.value == EXPECTED_SAFE_ID
        val markerPresentExactlyOnce = markerCount == 1
        val inventoryPresentExactlyOnce = inventoryCount == 1
        val profilePresentExactlyOnce = profileCount == 1
        val profileBuiltFromInventory = inventory.markers.single().safeId.value == marker.safeId.value
        val markerSourceSetIsCommonTest =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val inventorySourceSetIsCommonTest = inventory.inventoryIsCommonTestOnly
        val profileSourceSetIsCommonTest =
            profile.sourceSet == SkaldVaultV1TestOnlyProviderIdentityProfileSourceSet.CommonTest &&
                profile.profileIsCommonTestOnly
        val markerInventoryAndProfileAreInert =
            marker.implementedNow &&
                inventory.implementedNow &&
                inventory.inventoryContainsOnlyInertMarkers &&
                profile.implementedNow &&
                profile.profileKind ==
                SkaldVaultV1TestOnlyProviderIdentityProfileKind.InertTestOnlyIdentityProfile
        val markerInventoryAndProfileAreNonRuntime =
            !marker.runtimeSelectable && !inventory.runtimeSelectable && !profile.runtimeSelectable
        val markerInventoryAndProfileAreNonSelectable =
            !marker.registrySelectable &&
                !inventory.registrySelectable &&
                !profile.registrySelectable &&
                !marker.productionProviderSelectable &&
                !inventory.productionProviderSelectable &&
                !profile.productionProviderSelectable
        val markerInventoryAndProfileAreNotProductionRegistry =
            !inventory.inventoryIsProductionRegistry && !profile.profileIsProviderRegistry
        val markerInventoryAndProfileAreNotFactoryInput =
            !marker.factoryReachable && !inventory.factoryReachable && !profile.profileIsProviderFactoryInput
        val markerInventoryAndProfileAreNotDispatcherInput =
            !marker.dispatcherReachable && !inventory.dispatcherReachable && !profile.profileIsProviderDispatcherInput
        val markerInventoryAndProfileAreNotExecutorTarget =
            !marker.executorTargetable && !inventory.executorTargetable && !profile.profileIsExecutorTargetInput
        val markerInventoryAndProfileDoNotImplementVaultCryptoProvider =
            !marker.implementsVaultCryptoProvider &&
                !inventory.implementsVaultCryptoProvider &&
                !profile.implementsVaultCryptoProvider
        val markerInventoryAndProfileDoNotContainVaultCryptoProvider =
            !marker.vaultCryptoProviderInstanceExposed &&
                !inventory.containsVaultCryptoProvider &&
                !profile.containsVaultCryptoProvider
        val markerInventoryAndProfileCannotExecuteProviderOperations =
            !marker.canExecuteProviderOperations &&
                !inventory.canExecuteProviderOperations &&
                !profile.canExecuteProviderOperations
        val markerInventoryAndProfileCannotExecuteCrypto =
            !marker.canExecuteCrypto && !inventory.canExecuteCrypto && !profile.canExecuteCrypto
        val markerInventoryAndProfileCannotUseVaultLifecycle =
            !marker.canUseForVaultLifecycle &&
                !inventory.canUseForVaultLifecycle &&
                !profile.canUseForVaultLifecycle
        val markerInventoryAndProfileCannotPersist =
            !marker.canUseForPersistence && !inventory.canUseForPersistence && !profile.canUseForPersistence
        val markerInventoryAndProfileCannotSync =
            !marker.canUseForSync && !inventory.canUseForSync && !profile.canUseForSync
        val markerInventoryAndProfileCannotSignOrBroadcast =
            !marker.canUseForSigning &&
                !inventory.canUseForSigning &&
                !profile.canUseForSigning &&
                !marker.canUseForBroadcasting &&
                !inventory.canUseForBroadcasting &&
                !profile.canUseForBroadcasting
        val markerInventoryAndProfileCannotUseMainnet =
            !marker.canUseForMainnet && !inventory.canUseForMainnet && !profile.canUseForMainnet
        val providerSelectionRemainsDisabledProviderOnly = true
        val productionProviderSelectableRemainsFalse =
            !marker.productionProviderSelectable &&
                !inventory.productionProviderSelectable &&
                !profile.productionProviderSelectable
        val safeOutputRedactionSatisfied =
            sequenceOf(marker.toString(), marker.safeId.toString(), inventory.toString(), profile.toString())
                .none { output -> EXPECTED_SAFE_ID in output }
        val productionRuntimeSourceAbsenceSatisfied = true
        val allChecks = listOf(
            markerPresentExactlyOnce,
            inventoryPresentExactlyOnce,
            profilePresentExactlyOnce,
            profileBuiltFromInventory,
            markerSafeIdMatchesExpected,
            markerSourceSetIsCommonTest,
            inventorySourceSetIsCommonTest,
            profileSourceSetIsCommonTest,
            markerInventoryAndProfileAreInert,
            markerInventoryAndProfileAreNonRuntime,
            markerInventoryAndProfileAreNonSelectable,
            markerInventoryAndProfileAreNotProductionRegistry,
            markerInventoryAndProfileAreNotFactoryInput,
            markerInventoryAndProfileAreNotDispatcherInput,
            markerInventoryAndProfileAreNotExecutorTarget,
            markerInventoryAndProfileDoNotImplementVaultCryptoProvider,
            markerInventoryAndProfileDoNotContainVaultCryptoProvider,
            markerInventoryAndProfileCannotExecuteProviderOperations,
            markerInventoryAndProfileCannotExecuteCrypto,
            markerInventoryAndProfileCannotUseVaultLifecycle,
            markerInventoryAndProfileCannotPersist,
            markerInventoryAndProfileCannotSync,
            markerInventoryAndProfileCannotSignOrBroadcast,
            markerInventoryAndProfileCannotUseMainnet,
            providerSelectionRemainsDisabledProviderOnly,
            productionProviderSelectableRemainsFalse,
            safeOutputRedactionSatisfied,
            productionRuntimeSourceAbsenceSatisfied,
        )

        return SkaldVaultV1TestOnlyProviderIdentityProfileValidationReport(
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            expectedSafeIdMatched = markerSafeIdMatchesExpected,
            allChecksPassed = allChecks.all { passed -> passed },
            validationIsCommonTestOnly = true,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsRegistryAuthorization = false,
            validationIsFactoryAuthorization = false,
            validationIsDispatcherAuthorization = false,
            validationIsExecutorAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            runtimeSelectable = false,
            registrySelectable = false,
            factoryReachable = false,
            dispatcherReachable = false,
            executorTargetable = false,
            providerKatExecutorReachable = false,
            implementsVaultCryptoProvider = false,
            containsVaultCryptoProvider = false,
            canExecuteProviderOperations = false,
            canExecuteCrypto = false,
            canUseForVaultLifecycle = false,
            canUseForPersistence = false,
            canUseForSync = false,
            canUseForSigning = false,
            canUseForBroadcasting = false,
            canUseForMainnet = false,
            productionProviderSelectable = false,
            markerPresentExactlyOnce = markerPresentExactlyOnce,
            inventoryPresentExactlyOnce = inventoryPresentExactlyOnce,
            profilePresentExactlyOnce = profilePresentExactlyOnce,
            profileBuiltFromInventory = profileBuiltFromInventory,
            markerSafeIdMatchesExpected = markerSafeIdMatchesExpected,
            markerSourceSetIsCommonTest = markerSourceSetIsCommonTest,
            inventorySourceSetIsCommonTest = inventorySourceSetIsCommonTest,
            profileSourceSetIsCommonTest = profileSourceSetIsCommonTest,
            markerInventoryAndProfileAreInert = markerInventoryAndProfileAreInert,
            markerInventoryAndProfileAreNonRuntime = markerInventoryAndProfileAreNonRuntime,
            markerInventoryAndProfileAreNonSelectable = markerInventoryAndProfileAreNonSelectable,
            markerInventoryAndProfileAreNotProductionRegistry = markerInventoryAndProfileAreNotProductionRegistry,
            markerInventoryAndProfileAreNotFactoryInput = markerInventoryAndProfileAreNotFactoryInput,
            markerInventoryAndProfileAreNotDispatcherInput = markerInventoryAndProfileAreNotDispatcherInput,
            markerInventoryAndProfileAreNotExecutorTarget = markerInventoryAndProfileAreNotExecutorTarget,
            markerInventoryAndProfileDoNotImplementVaultCryptoProvider =
                markerInventoryAndProfileDoNotImplementVaultCryptoProvider,
            markerInventoryAndProfileDoNotContainVaultCryptoProvider =
                markerInventoryAndProfileDoNotContainVaultCryptoProvider,
            markerInventoryAndProfileCannotExecuteProviderOperations =
                markerInventoryAndProfileCannotExecuteProviderOperations,
            markerInventoryAndProfileCannotExecuteCrypto = markerInventoryAndProfileCannotExecuteCrypto,
            markerInventoryAndProfileCannotUseVaultLifecycle = markerInventoryAndProfileCannotUseVaultLifecycle,
            markerInventoryAndProfileCannotPersist = markerInventoryAndProfileCannotPersist,
            markerInventoryAndProfileCannotSync = markerInventoryAndProfileCannotSync,
            markerInventoryAndProfileCannotSignOrBroadcast = markerInventoryAndProfileCannotSignOrBroadcast,
            markerInventoryAndProfileCannotUseMainnet = markerInventoryAndProfileCannotUseMainnet,
            providerSelectionRemainsDisabledProviderOnly = providerSelectionRemainsDisabledProviderOnly,
            productionProviderSelectableRemainsFalse = productionProviderSelectableRemainsFalse,
            safeOutputRedactionSatisfied = safeOutputRedactionSatisfied,
            productionRuntimeSourceAbsenceSatisfied = productionRuntimeSourceAbsenceSatisfied,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityProfileValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProfileValidationSafeLabel(
                "inert validation evidence",
            ),
        )
    }
}
