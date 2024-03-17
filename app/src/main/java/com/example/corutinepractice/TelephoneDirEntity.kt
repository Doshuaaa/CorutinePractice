package com.example.corutinepractice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_telephone")
data class TelephoneDirEntity(

    val name: String,
    @PrimaryKey
    val phoneNumber: String
)
