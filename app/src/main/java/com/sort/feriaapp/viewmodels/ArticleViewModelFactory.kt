package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sort.feriaapp.data.repositories.ArticleRepository

class ArticleViewModelFactory(private val articleRepository: ArticleRepository): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArticleViewModel::class.java))
            return ArticleViewModel(articleRepository) as T
        throw IllegalArgumentException("Unable to construct ViewModel")
    }

}