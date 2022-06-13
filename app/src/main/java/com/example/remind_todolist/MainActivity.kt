package com.example.remind_todolist

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.divyanshu.draw.activity.DrawingActivity
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityMainBinding
import com.example.remind_todolist.utils.ContextUtil

/**
 * 메인엑티비티에는 이제 인공지능쪽 연결 예정
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
        binding.drawingBtn.setOnClickListener {
            val myIntent = Intent(mContext, DrawingActivity::class.java)
            startActivity(myIntent)
        }

        binding.logOutBtn.setOnClickListener {
            ContextUtil.setAutoLogin(mContext, false)
            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    override fun setupEvents() {
    }
}