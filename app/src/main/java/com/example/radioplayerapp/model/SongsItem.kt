package com.example.radioplayerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "songs")
data class SongsItem(
    val album: String,
    val artist: String,
    val image_url: String,
    val link_url: String,
    @NotNull
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val played_at: String,
    val preview_url: String,
    val sid: String
)