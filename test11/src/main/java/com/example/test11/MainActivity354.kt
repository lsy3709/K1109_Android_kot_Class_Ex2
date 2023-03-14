package com.example.test11

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain354Binding
import com.example.test11.databinding.Item354Binding

class MainActivity354 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main354)

        val binding= ActivityMain354Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        binding.viewpager.adapter=MyPagerAdapter(datas)
    }

    class MyPagerViewHolder(val binding: Item354Binding): RecyclerView.ViewHolder(binding.root)

    class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun getItemCount(): Int{
            return datas.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyPagerViewHolder(Item354Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerTextView.text=datas[position]
            when(position % 3){
                0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
                1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
                2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}