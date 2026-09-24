package com.example.bonly.domain

import com.example.bonly.data.BondReportEntity
import com.example.bonly.domain.repository.BondRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseUseCases @Inject constructor(private val repository: BondRepository){
    suspend fun save(report: BondReportEntity){
        repository.saveReport(report)
    }
    suspend fun delete(report: BondReportEntity){
        repository.deleteReport(report)
    }
    fun getAll(): Flow<List<BondReportEntity>>{
        return repository.getAllReports()
    }


}