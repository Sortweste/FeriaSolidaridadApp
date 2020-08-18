package com.sort.feriaapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sort.feriaapp.data.dao.InstitutionDao
import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.dao.NewsDao
import com.sort.feriaapp.data.dao.UserDao
import com.sort.feriaapp.utils.DATABASE_NAME
import com.sort.feriaapp.workers.SeedDatabaseWorker

@Database(entities = [Institution::class, Event::class, User::class, News::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun institutionDao(): InstitutionDao
    abstract fun eventDao(): EventDao
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao

}