package com.example.ch18_net_test1.retrofit


import com.example.ch18_net_test1.model.PageListModel
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {


    @GET("/api/food/img")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
        @Query("pageNo") pageNo: Int
    ): Call<PageListModel>

}