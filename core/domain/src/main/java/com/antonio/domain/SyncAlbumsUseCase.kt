package com.antonio.domain

import com.antonio.data.repository.AlbumRepository
import javax.inject.Inject

class SyncAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(): Boolean {
        return albumRepository.syncAlbums()
    }
}