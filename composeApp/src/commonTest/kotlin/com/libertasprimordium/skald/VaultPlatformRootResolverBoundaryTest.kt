package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderPlatformRootResolverRule
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationResult
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolverInputSnapshot
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverCapability
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverSource
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverStatus
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverWarning
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootSettingsPolicy
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

class VaultPlatformRootResolverBoundaryTest {
    @Test
    fun platformRootResolverPolicyIdAndSettingsEvidenceAreStable() {
        val platformPolicy = SkaldVaultV1PlatformRootSettingsPolicy.currentContract()

        assertEquals(
            "skald-vault-v1-platform-root-resolver-boundary-v1",
            SkaldVaultV1PlatformRootResolverPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1PlatformRootResolverPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1PlatformRootResolverPolicy.POLICY_ID,
            platformPolicy.platformRootResolverPolicyId,
        )
        assertTrue(platformPolicy.platformRootResolverBoundaryModeled)
        assertTrue(platformPolicy.androidAppPrivateRootEvidenceModeled)
        assertTrue(platformPolicy.linuxDefaultRootResolverEvidenceModeled)
        assertTrue(platformPolicy.platformRootResolverStillDisabled)
        assertTrue(platformPolicy.platformRootResolverDoesNotEnablePersistence)
        assertTrue(platformPolicy.platformRootResolverDoesNotProveDurability)
        assertTrue(platformPolicy.platformRootResolverDoesNotEnableProviderSelection)
        assertFalse(platformPolicy.androidRootResolutionImplemented)
        assertFalse(platformPolicy.linuxRootResolutionImplemented)
        assertFalse(platformPolicy.settingsUiImplemented)
        assertFalse(platformPolicy.settingsPersistenceImplemented)
        assertFalse(platformPolicy.actualPathConstructionImplemented)
        assertFalse(platformPolicy.storageImplementationImplemented)
    }

