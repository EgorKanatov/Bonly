package com.example.bonly.domain

import jakarta.inject.Inject

data class YieldResult(
    val netYield: Double,
    val totalCoupons: Double
)

class CalculateNetYieldUseCase @Inject constructor() {
    fun calculateYield(bond: Bond): YieldResult {
        if (bond.nominal <= 0 || bond.daysToMaturity <= 0) {
            return YieldResult(0.0, 0.0)
        }

        val purchasePrice = bond.nominal * (bond.pricePercent / 100.0) + bond.nkd
        if (purchasePrice <= 0) return YieldResult(0.0, 0.0)

        val yearsToMaturity = bond.daysToMaturity.toDouble() / 365.0
        val totalGrossCoupons = bond.coupon * bond.couponsPerYear * yearsToMaturity
        val totalNetCoupons = totalGrossCoupons * 0.87

        val capitalGain = bond.nominal - (bond.nominal * (bond.pricePercent / 100.0))
        val netCapitalGain = if (capitalGain > 0) capitalGain * 0.87 else capitalGain

        val totalNetProfit = totalNetCoupons + netCapitalGain - bond.nkd

        val annualYield = (totalNetProfit / yearsToMaturity / purchasePrice) * 100.0

        val finalYield = if (annualYield.isNaN() || annualYield.isInfinite()) 0.0 else annualYield

        return YieldResult(
            netYield = finalYield,
            totalCoupons = totalGrossCoupons
        )
    }
}
