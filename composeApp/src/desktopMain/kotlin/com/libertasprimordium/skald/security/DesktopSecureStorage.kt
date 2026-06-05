package com.libertasprimordium.skald.security

class DesktopSecureStorage : SecureSecretStorage by DisabledSecureSecretStorage(
    capability = desktopDisabledSecureStorageCapability(),
)
