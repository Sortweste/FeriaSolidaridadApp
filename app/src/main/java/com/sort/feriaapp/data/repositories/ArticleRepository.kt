package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.Article
import com.sort.feriaapp.data.ArticleWithEventsAndSocialMedia
import com.sort.feriaapp.data.dao.ArticleDao
import com.sort.feriaapp.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class ArticleRepository(private val articleDao: ArticleDao): SafeApiRequest() {

    val getAllArticles: Flow<List<Article>> = articleDao.getAllArticles()

    val articlesWithEventsAndSocialMedia: Flow<List<ArticleWithEventsAndSocialMedia>> = articleDao.getAllArticlesInfo()

    suspend fun insertArticle(article: Article){
        articleDao.insert(article)
    }

    suspend fun insertArticles(articles: List<Article>){
        articleDao.insertMany(*articles.toTypedArray())
    }


    companion object {
        @Volatile private var instance: ArticleRepository? = null
        fun getInstance(articleDao: ArticleDao) =
            instance ?: synchronized(this){
                instance ?: ArticleRepository(articleDao).also { instance = it }
            }
    }

}