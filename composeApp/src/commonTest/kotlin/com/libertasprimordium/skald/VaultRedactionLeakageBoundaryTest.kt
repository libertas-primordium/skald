package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderRedactionLeakageRule
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionOutputTarget
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionScope
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionValueKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSourceGuardMaterialClass
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

class VaultRedactionLeakageBoundaryTest {
    private val rawRootFixture = "/home/skald-test-user/.local/share"
    private val rawRecordFixture = "safe_record_fixture"
    private val rawSecretFixture = "pass" + "phrase fixture"
    private val rawProviderFixture = "provider-key-material"
    private val rawLocationFixture = "vault_202122232425262728292a2b2c2d2e2f"
    private val rawLongHexFixture = "a".repeat(64)

    @Test
    fun redactionLeakagePolicyIdAndDefaultPolicyAreStableAndDisabled() {
        val unknown = classified(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(
                    SkaldVaultV1VaultRedactionValueKind.Unknown,
                ),
            ),
        )
        val passphrase = classified(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(
                    SkaldVaultV1VaultRedactionValueKind.Passphrase,
                ),
            ),
        )

        assertEquals(
            "skald-vault-v1-redaction-leakage-boundary-v1",
            SkaldVaultV1RedactionLeakagePolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1RedactionLeakagePolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultRedactionStatus.UnsupportedFailClosed,
            unknown.status,
        )
        assertEquals(SkaldVaultV1VaultRedactionDecision.UnsupportedFailClosed, unknown.decision)
        assertContains(unknown.blockers, SkaldVaultV1VaultRedactionBlocker.UnknownValueKindRejected)
        assertEquals(SkaldVaultV1VaultRedactionDecision.Forbidden, passphrase.decision)
        assertContains(passphrase.blockers, SkaldVaultV1VaultRedactionBlocker.SensitiveValueForbidden)
        assertFalse(passphrase.capability.runtimeLoggingEnabled)
        assertFalse(passphrase.capability.crashReportingEnabled)
        assertFalse(passphrase.capability.analyticsEnabled)
        assertFalse(passphrase.capability.supportExportEnabled)
        assertFalse(passphrase.capability.secretFingerprintingEnabled)
        assertFalse(passphrase.capability.secretHashingEnabled)
        assertFalse(passphrase.capability.rawSecretDisplayEnabled)
        assertFalse(passphrase.capability.providerSelectable)
        assertFalse(passphrase.capability.vaultUnlockAvailable)
        assertFalse(passphrase.capability.vaultPersistenceAvailable)
        assertTrue(passphrase.capability.defaultToStringSafeForSensitiveValues)
    }

    @Test
    fun valueClassVocabularyIsModeled() {
        val kinds = SkaldVaultV1VaultRedactionValueKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultRedactionValueKind.Passphrase,
            SkaldVaultV1VaultRedactionValueKind.Pin,
            SkaldVaultV1VaultRedactionValueKind.MnemonicPhrase,
            SkaldVaultV1VaultRedactionValueKind.Seed,
            SkaldVaultV1VaultRedactionValueKind.PrivateKey,
            SkaldVaultV1VaultRedactionValueKind.XprvTprv,
            SkaldVaultV1VaultRedactionValueKind.Wif,
            SkaldVaultV1VaultRedactionValueKind.NostrNsec,
            SkaldVaultV1VaultRedactionValueKind.NostrPrivateKeyDerivedWalletMaterial,
            SkaldVaultV1VaultRedactionValueKind.DescriptorPrivateMaterial,
            SkaldVaultV1VaultRedactionValueKind.ProviderRootKey,
            SkaldVaultV1VaultRedactionValueKind.VaultRootKey,
            SkaldVaultV1VaultRedactionValueKind.MetadataEncryptionKey,
            SkaldVaultV1VaultRedactionValueKind.RecordEncryptionKey,
            SkaldVaultV1VaultRedactionValueKind.BackupExportKey,
            SkaldVaultV1VaultRedactionValueKind.KeyWrappingKey,
            SkaldVaultV1VaultRedactionValueKind.RawKdfOutput,
            SkaldVaultV1VaultRedactionValueKind.RawAeadKey,
            SkaldVaultV1VaultRedactionValueKind.RandomEntropySample,
            SkaldVaultV1VaultRedactionValueKind.DecryptedVaultRecord,
            SkaldVaultV1VaultRedactionValueKind.EncryptedVaultRecordBytes,
            SkaldVaultV1VaultRedactionValueKind.RecordIdentifier,
            SkaldVaultV1VaultRedactionValueKind.ManifestIdentifier,
            SkaldVaultV1VaultRedactionValueKind.StorageIndexIdentifier,
            SkaldVaultV1VaultRedactionValueKind.PlannedArtifactLocationToken,
            SkaldVaultV1VaultRedactionValueKind.RootToken,
            SkaldVaultV1VaultRedactionValueKind.RawPlatformRootPath,
            SkaldVaultV1VaultRedactionValueKind.BackendCredential,
            SkaldVaultV1VaultRedactionValueKind.RpcCookie,
            SkaldVaultV1VaultRedactionValueKind.LightningMacaroonRuneNwcSecret,
            SkaldVaultV1VaultRedactionValueKind.PhoenixdToken,
            SkaldVaultV1VaultRedactionValueKind.CashuProofMaterial,
            SkaldVaultV1VaultRedactionValueKind.WalletDatabaseBytes,
            SkaldVaultV1VaultRedactionValueKind.BdkPersistenceHandle,
            SkaldVaultV1VaultRedactionValueKind.TransactionHex,
            SkaldVaultV1VaultRedactionValueKind.Psbt,
            SkaldVaultV1VaultRedactionValueKind.TxidOutpoint,
            SkaldVaultV1VaultRedactionValueKind.BitcoinAddress,
            SkaldVaultV1VaultRedactionValueKind.NostrEventSignature,
            SkaldVaultV1VaultRedactionValueKind.PaymentTransactionNote,
            SkaldVaultV1VaultRedactionValueKind.WalletLabel,
            SkaldVaultV1VaultRedactionValueKind.UtxoLabel,
            SkaldVaultV1VaultRedactionValueKind.BackendEndpoint,
            SkaldVaultV1VaultRedactionValueKind.OnionEndpoint,
            SkaldVaultV1VaultRedactionValueKind.TorRoutingMetadata,
            SkaldVaultV1VaultRedactionValueKind.AndroidDeviceIdentifier,
            SkaldVaultV1VaultRedactionValueKind.FilesystemErrorText,
            SkaldVaultV1VaultRedactionValueKind.ExceptionStackTrace,
            SkaldVaultV1VaultRedactionValueKind.PublicNonWalletCryptographicKatVector,
            SkaldVaultV1VaultRedactionValueKind.PublicPolicyIdentifier,
            SkaldVaultV1VaultRedactionValueKind.SentinelPlaceholder,
            SkaldVaultV1VaultRedactionValueKind.EnumStatusValue,
            SkaldVaultV1VaultRedactionValueKind.BooleanCapabilityFlag,
            SkaldVaultV1VaultRedactionValueKind.AggregateCountStatistic,
        ).forEach { kind -> assertContains(kinds, kind) }
    }

    @Test
    fun outputTargetVocabularyIsModeled() {
        val targets = SkaldVaultV1VaultRedactionOutputTarget.entries.toSet()

        listOf(
            SkaldVaultV1VaultRedactionOutputTarget.DefaultToString,
            SkaldVaultV1VaultRedactionOutputTarget.UiStatusText,
            SkaldVaultV1VaultRedactionOutputTarget.OperationResultSummary,
            SkaldVaultV1VaultRedactionOutputTarget.ErrorFailureSummary,
            SkaldVaultV1VaultRedactionOutputTarget.BuildHistory,
            SkaldVaultV1VaultRedactionOutputTarget.SourceGuardDiagnostics,
            SkaldVaultV1VaultRedactionOutputTarget.TestAssertionMessage,
            SkaldVaultV1VaultRedactionOutputTarget.FutureStructuredAppLog,
            SkaldVaultV1VaultRedactionOutputTarget.FutureCrashReport,
            SkaldVaultV1VaultRedactionOutputTarget.FutureSupportExport,
            SkaldVaultV1VaultRedactionOutputTarget.FutureBackupExportManifest,
            SkaldVaultV1VaultRedactionOutputTarget.FutureDebugOnlyDiagnosticPanel,
        ).forEach { target -> assertContains(targets, target) }
    }

    @Test
    fun sensitiveValuesAreForbiddenForEveryOutputTarget() {
        val sensitiveKinds = listOf(
            SkaldVaultV1VaultRedactionValueKind.Passphrase,
            SkaldVaultV1VaultRedactionValueKind.MnemonicPhrase,
            SkaldVaultV1VaultRedactionValueKind.Seed,
            SkaldVaultV1VaultRedactionValueKind.PrivateKey,
            SkaldVaultV1VaultRedactionValueKind.XprvTprv,
            SkaldVaultV1VaultRedactionValueKind.Wif,
            SkaldVaultV1VaultRedactionValueKind.NostrNsec,
            SkaldVaultV1VaultRedactionValueKind.RawKdfOutput,
            SkaldVaultV1VaultRedactionValueKind.RawAeadKey,
            SkaldVaultV1VaultRedactionValueKind.DecryptedVaultRecord,
            SkaldVaultV1VaultRedactionValueKind.BackendCredential,
            SkaldVaultV1VaultRedactionValueKind.LightningMacaroonRuneNwcSecret,
            SkaldVaultV1VaultRedactionValueKind.CashuProofMaterial,
            SkaldVaultV1VaultRedactionValueKind.WalletDatabaseBytes,
        )

        sensitiveKinds.forEach { kind ->
            SkaldVaultV1VaultRedactionOutputTarget.entries.forEach { target ->
                val evidence = classified(
                    SkaldVaultV1RedactionLeakagePolicy.evaluate(
                        SkaldVaultV1VaultRedactionRequest.classify(
                            valueKind = kind,
                            outputTarget = target,
                        ),
                    ),
                )
                assertEquals(SkaldVaultV1VaultRedactionDecision.Forbidden, evidence.decision)
                assertFalse(evidence.decision.rawValueAllowed)
                assertFalse(evidence.decision.secretAllowed)
                assertContains(evidence.blockers, SkaldVaultV1VaultRedactionBlocker.SensitiveValueForbidden)
            }
        }
    }

    @Test
    fun metadataOperationalAndPublicEvidenceDecisionsStayConservative() {
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.ReplaceWithStableRedactedToken,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.RecordIdentifier),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.ReplaceWithStableRedactedToken,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.PlannedArtifactLocationToken),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.RedactCompletely,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.RawPlatformRootPath),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.SummarizeClassOnly,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.WalletLabel),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.SummarizeClassOnly,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.PaymentTransactionNote),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.SummarizeClassOnly,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.BackendEndpoint),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.AllowPublicPolicyIdentifier,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.PublicPolicyIdentifier),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.AllowEnumOrCapability,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.EnumStatusValue),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.AllowEnumOrCapability,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.BooleanCapabilityFlag),
        )
        assertEquals(
            SkaldVaultV1VaultRedactionDecision.SummarizeCountOnly,
            decisionFor(SkaldVaultV1VaultRedactionValueKind.AggregateCountStatistic),
        )
    }

    @Test
    fun publicNonWalletVectorIsScopedAndRejectedForWalletUtxoSync() {
        val allowedScopes = listOf(
            SkaldVaultV1VaultRedactionScope.Docs,
            SkaldVaultV1VaultRedactionScope.Test,
            SkaldVaultV1VaultRedactionScope.KatVector,
            SkaldVaultV1VaultRedactionScope.SourceGuard,
        )

        allowedScopes.forEach { scope ->
            val evidence = classified(
                SkaldVaultV1RedactionLeakagePolicy.evaluate(
                    SkaldVaultV1VaultRedactionRequest.publicNonWalletVector(
                        vectorId = "public-non-wallet-vector-id",
                        scope = scope,
                    ),
                ),
            )
            assertEquals(
                SkaldVaultV1VaultRedactionDecision.AllowPublicNonWalletVectorInTestScopeOnly,
                evidence.decision,
            )
        }

        val rejected = classified(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.publicNonWalletVector(
                    vectorId = "public-non-wallet-vector-id",
                    scope = SkaldVaultV1VaultRedactionScope.WalletUtxoSync,
                ),
            ),
        )
        assertEquals(SkaldVaultV1VaultRedactionDecision.RejectDiagnostic, rejected.decision)
        assertContains(rejected.blockers, SkaldVaultV1VaultRedactionBlocker.PublicVectorScopeRejected)
        assertContains(
            rejected.blockers,
            SkaldVaultV1VaultRedactionBlocker.PublicVectorRejectedInWalletUtxoSyncScope,
        )
    }

    @Test
    fun tokensAndResultsDoNotExposeRawValuesOrSecretFingerprints() {
        val request = SkaldVaultV1VaultRedactionRequest.classify(
            SkaldVaultV1VaultRedactionValueKind.RecordIdentifier,
        )
        val evidence = classified(SkaldVaultV1RedactionLeakagePolicy.evaluate(request))
        val rejected = SkaldVaultV1RedactionLeakagePolicy.evaluate(
            SkaldVaultV1VaultRedactionRequest.rawRedactionCandidate(rawSecretFixture),
        )
        val forbiddenValues = listOf(
            rawRootFixture,
            rawRecordFixture,
            rawSecretFixture,
            rawProviderFixture,
            rawLocationFixture,
            rawLongHexFixture,
        )

        assertFalse(evidence.redactionMarker.rawValueExposed)
        assertFalse(evidence.redactionMarker.containsSecretMaterial)
        assertFalse(evidence.redactionMarker.containsPassphraseMaterial)
        assertFalse(evidence.redactionMarker.containsKeyMaterial)
        assertFalse(evidence.redactionMarker.containsProviderKeyMaterial)
        assertFalse(evidence.redactionMarker.containsRootText)
        assertFalse(evidence.redactionMarker.containsPlannedLocationText)
        assertFalse(evidence.redactionMarker.containsRecordIdentifier)
        assertFalse(evidence.redactionMarker.containsPayload)
        assertFalse(evidence.redactionMarker.containsRawBytes)
        assertFalse(evidence.redactionMarker.stableFingerprintOfSecret)
        assertFalse(evidence.redactionMarker.hashOfSecret)
        assertFalse(evidence.redactionMarker.partialSecretDisplay)
        assertFalse(evidence.rawSensitiveValueExposed)
        assertFalse(evidence.secretHashingImplemented)
        assertFalse(evidence.secretFingerprintingImplemented)
        forbiddenValues.forEach { raw ->
            assertRedacted(request.toString(), raw)
            assertRedacted(evidence.toString(), raw)
            assertRedacted(evidence.redactionMarker.toString(), raw)
            assertRedacted(rejected.toString(), raw)
            evidence.blockers.forEach { blocker -> assertRedacted(blocker.label, raw) }
            evidence.warnings.forEach { warning -> assertRedacted(warning.label, raw) }
        }
    }

    @Test
    fun existingBoundaryEvidenceSourcesRemainRedactionSafe() {
        SkaldVaultV1VaultRedactionEvidenceSource.entries.forEach { source ->
            val evidence = classified(
                SkaldVaultV1RedactionLeakagePolicy.evaluate(
                    SkaldVaultV1VaultRedactionRequest.fromEvidenceSource(source),
                ),
            )

            assertEquals(source, evidence.evidenceSource)
            assertEquals(SkaldVaultV1VaultRedactionDecision.AllowEnumOrCapability, evidence.decision)
            assertFalse(evidence.rawSensitiveValueExposed)
            assertFalse(evidence.rawByteArrayExposed)
            assertFalse(evidence.runtimeLoggingImplemented)
            assertFalse(evidence.persistenceReady)
        }
    }

    @Test
    fun allRedactionCapabilitiesRemainConservative() {
        val capability = classified(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(
                    SkaldVaultV1VaultRedactionValueKind.Passphrase,
                ),
            ),
        ).capability

        assertDisabledCapabilities(capability)
    }

    @Test
    fun rawRedactionInputsAreRejectedWithTypedReasons() {
        val bitcoinAddressLike = "bc" + "1" + "q".repeat(24)
        val nostrSecretLike = "ns" + "ec" + "1" + "q".repeat(24)
        val extendedPrivatePrefixFixture = "xp" + "rv" + "9".repeat(32)
        val testPrivatePrefixFixture = "tp" + "rv" + "9".repeat(32)
        val wifLike = "K" + "1".repeat(50)
        val cases = listOf(
            null to SkaldVaultV1VaultRedactionFailureReason.EmptyEvidenceRejected,
            "" to SkaldVaultV1VaultRedactionFailureReason.EmptyEvidenceRejected,
            "   " to SkaldVaultV1VaultRedactionFailureReason.EmptyEvidenceRejected,
            "pass" + "phrase-fixture" to SkaldVaultV1VaultRedactionFailureReason.PassphraseInputRejected,
            "pin-fixture" to SkaldVaultV1VaultRedactionFailureReason.PinInputRejected,
            "raw-key-fixture" to SkaldVaultV1VaultRedactionFailureReason.KeyMaterialInputRejected,
            "key-material-fixture" to SkaldVaultV1VaultRedactionFailureReason.KeyMaterialInputRejected,
            "provider-key-material" to SkaldVaultV1VaultRedactionFailureReason.KeyMaterialInputRejected,
            "credential-fixture" to SkaldVaultV1VaultRedactionFailureReason.CredentialInputRejected,
            "token-fixture" to SkaldVaultV1VaultRedactionFailureReason.CredentialInputRejected,
            "byte-array-fixture" to SkaldVaultV1VaultRedactionFailureReason.RawByteArrayInputRejected,
            rawRootFixture to SkaldVaultV1VaultRedactionFailureReason.RawAbsoluteLocationInputRejected,
            "relative/path" to SkaldVaultV1VaultRedactionFailureReason.RawRelativeLocationInputRejected,
            "file://skald-vault" to SkaldVaultV1VaultRedactionFailureReason.LinkLikeInputRejected,
            "https://example.invalid/skald" to SkaldVaultV1VaultRedactionFailureReason.LinkLikeInputRejected,
            "content://example/skald" to SkaldVaultV1VaultRedactionFailureReason.LinkLikeInputRejected,
            "C:\\skald\\vault" to SkaldVaultV1VaultRedactionFailureReason.RawAbsoluteLocationInputRejected,
            "\\\\server\\share" to SkaldVaultV1VaultRedactionFailureReason.RawAbsoluteLocationInputRejected,
            "file-object-fixture" to SkaldVaultV1VaultRedactionFailureReason.PlatformObjectLikeInputRejected,
            "path-object-fixture" to SkaldVaultV1VaultRedactionFailureReason.PlatformObjectLikeInputRejected,
            "stack-trace-fixture" to SkaldVaultV1VaultRedactionFailureReason.StackTraceInputRejected,
            "segment..traversal" to SkaldVaultV1VaultRedactionFailureReason.TraversalRejected,
            "unsupported:colon" to SkaldVaultV1VaultRedactionFailureReason.UnsupportedEvidenceRejected,
            "secret-fixture" to SkaldVaultV1VaultRedactionFailureReason.SecretMaterialRejected,
            "mnemonic-fixture" to SkaldVaultV1VaultRedactionFailureReason.SecretMaterialRejected,
            rawLongHexFixture to SkaldVaultV1VaultRedactionFailureReason.TransactionLikeEvidenceRejected,
            bitcoinAddressLike to SkaldVaultV1VaultRedactionFailureReason.BitcoinAddressLikeEvidenceRejected,
            nostrSecretLike to SkaldVaultV1VaultRedactionFailureReason.WalletMaterialRejected,
            extendedPrivatePrefixFixture to SkaldVaultV1VaultRedactionFailureReason.WalletMaterialRejected,
            testPrivatePrefixFixture to SkaldVaultV1VaultRedactionFailureReason.WalletMaterialRejected,
            wifLike to SkaldVaultV1VaultRedactionFailureReason.WalletMaterialRejected,
        )

        cases.forEach { (raw, expectedReason) ->
            val rejected = rejected(
                SkaldVaultV1RedactionLeakagePolicy.evaluate(
                    SkaldVaultV1VaultRedactionRequest.rawRedactionCandidate(raw),
                ),
            )
            assertEquals(expectedReason, rejected.reason)
            assertEquals(SkaldVaultV1VaultRedactionStatus.RawCandidateRejected, rejected.status)
            if (!raw.isNullOrBlank()) {
                assertFalse(rejected.toString().contains(raw))
            }
        }
    }

    @Test
    fun readinessProviderAcceptanceDependencyAndSelectionRecordBoundaryButRemainDisabled() {
        val contract = commonProductionProviderAcceptanceContract()
        val storage = contract.containerManifestStorageContract
        val acceptanceEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess()
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1RedactionLeakagePolicy.POLICY_ID, storage.redactionLeakageBoundaryPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.redactionLeakageBoundaryStatus,
        )
        assertEquals(ProductionProviderRedactionLeakageRule.entries.toSet(), storage.redactionLeakageRules)
        assertTrue(storage.redactionLeakageBoundaryModeled)
        assertTrue(storage.redactionLeakageBoundaryStillDisabled)
        assertTrue(storage.redactionLeakageClassifiesSensitiveValueKinds)
        assertTrue(storage.redactionLeakageDoesNotAcceptRawSecrets)
        assertTrue(storage.redactionLeakageDoesNotHashSecrets)
        assertTrue(storage.redactionLeakageDoesNotLog)
        assertTrue(storage.redactionLeakageDoesNotEnablePersistence)
        assertTrue(storage.redactionLeakageDoesNotEnableProviderSelection)
        assertTrue(storage.redactionLeakageFailureVocabularyModeled)
        assertFalse(storage.vaultPersistenceImplemented)
        assertFalse(storage.secureSecretStorageSuccessPathImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            acceptanceEvidence.stateFor(ProductionProviderAcceptanceGate.RedactionLeakageBoundaryImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.RedactionLeakageBoundaryImplementedAndTested],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.Absent,
            readiness.requirementStatuses[EncryptedVaultRequirement.RedactionTestsPassed],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.RedactionLeakageBoundaryBuildingBlock)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.RedactionLeakageBoundaryStillDisabled)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.RedactionTestsMissing)
        assertFalse(readiness.productionPersistenceEnabled)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.RedactionLeakageBoundaryImplementedTested)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.RedactionLeakageFailureVocabularyModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.RedactionLeakageBoundaryStillDisabled)
        assertFalse(dependency.productionPersistenceEnabled)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
    }

    @Test
    fun sourceGuardMaterialVocabularyIsModeled() {
        val material = SkaldVaultV1VaultSourceGuardMaterialClass.entries.toSet()

        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoLoggingImplementation)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoCrashReportingImplementation)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoAnalyticsImplementation)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoSecretHashingOrFingerprinting)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoFilesystemApis)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoSettingsPersistence)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoProviderCryptoExecution)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.NoStorageSuccessPath)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.PublicNonWalletVectorScoped)
        assertContains(material, SkaldVaultV1VaultSourceGuardMaterialClass.WalletMaterialFixtureRejected)
    }

    private fun decisionFor(
        valueKind: SkaldVaultV1VaultRedactionValueKind,
    ): SkaldVaultV1VaultRedactionDecision =
        classified(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(valueKind),
            ),
        ).decision

    private fun classified(
        result: SkaldVaultV1VaultRedactionResult<SkaldVaultV1VaultRedactionEvidence>,
    ): SkaldVaultV1VaultRedactionEvidence =
        when (result) {
            is SkaldVaultV1VaultRedactionResult.Classified -> result.value
            is SkaldVaultV1VaultRedactionResult.Rejected ->
                error("expected classified redaction evidence but got ${result.reason}")
        }

    private fun rejected(
        result: SkaldVaultV1VaultRedactionResult<*>,
    ): SkaldVaultV1VaultRedactionResult.Rejected =
        when (result) {
            is SkaldVaultV1VaultRedactionResult.Classified ->
                error("expected rejected redaction evidence but got classified")
            is SkaldVaultV1VaultRedactionResult.Rejected -> result
        }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1VaultRedactionCapability) {
        assertFalse(capability.runtimeLoggingEnabled)
        assertFalse(capability.crashReportingEnabled)
        assertFalse(capability.analyticsEnabled)
        assertFalse(capability.supportExportEnabled)
        assertFalse(capability.secretFingerprintingEnabled)
        assertFalse(capability.secretHashingEnabled)
        assertFalse(capability.rawSecretDisplayEnabled)
        assertTrue(capability.defaultToStringSafeForSensitiveValues)
        assertFalse(capability.buildHistorySecretAllowed)
        assertFalse(capability.operationResultSecretAllowed)
        assertFalse(capability.uiSecretAllowed)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertRedacted(rendered: String, raw: String) {
        assertFalse(rendered.contains(raw), "redaction rendering leaked raw fixture: $rendered")
    }
}
