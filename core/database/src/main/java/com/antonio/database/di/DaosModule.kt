package com.antonio.database.di

import com.antonio.database.AppDatabase
import com.antonio.database.dao.AlbumDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: AppDatabase,
    ): AlbumDao = database.albumDao()
}