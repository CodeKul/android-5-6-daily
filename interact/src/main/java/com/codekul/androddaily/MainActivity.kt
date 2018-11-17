package com.codekul.androddaily

import android.app.Activity
import android.content.Intent
import android.graphics.Color
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
            //startActivity(int)
            startActivityForResult(int, 1234)
        }
        imgWf.setOnClickListener {
            val int = Intent(
                    this@MainActivity,
                    CommonActivity::class.java
            )
            int.putExtra("img", R.drawable.ic_network_wifi_black_24dp)
//            startActivity(int)

            startActivityForResult(int, 1235)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1234) {
            //coming from FingerPrint
            if(resultCode == Activity.RESULT_OK) {
                imgFp.setColorFilter(
                        Color.rgb(
                                data!!.getIntExtra("r", 150),
                                data.getIntExtra("g", 150),
                                data.getIntExtra("b", 150)
                        )
                )
            }
        }
        if(requestCode == 1235) {
            //coming from WifiActivity
            if(resultCode == Activity.RESULT_OK) {
                imgWf.setColorFilter(
                        Color.rgb(
                                data!!.getIntExtra("r", 150),
                                data.getIntExtra("g", 150),
                                data.getIntExtra("b", 150)
                        )
                )
            }
        }
    }
}


