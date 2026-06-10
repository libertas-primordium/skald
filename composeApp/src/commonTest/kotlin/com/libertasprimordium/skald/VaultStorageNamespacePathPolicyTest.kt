package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1StorageIdentifierSource
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathResult
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

class VaultStorageNamespacePathPolicyTest {
    @Test
    fun storageNamespacePolicyIdsAndConstantsAreStable() {
        assertEquals(
            "skald-vault-v1-storage-namespace-path-policy-v1",
            SkaldVaultV1StorageNamespacePathPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1StorageNamespacePathPolicy.POLICY_VERSION)
        assertEquals(
            "skald-vault-v1-local-manifest-storage-policy-v1",
            SkaldVaultV1StorageNamespacePathPolicy.STORAGE_POLICY_ID,
        )
        assertEquals(
            "skald-vault/v1/local-records",
            SkaldVaultV1StorageNamespacePathPolicy.STORAGE_NAMESPACE_ID,
        )
        assertEquals("skald-vault/v1/records", SkaldVaultV1StorageNamespacePathPolicy.RECORD_NAMESPACE_ID)
        assertEquals("skald-vault/v1/manifests", SkaldVaultV1StorageNamespacePathPolicy.MANIFEST_NAMESPACE_ID)
    }

    @Test
    fun acceptedNamespaceConstantsAndSegmentsValidate() {
        assertAcceptedNamespace(
            SkaldVaultV1StorageNamespacePathPolicy.validateStoragePolicyId(
                SkaldVaultV1StorageNamespacePathPolicy.STORAGE_POLICY_ID,
            ),
        )
        assertAcceptedNamespace(
            SkaldVaultV1StorageNamespacePathPolicy.validateStorageNamespaceId(
                SkaldVaultV1StorageNamespacePathPolicy.STORAGE_NAMESPACE_ID,
            ),
        )
        assertAcceptedNamespace(
            SkaldVaultV1StorageNamespacePathPolicy.validateRecordNamespaceId(
                SkaldVaultV1StorageNamespacePathPolicy.RECORD_NAMESPACE_ID,
            ),
        )
        assertAcceptedNamespace(
            SkaldVaultV1StorageNamespacePathPolicy.validateManifestNamespaceId(
                SkaldVaultV1StorageNamespacePathPolicy.MANIFEST_NAMESPACE_ID,
            ),
        )

        assertEquals(
            "segment_0001",
            acceptedSegment(
                SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("segment_0001"),
            ),
        )
        assertEquals(
            "manifest_v1",
            acceptedSegment(SkaldVaultV1StorageNamespacePathPolicy.manifestStorageSegment()),
        )
    }

    @Test
    fun fixedNonSecretVaultAndRecordIdsEncodeDeterministically() {
        assertEquals(
            "vault_202122232425262728292a2b2c2d2e2f",
            acceptedSegment(
                SkaldVaultV1StorageNamespacePathPolicy.encodeVaultStorageId(
                    (0x20..0x2f).map { it.toByte() }.toByteArray(),
                ),
            ),
        )
        assertEquals(
            "record_404142434445464748494a4b4c4d4e4f",
            acceptedSegment(
                SkaldVaultV1StorageNamespacePathPolicy.encodeRecordStorageId(
                    (0x40..0x4f).map { it.toByte() }.toByteArray(),
                ),
            ),
        )
    }

