package com.sort.feriaapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.repositories.ArticleRepository
import com.sort.feriaapp.viewmodels.ArticleViewModelFactory

object InjectorUtils {

    private fun getArticleRepository(context: Context):ArticleRepository{
        return ArticleRepository.getInstance(AppDatabase.getInstance(context).articleDao())
    }

    fun provideArticleViewModelFactory(fragment: Fragment): ArticleViewModelFactory{
        return ArticleViewModelFactory(getArticleRepository(fragment.requireContext()))
    }

}