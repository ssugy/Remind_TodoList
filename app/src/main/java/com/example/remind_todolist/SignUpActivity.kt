package com.example.remind_todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var signUpBinding: ActivitySignUpBinding

    var isEmailDuplicationCheck = false
    var isNickNameDuplicationCheck = false
    val isPwDuplicaionCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setValues()
        setupEvents()
    }

    override fun setValues() {
    }

    override fun setupEvents() {
        signUpBinding.signUpBtn.setOnClickListener {
//            1. 이메일 중복 체크
            if (!isEmailDuplicationCheck) {
                Toast.makeText(mContext, "이메일 중복 체크를 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!isPwDuplicaionCheck) {
                Toast.makeText(mContext, "비밀번호가 일치 하지 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(!isNickNameDuplicationCheck) {
                signUp()
            }
        }
    }

//    모든 조건을 통과하면 실행할 api
    fun signUp(){

    }
}