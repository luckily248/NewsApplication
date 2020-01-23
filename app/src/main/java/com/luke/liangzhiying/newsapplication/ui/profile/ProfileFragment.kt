package com.luke.liangzhiying.newsapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.managers.DataManager
import com.yuyakaido.android.cardstackview.*

class ProfileFragment : Fragment() {

    //private lateinit var profileViewModel: ProfileViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DataManager.profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val clearCacheButton:Button = root.findViewById(R.id.clear_cache)
        clearCacheButton.setOnClickListener { v->
            DataManager.profileViewModel?.deleteAll()
        }
        //val textView: TextView = root.findViewById(R.id.text_profile)
       // DataManager.profileViewModel?.text?.observe(this, Observer {
         //   textView.text = it
       // })

        return root
    }

}