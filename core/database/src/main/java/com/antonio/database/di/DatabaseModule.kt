package com.antonio.database.di

import android.content.Context
import androidx.room.Room
import com.antonio.database.AppDatabase
import com.antonio.database.DatabaseDefinition
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DatabaseDefinition.NAME
        ).allowMainThreadQueries().build()
    }
}