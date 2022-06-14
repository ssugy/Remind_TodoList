package com.example.remind_todolist.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.divyanshu.draw.activity.DrawingActivity
import com.example.remind_todolist.ClassifyingActivity
import com.example.remind_todolist.LoginActivity
import com.example.remind_todolist.R
import com.example.remind_todolist.databinding.FragmentGoalBinding
import com.example.remind_todolist.utils.ContextUtil

class GoalFragment() : BaseFragment(){

    lateinit var binding : FragmentGoalBinding

    // 프래그먼트는 onCreateView이다
    // 뷰를 리턴하는데, 그게 프래그먼트여야 한다.
    // 여기에 있는 inflater를 통해서 뷰를 만드는 것 이다. 그래서 mContext사용하는게 아니다.
    // onCreateView에서 바인딩 연결하고, onViewCreate에서 setvalues, setupevent넣기
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 여기부분 이해를 해야됨. xml 레이아웃 파일에 대한 뷰를 생성할때에는
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_goal, container, false  )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setValues()
        setupEvents()
    }

    override fun setValues() {
        // 로그아웃 버튼 눌렀을 때 -> 로그아웃 기능 (체크박스 false설정)
        binding.logOutBtn.setOnClickListener {
            //체크박스는 콘텍스트 유틸안에 저장되어있다.
            ContextUtil.clear(mContext)
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        // 그리기버튼 눌렀을 때 -> 저장해둔 drawingAcvity로 이동
        binding.drawingBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), DrawingActivity::class.java))
        }

        // 손글씨 버튼 눌렀을때 -> 손글씨 분류 엑티비티로 이동한다.
        binding.handWritingClassificationBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), ClassifyingActivity::class.java))
        }
    }

    override fun setupEvents() {
    }
}