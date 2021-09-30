package com.rivaldy.id.mvvmtemplateapp.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.utils.UtilDialog

/**
 * Created by rivaldy on 10/07/21
 * Find me on my Github -> https://github.com/im-o
 **/
typealias Inflate<T> = (LayoutInflater, ViewGroup, Boolean) -> T

abstract class BaseFragment<viewBinding : ViewBinding>(
    private val inflate: Inflate<viewBinding>
) : Fragment() {
    private var _binding: viewBinding? = null
    val binding get() = _binding!!

    private val progressDialog: Dialog by lazy { UtilDialog.setProgressDialog(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    abstract fun initView()

    abstract fun initObservers()

    abstract fun showFailure(failure: DataResource.Failure)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showLoading(isShown: Boolean) {
        if (isShown) showProgressDialog()
        else hideProgressDialog()
    }

    private fun showProgressDialog() {
        hideProgressDialog()
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        if (progressDialog.isShowing) progressDialog.cancel()
    }
}