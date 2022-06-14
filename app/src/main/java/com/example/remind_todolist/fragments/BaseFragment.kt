package com.example.remind_todolist.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.remind_todolist.API.APIListService
import com.example.remind_todolist.API.ServerAPI
import retrofit2.Retrofit

/**
 * 베이스엑티비티 역할 - mContext반환, retrofit객체, apiListService객체 반환
 */
abstract class BaseFragment : Fragment() {

    lateinit var mContext:Context

    lateinit var retrofit: Retrofit
    lateinit var apiListService: APIListService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
        retrofit = ServerAPI.getRetrofit(mContext)
        apiListService = retrofit.create(APIListService::class.java)     // apiList는 retrofit에 의해 만들어진다.
    }

    abstract fun setValues()
    abstract fun setupEvents()
}