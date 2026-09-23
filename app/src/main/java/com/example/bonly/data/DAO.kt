package com.example.bonly.data

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BondReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: BondReportEntity)

    @Delete
    suspend fun deleteReport(report: BondReportEntity)

    @Query("SELECT * FROM bond_reports ORDER BY id DESC")
    fun getAllReports(): Flow<List<BondReportEntity>>
}
