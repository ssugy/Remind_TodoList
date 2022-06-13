package com.example.remind_todolist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.remind_todolist.API.BasicResponse
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.utils.ContextUtil
import com.example.remind_todolist.utils.GlobalData
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
스플래시용 엑티비티
0. 바인드
1. 애니매이션 연결
2. 핸들러
3. 인텐트
 */
class SplashActivity : BaseActivity() {

    var isTokenOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setValues()
        setupEvents()
    }

    //    자동으로 넘어가는 핸들러 만들어야 된다.
    override fun setValues() {
        splashAction()
    }

    //    스플래쉬에서 토큰확인해서 자동로그인 확인하기
    fun splashAction() {
        val handler = Handler(Looper.getMainLooper())
        // 자세히 함수를 보면, 파라미터가 람다에 밀리세컨드가 포함되어 있다.
        handler.postDelayed({
            var myIntent = Intent(mContext, LoginActivity::class.java)

            if (isTokenOk && ContextUtil.getAutoLogin(mContext)) {
                Toast.makeText(
                    mContext,
                    "${GlobalData.loginUser!!.nickname}님 환영합니다.",
                    Toast.LENGTH_SHORT
                ).show()
                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
                myIntent = Intent(mContext, LoginActivity::class.java)
            }

            startActivity(myIntent)
            finish()
        },2500)

        getDeviceToken()
    }

    override fun setupEvents() {
        apiListService.getRequestMyInfo().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    isTokenOk = true
                    GlobalData.loginUser = br.data.user
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    // 키해시 가져오기
    fun getDeviceToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("token", "getDeviceToken: ${it.result}")
        }
    }
}
