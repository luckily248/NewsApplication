package com.luke.liangzhiying.newsapplication

import com.luke.liangzhiying.newsapplication.interfaces.NewsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NewsAPITest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
     fun testNewsApi() {
        val  count = CountDownLatch(1);
        val newsApiService by lazy {
            NewsApiService.create()
        }
        var disposable: Disposable? = null
        disposable =
            newsApiService.getEverything("bitcoin", BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> assertNotNull(result); count.countDown()},
                    { error -> assertNotNull(error) }
                )
        count.await(1000,TimeUnit.SECONDS);
    }
}
