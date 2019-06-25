package com.project.siva.dragdrop

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter(private val selectedData : ArrayList<Int>, private val myData: ArrayList<String>,private val iTalkToAdapter: ITalkToAdapter) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_items, p0, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        p0.textView.text = myData[p1]
        p0.index = p1
        if (selectedData.contains(p1)) {
            p0.textView.setBackgroundColor(Color.parseColor("#00574B"))
        }
    }

    inner class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        val textView = itemView.findViewById<TextView>(R.id.name)!!
        var index = 0

        override fun onClick(v: View?) {
            iTalkToAdapter.onClick(index)
            notifyItemChanged(index)
        }
    }
}