package com.libertasprimordium.skald

import com.libertasprimordium.skald.demo.DemoCoinControlRepository
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.CoinControlAcknowledgement
import com.libertasprimordium.skald.domain.onchain.CoinControlBlockingIssue
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftValidationError
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftWorkflow
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftState
import com.libertasprimordium.skald.domain.onchain.DemoUtxo
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletCreationIntent
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflow
import com.libertasprimordium.skald.domain.onchain.EditableCoinControlDraftInput
import com.libertasprimordium.skald.domain.onchain.EditableDescriptorWalletProfileInput
import com.libertasprimordium.skald.domain.onchain.PsbtDraftBlockingIssue
import com.libertasprimordium.skald.domain.onchain.PsbtDraftCapability
import com.libertasprimordium.skald.domain.onchain.PsbtDraftState
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.settings.CoinControlDraftSettingsCodec
import com.libertasprimordium.skald.settings.CoinControlDraftSettingsWriteResult
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

class CoinControlPsbtDraftWorkflowTest {
    private val secureStorageCapability = DisabledSecureSecretStorage().capability
    private val demoRepository = DemoCoinControlRepository()

    @Test
    fun draftCannotProceedWithoutSelectedWalletProfile() {
        val context = workflowContext()
        val review = CoinControlDraftWorkflow.review(
            input = validInput(context).copy(walletProfileId = null),
            walletSettings = context.walletSettings,
            demoUtxos = context.demoUtxos,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingWalletProfile)
        assertEquals(CoinControlDraftState.ChoosingWalletProfile, review.state)
        assertNull(review.draft)
    }

    @Test
    fun draftCannotProceedWithNonOperationalWalletProfileToRealPsbtConstruction() {
        val context = workflowContext()
        val draft = createdDraft(context)

        assertFalse(draft.isExecutable)
        assertTrue(draft.isNonOperationalDraft)
        assertContains(draft.coinControlBlockingIssues, CoinControlBlockingIssue.DescriptorWalletNonOperational)
        assertContains(draft.psbtBlockingIssues, PsbtDraftBlockingIssue.PsbtConstructionNotImplemented)
        assertEquals(PsbtDraftState.PsbtConstructionBlocked, draft.psbtDraftState)
        assertFalse(draft.psbtCreated)
    }

    @Test
    fun draftCannotProceedWithoutSelectedDemoUtxos() {
        val context = workflowContext()
        val review = CoinControlDraftWorkflow.review(
            input = validInput(context).copy(selectedDemoUtxoIds = emptySet()),
            walletSettings = context.walletSettings,
            demoUtxos = context.demoUtxos,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingSelectedDemoUtxos)
        assertEquals(CoinControlDraftState.SelectingInputs, review.state)
        assertNull(review.draft)
    }

    @Test
    fun demoUtxosAreMarkedNonRealAndNonSpendable() {
        val context = workflowContext()

        assertTrue(context.demoUtxos.isNotEmpty())
        assertTrue(context.demoUtxos.all { !it.isReal })
        assertTrue(context.demoUtxos.all { !it.isSpendable })
        assertTrue(context.demoUtxos.all { !it.outPoint.isRealOutPoint })
        assertTrue(context.demoUtxos.all { it.id.value.startsWith("DEMO_UTXO_ID_") })
        assertTrue(context.demoUtxos.all { it.outPoint.displayText.startsWith("DEMO_OUTPOINT_NOT_REAL_") })
    }

