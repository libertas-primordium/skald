package com.libertasprimordium.skald.domain.core

@JvmInline
value class SatsAmount(val value: Long) {
    init {
        require(value >= 0) { "SatsAmount cannot be negative." }
    }
}
