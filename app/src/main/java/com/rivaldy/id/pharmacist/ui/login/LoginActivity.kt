package com.rivaldy.id.pharmacist.ui.login

import androidx.appcompat.app.AppCompatActivity
import com.rivaldy.id.pharmacist.base.BaseActivity
import com.rivaldy.id.pharmacist.data.network.DataResource
import com.rivaldy.id.pharmacist.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun initView() {

    }

    override fun initObservers() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }
}