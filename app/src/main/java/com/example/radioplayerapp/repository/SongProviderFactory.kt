package com.example.radioplayerapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.radioplayerapp.viewModel.RecentlyPlayedViewModel

/**
 * Created by Gitanjali Ghangale on 7/12/2021.
 */

@Suppress("UNCHECKED_CAST")
class SongProviderFactory(val songRepository: SongRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecentlyPlayedViewModel(songRepository) as T
    }
}