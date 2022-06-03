package com.example.remind_todolist.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remind_todolist.R

class GoalFragment() : Fragment(){

    // 프래그먼트는 onCreateView이다
    // 뷰를 리턴하는데, 그게 프래그먼트여야 한다.
    // 여기에 있는 inflater를 통해서 뷰를 만드는 것 이다. 그래서 mContext사용하는게 아니다.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_goal, container, false)
    }
}