package com.antonio.domain

import com.antonio.data.repository.AlbumRepository
import com.antonio.model.Album
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(): List<Album> {
        return albumRepository.getAlbums()
    }
}