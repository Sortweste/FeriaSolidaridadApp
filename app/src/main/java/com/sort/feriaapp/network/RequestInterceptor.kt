package com.sort.feriaapp.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("Content-Type", "application/json; charset=utf-8")
        return chain.proceed(builder.build())
    }
}