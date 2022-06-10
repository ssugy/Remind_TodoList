package com.example.remind_todolist.bases

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.remind_todolist.API.APIListService
import com.example.remind_todolist.API.ServerAPI
import retrofit2.Retrofit

abstract class BaseActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    lateinit var mContext: Context

    // 레트로핏 관련 함수
    lateinit var retrofit : Retrofit
    lateinit var apiListService: APIListService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        retrofit = ServerAPI.getRetrofit()
        apiListService = retrofit.create(APIListService::class.java)
    }

    abstract fun setValues()
    abstract fun setupEvents()
}