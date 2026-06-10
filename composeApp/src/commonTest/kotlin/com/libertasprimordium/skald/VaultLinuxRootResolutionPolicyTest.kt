package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderLinuxRootResolutionRule
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootCandidate
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationResult
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionEvidenceKind
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionResult
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionSource
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootTokenKind
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

class VaultLinuxRootResolutionPolicyTest {
    @Test
    fun linuxRootResolutionPolicyIdAndConventionAreStable() {
        val platformPolicy = SkaldVaultV1PlatformRootSettingsPolicy.currentContract()

        assertEquals(
            "skald-vault-v1-linux-root-resolution-policy-v1",
            SkaldVaultV1LinuxRootResolutionPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1LinuxRootResolutionPolicy.POLICY_VERSION)
        assertEquals("~/.local/share/", SkaldVaultV1LinuxRootResolutionPolicy.FUTURE_LINUX_USER_DATA_CONVENTION)
        assertEquals(
            SkaldVaultV1LinuxRootResolutionPolicy.POLICY_ID,
            platformPolicy.linuxRootResolutionPolicyId,
        )
        assertTrue(platformPolicy.linuxRootResolutionEvidenceModeled)
        assertTrue(platformPolicy.linuxRootResolutionStillDisabled)
        assertTrue(platformPolicy.linuxRootResolutionDoesNotResolveFilesystem)
        assertTrue(platformPolicy.linuxRootResolutionDoesNotConstructPaths)
        assertTrue(platformPolicy.linuxRootResolutionDoesNotEnablePersistence)
        assertFalse(platformPolicy.linuxRootResolutionImplemented)
        assertFalse(platformPolicy.settingsUiImplemented)
        assertFalse(platformPolicy.settingsPersistenceImplemented)
        assertFalse(platformPolicy.actualPathConstructionImplemented)
        assertFalse(platformPolicy.storageImplementationImplemented)
    }

    @Test
    fun noEvidenceReturnsUnavailableBlockedResult() {
        val noEvidence = SkaldVaultV1LinuxRootResolutionPolicy.evaluate(
            SkaldVaultV1LinuxRootResolutionRequest.noEvidence(),
        )
        val noDefault = SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
            defaultUserDataBaseEvidence = null,
        )
        val noCustom = SkaldVaultV1LinuxRootResolutionPolicy.evaluateCustomRootCandidate(candidate = null)

