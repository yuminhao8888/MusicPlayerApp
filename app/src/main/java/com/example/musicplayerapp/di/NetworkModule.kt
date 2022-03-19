package com.example.musicplayerapp.di


import com.example.musicplayerapp.rest.MusicsApi
import com.example.musicplayerapp.rest.MusicsRepository
import com.example.musicplayerapp.rest.MusicsRepositoryImpl
import com.example.musicplayerapp.rest.RequestInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providesGson() = Gson()

    @Provides
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesRequestInterceptor() = RequestInterceptor()

    @Provides
    fun providesOkHttpClient(
        requestInterceptor: RequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofitService(okHttpClient: OkHttpClient, gson: Gson): MusicsApi {
        return Retrofit.Builder()
            .baseUrl(MusicsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MusicsApi::class.java)
    }

    @Provides
    fun providesMusicsRepository(musicsApi: MusicsApi): MusicsRepository {
        return MusicsRepositoryImpl(musicsApi)
    }
}