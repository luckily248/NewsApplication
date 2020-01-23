package com.luke.liangzhiying.newsapplication.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import kotlinx.android.synthetic.main.item_saved_news.view.*

class SavedListAdapter(private var myDataset: List<ArticleModel>,val listener :( ArticleModel) -> Unit,val longListener: (ArticleModel) -> Boolean) :
    RecyclerView.Adapter<SavedListAdapter.MyViewHolder>() {

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView){
        fun bind(article: ArticleModel,listener :( ArticleModel) -> Unit,longListener:(ArticleModel) -> Boolean) = with(cardView){
            author.text = article.author
            des.text = article.description
            Glide.with(pic)
                .load(article.urlToImage)
                .into(pic)
            setOnClickListener{listener(article)}
            setOnLongClickListener{longListener(article)}
        }
    }
    internal fun setArticles(articles: List<ArticleModel>) {
        this.myDataset = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SavedListAdapter.MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_news, parent, false) as CardView

        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myDataset.get(position),listener,longListener)
    }

    override fun getItemCount() = myDataset.size
}