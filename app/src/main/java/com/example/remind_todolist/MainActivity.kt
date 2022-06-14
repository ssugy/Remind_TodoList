package com.example.remind_todolist

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.divyanshu.draw.activity.DrawingActivity
import com.example.remind_todolist.adapters.MainViewPagerAdapter
import com.example.remind_todolist.bases.BaseActivity
import com.example.remind_todolist.databinding.ActivityMainBinding
import com.example.remind_todolist.utils.ContextUtil

/**
 * 메인엑티비티에는 이제 인공지능쪽 연결 예정
 */
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setValues()
        setupEvents()
    }

    override fun setValues() {
        mainViewAdapter = MainViewPagerAdapter(this)
        binding.mainViewPager.adapter = mainViewAdapter // 여기까지가 기본 뷰페이저만 연결한 상태

        // 뷰페이저가 이동할 때 세부적인 내용 정의해야 한다.
        binding.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // 바뀐 페이지 번호를 바텀 네비게이션에 알려야됨
                binding.mainBottomNav.menu.getItem(position).isChecked = true
            }
        })

        // 바텀 네비게이션의 아이콘을 클릭하면, 해당하는 뷰페이저가 이동해야 한다.
        // 그렇게 하기 위해서는, menu에 각각의 아이디를 부여해야 하고, 해당 아이디를 it.itemId로 이용해서 구분한다.
        // 뷰페이저를 이동한다.
        binding.mainBottomNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menuAI -> binding.mainViewPager.currentItem = 0
                R.id.menuAppointments -> binding.mainViewPager.currentItem = 1
                R.id.menuSettings -> binding.mainViewPager.currentItem = 2
            }

            // 이게 무슨의미지?
            return@setOnItemSelectedListener true
        }
    }

    override fun setupEvents() {
    }
}