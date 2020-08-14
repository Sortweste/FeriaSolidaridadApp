package com.sort.feriaapp.network

import com.sort.feriaapp.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    companion object {

        fun create(): WebService{

            val client = OkHttpClient.Builder().apply {
                addInterceptor (
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("Content-Type", "application/json; charset=utf-8")
                        return@Interceptor chain.proceed(builder.build())
                    }
                )}.build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()
                .create(WebService::class.java)
        }
    }

}