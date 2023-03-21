package com.example.ch18_net_test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch18_net_test1.databinding.ActivityMainBinding
import com.example.ch18_net_test1.model.PageListModel2
import com.example.ch18_net_test1.recycler.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val serviceKey = "tVW2KFmuQi45yJsJGHm71Ud8PRLHZJvrLgyJp7NYkEaT0aVvlkr82a5JJJZrVu4O"
        val serviceKey2 = "ALRX9GpugtvHxcIO%2FiPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH%2FAKv%2BA1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ%3D%3D"
        val serviceKey3 = "ALRX9GpugtvHxcIO/iPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH/AKv+A1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ=="
        val resultType ="json"

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = (applicationContext as MyApplication).networkService

        val userListCall = networkService.getList2(serviceKey3,1,10,resultType)
        Log.d("lsy", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<PageListModel2> {
            override fun onResponse(call: Call<PageListModel2>, response: Response<PageListModel2>) {

                Log.d("lsy","실행 여부 확인. userListCall.enqueue")
                val userList = response.body()
                Log.d("lsy","userList data 값 : ${userList?.getWalkingKr?.item}")
                //.......................................

                binding.recyclerView.adapter= MyAdapter(this@MainActivity,userList?.getWalkingKr?.item)


                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                )

//                binding.pageView.text=userList?.page
//                binding.totalView.text=userList?.total
            }

            override fun onFailure(call: Call<PageListModel2>, t: Throwable) {
                Log.d("lsy","fail")
                call.cancel()
            }
        })

      /*  val userListCall = networkService.getList(serviceKey,1)
        Log.d("lsy", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<PageListModel> {
            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {

                val userList = response.body()
                Log.d("lsy","userList data 값 : ${userList?.body}")
                //.......................................

                binding.recyclerView.adapter=MyAdapter(this@MainActivity, userList?.body)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                )

//                binding.pageView.text=userList?.page
//                binding.totalView.text=userList?.total
            }

            override fun onFailure(call: Call<PageListModel>, t: Throwable) {
                call.cancel()
            }
        })*/



    }
}