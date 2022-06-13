package com.example.remind_todolist.API

import retrofit2.Call
import retrofit2.http.*

// 레트로핏 기본형태 - 인터페이스 없으니, 함수 몸체가 들어가면 안된다.
interface APIListService {

    // 아이디 닉네임 중복 체크
    @GET("/user/check")
    fun getUserCheck(
        @Query("type") type: String,
        @Query("value") value : String,
    ): Call<BasicResponse>
    
    // 회원가입
    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email : String,
        @Field("password") pw : String,
        @Field("nick_name") nickname : String,
    ) : Call<BasicResponse>

    // 로그인
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin (
        @Field("email") email: String,
        @Field("password") password : String,
    ) : Call<BasicResponse>

    // 유저 정보
    @GET("/user")
    fun getRequestMyInfo() : Call<BasicResponse>
}