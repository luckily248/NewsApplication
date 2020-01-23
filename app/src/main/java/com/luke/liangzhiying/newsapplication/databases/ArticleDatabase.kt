package com.luke.liangzhiying.newsapplication.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luke.liangzhiying.newsapplication.daos.ArticleDao
import com.luke.liangzhiying.newsapplication.daos.Converters
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(ArticleModel::class), version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ArticleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "articles"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}