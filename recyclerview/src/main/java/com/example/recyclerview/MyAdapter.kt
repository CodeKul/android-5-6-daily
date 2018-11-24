package com.example.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class MyAdapter (
        private val dataSet : List<MyData>
): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
    }

    override fun getItemCount(): Int {
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
    }

    class MyViewHolder(
            val view : View
    ) : RecyclerView.ViewHolder(view)
}