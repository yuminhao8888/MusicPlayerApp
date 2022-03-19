package com.example.musicplayerapp.model


import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<MusicItem>
)