package com.codekul.actund

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.login_layout.*

class MainActivity : AppCompatActivity() {

     private val TAG = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_constraint)

        //kotlinsWay()
    }

    fun normalWay() {
        val etUsNm = findViewById<EditText>(R.id.etUsNm)

        val etPass = findViewById<EditText>(R.id.etPass)

        val btnLgn = findViewById<Button>(R.id.btLgn)
        btnLgn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.i("@codekul", "Info - This CodeKul")
                Log.e("@codekul", "Error - This CodeKul Again")
                Log.d("@codekul", "Debug - This CodeKul Again")
                Log.v("@codekul", "Verbose - This CodeKul Again")
                Log.i("@codekul", "User - "+ etUsNm.getText())
            }
        })
    }

    fun kotlinsWay() {

        btLgn.setOnClickListener {
            Log.i(
                    TAG,
                    "User Name is ${etUsNm.text} and password is ${etPass.text}"
            )
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        when(newConfig?.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> Log.i("@codekul", "Landscape orientation")
            Configuration.ORIENTATION_PORTRAIT -> Log.i("@codekul", "Portrait orientation")
            else -> Log.i("@codekul", "Default Orientation")
        }
    }
}

