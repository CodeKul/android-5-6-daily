package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var app: MyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app = application as MyApp

        btAd.setOnClickListener {
            Thread {
                app.db.iotDao().save(
                        IotData(
                                source = etSrc.text.toString()
                        )
                )
            }.start()
        }

        btRfsh.setOnClickListener {
            Thread {
                app.db.iotDao().allData().forEach {
                    Log.i("@codekul", "$it")
                }
            }.start()
        }
    }
}
