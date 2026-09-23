package com.example.bonly.data

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(entities = [BondReportEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun bondReportDao(): BondReportDao
}