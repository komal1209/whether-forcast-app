package com.example.weatherforecastingapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class WeatherViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter
(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val fragmentList: MutableList<Fragment> = ArrayList<Fragment>()
    val fragmentTitleList: MutableList<String> = ArrayList<String>() as MutableList<String>

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList.get(position)
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)

    }
}