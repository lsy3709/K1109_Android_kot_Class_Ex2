package com.example.ch18_net_test1.model

import com.google.gson.annotations.SerializedName

data class ItemModel (
    @SerializedName("RSTR_NM")
    var RSTR_NM: String,
    @SerializedName("FOOD_IMG_URL")
    var FOOD_IMG_URL: String
)

