package com.example.test11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.twoButton.setOnClickListener {
            // 앞으로 많이 보게 될 코드,
            // 다른 화면으로 이동 할 때 사용함.
            // 지금은 단순히 화면 이동만 하지만,
            // 여기에 데이터를 담아서 보낼수 도 있음.
            //
            val intent = Intent(this, TwoActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("kkang","onSupportNavigateUp.................")
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}