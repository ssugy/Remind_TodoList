package com.example.remind_todolist

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.API.APIListService
import com.example.remind_todolist.API.BasicResponse
import com.example.remind_todolist.API.ServerAPI
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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
                Toast.makeText(mContext, "비밀번호가 일치하지 않거나, 8자리 미만 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(!isNickNameDuplicationCheck) {
                Toast.makeText(mContext, "닉네임이 존재합니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                signUp()
            }
        }

//        이메일 중첩 확인
        signUpBinding.emailDuplBtn.setOnClickListener{
            // 사인업에 넣은 이메일을 쿼리로 보내기
            val userEmail = signUpBinding.emailEdt.text.toString()
            val retrofit = ServerAPI.getRetrofit()
            retrofit.create(APIListService::class.java)
            apiListService.getUserCheck("EMAIL", userEmail).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }

            })
        }

        signUpBinding.nickDupBtn.setOnClickListener{}
    }



//    모든 조건을 통과하면 실행할 api
    fun signUp(){
        // 이슈체크하기위한 커밋
        // 이슈체크하기위한 커밋2
        // 이슈체크하기위한 커밋3
    }

}