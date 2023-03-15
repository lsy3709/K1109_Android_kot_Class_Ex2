package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            // 데이터를 같이 전달.
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            startActivity(intent)
        }

        val result = intent.getStringExtra("result")
        binding.result.text = "$result"

        Log.d("lsy","디테일 액티비티에서 넘어온 result 값 : $result")

    }
}