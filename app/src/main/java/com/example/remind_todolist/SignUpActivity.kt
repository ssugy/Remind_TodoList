package com.example.remind_todolist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var signUpBinding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setValues()
        setupEvents()
    }

    override fun setValues() {
    }

    override fun setupEvents() {
    }
}