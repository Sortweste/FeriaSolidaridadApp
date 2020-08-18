package com.sort.feriaapp.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sort.feriaapp.data.News
import com.sort.feriaapp.data.repositories.NewsRepository

class NewsDetailViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository, @Assisted savedStateHandle: SavedStateHandle): ViewModel()  {

    private val newsId: String = savedStateHandle["newsId"]!!

    private val _getNewsInfo: LiveData<News> = newsRepository.getNewsById(newsId).asLiveData()

    val newsInfo: LiveData<News>
        get() = _getNewsInfo
}