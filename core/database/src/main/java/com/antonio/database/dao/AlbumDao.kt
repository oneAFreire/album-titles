package com.antonio.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antonio.database.DatabaseDefinition.TABLE_ALBUM
import com.antonio.database.model.AlbumEntity

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<AlbumEntity>)

    @Query("SELECT * FROM $TABLE_ALBUM")
    fun getAllAlbums(): List<AlbumEntity>

    @Query(
        value = """
        SELECT * FROM $TABLE_ALBUM
        WHERE id IN (:ids) """,
    )
    fun getAlbums(ids: Set<Int>): List<AlbumEntity>

    @Query("DELETE FROM $TABLE_ALBUM")
    fun clearAlbums()
}