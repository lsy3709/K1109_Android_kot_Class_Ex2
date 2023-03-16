package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13.databinding.ActivityMain443Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity443 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain443Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
//            var sum = 0L
//            var time = measureTimeMillis {
//                for(i in 1..2_000_000_000){
//                    sum += i
//                }
//            }
//            Log.d("kkang","time : $time")
//            binding.resultView.text = "sum : $sum"

//            val handler=object: Handler(){
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultView.text = "sum : ${msg.arg1}"
//                }
//            }
//
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..2_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1=sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("kkang", "time : $time")
//            }



            // 큐 알고리즘 과 비슷.
            val channel = Channel<Int>()

            // 스코프는 작업 2 방향 나눠서 작업.
            // 오래 걸리는 작업.
            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000000) {
                        sum += i
                    }
                }
                Log.d("kkang", "time : $time")
                channel.send(sum.toInt())
            }

            // 사용자 이벤트를 처리하는 스코프, 작업의 속도가 빠르게 처리 하는 수행을 시키는게 좋습니다.
            // 메인 스레드 , UI 스레드, 화면을 구현하는 부분.
            val mainScope= GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultView.text = "sum : $it"
                }
            }


        }
    }
}