package com.example.corutinepractice

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.telecom.InCallService
import android.telecom.TelecomManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.corutinepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewBinding: ActivityMainBinding? = null
    private val binding get() = viewBinding!!
    private val REQUEST_PERMISSIONS = 1


    private lateinit var activityResultLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()

        val filter = IntentFilter()
        filter.addAction("android.intent.action.NEW_OUTGOING_CALL")
        registerReceiver(CallReceiver(), filter)

        binding.phoneNumTextView.setOnClickListener {
            val intent = Intent(this, TelephoneActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkPermission() {

        val permissions = mutableMapOf<String, String>()
        permissions["phone"] = Manifest.permission.ANSWER_PHONE_CALLS
        permissions["read"] = Manifest.permission.READ_CONTACTS

        var denied = permissions.count { ContextCompat.checkSelfPermission(this, it.value)  == PackageManager.PERMISSION_DENIED }

        if(denied > 0) {
            requestPermissions(permissions.values.toTypedArray(), REQUEST_PERMISSIONS)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }


    inner class CallReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent) {

            if(intent.action == "android.intent.action.NEW_OUTGOING_CALL") {
                //val telephoneManager = getSystemService(Context.TELECOM_SERVICE) as TelecomManager

                val extras = intent.extras
                val num = extras?.getString(Intent.EXTRA_PHONE_NUMBER)
                binding.phoneNumTextView.text = num

                if(num == "01073619910") {
                    val telecomManager = getSystemService(Context.TELECOM_SERVICE) as TelecomManager
                    if (ActivityCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ANSWER_PHONE_CALLS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return
                    }
                    telecomManager.endCall()

                }
            }
        }
    }

    class MyInCallService : InCallService() {


        override fun onCallRemoved(call: Call?) {
            super.onCallRemoved(call)

        }

    }
}