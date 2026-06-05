package com.libertasprimordium.skald.demo

import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestHarness
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestPolicy
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestRequest
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestResult
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionWorkflow
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.security.SecureStorageCapability

class FakeBitcoinBackendConnectionTester(
    private val policy: BitcoinBackendConnectionTestPolicy = BitcoinBackendConnectionTestPolicy.SimulatedOnly,
) : BitcoinBackendConnectionTestHarness {
    override fun run(request: BitcoinBackendConnectionTestRequest): BitcoinBackendConnectionTestResult =
        BitcoinBackendConnectionWorkflow.run(
            request.copy(
                policy = policy,
                mode = policy.mode,
            ),
        )

    fun validateAndRun(
        input: EditableBitcoinBackendProfileInput,
        secureStorageCapability: SecureStorageCapability,
    ): BitcoinBackendConnectionTestResult =
        BitcoinBackendConnectionWorkflow.validateAndRun(
            input = input,
            secureStorageCapability = secureStorageCapability,
            policy = policy,
        )
}
