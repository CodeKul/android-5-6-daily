package com.example.fragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFrag(ContentFragment.getInstance(
                R.drawable.ic_launcher_background
        ))
    }

    fun loadFrag(frag : Fragment)  {
        val txn = supportFragmentManager.beginTransaction()
        txn.replace(R.id.frameLayout, frag)
        txn.commit()
    }
}
