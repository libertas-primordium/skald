package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatFixtureClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatNegativeVectorClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatPositiveVectorClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatRedactionVectorClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogResult
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorFutureWork
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderKatVectorSourceClass
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderKatVectorCatalogTest {
    @Test
    fun catalogIsModeledButStillDisabled() {
        val evidence = currentCatalog()

        assertEquals(
            "skald-vault-v1-test-only-provider-kat-vector-catalog-v1",
            SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.StillDisabled, evidence.status)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.CatalogModeled)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.StillDisabled)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.VectorReferencesOnly)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NoRawVectorMaterial)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NoExecutor)
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NoProviderExecution)
        assertTrue(evidence.catalogModeled)
        assertFalse(evidence.rawVectorMaterialPresent)
        assertFalse(evidence.executorImplemented)
        assertFalse(evidence.executorCallable)
        assertFalse(evidence.currentExecutionAuthorized)
        assertDisabled(evidence.disabledCapabilities)
    }

    @Test
    fun catalogContainsReferencesOnlyAndNoRawVectorMaterial() {
        val evidence = currentCatalog()

        evidence.sourceRows.forEach { row ->
            assertTrue(row.referenceOnly)
            assertFalse(row.rawMaterialPresent)
            assertFalse(row.currentExecutable)
            assertFalse(row.canAuthorizeExecution)
            assertFalse(row.canAuthorizeProduction)
            assertTrue(row.referenceId.value.startsWith("kat-source-"))
            assertTrue(row.provenanceId.value.startsWith("kat-provenance-"))
        }

        evidence.positiveVectorRows.forEach { row ->
            assertTrue(row.referenceOnly)
            assertFalse(row.rawMaterialPresent)
            assertFalse(row.currentExecutable)
            assertFalse(row.canAuthorizeExecution)
            assertFalse(row.canAuthorizeProduction)
            assertTrue(row.referenceId.value.startsWith("kat-"))
        }
        evidence.negativeVectorRows.forEach { row ->
            assertTrue(row.referenceOnly)
            assertFalse(row.rawMaterialPresent)
            assertFalse(row.currentExecutable)
            assertFalse(row.canAuthorizeExecution)
            assertFalse(row.canAuthorizeProduction)
            assertTrue(row.referenceId.value.startsWith("kat-"))
        }
        evidence.redactionVectorRows.forEach { row ->
            assertTrue(row.referenceOnly)
            assertFalse(row.rawMaterialPresent)
            assertFalse(row.currentExecutable)
            assertFalse(row.canAuthorizeExecution)
            assertFalse(row.canAuthorizeProduction)
            assertTrue(row.referenceId.value.startsWith("kat-"))
        }
        evidence.platformCheckVectorRows.forEach { row ->
            assertTrue(row.referenceOnly)
            assertFalse(row.rawMaterialPresent)
            assertFalse(row.currentExecutable)
            assertFalse(row.canAuthorizeExecution)
            assertFalse(row.canAuthorizeProduction)
            assertTrue(row.referenceId.value.startsWith("kat-"))
        }
    }

    @Test
    fun vectorClassesAreCataloged() {
        val evidence = currentCatalog()

        assertEquals(
            SkaldVaultV1TestOnlyProviderKatPositiveVectorClass.entries.toSet(),
            evidence.positiveVectorRows.map { it.vectorClass }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderKatNegativeVectorClass.entries.toSet(),
            evidence.negativeVectorRows.map { it.vectorClass }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderKatRedactionVectorClass.entries.toSet(),
            evidence.redactionVectorRows.map { it.vectorClass }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass.entries.toSet(),
            evidence.platformCheckVectorRows.map { it.vectorClass }.toSet(),
        )
        assertContains(
            evidence.positiveVectorRows.map { it.vectorClass },
            SkaldVaultV1TestOnlyProviderKatPositiveVectorClass.Argon2idKnownAnswer,
        )
        assertContains(
            evidence.negativeVectorRows.map { it.vectorClass },
            SkaldVaultV1TestOnlyProviderKatNegativeVectorClass.RawByteMaterialRejected,
        )
        assertContains(
            evidence.negativeVectorRows.map { it.vectorClass },
            SkaldVaultV1TestOnlyProviderKatNegativeVectorClass.WalletLikeFixtureRejected,
        )
        assertContains(
            evidence.redactionVectorRows.map { it.vectorClass },
            SkaldVaultV1TestOnlyProviderKatRedactionVectorClass.SafeVectorIdOnly,
        )
        assertContains(
            evidence.platformCheckVectorRows.map { it.vectorClass },
            SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass.BitcoinWalletLibraryUnrelated,
        )
    }

    @Test
    fun fixturePolicyAllowsOnlySafeFutureReferencesAndRejectsWalletOrMaterialClasses() {
        val rows = currentCatalog().fixtureClassRows.associateBy { it.fixtureClass }
        val allowed = setOf(
            SkaldVaultV1TestOnlyProviderKatFixtureClass.PublicNonWalletVectorReference,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.DeterministicSyntheticNonWalletReference,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.RuntimeGeneratedSyntheticNonWalletTestMaterial,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.RedactedExpectedOutcomeReference,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.SafeVectorId,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.SafeOperationLabel,
        )

        allowed.forEach { fixtureClass ->
            val row = rows.getValue(fixtureClass)
            assertTrue(row.futureAllowedAsReferenceOnly)
            assertFalse(row.forbidden)
            assertFalse(row.currentAccepted)
            assertFalse(row.payloadAllowed)
            assertFalse(row.canAuthorizeExecution)
            assertFalse(row.canAuthorizeProduction)
        }

        SkaldVaultV1TestOnlyProviderKatFixtureClass.entries
            .filter { it !in allowed }
            .forEach { fixtureClass ->
                val row = rows.getValue(fixtureClass)
                assertFalse(row.futureAllowedAsReferenceOnly)
                assertTrue(row.forbidden)
                assertFalse(row.currentAccepted)
                assertFalse(row.payloadAllowed)
                assertFalse(row.canAuthorizeExecution)
                assertFalse(row.canAuthorizeProduction)
            }

        listOf(
            SkaldVaultV1TestOnlyProviderKatFixtureClass.WalletSeed,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.MnemonicPhrase,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.Descriptor,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.XprvTprvWif,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.NostrNsec,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.LightningCredential,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.CashuProof,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.BackendCredential,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.RealAddress,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.RealTxid,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.Psbt,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.TransactionHex,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.WalletLabel,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.UtxoLabel,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.TransactionNote,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.BackendObservationMetadata,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.SecureMetadataRecord,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.ProductionVaultRecord,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.FileLocation,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.ProviderReference,
            SkaldVaultV1TestOnlyProviderKatFixtureClass.CryptoReference,
        ).forEach { fixtureClass ->
            assertTrue(rows.getValue(fixtureClass).forbidden)
        }
    }

    @Test
    fun catalogEntriesCannotAuthorizeExecutionProviderSelectionVaultLifecycleSyncSigningBroadcastingOrMainnet() {
        val evidence = currentCatalog()
        val rows = evidence.authorizationLimitRows.associateBy { it.limit }

        assertEquals(SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.entries.toSet(), rows.keys)
        SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.entries.forEach { limit ->
            val row = rows.getValue(limit)
            assertFalse(row.catalogEntryCanAuthorize)
            assertFalse(row.currentCatalogCanAuthorize)
            assertContains(
                row.blockers,
                SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.VectorCatalogEvidenceNonAuthorizing,
            )
        }

        listOf(
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.ExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.ExecutorExecution,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.ProviderOperationExecution,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.ProviderSelection,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.ProductionProviderSelectableTrue,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.VaultCreation,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.VaultUnlock,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.VaultPersistence,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.SecureStorageSuccess,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.SecureMetadataSuccess,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.ProductionSync,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.Signing,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.Broadcasting,
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.Mainnet,
        ).forEach { limit ->
            assertFalse(rows.getValue(limit).catalogEntryCanAuthorize)
            assertFalse(rows.getValue(limit).currentCatalogCanAuthorize)
        }
    }

    @Test
    fun disabledCapabilitiesAreFalseExceptCatalogModeled() {
        val capability = currentCatalog().disabledCapabilities

        assertDisabled(capability)
    }

    @Test
    fun redactedResultPolicyExposesSafeIdsAndLabelsOnly() {
        val evidence = currentCatalog()

        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.VectorIdsOnly)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.ProvenanceIdsOnly)
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.SafeOperationLabelsOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.SafeFailureCodesOnly,
        )
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoRawInput)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoRawOutput)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoKeyMaterial)
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoProviderReferences,
        )
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoCryptoReferences)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoFileLocations)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoWalletMetadata)
        assertContains(evidence.redactionClasses, SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NoBackendMetadata)
    }

    @Test
    fun catalogDistinguishesVectorProvenanceFromExecutionAuthorization() {
        val evidence = currentCatalog()
        val sources = evidence.sourceRows.associateBy { it.sourceClass }

        val rfc = sources.getValue(
            SkaldVaultV1TestOnlyProviderKatVectorSourceClass.Rfc9106Argon2idPublicVectorReference,
        )
        assertContains(
            rfc.provenanceStatuses,
            SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.PublicStandardsDocumentReference,
        )
        assertContains(
            rfc.provenanceStatuses,
            SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
        )
        assertFalse(rfc.canAuthorizeExecution)
        assertFalse(rfc.canAuthorizeProduction)

        val synthetic = sources.getValue(
            SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SyntheticNonWalletTestOnlyReference,
        )
        assertContains(
            synthetic.provenanceStatuses,
            SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SyntheticTestOnlyGeneratedAtRuntime,
        )
        assertContains(
            synthetic.provenanceStatuses,
            SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.TestOnlyProvenance,
        )
        assertFalse(synthetic.currentExecutable)
        assertFalse(synthetic.canAuthorizeExecution)
        assertFalse(synthetic.canAuthorizeProduction)

        val summary = SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy.currentPolicySummary()
        assertContains(
            summary.provenanceStatuses,
            SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.RejectedWalletLikeProvenance,
        )
    }

    @Test
    fun futureRequiredWorkIsExplicitWithoutCompletingIt() {
        val evidence = currentCatalog()
        val rows = evidence.futureRequiredWork.associateBy { it.work }

        assertEquals(SkaldVaultV1TestOnlyProviderKatVectorFutureWork.entries.toSet(), rows.keys)
        rows.values.forEach { row ->
            assertTrue(row.requiredBeforeExecutorBranch)
            assertTrue(row.requiredBeforeProductionSelection)
            assertFalse(row.currentBranchCompletesWork)
            assertTrue(row.safeLabel.value.isNotBlank())
        }
        assertContains(rows.keys, SkaldVaultV1TestOnlyProviderKatVectorFutureWork.TestOnlyExecutorBranchRequired)
        assertContains(rows.keys, SkaldVaultV1TestOnlyProviderKatVectorFutureWork.NoWalletFixtureReviewRequired)
        assertContains(
            rows.keys,
            SkaldVaultV1TestOnlyProviderKatVectorFutureWork.ReleaseMainnetNonAuthorizationReviewRequired,
        )
    }

    @Test
    fun warningConsentAndReleaseClaimsCannotOverrideCatalogBlockers() {
        val request = SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest(
            includeFutureReferences = true,
            warningOnlyEvidenceClaimed = true,
            userConsentOverrideRequested = true,
            releaseEvidenceClaimed = true,
            safeCatalogId = "raw-path-provider-handle-crypto-object-payload-marker",
        )
        val evidence = blocked(SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy.evaluateCatalog(request))

        assertFalse(evidence.currentExecutionAuthorized)
        assertFalse(evidence.executorCallable)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(evidence.disabledCapabilities.canSetProductionProviderSelectable)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultCreation)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultUnlock)
        assertFalse(evidence.disabledCapabilities.canAuthorizeVaultPersistence)
        assertFalse(evidence.disabledCapabilities.canAuthorizeProductionSync)
        assertFalse(evidence.disabledCapabilities.canAuthorizeSigning)
        assertFalse(evidence.disabledCapabilities.canAuthorizeBroadcasting)
        assertFalse(evidence.disabledCapabilities.canAuthorizeMainnet)
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.WarningOnlyEvidenceNonAuthorizing,
        )
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.UserConsentCannotOverride)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ReleaseReviewMissing)
        assertContains(evidence.blockers, SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.MainnetDisabled)
    }

    @Test
    fun requestRenderingIsRedactedAndDoesNotExposeRawPayloadsOrHandles() {
        val request = SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest.currentCatalog(
            safeCatalogId = "raw-path-provider-handle-crypto-object-payload-marker",
        )
        val rendered = request.toString()

        assertFalse(rendered.contains("raw-path-provider-handle-crypto-object-payload-marker"))
        assertFalse(rendered.contains("/tmp/"))
        assertFalse(rendered.contains("provider handle"))
        assertFalse(rendered.contains("crypto object"))
        assertTrue(rendered.contains("safeCatalogId=<redacted>"))
        assertTrue(rendered.contains("rawVector=<redacted>"))
        assertTrue(rendered.contains("providerReference=<redacted>"))
        assertTrue(rendered.contains("cryptoReference=<redacted>"))
        assertTrue(rendered.contains("fileLocation=<redacted>"))
    }

    @Test
    fun providerSelectionStillReturnsOnlyDisabledProvider() {
        val evidence = currentCatalog()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(evidence.providerSelectionDisabledProviderOnly)
        assertFalse(evidence.productionProviderSelectable)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(selection.candidates.none { it.productionSelectable })
        assertFalse(selection.selectedProvider.statusReport.canDeriveKeys)
        assertFalse(selection.selectedProvider.statusReport.canEncryptRecords)
        assertFalse(selection.selectedProvider.statusReport.productionPersistenceEnabled)
        assertFalse(selection.selectedProvider.statusReport.mainnetEnabled)
    }

    @Test
    fun blockedResultWrapperRemainsFailClosed() {
        val result = SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy.evaluateCatalog()

        assertIs<SkaldVaultV1TestOnlyProviderKatVectorCatalogResult.Blocked<SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence>>(
            result,
        )
        assertFalse(result.value.currentExecutionAuthorized)
        assertFalse(result.value.disabledCapabilities.canRunVectorsNow)
        assertContains(result.value.blockers, SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoExecutorCallableSurface)
    }

    private fun currentCatalog(): SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence =
        blocked(
            SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy.evaluateCatalog(
                SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest.currentCatalog(),
            ),
        )

    private fun blocked(
        result: SkaldVaultV1TestOnlyProviderKatVectorCatalogResult<
            SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence,
        >,
    ): SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence =
        when (result) {
            is SkaldVaultV1TestOnlyProviderKatVectorCatalogResult.Blocked -> result.value
        }

    private fun assertDisabled(capability: SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability) {
        assertTrue(capability.catalogModeled)
        assertFalse(capability.rawVectorMaterialPresent)
        assertFalse(capability.executorImplemented)
        assertFalse(capability.executorCallable)
        assertFalse(capability.canRunVectorsNow)
        assertFalse(capability.canExecuteProviderOperations)
        assertFalse(capability.canExecuteRandomness)
        assertFalse(capability.canExecuteKdf)
        assertFalse(capability.canExecuteAead)
        assertFalse(capability.canExecuteHkdf)
        assertFalse(capability.canExecuteHmac)
        assertFalse(capability.canGenerateKeys)
        assertFalse(capability.canStoreKeysets)
        assertFalse(capability.canAuthorizeProviderSelection)
        assertFalse(capability.canSetProductionProviderSelectable)
        assertFalse(capability.canAuthorizeVaultCreation)
        assertFalse(capability.canAuthorizeVaultUnlock)
        assertFalse(capability.canAuthorizeVaultPersistence)
        assertFalse(capability.canAuthorizeSecureStorageSuccess)
        assertFalse(capability.canAuthorizeSecureMetadataSuccess)
        assertFalse(capability.canAuthorizeProductionSync)
        assertFalse(capability.canAuthorizeSigning)
        assertFalse(capability.canAuthorizeBroadcasting)
        assertFalse(capability.canAuthorizeMainnet)
    }
}
