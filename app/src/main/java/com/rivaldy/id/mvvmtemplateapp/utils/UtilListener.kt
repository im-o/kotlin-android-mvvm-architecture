package com.rivaldy.id.mvvmtemplateapp.utils

import java.util.*

/**
 * Created by rivaldy on 30/09/21
 * Find me on my Github -> https://github.com/im-o
 **/
interface UtilListener {

    interface IDialogButtonClickListener {
        fun onPositiveButtonClick()
        fun onNegativeButtonClick()
    }

    interface IResultDatePicker {
        fun onDatePicker(calendar: Calendar?)
    }
}