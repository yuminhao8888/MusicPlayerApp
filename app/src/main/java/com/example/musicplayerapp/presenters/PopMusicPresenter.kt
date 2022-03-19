package com.example.musicplayerapp.presenters

import android.util.Log
import com.example.musicplayerapp.model.MusicItem
import com.example.musicplayerapp.rest.MusicsRepository
import com.example.musicplayerapp.rest.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopMusicPresenter @Inject constructor(
    private val musicsRepository: MusicsRepository,
    private val networkUtils: NetworkUtils,
    private val disposables: CompositeDisposable
) : PopMusicsPresenter {

    private var musicViewContract: MusicViewContract? = null

    override fun initializePresenter(viewContract: MusicViewContract) {
        musicViewContract = viewContract
    }

    override fun checkNetworkConnection() {
        // no-op
        networkUtils.registerForNetworkState()
    }

    override fun getPopMusics() {
        musicViewContract?.loadingMusics(true)

        Log.d("network","this is getClassicMusics")

        networkUtils.networkState
            .subscribe(
                { netState -> if (netState) {
                    Log.d("network", "before network call")
                    doNetworkCall()
                } else {
                    musicViewContract?.onError(Throwable("ERROR NO INTERNET CONNECTION"))
                } },
                { musicViewContract?.onError(it) }
            ).apply {
                disposables.add(this)
            }


    }

    private fun doNetworkCall() {

        Log.d("network","this is doNetworkCall")

        musicsRepository.getPop()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { musics ->
                    Log.d("musicsgot", musics.toString())
                    musicViewContract?.musicsSuccess(musics.results) },
                { error ->
                    Log.d("network", "this is error message")
                    musicViewContract?.onError(error) }
            )
            .apply {
                disposables.add(this)
            }
    }

    override fun destroyPresenter() {
        musicViewContract = null
        disposables.clear()
    }
}

interface PopMusicsPresenter {
    fun initializePresenter(viewContract: MusicViewContract)
    fun checkNetworkConnection()
    fun getPopMusics()
    fun destroyPresenter()
}

