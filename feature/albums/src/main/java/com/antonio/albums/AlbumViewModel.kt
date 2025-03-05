package com.antonio.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.antonio.domain.usecase.GetAlbumsUseCase
import com.antonio.domain.usecase.SyncAlbumsUseCase
import com.antonio.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val syncAlbumsUseCase: SyncAlbumsUseCase
) : ViewModel() {
    private val _albums = MutableStateFlow<PagingData<Album>>(PagingData.empty())
    val albums: StateFlow<PagingData<Album>> = _albums

    init {
        refreshAlbums()
    }

    fun loadAlbums() {
        viewModelScope.launch {
            getAlbumsUseCase().cachedIn(viewModelScope).collect { pagingData ->
                _albums.value = pagingData
            }
        }
    }

    private fun refreshAlbums() {
        viewModelScope.launch {
            val success = syncAlbumsUseCase()
            if (success) {
                loadAlbums()
            }
        }
    }
}
