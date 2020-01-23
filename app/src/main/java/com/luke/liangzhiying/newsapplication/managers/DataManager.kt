package com.luke.liangzhiying.newsapplication.managers

import androidx.lifecycle.ViewModel
import com.luke.liangzhiying.newsapplication.ui.news.NewsViewModel
import com.luke.liangzhiying.newsapplication.ui.profile.ProfileViewModel
import com.luke.liangzhiying.newsapplication.ui.saved.SavedViewModel

object DataManager{
    var newsViewModel:NewsViewModel? = null;
    var profileViewModel:ProfileViewModel? = null;
    var savedViewModel:SavedViewModel? = null;

}