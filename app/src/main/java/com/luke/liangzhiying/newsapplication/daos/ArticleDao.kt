package com.luke.liangzhiying.newsapplication.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.luke.liangzhiying.newsapplication.models.ArticleModel

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAll(): LiveData<List<ArticleModel>>

    //@Query("SELECT * FROM user WHERE uid IN (:userIds)")
   // fun loadAllByIds(userIds: IntArray): List<NewsModel.Article>

    //@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
    //        "last_name LIKE :last LIMIT 1")
    //fun findByName(first: String, last: String): NewsModel.Article

    @Insert
    suspend fun insertAll(vararg article: ArticleModel)

    @Delete
    suspend fun delete(article: ArticleModel)

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}