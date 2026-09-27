package com.example.bonly.presentation.reports

import com.example.bonly.data.BondReportEntity

data class ReportsState(
    val reports: List<BondReportEntity> = emptyList(),
    val isLoading: Boolean = true,
    val isEmpty: Boolean = false
)
