package com.libertasprimordium.skald.security

class AndroidSecureStorage : SecureSecretStorage by DisabledSecureSecretStorage(
    capability = androidDisabledSecureStorageCapability(),
)
