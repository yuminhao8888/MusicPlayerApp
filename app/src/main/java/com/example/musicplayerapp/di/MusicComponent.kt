package com.example.musicplayerapp.di

import com.example.musicplayerapp.di.NetworkModule
import com.example.musicplayerapp.MainActivity
import com.example.musicplayerapp.views.ClassicFragment
import com.example.musicplayerapp.views.PopFragment
import com.example.musicplayerapp.views.RockFragment
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        ApplicationModule::class,
        PresentersModule::class
    ]
)
interface MusicComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(classicFragment: ClassicFragment)
    fun inject(rockFragment: RockFragment)
    fun inject(popFragment: PopFragment)
}