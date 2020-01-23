package com.luke.liangzhiying.newsapplication.daos

import androidx.room.TypeConverter
import com.luke.liangzhiying.newsapplication.models.NewsModel

class Converters {
    @TypeConverter
    fun fromStringsToSource(strings:String): NewsModel.Source? {
        val list =  strings.split(",")
        val source = NewsModel.Source(list[0],list[1])
        return source
    }

    @TypeConverter
    fun fromSourceToStrings(source: NewsModel.Source): String? {

        return source.id+","+source.name
    }
}