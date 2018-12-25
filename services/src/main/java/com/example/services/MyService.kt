package com.example.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {

    private lateinit var mp : MediaPlayer

    override fun onBind(intent: Intent): IBinder? = null // i am not using

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            if(intent === null)  mp = MediaPlayer.create(this, R.raw.b )
            else mp = MediaPlayer.create(this, intent.getIntExtra("key_mp3", R.raw.b) )
            mp.start()

        }.start()

        return START_STICKY_COMPATIBILITY
    }

    override fun onDestroy() {
        mp.stop()
        mp.release()
        super.onDestroy()
    }
}
