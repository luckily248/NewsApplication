package com.luke.liangzhiying.newsapplication

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.jraska.livedata.test
import com.luke.liangzhiying.newsapplication.daos.ArticleDao
import com.luke.liangzhiying.newsapplication.databases.ArticleDatabase
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleDAOTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var articleDao: ArticleDao
    private lateinit var db: ArticleDatabase

    @Before
    fun  createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ArticleDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        articleDao = db.articleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetArticle()= runBlocking {
        val article = ArticleModel(1,NewsModel.Source("me","me"),"me","something","","","0","","")
        articleDao.insertAll(article)
        val it  = articleDao.getAll().waitForValue()
        assert(it?.get(0)?.title == article.title)
    }

    @Test
    @Throws(Exception::class)
    fun getAllArticles()=runBlocking{
        val article1 = ArticleModel(1,NewsModel.Source("me","me"),"luke","hello world1","something","","","0","")
        val article2 =ArticleModel(2,NewsModel.Source("me","me"),"luke","hello world2","something","","","0","")
        articleDao.insertAll(article1,article2)
        val it = articleDao.getAll().waitForValue()
        assert(it?.get(0)?.title == article1.title)
        assert(it?.get(1)?.title == article2.title)
    }
    @Test
    @Throws(Exception::class)
    fun deleteArticle()=runBlocking{
        val article1 = ArticleModel(1,NewsModel.Source("me","me"),"luke","hello world1","something","","","0","")
        val article2 =ArticleModel(2,NewsModel.Source("me","me"),"luke","hello world2","something","","","0","")
        articleDao.insertAll(article1,article2)
        articleDao.delete(article1)
        val it = articleDao.getAll().waitForValue()
        assert(it?.get(0)?.title == article2.title)
        assert(it?.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking{
        val article1 = ArticleModel(1,NewsModel.Source("me","me"),"luke","hello world1","something","","","0","")
        val article2 = ArticleModel(2,NewsModel.Source("me","me"),"luke","hello world2","something","","","0","")
        articleDao.insertAll(article1,article2)
        articleDao.deleteAll()
        val it = articleDao.getAll().waitForValue()
        assert(it?.isEmpty())
    }
}
