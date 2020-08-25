package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.dao.NewsDao
import com.sort.feriaapp.data.minimals.NewsMinimal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsDao: NewsDao) {

    val getAllNews: Flow<List<NewsMinimal>> = newsDao.getAllNewsMinimal()

    fun getNewsById(id: String) = newsDao.getNewsById(id)

}