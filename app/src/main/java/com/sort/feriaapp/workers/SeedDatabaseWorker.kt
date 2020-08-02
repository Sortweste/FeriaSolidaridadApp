package com.sort.feriaapp.workers

import android.content.Context
import com.google.gson.stream.JsonReader
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.Institution
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
                    val articleType = object : TypeToken<List<Institution>>() {}.type
                    val institutionList: List<Institution> = Gson().fromJson(jsonReader, articleType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.institutionDao().insertMany(*institutionList.toTypedArray())
                    Result.success()
                }
            }
        } catch(e: Exception) {
            Result.failure()
        }
    }

}