    @Test
    fun selectedDemoUtxosRoundTripThroughRepositoryAndCodec() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val context = workflowContext(repository = repository)
        val draft = createdDraft(context)

        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.saveCoinControlDraft(draft))
        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.selectCoinControlDraft(draft.id))
        val reloaded = PersistentSettingsRepository(storage).loadCoinControlDraftSettings()
        val serialized = assertNotNull(storage.readText(SettingsStorageKey.CoinControlDraftSettings))

        assertEquals(draft.id, reloaded.selectedDraftId)
        assertEquals(draft.selectedDemoUtxoIds, reloaded.drafts.single().selectedDemoUtxoIds)
        assertFalse(serialized.contains("DEMO_OUTPOINT_NOT_REAL"))
        assertFalse(serialized.contains("PSBT_NOT_CREATED"))
        assertFalse(serialized.contains("cHNidP"))
        assertFalse(serialized.contains("01000000"))
    }

    @Test
    fun outputMetadataAcceptsLabelAndNoteButNoAddressFieldExists() {
        val context = workflowContext()
        val draft = createdDraft(
            context,
            recipientNote = "Non-secret recipient planning note",
        )

        assertEquals("Non-secret recipient planning note", draft.outputDraft.recipientNote)
        assertEquals(21_000, draft.outputDraft.amountSats)
        assertFalse(draft.outputDraft.isAddressFieldAvailable)
        assertTrue(draft.outputDraft.warning.contains("Do not paste a Bitcoin address"))
    }

    @Test
    fun blankOrInvalidAmountIsRejected() {
        val context = workflowContext()
        val blank = CoinControlDraftWorkflow.review(
            validInput(context).copy(amountSatsText = ""),
            context.walletSettings,
            context.demoUtxos,
        )
        val invalid = CoinControlDraftWorkflow.review(
            validInput(context).copy(amountSatsText = "not-a-sats-value"),
            context.walletSettings,
            context.demoUtxos,
        )

        assertContains(blank.errors, CoinControlDraftValidationError.InvalidAmount)
        assertContains(invalid.errors, CoinControlDraftValidationError.InvalidAmount)
        assertNull(blank.draft)
        assertNull(invalid.draft)
    }

    @Test
    fun amountLargerThanSelectedDemoUtxoSumIsRejected() {
        val context = workflowContext()
        val selectedTotal = context.demoUtxos
            .filter { validInput(context).selectedDemoUtxoIds.contains(it.id) }
            .sumOf { it.amountSats }
        val review = CoinControlDraftWorkflow.review(
            validInput(context).copy(amountSatsText = (selectedTotal + 1).toString()),
            context.walletSettings,
            context.demoUtxos,
        )

        assertContains(review.errors, CoinControlDraftValidationError.AmountExceedsSelectedDemoTotal)
        assertNull(review.draft)
    }

    @Test
    fun feeAndChangeReviewAcknowledgementIsRequired() {
        val context = workflowContext()
        val review = reviewMissingAcknowledgement(
            context,
            CoinControlAcknowledgement.FeeChangeReviewAcknowledged,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingRequiredAcknowledgement)
        assertEquals(CoinControlDraftState.NeedsFeeChangeAcknowledgement, review.state)
        assertNull(review.draft)
    }

    @Test
    fun coinControlAcknowledgementIsRequired() {
        val context = workflowContext()
        val review = reviewMissingAcknowledgement(
            context,
            CoinControlAcknowledgement.ManualInputReviewAcknowledged,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingRequiredAcknowledgement)
        assertEquals(CoinControlDraftState.NeedsCoinControlAcknowledgement, review.state)
        assertNull(review.draft)
    }

    @Test
    fun signingDisabledAcknowledgementIsRequired() {
        val context = workflowContext()
        val review = reviewMissingAcknowledgement(
            context,
            CoinControlAcknowledgement.SigningDisabledAcknowledged,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingRequiredAcknowledgement)
        assertEquals(CoinControlDraftState.BlockedBySigningDisabled, review.state)
        assertNull(review.draft)
    }

    @Test
    fun broadcastDisabledAcknowledgementIsRequired() {
        val context = workflowContext()
        val review = reviewMissingAcknowledgement(
            context,
            CoinControlAcknowledgement.BroadcastDisabledAcknowledged,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingRequiredAcknowledgement)
        assertEquals(CoinControlDraftState.BlockedByBroadcastDisabled, review.state)
        assertNull(review.draft)
    }

    @Test
    fun missingAcknowledgementPreventsFinalDraftState() {
        val context = workflowContext()
        val review = CoinControlDraftWorkflow.review(
            input = validInput(context).copy(acknowledgements = emptySet()),
            walletSettings = context.walletSettings,
            demoUtxos = context.demoUtxos,
        )

        assertContains(review.errors, CoinControlDraftValidationError.MissingRequiredAcknowledgement)
        assertFalse(review.canSaveDraftMetadata)
        assertNull(review.draft)
    }

    @Test
    fun completedAcknowledgementsStillEndInPsbtConstructionBlocked() {
        val context = workflowContext()
        val draft = createdDraft(context)

        assertEquals(CoinControlDraftWorkflow.RequiredAcknowledgements, draft.acknowledgements)
        assertEquals(PsbtDraftState.PsbtConstructionBlocked, draft.psbtDraftState)
        assertContains(draft.psbtBlockingIssues, PsbtDraftBlockingIssue.PsbtConstructionNotImplemented)
        assertContains(draft.psbtCapabilities, PsbtDraftCapability.PsbtExportDisabled)
        assertContains(draft.psbtCapabilities, PsbtDraftCapability.PsbtImportDisabled)
        assertFalse(draft.signingEnabled)
        assertFalse(draft.broadcastEnabled)
        assertFalse(draft.exportEnabled)
    }

    @Test
    fun noValidPsbtStringIsCreatedOrSerialized() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val context = workflowContext(repository = repository)
        val draft = createdDraft(context)

        assertEquals("PSBT_NOT_CREATED", draft.placeholderPsbt)
        assertFalse(draft.psbtCreated)
        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.saveCoinControlDraft(draft))
        val serialized = assertNotNull(storage.readText(SettingsStorageKey.CoinControlDraftSettings))

        assertFalse(serialized.contains("PSBT_NOT_CREATED"))
        assertFalse(serialized.contains("cHNidP"))
        assertFalse(serialized.contains("psbt", ignoreCase = true) && serialized.contains("created", ignoreCase = true))
    }

    @Test
    fun noTxidAddressTransactionHexFixtureIsAddedToDraftOrSerialization() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val context = workflowContext(repository = repository)
        val draft = createdDraft(context)

        assertTrue(draft.selectedInputs.all { !looksLikeRealTxid(it.outPoint.displayText) })
        assertFalse(draft.outputDraft.isAddressFieldAvailable)
        assertFalse(draft.outputDraft.recipientNote.contains("bc1", ignoreCase = true))
        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.saveCoinControlDraft(draft))
        val serialized = assertNotNull(storage.readText(SettingsStorageKey.CoinControlDraftSettings))

        assertFalse(serialized.contains("DEMO_OUTPOINT_NOT_REAL"))
        assertFalse(serialized.contains("bc1", ignoreCase = true))
        assertFalse(serialized.contains("tb1", ignoreCase = true))
        assertFalse(serialized.contains("bcrt1", ignoreCase = true))
        assertFalse(serialized.contains("txid", ignoreCase = true))
    }

    @Test
    fun nostrRelatedWalletProfileTriggersIdentityLinkageWarning() {
        val context = workflowContext(DescriptorWalletCreationIntent.NostrNsecSpend)
        val draft = createdDraft(context)

        assertTrue(draft.privacyWarnings.any { it.title.contains("Nostr identity linkage", ignoreCase = true) })
    }

    @Test
    fun importedKeyWalletProfileTriggersImportedKeyWarning() {
        val context = workflowContext(DescriptorWalletCreationIntent.ImportedSingleKeyTaproot)
        val draft = createdDraft(context)

        assertTrue(draft.privacyWarnings.any { it.title.contains("Imported-key profile", ignoreCase = true) })
    }

    @Test
    fun deletingSelectedDraftClearsSelectedState() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val context = workflowContext(repository = repository)
        val draft = createdDraft(context)

        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.saveCoinControlDraft(draft))
        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.selectCoinControlDraft(draft.id))
        assertIs<CoinControlDraftSettingsWriteResult.Saved>(repository.deleteCoinControlDraft(draft.id))
        val reloaded = PersistentSettingsRepository(storage).loadCoinControlDraftSettings()

        assertTrue(reloaded.drafts.isEmpty())
        assertNull(reloaded.selectedDraftId)
    }

    @Test
    fun malformedCoinControlDraftSettingsFailSafelyToEmptyState() {
        val decoded = CoinControlDraftSettingsCodec.decode("skald.coin-control-draft-settings.v1\ndraft|bad")

        assertTrue(decoded.drafts.isEmpty())
        assertNull(decoded.selectedDraftId)
    }

    private fun workflowContext(
        intent: DescriptorWalletCreationIntent = DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
        repository: PersistentSettingsRepository = PersistentSettingsRepository(InMemorySettingsStorage()),
    ): WorkflowContext {
        val profile = assertNotNull(
            DescriptorWalletWorkflow.review(
                input = EditableDescriptorWalletProfileInput(
                    label = "${intent.name} profile",
                    intent = intent,
                    network = NetworkEnvironment.Testnet4,
                    acknowledgements = DescriptorWalletWorkflow.requiredAcknowledgementsFor(intent),
                ),
                secureStorageCapability = secureStorageCapability,
            ).profile,
        )
        assertIs<DescriptorWalletSettingsWriteResult.Saved>(repository.saveDescriptorWalletProfile(profile))
        val walletSettings = repository.loadDescriptorWalletSettings()
        val demoUtxos = demoRepository.demoUtxosFor(walletSettings.profiles)
        return WorkflowContext(walletSettings, demoUtxos)
    }

    private fun validInput(
        context: WorkflowContext,
        recipientNote: String = "Non-secret recipient planning note",
    ): EditableCoinControlDraftInput {
        val profile = context.walletSettings.profiles.single()
        val selectedDemoUtxoIds = context.demoUtxos
            .filter { it.walletProfileId == profile.id }
            .take(1)
            .map { it.id }
            .toSet()
        return EditableCoinControlDraftInput(
            label = "Non-operational draft metadata",
            walletProfileId = profile.id,
            selectedDemoUtxoIds = selectedDemoUtxoIds,
            recipientNote = recipientNote,
            amountSatsText = "21000",
            acknowledgements = CoinControlDraftWorkflow.RequiredAcknowledgements,
        )
    }

    private fun createdDraft(
        context: WorkflowContext,
        recipientNote: String = "Non-secret recipient planning note",
    ) = assertNotNull(
        CoinControlDraftWorkflow.review(
            input = validInput(context, recipientNote),
            walletSettings = context.walletSettings,
            demoUtxos = context.demoUtxos,
        ).draft,
    )

    private fun reviewMissingAcknowledgement(
        context: WorkflowContext,
        missing: CoinControlAcknowledgement,
    ) = CoinControlDraftWorkflow.review(
        input = validInput(context).copy(
            acknowledgements = CoinControlDraftWorkflow.RequiredAcknowledgements - missing,
        ),
        walletSettings = context.walletSettings,
        demoUtxos = context.demoUtxos,
    )

    private fun looksLikeRealTxid(value: String): Boolean =
        value.length == 64 && value.all { character ->
            character in '0'..'9' || character in 'a'..'f' || character in 'A'..'F'
        }

    private data class WorkflowContext(
        val walletSettings: DescriptorWalletSettingsState,
        val demoUtxos: List<DemoUtxo>,
    )
}
