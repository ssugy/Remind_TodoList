package com.example.remind_todolist

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityClassifyingBinding
import com.example.remind_todolist.tflite.ClassifierHandNum
import java.io.IOException
import java.util.*

class ClassifyingActivity : BaseActivity() {

    lateinit var binding : ActivityClassifyingBinding
    lateinit var cls : ClassifierHandNum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_classifying)

        setValues()
        setupEvents()
    }

    override fun setValues() {
        // 글자 두껍게 해야지 이미지 축소할때 손실이 나지 않는다.
        binding.drawView.setStrokeWidth(100f)
        binding.drawView.setBackgroundColor(Color.BLACK)    //배경색 - 파이썬 학습할때 색상 동일하게 적용.
        binding.drawView.setColor(Color.WHITE)  // 손글자 색 - 파이썬 학습 색상 동일하게.

        val resultView = binding.resultTxt
        
//        클리어 버튼 - 누르면 그려놓은 것 초기화
        binding.clearBtn.setOnClickListener {
            binding.drawView.clearCanvas()
        }

//        분류버튼(핵심)
        binding.classifyBtn.setOnClickListener {
            val drawImg : Bitmap = binding.drawView.getBitmap()

            val res = cls.classify(drawImg)
            val outStr =
                res?.let { it1 -> String.format(Locale.ENGLISH, "%d, %.0f%%", it1.first, res.second * 100.0f) }
            resultView.text = outStr
        }

        cls = ClassifierHandNum(mContext)
        try {
            cls.initHandNumber()
        } catch (e : IOException){
            Log.d("버그", "분류 실패", e)
        }
    }

    // 자원해제
    override fun onDestroy() {
        cls.finish()
        super.onDestroy()
    }

    override fun setupEvents() {

    }
}