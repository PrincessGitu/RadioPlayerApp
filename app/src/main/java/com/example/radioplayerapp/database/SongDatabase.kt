package com.example.radioplayerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.radioplayerapp.model.SongsItem

/**
 * Created by Gitanjali Ghangale on 7/14/2021.
 */

@Database(entities = [SongsItem::class],version = 1)
abstract class SongDatabase: RoomDatabase()  {
    abstract fun getSongDao():SongDao
    companion object{
        @Volatile
        private var instance: SongDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: SongDatabase.createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SongDatabase::class.java,
                "songs_db.db"
            ).build()
    }
}

