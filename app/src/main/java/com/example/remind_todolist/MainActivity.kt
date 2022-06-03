package com.example.remind_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityMainBinding

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