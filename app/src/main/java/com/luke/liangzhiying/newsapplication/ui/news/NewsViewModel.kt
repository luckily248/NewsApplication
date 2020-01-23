package com.luke.liangzhiying.newsapplication.ui.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.luke.liangzhiying.newsapplication.daos.ArticleDao
import com.luke.liangzhiying.newsapplication.databases.ArticleDatabase
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class NewsViewModel (application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is news Fragment"
    }
    val text: LiveData<String> = _text
    //val articles:LiveData<List<ArticleModel>>
    var dao: ArticleDao;

    init{
        dao = ArticleDatabase.getDatabase(application,viewModelScope).articleDao()
        //articles =dao.getAll()
    }
    fun addSavedArticle(newsArticle: NewsModel.Article) = viewModelScope.launch {
        //Log.d("test","added")
        dao.insertAll(ArticleModel(Random(System.currentTimeMillis()).nextInt(),newsArticle.source,newsArticle.author,newsArticle.title,newsArticle.description,newsArticle.url,newsArticle.urlToImage,newsArticle.publishedAt,newsArticle.content))
    }
}