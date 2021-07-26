package com.rivaldy.id.mvvmtemplateapp.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.rivaldy.id.mvvmtemplateapp.R


object UtilDialog {

    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(true)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }
}