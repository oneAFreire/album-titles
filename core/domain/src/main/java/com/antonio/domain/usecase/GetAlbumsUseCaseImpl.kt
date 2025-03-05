package com.antonio.domain.usecase

import androidx.paging.PagingData
import com.antonio.data.repository.AlbumRepository
import com.antonio.model.Album
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAlbumsUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
): GetAlbumsUseCase {
    override suspend operator fun invoke(): Flow<PagingData<Album>> {
        return albumRepository.getPagedAlbums()
    }
}