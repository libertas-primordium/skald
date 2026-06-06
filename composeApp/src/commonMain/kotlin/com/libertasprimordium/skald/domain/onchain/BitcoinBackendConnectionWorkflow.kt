package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.security.SecureStorageCapability

@JvmInline
value class BitcoinBackendConnectionTestId(val value: String)

enum class BitcoinBackendConnectionTestMode(val label: String) {
    SimulatedOnly("simulated only"),
    RealNetworkDisabled("real network disabled"),
    FutureRealNetworkPlanned("future real network planned"),
}

data class BitcoinBackendConnectionTestPolicy(
    val mode: BitcoinBackendConnectionTestMode,
    val realNetworkAllowed: Boolean,
) {
    companion object {
        val SimulatedOnly: BitcoinBackendConnectionTestPolicy = BitcoinBackendConnectionTestPolicy(
            mode = BitcoinBackendConnectionTestMode.SimulatedOnly,
            realNetworkAllowed = false,
        )
    }
}

data class BitcoinBackendConnectionTestRequest(
    val profile: BitcoinBackendProfile?,
    val mode: BitcoinBackendConnectionTestMode,
    val secureStorageCapability: SecureStorageCapability,
    val policy: BitcoinBackendConnectionTestPolicy = BitcoinBackendConnectionTestPolicy.SimulatedOnly,
)

enum class BitcoinBackendConnectionTestState(val label: String) {
    NotStarted("not started"),
    ReadyForSimulatedTest("ready for simulated test"),
    BlockedByInvalidProfile("blocked by invalid profile"),
    BlockedByMainnetDisabled("blocked by mainnet disabled"),
    BlockedByCredentialsUnavailable("blocked by credentials unavailable"),
    BlockedByRealNetworkDisabled("blocked by real network disabled"),
    SimulatedSuccess("simulated success"),
    SimulatedFailure("simulated failure"),
    Cancelled("cancelled"),
}

enum class BitcoinBackendConnectionTestStep(val label: String) {
    ProfileSelected("profile selected"),
    BackendTypeSupported("backend type supported"),
    DevelopmentNetwork("development network"),
    EndpointConfigured("endpoint configured"),
    EndpointHasNoCredentialMaterial("endpoint has no credential material"),
    PortValid("port valid"),
    CredentialStorageAvailability("credential storage availability"),
    TrustPrivacyWarning("trust/privacy warning"),
    NoRealNetworkAttempted("no real network attempted"),
    FutureBitcoinCoreRpcEndpointSyntax("future Bitcoin Core RPC endpoint syntax"),
    FutureBitcoinCoreAuthenticationReference("future Bitcoin Core authentication/cookie reference"),
    FutureBitcoinCoreNetworkMatch("future Bitcoin Core network match"),
    FutureBitcoinCoreChainInfo("future Bitcoin Core chain info"),
    FutureBitcoinCoreDescriptorWalletSupport("future Bitcoin Core descriptor wallet support"),
    FutureElectrumEndpointSyntax("future Electrum TCP/TLS endpoint syntax"),
    FutureElectrumServerFeatures("future Electrum server features"),
    FutureElectrumNetworkMatch("future Electrum network match"),
    FutureElectrumScripthashCapability("future Electrum scripthash query capability"),
    FutureEsploraEndpointSyntax("future Esplora HTTP endpoint syntax"),
    FutureEsploraTipHeight("future Esplora /blocks/tip/height"),
    FutureEsploraFeeEstimates("future Esplora fee estimates"),
    FutureEsploraAddressTxAvailability("future Esplora address/tx endpoint availability"),
}

enum class BitcoinBackendConnectionTestFindingLevel(val label: String) {
    Passed("passed"),
    Warning("warning"),
    Blocker("blocker"),
    Planned("planned"),
}

data class BitcoinBackendConnectionTestFinding(
    val step: BitcoinBackendConnectionTestStep,
    val level: BitcoinBackendConnectionTestFindingLevel,
    val detail: String,
) {
    val isBlocking: Boolean
        get() = level == BitcoinBackendConnectionTestFindingLevel.Blocker
}

