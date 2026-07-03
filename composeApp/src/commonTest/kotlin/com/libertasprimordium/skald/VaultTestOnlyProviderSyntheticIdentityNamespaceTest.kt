package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAlias
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkage
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRule
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderSyntheticIdentityNamespaceTest {
    @Test
    fun currentNamespaceEvidenceIsModeledAndStillDisabled() {
        val evidence = currentNamespace()

        assertEquals(
            "skald-vault-v1-test-only-provider-synthetic-identity-namespace-v1",
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.POLICY_VERSION)
        assertEquals(
            "skald-test-only-provider-identity-v1-",
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.ALLOWED_NAMESPACE_PREFIX,
        )
        assertTrue(evidence.modeledButStillDisabled)
        assertTrue(evidence.namespaceContractModeled)
        assertTrue(evidence.stillDisabled)
        assertFalse(evidence.labelSyntaxEvaluated)
        assertFalse(evidence.candidateLabelAcceptedForFutureReview)
        assertFalse(evidence.candidateLabelRejected)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NamespaceContractModeled,
        )
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.StillDisabled)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
                .NoTestOnlyProviderIdentityImplemented,
        )
        assertContains(evidence.statuses, SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.MainnetBlocked)
    }

    @Test
    fun noCurrentTestOnlyProviderIdentityIsImplemented() {
        val evidence = currentNamespace()

        assertFalse(evidence.testOnlyProviderIdentityImplemented)
        assertFalse(evidence.productionProviderIdentityImplemented)
        assertFalse(evidence.disabledCapabilities.canImplementProviderNow)
        assertFalse(evidence.disabledCapabilities.canInstantiateProviderNow)
        assertFalse(evidence.disabledCapabilities.canRegisterProviderNow)
        assertFalse(evidence.disabledCapabilities.canUseAsExecutorTarget)
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker
                .NoTestOnlyProviderIdentityImplemented,
        )
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenComposedWithPriorEvidence() {
        val namespace = currentNamespace()
        val decision = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val isolation = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(namespace.providerSelectionDisabledProviderOnly)
        assertTrue(decision.currentSelectableIdentityDisabledProviderOnly)
        assertTrue(isolation.providerSelectionDisabledProviderOnly)
        assertFalse(namespace.disabledCapabilities.canUseAsProviderSelectionId)
        assertFalse(decision.disabledCapabilities.canAuthorizeProviderSelection)
        assertFalse(isolation.disabledCapabilities.canAuthorizeProviderSelection)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence = currentNamespace()

        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.disabledCapabilities.productionProviderSelectable)
        assertContains(
            evidence.blockers,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ProductionProviderSelectableFalse,
        )
    }

    @Test
    fun allCapabilityBooleansRemainFalse() {
        assertAllCapabilityBooleansFalse(currentNamespace().disabledCapabilities)
    }

    @Test
    fun allNamespaceRulesAreRepresentedExactlyOnce() {
        val rules = currentNamespace().ruleRows.map { it.rule }

        assertEquals(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRule.entries.size, rules.size)
        assertEquals(rules.size, rules.toSet().size)
        SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRule.entries.forEach { rule ->
            val row = currentNamespace().ruleRows.single { it.rule == rule }
            assertTrue(row.represented)
            assertTrue(row.satisfiedForModelOnlyBoundary)
            assertFalse(row.authorizesCapability)
        }
    }

    @Test
    fun allForbiddenMaterialClassesAreRepresentedExactlyOnce() {
        val materials = currentNamespace().forbiddenMaterialRows.map { it.materialClass }

        assertEquals(SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialClass.entries.size, materials.size)
        assertEquals(materials.size, materials.toSet().size)
        SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialClass.entries.forEach { materialClass ->
            val row = currentNamespace().forbiddenMaterialRows.single { it.materialClass == materialClass }
            assertTrue(row.forbidden)
            assertFalse(row.acceptedNow)
            assertFalse(row.canAuthorize)
        }
    }

    @Test
    fun allForbiddenAliasesAreRepresentedExactlyOnce() {
        val aliases = currentNamespace().forbiddenAliasRows.map { it.alias }

        assertEquals(SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAlias.entries.size, aliases.size)
        assertEquals(aliases.size, aliases.toSet().size)
        SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAlias.entries.forEach { alias ->
            val row = currentNamespace().forbiddenAliasRows.single { it.alias == alias }
            assertTrue(row.forbidden)
            assertFalse(row.currentAliasPresent)
            assertFalse(row.canAuthorize)
        }
    }

    @Test
    fun allForbiddenLinkagesAreRepresentedExactlyOnce() {
        val linkages = currentNamespace().forbiddenLinkageRows.map { it.linkage }

        assertEquals(SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkage.entries.size, linkages.size)
        assertEquals(linkages.size, linkages.toSet().size)
        SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkage.entries.forEach { linkage ->
            val row = currentNamespace().forbiddenLinkageRows.single { it.linkage == linkage }
            assertTrue(row.forbidden)
            assertFalse(row.currentLinkPresent)
            assertFalse(row.canAuthorize)
        }
    }

    @Test
    fun validModelOnlySafeIdsAreAcceptedOnlyForFutureReviewAndAuthorizeNothing() {
        val examples = listOf(
            validId("deterministic-kat", "aad-round-trip"),
            validId("randomized-behavior-kat", "redaction-probe"),
            validId("platform-runtime-kat", "linux-jvm-check"),
        )

        examples.forEach { candidate ->
            val evidence = evaluate(candidate)

            assertTrue(evidence.labelSyntaxEvaluated)
            assertTrue(evidence.candidateLabelAcceptedForFutureReview)
            assertFalse(evidence.candidateLabelRejected)
            assertTrue(evidence.rejectionReasons.isEmpty())
            assertContains(
                evidence.statuses,
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
                    .CandidateLabelAcceptedForFutureReview,
            )
            assertContains(
                evidence.blockers,
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker
                    .LabelAcceptedForFutureReviewOnly,
            )
            assertAllCapabilityBooleansFalse(evidence.disabledCapabilities)
            assertTrue(evidence.allEvidenceNonAuthorizing)
        }
    }

    @Test
    fun acceptedSafeIdsExposeOnlyAllowedFamilyTokens() {
        assertEquals(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.DeterministicKat,
            evaluate(validId("deterministic-kat", "positive-case")).candidateFamily,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.RandomizedBehaviorKat,
            evaluate(validId("randomized-behavior-kat", "bounds-case")).candidateFamily,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.PlatformRuntimeKat,
            evaluate(validId("platform-runtime-kat", "jvm-case")).candidateFamily,
        )
        assertEquals(
            setOf("deterministic-kat", "randomized-behavior-kat", "platform-runtime-kat"),
            currentNamespace().allowedFamilyTokens,
        )
    }

    @Test
    fun invalidIdsAreRejectedForSyntaxSeparatorsAliasesAndMaterialTokens() {
        assertRejected("", SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Blank)
        assertRejected(
            validId("deterministic-kat", "Upper"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.UppercaseOrNonLowercaseAscii,
        )
        assertRejected(
            validId("deterministic-kat", "white space"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.IllegalCharacter,
        )
        assertRejected(
            validId("deterministic-kat", "slash/value"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.PathSeparator,
        )
        assertRejected(
            validId("deterministic-kat", "slash\\value"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.PathSeparator,
        )
        assertRejected(
            validId("deterministic-kat", "dot.value"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Dot,
        )
        assertRejected(
            validId("deterministic-kat", "dot..value"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Dot,
        )
        assertRejected(
            validId("deterministic-kat", "colon:value"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Colon,
        )
        assertRejected(
            validId("deterministic-kat", "at@value"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.AtSign,
        )
        assertRejected("https://namespace.invalid", SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.UrlLikeStructure)
        assertRejected(
            validId("deterministic-kat", "localhost-port"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.EndpointLikeStructure,
        )
        assertRejected(
            validId("deterministic-kat", "bc1sample"),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.WalletAddressLikeStructure,
        )
        assertRejected(
            validId("deterministic-kat", "a".repeat(64)),
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Hex64Token,
        )
    }

    @Test
    fun dependencyProviderProductionAndMaterialTokensAreRejected() {
        listOf("tink", "bouncy", "lazysodium", "ionspin", "bdk", "electrum", "esplora").forEach { token ->
            assertRejected(
                validId("deterministic-kat", token),
                SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.DependencyCandidateAlias,
            )
        }

        listOf("production", "release", "mainnet", "signing", "broadcasting").forEach { token ->
            assertRejected(
                validId("deterministic-kat", token),
                SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.ProductionPromotionAlias,
            )
        }

        listOf(
            "secret",
            "seed",
            "mnemonic",
            "xprv",
            "xpub",
            "nsec",
            "key",
            "credential",
            "descriptor",
            "path",
            "file",
            "backend",
            "endpoint",
        ).forEach { token ->
            assertTrue(evaluate(validId("deterministic-kat", token)).candidateLabelRejected)
        }
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilityBooleans() {
        val evidence = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                candidateSafeId = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(
                    validId("deterministic-kat", "future-review"),
                ),
                userConsentOverrideRequested = true,
                warningOnlyEvidenceClaimed = true,
                testOnlyEvidenceClaimedAsProductionPromotion = true,
                registryAliasClaimed = true,
                factoryAliasClaimed = true,
                dispatcherAliasClaimed = true,
                executorTargetAliasClaimed = true,
                dependencyCandidateAliasClaimed = true,
                endpointAliasClaimed = true,
                storagePathAliasClaimed = true,
                mainnetAliasClaimed = true,
            ),
        )

        listOf(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.RegistryAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.FactoryAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.DispatcherAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ExecutorTargetAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.DependencyCandidateAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.EndpointAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.StoragePathAliasRejected,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.MainnetAliasRejected,
        ).forEach { blocker ->
            assertContains(evidence.blockers, blocker)
        }
        assertAllCapabilityBooleansFalse(evidence.disabledCapabilities)
    }

    @Test
    fun priorIdentityDecisionEvidenceRemainsNonAuthorizingWhenComposed() {
        val evidence = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                includePriorIdentityDecisionEvidence = true,
                includePriorIsolationEvidence = false,
            ),
        )

        assertContains(
            evidence.evidenceSources,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.TestOnlyProviderIdentityDecision,
        )
        assertFalse(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard in evidence.evidenceSources,
        )
        assertTrue(evidence.priorIdentityDecisionEvidenceIncluded)
        assertTrue(evidence.priorIdentityDecisionEvidenceNonAuthorizing)
        assertFalse(evidence.disabledCapabilities.canImplementProviderNow)
        assertFalse(evidence.disabledCapabilities.canUseAsProviderSelectionId)
    }

    @Test
    fun priorIdentityIsolationEvidenceRemainsNonAuthorizingWhenComposed() {
        val evidence = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                includePriorIdentityDecisionEvidence = false,
                includePriorIsolationEvidence = true,
            ),
        )

        assertContains(
            evidence.evidenceSources,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )
        assertFalse(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource
                .TestOnlyProviderIdentityDecision in evidence.evidenceSources,
        )
        assertTrue(evidence.priorIsolationEvidenceIncluded)
        assertTrue(evidence.priorIsolationEvidenceNonAuthorizing)
        assertFalse(evidence.disabledCapabilities.canUseAsRegistryKey)
        assertFalse(evidence.disabledCapabilities.canUseAsExecutorTarget)
    }

    @Test
    fun redactedOutputDoesNotExposeCandidateIdsOrUnsafeReferenceTerms() {
        val candidate = validId("deterministic-kat", "safe-review")
        val request = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
            candidateSafeId = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(candidate),
            endpointAliasClaimed = true,
            storagePathAliasClaimed = true,
        )
        val requestText = request.toString()
        val safeIdText = request.candidateSafeId.toString()
        val evidence = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(request)
        val safeLabelText = evidence.candidateSafeLabel.toString()

        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass.SafeIdSyntaxOnly,
        )
        assertContains(
            evidence.redactionClasses,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass.NoRawInput,
        )
        assertFalse(candidate in requestText)
        assertFalse(candidate in safeIdText)
        assertFalse(candidate in safeLabelText)
        listOf(
            "passphrase",
            "mnemonic",
            "xprv",
            "nsec",
            "provider handle",
            "storage path",
            "backend credential",
            "crypto object",
            "endpoint",
            "wallet descriptor",
        ).forEach { forbidden ->
            assertFalse(forbidden in requestText.lowercase())
            assertFalse(forbidden in safeIdText.lowercase())
            assertFalse(forbidden in safeLabelText.lowercase())
        }
    }

    private fun currentNamespace() =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()

    private fun evaluate(candidate: String) =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                candidateSafeId = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(candidate),
            ),
        )

    private fun validId(family: String, purpose: String): String =
        "skald-test-only-provider-identity-v1-$family-$purpose"

    private fun assertRejected(
        candidate: String,
        reason: SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason,
    ) {
        val evidence = evaluate(candidate)

        assertTrue(evidence.labelSyntaxEvaluated)
        assertTrue(evidence.candidateLabelRejected)
        assertFalse(evidence.candidateLabelAcceptedForFutureReview)
        assertContains(evidence.rejectionReasons, reason)
        assertContains(
            evidence.statuses,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.CandidateLabelRejected,
        )
        assertAllCapabilityBooleansFalse(evidence.disabledCapabilities)
    }

    private fun assertAllCapabilityBooleansFalse(
        capability: SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceCapability,
    ) {
        assertFalse(capability.canImplementProviderNow)
        assertFalse(capability.canInstantiateProviderNow)
        assertFalse(capability.canRegisterProviderNow)
        assertFalse(capability.canUseAsProviderSelectionId)
        assertFalse(capability.canUseAsRegistryKey)
        assertFalse(capability.canUseAsFactoryInput)
        assertFalse(capability.canUseAsDispatcherInput)
        assertFalse(capability.canUseAsExecutorTarget)
        assertFalse(capability.canUseForProviderKatExecutor)
        assertFalse(capability.canExecuteProviderOperations)
        assertFalse(capability.canExecuteRandomness)
        assertFalse(capability.canExecuteKdf)
        assertFalse(capability.canExecuteHkdf)
        assertFalse(capability.canExecuteHmac)
        assertFalse(capability.canExecuteAead)
        assertFalse(capability.canGenerateKeys)
        assertFalse(capability.canStoreKeysets)
        assertFalse(capability.canUseForVaultCreation)
        assertFalse(capability.canUseForVaultUnlock)
        assertFalse(capability.canUseForVaultSession)
        assertFalse(capability.canUseForVaultPersistence)
        assertFalse(capability.canUseForSecureStorage)
        assertFalse(capability.canUseForSecureMetadataStorage)
        assertFalse(capability.canUseForStorageNamespace)
        assertFalse(capability.canUseForStoragePath)
        assertFalse(capability.canUseForProductionSync)
        assertFalse(capability.canUseForBackendClient)
        assertFalse(capability.canUseForBdkWalletState)
        assertFalse(capability.canUseForSettingsCodec)
        assertFalse(capability.canUseForUiSurface)
        assertFalse(capability.canUseForSigning)
        assertFalse(capability.canUseForBroadcasting)
        assertFalse(capability.canUseForTorTransport)
        assertFalse(capability.canUseForNostrParsing)
        assertFalse(capability.canUseForPublicEndpointDefault)
        assertFalse(capability.canUseForMainnet)
        assertFalse(capability.productionProviderSelectable)
    }
}
