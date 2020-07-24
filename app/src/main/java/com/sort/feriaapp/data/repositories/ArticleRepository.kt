package com.sort.feriaapp.data.repositories

import androidx.lifecycle.LiveData
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.data.dao.ArticleDAO
import com.sort.feriaapp.utils.SafeApiRequest

class ArticleRepository(private val articleDAO: ArticleDAO): SafeApiRequest() {

    val getAllArticles: LiveData<List<Article>> = articleDAO.getAllArticles()

    suspend fun insertArticle(article: Article){
        articleDAO.insertArticle(article)
    }

}