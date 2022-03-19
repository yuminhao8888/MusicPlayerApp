package com.example.musicplayerapp

import android.app.Application
import android.util.Log
import com.example.musicplayerapp.di.ApplicationModule
import com.example.musicplayerapp.di.DaggerMusicComponent
import com.example.musicplayerapp.di.MusicComponent

class MusicApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("main fragment", "on create main activity")

        musicComponent = DaggerMusicComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var musicComponent : MusicComponent
    }
}