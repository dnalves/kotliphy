package com.dnalves.giphy.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.dnalves.giphy.R
import com.dnalves.giphy.common.bind
import com.dnalves.giphy.trending.TrendingFragment

class MainActivity : AppCompatActivity() {

    private val navigationBottomBar: BottomNavigationView by bind(R.id.main_navigation)

    private val content: FrameLayout by bind(R.id.main_content)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_trending -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.main_content, TrendingFragment()).commit()
    }

}
