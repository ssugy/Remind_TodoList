package com.example.remind_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remind_todolist.bases.BaseActivity

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
    }

    override fun setValues() {

    }

    override fun setupEvents() {

    }
}