package com.example.musicplayerapp.di

import android.content.Context
import androidx.room.Room
import com.example.musicplayerapp.database.MusicsDatabase
import com.example.musicplayerapp.rest.MusicsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class DatabaseModule {

    @Provides
    fun providesDatabase(context:Context): MusicsDatabase {
        return Room.databaseBuilder(context, MusicsDatabase::class.java, "music_db").build()
    }
}