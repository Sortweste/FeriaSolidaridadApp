package com.sort.feriaapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sort.feriaapp.data.minimals.NewsMinimal
import com.sort.feriaapp.data.repositories.NewsRepository

/*ViewModel for NewsFragment*/
class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository): ViewModel()  {
    private val _getAllNews = newsRepository.getAllNews.asLiveData()

    val news: LiveData<List<NewsMinimal>>
        get() = _getAllNews
}