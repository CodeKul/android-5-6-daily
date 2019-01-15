package com.example.webservices

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MyApp : Application() {

    lateinit var q : RequestQueue
    lateinit var gson : Gson

    override fun onCreate() {
        super.onCreate()

        q = Volley.newRequestQueue(this)

        gson = Gson()
    }
}