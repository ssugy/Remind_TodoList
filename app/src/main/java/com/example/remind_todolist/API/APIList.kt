package com.example.remind_todolist.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 레트로핏 기본형태 - 인터페이스 없으니, 함수 몸체가 들어가면 안된다.
interface APIList {

    // user
    @GET("/user/check")
    fun getUserCheck(
        @Query("type") type: String,
        @Query("value") value : String,
    ): Call<BasicResponse>
}