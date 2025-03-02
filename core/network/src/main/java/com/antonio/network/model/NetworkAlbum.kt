package com.antonio.network.model

/**
 * Network representation of Album when fetched from /img/shared/technical-test.json"
 */
data class NetworkAlbum(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
