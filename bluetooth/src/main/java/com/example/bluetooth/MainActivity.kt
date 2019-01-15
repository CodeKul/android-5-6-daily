package com.example.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val REQUEST_ENABLE_BT = 1234
        val NAME = "codekulchat"
        val UUID : UUID = java.util.UUID.fromString("f854f2ee-9299-451f-ab52-65d528784b73")
    }

    private val mReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action: String = intent.action
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice =
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC address

                    Log.i("@codekul", "Remote name - $deviceName Add - $deviceHardwareAddress ")
                }
            }
        }
    }


    lateinit var adapter : BluetoothAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(mReceiver, filter)

        adapter = BluetoothAdapter.getDefaultAdapter()
        if (adapter == null) {
            Log.i("@codekul", "Bluetooth Not Found")
            return
        }

        btEn.setOnClickListener {
            if (!adapter.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
        }

        btFind.setOnClickListener {
            val pairedDevices: Set<BluetoothDevice> = adapter.bondedDevices
            pairedDevices.forEach { device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address
                Log.i("@codekul", "name - $deviceName Add - $deviceHardwareAddress ")
            }

            adapter.startDiscovery()
        }

        btSer.setOnClickListener {
            Server().start()
        }
        btCl.setOnClickListener {
            ConnectThread(adapter.getRemoteDevice("7C:6B:9C:BD:16:E6")).start()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    private inner class Server : Thread() {

        private val mmServerSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
            adapter.listenUsingInsecureRfcommWithServiceRecord(NAME, UUID)
        }

        override fun run() {
            // Keep listening until exception occurs or a socket is returned.
            var shouldLoop = true
            while (shouldLoop) {
                val socket: BluetoothSocket? = try {
                    mmServerSocket?.accept()
                } catch (e: IOException) {
                    Log.e("@codekul", "Socket's accept() method failed", e)
                    shouldLoop = false
                    null
                }
                socket?.also {
                    val dataFromClient = it.inputStream.bufferedReader().use {
                        it.readLine()
                    }

                    Log.i("@codekul", "Data From Client $dataFromClient")
                    //btSer.text = dataFromClient
                    mmServerSocket?.close()
                    shouldLoop = false
                }
            }
        }

        // Closes the connect socket and causes the thread to finish.
        fun cancel() {
            try {
                mmServerSocket?.close()
            } catch (e: IOException) {
                Log.e("@codekul", "Could not close the connect socket", e)
            }
        }
    }

    private inner class ConnectThread(device: BluetoothDevice) : Thread() {

        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createRfcommSocketToServiceRecord(UUID)
        }

        public override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            adapter.cancelDiscovery()

            mmSocket?.use { socket ->
                socket.connect()
                socket.outputStream.bufferedWriter().use {
                    it.write("Hey this message is from client")
                }
            }
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
                Log.e("@codekul", "Could not close the client socket", e)
            }
        }
    }
}
