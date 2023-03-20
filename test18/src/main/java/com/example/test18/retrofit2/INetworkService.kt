package com.example.test18.retrofit2

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface INetworkService {
    @GET("api/users")
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    @GET("users/list?sort=desc")
    fun test1(): Call<UserModel>

    @GET("group/{id}/users/{name}")
    fun test2(
        @Path("id") userId: String,
        @Path("name") arg2: String
    ): Call<UserModel>

    @GET("group/users")
    fun test3(
        @Query("sort") arg1: String,
        @Query("name") arg2: String
    ): Call<UserModel>

    @GET("group/users")
    fun test4(
        @QueryMap options: Map<String, String>,
        @Query("name") name: String
    ): Call<UserModel>

    @POST("group/users")
    fun test5(
        @Body user: UserModel,
        @Query("name") name: String
    ): Call<UserModel>

    @FormUrlEncoded
    @POST("user/edit")
    fun test6(
        @Field("first_name") first: String?,
        @Field("last_name") last: String?,
        @Query("name") name: String?
    ): Call<UserModel>

    @FormUrlEncoded
    @POST("tasks")
    fun test7(@Field("title") titles: List<String>): Call<UserModel>

    @Headers("Cache-Control: max-age=640000")
    @GET("widget/list")
    fun test8(): Call<UserModel>

    @GET
    fun test9(@Url url: String, @Query("name") name: String): Call<UserModel>
}