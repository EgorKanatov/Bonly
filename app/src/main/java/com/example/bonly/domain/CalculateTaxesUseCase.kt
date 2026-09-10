package com.example.bonly.domain

import javax.inject.Inject

class CalculateTaxesUseCase @Inject constructor() {
    fun calculateCouponTax(coupon: Double): Double {
        return coupon * 0.13
    }

    fun calculateCapitalGainTax(
        nominal: Double,
        cleanPrice: Double
    ): Double {
        return if (cleanPrice<nominal) (nominal-cleanPrice)* 0.13 else 0.0
    }

}