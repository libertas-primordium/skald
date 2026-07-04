package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseExecutionKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCasePurpose
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatCaseBindingTest {
    @Test
    fun katCaseBindingExistsOnlyAsCommonTestNonExecutableMetadata() {
        val binding = binding()

        assertTrue(binding.caseIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest, binding.sourceSet)
        assertTrue(binding.caseIsMetadataOnly)
        assertFalse(binding.caseIsExecutable)
        assertFalse(binding.runtimeSelectable)
        assertFalse(binding.productionProviderSelectable)
    }

    @Test
    fun katCaseBindingReadsExistingMarkerCatalogValidationAdmissionVectorFixtureVectorValidationAndCapabilityMatrix() {
        val binding = binding()

        assertEquals(1, binding.markerCount)
        assertEquals(1, binding.fixtureRowCount)
        assertEquals(1, binding.publicVectorRowCount)
        assertTrue(binding.expectedSafeIdMatched)
        assertTrue(binding.expectedFixtureIdMatched)
        assertTrue(binding.expectedVectorIdMatched)
    }

    @Test
    fun katCaseBindingBindsExactlyOneCase() {
        val binding = binding()

        assertEquals(1, binding.caseCount)
        assertTrue(binding.caseIsMetadataOnly)
        assertFalse(binding.caseIsExecutable)
    }

    @Test
    fun katCaseBindingReferencesExpectedMarkerSafeId() {
        val binding = binding()

        assertTrue(binding.expectedSafeIdMatched)
        assertEquals(markerSafeId(), binding.markerSafeId.value)
    }

    @Test
    fun katCaseBindingReferencesExpectedFixtureId() {
        val binding = binding()

        assertTrue(binding.expectedFixtureIdMatched)
        assertEquals(fixtureId(), binding.fixtureId.value)
    }

    @Test
    fun katCaseBindingReferencesExpectedVectorId() {
        val binding = binding()

        assertTrue(binding.expectedVectorIdMatched)
        assertEquals(vectorId(), binding.vectorId.value)
    }

    @Test
    fun katCaseBindingUsesExpectedCaseId() {
        val binding = binding()

        assertTrue(binding.expectedCaseIdMatched)
        assertEquals(caseId(), binding.caseId.value)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatCaseKind.PublicVectorMetadataCase, binding.caseKind)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatCasePurpose.InertIdentityMetadataOnly, binding.casePurpose)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatCaseExecutionKind.NonExecutableCaseBinding,
            binding.caseExecutionKind,
        )
    }

    @Test
    fun katCaseBindingIsMetadataOnlyAndNotExecutable() {
        val binding = binding()

        assertTrue(binding.caseIsMetadataOnly)
        assertFalse(binding.caseIsExecutable)
    }

    @Test
    fun katCaseBindingContainsNoRawBytesHexCryptoWalletEndpointOrProviderHandles() {
        val binding = binding()

        assertFalse(binding.caseContainsRawBytes)
        assertFalse(binding.caseContainsHex)
        assertFalse(binding.caseContainsCryptoMaterial)
        assertFalse(binding.caseContainsWalletMaterial)
        assertFalse(binding.caseContainsEndpointMaterial)
        assertFalse(binding.caseContainsProviderHandle)
    }

    @Test
    fun katCaseBindingIsNotProductionAuthorization() {
        assertFalse(binding().caseAuthorizesProduction)
    }

    @Test
    fun katCaseBindingIsNotProviderSelectionAuthorization() {
        assertFalse(binding().caseAuthorizesProviderSelection)
    }

    @Test
    fun katCaseBindingIsNotKatExecutionAuthorization() {
        assertFalse(binding().caseAuthorizesKatExecution)
    }

    @Test
    fun katCaseBindingIsNotCryptoAuthorization() {
        assertFalse(binding().caseAuthorizesCryptoExecution)
    }

    @Test
    fun katCaseBindingIsNotVaultPersistenceAuthorization() {
        assertFalse(binding().caseAuthorizesVaultPersistence)
    }

    @Test
    fun katCaseBindingIsNotMainnetAuthorization() {
        assertFalse(binding().caseAuthorizesMainnet)
    }

    @Test
    fun katCaseBindingDoesNotImplementVaultCryptoProvider() {
        assertFalse(binding().implementsVaultCryptoProvider)
    }

    @Test
    fun katCaseBindingDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(binding().containsVaultCryptoProvider)
    }

    @Test
    fun katCaseBindingIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.safeDetail.contains(caseId()))
        assertFalse(result.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityKatCaseBinding"))
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnly() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val binding = binding()

        assertFalse(binding.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val binding = binding()
        val blockedBooleans = listOf(
            binding.runtimeSelectable,
            binding.registrySelectable,
            binding.factoryReachable,
            binding.dispatcherReachable,
            binding.executorTargetable,
            binding.providerKatExecutorReachable,
            binding.providerOperationReachable,
            binding.cryptoExecutionReachable,
            binding.vaultLifecycleReachable,
            binding.persistenceReachable,
            binding.productionSyncReachable,
            binding.backendClientReachable,
            binding.bdkWalletStateReachable,
            binding.settingsCodecReachable,
            binding.uiSurfaceReachable,
            binding.signingBroadcastingReachable,
            binding.publicEndpointReachable,
            binding.mainnetReachable,
            binding.implementsVaultCryptoProvider,
            binding.containsVaultCryptoProvider,
            binding.canExecuteProviderOperations,
            binding.canExecuteCrypto,
            binding.canUseForVaultLifecycle,
            binding.canUseForPersistence,
            binding.canUseForSync,
            binding.canUseForSigning,
            binding.canUseForBroadcasting,
            binding.canUseForMainnet,
            binding.productionProviderSelectable,
            binding.caseIsExecutable,
            binding.caseContainsRawBytes,
            binding.caseContainsHex,
            binding.caseContainsCryptoMaterial,
            binding.caseContainsWalletMaterial,
            binding.caseContainsEndpointMaterial,
            binding.caseContainsProviderHandle,
            binding.caseAuthorizesProduction,
            binding.caseAuthorizesProviderSelection,
            binding.caseAuthorizesKatExecution,
            binding.caseAuthorizesCryptoExecution,
            binding.caseAuthorizesVaultPersistence,
            binding.caseAuthorizesMainnet,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun katCaseBindingIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val binding = binding()

        assertFalse(binding.registrySelectable)
        assertFalse(binding.factoryReachable)
        assertFalse(binding.dispatcherReachable)
        assertFalse(binding.executorTargetable)
        assertFalse(binding.providerKatExecutorReachable)
    }

    @Test
    fun katCaseBindingCannotExecuteProviderOperationsOrCrypto() {
        val binding = binding()

        assertFalse(binding.canExecuteProviderOperations)
        assertFalse(binding.canExecuteCrypto)
    }

    @Test
    fun katCaseBindingCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val binding = binding()

        assertFalse(binding.canUseForVaultLifecycle)
        assertFalse(binding.canUseForPersistence)
        assertFalse(binding.canUseForSync)
    }

    @Test
    fun katCaseBindingCannotSignBroadcastOrEnableMainnet() {
        val binding = binding()

        assertFalse(binding.canUseForSigning)
        assertFalse(binding.canUseForBroadcasting)
        assertFalse(binding.canUseForMainnet)
    }

    @Test
    fun katCaseBindingDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val binding = binding()
        val outputs = listOf(
            binding.toString(),
            binding.displayLabel.toString(),
            binding.caseId.toString(),
            binding.markerSafeId.toString(),
            binding.fixtureId.toString(),
            binding.vectorId.toString(),
        )
        val rejectedTerms = listOf(
            "raw KAT vector",
            "raw material",
            "provider handle",
            "crypto object",
            "storage reference",
            "backend reference",
            "endpoint reference",
            "wallet reference",
            "path",
            "descriptor",
            "source location",
            "implementation payload",
            "diagnostic payload",
            "public vector bytes",
            "public vector hex",
            "KAT vector bytes",
            "KAT vector hex",
            "fingerprint",
            "secret hash",
            "crash report",
            "analytics",
            "support export",
        )

        outputs.forEach { output ->
            assertFalse(output.contains(markerSafeId()))
            assertFalse(output.contains(fixtureId()))
            assertFalse(output.contains(vectorId()))
            assertFalse(output.contains(caseId()))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun katCaseBindingClassIdsAndCaseIdAreCommonTestOnlyBySourceGuardContract() {
        val binding = binding()

        assertTrue(binding.caseIsCommonTestOnly)
        assertEquals(1, binding.caseCount)
        assertTrue(binding.expectedSafeIdMatched)
        assertTrue(binding.expectedFixtureIdMatched)
        assertTrue(binding.expectedVectorIdMatched)
        assertTrue(binding.expectedCaseIdMatched)
        assertFalse(binding.caseAuthorizesKatExecution)
        assertFalse(binding.caseIsExecutable)
    }

    private fun binding() =
        SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy.currentKatCaseBinding()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
