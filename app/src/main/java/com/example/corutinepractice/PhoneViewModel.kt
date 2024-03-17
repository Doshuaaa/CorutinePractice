package com.example.corutinepractice

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PhoneViewModel(application: Application): ViewModel() {

    private val repository = PhoneRepository(application)
    private val phones = repository.getAll()


    fun getAll(): LiveData<List<TelephoneDirEntity>> {

        return phones
    }

    fun isBlocked(phoneNumber: String): Boolean {

        return repository.isBlocked(phoneNumber)
    }

    fun blockPhone(phone: TelephoneDirEntity) {

        repository.blockPhone(phone)
    }

    fun unblockPhone(phone: TelephoneDirEntity) {

        repository.unblockPhone(phone)
    }
}