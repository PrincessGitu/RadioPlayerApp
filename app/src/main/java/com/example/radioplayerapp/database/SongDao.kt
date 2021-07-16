package com.example.radioplayerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.radioplayerapp.model.SongsItem


/**
 * Created by Gitanjali Ghangale on 7/14/2021.
 */

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(song: SongsItem): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertList(song: List<SongsItem>)

    @Query("SELECT * FROM songs")
    fun getAllLocalSongs(): LiveData<List<SongsItem>>

    @Delete
    suspend fun deleteSong(song: SongsItem)
}