package com.example.remind_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    lateinit var loginBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun setValues() {
    }

    override fun setupEvents() {
    }
}