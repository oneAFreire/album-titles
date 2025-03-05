package com.antonio.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.antonio.data.model.asEntity
import com.antonio.data.model.asExternalModel
import com.antonio.database.dao.AlbumDao
import com.antonio.model.Album
import com.antonio.network.LeboncoinNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Reads from local storage to support offline access.
 */
internal class AlbumRepositoryImpl @Inject constructor(
    private val networkDataSource: LeboncoinNetworkDataSource,
    private val albumDao: AlbumDao
) : AlbumRepository {
    override suspend fun getAlbums(): List<Album> {
        return albumDao.getAllAlbums().map { it.asExternalModel() }
    }

    override suspend fun getPagedAlbums(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 40,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { albumDao.getPagedAlbums() }
        ).flow.map { pagingData ->
            pagingData.map { it.asExternalModel() }
        }
    }

    override suspend fun syncAlbums(): Boolean {
        return try {
            val networkAlbums = networkDataSource.getAlbums()

            if (networkAlbums.isNotEmpty()) {
                val entities = networkAlbums.map { it.asEntity() }

                albumDao.clearAlbums()
                albumDao.insertAlbums(entities)

                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}