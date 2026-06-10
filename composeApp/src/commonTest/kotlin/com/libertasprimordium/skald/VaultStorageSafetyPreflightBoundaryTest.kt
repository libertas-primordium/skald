package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderStorageSafetyPreflightRule
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolverInputSnapshot
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionArtifactKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionResult
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
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyGateStatus
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightRequest
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightStatus
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyWarning
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

class VaultStorageSafetyPreflightBoundaryTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()
    private val linuxFixtureRoot = "/home/skald-test-user/.local/share"

    @Test
    fun storageSafetyPreflightPolicyIdIsStable() {
        assertEquals(
            "skald-vault-v1-storage-safety-preflight-boundary-v1",
            SkaldVaultV1StorageSafetyPreflightPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1StorageSafetyPreflightPolicy.POLICY_VERSION)
    }

    @Test
    fun noEvidenceAndRejectedArtifactEvidenceFailClosed() {
        assertRejected(
            SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
                SkaldVaultV1StorageSafetyPreflightRequest.noEvidence(),
            ),
            SkaldVaultV1StorageSafetyPreflightFailureReason.NoCandidateEvidenceSupplied,
            SkaldVaultV1StorageSafetyPreflightStatus.NoEvidenceAvailable,
        )
        assertRejected(
            SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
                SkaldVaultV1StorageSafetyPreflightRequest.fromArtifactLocationEvidence(null),
            ),
            SkaldVaultV1StorageSafetyPreflightFailureReason.MissingArtifactLocationEvidence,
            SkaldVaultV1StorageSafetyPreflightStatus.MissingArtifactLocationEvidence,
        )

        val rejectedPath = SkaldVaultV1PlatformPathConstructionPolicy.plan(
            SkaldVaultV1PlatformPathConstructionRequest.rawPlatformPathCandidate("/tmp/skald-vault"),
        )
        val rejected = SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
            SkaldVaultV1StorageSafetyPreflightRequest.fromPathConstructionResult(rejectedPath),
        )

        assertTrue(rejected is SkaldVaultV1StorageSafetyPreflightResult.Rejected)
        assertEquals(
            SkaldVaultV1StorageSafetyPreflightFailureReason.RejectedArtifactLocationEvidence,
            rejected.reason,
        )
        assertEquals(
            SkaldVaultV1PlatformPathConstructionFailureReason.RawAbsolutePathInputRejected,
            rejected.artifactLocationFailureReason,
        )
    }

    @Test
    fun plannedArtifactLocationEvidenceIsConsumedOnlyAsStillDisabledSafetyEvidence() {
        val evidence = acceptedPreflight(androidPlannedArtifactEvidence())
        val artifactKinds = evidence.locations().map { it.artifactKind }.toSet()

        assertEquals(
            SkaldVaultV1StorageSafetyPreflightStatus.StorageSafetyPreflightBlockedStillDisabled,
            evidence.status,
        )
        assertEquals(
            SkaldVaultV1PlatformPathConstructionArtifactKind.entries
                .filterNot { it == SkaldVaultV1PlatformPathConstructionArtifactKind.ExplicitSegmentProbe }
                .toSet(),
            artifactKinds,
        )
        evidence.locations().forEach { location ->
            assertFalse(location.platformPathConstructed)
            assertFalse(location.realFilesystemContainmentChecked)
            assertFalse(location.symlinkChecked)
            assertFalse(location.permissionChecked)
            assertFalse(location.durabilityProbed)
            assertFalse(location.usableForFileIo)
            assertFalse(location.usableForPersistence)
            assertFalse(location.manifestReadWriteAvailable)
            assertFalse(location.storageIndexReadWriteAvailable)
            assertFalse(location.recordReadWriteAvailable)
        }
        assertContains(evidence.blockers, SkaldVaultV1StorageSafetyBlocker.MissingSafetyEvidence)
        assertContains(evidence.blockers, SkaldVaultV1StorageSafetyBlocker.AcceptedButPersistenceBlocked)
        assertContains(evidence.warnings, SkaldVaultV1StorageSafetyWarning.NoFilesystemChecks)
        assertFalse(evidence.capability.usableForPersistence)
        assertFalse(evidence.capability.providerSelectable)
        assertFalse(evidence.capability.storageImplementationAvailable)
    }

    @Test
    fun safetyGateVocabularyIsCompleteAndUnapprovedForCurrentPersistence() {
        val safetyEvidence = SkaldVaultV1StorageSafetyEvidenceKind.entries.map {
            SkaldVaultV1StorageSafetyEvidence.modelOnly(it)
        }
        val evidence = acceptedPreflight(
            plannedArtifactEvidence = linuxDefaultPlannedArtifactEvidence(),
            safetyEvidence = safetyEvidence,
        )

        assertEquals(SkaldVaultV1StorageSafetyEvidenceKind.entries.size, evidence.gateEvidence.size)
        SkaldVaultV1StorageSafetyEvidenceKind.entries.forEach { kind ->
            val gate = evidence.gateEvidence.single { it.kind == kind }
            assertEquals(SkaldVaultV1StorageSafetyGateStatus.ModelOnly, gate.status)
            assertTrue(gate.requiredForPersistence)
            assertFalse(gate.approvedForCurrentPersistence)
        }
        assertContains(evidence.blockers, SkaldVaultV1StorageSafetyBlocker.UnapprovedSafetyGate)
        assertFalse(evidence.capability.vaultPersistenceAvailable)
    }

    @Test
    fun warningOnlyAndUserConsentEvidenceCannotEnablePersistence() {
        val evidence = acceptedPreflight(
            plannedArtifactEvidence = linuxDefaultPlannedArtifactEvidence(),
            safetyEvidence = listOf(
                SkaldVaultV1StorageSafetyEvidence.warningOnly(
                    SkaldVaultV1StorageSafetyEvidenceKind.DurabilityCheckReviewed,
                ),
                SkaldVaultV1StorageSafetyEvidence.userConsentOnly(
                    SkaldVaultV1StorageSafetyEvidenceKind.StorageFailureMappingReviewed,
                ),
            ),
        )

        assertContains(evidence.blockers, SkaldVaultV1StorageSafetyBlocker.WarningOnlyEvidenceRejected)
        assertContains(evidence.blockers, SkaldVaultV1StorageSafetyBlocker.UserConsentOverrideRejected)
        assertContains(evidence.warnings, SkaldVaultV1StorageSafetyWarning.WarningOnlyCannotEnablePersistence)
        assertContains(evidence.warnings, SkaldVaultV1StorageSafetyWarning.UserConsentCannotOverrideFailedGates)
        assertFalse(evidence.capability.usableForPersistence)
        assertFalse(evidence.capability.manifestReadWriteAvailable)
        assertFalse(evidence.capability.storageIndexReadWriteAvailable)
        assertFalse(evidence.capability.recordReadWriteAvailable)
    }

    @Test
    fun androidLinuxAndCustomRootPlannedLocationsRemainStorageUnavailable() {
        val android = acceptedPreflight(androidPlannedArtifactEvidence())
        val linuxDefault = acceptedPreflight(linuxDefaultPlannedArtifactEvidence())
        val linuxCustom = acceptedPreflight(linuxCustomPlannedArtifactEvidence())

        listOf(android, linuxDefault, linuxCustom).forEach { evidence ->
            assertFalse(evidence.capability.usableForFileIo)
            assertFalse(evidence.capability.usableForPersistence)
            assertFalse(evidence.capability.providerSelectable)
            assertFalse(evidence.capability.vaultCreationAvailable)
            assertFalse(evidence.capability.vaultUnlockAvailable)
            assertFalse(evidence.capability.vaultPersistenceAvailable)
            assertFalse(evidence.capability.settingsPersistenceAvailable)
            assertFalse(evidence.capability.storageImplementationAvailable)
            assertFalse(evidence.capability.manifestReadWriteAvailable)
            assertFalse(evidence.capability.storageIndexReadWriteAvailable)
            assertFalse(evidence.capability.recordReadWriteAvailable)
            assertFalse(evidence.capability.atomicWriteAvailable)
            assertFalse(evidence.capability.crashRecoveryAvailable)
            assertFalse(evidence.capability.secureSecretStorageAvailable)
            assertFalse(evidence.capability.secureMetadataStorageAvailable)
            assertFalse(evidence.capability.realPathConstructed)
            assertFalse(evidence.capability.absolutePathConstructed)
            assertFalse(evidence.capability.realPathContainmentVerified)
            assertFalse(evidence.capability.symlinkSafetyVerified)
            assertFalse(evidence.capability.permissionsVerified)
            assertFalse(evidence.capability.ownershipVerified)
            assertFalse(evidence.capability.durabilityVerified)
            assertFalse(evidence.capability.mainnetAvailable)
        }
    }

    @Test
    fun redactedRenderingDoesNotExposeRootOrPlannedLocationEvidence() {
        val planned = linuxDefaultPlannedArtifactEvidence()
        val request = SkaldVaultV1StorageSafetyPreflightRequest.fromArtifactLocationEvidence(planned)
        val evidence = acceptedPreflight(planned)
        val secretLike = "candidate-password-token-value"
        val longHex = "0123456789abcdef0123456789abcdef" + "0123456789abcdef0123456789abcdef"
        val rejectedSecret = SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
            SkaldVaultV1StorageSafetyPreflightRequest.rawStorageSafetyEvidenceCandidate(secretLike),
        )
        val rejectedHex = SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
            SkaldVaultV1StorageSafetyPreflightRequest.rawStorageSafetyEvidenceCandidate(longHex),
        )

        listOf(
            request.toString(),
            evidence.toString(),
            evidence.token.toString(),
            evidence.locations().first().toString(),
            rejectedSecret.toString(),
            rejectedHex.toString(),
        ).forEach { rendered ->
            assertFalse(rendered.contains(linuxFixtureRoot))
            assertFalse(rendered.contains("vault_202122232425262728292a2b2c2d2e2f"))
            assertFalse(rendered.contains(secretLike))
            assertFalse(rendered.contains(longHex))
        }
    }

    @Test
    fun rawStorageSafetyEvidenceInputsAreRejectedWithTypedReasons() {
        val bitcoinAddressLikeSentinel = "bc" + "1invalidpreflightaddresssentinel0000000000"
        val nostrSecretLikeSentinel = "nsec" + "1invalidpreflightsentinel0000000000"
        val longHex = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
        val wifLikeSentinel = "K" + "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrs"

        mapOf(
            "" to SkaldVaultV1StorageSafetyPreflightFailureReason.EmptyEvidenceNameRejected,
            "   " to SkaldVaultV1StorageSafetyPreflightFailureReason.EmptyEvidenceNameRejected,
            "/home/skald-test-user/.local/share" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.RawAbsolutePathInputRejected,
            "relative/path" to SkaldVaultV1StorageSafetyPreflightFailureReason.RawRelativePathInputRejected,
            "file://skald-vault" to SkaldVaultV1StorageSafetyPreflightFailureReason.UriLikeInputRejected,
            "https://example.invalid/skald" to SkaldVaultV1StorageSafetyPreflightFailureReason.UriLikeInputRejected,
            "content://example/skald" to SkaldVaultV1StorageSafetyPreflightFailureReason.UriLikeInputRejected,
            "C:\\skald\\vault" to SkaldVaultV1StorageSafetyPreflightFailureReason.RawAbsolutePathInputRejected,
            "\\\\server\\share" to SkaldVaultV1StorageSafetyPreflightFailureReason.RawAbsolutePathInputRejected,
            "file-object-like-value" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.FileOrPathObjectInputRejected,
            "path-object-like-value" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.FileOrPathObjectInputRejected,
            "segment..traversal" to SkaldVaultV1StorageSafetyPreflightFailureReason.PathTraversalRejected,
            "contains-password-sentinel" to SkaldVaultV1StorageSafetyPreflightFailureReason.SecretMaterialRejected,
            "contains-token-sentinel" to SkaldVaultV1StorageSafetyPreflightFailureReason.SecretMaterialRejected,
            "user:pass@example" to SkaldVaultV1StorageSafetyPreflightFailureReason.SecretMaterialRejected,
            "xprv-invalid-preflight-sentinel" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.WalletMaterialRejected,
            "tprv-invalid-preflight-sentinel" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.WalletMaterialRejected,
            wifLikeSentinel to SkaldVaultV1StorageSafetyPreflightFailureReason.WalletMaterialRejected,
            nostrSecretLikeSentinel to SkaldVaultV1StorageSafetyPreflightFailureReason.WalletMaterialRejected,
            bitcoinAddressLikeSentinel to
                SkaldVaultV1StorageSafetyPreflightFailureReason.BitcoinAddressLikeEvidenceRejected,
            longHex to SkaldVaultV1StorageSafetyPreflightFailureReason.TransactionLikeEvidenceRejected,
            "unsupported:colon" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.UnsupportedEvidenceNameRejected,
            "unsupported space" to
                SkaldVaultV1StorageSafetyPreflightFailureReason.UnsupportedEvidenceNameRejected,
        ).forEach { (candidate, expectedReason) ->
            assertRejected(
                SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
                    SkaldVaultV1StorageSafetyPreflightRequest.rawStorageSafetyEvidenceCandidate(candidate),
                ),
                expectedReason,
                SkaldVaultV1StorageSafetyPreflightStatus.RawStorageSafetyEvidenceRejected,
            )
        }
    }

    @Test
    fun readinessProviderAcceptanceDependencyAndSelectionRepresentPreflightButRemainDisabled() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            SkaldVaultV1StorageSafetyPreflightPolicy.POLICY_ID,
            storageContract.storageSafetyPreflightBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.storageSafetyPreflightBoundaryStatus,
        )
        assertEquals(
            ProductionProviderStorageSafetyPreflightRule.entries.toSet(),
            storageContract.storageSafetyPreflightRules,
        )
        assertTrue(storageContract.storageSafetyPreflightBoundaryModeled)
        assertTrue(storageContract.storageSafetyPreflightStillDisabled)
        assertTrue(storageContract.storageSafetyPreflightDoesNotRunFilesystemChecks)
        assertTrue(storageContract.storageSafetyPreflightDoesNotEnablePersistence)
        assertTrue(storageContract.storageSafetyPreflightDoesNotEnableProviderSelection)
        assertTrue(storageContract.storageSafetyGateVocabularyModeled)
        assertFalse(storageContract.platformStorageImplementationAdded)
        assertFalse(storageContract.manifestReadWriteImplemented)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(
                ProductionProviderAcceptanceGate.StorageSafetyPreflightBoundaryImplementedAndTested,
            ),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageSafetyPreflightBoundaryImplementedAndTested
            ],
        )
        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.StorageSafetyPreflightBoundaryBuildingBlock,
        )
        assertFalse(readiness.productionPersistenceEnabled)

        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.StorageSafetyPreflightBoundaryImplementedTested,
        )
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun acceptedPreflight(
        plannedArtifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence,
        safetyEvidence: List<SkaldVaultV1StorageSafetyEvidence> = emptyList(),
    ) = when (
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
        val rootEvidence = acceptedRootEvidence(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootValidationResult(custom),
            ),
        )
        return plannedArtifactEvidence(rootEvidence)
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

    private fun assertRejected(
        result: SkaldVaultV1StorageSafetyPreflightResult<*>,
        expectedReason: SkaldVaultV1StorageSafetyPreflightFailureReason,
        expectedStatus: SkaldVaultV1StorageSafetyPreflightStatus,
    ) {
        assertTrue(result is SkaldVaultV1StorageSafetyPreflightResult.Rejected)
        assertEquals(expectedReason, result.reason, "candidate rejected with unexpected reason")
        assertEquals(expectedStatus, result.status)
    }
}
