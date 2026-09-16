package com.example.bonly.presentation

data class CalculatorState(
    val nominal: String = "1000",
    val pricePercent: String = "100",
    val coupon: String = "0",
    val nkd: String = "0",
    val couponsPerYear: String = "2",
    val daysToMaturity: String = "365",
    val yieldResult: Double = 0.0,
    val couponSum: Double = 0.0
)
