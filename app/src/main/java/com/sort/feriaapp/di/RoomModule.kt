package com.sort.feriaapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.dao.InstitutionDao
import com.sort.feriaapp.data.dao.NewsDao
import com.sort.feriaapp.data.dao.UserDao
import com.sort.feriaapp.utils.DATABASE_NAME
import com.sort.feriaapp.workers.SeedDatabaseWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideInstitutionDao(appDatabase: AppDatabase): InstitutionDao = appDatabase.institutionDao()

    @Singleton
    @Provides
    fun provideEventDao(appDatabase: AppDatabase): EventDao = appDatabase.eventDao()

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()

}