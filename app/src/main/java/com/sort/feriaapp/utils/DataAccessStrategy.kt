package com.sort.feriaapp.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*Perform POST Operation to API using coroutines*/
fun <T> performPostOperation(networkCall: suspend() -> Resource<T>)  =
    liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = networkCall.invoke()
        if(response.status == Resource.Status.SUCCESS)
            emit(Resource.success(response.data))
        else if(response.status == Resource.Status.ERROR)
            emit(Resource.error(response.message.toString(), response.message))
    }


/*Perform GET Operation to API using coroutines*/
fun <T, A> performGetOperation(databaseQuery: () -> Flow<T>, networkCall: suspend () -> Resource<A>, saveCallResult: suspend (A) -> Unit) : LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }.asLiveData()
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

/*Perform FETCH Operation to API using coroutines*/
fun <T> performFetchOperation(networkCall: suspend () -> Resource<T>, saveCallResult: suspend (T) -> Unit) : LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }