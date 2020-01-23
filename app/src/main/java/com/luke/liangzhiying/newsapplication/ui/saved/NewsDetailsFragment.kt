package com.luke.liangzhiying.newsapplication.ui.saved

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.models.ArticleModel

class NewsDetailsFragment(val articleModel: ArticleModel):Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news_details, container, false)
        val title:TextView = root.findViewById(R.id.title)
        title.text = articleModel.title
        val postTime:TextView = root.findViewById(R.id.post_time)
        postTime.text = articleModel.publishedAt
        val pic:ImageView = root.findViewById(R.id.pic)
        Glide.with(pic)
            .load(articleModel.urlToImage)
            .into(pic)
        val content:TextView = root.findViewById(R.id.content)
        content.text = articleModel.description
        val readMore:Button = root.findViewById(R.id.read_more)
        readMore.setOnClickListener { v ->
            openWebPage(articleModel.url)
        }
        return root
    }
    fun openWebPage(url:String?){
        val uris = Uri.parse(url)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context?.startActivity(intents)
    }

}