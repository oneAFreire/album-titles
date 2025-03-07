package com.antonio.domain

import com.antonio.data.repository.AlbumRepository
import com.antonio.domain.usecase.SyncAlbumsUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SyncAlbumsUseCaseTest {

    @Mock
    private lateinit var mockAlbumRepository: AlbumRepository
    private lateinit var syncAlbumsUseCase: SyncAlbumsUseCaseImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        syncAlbumsUseCase = SyncAlbumsUseCaseImpl(mockAlbumRepository)
    }

    @Test
    fun `test invoke returns true when syncAlbums is successful`(): Unit = runBlocking {
        // Arrange
        Mockito.`when`(mockAlbumRepository.syncAlbums()).thenReturn(true)

        // Act
        val result = syncAlbumsUseCase.invoke()

        // Assert
        assertTrue(result)
        Mockito.verify(mockAlbumRepository).syncAlbums()
    }

    @Test
    fun `test invoke returns false when syncAlbums fails`(): Unit = runBlocking {
        // Arrange
        Mockito.`when`(mockAlbumRepository.syncAlbums()).thenReturn(false)

        // Act
        val result = syncAlbumsUseCase.invoke()

        // Assert
        assertFalse(result)
        Mockito.verify(mockAlbumRepository).syncAlbums()
    }
}