        assertRejected(
            noEvidence,
            SkaldVaultV1LinuxRootResolutionFailureReason.NoCandidateEvidenceSupplied,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.None,
        )
        assertRejected(
            noDefault,
            SkaldVaultV1LinuxRootResolutionFailureReason.DefaultLinuxRootEvidenceUnavailable,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
        )
        assertRejected(
            noCustom,
            SkaldVaultV1LinuxRootResolutionFailureReason.NoCandidateEvidenceSupplied,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
        )
    }

    @Test
    fun callerSuppliedDefaultUserDataEvidenceProducesStillDisabledPlanOnly() {
        val fixture = "/home/skald-test-user/.local/share"
        val request = SkaldVaultV1LinuxRootResolutionRequest.defaultUserDataRoot(
            defaultHomeEvidence = "/home/skald-test-user",
            defaultUserDataBaseEvidence = fixture,
        )
        val evidence = assertAccepted(SkaldVaultV1LinuxRootResolutionPolicy.evaluate(request))

        assertEquals(SkaldVaultV1LinuxRootResolutionPolicy.POLICY_ID, evidence.policyId)
        assertEquals(SkaldVaultV1LinuxRootResolutionStatus.DefaultRootAcceptedStillDisabled, evidence.status)
        assertEquals(
            SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticDefaultUserDataEvidence,
            evidence.source,
        )
        assertEquals(SkaldVaultV1LinuxRootTokenKind.DefaultLinuxUserDataRoot, evidence.token.kind)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
        assertFalse(evidence.token.containsResolvedPlatformPath)
        assertFalse(evidence.token.usableForFileIo)
        assertFalse(evidence.rawRootStringExposedByDefault)
        assertContains(evidence.blockers, SkaldVaultV1LinuxRootResolutionBlocker.AcceptedButPersistenceBlocked)
        assertDisabledCapabilities(evidence.capability)
        assertRedacted(request.toString(), fixture)
        assertRedacted(evidence.toString(), fixture)
        assertRedacted(evidence.token.toString(), fixture)
    }

    @Test
    fun acceptedCustomRootValidationProducesStillDisabledPlanOnly() {
        val fixture = "/srv/skald-test-vaults/user-data"
        val customCandidate = assertCustomRootValidationAccepted(fixture)
        val evidence = assertAccepted(
            SkaldVaultV1LinuxRootResolutionPolicy.evaluateAcceptedCustomRootValidation(customCandidate),
        )

        assertEquals(SkaldVaultV1LinuxRootResolutionStatus.CustomRootAcceptedStillDisabled, evidence.status)
        assertEquals(
            SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticCustomRootEvidence,
            evidence.source,
        )
        assertEquals(SkaldVaultV1LinuxRootTokenKind.CustomLinuxCandidateRoot, evidence.token.kind)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
        assertFalse(evidence.token.containsResolvedPlatformPath)
        assertFalse(evidence.token.usableForFileIo)
        assertDisabledCapabilities(evidence.capability)
    }

    @Test
    fun rejectedCustomRootValidationRemainsRejectedAndPreservesFailureEvidence() {
        val result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateCustomRootCandidate("/tmp/skald-vaults")

        val rejected = assertRejected(
            result,
            SkaldVaultV1LinuxRootResolutionFailureReason.CustomRootValidationFailed,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
        )
        assertEquals(
            SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected,
            rejected.customRootValidationFailureReason,
        )
        assertFalse(rejected.toString().contains("/tmp/skald-vaults"))
    }

    @Test
    fun customRootResolutionCanRejectValidatorAcceptedButUnsafeEvidence() {
        val customCandidate = assertCustomRootValidationAccepted("/etc/skald-vaults")
        val result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateAcceptedCustomRootValidation(customCandidate)

        val rejected = assertRejected(
            result,
            SkaldVaultV1LinuxRootResolutionFailureReason.CustomRootResolutionEvidenceRejected,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
        )
        assertEquals(SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected, rejected.nestedReason)
        assertFalse(rejected.toString().contains("/etc/skald-vaults"))
    }

    @Test
    fun unsafeDefaultEvidenceFailsClosedWithTypedNestedReasons() {
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
            "/dev/shm" to SkaldVaultV1LinuxRootResolutionFailureReason.TempRootRejected,
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
            "/Volumes" to SkaldVaultV1LinuxRootResolutionFailureReason.RemovableMediaRootRejected,
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
            "wallet one" to SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected,
            "cold storage" to SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected,
            "user:pass@example" to SkaldVaultV1LinuxRootResolutionFailureReason.CredentialUserInfoRejected,
            "/home/password-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/token-vaults" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/$fakeLongHex" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/xprv_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/tprv_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/wif_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/nsec1_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/home/bc1_invalid_fixture" to SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            "/" + "a".repeat(SkaldVaultV1LinuxRootResolutionPolicy.MAX_EVIDENCE_BYTES) to
                SkaldVaultV1LinuxRootResolutionFailureReason.TooLong,
        )

        cases.forEach { (fixture, expectedNestedReason) ->
            val rejected = assertRejected(
                SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                    defaultUserDataBaseEvidence = fixture,
                ),
                SkaldVaultV1LinuxRootResolutionFailureReason.DefaultUserDataBaseEvidenceRejected,
                SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
            )
            assertEquals(expectedNestedReason, rejected.nestedReason, "Fixture should fail closed: $fixture")
            assertRedacted(rejected.toString(), fixture)
            assertRedacted(rejected.safeMessage, fixture)
        }
    }

    @Test
    fun defaultHomeEvidenceRejectionIsDistinguishedFromUserDataBaseRejection() {
        val result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
            defaultHomeEvidence = "my vault label",
            defaultUserDataBaseEvidence = "/home/skald-test-user/.local/share",
        )

        val rejected = assertRejected(
            result,
            SkaldVaultV1LinuxRootResolutionFailureReason.DefaultHomeEvidenceRejected,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultHomeEvidence,
        )
        assertEquals(SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected, rejected.nestedReason)
    }

    @Test
    fun sourceClassificationRejectsLabelsSecretsAndRealResolutionNeeds() {
        assertRejected(
            SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                defaultUserDataBaseEvidence = "/home/skald-test-user/.local/share",
                source = SkaldVaultV1LinuxRootResolutionSource.UserDisplayLabel,
            ),
            SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
        )
        assertRejected(
            SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                defaultUserDataBaseEvidence = "/home/skald-test-user/.local/share",
                source = SkaldVaultV1LinuxRootResolutionSource.SecretMaterialCandidate,
            ),
            SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
        )
        assertRejected(
            SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                defaultUserDataBaseEvidence = "/home/skald-test-user/.local/share",
                source = SkaldVaultV1LinuxRootResolutionSource.RequiresRealPlatformResolution,
            ),
            SkaldVaultV1LinuxRootResolutionFailureReason.RequiresRealPlatformResolution,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
        )
    }

    @Test
    fun renderingAndDiagnosticsRedactRawRootEvidenceByDefault() {
        val fixture = "/home/skald-test-user/.local/share"
        val rejectedSecretFixture = "/home/token-vaults"
        val accepted = SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
            defaultUserDataBaseEvidence = fixture,
        )
        val rejected = SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
            defaultUserDataBaseEvidence = rejectedSecretFixture,
        )
        val request = SkaldVaultV1LinuxRootResolutionRequest.defaultUserDataRoot(
            defaultUserDataBaseEvidence = fixture,
        )
        val evidence = assertAccepted(accepted)

        assertRedacted(request.toString(), fixture)
        assertRedacted(accepted.toString(), fixture)
        assertRedacted(rejected.toString(), rejectedSecretFixture)
        assertRedacted(evidence.toString(), fixture)
        assertRedacted(evidence.token.toString(), fixture)
        assertEquals(fixture, evidence.token.testOnlyRawStaticEvidence())
    }

    @Test
    fun acceptedEvidenceCapabilityFlagsRemainUnavailable() {
        val evidence = assertAccepted(
            SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                defaultUserDataBaseEvidence = "/home/testuser/.local/share",
            ),
        )

        assertEquals(SkaldVaultV1LinuxRootResolutionCapability.StillDisabled, evidence.capability)
        assertDisabledCapabilities(evidence.capability)
        assertEquals(SkaldVaultV1LinuxRootResolutionBlocker.entries.toSet(), evidence.blockers)
    }

    @Test
    fun rootResolutionEvidenceIsRepresentedButDoesNotEnablePersistenceOrSelection() {
        val acceptance = commonProductionProviderAcceptanceContract()
        val storage = acceptance.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = acceptance.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1LinuxRootResolutionPolicy.POLICY_ID, storage.linuxRootResolutionPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.linuxRootResolutionPolicyStatus,
        )
        assertEquals(ProductionProviderLinuxRootResolutionRule.entries.toSet(), storage.linuxRootResolutionRules)
        assertTrue(storage.linuxRootResolutionEvidenceModeled)
        assertTrue(storage.linuxRootResolutionStillDisabled)
        assertTrue(storage.linuxRootResolutionDoesNotResolveFilesystem)
        assertTrue(storage.linuxRootResolutionDoesNotConstructPaths)
        assertTrue(storage.linuxRootResolutionDoesNotEnablePersistence)
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
            evidence.stateFor(ProductionProviderAcceptanceGate.LinuxRootResolutionPolicyImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.LinuxRootResolutionPolicyImplementedAndTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.LinuxRootResolutionPolicyBuildingBlock)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.readyForProductionPersistence)

        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.LinuxRootResolutionPolicyImplementedTested,
        )
        assertFalse(dependency.blockers.contains(VaultCryptoDependencyBlocker.LinuxCustomRootValidationMissing))
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertFalse(dependency.readyForVaultImplementation)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun assertCustomRootValidationAccepted(candidate: String): SkaldVaultV1LinuxCustomRootCandidate =
        when (val result = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(candidate)) {
            is SkaldVaultV1LinuxCustomRootValidationResult.Accepted -> result.value
            is SkaldVaultV1LinuxCustomRootValidationResult.Rejected ->
                error("expected custom-root validation to accept fixture, got ${result.reason}")
        }

    private fun assertAccepted(
        result: SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence>,
    ): SkaldVaultV1LinuxRootResolutionEvidence =
        when (result) {
            is SkaldVaultV1LinuxRootResolutionResult.Accepted -> result.value
            is SkaldVaultV1LinuxRootResolutionResult.Rejected ->
                error("expected accepted root-resolution evidence, got ${result.reason}")
        }

    private fun assertRejected(
        result: SkaldVaultV1LinuxRootResolutionResult<*>,
        expectedReason: SkaldVaultV1LinuxRootResolutionFailureReason,
        expectedEvidenceKind: SkaldVaultV1LinuxRootResolutionEvidenceKind,
    ): SkaldVaultV1LinuxRootResolutionResult.Rejected {
        assertTrue(
            result is SkaldVaultV1LinuxRootResolutionResult.Rejected,
            "Expected rejection $expectedReason",
        )
        assertEquals(expectedReason, result.reason)
        assertEquals(expectedEvidenceKind, result.evidenceKind)
        assertEquals(expectedReason.label, result.safeMessage)
        return result
    }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1LinuxRootResolutionCapability) {
        assertFalse(capability.usableForPersistence)
        assertFalse(capability.resolvedOnFilesystem)
        assertFalse(capability.pathConstructed)
        assertFalse(capability.settingsPersistenceAvailable)
        assertFalse(capability.safePathConstructionAvailable)
        assertFalse(capability.containmentVerified)
        assertFalse(capability.symlinkSafetyVerified)
        assertFalse(capability.permissionsVerified)
        assertFalse(capability.ownershipVerified)
        assertFalse(capability.durabilityVerified)
        assertFalse(capability.atomicWriteVerified)
        assertFalse(capability.manifestReadWriteAvailable)
        assertFalse(capability.storageIndexReadWriteAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertRedacted(rendered: String, rawFixture: String) {
        if (rawFixture.isBlank() || rawFixture == "/") return
        assertFalse(rendered.contains(rawFixture), "Rendered value must redact raw fixture: $rendered")
    }
}
