package com.rivaldy.id.pharmacist.ui.login

import com.rivaldy.id.pharmacist.base.BaseActivity
import com.rivaldy.id.pharmacist.data.network.DataResource
import com.rivaldy.id.pharmacist.databinding.ActivityLoginBinding
import com.rivaldy.id.pharmacist.ui.register.RegisterActivity
import com.rivaldy.id.pharmacist.utils.UtilExtensions.openActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun initView() {
        initClick()
    }

    override fun initObservers() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }

    private fun initClick() {
        binding.hintRegisterTV.setOnClickListener {
            openActivity(RegisterActivity::class.java)
        }
    }
}