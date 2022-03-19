package com.example.musicplayerapp.di

import android.content.Context
import com.example.musicplayerapp.presenters.*

import com.example.musicplayerapp.rest.MusicsRepository
import com.example.musicplayerapp.rest.NetworkUtils
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class PresentersModule {

    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

//    @Provides
//    fun provideContext() =

    @Provides
    fun provideNetworkUtils(context: Context) = NetworkUtils(context)

    @Provides
    fun providesClassicMusicsPresenter(
        musicsRepository: MusicsRepository,
        networkUtils: NetworkUtils,
        disposable: CompositeDisposable
    ): ClassicMusicsPresenter {
        return ClassicMusicPresenter(musicsRepository, networkUtils,disposable)
    }

    @Provides
    fun providesPopMusicsPresenter(
        musicsRepository: MusicsRepository,
        networkUtils: NetworkUtils,
        disposable: CompositeDisposable
    ): PopMusicsPresenter {
        return PopMusicPresenter(musicsRepository, networkUtils,disposable)
    }

    @Provides
    fun providesRockMusicsPresenter(
        musicsRepository: MusicsRepository,
        networkUtils: NetworkUtils,
        disposable: CompositeDisposable
    ): RockMusicsPresenter {
        return RockMusicPresenter(musicsRepository, networkUtils,disposable)
    }
}