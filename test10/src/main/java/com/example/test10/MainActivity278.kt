package com.example.test10

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.test10.databinding.ActivityMain278Binding
import com.example.test10.databinding.ActivityMainBinding

class MainActivity278 : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    fun showToast() {
        val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_LONG)
        toast.addCallback(
            object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("lsy", "toast hidden")
                }
                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("lsy", "toast shown")
                }
            })
        toast.show()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main278)
        val binding = ActivityMain278Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            showToast()
        }

        binding.button2.setOnClickListener {
            val toast = Toast.makeText(this, "버튼2번 테스트.", Toast.LENGTH_SHORT)
            toast.show()
            Log.d("lsy","버튼2 출력.")
        }

        binding.b3.setOnClickListener {
            showToast()
        }

        /*showToast()*/

    }
}