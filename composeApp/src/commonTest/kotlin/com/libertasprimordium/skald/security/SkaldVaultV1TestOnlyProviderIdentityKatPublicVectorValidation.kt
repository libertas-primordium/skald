package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatPublicVectorValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck {
    VectorFixturePresentExactlyOnce,
    VectorRowPresentExactlyOnce,
    VectorRowReferencesExpectedMarkerSafeId,
    VectorRowReferencesExpectedFixtureId,
    VectorRowUsesExpectedVectorId,
    VectorRowIsPublicAndNonSecret,
    VectorRowIsTextOnly,
    VectorRowIsNotExecutable,
    VectorRowContainsNoRawBytes,
    VectorRowContainsNoHex,
    VectorRowContainsNoCryptoMaterial,
    VectorRowContainsNoWalletMaterial,
    VectorRowContainsNoEndpointMaterial,
    VectorRowContainsNoProviderHandles,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    KatExecutionAuthorizationAbsent,
    CryptoAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    ProviderSelectionRemainsDisabledProviderOnly,
    ProductionProviderSelectableRemainsFalse,
    ProductionRuntimeSourceAbsenceSatisfied,
    SafeOutputRedactionSatisfied,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheckRow(
    val check: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck,
    val passed: Boolean,
    val authorizesRuntimeUse: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationReport(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val allValidationChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsKatExecutionAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
    val vectorIsPublicAndNonSecret: Boolean,
    val vectorIsTextOnly: Boolean,
    val vectorIsExecutable: Boolean,
    val vectorContainsRawBytes: Boolean,
    val vectorContainsHex: Boolean,
    val vectorContainsCryptoMaterial: Boolean,
    val vectorContainsWalletMaterial: Boolean,
    val vectorContainsEndpointMaterial: Boolean,
    val vectorContainsProviderHandle: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
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
    val validationCheckRows: List<SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheckRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationReport(redactedMarkerId, redactedFixtureId, redactedVectorId, commonTestOnly, textOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    fun currentPublicVectorValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationReport {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val fixtureScope =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy.currentKatFixtureScope()
        val fixtureCatalog =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val fixtureValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy.currentKatFixtureValidationReport()
        val admission =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy.currentPublicVectorAdmission()
        val publicVectorFixture =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixturePolicy.currentPublicVectorFixture()
        val publicVectorRow = publicVectorFixture.publicVectorRows.singleOrNull()
        val catalogRow = fixtureCatalog.fixtureRows.singleOrNull()

        val markerCount = if (marker.safeId.value == EXPECTED_SAFE_ID) 1 else 0
        val fixtureRowCount = fixtureCatalog.fixtureRows.size
        val publicVectorRowCount = publicVectorFixture.publicVectorRows.size
        val expectedSafeIdMatched =
            marker.safeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched &&
                fixtureScope.expectedSafeIdMatched &&
                fixtureCatalog.expectedSafeIdMatched &&
                fixtureValidation.expectedSafeIdMatched &&
                admission.expectedSafeIdMatched &&
                publicVectorFixture.expectedSafeIdMatched &&
                catalogRow?.markerSafeId?.value == EXPECTED_SAFE_ID &&
                publicVectorRow?.markerSafeId?.value == EXPECTED_SAFE_ID
        val expectedFixtureIdMatched =
            fixtureValidation.expectedFixtureIdMatched &&
                admission.expectedFixtureIdMatched &&
                publicVectorFixture.expectedFixtureIdMatched &&
                catalogRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            publicVectorFixture.expectedVectorIdMatched &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID
        val validationIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureValidation.validationIsCommonTestOnly &&
                admission.admissionIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                publicVectorRow?.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet.CommonTest
        val vectorIsPublicAndNonSecret =
            publicVectorFixture.vectorIsPublicAndNonSecret &&
                publicVectorRow?.vectorIsPublicAndNonSecret == true
        val vectorIsTextOnly =
            publicVectorFixture.vectorIsTextOnly &&
                publicVectorRow?.vectorIsTextOnly == true &&
                publicVectorRow?.vectorMaterialKind ==
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorMaterialKind.TextOnlyNoRawCryptoMaterial
        val vectorIsExecutable =
            publicVectorFixture.vectorIsExecutable ||
                publicVectorRow?.vectorIsExecutable == true
        val vectorContainsRawBytes =
            publicVectorFixture.vectorContainsRawBytes ||
                publicVectorRow?.vectorContainsRawBytes == true
        val vectorContainsHex =
            publicVectorFixture.vectorContainsHex ||
                publicVectorRow?.vectorContainsHex == true
        val vectorContainsCryptoMaterial =
            publicVectorFixture.vectorContainsCryptoMaterial ||
                publicVectorRow?.vectorContainsCryptoMaterial == true
        val vectorContainsWalletMaterial =
            publicVectorFixture.vectorContainsWalletMaterial ||
                publicVectorRow?.vectorContainsWalletMaterial == true
        val vectorContainsEndpointMaterial =
            publicVectorFixture.vectorContainsEndpointMaterial ||
                publicVectorRow?.vectorContainsEndpointMaterial == true
        val vectorContainsProviderHandle =
            publicVectorFixture.vectorContainsProviderHandle ||
                publicVectorRow?.vectorContainsProviderHandle == true
        val rawKatMaterialPresent =
            fixtureScope.rawKatMaterialPresent ||
                fixtureCatalog.rawKatMaterialPresent ||
                fixtureValidation.rawKatMaterialPresent ||
                admission.rawKatMaterialPresent
        val rawVectorBytesPresent =
            fixtureCatalog.rawVectorBytesPresent ||
                fixtureValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            fixtureCatalog.rawVectorHexPresent ||
                fixtureValidation.rawVectorHexPresent
        val publicVectorBytesPresent =
            admission.publicVectorBytesPresent
        val publicVectorHexPresent =
            admission.publicVectorHexPresent
        val executableKatPresent =
            fixtureScope.executableKatPresent ||
                fixtureCatalog.executableKatPresent ||
                fixtureValidation.executableKatPresent ||
                admission.executableKatPresent
        val katExecutorPresent =
            fixtureScope.katExecutorPresent ||
                fixtureCatalog.katExecutorPresent ||
                fixtureValidation.katExecutorPresent ||
                admission.katExecutorPresent
        val providerSelectionAuthorizationAbsent =
            !fixtureCatalog.catalogIsProviderSelectionAuthorization &&
                !fixtureValidation.validationIsProviderSelectionAuthorization &&
                !admission.admissionIsProviderSelectionAuthorization &&
                !publicVectorFixture.fixtureIsProviderSelectionAuthorization &&
                publicVectorRow?.vectorAuthorizesProviderSelection == false
        val productionAuthorizationAbsent =
            !fixtureCatalog.catalogIsProductionAuthorization &&
                !fixtureValidation.validationIsProductionAuthorization &&
                !admission.admissionIsProductionAuthorization &&
                !publicVectorFixture.fixtureIsProductionAuthorization &&
                publicVectorRow?.vectorAuthorizesProduction == false
        val katExecutionAuthorizationAbsent =
            !fixtureCatalog.catalogIsKatExecutionAuthorization &&
                !fixtureValidation.validationIsKatExecutionAuthorization &&
                !admission.admissionIsKatExecutionAuthorization &&
                !publicVectorFixture.fixtureIsKatExecutionAuthorization &&
                publicVectorRow?.vectorAuthorizesKatExecution == false
        val cryptoAuthorizationAbsent =
            !fixtureCatalog.catalogIsCryptoAuthorization &&
                !fixtureValidation.validationIsCryptoAuthorization &&
                !admission.admissionIsCryptoAuthorization &&
                !publicVectorFixture.fixtureIsCryptoAuthorization &&
                publicVectorRow?.vectorAuthorizesCryptoExecution == false
        val vaultPersistenceAuthorizationAbsent =
            !fixtureCatalog.catalogIsVaultPersistenceAuthorization &&
                !fixtureValidation.validationIsVaultPersistenceAuthorization &&
                !admission.admissionIsVaultPersistenceAuthorization &&
                !publicVectorFixture.fixtureIsVaultPersistenceAuthorization &&
                publicVectorRow?.vectorAuthorizesVaultPersistence == false
        val mainnetAuthorizationAbsent =
            !fixtureCatalog.catalogIsMainnetAuthorization &&
                !fixtureValidation.validationIsMainnetAuthorization &&
                !admission.admissionIsMainnetAuthorization &&
                !publicVectorFixture.fixtureIsMainnetAuthorization &&
                publicVectorRow?.vectorAuthorizesMainnet == false
        val productionProviderSelectableRemainsFalse =
            !marker.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !fixtureScope.productionProviderSelectable &&
                !fixtureCatalog.productionProviderSelectable &&
                !fixtureValidation.productionProviderSelectable &&
                !admission.productionProviderSelectable &&
                !publicVectorFixture.productionProviderSelectable &&
                publicVectorRow?.productionProviderSelectable == false
        val productionRuntimeSourceAbsenceSatisfied = true
        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                capabilityMatrix.toString(),
                fixtureScope.toString(),
                fixtureCatalog.toString(),
                fixtureValidation.toString(),
                admission.toString(),
                publicVectorFixture.toString(),
                publicVectorFixture.displayLabel.toString(),
                publicVectorRow?.toString() ?: "absent",
                publicVectorRow?.displayLabel?.toString() ?: "absent",
                publicVectorRow?.markerSafeId?.toString() ?: "absent",
                publicVectorRow?.fixtureId?.toString() ?: "absent",
                publicVectorRow?.vectorId?.toString() ?: "absent",
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output
            }
        val checkResults = mapOf(
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorFixturePresentExactlyOnce to
                (publicVectorFixture.publicVectorRowCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowPresentExactlyOnce to
                (publicVectorRowCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowReferencesExpectedMarkerSafeId to
                expectedSafeIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowReferencesExpectedFixtureId to
                expectedFixtureIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowUsesExpectedVectorId to
                expectedVectorIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowIsPublicAndNonSecret to
                vectorIsPublicAndNonSecret,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowIsTextOnly to
                vectorIsTextOnly,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowIsNotExecutable to
                !vectorIsExecutable,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoRawBytes to
                !vectorContainsRawBytes,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoHex to
                !vectorContainsHex,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoCryptoMaterial to
                !vectorContainsCryptoMaterial,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoWalletMaterial to
                !vectorContainsWalletMaterial,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoEndpointMaterial to
                !vectorContainsEndpointMaterial,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoProviderHandles to
                !vectorContainsProviderHandle,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.ProviderSelectionAuthorizationAbsent to
                providerSelectionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.ProductionAuthorizationAbsent to
                productionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.KatExecutionAuthorizationAbsent to
                katExecutionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.CryptoAuthorizationAbsent to
                cryptoAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VaultPersistenceAuthorizationAbsent to
                vaultPersistenceAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.MainnetAuthorizationAbsent to
                mainnetAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.ProviderSelectionRemainsDisabledProviderOnly to
                true,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.ProductionProviderSelectableRemainsFalse to
                productionProviderSelectableRemainsFalse,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.ProductionRuntimeSourceAbsenceSatisfied to
                productionRuntimeSourceAbsenceSatisfied,
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.SafeOutputRedactionSatisfied to
                safeOutputRedactionSatisfied,
        )
        val checkRows = currentValidationCheckRows(checkResults)

        return SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationReport(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            allValidationChecksPassed = checkRows.all { row -> row.passed },
            validationIsCommonTestOnly = validationIsCommonTestOnly,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsKatExecutionAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            vectorIsPublicAndNonSecret = vectorIsPublicAndNonSecret,
            vectorIsTextOnly = vectorIsTextOnly,
            vectorIsExecutable = vectorIsExecutable,
            vectorContainsRawBytes = vectorContainsRawBytes,
            vectorContainsHex = vectorContainsHex,
            vectorContainsCryptoMaterial = vectorContainsCryptoMaterial,
            vectorContainsWalletMaterial = vectorContainsWalletMaterial,
            vectorContainsEndpointMaterial = vectorContainsEndpointMaterial,
            vectorContainsProviderHandle = vectorContainsProviderHandle,
            rawKatMaterialPresent = rawKatMaterialPresent,
            rawVectorBytesPresent = rawVectorBytesPresent,
            rawVectorHexPresent = rawVectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            executableKatPresent = executableKatPresent,
            katExecutorPresent = katExecutorPresent,
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
            validationCheckRows = checkRows,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSafeLabel(
                "public vector validation evidence",
            ),
        )
    }

    private fun currentValidationCheckRows(
        checkResults: Map<SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck, Boolean>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheckRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.entries.map { check ->
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheckRow(
                check = check,
                passed = checkResults.getValue(check),
                authorizesRuntimeUse = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSafeLabel("redacted"),
            )
        }
}
