package com.example.remind_todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.API.BasicResponse
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityLoginBinding
import com.example.remind_todolist.utils.ContextUtil
import com.example.remind_todolist.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    lateinit var loginBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setValues()
        setupEvents()
    }

    override fun setValues() {

    }

    override fun setupEvents() {
        loginBinding.loginBtn.setOnClickListener {
            val inputEmail = loginBinding.emailEdt.text.toString()
            val inputPw = loginBinding.passwordEdt.text.toString()

            apiListService.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!

                        ContextUtil.setLoginToken(mContext, br.data.token)
                        ContextUtil.setAutoLogin(mContext, loginBinding.autoLoginCb.isChecked)
                        GlobalData.loginUser = br.data.user     // 여기에서 로그인하면 유저 데이터를 가지고 있다. -> 세팅에서 사용함

//                        Toast.makeText(
//                            mContext,
//                            "${GlobalData.loginUser!!.nickname}님 환영합니다.",
//                            Toast.LENGTH_SHORT
//                        ).show()

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                    else {
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val code = jsonObj.getInt("code")
                        val message = jsonObj.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })

        }

        loginBinding.mainSignUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }
    }
}