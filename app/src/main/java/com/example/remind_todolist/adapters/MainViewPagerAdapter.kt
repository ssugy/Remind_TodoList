package com.example.remind_todolist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.remind_todolist.fragments.GoalFragment
import com.example.remind_todolist.fragments.SettingFragment
import com.example.remind_todolist.fragments.TodoFragment

// 프래그먼트와 뷰페이저1을 연결하기 위해서는 이렇게 한다.
class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GoalFragment()
            1 -> TodoFragment()
            else -> SettingFragment()
        }
    }
}