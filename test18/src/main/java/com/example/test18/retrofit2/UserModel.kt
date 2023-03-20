package com.example.test18.retrofit2

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class UserModel(
    var id: String,
    @SerializedName("first_name")
    var firstName: String,
    // @SerializedName("last_name")
    var lastName: String,
    var avatar: String,
    var avatarBitmap: Bitmap
)