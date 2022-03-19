package com.example.musicplayerapp.presenters

import android.content.Context
import android.util.Log
import com.example.musicplayerapp.database.MusicsDatabase
//import com.example.musicplayerapp.model.Flowers
import com.example.musicplayerapp.model.MusicItem
//import com.example.musicplayerapp.rest.FlowersService
import com.example.musicplayerapp.rest.MusicsRepository
import com.example.musicplayerapp.rest.MusicsRepositoryImpl
import com.example.musicplayerapp.rest.NetworkUtils
import com.example.musicplayerapp.utils.NetworkMonitor
import com.example.musicplayerapp.utils.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * [MusicViewContract] this is taking of updatign the UI
 */
class ClassicMusicPresenter @Inject constructor(
    private val musicsRepository: MusicsRepository ,
    private val networkUtils: NetworkUtils,
    private val disposables: CompositeDisposable
) : ClassicMusicsPresenter {

    @Inject
    lateinit var musicsDatabase: MusicsDatabase

    private var musicViewContract: MusicViewContract? = null

    override fun initializePresenter(viewContract: MusicViewContract) {
        musicViewContract = viewContract
    }

    override fun checkNetworkConnection() {
        // no-op
        networkUtils.registerForNetworkState()
    }

    override fun getClassicMusics() {
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

        musicsRepository.getClassic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { musics ->
                    Log.d("musicsgot", musics.toString())
                    //musicsDatabase.getMusicsDao().insertAllMusics(musics.results)
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

interface ClassicMusicsPresenter {
    fun initializePresenter(viewContract: MusicViewContract)
    fun checkNetworkConnection()
    fun getClassicMusics()
    fun destroyPresenter()
}

interface MusicViewContract {
    fun loadingMusics(isLoading: Boolean)
    fun musicsSuccess(musics: List<MusicItem>)
    fun onError(error: Throwable)
}