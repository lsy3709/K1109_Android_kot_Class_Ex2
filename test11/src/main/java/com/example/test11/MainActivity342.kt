package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain342Binding
import com.example.test11.databinding.Item342Binding

class MainActivity342 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain342Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL)
        )
    }
}

class MyViewHolder(val binding: Item342Binding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        Log.d("kkang", "init datas size: ${datas.size}")
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kkang","onBindViewHolder : $position")
        val binding=(holder as MyViewHolder).binding
        //뷰에 데이터 출력
        binding.itemData.text= datas[position]

        //뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener{
            Log.d("kkang", "item root click : $position")
        }
    }
}