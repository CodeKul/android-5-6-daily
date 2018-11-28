package com.example.recyclerview


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rtVw = inflater.inflate(R.layout.fragment_recycler, container, false)
        recyclerView = rtVw.findViewById(R.id.recycler)

        viewManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = viewManager

        val dataSet = ArrayList<MyData>()
        dataSet.add(MyData(R.mipmap.ic_launcher_round, "Android"))
        dataSet.add(MyData(R.mipmap.ic_launcher_round, "Apple"))
        dataSet.add(MyData(R.mipmap.ic_launcher_round, "Symbian"))

        viewAdapter = MyAdapter(activity as Context, dataSet)
        recyclerView.adapter = viewAdapter


        return rtVw
    }
}
