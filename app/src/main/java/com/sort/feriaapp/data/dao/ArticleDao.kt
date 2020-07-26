package com.sort.feriaapp.data.dao

import androidx.room.*
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.data.ArticleWithEventsAndSocialMedia
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArticleDao: BaseDao<Article> {

    @Transaction
    @Query("SELECT * FROM articles")
    abstract fun getAllArticles(): Flow<List<ArticleWithEventsAndSocialMedia>>

}