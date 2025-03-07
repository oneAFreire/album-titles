package com.antonio.data

import com.antonio.data.repository.AlbumRepositoryImpl
import com.antonio.database.dao.AlbumDao
import com.antonio.database.model.AlbumEntity
import com.antonio.model.Album
import com.antonio.network.LeboncoinNetworkDataSource
import com.antonio.network.model.NetworkAlbum
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

class AlbumRepositoryImplTest {

    private lateinit var albumRepository: AlbumRepositoryImpl
    private lateinit var networkDataSource: LeboncoinNetworkDataSource
    private lateinit var albumDao: AlbumDao
    private lateinit var albumEntity: AlbumEntity
    private lateinit var album: Album
    private lateinit var networkAlbum: NetworkAlbum

    @Before
    fun setUp() {
        networkDataSource = mock(LeboncoinNetworkDataSource::class.java)
        albumDao = mock(AlbumDao::class.java)
        albumRepository = AlbumRepositoryImpl(networkDataSource, albumDao)
        albumEntity = AlbumEntity(
            id = 1,
            albumId = 1,
            title = "Test title",
            url = "Test url",
            thumbnailUrl = "Test thumbnailUrl"
        )
        album = Album(
            id = 1,
            albumId = 1,
            title = "Test title",
            url = "Test url",
            thumbnailUrl = "Test thumbnailUrl"
        )
        networkAlbum = NetworkAlbum(
            id = 1,
            albumId = 1,
            title = "Test title",
            url = "Test url",
            thumbnailUrl = "Test thumbnailUrl"
        )
    }

    @Test
    fun `getAlbums returns mapped albums from dao`() = runBlocking {
        // Arrange
        val albumEntities = listOf(albumEntity)
        val albumListMapped = listOf(album)

        `when`(albumDao.getAllAlbums()).thenReturn(albumEntities)

        // Act
        val albums = albumRepository.getAlbums()

        // Assert
        assertEquals(albumListMapped, albums)
    }

    @Test
    fun `syncAlbums inserts albums from network data source`() = runBlocking {
        // Arrange
        val networkAlbums = listOf(networkAlbum)
        val albumEntities = listOf(albumEntity)

        `when`(networkDataSource.getAlbums()).thenReturn(networkAlbums)

        // Act
        val result = albumRepository.syncAlbums()

        // Assert
        assertTrue(result)
        verify(albumDao).clearAlbums()
        verify(albumDao).insertAlbums(albumEntities)
    }

    @Test
    fun `syncAlbums returns false when network data source is empty`() = runBlocking {
        // Arrange
        `when`(networkDataSource.getAlbums()).thenReturn(emptyList())

        // Act
        val result = albumRepository.syncAlbums()

        // Assert
        assertFalse(result)
        verify(albumDao, never()).clearAlbums()
        verify(albumDao, never()).insertAlbums(any())
    }

    @Test
    fun `syncAlbums returns false on exception`() = runBlocking {
        // Arrange
        `when`(networkDataSource.getAlbums()).thenThrow(RuntimeException("Network error"))

        // Act
        val result = albumRepository.syncAlbums()

        // Assert
        assertFalse(result)
        verify(albumDao, never()).clearAlbums()
        verify(albumDao, never()).insertAlbums(any())
    }
}
