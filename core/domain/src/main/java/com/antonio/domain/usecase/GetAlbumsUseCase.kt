package com.antonio.domain.usecase

import androidx.paging.PagingData
import com.antonio.model.Album
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {
    suspend operator fun invoke(): Flow<PagingData<Album>>
}