package com.antonio.domain.usecase

import com.antonio.data.repository.AlbumRepository
import javax.inject.Inject

internal class SyncAlbumsUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
): SyncAlbumsUseCase {
    override suspend operator fun invoke(): Boolean {
        return albumRepository.syncAlbums()
    }
}