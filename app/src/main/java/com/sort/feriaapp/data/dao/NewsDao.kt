package com.sort.feriaapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sort.feriaapp.data.News
import com.sort.feriaapp.data.minimals.NewsMinimal
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NewsDao : BaseDao<News> {
    @Query("SELECT * FROM news")
    abstract fun getAllNews(): Flow<List<News>>

    @Query("SELECT id,title , image_url as imageURL FROM news")
    abstract fun getAllNewsMinimal(): Flow<List<NewsMinimal>>

    @Query("SELECT * FROM news WHERE id=:id")
    abstract fun getNewsById(id: String): Flow<News>
}