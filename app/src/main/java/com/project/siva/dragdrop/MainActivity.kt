package com.project.siva.dragdrop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.recycler_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val myData = arrayListOf<String>()
    private val selectedData = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_layout)

        for (i in 1..20) {
            myData.add("Item Number $i")
        }

        val mLayoutManager = LinearLayoutManager(this)
        val mAdapter = MyAdapter(selectedData, myData, object : ITalkToAdapter{
            override fun onClick(index: Int) {
                if (selectedData.contains(index))
                    selectedData.remove(index)
                else
                    selectedData.add(index)
            }
        })

        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter

        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0)
            }

            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                Collections.swap(myData,p1.adapterPosition,p2.adapterPosition)
                mAdapter.notifyItemMoved(p1.adapterPosition,p2.adapterPosition)
                return true
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

            }
            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return false
            }
        })

//        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0){
//            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
//                Collections.swap(myData,p1.adapterPosition,p2.adapterPosition)
//                mAdapter.notifyItemMoved(p1.adapterPosition,p2.adapterPosition)
//                return false
//            }
//
//            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
//
//            }
//
//            override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
//                return 0
//            }
//        })
        helper.attachToRecyclerView(recyclerView)
    }
}

interface ITalkToAdapter {
    fun onClick(index : Int)
}