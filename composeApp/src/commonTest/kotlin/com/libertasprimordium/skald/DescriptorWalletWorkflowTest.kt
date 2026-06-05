package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletBlockingIssue
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletCapability
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletCreationIntent
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileStatus
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletRiskAcknowledgement
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSpendPolicy
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletValidationError
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflow
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflowState
import com.libertasprimordium.skald.domain.onchain.EditableDescriptorWalletProfileInput
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.settings.DescriptorWalletSettingsCodec
import com.libertasprimordium.skald.settings.DescriptorWalletSettingsWriteResult
import com.libertasprimordium.skald.settings.InMemorySettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository
import com.libertasprimordium.skald.settings.SettingsStorageKey
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DescriptorWalletWorkflowTest {
    private val disabledStorageCapability = DisabledSecureSecretStorage().capability

    @Test
    fun nativeDescriptorMetadataIsBlockedFromOperationalStatusBecauseSecretStorageIsDisabled() {
        val profile = createdProfile(DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed)

        assertFalse(profile.isOperational)
        assertContains(profile.blockingIssues, DescriptorWalletBlockingIssue.SecretStorageDisabled)
        assertContains(profile.blockingIssues, DescriptorWalletBlockingIssue.KeyMaterialNotImplemented)
        assertEquals(DescriptorWalletWorkflowState.BlockedBySecretStorageDisabled, profile.workflowState)
    }

    @Test
    fun nativeDescriptorMetadataCanCreateOnlyNonOperationalProfileMetadata() {
        val profile = createdProfile(DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed)

        assertEquals(DescriptorWalletProfileStatus.MissingKeyMaterial, profile.profileStatus)
        assertEquals("DESCRIPTOR_TEXT_NOT_STORED", profile.descriptorTextState)
        assertEquals("KEY_MATERIAL_NOT_CREATED", profile.keyMaterialState)
        assertFalse(profile.containsWalletMaterial)
        assertDisabledOperationalCapabilities(profile)
    }

    @Test
    fun blankProfileLabelIsRejected() {
        val review = reviewFor(
            input = validInput(
                intent = DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
                label = " ",
            ),
        )

        assertContains(review.errors, DescriptorWalletValidationError.BlankLabel)
        assertNull(review.profile)
    }

    @Test
    fun mainnetNetworkIsRejectedAndBlocked() {
        val review = reviewFor(
            input = validInput(
                intent = DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
                network = NetworkEnvironment.MainnetDisabled,
            ),
        )

        assertContains(review.errors, DescriptorWalletValidationError.MainnetDisabled)
        assertEquals(DescriptorWalletWorkflowState.BlockedByMainnetDisabled, review.state)
        assertNull(review.profile)
    }

    @Test
    fun importedDescriptorMetadataIsBlockedByDescriptorValidationAndStoresNoDescriptorText() {
        val profile = createdProfile(DescriptorWalletCreationIntent.ImportedDescriptor)

        assertContains(profile.blockingIssues, DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented)
        assertEquals(DescriptorWalletSpendPolicy.DisabledDescriptorValidation, profile.spendPolicy)
        assertEquals("DESCRIPTOR_TEXT_NOT_STORED", profile.descriptorTextState)
        assertFalse(profile.containsWalletMaterial)
    }

    @Test
    fun watchOnlyDescriptorProfileCannotSpend() {
        val profile = createdProfile(DescriptorWalletCreationIntent.WatchOnlyDescriptor)

        assertEquals(DescriptorWalletSpendPolicy.WatchOnlyCannotSpend, profile.spendPolicy)
        assertContains(profile.blockingIssues, DescriptorWalletBlockingIssue.WatchOnlyCannotSpend)
        assertContains(profile.capabilities, DescriptorWalletCapability.WatchOnlyCannotSpend)
        assertFalse(profile.canSpendNow)
    }

    @Test
    fun importedSingleKeyProfileRequiresSeparateBackupAcknowledgement() {
        val missing = reviewFor(
            validInput(
                intent = DescriptorWalletCreationIntent.ImportedSingleKeyTaproot,
                acknowledgements = emptySet(),
            ),
        )

        assertContains(missing.errors, DescriptorWalletValidationError.MissingRequiredAcknowledgement)
        assertNull(missing.profile)

        val profile = createdProfile(DescriptorWalletCreationIntent.ImportedSingleKeyTaproot)
        assertContains(
            profile.riskAcknowledgements,
            DescriptorWalletRiskAcknowledgement.SeparateImportedKeyBackupAcknowledged,
        )
        assertContains(profile.blockingIssues, DescriptorWalletBlockingIssue.KeyMaterialNotImplemented)
    }

    @Test
    fun nostrNpubWatchOnlyProfileCannotSpendAndRequiresWatchOnlyAcknowledgement() {
        val missing = reviewFor(
            validInput(
                intent = DescriptorWalletCreationIntent.NostrNpubWatchOnly,
                acknowledgements = emptySet(),
            ),
        )

        assertContains(missing.errors, DescriptorWalletValidationError.MissingRequiredAcknowledgement)
        assertEquals(DescriptorWalletWorkflowState.NeedsRiskAcknowledgement, missing.state)

        val profile = createdProfile(DescriptorWalletCreationIntent.NostrNpubWatchOnly)
        assertEquals(DescriptorWalletSpendPolicy.WatchOnlyCannotSpend, profile.spendPolicy)
        assertContains(
            profile.riskAcknowledgements,
            DescriptorWalletRiskAcknowledgement.WatchOnlyCannotSpendAcknowledged,
        )
        assertFalse(profile.canSpendNow)
    }

    @Test
    fun nostrNsecSpendProfileRequiresIdentityKeyRiskAcknowledgement() {
        val missing = reviewFor(
            validInput(
                intent = DescriptorWalletCreationIntent.NostrNsecSpend,
                acknowledgements = setOf(
                    DescriptorWalletRiskAcknowledgement.SeparateImportedKeyBackupAcknowledged,
                ),
            ),
        )

        assertContains(missing.errors, DescriptorWalletValidationError.MissingRequiredAcknowledgement)
        assertEquals(DescriptorWalletWorkflowState.NeedsRiskAcknowledgement, missing.state)

        val profile = createdProfile(DescriptorWalletCreationIntent.NostrNsecSpend)
        assertContains(
            profile.riskAcknowledgements,
            DescriptorWalletRiskAcknowledgement.IdentityKeyReuseWarningAcknowledged,
        )
        assertContains(profile.blockingIssues, DescriptorWalletBlockingIssue.SecretStorageDisabled)
        assertFalse(profile.isOperational)
    }

    @Test
    fun missingRequiredAcknowledgementPreventsProfileMetadataCreation() {
        val review = reviewFor(
            validInput(
                intent = DescriptorWalletCreationIntent.ImportedDescriptor,
                acknowledgements = emptySet(),
            ),
        )

        assertContains(review.errors, DescriptorWalletValidationError.MissingRequiredAcknowledgement)
        assertFalse(review.canCreateProfileMetadata)
        assertNull(review.profile)
    }

    @Test
    fun completedRequiredAcknowledgementAllowsMetadataOnlyProfileCreation() {
        val review = reviewFor(validInput(DescriptorWalletCreationIntent.ImportedDescriptor))
        val profile = assertNotNull(review.profile)

        assertTrue(review.canCreateProfileMetadata)
        assertTrue(review.errors.isEmpty())
        assertContains(
            profile.riskAcknowledgements,
            DescriptorWalletRiskAcknowledgement.DescriptorMaterialNotStoredAcknowledged,
        )
        assertFalse(profile.isOperational)
    }

    @Test
    fun createdProfilesContainNoWalletMaterial() {
        DescriptorWalletCreationIntent.entries.forEach { intent ->
            val profile = createdProfile(intent)

            assertEquals("DESCRIPTOR_TEXT_NOT_STORED", profile.descriptorTextState)
            assertEquals("KEY_MATERIAL_NOT_CREATED", profile.keyMaterialState)
            assertFalse(profile.containsWalletMaterial)
            assertFalse(profile.isOperational)
        }
    }

    @Test
    fun savedProfileMetadataRoundTripsThroughRepositoryAndCodec() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val profile = createdProfile(DescriptorWalletCreationIntent.WatchOnlyDescriptor)

        assertIs<DescriptorWalletSettingsWriteResult.Saved>(repository.saveDescriptorWalletProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadDescriptorWalletSettings()
        val serialized = assertNotNull(storage.readText(SettingsStorageKey.DescriptorWalletSettings))

        assertEquals(1, reloaded.profiles.size)
        assertEquals(profile.id, reloaded.profiles.single().id)
        assertEquals(profile.origin, reloaded.profiles.single().origin)
        assertEquals("DESCRIPTOR_TEXT_NOT_STORED", reloaded.profiles.single().descriptorTextState)
        assertEquals("KEY_MATERIAL_NOT_CREATED", reloaded.profiles.single().keyMaterialState)
        assertFalse(serialized.contains("DESCRIPTOR_TEXT_NOT_STORED"))
        assertFalse(serialized.contains("KEY_MATERIAL_NOT_CREATED"))
        assertFalse(serialized.contains("NOT_A_REAL_SECRET"))
        assertFalse(serialized.contains("DEMO_VALUE_DO_NOT_USE"))
        assertFalse(serialized.contains("PSBT_NOT_CREATED"))
    }

    @Test
    fun deletingSelectedProfileClearsSelectedState() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val profile = createdProfile(DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed)

        assertIs<DescriptorWalletSettingsWriteResult.Saved>(repository.saveDescriptorWalletProfile(profile))
        assertIs<DescriptorWalletSettingsWriteResult.Saved>(repository.selectDescriptorWalletProfile(profile.id))
        assertIs<DescriptorWalletSettingsWriteResult.Saved>(repository.deleteDescriptorWalletProfile(profile.id))
        val reloaded = PersistentSettingsRepository(storage).loadDescriptorWalletSettings()

        assertTrue(reloaded.profiles.isEmpty())
        assertNull(reloaded.selectedProfileId)
    }

    @Test
    fun noProfileCapabilityEnablesReceiveSpendExportSignOrBroadcastInThisPass() {
        DescriptorWalletCreationIntent.entries.forEach { intent ->
            val profile = createdProfile(intent)

            assertDisabledOperationalCapabilities(profile)
        }
    }

    @Test
    fun secureStorageDisabledBlocksAnyKeyMaterialCreatedState() {
        val native = createdProfile(DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed)
        val nostr = createdProfile(DescriptorWalletCreationIntent.NostrNsecSpend)

        assertContains(native.blockingIssues, DescriptorWalletBlockingIssue.SecretStorageDisabled)
        assertContains(nostr.blockingIssues, DescriptorWalletBlockingIssue.SecretStorageDisabled)
        assertEquals("KEY_MATERIAL_NOT_CREATED", native.keyMaterialState)
        assertEquals("KEY_MATERIAL_NOT_CREATED", nostr.keyMaterialState)
    }

    @Test
    fun malformedDescriptorWalletSettingsFailSafelyToEmptyState() {
        val decoded = DescriptorWalletSettingsCodec.decode("skald.descriptor-wallet-settings.v1\nprofile|bad")

        assertTrue(decoded.profiles.isEmpty())
        assertNull(decoded.selectedProfileId)
    }

    private fun createdProfile(
        intent: DescriptorWalletCreationIntent,
    ) = assertNotNull(
        reviewFor(validInput(intent)).profile,
        "Expected metadata profile for $intent",
    )

    private fun reviewFor(
        input: EditableDescriptorWalletProfileInput,
    ) = DescriptorWalletWorkflow.review(input, disabledStorageCapability)

    private fun validInput(
        intent: DescriptorWalletCreationIntent,
        label: String = "${intent.name} metadata",
        network: NetworkEnvironment = NetworkEnvironment.Testnet4,
        acknowledgements: Set<DescriptorWalletRiskAcknowledgement> =
            DescriptorWalletWorkflow.requiredAcknowledgementsFor(intent),
    ): EditableDescriptorWalletProfileInput =
        EditableDescriptorWalletProfileInput(
            label = label,
            intent = intent,
            network = network,
            acknowledgements = acknowledgements,
        )

    private fun assertDisabledOperationalCapabilities(
        profile: com.libertasprimordium.skald.domain.onchain.DescriptorWalletMetadataProfile,
    ) {
        assertFalse(profile.canReceiveNow)
        assertFalse(profile.canSpendNow)
        assertFalse(profile.canExportDescriptorNow)
        assertFalse(profile.canBuildPsbtNow)
        assertFalse(profile.canSignNow)
        assertFalse(profile.canBroadcastNow)
    }
}
