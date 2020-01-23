package com.luke.liangzhiying.newsapplication.ui.saved

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luke.liangzhiying.newsapplication.daos.ArticleDao
import com.luke.liangzhiying.newsapplication.databases.ArticleDatabase
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import com.luke.liangzhiying.newsapplication.ui.news.NewsViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class SavedViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is saved Fragment"
    }
    val text: LiveData<String> = _text

    val articles:LiveData<List<ArticleModel>>
    var dao:ArticleDao;

    init{
        dao = ArticleDatabase.getDatabase(application,viewModelScope).articleDao()
        articles =dao.getAll()
    }
    fun delete(article:ArticleModel) = viewModelScope.launch {
        dao.delete(article)
    }

}