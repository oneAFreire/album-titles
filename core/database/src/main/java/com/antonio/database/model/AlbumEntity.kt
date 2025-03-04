package com.antonio.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antonio.database.DatabaseDefinition.TABLE_ALBUM

/**
 * Defines an Album.
 */
@Entity(tableName = TABLE_ALBUM)
data class AlbumEntity(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)