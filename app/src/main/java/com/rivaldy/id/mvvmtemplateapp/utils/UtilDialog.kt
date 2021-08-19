package com.rivaldy.id.mvvmtemplateapp.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
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

    fun setProgressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
        dialog.setContentView(inflate)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}