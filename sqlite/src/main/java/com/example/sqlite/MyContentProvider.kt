package com.example.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.room.Room

class MyContentProvider : ContentProvider() {

    private lateinit var  db : MyDb

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException()
    }

    override fun insert(uri: Uri, values: ContentValues): Uri? {
        throw UnsupportedOperationException()
    }

    override fun onCreate(): Boolean {
        db =  Room.databaseBuilder(
                context!!,
                MyDb::class.java, "iot-data"
        ).build()
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {

        Log.i("@codekul",  "URI is ${uri.authority}")
        val csr = db.iotDao().contentData()
        Log.i("@codekul",  "Cursor Value $csr")
        csr.setNotificationUri(context!!.contentResolver, uri)
       return  csr

    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }
}
