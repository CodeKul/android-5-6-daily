package com.example.intentfilterbroadcastreceiver

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Everything from intent should find the match
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btOk.setOnClickListener {
           call()
        }
    }

    fun explicit() {
        startActivity(
                Intent(
                        this@MainActivity,
                        WalletActivity::class.java
                )
        )
    }

    fun implicit() {
        startActivity(
                Intent("android.intent.action.MUSIC_PLAYER")
        )
    }

    fun main() {

        val int = Intent(Intent.ACTION_MAIN)
        int.addCategory(Intent.CATEGORY_LAUNCHER)
        int.data= Uri.parse("file:///data/data/xyx.mp3")
        startActivity(int)
    }
    fun call() {
//        val int = Intent(Intent.ACTION_DIAL)
        //val int = Intent(Intent.ACTION_CALL)

        val int = Intent(Intent.ACTION_VIEW)
        int.data = Uri.parse("http://codekul.com")
        startActivity(int)
    }
}
