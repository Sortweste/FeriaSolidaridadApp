package com.sort.feriaapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T> performPostOperation(networkCall: suspend() -> Resource<T>)  =
    liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = networkCall.invoke()
        if(response.status == Resource.Status.SUCCESS)
            emit(Resource.success(response.data))
        else if(response.status == Resource.Status.ERROR)
            emit(Resource.error("Error", response.message))
    }


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