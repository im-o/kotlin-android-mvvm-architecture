package com.rivaldy.id.mvvmtemplateapp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.rivaldy.id.mvvmtemplateapp.R

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

object UtilExtensions {
    fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)
    }

    fun View.isVisible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun Context.myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun View.showSnackBar(message: String, action: (() -> Unit)? = null) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        action?.let {
            snackBar.setAction(context.getString(R.string.retry)) {
                it()
            }
        }
        snackBar.show()
    }
}