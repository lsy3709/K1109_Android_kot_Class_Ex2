package com.example.test8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.example.test8_2.databinding.ActivityMain2Binding



class MainActivity2 : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    open class MyEventHandler : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            Log.d("lsy", "체크박스 클릭")
        }

    }

    // 2번째 방법의 인터페이스 구현시 재정의해야하는 함수.
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("lsy", "체크박스 클릭222")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var state: Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.img1.setOnClickListener {
//            if(state == 0){
//                binding.img1.visibility = View.INVISIBLE
//                state = 1
//            } else {
//                binding.img1.visibility = View.VISIBLE
//                state = 0
//            }
//        }
//
//        binding.button.setOnLongClickListener {
//            if(state == 0){
//                binding.img1.visibility = View.INVISIBLE
//                state = 1
//            } else {
//                binding.img1.visibility = View.VISIBLE
//                state = 0
//            }
//            true
//        }


      //3번째 sam 기법 (single abstract method)
        binding.checkbox?.setOnCheckedChangeListener {
                a, b ->
            if(state == 0){
//                binding.img1.visibility = View.INVISIBLE
                state = 1
            } else {
//                binding.img1.visibility = View.VISIBLE
                state = 0
            }


        }


      //2 번째방법
//        binding.checkbox.setOnCheckedChangeListener(this)

//        val test = MyEventHandler()
//        test.onCheckedChanged(binding.checkbox, true)
  // 1 번째 방법
//        binding.checkbox.setOnCheckedChangeListener(MyEventHandler())



    }
}