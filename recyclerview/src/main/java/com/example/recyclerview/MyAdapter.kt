package com.example.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MyAdapter (
        private val context : Context,
        private val dataSet : List<MyData>,
        private  val fn : (pos : Int) -> Unit
): androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {

        return MyViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(
                                R.layout.recycler_item,
                                parent,
                                false
                        )
        )
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val itm = dataSet[pos]
        holder.view.setOnClickListener { fn(pos) }
        holder.img().setImageResource(itm.imgId)
        holder.txt().text = itm.txt
    }

    class MyViewHolder(
            val view : View
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        fun txt() = view.findViewById<TextView>(R.id.txt)

        fun img() = view.findViewById<ImageView>(R.id.img)
    }
}