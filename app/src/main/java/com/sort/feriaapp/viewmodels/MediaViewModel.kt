package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.dao.ArticleDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MediaViewModel(dataSource: ArticleDAO): ViewModel(){
    val database = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)






}