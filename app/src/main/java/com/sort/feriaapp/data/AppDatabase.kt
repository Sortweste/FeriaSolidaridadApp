package com.sort.feriaapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sort.feriaapp.data.dao.ArticleDao
import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.dao.SocialMediaDao
import com.sort.feriaapp.utils.DATABASE_NAME
import com.sort.feriaapp.workers.SeedDatabaseWorker
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Article::class, Event::class, SocialMedia::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun eventDao(): EventDao
    abstract fun socialMediaDao(): SocialMediaDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {instance = it}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
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
    }
}