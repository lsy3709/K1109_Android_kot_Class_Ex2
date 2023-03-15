package com.example.ch13_test_intent_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ch13_test_intent_data.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginBtn.setOnClickListener {
            val id1 = binding.ID1.text.toString()
            val pw1 = binding.PW1.text.toString()

            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ID1", id1)
            intent.putExtra("PW1", pw1)
            //후처리 작업 하는 코드 부분.
//            : 후처리 안할 때
             startActivity(intent)

//            startActivityForResult(intent, 10)
        }

    }
}