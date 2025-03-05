package com.antonio.domain.usecase

import com.antonio.data.repository.AlbumRepository
import com.antonio.model.Album
import javax.inject.Inject

internal class GetAlbumsUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
): GetAlbumsUseCase {
    override suspend operator fun invoke(): List<Album> {
        return albumRepository.getAlbums()
    }
}