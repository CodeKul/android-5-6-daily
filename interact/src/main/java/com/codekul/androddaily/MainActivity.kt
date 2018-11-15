package com.codekul.androddaily

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgFp.setOnClickListener {

            val int = Intent(
                    this@MainActivity,
                    CommonActivity::class.java
            )
            int.putExtra("img", R.drawable.ic_fingerprint_black_24dp)
            startActivity(int)
//            finish()
        }
        imgWf.setOnClickListener {
            val int = Intent(
                    this@MainActivity,
//                            WifiActivity::class.java
                    CommonActivity::class.java
            )
            int.putExtra("img", R.drawable.ic_network_wifi_black_24dp)
            startActivity(int)
        }
    }
}
