package com.example.ch13_test_intent_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ch13_test_intent_data.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메인 액티비티에서 넘어온 값을 , 값을 가지고 오는 부분.
        val ID1 = intent.getStringExtra("ID1")
        val PW1 = intent.getStringExtra("PW1")

        binding.IDM1.text = ID1
        binding.PWM1.text = PW1

        Log.d("lsy", "MainActivity ID1 : $ID1,  PW1: $PW1")
    }
}