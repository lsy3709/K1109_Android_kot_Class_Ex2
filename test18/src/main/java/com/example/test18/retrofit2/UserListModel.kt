package com.example.test18.retrofit2

import com.google.gson.annotations.SerializedName

data class UserListModel(
    var page: String,
    @SerializedName("per_page")
    var perPage: String,
    var total: String,
    @SerializedName("total_pages")
    var totalPages: String,
    var data: List<UserModel>?
)