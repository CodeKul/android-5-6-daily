package com.example.sqlite

import android.app.Application
import androidx.room.Room

class MyApp : Application() {

    val db by lazy {
        Room.databaseBuilder(
                this@MyApp,
                MyDb::class.java, "iot-data"
        ).build()
    }


    override fun onCreate() {
        super.onCreate()

        db
    }
}