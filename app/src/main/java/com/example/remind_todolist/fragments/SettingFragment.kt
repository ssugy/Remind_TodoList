package com.example.remind_todolist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remind_todolist.R

// 프래그먼트는 자체적으로 뷰를 만들어야 한다. 자신만의 생명주기를 가지므로, onCreateView를 오버라이딩 한다.
// 여기에 내가 지정하고자 하는 프래그먼트용 레이아웃을 연결한다.
class SettingFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setValues()
        setupEvents()
    }

    override fun setValues() {
    }

    override fun setupEvents() {
    }
}