enum class BitcoinBackendConnectionTestCapability(val label: String) {
    ValidateConfigurationOnly("validate configuration only"),
    ReportTrustPrivacy("report trust/privacy warning"),
    ReportFutureChecks("report future real checks"),
    NoRealNetworkAttempted("no real network attempted"),
    RealNetworkDisabled("real network disabled"),
}

enum class BitcoinBackendConnectionTestBlockingIssue(val label: String) {
    NoSelectedProfile("select or create a backend profile before running a simulated test"),
    InvalidProfile("profile failed validation"),
    MainnetDisabled("mainnet is disabled during development"),
    EndpointMissing("backend endpoint is missing"),
    CredentialMaterialRejected("endpoint contains credential-like material"),
    CredentialsUnavailable("credential storage is disabled or unavailable"),
    RealNetworkDisabled("real backend networking is disabled in this build"),
    UnsupportedBackend("backend type is unsupported"),
    InvalidPort("port is invalid"),
}

data class BitcoinBackendConnectionTestResult(
    val id: BitcoinBackendConnectionTestId,
    val profileId: BitcoinBackendProfileId?,
    val profileLabel: String,
    val backendType: BitcoinBackendType?,
    val mode: BitcoinBackendConnectionTestMode,
    val state: BitcoinBackendConnectionTestState,
    val findings: List<BitcoinBackendConnectionTestFinding>,
    val blockingIssues: Set<BitcoinBackendConnectionTestBlockingIssue>,
    val capabilities: Set<BitcoinBackendConnectionTestCapability>,
    val warnings: List<String>,
) {
    val noRealNetworkAttempted: Boolean
        get() = findings.any {
            it.step == BitcoinBackendConnectionTestStep.NoRealNetworkAttempted &&
                it.detail.contains("CONNECTION_TEST_NOT_REAL_NETWORK")
        }
}

interface BitcoinBackendConnectionTestHarness {
    fun run(request: BitcoinBackendConnectionTestRequest): BitcoinBackendConnectionTestResult
}

object BitcoinBackendConnectionWorkflow {
    fun validateAndRun(
        input: EditableBitcoinBackendProfileInput,
        secureStorageCapability: SecureStorageCapability,
        policy: BitcoinBackendConnectionTestPolicy = BitcoinBackendConnectionTestPolicy.SimulatedOnly,
    ): BitcoinBackendConnectionTestResult {
        val validation = BitcoinBackendValidator.validate(input)
        val normalizedProfile = validation.normalizedProfile
        if (normalizedProfile == null) {
            return invalidValidationResult(
                input = input,
                errors = validation.errors,
                warnings = validation.warnings,
                mode = policy.mode,
            )
        }
        return run(
            BitcoinBackendConnectionTestRequest(
                profile = normalizedProfile,
                mode = policy.mode,
                secureStorageCapability = secureStorageCapability,
                policy = policy,
            ),
        )
    }

