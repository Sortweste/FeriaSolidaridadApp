package com.sort.feriaapp.di

import android.content.Context
import android.content.SharedPreferences
import com.sort.feriaapp.utils.SHARE_PREFERENCES_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/*Dependency Injection for SharedPreferences*/
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences(
        SHARE_PREFERENCES_FILE, Context.MODE_PRIVATE)

}