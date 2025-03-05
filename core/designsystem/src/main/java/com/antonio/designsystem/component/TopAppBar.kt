package com.antonio.designsystem.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumTitlesTopBar(
    title: String,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewSimpleTopBar() {
    AlbumTitlesTopBar(title = "Album Titles")
}
