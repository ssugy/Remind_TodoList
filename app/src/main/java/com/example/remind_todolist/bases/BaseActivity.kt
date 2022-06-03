package com.example.remind_todolist.bases

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    abstract fun setValues()
    abstract fun setupEvents()
}