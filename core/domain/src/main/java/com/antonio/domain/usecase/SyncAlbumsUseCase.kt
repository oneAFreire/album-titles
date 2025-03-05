package com.antonio.domain.usecase

interface SyncAlbumsUseCase {
    suspend operator fun invoke(): Boolean
}