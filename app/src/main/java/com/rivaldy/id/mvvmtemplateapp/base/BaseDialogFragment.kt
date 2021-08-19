package com.rivaldy.id.mvvmtemplateapp.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.utils.UtilDialog.setProgressDialog

/**
 * Created by rivaldy on 19/08/21
 * Find me on my Github -> https://github.com/im-o
 **/

abstract class BaseDialogFragment<viewBinding : ViewBinding> : DialogFragment() {

    private val progressDialog: Dialog by lazy { setProgressDialog(requireContext()) }
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> viewBinding
    private var _binding: viewBinding? = null
    protected val binding: viewBinding
        get() = _binding as viewBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        dialog.setCancelable(false)
        return dialog
    }

    override fun show(fragmentManager: FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        initView()
        initObserver()
    }

    abstract fun initView()

    abstract fun initObserver()

    abstract fun showFailure(failure: DataResource.Failure)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}