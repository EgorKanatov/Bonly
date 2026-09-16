package com.example.bonly.domain

import javax.inject.Inject

class CalculateCouponSumUseCase @Inject constructor() {
    fun calculateCouponSum(bond: Bond): Double{
        if (bond.daysToMaturity <= 0 || bond.couponsPerYear <= 0) return 0.0
        val years = bond.daysToMaturity.toDouble() / 365.0
        return bond.coupon * bond.couponsPerYear * years
    }
}