    @Test
    fun noEvidenceReturnsUnavailableAndBlockedResults() {
        val noEvidence = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.noEvidence(),
        )
        val noLinuxSnapshot = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(),
            ),
        )
        val noCustom = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootCandidate(null),
        )

        assertRejected(
            noEvidence,
            SkaldVaultV1PlatformRootResolverFailureReason.NoCandidateEvidenceSupplied,
            SkaldVaultV1PlatformRootResolverStatus.NoEvidenceAvailable,
        )
        assertRejected(
            noLinuxSnapshot,
            SkaldVaultV1PlatformRootResolverFailureReason.LinuxDefaultRootEvidenceUnavailable,
            SkaldVaultV1PlatformRootResolverStatus.LinuxRootEvidenceUnavailable,
        )
        assertRejected(
            noCustom,
            SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootEvidenceUnavailable,
            SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootRejected,
        )
    }

    @Test
    fun androidAppPrivateEvidenceProducesStillDisabledEvidenceOnly() {
        val fixture = "android-app-private-internal-root-evidence-fixture"
        val request = SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence(
            staticEvidence = fixture,
        )
        val evidence = assertAccepted(SkaldVaultV1PlatformRootResolverPolicy.resolve(request))

        assertEquals(SkaldVaultV1PlatformRootResolverPolicy.POLICY_ID, evidence.policyId)
        assertEquals(
            SkaldVaultV1PlatformRootResolverStatus.AndroidAppPrivateEvidenceAcceptedStillDisabled,
            evidence.status,
        )
        assertEquals(
            SkaldVaultV1PlatformRootResolverSource.AndroidAppPrivateInternalEvidence,
            evidence.source,
        )
        assertEquals(SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal, evidence.rootKind)
        assertEquals(SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal, evidence.token.kind)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
        assertFalse(evidence.token.containsResolvedPlatformPath)
        assertFalse(evidence.token.usableForFileIo)
        assertFalse(evidence.platformPathObjectReturned)
        assertFalse(evidence.rawRootStringExposedByDefault)
        assertContains(evidence.warnings, SkaldVaultV1PlatformRootResolverWarning.AndroidRootResolutionFutureWork)
        assertDisabledCapabilities(evidence.capability)
        assertRedacted(request.toString(), fixture)
        assertRedacted(evidence.toString(), fixture)
        assertRedacted(evidence.token.toString(), fixture)
    }

    @Test
    fun androidExternalSharedAndUserSelectedRootsAreRejected() {
        assertRejected(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence(available = false),
            ),
            SkaldVaultV1PlatformRootResolverFailureReason.AndroidAppPrivateRootEvidenceUnavailable,
            SkaldVaultV1PlatformRootResolverStatus.AndroidAppPrivateEvidenceUnavailable,
        )
        assertRejected(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.androidExternalSharedEvidence(
                    staticEvidence = "android-external-shared-root-fixture",
                ),
            ),
            SkaldVaultV1PlatformRootResolverFailureReason.AndroidExternalSharedRootRejected,
            SkaldVaultV1PlatformRootResolverStatus.AndroidExternalSharedRootRejected,
        )
        assertRejected(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.androidUserSelectedRootEvidence(
                    staticEvidence = "android-user-selected-root-fixture",
                ),
            ),
            SkaldVaultV1PlatformRootResolverFailureReason.AndroidUserSelectedRootRejected,
            SkaldVaultV1PlatformRootResolverStatus.AndroidUserSelectedRootRejected,
        )
    }

    @Test
    fun linuxXdgDataHomeSnapshotProducesStillDisabledEvidenceOnly() {
        val fixture = "/home/skald-test-user/.local/share"
        val snapshot = SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
            xdgDataHomeEvidence = fixture,
        )
        val request = SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(snapshot)
        val evidence = assertAccepted(SkaldVaultV1PlatformRootResolverPolicy.resolve(request))

        assertEquals(
            SkaldVaultV1PlatformRootResolverStatus.LinuxDefaultRootEvidenceAcceptedStillDisabled,
            evidence.status,
        )
        assertEquals(SkaldVaultV1PlatformRootKind.LinuxXdgDataHome, evidence.rootKind)
        assertEquals(SkaldVaultV1PlatformRootKind.LinuxXdgDataHome, evidence.token.kind)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
        assertEquals(
            SkaldVaultV1LinuxRootResolutionStatus.DefaultRootAcceptedStillDisabled,
            evidence.linuxRootResolutionStatus,
        )
        assertFalse(evidence.token.containsResolvedPlatformPath)
        assertFalse(evidence.token.usableForFileIo)
        assertFalse(evidence.platformPathObjectReturned)
        assertContains(evidence.warnings, SkaldVaultV1PlatformRootResolverWarning.LinuxDefaultResolutionFutureWork)
        assertDisabledCapabilities(evidence.capability)
        assertRedacted(snapshot.toString(), fixture)
        assertRedacted(request.toString(), fixture)
        assertRedacted(evidence.toString(), fixture)
        assertRedacted(evidence.token.toString(), fixture)
    }

    @Test
    fun linuxHomeFallbackSnapshotProducesStillDisabledEvidenceOnly() {
        val fixture = "/home/skald-test-user/.local/share"
        val evidence = assertAccepted(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                    SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                        homeFallbackUserDataEvidence = fixture,
                    ),
                ),
            ),
        )

        assertEquals(
            SkaldVaultV1PlatformRootResolverStatus.LinuxDefaultRootEvidenceAcceptedStillDisabled,
            evidence.status,
        )
        assertEquals(SkaldVaultV1PlatformRootKind.LinuxHomeFallbackUserData, evidence.rootKind)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
        assertDisabledCapabilities(evidence.capability)
    }

    @Test
    fun acceptedCustomRootValidationProducesStillDisabledResolverEvidenceOnly() {
        val fixture = "/srv/skald-test-vaults/user-data"
        val validation = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(fixture)
        val request = SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootValidationResult(validation)
        val evidence = assertAccepted(SkaldVaultV1PlatformRootResolverPolicy.resolve(request))

        assertEquals(SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootAcceptedStillDisabled, evidence.status)
        assertEquals(SkaldVaultV1PlatformRootKind.LinuxCustomCandidate, evidence.rootKind)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
        assertContains(evidence.warnings, SkaldVaultV1PlatformRootResolverWarning.LinuxCustomRootSettingsFutureWork)
        assertDisabledCapabilities(evidence.capability)
    }

    @Test
    fun rejectedCustomRootValidationRemainsBlocked() {
        val result = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootCandidate("/tmp/skald-vaults"),
        )

        val rejected = assertRejected(
            result,
            SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootValidationFailed,
            SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootRejected,
        )
        assertEquals(
            SkaldVaultV1LinuxRootResolutionFailureReason.CustomRootValidationFailed,
            rejected.linuxRootResolutionReason,
        )
        assertEquals(
            SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected,
            rejected.customRootValidationFailureReason,
        )
        assertRedacted(rejected.toString(), "/tmp/skald-vaults")
    }

    @Test
    fun unsafeLinuxDefaultEvidenceFailsClosedWithNestedReasons() {
        val fakeLongHex = "0123456789abcdef0123456789abcdef" +
            "0123456789abcdef0123456789abcdef"
        val cases = mapOf(
            "" to SkaldVaultV1LinuxRootResolutionFailureReason.BlankEvidenceRejected,
            "   " to SkaldVaultV1LinuxRootResolutionFailureReason.BlankEvidenceRejected,
            "relative/vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.RelativePathRejected,
            "~/vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.TildePathRejected,
            "/" to SkaldVaultV1LinuxRootResolutionFailureReason.RootFilesystemRejected,
            "/tmp" to SkaldVaultV1LinuxRootResolutionFailureReason.TempRootRejected,
            "/var/tmp" to SkaldVaultV1LinuxRootResolutionFailureReason.TempRootRejected,
            "/run" to SkaldVaultV1LinuxRootResolutionFailureReason.RuntimeRootRejected,
            "/var/run" to SkaldVaultV1LinuxRootResolutionFailureReason.RuntimeRootRejected,
            "/etc" to SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected,
            "/usr" to SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected,
            "/bin" to SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected,
            "/dev" to SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected,
            "/proc" to SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected,
            "/sys" to SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected,
            "/media" to SkaldVaultV1LinuxRootResolutionFailureReason.RemovableMediaRootRejected,
            "/mnt" to SkaldVaultV1LinuxRootResolutionFailureReason.RemovableMediaRootRejected,
            "/home/../skald-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.PathTraversalRejected,
            "/home//skald-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.EmptyPathSegmentRejected,
            "/home/skald\u0007vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.ControlCharacterRejected,
            "/home/skald\u200bvaults" to SkaldVaultV1LinuxRootResolutionFailureReason.InvisibleFormatRejected,
            "/home/skaldväults" to SkaldVaultV1LinuxRootResolutionFailureReason.NonAsciiRejected,
            "/home/skald?vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.UnsupportedCharacterRejected,
            "file://skald-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.UriLikePrefixRejected,
            "https://skald.invalid/vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.UriLikePrefixRejected,
            "content://skald-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.UriLikePrefixRejected,
            "C:\\skald-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.WindowsDrivePrefixRejected,
            "\\\\server\\share\\skald" to SkaldVaultV1LinuxRootResolutionFailureReason.UncPathRejected,
            "my vault" to SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected,
            "user:pass@example" to SkaldVaultV1LinuxRootResolutionFailureReason.CredentialUserInfoRejected,
            "/home/password-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/token-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/$fakeLongHex" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/xprv_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/tprv_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/wif_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/nsec1_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/bc1_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/" + "a".repeat(4096) to SkaldVaultV1LinuxRootResolutionFailureReason.TooLong,
        )

        cases.forEach { (fixture, expectedNestedReason) ->
            val rejected = assertRejected(
                SkaldVaultV1PlatformRootResolverPolicy.resolve(
                    SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                        SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                            xdgDataHomeEvidence = fixture,
                        ),
                    ),
                ),
                SkaldVaultV1PlatformRootResolverFailureReason.LinuxRootEvidenceRejected,
                SkaldVaultV1PlatformRootResolverStatus.LinuxRootEvidenceRejected,
            )
            assertEquals(
                SkaldVaultV1LinuxRootResolutionFailureReason.DefaultUserDataBaseEvidenceRejected,
                rejected.linuxRootResolutionReason,
            )
            assertEquals(expectedNestedReason, rejected.linuxRootResolutionNestedReason)
            assertRedacted(rejected.toString(), fixture)
            assertRedacted(rejected.safeMessage, fixture)
        }
    }

    @Test
    fun renderingRedactsRootStringsAndSecretLookingEvidenceByDefault() {
        val acceptedFixture = "/home/skald-test-user/.local/share"
        val secretFixture = "/home/token-vaults"
        val acceptedRequest = SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
            SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                xdgDataHomeEvidence = acceptedFixture,
            ),
        )
        val rejectedRequest = SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
            SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                xdgDataHomeEvidence = secretFixture,
            ),
        )
        val accepted = SkaldVaultV1PlatformRootResolverPolicy.resolve(acceptedRequest)
        val rejected = SkaldVaultV1PlatformRootResolverPolicy.resolve(rejectedRequest)
        val evidence = assertAccepted(accepted)

        assertRedacted(acceptedRequest.toString(), acceptedFixture)
        assertRedacted(accepted.toString(), acceptedFixture)
        assertRedacted(evidence.toString(), acceptedFixture)
        assertRedacted(evidence.token.toString(), acceptedFixture)
        assertRedacted(rejectedRequest.toString(), secretFixture)
        assertRedacted(rejected.toString(), secretFixture)
        assertEquals(acceptedFixture, evidence.token.testOnlyRawStaticEvidence())
    }

    @Test
    fun acceptedResolverEvidenceCapabilitiesRemainUnavailable() {
        val android = assertAccepted(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence(),
            ),
        )
        val linux = assertAccepted(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                    SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                        xdgDataHomeEvidence = "/home/testuser/.local/share",
                    ),
                ),
            ),
        )
        val custom = assertAccepted(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxCustomRootCandidate(
                    "/srv/skald-test-vaults/user-data",
                ),
            ),
        )

        listOf(android, linux, custom).forEach { evidence ->
            assertEquals(SkaldVaultV1PlatformRootResolverCapability.StillDisabled, evidence.capability)
            assertEquals(SkaldVaultV1PlatformRootResolverBlocker.entries.toSet(), evidence.blockers)
            assertDisabledCapabilities(evidence.capability)
            assertFalse(evidence.platformPathObjectReturned)
            assertTrue(evidence.rootEvidenceOnly)
            assertTrue(evidence.platformRootResolverStillDisabled)
        }
    }

    @Test
    fun resolverEvidenceIsRepresentedButDoesNotEnablePersistenceOrSelection() {
        val acceptance = commonProductionProviderAcceptanceContract()
        val storage = acceptance.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = acceptance.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1PlatformRootResolverPolicy.POLICY_ID, storage.platformRootResolverPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.platformRootResolverBoundaryStatus,
        )
        assertEquals(ProductionProviderPlatformRootResolverRule.entries.toSet(), storage.platformRootResolverRules)
        assertTrue(storage.platformRootResolverBoundaryModeled)
        assertTrue(storage.androidAppPrivateRootEvidenceModeled)
        assertTrue(storage.linuxDefaultRootResolverEvidenceModeled)
        assertTrue(storage.platformRootResolverStillDisabled)
        assertTrue(storage.platformRootResolverDoesNotEnablePersistence)
        assertTrue(storage.platformRootResolverDoesNotProveDurability)
        assertTrue(storage.platformRootResolverDoesNotEnableProviderSelection)
        assertFalse(storage.platformRootResolutionImplemented)
        assertFalse(storage.platformRootSelectionImplemented)
        assertFalse(storage.actualPathConstructionImplemented)
        assertFalse(storage.pathContainmentCheckImplementationAdded)
        assertFalse(storage.symlinkCheckImplementationAdded)
        assertFalse(storage.permissionCheckImplementationAdded)
        assertFalse(storage.durabilityProbeImplementationAdded)
        assertFalse(storage.settingsUiImplemented)
        assertFalse(storage.settingsPersistenceImplemented)
        assertFalse(storage.platformStorageImplementationAdded)
        assertFalse(storage.vaultPersistenceImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PlatformRootResolverBoundaryImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.PlatformRootResolverBoundaryImplementedAndTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformRootResolverBoundaryBuildingBlock)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.PlatformRootResolverBoundaryImplementedTested,
        )
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.PlatformStorageRootImplementationMissing)
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertFalse(dependency.readyForVaultImplementation)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun assertAccepted(
        result: SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>,
    ): SkaldVaultV1PlatformRootResolverEvidence =
        when (result) {
            is SkaldVaultV1PlatformRootResolverResult.Accepted -> result.value
            is SkaldVaultV1PlatformRootResolverResult.Rejected ->
                error("expected accepted platform root evidence, got ${result.reason}")
        }

    private fun assertRejected(
        result: SkaldVaultV1PlatformRootResolverResult<*>,
        expectedReason: SkaldVaultV1PlatformRootResolverFailureReason,
        expectedStatus: SkaldVaultV1PlatformRootResolverStatus,
    ): SkaldVaultV1PlatformRootResolverResult.Rejected {
        assertTrue(
            result is SkaldVaultV1PlatformRootResolverResult.Rejected,
            "Expected rejection $expectedReason",
        )
        assertEquals(expectedReason, result.reason)
        assertEquals(expectedStatus, result.status)
        assertEquals(expectedReason.label, result.safeMessage)
        return result
    }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1PlatformRootResolverCapability) {
        assertFalse(capability.usableForPersistence)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.settingsPersistenceAvailable)
        assertFalse(capability.storageImplementationAvailable)
        assertFalse(capability.manifestReadWriteAvailable)
        assertFalse(capability.storageIndexReadWriteAvailable)
        assertFalse(capability.atomicWriteAvailable)
        assertFalse(capability.crashRecoveryAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.realPathContainmentVerified)
        assertFalse(capability.symlinkSafetyVerified)
        assertFalse(capability.permissionsVerified)
        assertFalse(capability.ownershipVerified)
        assertFalse(capability.durabilityVerified)
        assertFalse(capability.antiRollbackAnchorAvailable)
        assertFalse(capability.walletSyncAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertRedacted(rendered: String, rawFixture: String) {
        if (rawFixture.isBlank() || rawFixture == "/") return
        assertFalse(rendered.contains(rawFixture), "Rendered value must redact raw fixture: $rendered")
    }
}
