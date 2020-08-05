package com.sort.feriaapp.workers

import android.content.Context
import com.google.gson.stream.JsonReader
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.utils.EVENT_DUMMY_CONTENT_FILENAME
import com.sort.feriaapp.utils.INSTITUTION_DUMMY_CONTENT_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = AppDatabase.getInstance(applicationContext)
            applicationContext.assets.open(INSTITUTION_DUMMY_CONTENT_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val institutionType = object : TypeToken<List<Institution>>() {}.type
                    val institutionList: List<Institution> =
                        Gson().fromJson(jsonReader, institutionType)
                    database.institutionDao().insertMany(*institutionList.toTypedArray())
                }
            }
            applicationContext.assets.open(EVENT_DUMMY_CONTENT_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val eventType = object : TypeToken<List<Event>>() {}.type
                    val eventList: List<Event> =
                        Gson().fromJson(jsonReader, eventType)
                    database.eventDao().insertMany(*eventList.toTypedArray())
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}