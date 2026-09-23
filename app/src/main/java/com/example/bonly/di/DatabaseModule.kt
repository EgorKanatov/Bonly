package com.example.bonly.di

import android.content.Context
import androidx.room3.Room
import com.example.bonly.data.AppDatabase
import com.example.bonly.data.BondReportDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "bonly_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBondReportDao(database: AppDatabase): BondReportDao {
        return database.bondReportDao()
    }
}
