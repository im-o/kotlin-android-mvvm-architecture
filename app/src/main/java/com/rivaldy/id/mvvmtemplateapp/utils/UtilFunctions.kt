package com.rivaldy.id.mvvmtemplateapp.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.LOG_MESSAGE

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

object UtilFunctions {
    fun loge(message: String) {
        Log.e(LOG_MESSAGE, message)
    }

    fun openAlertDialog(context: Context, title: String?, msg: String?, listener: UtilListener.IDialogButtonClickListener) {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle(title)
            .setMessage(msg)
            //.setIcon(R.drawable.ic_baseline_info_24)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                listener.onPositiveButtonClick()
            }.setNegativeButton(android.R.string.cancel) { _, _ ->
                listener.onNegativeButtonClick()
            }
        builder.create().show()
    }
}