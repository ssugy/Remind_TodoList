package com.example.remind_todolist.API

import android.content.Context
import com.example.remind_todolist.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

    companion object {
        //        api에서는 서버주소에 접속해서, 확인하는 용도
        private val baseUrl = "https://keepthetime.xyz"

        // 레트로핏 설치
        private var retrofit: Retrofit? = null

        fun getRetrofit(context : Context): Retrofit {
            if (retrofit == null) {

                // API요청이 발생하면, 중간에 가로채서 헤더를 추가하기.
                val interceptor = Interceptor {
                    with(it) {
                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getLoginToken(context))
                            .build()
                        proceed(newRequest)
                    }
                }

                // 레트로핏은 okhttp 확장판이라서, 클라이언트에 우리가 만든 인터셉터를 달아주기
                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }
    }
}