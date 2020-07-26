package com.sort.feriaapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.data.dao.ArticleDAO
import kotlinx.coroutines.*

class ArticleViewModel( dataSource: ArticleDAO,
                        application: Application): ViewModel() {
    val database = dataSource
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val articles = database.getAllArticles()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}