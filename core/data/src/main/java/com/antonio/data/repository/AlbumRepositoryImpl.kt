package com.antonio.data.repository

import com.antonio.data.model.asEntity
import com.antonio.data.model.asExternalModel
import com.antonio.database.dao.AlbumDao
import com.antonio.model.Album
import com.antonio.network.LeboncoinNetworkDataSource
import javax.inject.Inject

/**
 * Reads from local storage to support offline access.
 */
internal class AlbumRepositoryImpl @Inject constructor(
    private val networkDataSource: LeboncoinNetworkDataSource,
    private val albumDao: AlbumDao
): AlbumRepository {
    override suspend fun getAlbums(): List<Album> {
        return albumDao.getAllAlbums().map { it.asExternalModel() }
    }

    override suspend fun getAlbums(ids: Set<Int>): List<Album> {
        return albumDao.getAlbums(ids).map { it.asExternalModel() }
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