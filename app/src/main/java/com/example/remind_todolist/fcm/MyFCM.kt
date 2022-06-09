package com.example.remind_todolist.fcm

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService(){

    // 메시지를 담고있는 변수 확인
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("token", "토큰실행: ")
        
        message.notification?.let {
            Log.d("token", "onMessageReceived: ${it.body}")
        }

//        데이터
        message.data.isNotEmpty().let {
            Log.d("token", "onMessageReceived: ${message.data}")
            val title = message.data["title"]   //이런식으로 뽑아온다.
        }

//        예시 : notification이 왔을 때, 토스트로 알람의 제목을 출력
        val title = message.notification!!.title    // 이게 널너블이라서 제대로 해줘야됨 나중에.

//        ui 쓰레드에 토스트를 띄우는 업무 부여
        val myHandler = Handler(Looper.getMainLooper()) //ui 쓰레드에 일을 맡겨주는 핸들러이다.
        myHandler.post {
//        이게 우리가 엑티비티의 컨텍스트만 활용했는데, 여기는 엑티비티가 없음.
//        그래서 어플리케이션 컨텍스트를 가져와야 한다. 이거 ui에 뿌려야하는데, 없음
            Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()
        }
    }
}