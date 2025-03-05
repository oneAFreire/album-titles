package com.antonio.albums

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
        topBar = { AlbumTitlesTopBar(title = stringResource(id = R.string.top_bar_title)) },
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
                        Text(
                            stringResource(id = R.string.error_getting_data),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun AlbumItem(album: Album) {
    val context = LocalContext.current

    val imageLoader = remember { getImageLoader(context) }

    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(album.thumbnailUrl)
                    .build(),
                imageLoader = imageLoader
            ),
            contentDescription = stringResource(id = R.string.album_cover),
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = album.title, fontWeight = FontWeight.Bold)
    }
}