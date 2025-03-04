package com.antonio.data.model

import com.antonio.database.model.AlbumEntity
import com.antonio.model.Album
import com.antonio.network.model.NetworkAlbum

fun NetworkAlbum.asEntity() = AlbumEntity(
    id = id,
    albumId = albumId,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)

fun AlbumEntity.asExternalModel() = Album(
    id = id,
    albumId = albumId,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)