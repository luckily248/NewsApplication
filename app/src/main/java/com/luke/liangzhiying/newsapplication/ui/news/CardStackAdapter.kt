package com.luke.liangzhiying.newsapplication.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.models.NewsModel

class CardStackAdapter(private var result:NewsModel.Result) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           // Log.d("vh","itembind:"+position)
            var article = this.result.articles[position];
            holder.name.text = "${article.title}. ${article.author}"
            holder.city.text = article.description
            Glide.with(holder.image)
                .load(article.urlToImage)
                .into(holder.image)
            holder.itemView.setOnClickListener { v ->
                Toast.makeText(v.context, article.title, Toast.LENGTH_SHORT).show()
            }

    }

    override fun getItemCount(): Int {
       // Log.d("count","item count:"+result.articles.size)
        return result.articles.size
    }

    fun setResult(result: NewsModel.Result) {
        this.result = result
       // Log.d("result: ", "total:"+result.totalResults+ " array: "+result.articles.size);
        this.notifyDataSetChanged()
    }

    fun getResult(): NewsModel.Result {
        return result
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_title)
        var city: TextView = view.findViewById(R.id.item_content)
        var image: ImageView = view.findViewById(R.id.item_image)
    }

}