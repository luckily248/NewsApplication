package com.luke.liangzhiying.newsapplication.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.luke.liangzhiying.newsapplication.BuildConfig
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.interfaces.NewsApiService
import com.luke.liangzhiying.newsapplication.managers.DataManager
import com.luke.liangzhiying.newsapplication.models.NewsModel
import com.yuyakaido.android.cardstackview.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsFragment : Fragment(), CardStackListener {

   // private lateinit var newsViewModel: NewsViewModel
    private val manager by lazy { CardStackLayoutManager(context, this) }
    private lateinit var root:View;
    private lateinit var cardStackView: CardStackView
    private val adapter by lazy { CardStackAdapter(NewsModel.empty()) }
    val newsApiService by lazy {
        NewsApiService.create()
    }
    var disposable: Disposable? = null

    private fun setupCardStackView() {
        initialize()
    }
    private fun setupButton() {
        val skip = root.findViewById<View>(R.id.skip_button)
        skip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }

        val rewind = root.findViewById<View>(R.id.rewind_button)
        rewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            cardStackView.rewind()
        }

        val like = root.findViewById<View>(R.id.like_button)
        like.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
    }
    private fun initialize() {
        manager.setStackFrom(StackFrom.Bottom)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DataManager.newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_news, container, false)

        cardStackView = root.findViewById<CardStackView>(R.id.card_stack_view)
        setupCardStackView()
        setupButton()

        disposable =
            newsApiService.getEverything("bitcoin", BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> adapter.setResult(result)},
                    { error -> Toast.makeText(context,error.localizedMessage,Toast.LENGTH_SHORT) }
                )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose();

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        //Toast.makeText(context,"Disappeared:"+position,Toast.LENGTH_SHORT).show();
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        //Toast.makeText(context,"direction:"+direction?.name,Toast.LENGTH_SHORT).show();
    }

    override fun onCardSwiped(direction: Direction?) {
        when (direction?.name){
            "Right"
            ->  {
                //Toast.makeText(context,"swiped:"+direction?.name+" and:"+ adapter.getResult().articles[manager.topPosition-1].title,Toast.LENGTH_SHORT).show()
                DataManager.newsViewModel?.addSavedArticle(adapter.getResult().articles[manager.topPosition-1])
            }
        }

    }

    override fun onCardCanceled() {
        //Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show();
    }

    override fun onCardAppeared(view: View?, position: Int) {
       // Toast.makeText(context,"position:"+position,Toast.LENGTH_SHORT).show();
    }

    override fun onCardRewound() {
        //Toast.makeText(context,"Rewound",Toast.LENGTH_SHORT).show();
    }
}