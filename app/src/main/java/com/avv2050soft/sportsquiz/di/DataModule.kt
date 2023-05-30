package com.avv2050soft.sportsquiz.di

import android.content.Context
import com.avv2050soft.sportsquiz.data.DatabaseRepositoryImpl
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(@ApplicationContext context: Context) : DatabaseRepository{
        return DatabaseRepositoryImpl(context = context)
    }
}