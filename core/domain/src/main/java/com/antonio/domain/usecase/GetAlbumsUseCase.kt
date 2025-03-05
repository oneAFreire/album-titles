package com.antonio.domain.usecase

import com.antonio.model.Album

interface GetAlbumsUseCase {
    suspend operator fun invoke(): List<Album>
}