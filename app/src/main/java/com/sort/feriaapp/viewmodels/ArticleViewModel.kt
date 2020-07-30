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

    val articles: LiveData<List<ArticleWithEventsAndSocialMedia>>
    get() = _getAllArticles

    init{

    }

    fun insert(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        articleRepository.insertArticle(article)
    }


    override fun onCleared() {
        super.onCleared()
    }

}