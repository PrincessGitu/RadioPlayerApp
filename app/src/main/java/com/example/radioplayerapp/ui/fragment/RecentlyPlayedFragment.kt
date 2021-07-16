package com.example.radioplayerapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radioplayerapp.R
import com.example.radioplayerapp.adapter.SongAdapter
import com.example.radioplayerapp.database.SongDatabase
import com.example.radioplayerapp.repository.SongProviderFactory
import com.example.radioplayerapp.repository.SongRepository
import com.example.radioplayerapp.viewModel.RecentlyPlayedViewModel
import com.example.radioplayerapp.utils.InternetCheck.Companion.isNetworkAvailable

class RecentlyPlayedFragment : Fragment() {

    private lateinit var recentlyPlayedViewModel: RecentlyPlayedViewModel
    lateinit var songAdapter: SongAdapter
    private lateinit var progressBar: ProgressBar
    lateinit var rvSongs: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val songRepository= SongRepository(SongDatabase.invoke(requireContext()))
        val songProviderFactory= SongProviderFactory(songRepository)
        recentlyPlayedViewModel = ViewModelProvider(this,songProviderFactory).get(RecentlyPlayedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recently_played, container, false)
        rvSongs = root.findViewById(R.id.rvSongs)
        progressBar = root.findViewById(R.id.progressBar)
        setupRecyclerView()
//        recentlyPlayedViewModel.songList.observe(viewLifecycleOwner, { response ->
//            when (response) {
//                is Resource.Success -> {
//                    hideProgressBar()
//                    response.data?.let { songResponse ->
//                        songAdapter.setSongList(songResponse)
//
//                    }
//                }
//                is Resource.Error -> {
//                    hideProgressBar()
//                    response.message?.let { message ->
//                        Log.e("RecentlyPlayedFragment", "An error occured: $message")
//                    }
//                }
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
//            }
//
//        })

        recentlyPlayedViewModel.getAllLocalSongs().observe(viewLifecycleOwner,{response->
            songAdapter.setSongList(response)
        })
        return root
    }

    override fun onResume() {
        super.onResume()
        if (isNetworkAvailable(requireContext())) {
            recentlyPlayedViewModel.getSongs()

        } else if (!isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
        }
    }
    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {

        songAdapter = SongAdapter(recentlyPlayedViewModel, arrayListOf())
        rvSongs.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    context, (layoutManager as LinearLayoutManager).orientation
                )
            )
        }

    }//setupRecyclerView
}