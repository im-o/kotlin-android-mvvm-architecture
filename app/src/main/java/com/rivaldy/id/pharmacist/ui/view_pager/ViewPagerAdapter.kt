package com.rivaldy.id.pharmacist.ui.view_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by rivaldy on 04/06/21
 * Find me on my Github -> https://github.com/im-o
 **/

class ViewPagerAdapter(
    private val listStr: ArrayList<String>,
    private val listFrag: ArrayList<Fragment>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = listFrag[position]

    override fun getCount() = listFrag.size

    override fun getPageTitle(position: Int) = listStr[position]
}