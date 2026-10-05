package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.MoneroMaterialCandidateKind
import com.libertasprimordium.skald.security.MoneroMaterialCandidatePolicy
import com.libertasprimordium.skald.security.MoneroMaterialRejectionReason
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MoneroMaterialCandidatePolicyTest {
    @Test
    fun everyDeclaredMaterialClassIsRejectedWithoutReturningInputOrAuthority() {
        val candidate = "UNTRUSTED_MATERIAL_FIXTURE"
        MoneroMaterialCandidateKind.entries.forEach { kind ->
            val result = MoneroMaterialCandidatePolicy.reject(kind, candidate)
            assertEquals(kind, result.declaredKind)
            assertEquals(
                if (kind == MoneroMaterialCandidateKind.Unknown) MoneroMaterialRejectionReason.UnsupportedRawCandidate
                else MoneroMaterialRejectionReason.DeclaredMoneroMaterial,
                result.reason,
            )
            assertFalse(result.toString().contains(candidate))
        }
        val selection = VaultCryptoProviderSelectionRegistry.select()
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun blankAndOversizedCandidatesHaveBoundedPayloadFreeRejectionForEveryClass() {
        MoneroMaterialCandidateKind.entries.forEach { kind ->
            listOf(null, "", " ", "\t\n").forEach { candidate ->
                assertEquals(MoneroMaterialRejectionReason.EmptyCandidate, MoneroMaterialCandidatePolicy.reject(kind, candidate).reason)
            }
            val oversized = "Z".repeat(MoneroMaterialCandidatePolicy.MAX_CANDIDATE_CHARACTERS + 1)
            val result = MoneroMaterialCandidatePolicy.reject(kind, oversized)
            assertEquals(MoneroMaterialRejectionReason.OversizedCandidate, result.reason)
            assertFalse(result.toString().contains(oversized))
            val bounded = MoneroMaterialCandidatePolicy.reject(kind, "Z".repeat(MoneroMaterialCandidatePolicy.MAX_CANDIDATE_CHARACTERS))
            assertEquals(
                if (kind == MoneroMaterialCandidateKind.Unknown) MoneroMaterialRejectionReason.UnsupportedRawCandidate
                else MoneroMaterialRejectionReason.DeclaredMoneroMaterial,
                bounded.reason,
            )
        }
    }

    @Test
    fun unknownHexShapeCannotIdentifyAnAddressOrPrivateKeyRole() {
        // All-zero scalar text is deliberately invalid wallet material, used only as a shape probe.
        val invalidScalarShape = "0".repeat(64)
        val result = MoneroMaterialCandidatePolicy.reject(MoneroMaterialCandidateKind.Unknown, invalidScalarShape)
        assertEquals(MoneroMaterialCandidateKind.Unknown, result.declaredKind)
        assertEquals(MoneroMaterialRejectionReason.AmbiguousRawCryptographicMaterial, result.reason)
        assertFalse(result.toString().contains(invalidScalarShape))
        listOf(invalidScalarShape.drop(1), invalidScalarShape + "0", "G".repeat(64), " " + invalidScalarShape).forEach { malformed ->
            assertEquals(MoneroMaterialRejectionReason.UnsupportedRawCandidate, MoneroMaterialCandidatePolicy.reject(MoneroMaterialCandidateKind.Unknown, malformed).reason)
        }
    }

    @Test
    fun addressShapeRejectionCoversBothLengthsWithoutChecksumOrNetworkClaims() {
        // Repeated digit has no valid network prefix/checksum; this is never a wallet address fixture.
        listOf(95, 106).forEach { length ->
            val invalidAddressShape = "1".repeat(length)
            val result = MoneroMaterialCandidatePolicy.reject(MoneroMaterialCandidateKind.Unknown, invalidAddressShape)
            assertEquals(MoneroMaterialRejectionReason.AddressShapedCandidate, result.reason)
            assertEquals(MoneroMaterialCandidateKind.Unknown, result.declaredKind)
            assertFalse(result.toString().contains(invalidAddressShape))
            listOf(invalidAddressShape.drop(1), invalidAddressShape + "1", "0" + invalidAddressShape.drop(1), " " + invalidAddressShape).forEach { malformed ->
                assertEquals(MoneroMaterialRejectionReason.UnsupportedRawCandidate, MoneroMaterialCandidatePolicy.reject(MoneroMaterialCandidateKind.Unknown, malformed).reason)
            }
        }
    }
}
