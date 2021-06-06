package com.example.myapplication.intro

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
import java.util.*

class IntroActivity : AppCompatActivity() {
    private var screenPager: ViewPager? = null
    private var introViewPagerAdapter: IntroViewPagerAdapter? = null
    private var position = 0

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mList: MutableList<ScreenItem> = ArrayList()
        mList.add(
            ScreenItem(
                getString(R.string.intro_read_book),
                getString(R.string.intro_read_book_description),
                R.raw.intro1
            )
        )
        mList.add(
            ScreenItem(
                getString(R.string.intro_unlimited_knowledge),
                getString(R.string.unlimited_knowledge_description),
                R.raw.intro2
            )
        )
        mList.add(
            ScreenItem(
                getString(R.string.connect),
                getString(R.string.unlimited_knowledge_description),
                R.raw.intro3
            )
        )
        screenPager = findViewById(R.id.screen_viewpager)
        introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        screenPager!!.adapter = introViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(screenPager)
        binding.btnNext.setOnClickListener(View.OnClickListener {
            position = screenPager!!.currentItem
            if (position < mList.size) {
                position++
                screenPager!!.currentItem = position
            }
            if (position == mList.size - 1) { // when we reach to the last screen
                loadLastScreen()
            }
        })
        binding.btnGetStarted.setOnClickListener(View.OnClickListener { })
    }

    private fun loadLastScreen() {
        binding.btnNext.visibility = View.INVISIBLE
        binding.btnGetStarted.visibility = View.VISIBLE
        binding.tabIndicator.visibility = View.INVISIBLE
    }
}