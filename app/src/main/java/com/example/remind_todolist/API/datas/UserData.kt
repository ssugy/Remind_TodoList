package com.example.remind_todolist.API.datas

import com.google.gson.annotations.SerializedName

class UserData(
    val id : Int,
    val provider : String,
    val email : String,
    @SerializedName("nick_name")
    val nickname : String,
    @SerializedName("profile_img")
    val profileImg : String,
    @SerializedName("ready_minute")
    val readyMinute : String,
) {
}