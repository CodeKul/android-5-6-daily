package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFrag(ContentFragment.getInstance(
                R.drawable.ic_launcher_background
        ))
    }

    fun loadFrag(frag : androidx.fragment.app.Fragment)  {
        val txn = supportFragmentManager.beginTransaction()
        txn.replace(R.id.frameLayout, frag)
        txn.commit()
    }
}
