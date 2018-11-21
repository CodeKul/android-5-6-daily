package com.example.fragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFrag()
    }

    fun loadFrag()  {

        val txn = supportFragmentManager.beginTransaction()
        txn.replace(R.id.frameLayout, ContentFragment())
        txn.commit()
    }
}
