package com.rivaldy.id.mvvmtemplateapp.utils

import com.google.android.gms.maps.model.LatLng
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

    interface IResultTimePicker {
        fun onTimePicker(time: String?)
    }

    interface ICheckedPermittedMap {
        fun onPermittedGranted()
        fun onPermittedDenied()
    }

    interface IMyLatLong {
        fun onLoadLatLong(latLng: LatLng?, isCurrentLocation: Boolean)
        fun onErrorLatLong()
    }
}