package com.luke.liangzhiying.newsapplication.models

object NewsModel {
    data class Result(val status: String,val totalResults: Int,val articles: Array<Article>)
    data class Article(val source:Source,val author:String,val title:String,val description:String,val url:String,val urlToImage:String, val publishedAt:String,val content:String)
    data class Source(val id:String,val name:String)

    fun empty():NewsModel.Result{
        val article = Article(Source("",""),"","","","","","","")
        return Result("",0, arrayOf(article));
    }
}