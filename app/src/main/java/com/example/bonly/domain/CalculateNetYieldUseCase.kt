package com.example.bonly.domain

import javax.inject.Inject

class CalculateNetYieldUseCase @Inject constructor() {
    fun calculateYield(bond: Bond): Double {
        if (bond.nominal <= 0 || bond.daysToMaturity <= 0) return 0.0

        // 1. Считаем затраты на покупку одной облигации
        val purchasePrice = bond.nominal * (bond.pricePercent / 100.0) + bond.nkd

        if (purchasePrice <= 0) return 0.0

        // 2. Считаем количество лет до погашения
        val yearsToMaturity = bond.daysToMaturity.toDouble() / 365.0

        // 3. Валовая сумма купонов за весь период
        val totalGrossCoupons = bond.coupon * bond.couponsPerYear * yearsToMaturity

        // 4. Купоны за вычетом НДФЛ 13%
        val totalNetCoupons = totalGrossCoupons * 0.87

        // 5. Выплата номинала в конце срока (дисконт/премия)
        val capitalGain = bond.nominal - (bond.nominal * (bond.pricePercent / 100.0))

        // НДФЛ на прирост капитала (если купили дешевле номинала)
        val netCapitalGain = if (capitalGain > 0) capitalGain * 0.87 else capitalGain

        // 6. Итоговый чистый доход
        val totalNetProfit = totalNetCoupons + netCapitalGain

        // 7. Чистая годовая доходность к погашению (%)
        val annualYield = (totalNetProfit / purchasePrice) * (1.0 / yearsToMaturity) * 100.0

        return if (annualYield.isNaN() || annualYield.isInfinite()) 0.0 else annualYield
    }
}
