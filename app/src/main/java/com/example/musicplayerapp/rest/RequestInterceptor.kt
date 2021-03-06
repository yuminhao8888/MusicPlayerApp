package com.example.musicplayerapp.rest

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
    }
}


