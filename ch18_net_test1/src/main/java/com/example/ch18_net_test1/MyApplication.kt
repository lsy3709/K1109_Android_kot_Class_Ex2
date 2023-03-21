package com.example.ch18_net_test1

import android.app.Application
import com.example.ch18_net_test1.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

var url2 = "https://apis.data.go.kr/6260000"

        //add....................................
        var networkService: NetworkService

        val retrofit: Retrofit
            get() = Retrofit.Builder()
//                .baseUrl("https://busan-food.openapi.redtable.global")
                .baseUrl("https://apis.data.go.kr/6260000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
         }
}