    fun run(request: BitcoinBackendConnectionTestRequest): BitcoinBackendConnectionTestResult {
        val profile = request.profile
        if (profile == null) {
            return result(
                id = BitcoinBackendConnectionTestId("CONNECTION_TEST_SIMULATED_NO_PROFILE"),
                profile = null,
                mode = request.mode,
                state = BitcoinBackendConnectionTestState.NotStarted,
                findings = listOf(
                    blocker(
                        BitcoinBackendConnectionTestStep.ProfileSelected,
                        "Select or create a backend profile before running a simulated test.",
                    ),
                    noNetworkFinding(),
                ),
                blockingIssues = setOf(BitcoinBackendConnectionTestBlockingIssue.NoSelectedProfile),
                warnings = listOf("Select or create a backend profile before running a simulated test."),
            )
        }

        if (request.mode != BitcoinBackendConnectionTestMode.SimulatedOnly ||
            request.policy.mode != BitcoinBackendConnectionTestMode.SimulatedOnly ||
            request.policy.realNetworkAllowed
        ) {
            return result(
                id = testIdFor(profile),
                profile = profile,
                mode = request.mode,
                state = BitcoinBackendConnectionTestState.BlockedByRealNetworkDisabled,
                findings = listOf(
                    blocker(
                        BitcoinBackendConnectionTestStep.NoRealNetworkAttempted,
                        "REAL_NETWORK_DISABLED - real backend connection testing is not implemented.",
                    ),
                    noNetworkFinding(),
                ),
                blockingIssues = setOf(BitcoinBackendConnectionTestBlockingIssue.RealNetworkDisabled),
                warnings = listOf("Real backend connection testing is not implemented yet."),
            )
        }

        if (!profile.network.isDevelopmentSelectable || profile.network.allowsMainnetOperations) {
            return result(
                id = testIdFor(profile),
                profile = profile,
                mode = request.mode,
                state = BitcoinBackendConnectionTestState.BlockedByMainnetDisabled,
                findings = baseFindings(profile) + blocker(
                    BitcoinBackendConnectionTestStep.DevelopmentNetwork,
                    "MAINNET_DISABLED - Mainnet is disabled during development.",
                ) + noNetworkFinding(),
                blockingIssues = setOf(BitcoinBackendConnectionTestBlockingIssue.MainnetDisabled),
                warnings = profile.warnings + "Mainnet is disabled during development.",
            )
        }

        if (!profile.endpoint.isConfigured) {
            return result(
                id = testIdFor(profile),
                profile = profile,
                mode = request.mode,
                state = BitcoinBackendConnectionTestState.BlockedByInvalidProfile,
                findings = baseFindings(profile) + blocker(
                    BitcoinBackendConnectionTestStep.EndpointConfigured,
                    "BACKEND_NOT_CONNECTED - endpoint metadata is not configured.",
                ) + noNetworkFinding(),
                blockingIssues = setOf(BitcoinBackendConnectionTestBlockingIssue.EndpointMissing),
                warnings = profile.warnings,
            )
        }

        val credentialBlocked = profile.credentialReference != null &&
            !request.secureStorageCapability.canReadSecrets
        if (credentialBlocked) {
            return result(
                id = testIdFor(profile),
                profile = profile,
                mode = request.mode,
                state = BitcoinBackendConnectionTestState.BlockedByCredentialsUnavailable,
                findings = baseFindings(profile) +
                    passed(
                        BitcoinBackendConnectionTestStep.EndpointConfigured,
                        "Endpoint metadata is configured: ${profile.endpointDisplay}",
                    ) +
                    blocker(
                        BitcoinBackendConnectionTestStep.CredentialStorageAvailability,
                        "CREDENTIAL_STORAGE_NOT_IMPLEMENTED - credential references cannot unlock real backend tests.",
                    ) +
                    futureChecksFor(profile.type) +
                    noNetworkFinding(),
                blockingIssues = setOf(BitcoinBackendConnectionTestBlockingIssue.CredentialsUnavailable),
                warnings = profile.warnings + request.secureStorageCapability.implementationNote,
            )
        }

        return result(
            id = testIdFor(profile),
            profile = profile,
            mode = request.mode,
            state = BitcoinBackendConnectionTestState.SimulatedSuccess,
            findings = baseFindings(profile) +
                passed(
                    BitcoinBackendConnectionTestStep.EndpointConfigured,
                    "Endpoint metadata is configured: ${profile.endpointDisplay}",
                ) +
                passed(
                    BitcoinBackendConnectionTestStep.PortValid,
                    "Port metadata passed deterministic validation when the profile was saved.",
                ) +
                credentialFindingFor(profile, request.secureStorageCapability) +
                trustFindingFor(profile) +
                futureChecksFor(profile.type) +
                noNetworkFinding(),
            blockingIssues = emptySet(),
            warnings = profile.warnings + simulatedWarningsFor(profile, request.secureStorageCapability),
        )
    }

