package com.rivaldy.id.pharmacist.ui.register

import androidx.fragment.app.Fragment
import com.rivaldy.id.pharmacist.R
import com.rivaldy.id.pharmacist.base.BaseActivity
import com.rivaldy.id.pharmacist.data.network.DataResource
import com.rivaldy.id.pharmacist.databinding.ActivityRegisterBinding
import com.rivaldy.id.pharmacist.ui.view_pager.ViewPagerAdapter

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override fun getViewBinding() = ActivityRegisterBinding.inflate(layoutInflater)

    override fun initView() {
        val titles = arrayListOf(getString(R.string.patient), getString(R.string.pharmacist), getString(R.string.pharmacy))
        val fragments: ArrayList<Fragment> = arrayListOf(RegisterFragment(), RegisterFragment(), RegisterFragment())
        val viewPagerAdapter = ViewPagerAdapter(titles, fragments, supportFragmentManager)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun initObservers() {

    }

    override fun showFailure(failure: DataResource.Failure) {

    }
}