package com.example.test16

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.test16.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var requestContactsLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            for (entry in it.entries) {
                if(entry.key == "android.permission.READ_CONTACTS" && entry.value) {
                    Log.d("kkang", "granted ok...")
                    val intent =
                        Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                    requestContactsLauncher.launch(intent)
                }else {
                    Toast.makeText(this, "required permission..", Toast.LENGTH_SHORT).show()
                }
            }

        }

        requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                Log.d("kkang", "${it.data?.data}")
                Log.d("lsy","it의 data 하위의 data 값 보기 : ${it.data?.data}")
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null
                )
                Log.d("kkang", "cursor size....${cursor?.count}")
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContact.text = "name: $name, phone: $phone"
                }
            }
        }
        binding.contactButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    "android.permission.READ_CONTACTS"
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(arrayOf("android.permission.READ_CONTACTS"))
            } else {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestContactsLauncher.launch(intent)
            }
        }
    }
}