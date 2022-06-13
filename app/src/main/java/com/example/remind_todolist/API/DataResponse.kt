package com.example.remind_todolist.API

import com.example.remind_todolist.API.datas.PlaceData
import com.example.remind_todolist.API.datas.UserData
import com.google.gson.annotations.SerializedName

class DataResponse(
    val user : UserData,
    val token : String,
    val users : List<UserData>,
    val friends : List<UserData>,
    val places : List<PlaceData>,
) {
}