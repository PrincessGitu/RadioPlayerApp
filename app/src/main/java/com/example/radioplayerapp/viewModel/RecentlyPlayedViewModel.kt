package com.example.radioplayerapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radioplayerapp.model.Songs
import com.example.radioplayerapp.model.SongsItem
import com.example.radioplayerapp.repository.SongRepository
import com.example.radioplayerapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class RecentlyPlayedViewModel( private val songRepository: SongRepository) : ViewModel() {

     val songList: MutableLiveData<Resource<Songs>> = MutableLiveData()


     fun getSongs() = viewModelScope.launch {
        songList.postValue(Resource.Loading())
        val response = songRepository.getSongs()
        songList.postValue(handleSongsResponse(response))
    }

    private fun handleSongsResponse(response: Response<Songs>): Resource<Songs> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                addAllSong(resultResponse)
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }

    private fun addAllSong(songsItem: List<SongsItem>)=viewModelScope.launch {
        songRepository.addSongs(songsItem)
    }

    fun getAllLocalSongs()=songRepository.getAllLocalSong()

}