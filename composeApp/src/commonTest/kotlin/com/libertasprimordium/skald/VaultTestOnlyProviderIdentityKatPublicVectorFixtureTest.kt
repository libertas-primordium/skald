package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixturePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorMaterialKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorPurpose
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatPublicVectorFixtureTest {
    @Test
    fun publicVectorFixtureExistsOnlyAsCommonTestPublicNonSecretMetadata() {
        val fixture = fixture()

        assertTrue(fixture.fixtureIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet.CommonTest, fixture.sourceSet)
        assertTrue(fixture.vectorIsPublicAndNonSecret)
        assertTrue(fixture.vectorIsTextOnly)
        assertFalse(fixture.vectorIsExecutable)
        assertFalse(fixture.runtimeSelectable)
        assertFalse(fixture.productionProviderSelectable)
    }

    @Test
    fun publicVectorFixtureReadsExistingMarkerCatalogValidationAndAdmissionGate() {
        val fixture = fixture()

        assertEquals(1, fixture.markerCount)
        assertEquals(1, fixture.fixtureCatalogCount)
        assertEquals(1, fixture.fixtureValidationCount)
        assertEquals(1, fixture.publicVectorAdmissionCount)
        assertEquals(1, fixture.fixtureRowCount)
    }

    @Test
    fun publicVectorFixtureContainsExactlyOnePublicVectorRow() {
        val fixture = fixture()

        assertEquals(1, fixture.publicVectorRowCount)
        assertEquals(1, fixture.publicVectorRows.size)
        assertTrue(fixture.fixtureContainsExactlyOnePublicVector)
    }

    @Test
    fun publicVectorRowReferencesExpectedMarkerSafeId() {
        val row = row()

        assertTrue(row.expectedSafeIdMatched)
        assertEquals(markerSafeId(), row.markerSafeId.value)
    }

    @Test
    fun publicVectorRowReferencesExpectedFixtureId() {
        val row = row()

        assertTrue(row.expectedFixtureIdMatched)
        assertEquals(fixtureId(), row.fixtureId.value)
    }

    @Test
    fun publicVectorRowUsesExpectedVectorId() {
        val row = row()

        assertTrue(row.expectedVectorIdMatched)
        assertEquals(vectorId(), row.vectorId.value)
    }

    @Test
    fun publicVectorRowIsPublicNonSecretTextOnlyMetadata() {
        val row = row()

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorKind.PublicNonSecretMetadataVector, row.vectorKind)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorPurpose.InertIdentityMetadataOnly, row.vectorPurpose)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorMaterialKind.TextOnlyNoRawCryptoMaterial,
            row.vectorMaterialKind,
        )
        assertTrue(row.vectorIsPublicAndNonSecret)
        assertTrue(row.vectorIsTextOnly)
        assertTrue(row.vectorIsCommonTestOnly)
    }

    @Test
    fun publicVectorRowIsNotExecutableAndContainsNoRawBytesHexOrSensitiveMaterialClasses() {
        val row = row()

        assertFalse(row.vectorIsExecutable)
        assertFalse(row.vectorContainsRawBytes)
        assertFalse(row.vectorContainsHex)
        assertFalse(row.vectorContainsCryptoMaterial)
        assertFalse(row.vectorContainsWalletMaterial)
        assertFalse(row.vectorContainsEndpointMaterial)
        assertFalse(row.vectorContainsProviderHandle)
    }

    @Test
    fun publicVectorFixtureIsNotProductionAuthorization() {
        assertFalse(fixture().fixtureIsProductionAuthorization)
        assertFalse(row().vectorAuthorizesProduction)
    }

    @Test
    fun publicVectorFixtureIsNotProviderSelectionAuthorization() {
        assertFalse(fixture().fixtureIsProviderSelectionAuthorization)
        assertFalse(row().vectorAuthorizesProviderSelection)
    }

    @Test
    fun publicVectorFixtureIsNotKatExecutionAuthorization() {
        assertFalse(fixture().fixtureIsKatExecutionAuthorization)
        assertFalse(row().vectorAuthorizesKatExecution)
    }

    @Test
    fun publicVectorFixtureIsNotCryptoAuthorization() {
        assertFalse(fixture().fixtureIsCryptoAuthorization)
        assertFalse(row().vectorAuthorizesCryptoExecution)
    }

    @Test
    fun publicVectorFixtureIsNotVaultPersistenceAuthorization() {
        assertFalse(fixture().fixtureIsVaultPersistenceAuthorization)
        assertFalse(row().vectorAuthorizesVaultPersistence)
    }

    @Test
    fun publicVectorFixtureIsNotMainnetAuthorization() {
        assertFalse(fixture().fixtureIsMainnetAuthorization)
        assertFalse(row().vectorAuthorizesMainnet)
    }

    @Test
    fun publicVectorFixtureDoesNotImplementVaultCryptoProvider() {
        assertFalse(fixture().implementsVaultCryptoProvider)
        assertFalse(row().implementsVaultCryptoProvider)
    }

    @Test
    fun publicVectorFixtureDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(fixture().containsVaultCryptoProvider)
        assertFalse(row().containsVaultCryptoProvider)
    }

    @Test
    fun publicVectorFixtureIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.candidates.any { candidate -> candidate.safeDetail.contains(vectorId()) })
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
        val fixture = fixture()

        assertFalse(fixture.productionProviderSelectable)
        assertFalse(row().productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val fixture = fixture()
        val blockedBooleans = listOf(
            fixture.runtimeSelectable,
            fixture.registrySelectable,
            fixture.factoryReachable,
            fixture.dispatcherReachable,
            fixture.executorTargetable,
            fixture.providerKatExecutorReachable,
            fixture.providerOperationReachable,
            fixture.cryptoExecutionReachable,
            fixture.vaultLifecycleReachable,
            fixture.persistenceReachable,
            fixture.productionSyncReachable,
            fixture.backendClientReachable,
            fixture.bdkWalletStateReachable,
            fixture.settingsCodecReachable,
            fixture.uiSurfaceReachable,
            fixture.signingBroadcastingReachable,
            fixture.publicEndpointReachable,
            fixture.mainnetReachable,
            fixture.implementsVaultCryptoProvider,
            fixture.containsVaultCryptoProvider,
            fixture.canExecuteProviderOperations,
            fixture.canExecuteCrypto,
            fixture.canUseForVaultLifecycle,
            fixture.canUseForPersistence,
            fixture.canUseForSync,
            fixture.canUseForSigning,
            fixture.canUseForBroadcasting,
            fixture.canUseForMainnet,
            fixture.productionProviderSelectable,
            fixture.vectorIsExecutable,
            fixture.vectorContainsRawBytes,
            fixture.vectorContainsHex,
            fixture.vectorContainsCryptoMaterial,
            fixture.vectorContainsWalletMaterial,
            fixture.vectorContainsEndpointMaterial,
            fixture.vectorContainsProviderHandle,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun publicVectorFixtureIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val fixture = fixture()

        assertFalse(fixture.registrySelectable)
        assertFalse(fixture.factoryReachable)
        assertFalse(fixture.dispatcherReachable)
        assertFalse(fixture.executorTargetable)
        assertFalse(fixture.providerKatExecutorReachable)
    }

    @Test
    fun publicVectorFixtureCannotExecuteProviderOperationsOrCrypto() {
        val fixture = fixture()

        assertFalse(fixture.canExecuteProviderOperations)
        assertFalse(fixture.canExecuteCrypto)
    }

    @Test
    fun publicVectorFixtureCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val fixture = fixture()

        assertFalse(fixture.canUseForVaultLifecycle)
        assertFalse(fixture.canUseForPersistence)
        assertFalse(fixture.canUseForSync)
    }

    @Test
    fun publicVectorFixtureCannotSignBroadcastOrEnableMainnet() {
        val fixture = fixture()

        assertFalse(fixture.canUseForSigning)
        assertFalse(fixture.canUseForBroadcasting)
        assertFalse(fixture.canUseForMainnet)
    }

    @Test
    fun publicVectorRowMatchesExistingCatalogFixtureRowWithoutChangingCatalogCardinality() {
        val catalogRow = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
            .fixtureRows
            .single()
        val row = row()

        assertEquals(catalogRow.fixtureId.value, row.fixtureId.value)
        assertEquals(catalogRow.markerSafeId.value, row.markerSafeId.value)
        assertEquals(1, row.fixtureRowCount)
        assertEquals(1, row.markerCount)
    }

    @Test
    fun publicVectorFixtureDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val fixture = fixture()
        val row = row()
        val outputs = listOf(
            fixture.toString(),
            fixture.displayLabel.toString(),
            row.toString(),
            row.displayLabel.toString(),
            row.vectorId.toString(),
            row.fixtureId.toString(),
            row.markerSafeId.toString(),
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
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun publicVectorFixtureClassIdsAndVectorIdAreCommonTestOnlyBySourceGuardContract() {
        val fixture = fixture()

        assertTrue(fixture.fixtureIsCommonTestOnly)
        assertEquals(1, fixture.publicVectorRowCount)
        assertTrue(fixture.expectedSafeIdMatched)
        assertTrue(fixture.expectedFixtureIdMatched)
        assertTrue(fixture.expectedVectorIdMatched)
        assertFalse(fixture.fixtureIsKatExecutionAuthorization)
        assertFalse(fixture.vectorIsExecutable)
    }

    private fun fixture() =
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixturePolicy.currentPublicVectorFixture()

    private fun row() =
        fixture().publicVectorRows.single()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
}
