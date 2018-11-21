package com.codekul.lifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("onCreate")
        setContentView(R.layout.activity_main)

        imgDt.setImageResource(R.drawable.ic_cup)

        btOk.setOnClickListener {
            txDt.text = System.currentTimeMillis().toString()
        }
    }

    override fun onStart() {
        super.onStart()
        toast("onStart")
    }

    override fun onResume() {
        super.onResume()
        toast("onResume")
    }

    override fun onPause() {
        toast("onPause")
        super.onPause()
    }

    override fun onStop() {
        toast("onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        toast("onSaveInstanceState")
        outState?.putString("txDt", txDt.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        toast("onRestoreInstanceState")
        txDt.text = savedInstanceState?.getString("txDt")
    }
    override fun onDestroy() {
        toast("onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        toast("onRestart")
        super.onRestart()
    }
}
