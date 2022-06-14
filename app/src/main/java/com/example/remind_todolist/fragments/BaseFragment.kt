package com.example.remind_todolist.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * 베이스엑티비티 역할 - mContext반환, retrofit객체, apiListService객체 반환
 */
abstract class BaseFragment : Fragment() {

    lateinit var mContext:Context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
    }

    abstract fun setValues()
    abstract fun setupEvents()
}