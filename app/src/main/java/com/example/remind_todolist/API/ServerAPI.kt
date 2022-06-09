package com.example.remind_todolist.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

    companion object {
        //        api에서는 서버주소에 접속해서, 확인하는 용도
        private val baseUrl = "https://keepthetime.xyz"

        // 레트로핏 설치
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit!!
        }
    }
}