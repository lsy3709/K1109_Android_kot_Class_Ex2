package com.example.ch17_database_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch17_database_test.databinding.ItemRecyclerview2Binding

class MyViewHolder2(val binding: ItemRecyclerview2Binding): RecyclerView.ViewHolder(binding.root)

class MyAdapter2(val datas: MutableList<String>?,val datas2: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder2(ItemRecyclerview2Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder2).binding
        binding.itemDataName.text= datas!![position]
        binding.itemDataAge.text= datas2!![position]
    }
}