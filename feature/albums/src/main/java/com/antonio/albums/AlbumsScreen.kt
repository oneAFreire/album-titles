package com.antonio.albums

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.antonio.albums.Network.getImageLoader
import com.antonio.designsystem.component.AlbumTitlesTopBar
import com.antonio.model.Album

@Composable
fun AlbumsInit() {
    AlbumsScreen()
}

@Composable
internal fun AlbumsScreen(
    albumViewModel: AlbumViewModel = hiltViewModel()
) {
    val albums = albumViewModel.albums.collectAsLazyPagingItems()

    Scaffold(
        topBar = { AlbumTitlesTopBar(title = "Meu App") },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
            ) {
                items(albums.itemCount) { index ->
                    albums[index]?.let { album ->
                        AlbumItem(album = album)
                    }
                }

                albums.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { CircularProgressIndicator() }
                        }
                        loadState.append is LoadState.Loading -> {
                            item { CircularProgressIndicator() }
                        }
                    }
                }

                if (albums.loadState.refresh is LoadState.Error) {
                    item {
                        Text("Erro ao carregar dados", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun AlbumItem(album: Album) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(album.thumbnailUrl)
                    .build(),
                imageLoader = getImageLoader(LocalContext.current)
            ),
            contentDescription = "Album Cover",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = album.title, fontWeight = FontWeight.Bold)
            Text(text = "ID: ${album.id}", style = MaterialTheme.typography.bodySmall)
        }
    }
}