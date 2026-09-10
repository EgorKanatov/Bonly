package com.example.bonly.domain

data class Bond(
    val name: String,
    val nominal: Double,
    val pricePercent: Double,
    val coupon: Double,
    val nkd: Double,
    val couponsPerYear: Int,
    val daysToMaturity: Int,

    val clearPrice: Double = nominal * pricePercent / 100,
    val years: Int = (daysToMaturity / 365.0).toInt(),
    val paydays: Int = years * couponsPerYear,

)
