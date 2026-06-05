package com.libertasprimordium.skald.domain

data class QuoteRequest(
    val sourceRail: WalletRail,
    val destinationRail: WalletRail,
    val amountSats: Long,
    val network: NetworkEnvironment,
)

data class OperationQuote(
    val operation: String,
    val sourceRail: WalletRail,
    val destinationRail: WalletRail,
    val amountInSats: Long,
    val amountOutSats: Long,
    val feeBreakdown: FeeBreakdown,
    val feeRateSatPerVbyte: Long?,
    val expiry: String,
    val trustBoundary: TrustBoundary,
    val privacyChange: String,
    val failureModes: List<String>,
    val isPlaceholder: Boolean,
)

data class FeeBreakdown(
    val networkFeeSats: Long?,
    val routingFeeSats: Long?,
    val lspFeeSats: Long?,
    val mintFeeSats: Long?,
    val serviceFeeSats: Long?,
)

data class TrustBoundary(
    val title: String,
    val detail: String,
)

interface QuoteEngine {
    fun quote(request: QuoteRequest): OperationQuote
}

class PlaceholderQuoteEngine : QuoteEngine {
    override fun quote(request: QuoteRequest): OperationQuote =
        OperationQuote(
            operation = "Placeholder quote only",
            sourceRail = request.sourceRail,
            destinationRail = request.destinationRail,
            amountInSats = request.amountSats,
            amountOutSats = 0,
            feeBreakdown = FeeBreakdown(
                networkFeeSats = null,
                routingFeeSats = null,
                lspFeeSats = null,
                mintFeeSats = null,
                serviceFeeSats = null,
            ),
            feeRateSatPerVbyte = null,
            expiry = "not issued",
            trustBoundary = TrustBoundary(
                title = "No executable quote",
                detail = "The quote engine interface exists, but fee estimation and routing are not implemented.",
            ),
            privacyChange = "No transaction, payment, mint, melt, or route is built in this scaffold.",
            failureModes = listOf(
                "Requires protocol-specific quote implementation.",
                "Requires user-selected infrastructure.",
                "Requires explicit approval screens before any value-moving operation.",
            ),
            isPlaceholder = true,
        )
}
