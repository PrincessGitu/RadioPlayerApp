package com.example.radioplayerapp.repository

import com.example.radioplayerapp.api.RetrofitInstance
import com.example.radioplayerapp.database.SongDatabase
import com.example.radioplayerapp.model.SongsItem

/**
 * Created by Gitanjali Ghangale on 7/12/2021.
 */
class SongRepository(private val db:SongDatabase) {

    suspend fun getSongs() = RetrofitInstance.api.getAllSongs()
    suspend fun addSongs(songsItem: List<SongsItem>)=db.getSongDao().upsertList(songsItem)
    fun getAllLocalSong()=db.getSongDao().getAllLocalSongs()
}