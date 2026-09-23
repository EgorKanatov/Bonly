package com.example.bonly.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "bond_reports")
data class BondReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val currentPrice: Double,
    val coupon: Double,
    val netYield: Double,
    val totalCoupons: Double,
)
