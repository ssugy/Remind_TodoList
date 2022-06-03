package com.example.remind_todolist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityMainBinding

/**
 * 1. 탭레이아웃 작성
 * 2. 바텀네비게이션
 * 3. 뷰페이저 연결
 *
 */
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setValues()
        setupEvents()
    }

    override fun setValues() {
    }

    override fun setupEvents() {
    }
}