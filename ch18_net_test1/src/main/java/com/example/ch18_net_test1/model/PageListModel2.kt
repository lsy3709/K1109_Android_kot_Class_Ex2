package com.example.ch18_net_test1.model

import com.google.gson.annotations.SerializedName

data class PageListModel2 (
    //var data: List<ItemModel>?
        var getWalkingKr: GetWalkingKr
)

data class GetWalkingKr (
    var item : List<ItemModel3>
        )

data class ItemModel3 (
    @SerializedName("TITLE")
    var TITLE: String,
    @SerializedName("MAIN_IMG_NORMAL")
    var MAIN_IMG_NORMAL: String
)
