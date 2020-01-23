package com.luke.liangzhiying.newsapplication.ui.saved

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.managers.DataManager
import com.luke.liangzhiying.newsapplication.models.ArticleModel
import com.luke.liangzhiying.newsapplication.models.NewsModel
import com.luke.liangzhiying.newsapplication.ui.news.NewsViewModel

class SavedFragment : Fragment() {

    //private lateinit var savedViewModel: SavedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SavedListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DataManager.savedViewModel =
            ViewModelProviders.of(this).get(SavedViewModel::class.java)
        Log.d("test","savemvvm setup")
        root = inflater.inflate(R.layout.fragment_saved, container, false)
//    val textView: TextView = root.findViewById(R.id.text_saved)
////        DataManager.savedViewModel?.text?.observe(this, Observer {
////            textView.text = it
////        })
        setUpSavedList();

        DataManager.savedViewModel?.articles?.observe(this, Observer { articles->
            viewAdapter.setArticles(articles)
            //Toast.makeText(root.context,"update:"+articles.size,Toast.LENGTH_SHORT).show()
        })


        return root
    }

    fun openArticleDetails(articleModel: ArticleModel){
        //Toast.makeText(context,"click the "+articleModel.title,Toast.LENGTH_SHORT).show()
        val transition = fragmentManager?.beginTransaction()
        transition?.add(R.id.nav_host_fragment,NewsDetailsFragment(articleModel),"NewsDetail")
        transition?.addToBackStack("savedToNewsDetail")
        transition?.commit()
    }
    fun openBottonSheet(articleModel: ArticleModel){
        val bottomSheet = BottomSheet(articleModel)
        bottomSheet.show(this.fragmentManager,BottomSheet.TAG)
    }
    fun setUpSavedList(){
        viewManager = LinearLayoutManager(context)
        viewAdapter = SavedListAdapter(emptyList<ArticleModel>(),{articleModel -> this.openArticleDetails(articleModel) },{articleModel ->
            openBottonSheet(articleModel)
            true
        });
        recyclerView = root.findViewById<RecyclerView>(R.id.saved_list).apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}