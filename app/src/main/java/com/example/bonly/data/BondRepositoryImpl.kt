package com.example.bonly.data

import com.example.bonly.domain.repository.BondRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BondRepositoryImpl @Inject constructor(
    private val dao: BondReportDao
) : BondRepository {

    override suspend fun saveReport(report: BondReportEntity) {
        dao.insertReport(report)
    }

    override suspend fun deleteReport(report: BondReportEntity) {
        dao.deleteReport(report)
    }

    override fun getAllReports(): Flow<List<BondReportEntity>> {
        return dao.getAllReports()
    }
}
