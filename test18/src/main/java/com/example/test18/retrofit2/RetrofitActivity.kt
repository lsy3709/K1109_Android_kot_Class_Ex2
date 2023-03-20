package com.example.test18.retrofit2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.test18.databinding.ActivityRetrofitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = (applicationContext as MyApplication).networkService

        val userListCall = networkService.doGetUserList("1")
        Log.d("kkang", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<UserListModel> {
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {

                val userList = response.body()

                //.......................................

                binding.recyclerView.adapter=MyAdapter(this@RetrofitActivity, userList?.data)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@RetrofitActivity, LinearLayoutManager.VERTICAL)
                )

                binding.pageView.text=userList?.page
                binding.totalView.text=userList?.total
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }
        })

        binding.testButton.setOnClickListener {
//            val call: Call<UserModel> = networkService.test1()//https://reqres.in/users/list?sort=desc

//            val call: Call<UserModel> = networkService.test2("10", "kkang")//https://reqres.in/group/10/users/kkang

//            val call: Call<UserModel> = networkService.test3("age", "kkang")//https://reqres.in/group/users?sort=age&name=kkang

            val call: Call<UserModel> = networkService.test4(
                mapOf<String, String>("one" to "hello", "two" to "world"),
                "kkang"
            )//https://reqres.in/group/users?one=hello&two=world&name=kkang

//            val call: Call<UserModel> = networkService.test5(
//                UserModel(id="1", firstName = "gildong", lastName = "hong", avatar = "some url"),
//                "kkang"
//            )//https://reqres.in/group/users?name=kkang

//            val call: Call<UserModel> = networkService.test6(
//                "gildong 길동",
//                "hong 홍",
//                "kkang"
//            )//https://reqres.in/user/edit?name=kkang

//            val list: MutableList<String> = ArrayList()
//            list.add("홍길동")
//            list.add("류현진")
//            val call = networkService.test7(list)

//            val call = networkService.test9("http://www.google.com", "kkang")//http://www.google.com/?name=kkang
//
            Log.d("kkang","url:"+call.request().url().toString())

        }

    }


}