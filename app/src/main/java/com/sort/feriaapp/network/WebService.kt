package com.sort.feriaapp.network

import com.sort.feriaapp.network.services.EventService
import com.sort.feriaapp.network.services.UserService
import com.sort.feriaapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebService {

    private fun instance(): Retrofit {

        val client = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    val userService: UserService by lazy {
        instance().create(UserService::class.java)
    }

    val eventService: EventService by lazy {
        instance().create(EventService::class.java)
    }

}

