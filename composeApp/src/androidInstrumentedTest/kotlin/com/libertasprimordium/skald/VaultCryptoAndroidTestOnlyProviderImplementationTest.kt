package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.AndroidVaultCryptoDependencyCompileProbe
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonDisabledVaultCryptoProviderStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidTestOnlyProviderImplementationTest {
    @Test
    fun androidTestWrapperConfirmsFutureImplementationDependenciesCompileWithoutSelection() {
        val apiClassNames = AndroidVaultCryptoDependencyCompileProbe.availableApiClassNames
        val status = commonDisabledVaultCryptoProviderStatus()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(apiClassNames.any { it.endsWith("XChaCha20Poly1305Key") })
        assertTrue(apiClassNames.any { it.endsWith("InsecureNonceXChaCha20Poly1305") })
        assertTrue(apiClassNames.any { it.endsWith("Argon2BytesGenerator") })
        assertTrue(apiClassNames.any { it.endsWith("ChaCha20Poly1305") })
        assertEquals(VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly, status.implementationStatus)
        assertFalse(status.implementationStatus.canExecuteCrypto)
        assertFalse(status.implementationStatus.productionApproved)
        assertFalse(status.canDeriveKeys)
        assertFalse(status.canEncryptRecords)
        assertFalse(status.canDecryptRecords)
        assertFalse(status.productionPersistenceEnabled)
        assertFalse(status.mainnetEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }
}
