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
        val adapter = MyPagerAdapter(supportFragmentManager, lists)
        myViewPager.adapter = adapter
        tabLayout.setupWithViewPager(myViewPager)
        val tabTitles = arrayOf("Tab1", "Tab2", "Tab3", "Tab4", "Tab5")
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[1]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[2]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[3]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[4]))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d(TAG, "onTabSelected tab:${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        myViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
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