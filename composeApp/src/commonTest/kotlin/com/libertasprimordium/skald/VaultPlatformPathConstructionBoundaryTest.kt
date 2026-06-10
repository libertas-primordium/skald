package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolverInputSnapshot
import com.libertasprimordium.skald.security.SkaldVaultV1PathContainmentPlanner
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionArtifactKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionSource
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionWarning
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlan
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlanPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutResult
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultPlatformPathConstructionBoundaryTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()

    @Test
    fun platformPathConstructionPolicyIdIsStable() {
        assertEquals(
            "skald-vault-v1-platform-path-construction-boundary-v1",
            SkaldVaultV1PlatformPathConstructionPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1PlatformPathConstructionPolicy.POLICY_VERSION)
    }

    @Test
    fun noEvidenceMissingRootAndMissingLogicalLayoutFailClosed() {
        assertRejected(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.noEvidence(),
            ),
            SkaldVaultV1PlatformPathConstructionFailureReason.NoCandidateEvidenceSupplied,
            SkaldVaultV1PlatformPathConstructionStatus.NoEvidenceAvailable,
        )
        assertRejected(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = androidRootEvidence(),
                    layoutPlan = null,
                ),
            ),
            SkaldVaultV1PlatformPathConstructionFailureReason.MissingLogicalArtifactEvidence,
            SkaldVaultV1PlatformPathConstructionStatus.MissingLogicalArtifactEvidence,
        )
        assertRejected(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = null,
                    layoutPlan = fixedLayout(),
                ),
            ),
            SkaldVaultV1PlatformPathConstructionFailureReason.MissingPlatformRootEvidence,
            SkaldVaultV1PlatformPathConstructionStatus.MissingPlatformRootEvidence,
        )
    }

    @Test
    fun rejectedRootEvidenceRemainsBlocked() {
        val rejectedRoot = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.androidExternalSharedEvidence(
                staticEvidence = "android-external-shared-root-fixture",
            ),
        )
        val rejected = assertRejected(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootResultAndLayout(
                    rootResult = rejectedRoot,
                    layoutPlan = fixedLayout(),
                ),
            ),
            SkaldVaultV1PlatformPathConstructionFailureReason.RejectedPlatformRootEvidence,
            SkaldVaultV1PlatformPathConstructionStatus.RootEvidenceRejected,
        )

        assertEquals(
            SkaldVaultV1PlatformRootResolverFailureReason.AndroidExternalSharedRootRejected,
            rejected.rootResolverFailureReason,
        )
    }

    @Test
    fun androidAppPrivateRootEvidenceProducesPlannedLocationsOnly() {
        val rootFixture = "android-app-private-internal-root-evidence-fixture"
        val rootEvidence = androidRootEvidence(rootFixture)
        val request = SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
            rootEvidence = rootEvidence,
            layoutPlan = fixedLayout(),
        )
        val evidence = assertAccepted(SkaldVaultV1PlatformPathConstructionPolicy.plan(request))

        assertEquals(SkaldVaultV1PlatformPathConstructionPolicy.POLICY_ID, evidence.policyId)
        assertEquals(
            SkaldVaultV1PlatformPathConstructionStatus.PlannedArtifactLocationsAcceptedStillDisabled,
            evidence.status,
        )
        assertEquals(
            SkaldVaultV1PlatformPathConstructionSource.RootResolverAndLogicalLayout,
            evidence.source,
        )
        assertEquals(SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal, evidence.rootKind)
        assertContains(evidence.warnings, SkaldVaultV1PlatformPathConstructionWarning.AndroidRootStillAppPrivateOnly)
        assertContains(evidence.blockers, SkaldVaultV1PlatformPathConstructionBlocker.AcceptedButFileIoBlocked)
        assertDisabledCapabilities(evidence.capability)
        assertAllLocationsAreEvidenceOnly(evidence)
        assertRedacted(request.toString(), rootFixture)
        assertRedacted(evidence.toString(), rootFixture)
        evidence.locations().forEach { location ->
            assertRedacted(location.toString(), rootFixture)
            assertRedacted(location.token.toString(), rootFixture)
        }
    }

    @Test
    fun linuxDefaultRootEvidenceProducesPlannedLocationsOnly() {
        val rootFixture = "/home/skald-test-user/.local/share"
        val evidence = assertAccepted(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = linuxDefaultRootEvidence(rootFixture),
                    layoutPlan = fixedLayout(),
                ),
            ),
        )

        assertEquals(SkaldVaultV1PlatformRootKind.LinuxXdgDataHome, evidence.rootKind)
        assertDisabledCapabilities(evidence.capability)
        assertAllLocationsAreEvidenceOnly(evidence)
        assertFalse(evidence.toString().contains(rootFixture))
        evidence.locations().forEach { location ->
            assertEquals(SkaldVaultV1PlatformRootKind.LinuxXdgDataHome, location.rootKind)
            assertFalse(location.toString().contains(rootFixture))
            assertFalse(location.token.toString().contains(rootFixture))
        }
    }

    @Test
    fun linuxCustomRootEvidenceProducesPlannedLocationsOnlyAndRemainsUnusable() {
        val rootFixture = "/srv/skald-test-vaults/user-data"
        val evidence = assertAccepted(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = linuxCustomRootEvidence(rootFixture),
                    layoutPlan = fixedLayout(),
                ),
            ),
        )

        assertEquals(SkaldVaultV1PlatformRootKind.LinuxCustomCandidate, evidence.rootKind)
        assertContains(
            evidence.warnings,
            SkaldVaultV1PlatformPathConstructionWarning.LinuxCustomRootsStillSettingsFutureWork,
        )
        assertDisabledCapabilities(evidence.capability)
        assertAllLocationsAreEvidenceOnly(evidence)
        assertFalse(evidence.toString().contains(rootFixture))
    }

    @Test
    fun rejectedLinuxCustomRootValidationRemainsBlocked() {
        val validation = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate("/tmp/skald-vaults")
        val rootResult = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootValidationResult(validation),
        )
        val rejected = assertRejected(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootResultAndLayout(
                    rootResult = rootResult,
                    layoutPlan = fixedLayout(),
                ),
            ),
            SkaldVaultV1PlatformPathConstructionFailureReason.RejectedPlatformRootEvidence,
            SkaldVaultV1PlatformPathConstructionStatus.RootEvidenceRejected,
        )

        assertEquals(
            SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootValidationFailed,
            rejected.rootResolverFailureReason,
        )
        val rootRejected = rootResult as SkaldVaultV1PlatformRootResolverResult.Rejected
        assertEquals(
            SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected,
            rootRejected.customRootValidationFailureReason,
        )
    }

    @Test
    fun allLogicalArtifactKindsAreModeledAsNonWritablePlannedLocations() {
        val evidence = assertAccepted(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = androidRootEvidence(),
                    layoutPlan = fixedLayout(),
                ),
            ),
        )
        val artifactKinds = evidence.locations().map { it.artifactKind }.toSet()

        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentContainer)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentManifest)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentStorageIndex)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.RecordArtifact)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.TempContainer)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.TempManifest)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.TempStorageIndex)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.QuarantineArtifact)
        assertContains(artifactKinds, SkaldVaultV1PlatformPathConstructionArtifactKind.RecoveryMetadata)
        evidence.locations().forEach { location ->
            assertFalse(location.usableForFileIo)
            assertFalse(location.usableForPersistence)
            assertFalse(location.manifestReadWriteAvailable)
            assertFalse(location.storageIndexReadWriteAvailable)
            assertFalse(location.recordReadWriteAvailable)
        }
    }

    @Test
    fun explicitSafeRelativeSegmentsCanBeRepresentedOnlyAsStillDisabledEvidence() {
        val evidence = assertAccepted(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndSegmentValues(
                    rootEvidence = androidRootEvidence(),
                    artifactKind = SkaldVaultV1PlatformPathConstructionArtifactKind.RecoveryMetadata,
                    segmentValues = listOf("skald-vault-v1", "vault_fixture", "recovery", "recovery_v1"),
                ),
            ),
        )
        val location = evidence.locations().single()

        assertEquals(SkaldVaultV1PlatformPathConstructionArtifactKind.RecoveryMetadata, location.artifactKind)
        assertEquals(
            listOf("skald-vault-v1", "vault_fixture", "recovery", "recovery_v1"),
            location.testOnlySegmentValues(),
        )
        assertDisabledCapabilities(evidence.capability)
        assertFalse(location.platformPathConstructed)
        assertFalse(location.realFilesystemContainmentChecked)
        assertFalse(location.symlinkChecked)
        assertFalse(location.permissionChecked)
        assertFalse(location.durabilityProbed)
    }

    @Test
    fun rawPlatformPathLikeInputsAreRejectedBeforePlanning() {
        val longHex = "0123456789abcdef0123456789abcdef" +
            "0123456789abcdef0123456789abcdef"
        val wifLike = "K" + "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".take(51)
        val bitcoinAddressLikeSentinel = "bc1" + "invalidaddresssentinel0000000000"
        val nostrSecretLikeSentinel = "nsec1" + "invalidsentinel"
        val cases = mapOf(
            "" to SkaldVaultV1PlatformPathConstructionFailureReason.EmptyArtifactSegmentRejected,
            "   " to SkaldVaultV1PlatformPathConstructionFailureReason.EmptyArtifactSegmentRejected,
            "relative/path" to SkaldVaultV1PlatformPathConstructionFailureReason.RawRelativePathInputRejected,
            "/home/skald-test-user/.local/share" to
                SkaldVaultV1PlatformPathConstructionFailureReason.RawAbsolutePathInputRejected,
            "file://tmp/skald" to SkaldVaultV1PlatformPathConstructionFailureReason.UriOrUrlInputRejected,
            "https://example.invalid/skald" to
                SkaldVaultV1PlatformPathConstructionFailureReason.UriOrUrlInputRejected,
            "content://example.invalid/skald" to
                SkaldVaultV1PlatformPathConstructionFailureReason.UriOrUrlInputRejected,
            "C:\\vaults\\skald" to
                SkaldVaultV1PlatformPathConstructionFailureReason.WindowsOrUncPathInputRejected,
            "\\\\server\\share" to
                SkaldVaultV1PlatformPathConstructionFailureReason.WindowsOrUncPathInputRejected,
            "vault..segment" to SkaldVaultV1PlatformPathConstructionFailureReason.PathTraversalRejected,
            "contains-password-sentinel" to
                SkaldVaultV1PlatformPathConstructionFailureReason.SecretMaterialRejected,
            "contains-token-sentinel" to
                SkaldVaultV1PlatformPathConstructionFailureReason.SecretMaterialRejected,
            longHex to SkaldVaultV1PlatformPathConstructionFailureReason.TransactionLikeArtifactRejected,
            "xprv-invalid-sentinel" to
                SkaldVaultV1PlatformPathConstructionFailureReason.WalletMaterialRejected,
            "tprv-invalid-sentinel" to
                SkaldVaultV1PlatformPathConstructionFailureReason.WalletMaterialRejected,
            wifLike to SkaldVaultV1PlatformPathConstructionFailureReason.WalletMaterialRejected,
            nostrSecretLikeSentinel to
                SkaldVaultV1PlatformPathConstructionFailureReason.WalletMaterialRejected,
            bitcoinAddressLikeSentinel to
                SkaldVaultV1PlatformPathConstructionFailureReason.BitcoinAddressLikeArtifactRejected,
            "plain-label" to SkaldVaultV1PlatformPathConstructionFailureReason.RawPlatformPathInputRejected,
        )

        cases.forEach { (candidate, reason) ->
            val rejected = assertRejected(
                SkaldVaultV1PlatformPathConstructionPolicy.plan(
                    SkaldVaultV1PlatformPathConstructionRequest.rawPlatformPathCandidate(candidate),
                ),
                reason,
                SkaldVaultV1PlatformPathConstructionStatus.RawPlatformPathInputRejected,
            )
            if (candidate.isNotEmpty()) {
                assertFalse(rejected.toString().contains(candidate))
            }
        }
    }

    @Test
    fun unsafeArtifactSegmentsAreRejectedByExistingSafeSegmentPolicy() {
        val rootEvidence = androidRootEvidence()
        val longHex = "0123456789abcdef0123456789abcdef" +
            "0123456789abcdef0123456789abcdef"
        val bitcoinAddressLikeSentinel = "bc1" + "invalidaddresssentinel0000000000"
        val nostrSecretLikeSentinel = "nsec1" + "invalidsentinel"
        val cases = listOf(
            listOf("skald-vault-v1", "", "recovery"),
            listOf("skald-vault-v1", "..", "recovery"),
            listOf("skald-vault-v1", "path/segment", "recovery"),
            listOf("skald-vault-v1", "secret_material", "recovery"),
            listOf("skald-vault-v1", longHex, "recovery"),
            listOf("skald-vault-v1", nostrSecretLikeSentinel, "recovery"),
            listOf("skald-vault-v1", bitcoinAddressLikeSentinel, "recovery"),
            listOf("skald-vault-v1", "unsupported:colon", "recovery"),
        )

        cases.forEach { segments ->
            val rejected = assertRejectedStatus(
                SkaldVaultV1PlatformPathConstructionPolicy.plan(
                    SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndSegmentValues(
                        rootEvidence = rootEvidence,
                        artifactKind = SkaldVaultV1PlatformPathConstructionArtifactKind.ExplicitSegmentProbe,
                        segmentValues = segments,
                    ),
                ),
                SkaldVaultV1PlatformPathConstructionStatus.ArtifactSegmentEvidenceRejected,
            )
            assertTrue(
                rejected.containmentFailureReason != null ||
                    rejected.reason != SkaldVaultV1PlatformPathConstructionFailureReason.ContainmentPlannerRejected,
            )
        }
    }

    @Test
    fun acceptedEvidenceAndTokensDoNotExposeRawRootsOrAbsolutePathsByDefault() {
        val rootFixture = "/home/skald-test-user/.local/share"
        val evidence = assertAccepted(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = linuxDefaultRootEvidence(rootFixture),
                    layoutPlan = fixedLayout(),
                ),
            ),
        )
        val location = evidence.locations().first()

        assertFalse(evidence.toString().contains(rootFixture))
        assertFalse(location.toString().contains(rootFixture))
        assertFalse(location.token.toString().contains(rootFixture))
        assertFalse(evidence.toString().contains("/home/"))
        assertFalse(location.toString().contains("/home/"))
        assertFalse(location.token.toString().contains("/home/"))
        assertFalse(evidence.toString().contains("skald-test-user"))
        assertFalse(location.toString().contains("skald-test-user"))
        assertFalse(location.token.toString().contains("skald-test-user"))
    }

    @Test
    fun acceptedEvidenceStillHasEveryStorageAndProviderCapabilityDisabled() {
        val evidence = assertAccepted(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = androidRootEvidence(),
                    layoutPlan = fixedLayout(),
                ),
            ),
        )

        assertDisabledCapabilities(evidence.capability)
        assertFalse(evidence.platformPathObjectReturned)
        assertFalse(evidence.rawRootStringExposedByDefault)
        assertTrue(evidence.platformPathConstructionStillDisabled)
        assertTrue(evidence.plannedArtifactLocationEvidenceModeled)
        assertContains(evidence.blockers, SkaldVaultV1PlatformPathConstructionBlocker.RealPathConstructionMissing)
        assertContains(evidence.blockers, SkaldVaultV1PlatformPathConstructionBlocker.AbsolutePathConstructionMissing)
        assertContains(evidence.blockers, SkaldVaultV1PlatformPathConstructionBlocker.DurabilityMissing)
        assertContains(evidence.blockers, SkaldVaultV1PlatformPathConstructionBlocker.MainnetDisabled)
    }

    private fun fixedLayout(): SkaldVaultV1StorageLayoutPlan =
        when (
            val result = SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(fixedRecordId),
            )
        ) {
            is SkaldVaultV1StorageLayoutResult.Accepted -> result.value
            is SkaldVaultV1StorageLayoutResult.Rejected ->
                error("fixed non-secret storage layout fixture must be accepted: ${result.reason}")
        }

    private fun androidRootEvidence(
        fixture: String = "android-app-private-internal-root-evidence-fixture",
    ): SkaldVaultV1PlatformRootResolverEvidence =
        acceptedRoot(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence(fixture),
            ),
        )

    private fun linuxDefaultRootEvidence(
        fixture: String,
    ): SkaldVaultV1PlatformRootResolverEvidence =
        acceptedRoot(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                    SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                        xdgDataHomeEvidence = fixture,
                    ),
                ),
            ),
        )

    private fun linuxCustomRootEvidence(
        fixture: String,
    ): SkaldVaultV1PlatformRootResolverEvidence {
        val validation = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(fixture)
        return acceptedRoot(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootValidationResult(validation),
            ),
        )
    }

    private fun acceptedRoot(
        result: SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>,
    ): SkaldVaultV1PlatformRootResolverEvidence =
        when (result) {
            is SkaldVaultV1PlatformRootResolverResult.Accepted -> result.value
            is SkaldVaultV1PlatformRootResolverResult.Rejected ->
                error("expected accepted root evidence but got ${result.reason}")
        }

    private fun assertAccepted(
        result: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
    ): SkaldVaultV1PlatformPathConstructionEvidence =
        when (result) {
            is SkaldVaultV1PlatformPathConstructionResult.Accepted -> result.value
            is SkaldVaultV1PlatformPathConstructionResult.Rejected ->
                error("expected accepted path-construction evidence but got ${result.reason}")
        }

    private fun assertRejected(
        result: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
        reason: SkaldVaultV1PlatformPathConstructionFailureReason,
        status: SkaldVaultV1PlatformPathConstructionStatus,
    ): SkaldVaultV1PlatformPathConstructionResult.Rejected {
        val rejected = assertRejectedStatus(result, status)
        assertEquals(reason, rejected.reason)
        return rejected
    }

    private fun assertRejectedStatus(
        result: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
        status: SkaldVaultV1PlatformPathConstructionStatus,
    ): SkaldVaultV1PlatformPathConstructionResult.Rejected =
        when (result) {
            is SkaldVaultV1PlatformPathConstructionResult.Accepted ->
                error("expected rejected path-construction evidence but got accepted")
            is SkaldVaultV1PlatformPathConstructionResult.Rejected -> {
                assertEquals(status, result.status)
                result
            }
        }

    private fun assertAllLocationsAreEvidenceOnly(
        evidence: SkaldVaultV1PlatformPathConstructionEvidence,
    ) {
        assertTrue(evidence.locations().isNotEmpty())
        evidence.locations().forEach { location ->
            assertEquals(SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID, location.storageLayoutPolicyId)
            assertEquals(SkaldVaultV1PathContainmentPlanner.POLICY_ID, location.pathContainmentPlannerPolicyId)
            assertTrue(location.rootTokenBound)
            assertTrue(location.relativeSegmentsOnly)
            assertFalse(location.platformPathConstructed)
            assertFalse(location.realFilesystemContainmentChecked)
            assertFalse(location.symlinkChecked)
            assertFalse(location.permissionChecked)
            assertFalse(location.durabilityProbed)
            assertFalse(location.token.containsPlatformPath)
            assertFalse(location.token.containsAbsolutePath)
            assertFalse(location.token.usableForFileIo)
            assertFalse(location.usableForFileIo)
            assertFalse(location.usableForPersistence)
            assertFalse(location.manifestReadWriteAvailable)
            assertFalse(location.storageIndexReadWriteAvailable)
            assertFalse(location.recordReadWriteAvailable)
        }
    }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1PlatformPathConstructionCapability) {
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
        assertFalse(rendered.contains(raw), "default rendering must redact raw fixture: $rendered")
    }
}
