package com.example.radioplayerapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radioplayerapp.R
import com.example.radioplayerapp.model.SongsItem
import com.example.radioplayerapp.viewModel.RecentlyPlayedViewModel
import kotlinx.android.synthetic.main.row_songs.view.*

/**
 * Created by Gitanjali Ghangale on 7/12/2021.
 */
class SongAdapter(val viewModel: RecentlyPlayedViewModel, private val songList: ArrayList<SongsItem>):RecyclerView.Adapter<SongAdapter.SongViewHolder>() {


    class SongViewHolder(item:View): RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_songs, parent, false))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song=songList[position]
        Log.e("song.name","="+song.name)
        holder.itemView.textSongTitle.text=song.name
        holder.itemView.textArtistName.text=song.artist

    }

    override fun getItemCount(): Int {
        Log.e("size","="+songList.size)
       return songList.size
    }

    fun setSongList(list: List<SongsItem>){
        this.songList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}
