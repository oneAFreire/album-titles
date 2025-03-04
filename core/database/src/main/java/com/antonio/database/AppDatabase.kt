package com.antonio.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antonio.database.dao.AlbumDao
import com.antonio.database.model.AlbumEntity

@Database(entities = [AlbumEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}