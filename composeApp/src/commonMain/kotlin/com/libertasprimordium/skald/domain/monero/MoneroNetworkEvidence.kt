package com.libertasprimordium.skald.domain.monero

/** Chain identity evidence only. No value grants wallet or storage authorization. */
enum class MoneroNetworkEvidence {
    Unspecified,
    Mainnet,
    Testnet,
    Stagenet,
}
