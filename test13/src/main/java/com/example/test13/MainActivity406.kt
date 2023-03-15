package com.example.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test13.databinding.ActivityMain406Binding
import com.example.test13.databinding.ActivityMainBinding

class MainActivity406 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain406Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            //후처리 작업 하는 코드 부분.
            // startActivity() : 후처리 안할 때
            startActivityForResult(intent, 10)
        }
    }

    // 후 처리하는 함수 , 디텔일 액티비티에서 넘어온 값을 처리하는 코드.
    // 자동으로 실행됨.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val binding = ActivityMain406Binding.inflate(layoutInflater)
        setContentView(binding.root)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("result")
            Toast.makeText(this@MainActivity406,"후처리 값 : $result",Toast.LENGTH_SHORT).show()
            Log.d("lsy","후처리 값 : $result")
            binding.result.text = "$result"
        }
    }
}