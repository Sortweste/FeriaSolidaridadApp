package com.sort.feriaapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sort.feriaapp.data.converters.Converters
import com.sort.feriaapp.data.dao.InstitutionDao
import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.dao.NewsDao
import com.sort.feriaapp.data.dao.UserDao

/*Local database configuration*/
@Database(entities = [Institution::class, Event::class, User::class, News::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun institutionDao(): InstitutionDao
    abstract fun eventDao(): EventDao
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao

}