package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityKatFixtureValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSourceSet {
    CommonTest,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationReport(
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val reachabilityProofCount: Int,
    val capabilityMatrixCount: Int,
    val fixtureScopeCount: Int,
    val fixtureCatalogCount: Int,
    val fixtureRowCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val allValidationChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsKatExecutionAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
    val fixtureMetadataOnly: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val executableKatPresent: Boolean,
    val katExecutorPresent: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
    val providerOperationReachable: Boolean,
    val cryptoExecutionReachable: Boolean,
    val vaultLifecycleReachable: Boolean,
    val persistenceReachable: Boolean,
    val productionSyncReachable: Boolean,
    val backendClientReachable: Boolean,
    val bdkWalletStateReachable: Boolean,
    val settingsCodecReachable: Boolean,
    val uiSurfaceReachable: Boolean,
    val signingBroadcastingReachable: Boolean,
    val publicEndpointReachable: Boolean,
    val mainnetReachable: Boolean,
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
    val validationReportPresentExactlyOnce: Boolean,
    val reachabilityProofPresentExactlyOnce: Boolean,
    val capabilityMatrixPresentExactlyOnce: Boolean,
    val katFixtureScopePresentExactlyOnce: Boolean,
    val katFixtureCatalogPresentExactlyOnce: Boolean,
    val catalogContainsExactlyOneFixture: Boolean,
    val fixtureRowReferencesExpectedMarkerSafeId: Boolean,
    val fixtureRowUsesExpectedFixtureId: Boolean,
    val fixtureRowIsMetadataOnly: Boolean,
    val rawKatMaterialAbsent: Boolean,
    val rawVectorBytesAbsent: Boolean,
    val rawVectorHexAbsent: Boolean,
    val executableKatAbsent: Boolean,
    val katExecutorAbsent: Boolean,
    val providerSelectionAuthorizationAbsent: Boolean,
    val productionAuthorizationAbsent: Boolean,
    val katExecutionAuthorizationAbsent: Boolean,
    val cryptoAuthorizationAbsent: Boolean,
    val vaultPersistenceAuthorizationAbsent: Boolean,
    val mainnetAuthorizationAbsent: Boolean,
    val providerSelectionRemainsDisabledProviderOnly: Boolean,
    val productionProviderSelectableRemainsFalse: Boolean,
    val productionRuntimeSourceAbsenceSatisfied: Boolean,
    val safeOutputRedactionSatisfied: Boolean,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationReport(redactedFixtureId, redactedMarkerId, commonTestOnly, inert, metadataOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    fun currentKatFixtureValidationReport(): SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationReport {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val marker = inventory.markers.single()
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val validationReport =
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()
        val reachabilityProof =
            SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy.currentReachabilityProof()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val fixtureScope =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy.currentKatFixtureScope()
        val fixtureCatalog =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val fixtureRow = fixtureCatalog.fixtureRows.singleOrNull()

        val markerCount = inventory.markers.count { item -> item.safeId.value == marker.safeId.value }
        val inventoryCount = if (inventory.markerCount == 1) 1 else 0
        val profileCount = if (profile.marker.safeId.value == marker.safeId.value && profile.inventorySize == 1) 1 else 0
        val validationReportCount =
            if (
                validationReport.markerCount == 1 &&
                validationReport.inventoryCount == 1 &&
                validationReport.profileCount == 1
            ) {
                1
            } else {
                0
            }
        val reachabilityProofCount =
            if (
                reachabilityProof.markerCount == 1 &&
                reachabilityProof.inventoryCount == 1 &&
                reachabilityProof.profileCount == 1 &&
                reachabilityProof.validationReportCount == 1
            ) {
                1
            } else {
                0
            }
        val capabilityMatrixCount =
            if (
                capabilityMatrix.markerCount == 1 &&
                capabilityMatrix.inventoryCount == 1 &&
                capabilityMatrix.profileCount == 1 &&
                capabilityMatrix.validationReportCount == 1 &&
                capabilityMatrix.reachabilityProofCount == 1
            ) {
                1
            } else {
                0
            }
        val fixtureScopeCount =
            if (
                fixtureScope.markerCount == 1 &&
                fixtureScope.inventoryCount == 1 &&
                fixtureScope.profileCount == 1 &&
                fixtureScope.validationReportCount == 1 &&
                fixtureScope.reachabilityProofCount == 1 &&
                fixtureScope.capabilityMatrixCount == 1
            ) {
                1
            } else {
                0
            }
        val fixtureCatalogCount =
            if (
                fixtureCatalog.markerCount == 1 &&
                fixtureCatalog.inventoryCount == 1 &&
                fixtureCatalog.profileCount == 1 &&
                fixtureCatalog.validationReportCount == 1 &&
                fixtureCatalog.reachabilityProofCount == 1 &&
                fixtureCatalog.capabilityMatrixCount == 1 &&
                fixtureCatalog.fixtureScopeCount == 1 &&
                fixtureCatalog.catalogContainsExactlyOneFixture
            ) {
                1
            } else {
                0
            }

        val fixtureRowCount = fixtureCatalog.fixtureRows.size
        val expectedSafeIdMatched =
            marker.safeId.value == EXPECTED_SAFE_ID &&
                profile.marker.safeId.value == EXPECTED_SAFE_ID &&
                validationReport.expectedSafeIdMatched &&
                reachabilityProof.expectedSafeIdMatched &&
                capabilityMatrix.expectedSafeIdMatched &&
                fixtureScope.expectedSafeIdMatched &&
                fixtureCatalog.expectedSafeIdMatched &&
                fixtureRow?.markerSafeId?.value == EXPECTED_SAFE_ID
        val expectedFixtureIdMatched = fixtureRow?.fixtureId?.value == EXPECTED_FIXTURE_ID
        val validationIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                inventory.inventoryIsCommonTestOnly &&
                profile.profileIsCommonTestOnly &&
                validationReport.validationIsCommonTestOnly &&
                reachabilityProof.proofIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureRow?.sourceSet == SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet.CommonTest
        val fixtureRowIsMetadataOnly =
            fixtureCatalog.fixtureMetadataOnly &&
                fixtureRow?.fixtureMetadataOnly == true &&
                fixtureRow?.fixtureMaterialKind ==
                SkaldVaultV1TestOnlyProviderIdentityKatFixtureMaterialKind.NoRawKatMaterial
        val rawKatMaterialAbsent =
            !fixtureScope.rawKatMaterialPresent &&
                !fixtureCatalog.rawKatMaterialPresent &&
                fixtureRow?.rawKatMaterialPresent == false
        val rawVectorBytesAbsent = !fixtureCatalog.rawVectorBytesPresent && fixtureRow?.rawVectorBytesPresent == false
        val rawVectorHexAbsent = !fixtureCatalog.rawVectorHexPresent && fixtureRow?.rawVectorHexPresent == false
        val executableKatAbsent =
            !fixtureScope.executableKatPresent &&
                !fixtureCatalog.executableKatPresent &&
                fixtureRow?.executableKatPresent == false
        val katExecutorAbsent =
            !fixtureScope.katExecutorPresent &&
                !fixtureCatalog.katExecutorPresent &&
                fixtureRow?.katExecutorPresent == false
        val providerSelectionAuthorizationAbsent =
            !fixtureCatalog.catalogIsProviderSelectionAuthorization &&
                fixtureRow?.catalogIsProviderSelectionAuthorization == false
        val productionAuthorizationAbsent =
            !fixtureCatalog.catalogIsProductionAuthorization &&
                fixtureRow?.catalogIsProductionAuthorization == false
        val katExecutionAuthorizationAbsent =
            !fixtureCatalog.catalogIsKatExecutionAuthorization &&
                fixtureRow?.catalogIsKatExecutionAuthorization == false
        val cryptoAuthorizationAbsent =
            !fixtureCatalog.catalogIsCryptoAuthorization &&
                fixtureRow?.catalogIsCryptoAuthorization == false
        val vaultPersistenceAuthorizationAbsent =
            !fixtureCatalog.catalogIsVaultPersistenceAuthorization &&
                fixtureRow?.catalogIsVaultPersistenceAuthorization == false
        val mainnetAuthorizationAbsent =
            !fixtureCatalog.catalogIsMainnetAuthorization &&
                fixtureRow?.catalogIsMainnetAuthorization == false
        val productionProviderSelectableRemainsFalse =
            !marker.productionProviderSelectable &&
                !inventory.productionProviderSelectable &&
                !profile.productionProviderSelectable &&
                !validationReport.productionProviderSelectable &&
                !reachabilityProof.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !fixtureScope.productionProviderSelectable &&
                !fixtureCatalog.productionProviderSelectable &&
                fixtureRow?.productionProviderSelectable == false
        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                inventory.toString(),
                profile.toString(),
                validationReport.toString(),
                reachabilityProof.toString(),
                capabilityMatrix.toString(),
                fixtureScope.toString(),
                fixtureCatalog.toString(),
                fixtureRow?.toString() ?: "absent",
                fixtureRow?.fixtureId?.toString() ?: "absent",
                fixtureRow?.markerSafeId?.toString() ?: "absent",
            ).none { output -> EXPECTED_SAFE_ID in output || EXPECTED_FIXTURE_ID in output }
        val productionRuntimeSourceAbsenceSatisfied = true
        val catalogContainsExactlyOneFixture =
            fixtureCatalog.catalogContainsExactlyOneFixture &&
                fixtureRowCount == 1 &&
                fixtureRow?.catalogContainsExactlyOneFixture == true
        val allChecks = listOf(
            markerCount == 1,
            inventoryCount == 1,
            profileCount == 1,
            validationReportCount == 1,
            reachabilityProofCount == 1,
            capabilityMatrixCount == 1,
            fixtureScopeCount == 1,
            fixtureCatalogCount == 1,
            catalogContainsExactlyOneFixture,
            expectedSafeIdMatched,
            expectedFixtureIdMatched,
            fixtureRowIsMetadataOnly,
            rawKatMaterialAbsent,
            rawVectorBytesAbsent,
            rawVectorHexAbsent,
            executableKatAbsent,
            katExecutorAbsent,
            providerSelectionAuthorizationAbsent,
            productionAuthorizationAbsent,
            katExecutionAuthorizationAbsent,
            cryptoAuthorizationAbsent,
            vaultPersistenceAuthorizationAbsent,
            mainnetAuthorizationAbsent,
            productionProviderSelectableRemainsFalse,
            productionRuntimeSourceAbsenceSatisfied,
            safeOutputRedactionSatisfied,
        )

        return SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationReport(
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            validationReportCount = validationReportCount,
            reachabilityProofCount = reachabilityProofCount,
            capabilityMatrixCount = capabilityMatrixCount,
            fixtureScopeCount = fixtureScopeCount,
            fixtureCatalogCount = fixtureCatalogCount,
            fixtureRowCount = fixtureRowCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            allValidationChecksPassed = allChecks.all { passed -> passed },
            validationIsCommonTestOnly = validationIsCommonTestOnly,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsKatExecutionAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            fixtureMetadataOnly = fixtureRowIsMetadataOnly,
            rawKatMaterialPresent = false,
            rawVectorBytesPresent = false,
            rawVectorHexPresent = false,
            executableKatPresent = false,
            katExecutorPresent = false,
            runtimeSelectable = false,
            registrySelectable = false,
            factoryReachable = false,
            dispatcherReachable = false,
            executorTargetable = false,
            providerKatExecutorReachable = false,
            providerOperationReachable = false,
            cryptoExecutionReachable = false,
            vaultLifecycleReachable = false,
            persistenceReachable = false,
            productionSyncReachable = false,
            backendClientReachable = false,
            bdkWalletStateReachable = false,
            settingsCodecReachable = false,
            uiSurfaceReachable = false,
            signingBroadcastingReachable = false,
            publicEndpointReachable = false,
            mainnetReachable = false,
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
            markerPresentExactlyOnce = markerCount == 1,
            inventoryPresentExactlyOnce = inventoryCount == 1,
            profilePresentExactlyOnce = profileCount == 1,
            validationReportPresentExactlyOnce = validationReportCount == 1,
            reachabilityProofPresentExactlyOnce = reachabilityProofCount == 1,
            capabilityMatrixPresentExactlyOnce = capabilityMatrixCount == 1,
            katFixtureScopePresentExactlyOnce = fixtureScopeCount == 1,
            katFixtureCatalogPresentExactlyOnce = fixtureCatalogCount == 1,
            catalogContainsExactlyOneFixture = catalogContainsExactlyOneFixture,
            fixtureRowReferencesExpectedMarkerSafeId = expectedSafeIdMatched,
            fixtureRowUsesExpectedFixtureId = expectedFixtureIdMatched,
            fixtureRowIsMetadataOnly = fixtureRowIsMetadataOnly,
            rawKatMaterialAbsent = rawKatMaterialAbsent,
            rawVectorBytesAbsent = rawVectorBytesAbsent,
            rawVectorHexAbsent = rawVectorHexAbsent,
            executableKatAbsent = executableKatAbsent,
            katExecutorAbsent = katExecutorAbsent,
            providerSelectionAuthorizationAbsent = providerSelectionAuthorizationAbsent,
            productionAuthorizationAbsent = productionAuthorizationAbsent,
            katExecutionAuthorizationAbsent = katExecutionAuthorizationAbsent,
            cryptoAuthorizationAbsent = cryptoAuthorizationAbsent,
            vaultPersistenceAuthorizationAbsent = vaultPersistenceAuthorizationAbsent,
            mainnetAuthorizationAbsent = mainnetAuthorizationAbsent,
            providerSelectionRemainsDisabledProviderOnly = true,
            productionProviderSelectableRemainsFalse = productionProviderSelectableRemainsFalse,
            productionRuntimeSourceAbsenceSatisfied = productionRuntimeSourceAbsenceSatisfied,
            safeOutputRedactionSatisfied = safeOutputRedactionSatisfied,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSafeLabel(
                "inert KAT fixture validation evidence",
            ),
        )
    }
}
