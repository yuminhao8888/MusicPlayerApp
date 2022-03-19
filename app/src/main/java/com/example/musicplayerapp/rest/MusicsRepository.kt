package com.example.musicplayerapp.rest

import com.example.musicplayerapp.model.Music
import com.example.musicplayerapp.model.MusicItem
import io.reactivex.Single
import javax.inject.Inject



interface MusicsRepository {
    fun getClassic(): Single<Music>
    fun getPop(): Single<Music>
    fun getRock(): Single<Music>
}

class MusicsRepositoryImpl @Inject constructor(
    private val musicApi: MusicsApi
) : MusicsRepository {

    override fun getClassic(): Single<Music> {
        return musicApi.getClassicMusic()
    }

    override fun getPop(): Single<Music> {
        return musicApi.getPopMusic()
    }

    override fun getRock(): Single<Music> {
        return musicApi.getRockMusic()
    }
}