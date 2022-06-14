package com.example.remind_todolist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.remind_todolist.R
import com.example.remind_todolist.databinding.FragmentSettingBinding
import com.example.remind_todolist.utils.GlobalData

// 프래그먼트는 자체적으로 뷰를 만들어야 한다. 자신만의 생명주기를 가지므로, onCreateView를 오버라이딩 한다.
// 여기에 내가 지정하고자 하는 프래그먼트용 레이아웃을 연결한다.
class SettingFragment : BaseFragment() {

    lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩 처리
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setValues()
        setupEvents()
    }

    override fun setValues() {
        // 여기에 프로필을 적용하면 되나? 최초에 1번만 서버에서 가져와서 적용하면될듯? 맞나? - 맞음
        setProfileImgAndNickName()  // 프로필 이미지와, 닉네임 적용
    }

    override fun setupEvents() {
    }

    fun setProfileImgAndNickName(){
        if (GlobalData.loginUser != null){
            Glide.with(this).load(GlobalData.loginUser!!.profileImg).into(binding.settingUserCircleImg)
            binding.settingUserNickName.text = GlobalData.loginUser!!.nickname
        }
    }
}