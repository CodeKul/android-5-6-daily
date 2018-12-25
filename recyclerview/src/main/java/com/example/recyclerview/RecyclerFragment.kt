package com.example.recyclerview


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rtVw = inflater.inflate(R.layout.fragment_recycler, container, false)
        recyclerView = rtVw.findViewById(R.id.recycler)

        viewManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recyclerView.layoutManager = viewManager

        val dataSet = ArrayList<MyData>()
        dataSet.add(MyData(R.mipmap.ic_launcher_round, "Android"))
        dataSet.add(MyData(R.mipmap.ic_launcher_round, "Apple"))
        dataSet.add(MyData(R.mipmap.ic_launcher_round, "Symbian"))

        viewAdapter = MyAdapter(activity as Context, dataSet) {
            Log.i("@codekul", "Clicked $it")
        }
        recyclerView.adapter = viewAdapter


        return rtVw
    }
}
