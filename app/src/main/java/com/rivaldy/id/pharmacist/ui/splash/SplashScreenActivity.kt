package com.rivaldy.id.pharmacist.ui.splash

import android.os.Handler
import android.os.Looper
import com.rivaldy.id.pharmacist.base.BaseActivity
import com.rivaldy.id.pharmacist.data.network.DataResource
import com.rivaldy.id.pharmacist.databinding.ActivitySplashScreenBinding
import com.rivaldy.id.pharmacist.ui.MainActivity
import com.rivaldy.id.pharmacist.ui.login.LoginActivity
import com.rivaldy.id.pharmacist.utils.UtilExtensions.openActivity

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    private var userToken = ""

    override fun getViewBinding() = ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObservers() {
        Handler(Looper.getMainLooper()).postDelayed({
            showLoading(false)
            if (userToken.isEmpty()) openActivity(LoginActivity::class.java)
            else openActivity(MainActivity::class.java)
            finish()
        }, 2000)
    }

    override fun showFailure(failure: DataResource.Failure) {

    }
}