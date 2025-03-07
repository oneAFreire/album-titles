package com.antonio.data.di

import com.antonio.data.repository.AlbumRepository
import com.antonio.data.repository.AlbumRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl,
    ): AlbumRepository
}