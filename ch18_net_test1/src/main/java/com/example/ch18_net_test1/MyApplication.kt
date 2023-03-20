package com.example.ch18_net_test1

import android.app.Application
import com.example.ch18_net_test1.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){



        //add....................................
        var networkService: NetworkService

        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://busan-food.openapi.redtable.global")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
         }
}