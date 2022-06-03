package com.example.remind_todolist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.remind_todolist.fragments.GoalFragment
import com.example.remind_todolist.fragments.SettingFragment
import com.example.remind_todolist.fragments.TodoFragment

// 프래그먼트와 뷰페이저1을 연결하기 위해서는 이렇게 한다.
class MainViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getCount() = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TodoFragment()
            }
            1 -> {
                GoalFragment()
            }
            else -> {
                SettingFragment()
            }
        }
    }
}