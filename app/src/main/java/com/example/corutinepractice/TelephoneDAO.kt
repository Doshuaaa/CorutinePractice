package com.example.corutinepractice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TelephoneDAO {

    @Query("SELECT * FROM table_telephone")
    fun getAll(): LiveData<List<TelephoneDirEntity>>

    @Query("SELECT EXISTS(SELECT * FROM table_telephone WHERE phoneNumber = :phoneNumber) ")
    // 1 : true, 0 : false
    fun isPhoneBlocked(phoneNumber: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun blockPhone(phone: TelephoneDirEntity)

    @Delete
    fun unblockPhone(phone: TelephoneDirEntity)

}