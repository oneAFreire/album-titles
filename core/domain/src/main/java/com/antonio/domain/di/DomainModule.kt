package com.antonio.domain.di

import com.antonio.domain.usecase.GetAlbumsUseCase
import com.antonio.domain.usecase.GetAlbumsUseCaseImpl
import com.antonio.domain.usecase.SyncAlbumsUseCase
import com.antonio.domain.usecase.SyncAlbumsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    internal abstract fun bindsGetAlbumsUseCase(
        getAlbumsUseCaseImpl: GetAlbumsUseCaseImpl,
    ): GetAlbumsUseCase

    @Binds
    internal abstract fun bindsSyncAlbumsUseCase(
        getSyncAlbumsUseCaseImpl: SyncAlbumsUseCaseImpl,
    ): SyncAlbumsUseCase
}