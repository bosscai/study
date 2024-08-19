package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.test.adapter.MyPagerAdapter
import com.example.test.fragment.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_component.*

class ComponentActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ComponentActivity"
    }

    private val lists = arrayOf(
        Fragment1(),
        Fragment2(),
        Fragment3(),
        Fragment4(),
        Fragment5()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)
        tab_layout.setupWithViewPager(view_pager)
        val tab0 = tab_layout.newTab().apply {
            text = "第一个"
        }
        val tab1 = tab_layout.newTab().apply {
            text = "第二个"
        }
        val tab2 = tab_layout.newTab().apply {
            text = "第三个"
        }
        val tab3 = tab_layout.newTab().apply {
            text = "第四个"
        }
        val tab4 = tab_layout.newTab().apply {
            text = "第五个"
        }
        tab_layout.addTab(tab0)
        tab_layout.addTab(tab1)
        tab_layout.addTab(tab2)
        tab_layout.addTab(tab3)
        tab_layout.addTab(tab4)

        view_pager.offscreenPageLimit = 4
        val adapter = MyPagerAdapter(supportFragmentManager, lists)
        view_pager.adapter = adapter

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d(TAG, "onTabSelected tab:${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                Log.d(TAG, "onPageSelected position:${position}")
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }
}