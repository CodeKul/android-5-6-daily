package com.example.sqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class IotData(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @ColumnInfo(name = "source_dt")
        val source : String = "iot",

        @ColumnInfo
        val tmst : Long = System.currentTimeMillis()
) {
        override fun toString(): String {
                return "IotData(id=$id, source='$source', tmst=$tmst)"
        }
}