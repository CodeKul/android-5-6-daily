package com.example.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val int = Intent(this, MyService::class.java)
        int.putExtra("key_mp3", R.raw.a)

        btSt.setOnClickListener {
            startService(int)
        }
        btStp.setOnClickListener {
            stopService(int)
        }
    }
}
