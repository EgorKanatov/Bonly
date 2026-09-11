package com.example.bonly.domain

import javax.inject.Inject

class CalculateNetYieldUseCase @Inject constructor(
    private val costUseCase: CalculateBondCostUseCase,
    private val taxesUseCase: CalculateTaxesUseCase
) {
    fun calculateYield(bond: Bond): Double{

        val dirtyPrice = costUseCase.calculatePrice(bond)
        val couponTax = taxesUseCase.calculateCouponTax(bond.coupon)
        val couponGainTax = taxesUseCase.calculateCapitalGainTax(bond.nominal, bond.clearPrice)

        val profit = (bond.nominal - dirtyPrice - couponGainTax) + ((bond.coupon - couponTax) * bond.paydays)
        val profitPerYear = (profit / dirtyPrice) * (365.0 / bond.daysToMaturity) * 100
        return profitPerYear
    }
}