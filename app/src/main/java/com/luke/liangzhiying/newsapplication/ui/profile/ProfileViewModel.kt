package com.luke.liangzhiying.newsapplication.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.luke.liangzhiying.newsapplication.daos.ArticleDao
import com.luke.liangzhiying.newsapplication.databases.ArticleDatabase
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

  // val articles:LiveData<List<ArticleModel>>
    var dao: ArticleDao;

    init{
        dao = ArticleDatabase.getDatabase(application,viewModelScope).articleDao()
        //articles =dao.getAll()
    }
    fun deleteAll() = viewModelScope.launch {
        dao.deleteAll()
    }
}