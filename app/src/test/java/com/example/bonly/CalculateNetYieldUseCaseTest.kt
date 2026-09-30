/*
package com.example.bonly

import com.example.bonly.domain.Bond
import com.example.bonly.domain.CalculateNetYieldUseCase
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculateNetYieldUseCaseTest {

    @Test
    fun testNegativeYield() {
        val useCase = CalculateNetYieldUseCase()
        val bond = Bond(
            name = "Test Bond",
            nominal = 1000.0,
            pricePercent = 150.0,
            coupon = 0.0,
            nkd = 0.0,
            couponsPerYear = 2,
            daysToMaturity = 365
        )

        val yield = useCase.calculateYield(bond)
        println("Calculated yield: $yield")
        assertTrue("Yield should be negative, but was $yield", yield < 0.0)
    }

    @Test
    fun testNkdNegativeYield() {
        val useCase = CalculateNetYieldUseCase()
        val bond = Bond(
            name = "Test Bond NKD",
            nominal = 1000.0,
            pricePercent = 100.0,
            coupon = 0.0,
            nkd = 100.0,
            couponsPerYear = 2,
            daysToMaturity = 365
        )

        val yield = useCase.calculateYield(bond)
        println("Calculated yield with NKD: $yield")
        assertTrue("Yield should be negative when NKD > 0 and coupon = 0, but was $yield", yield < 0.0)
    }
}
*/
