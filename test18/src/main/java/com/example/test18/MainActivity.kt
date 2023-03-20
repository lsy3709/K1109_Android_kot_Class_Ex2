package com.example.test18

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.test18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var telephonyManager: TelephonyManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (it.all { permission -> permission.value == true }) {
                Toast.makeText(this, "권한 승인", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "권한 거부", Toast.LENGTH_SHORT).show()
            }
        }

        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            telephonyManager.registerTelephonyCallback(
                mainExecutor,
                object : TelephonyCallback(), TelephonyCallback.CallStateListener {
                    override fun onCallStateChanged(state: Int) {
                        when (state) {
                            TelephonyManager.CALL_STATE_IDLE -> {
                                Log.d("kkang", "IDLE....")
                            }
                            TelephonyManager.CALL_STATE_OFFHOOK -> {
                                Log.d("kkang", "OFFHOOK....")
                            }
                            TelephonyManager.CALL_STATE_RINGING -> {
                                Log.d("kkang", "RINGING....")
                            }
                        }
                    }
                })


        }else {
            val phoneStateListener = object : PhoneStateListener() {
                override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                    when (state) {
                        TelephonyManager.CALL_STATE_IDLE -> Log.d("kkang", "IDLE..")
                        TelephonyManager.CALL_STATE_RINGING -> Log.d("kkang", "RINGING..")
                        TelephonyManager.CALL_STATE_OFFHOOK -> Log.d("kkang", "OFFHOOK..")
                    }
                }
            }
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
        }



        val status= ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED){
            val countryIso = telephonyManager.networkCountryIso
            val operatorName = telephonyManager.networkOperatorName
            val phoneNumber = telephonyManager.line1Number
            Log.d("kkang", "countryIso: $countryIso, operatorName: $operatorName, phoneNumber: $phoneNumber")

        }else {
            requestPermissionLauncher.launch(
                arrayOf(
                    "android.permission.READ_PHONE_NUMBERS",
                    "android.permission.READ_PHONE_STATE"
                )
            )
        }
    }
}