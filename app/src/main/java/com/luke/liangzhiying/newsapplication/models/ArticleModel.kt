package com.luke.liangzhiying.newsapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleModel(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "source") val source: NewsModel.Source?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,
    @ColumnInfo(name = "content") val content: String?
)