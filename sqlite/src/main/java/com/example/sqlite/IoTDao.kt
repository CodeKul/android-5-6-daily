package com.example.sqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IoTDao {

    @Insert
    fun save( data : IotData)

    @Query("select * from IotData")
    fun allData() : List<IotData>
}