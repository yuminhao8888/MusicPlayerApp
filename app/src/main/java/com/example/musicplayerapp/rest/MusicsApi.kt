package com.example.musicplayerapp.rest

import com.example.musicplayerapp.model.Music
import com.example.musicplayerapp.model.MusicItem
import io.reactivex.Single
import retrofit2.http.GET




interface MusicsApi {

    /**
     * Right here I am getting the data from server
     *
     * Using the Single observable to make the asynchronous task
     */
    @GET(CLASSIC_MUSIC)
    fun getClassicMusic(): Single<Music>

    @GET(POP_MUSIC)
    fun getPopMusic(): Single<Music>

    @GET(ROCK_MUSIC)
    fun getRockMusic(): Single<Music>

    /**
     * Companion object will handle the base url and the path
     */
    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        private const val CLASSIC_MUSIC = "search?term=classick&amp;media=music&amp;entity=song&amp;limit=50"
        private const val POP_MUSIC = "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
        private const val ROCK_MUSIC = "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
    }
}