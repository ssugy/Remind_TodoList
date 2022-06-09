package com.example.remind_todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.example.remind_todolist.bases.BaseActivity
import com.google.firebase.messaging.FirebaseMessaging

/*
스플래시용 엑티비티
0. 바인드
1. 애니매이션 연결
2. 핸들러
3. 인텐트
 */
class SplashActivity : BaseActivity() {

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

    //    핸들러의 기본은 루퍼부터 시작
    fun splashAction() {
        val handler = Handler(Looper.getMainLooper())
        // 자세히 함수를 보면, 파라미터가 람다에 밀리세컨드가 포함되어 있다.
        handler.postDelayed({
            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        },2500)

        getDeviceToken()
    }

    override fun setupEvents() {
    }

    // 키해시 가져오기
    fun getDeviceToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("token", "getDeviceToken: ${it.result}")
        }
    }
}
