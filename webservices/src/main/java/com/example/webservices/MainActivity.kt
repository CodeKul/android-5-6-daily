package com.example.webservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.webservices.domain.Json4Kotlin_Base
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private var city: String = "pune"
    private val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&apiKey=7406c21bb1cb9f59d936a59c4e890279&units=metric"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start progress bar here
        btOk.setOnClickListener { _ ->
            postReq()
        }
    }

    fun getReq() {
        city = etCt.text.toString()
        (application as MyApp).q.add(
                JsonObjectRequest(url, JSONObject(), { resObj ->
                    Log.i("@codekul", resObj.toString())

                    val myObj = (application as MyApp).gson.fromJson(resObj.toString(), Json4Kotlin_Base::class.java)

                    // you can save data here using room or traditional api

                    textView.text = "Temp of $city is ${myObj.main.temp}"

                    // stop progress bar here
                }, { errObj ->
                    Log.e("@codekul", errObj.toString())

                    // stop progress bar here
                })
        )
    }

    fun postReq() {
        val jsObj = JSONObject()
        jsObj.put("name","android")
        jsObj.put("job","developer")

        (application as MyApp).q.add(
                JsonObjectRequest(
                        "https://reqres.in/api/users",
                        jsObj,
                        { resObj ->  Log.i("@codekul", resObj.toString())}, { errObj -> }
                )
        )
    }
}
