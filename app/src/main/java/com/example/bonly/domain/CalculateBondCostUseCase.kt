package com.example.bonly.domain

import javax.inject.Inject

class CalculateBondCostUseCase @Inject constructor() {
    fun calculatePrice(bond: Bond): Double {
        return (bond.nominal * bond.pricePercent / 100) + bond.nkd
    }
}