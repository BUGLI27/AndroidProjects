package com.example.uibestpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    //定义两个ViewHolder，分别用于缓存left和right布局中的控件
//    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
//    }
//
//    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
//    }

    // 返回当前position对应的消息类型
    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    // 创建ViewHolder实例——加载布局，创建一个ViewHolder实例，并把加载出的布局传入构造函数中，最后返回实例
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == Msg.TYPE_RECEIVED) {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
        LeftViewHolder(view)
    } else {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
        RightViewHolder(view)
    }

    // 对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
//            else -> throw IllegalAccessException()
        }
    }

    // 告诉RecyclerView一共有有多少子项
    override fun getItemCount() = msgList.size
}