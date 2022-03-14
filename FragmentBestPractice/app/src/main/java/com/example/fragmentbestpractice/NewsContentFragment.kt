package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class NewsContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_frag, container, false)
    }

    fun refresh(title: String, content: String) {
        val contentLayout: LinearLayout = view!!.findViewById(R.id.contentLayout)
        contentLayout.visibility = View.VISIBLE
        val newsTitle: TextView = view!!.findViewById(R.id.newsTitle)
        val newsContent: TextView = view!!.findViewById(R.id.newsContent)
        newsTitle.text = title
        newsContent.text = content
    }

}