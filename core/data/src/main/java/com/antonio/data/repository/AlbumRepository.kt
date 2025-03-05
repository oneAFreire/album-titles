package com.antonio.data.repository

import androidx.paging.PagingData
import com.antonio.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getPagedAlbums(): Flow<PagingData<Album>>
    suspend fun syncAlbums(): Boolean
}