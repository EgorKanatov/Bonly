package com.example.bonly.domain.repository

import com.example.bonly.data.BondReportEntity
import kotlinx.coroutines.flow.Flow

interface BondRepository {
    suspend fun saveReport(report: BondReportEntity)
    suspend fun deleteReport(report: BondReportEntity)
    fun getAllReports(): Flow<List<BondReportEntity>>
}
