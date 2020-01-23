package com.luke.liangzhiying.newsapplication.interfaces

import android.graphics.ColorSpace
import com.luke.liangzhiying.newsapplication.BuildConfig
import com.luke.liangzhiying.newsapplication.models.NewsModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    fun getEverything(@Query("q") q: String,
                      @Query("apiKey") apiKey: String):
            Observable<NewsModel.Result>

    companion object {
        fun create(): NewsApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BuildConfig.NEWS_API_URL)
                .build()

            return retrofit.create(NewsApiService::class.java)
        }
    }


}