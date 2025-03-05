package com.antonio.albumtitles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonio.domain.usecase.GetAlbumsUseCase
import com.antonio.domain.usecase.SyncAlbumsUseCase
import com.antonio.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val syncAlbumsUseCase: SyncAlbumsUseCase
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    fun loadAlbums() {
        viewModelScope.launch {
            _albums.value = getAlbumsUseCase()
        }
    }

    fun refreshAlbums() {
        viewModelScope.launch {
            val success = syncAlbumsUseCase()
            if (success) {
                _albums.value = getAlbumsUseCase()
            }
        }
    }
}