    private fun invalidValidationResult(
        input: EditableBitcoinBackendProfileInput,
        errors: List<BackendProfileValidationError>,
        warnings: List<String>,
        mode: BitcoinBackendConnectionTestMode,
    ): BitcoinBackendConnectionTestResult {
        val blockingIssues = errors.map { error ->
            when (error) {
                BackendProfileValidationError.MainnetDisabled -> BitcoinBackendConnectionTestBlockingIssue.MainnetDisabled
                BackendProfileValidationError.MissingHost -> BitcoinBackendConnectionTestBlockingIssue.EndpointMissing
                BackendProfileValidationError.MissingPort,
                BackendProfileValidationError.InvalidPort,
                BackendProfileValidationError.AmbiguousEndpointPort,
                -> BitcoinBackendConnectionTestBlockingIssue.InvalidPort
                BackendProfileValidationError.CredentialMaterialRejected ->
                    BitcoinBackendConnectionTestBlockingIssue.CredentialMaterialRejected
                BackendProfileValidationError.BlankLabel,
                BackendProfileValidationError.UnsupportedScheme,
                BackendProfileValidationError.UnsupportedPath,
                BackendProfileValidationError.MalformedEndpoint,
                BackendProfileValidationError.MainnetDefaultEndpointRejected,
                -> BitcoinBackendConnectionTestBlockingIssue.InvalidProfile
            }
        }.toSet()
        val state = when {
            errors.contains(BackendProfileValidationError.MainnetDisabled) ->
                BitcoinBackendConnectionTestState.BlockedByMainnetDisabled
            else -> BitcoinBackendConnectionTestState.BlockedByInvalidProfile
        }
        val findings = listOf(
            passed(
                BitcoinBackendConnectionTestStep.BackendTypeSupported,
                "Backend type metadata selected: ${input.type.label}.",
            ),
        ) + errors.map { error ->
            blocker(
                step = validationStepFor(error),
                detail = error.message,
            )
        } + noNetworkFinding()

        return BitcoinBackendConnectionTestResult(
            id = BitcoinBackendConnectionTestId("CONNECTION_TEST_SIMULATED_INVALID_PROFILE"),
            profileId = input.id,
            profileLabel = input.label.ifBlank { "invalid backend profile input" },
            backendType = input.type,
            mode = mode,
            state = state,
            findings = findings,
            blockingIssues = blockingIssues.ifEmpty { setOf(BitcoinBackendConnectionTestBlockingIssue.InvalidProfile) },
            capabilities = commonCapabilities(),
            warnings = warnings,
        )
    }

    private fun baseFindings(profile: BitcoinBackendProfile): List<BitcoinBackendConnectionTestFinding> =
        listOf(
            passed(
                BitcoinBackendConnectionTestStep.ProfileSelected,
                "Selected profile: ${profile.label}.",
            ),
            passed(
                BitcoinBackendConnectionTestStep.BackendTypeSupported,
                "Backend type metadata selected: ${profile.type.label}.",
            ),
            passed(
                BitcoinBackendConnectionTestStep.DevelopmentNetwork,
                "Development network selected: ${profile.network.label}.",
            ),
            passed(
                BitcoinBackendConnectionTestStep.EndpointHasNoCredentialMaterial,
                "Endpoint display contains no stored credential material.",
            ),
        )

