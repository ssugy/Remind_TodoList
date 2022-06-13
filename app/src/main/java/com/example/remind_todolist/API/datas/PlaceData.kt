package com.example.remind_todolist.API.datas

import com.google.gson.annotations.SerializedName

class PlaceData(
    val id : Int,
    val name : String,
    val latitude : Double,
    val longitude : Double,
    @SerializedName("is_primary")
    val isPrimary : Boolean,
) {
}