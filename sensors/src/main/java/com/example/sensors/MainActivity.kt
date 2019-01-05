package com.example.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mSensorManager: SensorManager
    private lateinit var mLight: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val deviceSensors: List<Sensor> = mSensorManager.getSensorList(Sensor.TYPE_ALL)

        deviceSensors.forEach {
            Log.i("@codekul", "Sensor Name - ${it.name}")
        }


        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        mSensorManager.registerListener( object : SensorEventListener{
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

            override fun onSensorChanged(event: SensorEvent?) {
                txtDt.text = event!!.values[0].toString()
            }

        }, mLight, SensorManager.SENSOR_DELAY_NORMAL)
    }
}
