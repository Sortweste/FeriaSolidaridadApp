package com.sort.feriaapp.viewmodels


import androidx.lifecycle.*
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.data.ArticleWithEventsAndSocialMedia
import com.sort.feriaapp.data.repositories.ArticleRepository
import kotlinx.coroutines.*


class ArticleViewModel(private val articleRepository:ArticleRepository): ViewModel() {

    private val _getAllArticles = articleRepository
        .getAllArticles
        .asLiveData()

    val getAllArticles: LiveData<List<ArticleWithEventsAndSocialMedia>>
    get() = _getAllArticles

    init{

    }

    fun insert(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        articleRepository.insertArticle(article)
    }

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}