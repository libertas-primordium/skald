package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderDisabledStorageServiceFacadeRule
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolverInputSnapshot
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionArtifactKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlan
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlanPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyEvidenceKind
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightRequest
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageFacadeStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStoragePayloadPlaceholder
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStoragePayloadPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageRecordDescriptor
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageRecordDescriptorResult
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultDisabledStorageServiceFacadeTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()
    private val linuxFixtureRoot = "/home/skald-test-user/.local/share"

    @Test
    fun disabledStorageServiceFacadePolicyIdAndStatusAreStable() {
        assertEquals(
            "skald-vault-v1-disabled-storage-service-facade-v1",
            SkaldVaultV1DisabledStorageServiceFacade.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1DisabledStorageServiceFacade.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultStorageFacadeStatus.StillDisabled,
            SkaldVaultV1DisabledStorageServiceFacade.status(),
        )
        assertFalse(SkaldVaultV1DisabledStorageServiceFacade.status().operationsEnabled)
        assertFalse(SkaldVaultV1DisabledStorageServiceFacade.status().persistenceAvailable)
        assertFalse(SkaldVaultV1DisabledStorageServiceFacade.status().providerSelectable)
        assertEquals(
            SkaldVaultV1VaultStorageOperation.entries.toSet(),
            SkaldVaultV1DisabledStorageServiceFacade.modeledOperations(),
        )
    }

    @Test
    fun everyModeledOperationReturnsDisabledAndNeverSuccess() {
        val artifactEvidence = androidPlannedArtifactEvidence()
        val preflightEvidence = acceptedPreflight(artifactEvidence)

        SkaldVaultV1VaultStorageOperation.entries.forEach { operation ->
            val result = SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
                    operation = operation,
                    artifactEvidence = artifactEvidence,
                    preflightEvidence = preflightEvidence,
                    recordDescriptor = safeRecordDescriptor(),
                    payload = disabledPayload(),
                ),
            )
            val evidence = assertDisabled(result)

            assertEquals(operation, evidence.operation)
            assertEquals(SkaldVaultV1VaultStorageOperationStatus.StorageOperationDisabled, evidence.operationStatus)
            assertFalse(result.success)
            assertTrue(evidence.noOperationReturnsSuccess)
            assertTrue(evidence.storageServiceOperationsFailClosed)
            assertTrue(evidence.plannedArtifactLocationEvidenceConsumed)
            assertTrue(evidence.storageSafetyPreflightEvidenceConsumed)
            assertContains(evidence.blockers, SkaldVaultV1VaultStorageOperationBlocker.AcceptedButFileIoBlocked)
            assertContains(evidence.blockers, SkaldVaultV1VaultStorageOperationBlocker.AcceptedButPersistenceBlocked)
            assertDisabledCapabilities(evidence.capability)
        }
    }

    @Test
    fun futureStorageOperationsAreAllModeledAsDisabled() {
        val expected = setOf(
            SkaldVaultV1VaultStorageOperation.InitializeStorageNamespace,
            SkaldVaultV1VaultStorageOperation.ReadCurrentContainer,
            SkaldVaultV1VaultStorageOperation.WriteCurrentContainer,
            SkaldVaultV1VaultStorageOperation.ReadCurrentManifest,
            SkaldVaultV1VaultStorageOperation.WriteCurrentManifest,
            SkaldVaultV1VaultStorageOperation.ReadStorageIndex,
            SkaldVaultV1VaultStorageOperation.WriteStorageIndex,
            SkaldVaultV1VaultStorageOperation.ReadRecord,
            SkaldVaultV1VaultStorageOperation.WriteRecord,
            SkaldVaultV1VaultStorageOperation.ListRecordDescriptors,
            SkaldVaultV1VaultStorageOperation.DeleteRecord,
            SkaldVaultV1VaultStorageOperation.QuarantineRecord,
            SkaldVaultV1VaultStorageOperation.RecoverInterruptedWrite,
            SkaldVaultV1VaultStorageOperation.RunStorageHealthCheck,
            SkaldVaultV1VaultStorageOperation.MapStorageFailure,
            SkaldVaultV1VaultStorageOperation.PrepareAtomicWrite,
            SkaldVaultV1VaultStorageOperation.CommitAtomicWrite,
            SkaldVaultV1VaultStorageOperation.RollbackAtomicWrite,
            SkaldVaultV1VaultStorageOperation.VerifyAntiRollbackFreshnessEvidence,
            SkaldVaultV1VaultStorageOperation.CloseLockStorageSession,
        )

        assertEquals(expected, SkaldVaultV1DisabledStorageServiceFacade.modeledOperations())
    }

    @Test
    fun missingRejectedAndWarningOnlyEvidenceCannotEnableStorage() {
        val artifactEvidence = linuxDefaultPlannedArtifactEvidence()
        val preflightEvidence = acceptedPreflight(artifactEvidence)
        val noEvidence = assertDisabled(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.noEvidence(
                    SkaldVaultV1VaultStorageOperation.ReadRecord,
                ),
            ),
        )
        val missingArtifact = assertDisabled(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
                    operation = SkaldVaultV1VaultStorageOperation.WriteRecord,
                    artifactEvidence = null,
                    preflightEvidence = preflightEvidence,
                ),
            ),
        )
        val missingPreflight = assertDisabled(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
                    operation = SkaldVaultV1VaultStorageOperation.ReadCurrentManifest,
                    artifactEvidence = artifactEvidence,
                    preflightEvidence = null,
                ),
            ),
        )
        val rejectedArtifact = SkaldVaultV1PlatformPathConstructionPolicy.plan(
            SkaldVaultV1PlatformPathConstructionRequest.rawPlatformPathCandidate("/tmp/skald-vault"),
        )
        val rejectedPreflight = SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
            SkaldVaultV1StorageSafetyPreflightRequest.fromPathConstructionResult(rejectedArtifact),
        )
        val rejected = assertDisabled(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.fromBoundaryResults(
                    operation = SkaldVaultV1VaultStorageOperation.InitializeStorageNamespace,
                    artifactResult = rejectedArtifact,
                    preflightResult = rejectedPreflight,
                ),
            ),
        )
        val warningOnly = acceptedPreflight(
            plannedArtifactEvidence = artifactEvidence,
            safetyEvidence = listOf(
                SkaldVaultV1StorageSafetyEvidence.warningOnly(
                    SkaldVaultV1StorageSafetyEvidenceKind.DurabilityCheckReviewed,
                ),
                SkaldVaultV1StorageSafetyEvidence.userConsentOnly(
                    SkaldVaultV1StorageSafetyEvidenceKind.StorageFailureMappingReviewed,
                ),
            ),
        )
        val warningOnlyFacade = assertDisabled(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
                    operation = SkaldVaultV1VaultStorageOperation.CommitAtomicWrite,
                    artifactEvidence = artifactEvidence,
                    preflightEvidence = warningOnly,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultStorageOperationStatus.NoEvidenceAvailable, noEvidence.operationStatus)
        assertContains(noEvidence.blockers, SkaldVaultV1VaultStorageOperationBlocker.MissingArtifactLocationEvidence)
        assertContains(
            noEvidence.blockers,
            SkaldVaultV1VaultStorageOperationBlocker.MissingStorageSafetyPreflightEvidence,
        )
        assertEquals(
            SkaldVaultV1VaultStorageOperationStatus.MissingArtifactLocationEvidence,
            missingArtifact.operationStatus,
        )
        assertContains(
            missingArtifact.blockers,
            SkaldVaultV1VaultStorageOperationBlocker.MissingArtifactLocationEvidence,
        )
        assertEquals(
            SkaldVaultV1VaultStorageOperationStatus.MissingStorageSafetyPreflightEvidence,
            missingPreflight.operationStatus,
        )
        assertContains(
            missingPreflight.blockers,
            SkaldVaultV1VaultStorageOperationBlocker.MissingStorageSafetyPreflightEvidence,
        )
        assertEquals(
            SkaldVaultV1VaultStorageOperationStatus.ArtifactLocationEvidenceRejected,
            rejected.operationStatus,
        )
        assertContains(rejected.blockers, SkaldVaultV1VaultStorageOperationBlocker.RejectedArtifactLocationEvidence)
        assertContains(warningOnly.blockers, SkaldVaultV1StorageSafetyBlocker.WarningOnlyEvidenceRejected)
        assertContains(warningOnly.blockers, SkaldVaultV1StorageSafetyBlocker.UserConsentOverrideRejected)
        assertContains(
            warningOnlyFacade.blockers,
            SkaldVaultV1VaultStorageOperationBlocker.WarningOnlyEvidenceRejected,
        )
        assertContains(
            warningOnlyFacade.blockers,
            SkaldVaultV1VaultStorageOperationBlocker.UserConsentOverrideRejected,
        )
        assertFalse(warningOnlyFacade.capability.usableForPersistence)
    }

    @Test
    fun androidLinuxAndCustomArtifactPreflightEvidenceRemainDisabled() {
        val android = disabledFrom(androidPlannedArtifactEvidence())
        val linuxDefault = disabledFrom(linuxDefaultPlannedArtifactEvidence())
        val linuxCustom = disabledFrom(linuxCustomPlannedArtifactEvidence())

        assertEquals(SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal, android.locations().first().rootKind)
        assertEquals(SkaldVaultV1PlatformRootKind.LinuxXdgDataHome, linuxDefault.locations().first().rootKind)
        assertEquals(SkaldVaultV1PlatformRootKind.LinuxCustomCandidate, linuxCustom.locations().first().rootKind)
        listOf(android, linuxDefault, linuxCustom).forEach { evidence ->
            assertTrue(evidence.locations().isNotEmpty())
            assertEquals(
                SkaldVaultV1PlatformPathConstructionArtifactKind.entries
                    .filterNot { it == SkaldVaultV1PlatformPathConstructionArtifactKind.ExplicitSegmentProbe }
                    .toSet(),
                evidence.locations().map { it.artifactKind }.toSet(),
            )
            evidence.locations().forEach { location ->
                assertFalse(location.platformPathConstructed)
                assertFalse(location.usableForFileIo)
                assertFalse(location.usableForPersistence)
                assertFalse(location.manifestReadWriteAvailable)
                assertFalse(location.storageIndexReadWriteAvailable)
                assertFalse(location.recordReadWriteAvailable)
            }
            assertDisabledCapabilities(evidence.capability)
            assertFalse(evidence.capability.providerSelectable)
            assertFalse(evidence.capability.vaultPersistenceAvailable)
        }
    }

    @Test
    fun redactedRenderingDoesNotExposeRootLocationRecordOrPayloadData() {
        val artifactEvidence = linuxDefaultPlannedArtifactEvidence()
        val preflightEvidence = acceptedPreflight(artifactEvidence)
        val recordId = "safe_record_fixture"
        val descriptor = SkaldVaultV1VaultStorageRecordDescriptor.safeTestFixture(recordId)
        val payload = disabledPayload()
        val request = SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
            operation = SkaldVaultV1VaultStorageOperation.WriteRecord,
            artifactEvidence = artifactEvidence,
            preflightEvidence = preflightEvidence,
            recordDescriptor = descriptor,
            payload = payload,
        )
        val evidence = assertDisabled(SkaldVaultV1DisabledStorageServiceFacade.perform(request))
        val secretLike = "storage-password-token-sentinel"
        val longHex = "0123456789abcdef0123456789abcdef" +
            "0123456789abcdef0123456789abcdef"
        val rejectedSecret = SkaldVaultV1DisabledStorageServiceFacade.perform(
            SkaldVaultV1VaultStorageOperationRequest.rawStorageInputCandidate(
                SkaldVaultV1VaultStorageOperation.WriteRecord,
                secretLike,
            ),
        )
        val rejectedHex = SkaldVaultV1DisabledStorageServiceFacade.perform(
            SkaldVaultV1VaultStorageOperationRequest.rawStorageInputCandidate(
                SkaldVaultV1VaultStorageOperation.WriteRecord,
                longHex,
            ),
        )

        assertEquals(recordId, descriptor.testOnlyRecordIdentifier())
        listOf(
            request.toString(),
            evidence.toString(),
            evidence.token.toString(),
            descriptor.toString(),
            payload.toString(),
            rejectedSecret.toString(),
            rejectedHex.toString(),
        ).forEach { rendered ->
            assertRedacted(rendered, linuxFixtureRoot)
            assertRedacted(rendered, "vault_202122232425262728292a2b2c2d2e2f")
            assertRedacted(rendered, recordId)
            assertRedacted(rendered, secretLike)
            assertRedacted(rendered, longHex)
        }
        assertFalse(evidence.token.containsPlatformLocationText)
        assertFalse(evidence.token.containsRecordIdentifier)
        assertFalse(evidence.token.containsPayload)
        assertFalse(payload.containsPlaintext)
        assertFalse(payload.containsCiphertext)
        assertFalse(payload.byteLengthExposed)
    }

    @Test
    fun operationCapabilitiesRemainUnavailable() {
        val evidence = disabledFrom(androidPlannedArtifactEvidence())

        assertDisabledCapabilities(evidence.capability)
        assertFalse(evidence.platformPathObjectReturned)
        assertFalse(evidence.payloadExposedByDefault)
        assertTrue(evidence.disabledStorageServiceFacadeModeled)
        assertTrue(evidence.storageServiceFacadeStillDisabled)
        assertTrue(evidence.storageServiceDoesNotUseFilesystem)
        assertTrue(evidence.storageServiceDoesNotEnablePersistence)
        assertTrue(evidence.storageServiceDoesNotEnableProviderSelection)
        assertTrue(evidence.storageOperationFailureVocabularyModeled)
    }

    @Test
    fun rawStorageInputsAndRecordDescriptorsAreRejectedWithTypedReasons() {
        val bitcoinAddressLikeSentinel = "bc" + "1invalidstoragefacadesentinel0000000000"
        val nostrSecretLikeSentinel = "nsec" + "1invalidstoragefacadesentinel0000000000"
        val longHex = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
        val wifLikeSentinel = "K" + "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrs"

        mapOf(
            "" to SkaldVaultV1VaultStorageOperationFailureReason.EmptyRecordIdRejected,
            "   " to SkaldVaultV1VaultStorageOperationFailureReason.EmptyRecordIdRejected,
            "/home/skald-test-user/.local/share" to
                SkaldVaultV1VaultStorageOperationFailureReason.RawAbsoluteLocationInputRejected,
            "relative/path" to SkaldVaultV1VaultStorageOperationFailureReason.RawRelativeLocationInputRejected,
            "file://skald-vault" to SkaldVaultV1VaultStorageOperationFailureReason.LinkLikeInputRejected,
            "https://example.invalid/skald" to SkaldVaultV1VaultStorageOperationFailureReason.LinkLikeInputRejected,
            "content://example/skald" to SkaldVaultV1VaultStorageOperationFailureReason.LinkLikeInputRejected,
            "C:\\skald\\vault" to SkaldVaultV1VaultStorageOperationFailureReason.RawAbsoluteLocationInputRejected,
            "\\\\server\\share" to SkaldVaultV1VaultStorageOperationFailureReason.RawAbsoluteLocationInputRejected,
            "file-object-like-value" to SkaldVaultV1VaultStorageOperationFailureReason.PlatformObjectLikeInputRejected,
            "path-object-like-value" to SkaldVaultV1VaultStorageOperationFailureReason.PlatformObjectLikeInputRejected,
            "stream-like-value" to SkaldVaultV1VaultStorageOperationFailureReason.PlatformObjectLikeInputRejected,
            "database-like-value" to SkaldVaultV1VaultStorageOperationFailureReason.PlatformObjectLikeInputRejected,
            "segment..traversal" to SkaldVaultV1VaultStorageOperationFailureReason.TraversalRejected,
            "contains-password-sentinel" to SkaldVaultV1VaultStorageOperationFailureReason.SecretMaterialRejected,
            "contains-token-sentinel" to SkaldVaultV1VaultStorageOperationFailureReason.SecretMaterialRejected,
            "user:pass@example" to SkaldVaultV1VaultStorageOperationFailureReason.SecretMaterialRejected,
            "xprv-invalid-storage-sentinel" to SkaldVaultV1VaultStorageOperationFailureReason.WalletMaterialRejected,
            "tprv-invalid-storage-sentinel" to SkaldVaultV1VaultStorageOperationFailureReason.WalletMaterialRejected,
            wifLikeSentinel to SkaldVaultV1VaultStorageOperationFailureReason.WalletMaterialRejected,
            nostrSecretLikeSentinel to SkaldVaultV1VaultStorageOperationFailureReason.WalletMaterialRejected,
            bitcoinAddressLikeSentinel to
                SkaldVaultV1VaultStorageOperationFailureReason.BitcoinAddressLikeRecordIdRejected,
            longHex to SkaldVaultV1VaultStorageOperationFailureReason.TransactionLikeRecordIdRejected,
            "plaintext-record-payload-sentinel" to
                SkaldVaultV1VaultStorageOperationFailureReason.PlaintextPayloadRejected,
            "ciphertext-record-payload-sentinel" to
                SkaldVaultV1VaultStorageOperationFailureReason.CiphertextPayloadRejected,
            "unsupported:colon" to SkaldVaultV1VaultStorageOperationFailureReason.UnsupportedRecordIdRejected,
            "unsupported space" to SkaldVaultV1VaultStorageOperationFailureReason.UnsupportedRecordIdRejected,
        ).forEach { (candidate, expectedReason) ->
            val operationResult = SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.rawStorageInputCandidate(
                    operation = SkaldVaultV1VaultStorageOperation.WriteRecord,
                    rawCandidate = candidate,
                ),
            )
            val descriptorResult = SkaldVaultV1VaultStorageRecordDescriptor.rawCandidate(candidate)

            assertRejected(operationResult, expectedReason)
            assertTrue(descriptorResult is SkaldVaultV1VaultStorageRecordDescriptorResult.Rejected)
            assertEquals(expectedReason, descriptorResult.reason)
            if (candidate.isNotBlank()) {
                assertRedacted(operationResult.toString(), candidate)
                assertRedacted(descriptorResult.toString(), candidate)
            }
        }
    }

    @Test
    fun readinessProviderAcceptanceDependencyAndSelectionRepresentFacadeButRemainDisabled() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            SkaldVaultV1DisabledStorageServiceFacade.POLICY_ID,
            storageContract.disabledStorageServiceFacadePolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.disabledStorageServiceFacadeStatus,
        )
        assertEquals(
            ProductionProviderDisabledStorageServiceFacadeRule.entries.toSet(),
            storageContract.disabledStorageServiceFacadeRules,
        )
        assertTrue(storageContract.disabledStorageServiceFacadeModeled)
        assertTrue(storageContract.storageServiceFacadeStillDisabled)
        assertTrue(storageContract.storageServiceOperationsFailClosed)
        assertTrue(storageContract.storageServiceDoesNotUseFilesystem)
        assertTrue(storageContract.storageServiceDoesNotEnablePersistence)
        assertTrue(storageContract.storageServiceDoesNotEnableProviderSelection)
        assertTrue(storageContract.storageOperationFailureVocabularyModeled)
        assertFalse(storageContract.storageServiceOperationSuccessPathImplemented)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertFalse(storageContract.manifestReadWriteImplemented)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.platformStorageImplementationAdded)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(
                ProductionProviderAcceptanceGate.DisabledStorageServiceFacadeImplementedAndTested,
            ),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.DisabledStorageServiceFacadeImplementedAndTested
            ],
        )
        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.DisabledStorageServiceFacadeBoundaryBuildingBlock,
        )
        assertFalse(readiness.productionPersistenceEnabled)

        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.DisabledStorageServiceFacadeImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.StorageOperationFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.DisabledStorageServiceFacadeStillDisabled,
        )
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun disabledFrom(
        artifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence,
    ) = assertDisabled(
        SkaldVaultV1DisabledStorageServiceFacade.perform(
            SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
                operation = SkaldVaultV1VaultStorageOperation.ReadRecord,
                artifactEvidence = artifactEvidence,
                preflightEvidence = acceptedPreflight(artifactEvidence),
                recordDescriptor = safeRecordDescriptor(),
            ),
        ),
    )

    private fun acceptedPreflight(
        plannedArtifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence,
        safetyEvidence: List<SkaldVaultV1StorageSafetyEvidence> = emptyList(),
    ): SkaldVaultV1StorageSafetyPreflightEvidence =
        when (
            val result = SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
                SkaldVaultV1StorageSafetyPreflightRequest.fromArtifactLocationEvidence(
                    artifactEvidence = plannedArtifactEvidence,
                    safetyEvidence = safetyEvidence,
                ),
            )
        ) {
            is SkaldVaultV1StorageSafetyPreflightResult.Accepted -> result.value
            is SkaldVaultV1StorageSafetyPreflightResult.Rejected -> error(result.safeMessage)
        }

    private fun androidPlannedArtifactEvidence(): SkaldVaultV1PlatformPathConstructionEvidence =
        plannedArtifactEvidence(
            acceptedRootEvidence(
                SkaldVaultV1PlatformRootResolverPolicy.resolve(
                    SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence(
                        staticEvidence = "android-app-private-internal-test-evidence",
                    ),
                ),
            ),
        )

    private fun linuxDefaultPlannedArtifactEvidence(): SkaldVaultV1PlatformPathConstructionEvidence =
        plannedArtifactEvidence(
            acceptedRootEvidence(
                SkaldVaultV1PlatformRootResolverPolicy.resolve(
                    SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                        SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                            xdgDataHomeEvidence = linuxFixtureRoot,
                        ),
                    ),
                ),
            ),
        )

    private fun linuxCustomPlannedArtifactEvidence(): SkaldVaultV1PlatformPathConstructionEvidence {
        val custom = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(
            "/srv/skald-test-vaults/user-data",
        )
        return plannedArtifactEvidence(
            acceptedRootEvidence(
                SkaldVaultV1PlatformRootResolverPolicy.resolve(
                    SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootValidationResult(custom),
                ),
            ),
        )
    }

    private fun plannedArtifactEvidence(
        rootEvidence: SkaldVaultV1PlatformRootResolverEvidence,
    ): SkaldVaultV1PlatformPathConstructionEvidence =
        when (
            val result = SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = rootEvidence,
                    layoutPlan = fixedLayout(),
                ),
            )
        ) {
            is SkaldVaultV1PlatformPathConstructionResult.Accepted -> result.value
            is SkaldVaultV1PlatformPathConstructionResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedRootEvidence(
        result: SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>,
    ): SkaldVaultV1PlatformRootResolverEvidence =
        when (result) {
            is SkaldVaultV1PlatformRootResolverResult.Accepted -> result.value
            is SkaldVaultV1PlatformRootResolverResult.Rejected -> error(result.safeMessage)
        }

    private fun fixedLayout(): SkaldVaultV1StorageLayoutPlan =
        when (
            val result = SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(fixedRecordId),
            )
        ) {
            is SkaldVaultV1StorageLayoutResult.Accepted -> result.value
            is SkaldVaultV1StorageLayoutResult.Rejected -> error(result.safeMessage)
        }

    private fun safeRecordDescriptor(): SkaldVaultV1VaultStorageRecordDescriptor =
        SkaldVaultV1VaultStorageRecordDescriptor.safeTestFixture("safe_record_fixture")

    private fun disabledPayload(): SkaldVaultV1VaultStoragePayloadPlaceholder =
        SkaldVaultV1VaultStoragePayloadPlaceholder.disabledPlaceholder(
            SkaldVaultV1VaultStoragePayloadPurpose.DisabledRecordWritePlaceholder,
        )

    private fun assertDisabled(
        result: SkaldVaultV1VaultStorageOperationResult<*>,
    ): com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageDisabledEvidence {
        assertTrue(result is SkaldVaultV1VaultStorageOperationResult.Disabled)
        assertFalse(result.success)
        return result.value as com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageDisabledEvidence
    }

    private fun assertRejected(
        result: SkaldVaultV1VaultStorageOperationResult<*>,
        expectedReason: SkaldVaultV1VaultStorageOperationFailureReason,
    ) {
        assertTrue(result is SkaldVaultV1VaultStorageOperationResult.Rejected)
        assertFalse(result.success)
        assertEquals(expectedReason, result.reason)
        assertEquals(SkaldVaultV1VaultStorageOperationStatus.RawStorageInputRejected, result.status)
    }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1VaultStorageOperationCapability) {
        assertFalse(capability.usableForFileIo)
        assertFalse(capability.usableForPersistence)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.settingsPersistenceAvailable)
        assertFalse(capability.storageImplementationAvailable)
        assertFalse(capability.manifestReadWriteAvailable)
        assertFalse(capability.storageIndexReadWriteAvailable)
        assertFalse(capability.recordReadWriteAvailable)
        assertFalse(capability.atomicWriteAvailable)
        assertFalse(capability.crashRecoveryAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.realPathConstructed)
        assertFalse(capability.absolutePathConstructed)
        assertFalse(capability.realPathContainmentVerified)
        assertFalse(capability.symlinkSafetyVerified)
        assertFalse(capability.permissionsVerified)
        assertFalse(capability.ownershipVerified)
        assertFalse(capability.durabilityVerified)
        assertFalse(capability.antiRollbackAnchorAvailable)
        assertFalse(capability.walletSyncAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertRedacted(rendered: String, raw: String) {
        assertFalse(rendered.contains(raw), "rendered value leaked raw fixture: $rendered")
    }
}
