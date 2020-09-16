package com.sort.feriaapp.network

import okhttp3.Interceptor
import okhttp3.Response

/*Interceptor to add header Content-Type: application/json to all request in Retrofit*/
class RequestInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("Content-Type", "application/json; charset=utf-8")
        return chain.proceed(builder.build())
    }
}