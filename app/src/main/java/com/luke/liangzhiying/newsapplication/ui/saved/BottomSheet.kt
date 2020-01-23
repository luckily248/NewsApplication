package com.luke.liangzhiying.newsapplication.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luke.liangzhiying.newsapplication.R
import com.luke.liangzhiying.newsapplication.managers.DataManager
import com.luke.liangzhiying.newsapplication.models.ArticleModel

class BottomSheet(val articleModel: ArticleModel) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.bottom_sheet,container,false)
        val deleteButton:Button = root.findViewById(R.id.delete)
        deleteButton.setOnClickListener{v ->
            DataManager.savedViewModel?.delete(articleModel)
            this.dismiss()
        }
        return root
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }
}