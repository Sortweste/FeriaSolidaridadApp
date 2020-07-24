package com.sort.feriaapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sort.feriaapp.data.Article

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyArticles(vararg article: Article)

    @Insert
    suspend fun insertArticle(article: Article)

    @Update
    suspend fun updateArticle(article: Article)

}