    private fun futureChecksFor(
        type: BitcoinBackendType,
    ): List<BitcoinBackendConnectionTestFinding> =
        when (type) {
            BitcoinBackendType.BitcoinCoreRpc -> listOf(
                planned(BitcoinBackendConnectionTestStep.FutureBitcoinCoreRpcEndpointSyntax, "Future real test: RPC endpoint syntax."),
                planned(BitcoinBackendConnectionTestStep.FutureBitcoinCoreAuthenticationReference, "Future real test: authentication or cookie secret reference."),
                planned(BitcoinBackendConnectionTestStep.FutureBitcoinCoreNetworkMatch, "Future real test: configured network matches node chain."),
                planned(BitcoinBackendConnectionTestStep.FutureBitcoinCoreChainInfo, "Future real test: chain info RPC response."),
                planned(BitcoinBackendConnectionTestStep.FutureBitcoinCoreDescriptorWalletSupport, "Future real test: descriptor wallet RPC support."),
            )
            BitcoinBackendType.Electrum -> listOf(
                planned(BitcoinBackendConnectionTestStep.FutureElectrumEndpointSyntax, "Future real test: TCP/TLS endpoint syntax."),
                planned(BitcoinBackendConnectionTestStep.FutureElectrumServerFeatures, "Future real test: server features."),
                planned(BitcoinBackendConnectionTestStep.FutureElectrumNetworkMatch, "Future real test: server network matches profile."),
                planned(BitcoinBackendConnectionTestStep.FutureElectrumScripthashCapability, "Future real test: scripthash query capability."),
            )
            BitcoinBackendType.Esplora -> listOf(
                planned(BitcoinBackendConnectionTestStep.FutureEsploraEndpointSyntax, "Future real test: HTTP endpoint syntax."),
                planned(BitcoinBackendConnectionTestStep.FutureEsploraTipHeight, "Future real test: /blocks/tip/height."),
                planned(BitcoinBackendConnectionTestStep.FutureEsploraFeeEstimates, "Future real test: fee estimates endpoint."),
                planned(BitcoinBackendConnectionTestStep.FutureEsploraAddressTxAvailability, "Future real test: address and tx endpoint availability."),
            )
        }

    private fun credentialFindingFor(
        profile: BitcoinBackendProfile,
        secureStorageCapability: SecureStorageCapability,
    ): BitcoinBackendConnectionTestFinding =
        when {
            profile.credentialPolicy == BackendCredentialPolicy.RequiresSecureStorageBeforeUse &&
                !secureStorageCapability.canStoreSecrets ->
                warning(
                    BitcoinBackendConnectionTestStep.CredentialStorageAvailability,
                    "Future real ${profile.type.label} authentication is unavailable because secure storage is disabled.",
                )
            profile.credentialPolicy == BackendCredentialPolicy.PublicEndpointNoCredentialStillLeaks ->
                warning(
                    BitcoinBackendConnectionTestStep.CredentialStorageAvailability,
                    "Public endpoint profile has no credential, but wallet-query privacy still leaks to the backend.",
                )
            else ->
                passed(
                    BitcoinBackendConnectionTestStep.CredentialStorageAvailability,
                    "No credential is stored or required for this simulated validation path.",
                )
        }

    private fun trustFindingFor(profile: BitcoinBackendProfile): BitcoinBackendConnectionTestFinding =
        when (profile.trustModel) {
            BitcoinBackendTrustModel.PublicBackend,
            BitcoinBackendTrustModel.TrustedThirdParty,
            -> warning(
                BitcoinBackendConnectionTestStep.TrustPrivacyWarning,
                "Public backends can observe wallet queries. Prefer a user-owned node.",
            )
            BitcoinBackendTrustModel.UserOwnedNode -> passed(
                BitcoinBackendConnectionTestStep.TrustPrivacyWarning,
                "User-owned backend trust model selected.",
            )
            BitcoinBackendTrustModel.Unknown -> warning(
                BitcoinBackendConnectionTestStep.TrustPrivacyWarning,
                "Unknown backend trust means wallet-query privacy risk is not classified.",
            )
        }

    private fun simulatedWarningsFor(
        profile: BitcoinBackendProfile,
        secureStorageCapability: SecureStorageCapability,
    ): List<String> =
        buildList {
            add("Simulated only. No network connection was attempted.")
            add("Real backend connection testing is not implemented yet.")
            if (profile.trustModel == BitcoinBackendTrustModel.PublicBackend ||
                profile.trustModel == BitcoinBackendTrustModel.TrustedThirdParty
            ) {
                add("Public backends can observe wallet queries. Prefer a user-owned node.")
            }
            if (profile.credentialPolicy == BackendCredentialPolicy.RequiresSecureStorageBeforeUse &&
                !secureStorageCapability.canStoreSecrets
            ) {
                add("Future real credential use is unavailable until secure storage is implemented.")
            }
        }

