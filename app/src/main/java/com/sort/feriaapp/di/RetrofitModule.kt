package com.sort.feriaapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sort.feriaapp.network.RequestInterceptor
import com.sort.feriaapp.network.remotes.EventRemoteDataSource
import com.sort.feriaapp.network.remotes.UserRemoteDataSource
import com.sort.feriaapp.network.services.EventService
import com.sort.feriaapp.network.services.UserService
import com.sort.feriaapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(RequestInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideEventService(retrofit: Retrofit): EventService = retrofit.create(EventService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService) = UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideEventRemoteDataSource(eventService: EventService) = EventRemoteDataSource(eventService)

}