package com.libertasprimordium.skald

import com.libertasprimordium.skald.demo.FakeBitcoinBackendConnectionTester
import com.libertasprimordium.skald.domain.core.CredentialReference
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendCredentialPolicy
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestBlockingIssue
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestCapability
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestFindingLevel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestMode
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestRequest
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestState
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestStep
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BitcoinBackendConnectionHarnessTest {
    private val secureStorageCapability = DisabledSecureSecretStorage().capability
    private val tester = FakeBitcoinBackendConnectionTester()

    @Test
    fun simulatedBitcoinCoreProfileTestSucceedsForValidNonSecretDevelopmentProfile() {
        val result = tester.run(request(validProfile(BitcoinBackendType.BitcoinCoreRpc)))

        assertEquals(BitcoinBackendConnectionTestState.SimulatedSuccess, result.state)
        assertEquals(BitcoinBackendType.BitcoinCoreRpc, result.backendType)
        assertNoRealNetwork(result)
    }

    @Test
    fun simulatedElectrumProfileTestSucceedsForValidNonSecretDevelopmentProfile() {
        val result = tester.run(
            request(
                validProfile(
                    type = BitcoinBackendType.Electrum,
                    host = "example.invalid",
                    portText = "50002",
                    useTls = true,
                ),
            ),
        )

        assertEquals(BitcoinBackendConnectionTestState.SimulatedSuccess, result.state)
        assertEquals(BitcoinBackendType.Electrum, result.backendType)
        assertNoRealNetwork(result)
    }

    @Test
    fun simulatedEsploraProfileTestSucceedsForValidNonSecretDevelopmentProfile() {
        val result = tester.run(
            request(
                validProfile(
                    type = BitcoinBackendType.Esplora,
                    host = "https://example.invalid/api",
                    portText = "",
                    useTls = false,
                ),
            ),
        )

        assertEquals(BitcoinBackendConnectionTestState.SimulatedSuccess, result.state)
        assertEquals(BitcoinBackendType.Esplora, result.backendType)
        assertNoRealNetwork(result)
    }

    @Test
    fun missingSelectedProfileReturnsNotStartedBlocker() {
        val result = tester.run(request(null))

        assertEquals(BitcoinBackendConnectionTestState.NotStarted, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.NoSelectedProfile)
        assertNoRealNetwork(result)
    }

    @Test
    fun blankLabelInvalidProfileReturnsValidationFailure() {
        val result = tester.validateAndRun(
            validInput(label = " "),
            secureStorageCapability,
        )

        assertEquals(BitcoinBackendConnectionTestState.BlockedByInvalidProfile, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.InvalidProfile)
        assertTrue(result.findings.any { it.level == BitcoinBackendConnectionTestFindingLevel.Blocker })
        assertNoRealNetwork(result)
    }

    @Test
    fun missingHostReturnsValidationFailure() {
        val result = tester.validateAndRun(
            validInput(host = ""),
            secureStorageCapability,
        )

        assertEquals(BitcoinBackendConnectionTestState.BlockedByInvalidProfile, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.EndpointMissing)
        assertNoRealNetwork(result)
    }

    @Test
    fun invalidPortReturnsValidationFailure() {
        val result = tester.validateAndRun(
            validInput(portText = "70000"),
            secureStorageCapability,
        )

        assertEquals(BitcoinBackendConnectionTestState.BlockedByInvalidProfile, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.InvalidPort)
        assertNoRealNetwork(result)
    }

    @Test
    fun mainnetProfileIsBlocked() {
        val result = tester.validateAndRun(
            validInput(network = NetworkEnvironment.MainnetDisabled),
            secureStorageCapability,
        )

        assertEquals(BitcoinBackendConnectionTestState.BlockedByMainnetDisabled, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.MainnetDisabled)
        assertTrue(result.findings.any { it.detail.contains("Mainnet is disabled") })
        assertNoRealNetwork(result)
    }

    @Test
    fun endpointUserinfoCredentialLikeInputIsRejected() {
        val result = tester.validateAndRun(
            validInput(
                type = BitcoinBackendType.Esplora,
                host = "https://DEMO_VALUE_DO_NOT_USE@example.invalid/api",
                portText = "",
            ),
            secureStorageCapability,
        )

        assertEquals(BitcoinBackendConnectionTestState.BlockedByInvalidProfile, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.CredentialMaterialRejected)
        assertNoRealNetwork(result)
    }

    @Test
    fun credentialReferenceProfileIsBlockedBecauseSecureStorageIsDisabled() {
        val profile = validProfile(BitcoinBackendType.BitcoinCoreRpc).copy(
            credentialPolicy = BackendCredentialPolicy.CredentialReferenceOnly,
            credentialReference = CredentialReference("CREDENTIAL_STORAGE_NOT_IMPLEMENTED"),
        )
        val result = tester.run(request(profile))

        assertEquals(BitcoinBackendConnectionTestState.BlockedByCredentialsUnavailable, result.state)
        assertContains(result.blockingIssues, BitcoinBackendConnectionTestBlockingIssue.CredentialsUnavailable)
        assertTrue(result.findings.any { it.detail.contains("CREDENTIAL_STORAGE_NOT_IMPLEMENTED") })
        assertNoRealNetwork(result)
    }

    @Test
    fun simulatedResultIncludesMachineReadableNoNetworkFinding() {
        val result = tester.run(request(validProfile(BitcoinBackendType.Electrum)))

        assertTrue(result.noRealNetworkAttempted)
        assertTrue(
            result.findings.any {
                it.step == BitcoinBackendConnectionTestStep.NoRealNetworkAttempted &&
                    it.detail.contains("CONNECTION_TEST_NOT_REAL_NETWORK")
            },
        )
        assertContains(result.capabilities, BitcoinBackendConnectionTestCapability.NoRealNetworkAttempted)
        assertContains(result.capabilities, BitcoinBackendConnectionTestCapability.RealNetworkDisabled)
    }

    @Test
    fun simulatedResultsIncludeBackendTypeSpecificFutureChecks() {
        val core = tester.run(request(validProfile(BitcoinBackendType.BitcoinCoreRpc)))
        val electrum = tester.run(request(validProfile(BitcoinBackendType.Electrum)))
        val esplora = tester.run(request(validProfile(BitcoinBackendType.Esplora, host = "example.invalid", portText = "")))

        assertContains(
            core.findings.map { it.step },
            BitcoinBackendConnectionTestStep.FutureBitcoinCoreChainInfo,
        )
        assertContains(
            electrum.findings.map { it.step },
            BitcoinBackendConnectionTestStep.FutureElectrumServerFeatures,
        )
        assertContains(
            esplora.findings.map { it.step },
            BitcoinBackendConnectionTestStep.FutureEsploraTipHeight,
        )
        assertTrue((core.findings + electrum.findings + esplora.findings).any {
            it.level == BitcoinBackendConnectionTestFindingLevel.Planned
        })
    }

    @Test
    fun publicBackendProfileIncludesPrivacyWarning() {
        val result = tester.run(
            request(
                validProfile(
                    type = BitcoinBackendType.Esplora,
                    host = "example.invalid",
                    portText = "",
                    trustModel = BitcoinBackendTrustModel.PublicBackend,
                ),
            ),
        )

        assertEquals(BitcoinBackendConnectionTestState.SimulatedSuccess, result.state)
        assertTrue(result.warnings.any { it.contains("Public backends can observe wallet queries") })
        assertTrue(result.findings.any { it.step == BitcoinBackendConnectionTestStep.TrustPrivacyWarning })
    }

    @Test
    fun simulatedHarnessDeclaresRealNetworkDisabledForEveryBackendType() {
        BitcoinBackendType.entries.forEach { type ->
            val result = tester.run(request(validProfile(type)))

            assertEquals(BitcoinBackendConnectionTestMode.SimulatedOnly, result.mode)
            assertContains(result.capabilities, BitcoinBackendConnectionTestCapability.RealNetworkDisabled)
            assertTrue(result.noRealNetworkAttempted)
            assertFalse(result.findings.any { it.detail.contains("connected to", ignoreCase = true) })
        }
    }

    private fun request(
        profile: BitcoinBackendProfile?,
    ): BitcoinBackendConnectionTestRequest =
        BitcoinBackendConnectionTestRequest(
            profile = profile,
            mode = BitcoinBackendConnectionTestMode.SimulatedOnly,
            secureStorageCapability = secureStorageCapability,
        )

    private fun validProfile(
        type: BitcoinBackendType,
        host: String = when (type) {
            BitcoinBackendType.BitcoinCoreRpc -> "127.0.0.1"
            BitcoinBackendType.Electrum -> "example.invalid"
            BitcoinBackendType.Esplora -> "example.invalid"
        },
        portText: String = when (type) {
            BitcoinBackendType.BitcoinCoreRpc -> "18443"
            BitcoinBackendType.Electrum -> "50002"
            BitcoinBackendType.Esplora -> ""
        },
        useTls: Boolean = type != BitcoinBackendType.BitcoinCoreRpc,
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
    ): BitcoinBackendProfile {
        val validation = BitcoinBackendValidator.validate(
            validInput(
                type = type,
                host = host,
                portText = portText,
                useTls = useTls,
                trustModel = trustModel,
            ),
        )
        assertTrue(validation.errors.isEmpty(), validation.errors.joinToString { it.message })
        return assertNotNull(validation.normalizedProfile)
    }

    private fun validInput(
        label: String = "Simulated backend profile",
        type: BitcoinBackendType = BitcoinBackendType.BitcoinCoreRpc,
        network: NetworkEnvironment = NetworkEnvironment.Testnet4,
        host: String = "127.0.0.1",
        portText: String = "18443",
        useTls: Boolean = false,
        path: String = "",
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
    ): EditableBitcoinBackendProfileInput =
        EditableBitcoinBackendProfileInput(
            label = label,
            type = type,
            network = network,
            host = host,
            portText = portText,
            useTls = useTls,
            path = path,
            trustModel = trustModel,
        )

    private fun assertNoRealNetwork(
        result: com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestResult,
    ) {
        assertTrue(result.noRealNetworkAttempted)
        assertTrue(result.findings.any { it.step == BitcoinBackendConnectionTestStep.NoRealNetworkAttempted })
        assertContains(result.capabilities, BitcoinBackendConnectionTestCapability.RealNetworkDisabled)
    }
}