    private fun result(
        id: BitcoinBackendConnectionTestId,
        profile: BitcoinBackendProfile?,
        mode: BitcoinBackendConnectionTestMode,
        state: BitcoinBackendConnectionTestState,
        findings: List<BitcoinBackendConnectionTestFinding>,
        blockingIssues: Set<BitcoinBackendConnectionTestBlockingIssue>,
        warnings: List<String>,
    ): BitcoinBackendConnectionTestResult =
        BitcoinBackendConnectionTestResult(
            id = id,
            profileId = profile?.id,
            profileLabel = profile?.label ?: "BACKEND_NOT_CONFIGURED",
            backendType = profile?.type,
            mode = mode,
            state = state,
            findings = findings,
            blockingIssues = blockingIssues,
            capabilities = commonCapabilities(),
            warnings = warnings.distinct(),
        )

    private fun commonCapabilities(): Set<BitcoinBackendConnectionTestCapability> =
        setOf(
            BitcoinBackendConnectionTestCapability.ValidateConfigurationOnly,
            BitcoinBackendConnectionTestCapability.ReportTrustPrivacy,
            BitcoinBackendConnectionTestCapability.ReportFutureChecks,
            BitcoinBackendConnectionTestCapability.NoRealNetworkAttempted,
            BitcoinBackendConnectionTestCapability.RealNetworkDisabled,
        )

    private fun validationStepFor(error: BackendProfileValidationError): BitcoinBackendConnectionTestStep =
        when (error) {
            BackendProfileValidationError.BlankLabel -> BitcoinBackendConnectionTestStep.ProfileSelected
            BackendProfileValidationError.MainnetDisabled -> BitcoinBackendConnectionTestStep.DevelopmentNetwork
            BackendProfileValidationError.MissingHost -> BitcoinBackendConnectionTestStep.EndpointConfigured
            BackendProfileValidationError.MissingPort,
            BackendProfileValidationError.InvalidPort,
            BackendProfileValidationError.AmbiguousEndpointPort,
            -> BitcoinBackendConnectionTestStep.PortValid
            BackendProfileValidationError.CredentialMaterialRejected ->
                BitcoinBackendConnectionTestStep.EndpointHasNoCredentialMaterial
            BackendProfileValidationError.UnsupportedScheme,
            BackendProfileValidationError.UnsupportedPath,
            BackendProfileValidationError.MalformedEndpoint,
            BackendProfileValidationError.MainnetDefaultEndpointRejected,
            -> BitcoinBackendConnectionTestStep.EndpointConfigured
        }

    private fun testIdFor(profile: BitcoinBackendProfile): BitcoinBackendConnectionTestId =
        BitcoinBackendConnectionTestId("CONNECTION_TEST_SIMULATED_${profile.id.value}")

    private fun noNetworkFinding(): BitcoinBackendConnectionTestFinding =
        passed(
            BitcoinBackendConnectionTestStep.NoRealNetworkAttempted,
            "CONNECTION_TEST_NOT_REAL_NETWORK - simulated only; no socket, DNS, RPC, Electrum, or HTTP call occurred.",
        )

    private fun passed(
        step: BitcoinBackendConnectionTestStep,
        detail: String,
    ): BitcoinBackendConnectionTestFinding =
        BitcoinBackendConnectionTestFinding(step, BitcoinBackendConnectionTestFindingLevel.Passed, detail)

    private fun warning(
        step: BitcoinBackendConnectionTestStep,
        detail: String,
    ): BitcoinBackendConnectionTestFinding =
        BitcoinBackendConnectionTestFinding(step, BitcoinBackendConnectionTestFindingLevel.Warning, detail)

    private fun planned(
        step: BitcoinBackendConnectionTestStep,
        detail: String,
    ): BitcoinBackendConnectionTestFinding =
        BitcoinBackendConnectionTestFinding(step, BitcoinBackendConnectionTestFindingLevel.Planned, detail)

    private fun blocker(
        step: BitcoinBackendConnectionTestStep,
        detail: String,
    ): BitcoinBackendConnectionTestFinding =
        BitcoinBackendConnectionTestFinding(step, BitcoinBackendConnectionTestFindingLevel.Blocker, detail)
}
