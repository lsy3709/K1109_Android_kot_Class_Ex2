package com.example.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test12.databinding.ActivityMain384Binding
import com.example.test12.databinding.ActivityMain385Binding
import com.google.android.material.tabs.TabLayout

class MainActivity385 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain385Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        supportFragmentManager.beginTransaction().add(R.id.tabContent, OneFragment()).commit()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            //탭 버튼 선택시 이벤트
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text){
                    "Tab1"-> transaction.replace(R.id.tabContent, OneFragment())
                    "Tab2"-> transaction.replace(R.id.tabContent, TwoFragment())
                    "Tab3"-> transaction.replace(R.id.tabContent, ThreeFragment())
                }
                transaction.commit()
            }
            //선택된 탭 버튼을 다시 선택하는 경우
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("kkang", "onTabReselected........")
            }
            //선택된 탭버튼이 다른 탭 버튼을 눌러 선택 해제 되는 경우
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("kkang", "onTabUnselected........")
            }
        })
    }


}