    @Test
    fun pathSegmentsRejectTraversalAndPathSyntax() {
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(""),
            SkaldVaultV1StorageNamespacePathRejectionReason.EmptyIdentifier,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("."),
            SkaldVaultV1StorageNamespacePathRejectionReason.DotSegment,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(".."),
            SkaldVaultV1StorageNamespacePathRejectionReason.ParentSegment,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("record/0001"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("record\\0001"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("record/../manifest"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsTraversal,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("record%2e%2e"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsTraversal,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("/absolute"),
            SkaldVaultV1StorageNamespacePathRejectionReason.AbsolutePathRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("C:\\vault"),
            SkaldVaultV1StorageNamespacePathRejectionReason.WindowsDrivePrefixRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("file:skald-vault"),
            SkaldVaultV1StorageNamespacePathRejectionReason.UriLikePrefixRejected,
        )
    }

    @Test
    fun pathSegmentsRejectUnsafeTextAndWrongLengthIds() {
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("wallet label"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsWhitespace,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("wallet\nlabel"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsControlCharacter,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("wallet\u200blabel"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsInvisibleFormat,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("wallet-label-\u00e9"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsNonAscii,
        )
        listOf("label.one", "label~one", "label:one", "label*one", "label?one", "\"label\"", "<label>", "label|one")
            .forEach { value ->
                assertRejected(
                    SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(value),
                    SkaldVaultV1StorageNamespacePathRejectionReason.ContainsUnsupportedCharacter,
                )
            }
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("-segment"),
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsUnsupportedCharacter,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(
                "a".repeat(SkaldVaultV1StorageNamespacePathPolicy.MAX_SEGMENT_BYTES + 1),
            ),
            SkaldVaultV1StorageNamespacePathRejectionReason.TooLong,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.encodeVaultStorageId(ByteArray(15) { it.toByte() }),
            SkaldVaultV1StorageNamespacePathRejectionReason.WrongVaultIdLength,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.encodeRecordStorageId(ByteArray(17) { it.toByte() }),
            SkaldVaultV1StorageNamespacePathRejectionReason.WrongRecordIdLength,
        )
    }

    @Test
    fun userControlledAndSecretLookingInputsAreRejected() {
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(
                value = "wallet_label",
                source = SkaldVaultV1StorageIdentifierSource.UserControlledText,
            ),
            SkaldVaultV1StorageNamespacePathRejectionReason.UserControlledInputRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validateStorageNamespaceId(
                value = SkaldVaultV1StorageNamespacePathPolicy.STORAGE_NAMESPACE_ID,
                source = SkaldVaultV1StorageIdentifierSource.UserControlledText,
            ),
            SkaldVaultV1StorageNamespacePathRejectionReason.UserControlledInputRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(
                value = "candidate_secret_material",
                source = SkaldVaultV1StorageIdentifierSource.SecretMaterialCandidate,
            ),
            SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("nsec1qqqqqqqqqqqqqqqq"),
            SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment("a".repeat(64)),
            SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected,
        )
        assertRejected(
            SkaldVaultV1StorageNamespacePathPolicy.validateStorageNamespaceId("skald-vault/v1/unknown"),
            SkaldVaultV1StorageNamespacePathRejectionReason.UnknownNamespacePolicy,
        )
    }

    @Test
    fun namespacePathEvidenceIsRepresentedButDoesNotEnablePersistenceOrSelection() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            SkaldVaultV1StorageNamespacePathPolicy.POLICY_ID,
            storageContract.storageNamespacePathPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.storageNamespacePathHygieneStatus,
        )
        assertTrue(storageContract.storageNamespacePathPolicyImplemented)
        assertTrue(storageContract.storagePathSegmentEncodingImplemented)
        assertFalse(storageContract.storagePathConstructionImplemented)
        assertFalse(storageContract.platformStorageImplementationAdded)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageNamespacePathHygieneContractApproved),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)

        listOf(
            ProductionProviderAcceptanceEvidenceState.Missing to ProductionProviderAcceptanceBlocker.MissingGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unknown to ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Failed to ProductionProviderAcceptanceBlocker.FailedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unsupported to
                ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        ).forEach { (state, blocker) ->
            val blocked = contract.assess(
                ProductionProviderAcceptanceEvidence(
                    gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                        ProductionProviderAcceptanceEvidenceState.Satisfied
                    } + mapOf(
                        ProductionProviderAcceptanceGate.StorageNamespacePathHygieneContractApproved to state,
                    ),
                ),
            )

            assertContains(blocked.blockers, blocker)
            assertFalse(blocked.productionProviderSelectable)
            assertFalse(blocked.productionPersistenceAllowed)
        }

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageNamespacePathPolicyImplementedAndTested
            ],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageNamespacePathPolicyBuildingBlock)
        assertFalse(readiness.blockers.contains(EncryptedVaultBlockingIssue.StorageNamespacePathImplementationMissing))
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.StoragePathConstructionImplementationMissing)
        assertFalse(readiness.productionPersistenceEnabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.StorageNamespacePathPolicyImplementedTested,
        )
        assertFalse(dependency.blockers.contains(VaultCryptoDependencyBlocker.StorageNamespacePathImplementationMissing))
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.StoragePathConstructionImplementationMissing)
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun assertAcceptedNamespace(result: SkaldVaultV1StorageNamespacePathResult<String>) {
        assertTrue(result is SkaldVaultV1StorageNamespacePathResult.Accepted)
    }

    private fun acceptedSegment(
        result: SkaldVaultV1StorageNamespacePathResult<com.libertasprimordium.skald.security.SkaldVaultV1StoragePathSegment>,
    ): String =
        when (result) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted -> result.value.value
            is SkaldVaultV1StorageNamespacePathResult.Rejected -> error(result.safeMessage)
        }

    private fun assertRejected(
        result: SkaldVaultV1StorageNamespacePathResult<*>,
        expected: SkaldVaultV1StorageNamespacePathRejectionReason,
    ) {
        assertTrue(result is SkaldVaultV1StorageNamespacePathResult.Rejected)
        assertEquals(expected, result.reason)
        assertFalse(result.safeMessage.contains("wallet_label"))
        assertFalse(result.safeMessage.contains("nsec1"))
        assertFalse(result.safeMessage.contains("/absolute"))
    }
}
