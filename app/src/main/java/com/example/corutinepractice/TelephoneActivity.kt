package com.example.corutinepractice

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TelephoneActivity : AppCompatActivity() {

    data class PhoneNumber(
        val name: String,
        val number: String,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telephone)

        val resolver = contentResolver!!
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            ,  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            ,  ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cursor = resolver.query(phoneUri, projection, null, null, null)
        val numberList = arrayListOf<PhoneNumber>()

        if(cursor != null) {

            while(cursor.moveToNext()) {
                val nameIndex = cursor.getColumnIndex(projection[1])
                val numberIndex = cursor.getColumnIndex(projection[2])
                val name = cursor.getString(nameIndex)
                var number = cursor.getString(numberIndex)
                //number = number.replace("-","")
                numberList.add(PhoneNumber(name, number))
                //Log.d("GetContact", "이름 : " + name + " 번호 : " + number);
            }

            numberList.sortBy { it.name }

            for(number in numberList) {

                Log.d("GetContact", "이름 : " + number.name + " 번호 : " + number.number)
            }
        }

        cursor?.close()
    }
}