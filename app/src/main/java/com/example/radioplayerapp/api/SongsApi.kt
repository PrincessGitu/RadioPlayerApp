package com.example.radioplayerapp.api

import com.example.radioplayerapp.model.Songs
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Gitanjali Ghangale on 7/12/2021.
 */
interface SongsApi {

    @GET("testapi")
    suspend fun getAllSongs(): Response<Songs>
}