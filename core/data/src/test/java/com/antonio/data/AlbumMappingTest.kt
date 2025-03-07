package com.antonio.data

import com.antonio.data.model.asEntity
import com.antonio.data.model.asExternalModel
import com.antonio.database.model.AlbumEntity
import com.antonio.network.model.NetworkAlbum
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AlbumMappingTest {
    private lateinit var albumEntity: AlbumEntity
    private lateinit var networkAlbum: NetworkAlbum

    @Before
    fun setUp() {
        albumEntity = AlbumEntity(
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
    fun `test NetworkAlbum to AlbumEntity mapping`() {
        // Act
        val albumEntity = networkAlbum.asEntity()

        // Assert
        assertEquals(networkAlbum.id, albumEntity.id)
        assertEquals(networkAlbum.albumId, albumEntity.albumId)
        assertEquals(networkAlbum.title, albumEntity.title)
        assertEquals(networkAlbum.url, albumEntity.url)
        assertEquals(networkAlbum.thumbnailUrl, albumEntity.thumbnailUrl)
    }

    @Test
    fun `test AlbumEntity to Album mapping`() {
        // Act
        val album = albumEntity.asExternalModel()

        // Assert
        assertEquals(albumEntity.id, album.id)
        assertEquals(albumEntity.albumId, album.albumId)
        assertEquals(albumEntity.title, album.title)
        assertEquals(albumEntity.url, album.url)
        assertEquals(albumEntity.thumbnailUrl, album.thumbnailUrl)
    }
}
