package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityDetailBinding
import kotlinx.coroutines.NonCancellable.start

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("kkang","DetailActivity..onCreate..........")

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메인 액티비티에서 넘어온 값을 , 값을 가지고 오는 부분.
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)

        binding.detailResultView.text = "data1: $data1, data2: $data2"

        binding.detailButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("result","world")
            setResult(RESULT_OK, intent)
            // 해당 액티비트 끝내고, 이전 액티비티로 이동함.
            // 실제 splash 화면을 구성 할 때, 사용함.
            finish()
            // 디테일 액티비티 -> 메인 액티비티 이동.
            // 후처리를 뒤에서 다시 이야기 함.

//            startActivity(intent)
        }



    }
}