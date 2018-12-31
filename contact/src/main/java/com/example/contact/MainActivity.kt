package com.example.contact

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readIot()
    }

    fun readContacts() {

       val cursor =  contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER

                ),
                null,
                null,
                null
        )

        while(cursor?.moveToNext()!!) {

            val nm = cursor.getString(cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val num = cursor.getString(cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER))

            Log.i("@codekul", "Name is $nm and Number is $num")
        }

        cursor.close()
    }

    fun readIot() {
        val cursor =  contentResolver.query(
                Uri.parse("content://com.codekul.provider"),
                null,
                null,
                null,
                null
        )
        Log.i("@codekul", "Cursor in Contacts $cursor")

        while(cursor?.moveToNext()!!) {

            val id = cursor.getInt(cursor.getColumnIndex( "id"))
            val source = cursor.getString(cursor.getColumnIndex( "source"))
            val tmst = cursor.getLong(cursor.getColumnIndex( "tmst"))

            Log.i("@codekul", "Id is $id and Source is $source Time is $tmst")
        }

        cursor.close()
    }
}
