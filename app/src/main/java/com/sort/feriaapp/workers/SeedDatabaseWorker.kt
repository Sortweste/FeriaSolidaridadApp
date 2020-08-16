package com.sort.feriaapp.workers

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import com.google.gson.stream.JsonReader
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.dao.InstitutionDao
import com.sort.feriaapp.utils.EVENT_DUMMY_CONTENT_FILENAME
import com.sort.feriaapp.utils.INSTITUTION_DUMMY_CONTENT_FILENAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class SeedDatabaseWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val institutionDao: InstitutionDao,
    private val eventDao: EventDao
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO){
            try {
                applicationContext.assets.open(INSTITUTION_DUMMY_CONTENT_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val institutionType = object : TypeToken<List<Institution>>() {}.type
                        val institutionList: List<Institution> =
                            Gson().fromJson(jsonReader, institutionType)
                        institutionDao.insertMany(*institutionList.toTypedArray())
                    }
                }
                applicationContext.assets.open(EVENT_DUMMY_CONTENT_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val eventType = object : TypeToken<List<Event>>() {}.type
                        val eventList: List<Event> =
                            Gson().fromJson(jsonReader, eventType)
                        eventDao.insertMany(*eventList.toTypedArray())
                    }
                }
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}