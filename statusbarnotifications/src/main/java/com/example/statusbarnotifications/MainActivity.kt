package com.example.statusbarnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bldr = createBuilder()

        btnOkay.setOnClickListener {
            createNotificationChannel(bldr)
        }
    }

    //https://pbs.twimg.com/media/DFEMUYfUIAAGKr-.jpg

    private fun createNotificationChannel( mBuilder : NotificationCompat.Builder) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "myChannel"
            val descriptionText = getString(R.string.smDes)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("myChannelId", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
        with(notificationManager) {
            notify(1234, mBuilder.build())
        }
    }

    fun createBuilder() : NotificationCompat.Builder {

        val pending = PendingIntent.getActivity(
                this@MainActivity,
                    1334,
                    Intent(this@MainActivity, Main2Activity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Builder(this,"myChannels")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Title")
                .setContentText("Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pending)
                .addAction(R.mipmap.ic_launcher_round,"Android", pending)

    }
}
