package com.sort.feriaapp.workers

import android.content.Context
import android.util.JsonReader
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.utils.DUMMY_CONTENT_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(DUMMY_CONTENT_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val articleType = object : TypeToken<List<Article>>() {}.type
                    val articleList: List<Article> = Gson().fromJson(jsonReader, articleType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.articleDao().insertMany(*articleList.toTypedArray())
                    Result.success()
                }
            }
        } catch(e: Exception) {
            Result.failure()
        }
    }

}