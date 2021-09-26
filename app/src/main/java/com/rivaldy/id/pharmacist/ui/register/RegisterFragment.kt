package com.rivaldy.id.pharmacist.ui.register

import android.view.LayoutInflater
import android.view.ViewGroup
import com.rivaldy.id.pharmacist.base.BaseDialogFragment
import com.rivaldy.id.pharmacist.data.network.DataResource
import com.rivaldy.id.pharmacist.databinding.FragmentRegisterBinding

class RegisterFragment : BaseDialogFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun initView() {
    }

    override fun initObserver() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }
}