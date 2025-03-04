package com.antonio.data.repository

import com.antonio.model.Album

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbums(ids: Set<Int>): List<Album>
    suspend fun syncAlbums(): Boolean
}