package com.example.bonly.di

import com.example.bonly.data.BondRepositoryImpl
import com.example.bonly.domain.repository.BondRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBondRepository(
        impl: BondRepositoryImpl
    ): BondRepository
}
