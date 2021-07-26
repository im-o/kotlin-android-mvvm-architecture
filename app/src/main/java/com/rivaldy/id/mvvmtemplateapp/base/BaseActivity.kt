package com.rivaldy.id.mvvmtemplateapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initView()
        initObservers()
    }

    abstract fun getViewBinding(): VB

    abstract fun initView()

    abstract fun initObservers()

    abstract fun showLoading(isShown : Boolean)

    abstract fun showFailure(failure: DataResource.Failure)
}