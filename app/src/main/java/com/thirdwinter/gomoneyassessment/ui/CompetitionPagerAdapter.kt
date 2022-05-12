package com.thirdwinter.gomoneyassessment.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CompetitionPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    lateinit var pages: List<Fragment>

    override fun getItemCount(): Int = pages.size


    override fun createFragment(position: Int): Fragment = pages[position]
}