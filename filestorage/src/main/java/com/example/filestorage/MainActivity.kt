package com.example.filestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeToExternal()
        readFromExternal()
    }

    fun writeToInternal() {
        Log.i("@codekul", " Internal Storage Path - ${filesDir.absolutePath}")

        val fos = openFileOutput("myFile", Context.MODE_PRIVATE)
        fos.write("Hi codekul".toByteArray())
        fos.close()
    }

    fun readFromInternal() {
        val fis = openFileInput("myFile")
        val data = fis.bufferedReader().use {
            it.readLine()
        }

        Log.i("@codekul", "Internal Storage data is $data")
    }

    fun writeToExternal() {


        fun writeToPrivate() {
            Log.i("@codekul", "Private External Storage${getExternalFilesDir("").absolutePath}")
            val fos = FileOutputStream(
                    File(
                            getExternalFilesDir(""),
                            "myFile"
                    )
            )
            fos.use {
                it.write("Hi this is external private storage data".toByteArray())
            }

        }

        fun writeToPublic() {

            val fl = File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS
                    ),
                    "myFile"
            )
            Log.i("@codekul", "External Storage Public Path ${fl.absolutePath}")
            val fos = FileOutputStream( fl )
            fos.use {
                it.write("THis is external storage public ".toByteArray())
            }
        }

        writeToPrivate()

        writeToPublic()
    }

    fun readFromExternal() {
        fun readFromPrivate() {
            val fis = FileInputStream(
                    File(
                            getExternalFilesDir(""),
                            "myFile"
                    )
            )
            val data = fis.bufferedReader().use {
                it.readLine()
            }
            Log.i("@codekul", data)
        }

        fun readFromPublic() {
            val fis = FileInputStream(
                    File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                            "myFile"
                    )
            )
            val data = fis.bufferedReader().use {
                it.readLine()
            }
            Log.i("@codekul", data)
        }

        readFromPrivate()

        readFromPublic()
    }

    fun writeToSp() {
        getPreferences(Context.MODE_PRIVATE)
        val sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putInt("keyInt", 10)
        editor.putBoolean("keyBool", true)
        editor.apply()
    }

    fun readFromSp() {
        val sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        Log.i(
                "@codekul",
                """
                    Integer - ${sp.getInt("keyInt", -9)}
                    Boolean - ${sp.getBoolean("keyBool", false)}
                """.trimIndent()
        )
    }
}
