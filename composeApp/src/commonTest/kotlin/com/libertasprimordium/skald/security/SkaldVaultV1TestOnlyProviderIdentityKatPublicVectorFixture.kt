package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeId(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatPublicVectorFixtureSafeId"
}

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatPublicVectorFixtureSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorKind {
    PublicNonSecretMetadataVector,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorPurpose {
    InertIdentityMetadataOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorMaterialKind {
    TextOnlyNoRawCryptoMaterial,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorRow(
    val vectorId: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeId,
    val fixtureId: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId,
    val markerSafeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet,
    val vectorKind: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorKind,
    val vectorPurpose: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorPurpose,
    val vectorMaterialKind: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorMaterialKind,
    val markerCount: Int,
    val fixtureRowCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val vectorIsCommonTestOnly: Boolean,
    val vectorIsPublicAndNonSecret: Boolean,
    val vectorIsTextOnly: Boolean,
    val vectorIsExecutable: Boolean,
    val vectorContainsRawBytes: Boolean,
    val vectorContainsHex: Boolean,
    val vectorContainsCryptoMaterial: Boolean,
    val vectorContainsWalletMaterial: Boolean,
    val vectorContainsEndpointMaterial: Boolean,
    val vectorContainsProviderHandle: Boolean,
    val vectorAuthorizesProduction: Boolean,
    val vectorAuthorizesProviderSelection: Boolean,
    val vectorAuthorizesKatExecution: Boolean,
    val vectorAuthorizesCryptoExecution: Boolean,
    val vectorAuthorizesVaultPersistence: Boolean,
    val vectorAuthorizesMainnet: Boolean,
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
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorRow(redactedVectorId, redactedFixtureId, redactedMarkerId, commonTestOnly, textOnly, nonAuthorizing)"
}

data class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixture(
    val publicVectorRows: List<SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorRow>,
    val markerCount: Int,
    val fixtureCatalogCount: Int,
    val fixtureValidationCount: Int,
    val publicVectorAdmissionCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val fixtureIsCommonTestOnly: Boolean,
    val fixtureContainsExactlyOnePublicVector: Boolean,
    val fixtureIsProductionAuthorization: Boolean,
    val fixtureIsProviderSelectionAuthorization: Boolean,
    val fixtureIsKatExecutionAuthorization: Boolean,
    val fixtureIsCryptoAuthorization: Boolean,
    val fixtureIsVaultPersistenceAuthorization: Boolean,
    val fixtureIsMainnetAuthorization: Boolean,
    val vectorIsPublicAndNonSecret: Boolean,
    val vectorIsTextOnly: Boolean,
    val vectorIsExecutable: Boolean,
    val vectorContainsRawBytes: Boolean,
    val vectorContainsHex: Boolean,
    val vectorContainsCryptoMaterial: Boolean,
    val vectorContainsWalletMaterial: Boolean,
    val vectorContainsEndpointMaterial: Boolean,
    val vectorContainsProviderHandle: Boolean,
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
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixture(redactedVectorIds, redactedFixtureIds, redactedMarkerIds, commonTestOnly, publicNonSecretTextOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixturePolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    fun currentPublicVectorFixture(): SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixture {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val catalog = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy.currentKatFixtureValidationReport()
        val admission =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy.currentPublicVectorAdmission()
        val fixtureRow = catalog.fixtureRows.single()
        val expectedSafeIdMatched =
            marker.safeId.value == EXPECTED_SAFE_ID &&
                catalog.expectedSafeIdMatched &&
                validation.expectedSafeIdMatched &&
                admission.expectedSafeIdMatched &&
                fixtureRow.markerSafeId.value == EXPECTED_SAFE_ID
        val expectedFixtureIdMatched =
            validation.expectedFixtureIdMatched &&
                admission.expectedFixtureIdMatched &&
                fixtureRow.fixtureId.value == EXPECTED_FIXTURE_ID
        val publicVectorRow = currentPublicVectorRow(
            markerSafeId = marker.safeId,
            fixtureId = fixtureRow.fixtureId,
            markerCount = if (expectedSafeIdMatched) 1 else 0,
            fixtureRowCount = catalog.fixtureRows.size,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
        )
        val rows = listOf(publicVectorRow)

        return SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixture(
            publicVectorRows = rows,
            markerCount = if (expectedSafeIdMatched) 1 else 0,
            fixtureCatalogCount = if (catalog.catalogContainsExactlyOneFixture) 1 else 0,
            fixtureValidationCount = if (validation.allValidationChecksPassed) 1 else 0,
            publicVectorAdmissionCount = if (admission.futureVectorCriteriaModeled) 1 else 0,
            fixtureRowCount = catalog.fixtureRows.size,
            publicVectorRowCount = rows.size,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = rows.single().vectorId.value == EXPECTED_VECTOR_ID,
            fixtureIsCommonTestOnly =
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                    catalog.catalogIsCommonTestOnly &&
                    validation.validationIsCommonTestOnly &&
                    admission.admissionIsCommonTestOnly &&
                    rows.single().sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet.CommonTest,
            fixtureContainsExactlyOnePublicVector = rows.size == 1,
            fixtureIsProductionAuthorization = false,
            fixtureIsProviderSelectionAuthorization = false,
            fixtureIsKatExecutionAuthorization = false,
            fixtureIsCryptoAuthorization = false,
            fixtureIsVaultPersistenceAuthorization = false,
            fixtureIsMainnetAuthorization = false,
            vectorIsPublicAndNonSecret = true,
            vectorIsTextOnly = true,
            vectorIsExecutable = false,
            vectorContainsRawBytes = false,
            vectorContainsHex = false,
            vectorContainsCryptoMaterial = false,
            vectorContainsWalletMaterial = false,
            vectorContainsEndpointMaterial = false,
            vectorContainsProviderHandle = false,
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
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeLabel(
                "public non-secret inert identity metadata vector",
            ),
        )
    }

    private fun currentPublicVectorRow(
        markerSafeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
        fixtureId: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId,
        markerCount: Int,
        fixtureRowCount: Int,
        expectedSafeIdMatched: Boolean,
        expectedFixtureIdMatched: Boolean,
    ): SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorRow =
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorRow(
            vectorId = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeId(EXPECTED_VECTOR_ID),
            fixtureId = fixtureId,
            markerSafeId = markerSafeId,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet.CommonTest,
            vectorKind =
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorKind.PublicNonSecretMetadataVector,
            vectorPurpose =
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorPurpose.InertIdentityMetadataOnly,
            vectorMaterialKind =
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorMaterialKind.TextOnlyNoRawCryptoMaterial,
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = true,
            vectorIsCommonTestOnly = true,
            vectorIsPublicAndNonSecret = true,
            vectorIsTextOnly = true,
            vectorIsExecutable = false,
            vectorContainsRawBytes = false,
            vectorContainsHex = false,
            vectorContainsCryptoMaterial = false,
            vectorContainsWalletMaterial = false,
            vectorContainsEndpointMaterial = false,
            vectorContainsProviderHandle = false,
            vectorAuthorizesProduction = false,
            vectorAuthorizesProviderSelection = false,
            vectorAuthorizesKatExecution = false,
            vectorAuthorizesCryptoExecution = false,
            vectorAuthorizesVaultPersistence = false,
            vectorAuthorizesMainnet = false,
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
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeLabel(
                "inert identity metadata vector row",
            ),
        )
}
