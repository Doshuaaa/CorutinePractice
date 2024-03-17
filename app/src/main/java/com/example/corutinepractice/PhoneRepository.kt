package com.example.corutinepractice

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

class PhoneRepository(application: Application) {

    private val phoneDataBase = PhoneDataBase.getInstance(application)!!
    private val phoneDao = phoneDataBase.phoneDao()
    private val phones = phoneDao.getAll()
    private val isBlocked = 0

    fun getAll(): LiveData<List<TelephoneDirEntity>> {
        return phones
    }

    fun isBlocked(phoneNumber: String) : Boolean{

        return when(phoneDao.isPhoneBlocked(phoneNumber)) {

            0 -> false
            else -> true
        }
    }

    fun blockPhone(phone: TelephoneDirEntity) {

        try {
            val thread = Thread(Runnable {
                phoneDao.blockPhone(phone)
            })
            thread.start()
        } catch (_: Exception) { }
    }

    fun unblockPhone(phone: TelephoneDirEntity) {

        try {
            val thread = Thread(Runnable {
                phoneDao.unblockPhone(phone)
            })
            thread.start()
        } catch (_: Exception) { }
    }
}