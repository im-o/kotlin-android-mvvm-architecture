package com.rivaldy.id.mvvmtemplateapp.utils

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.LOG_MESSAGE
import java.io.ByteArrayOutputStream
import java.text.NumberFormat
import java.util.*

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

object UtilFunctions {
    val localeID = Locale("in", "ID")

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

    fun formatRupiahFloat(rupiah: Float): String? {
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(rupiah)
    }

    fun formatRupiah(original: String?): String {
        val strValue: String
        if (original == "") {
            return ""
        }
        val originalReplace = original?.replace(".00", "")
        val myBalance = originalReplace?.toLong() ?: 0F
        val total = "Rp "
        val valueRp = NumberFormat.getNumberInstance(localeID).format(myBalance).replace(",", ".")
        strValue = total + valueRp
        return strValue
    }

    fun formatRupiahNoRp(original: String?): String {
        val strValue: String
        if (original == "") {
            return ""
        }
        val originalReplace = original?.replace(".00", "")
        val myBalance = originalReplace?.toLong() ?: 0F
        strValue = NumberFormat.getNumberInstance(localeID).format(myBalance).replace(",", ".")
        return strValue
    }

    fun encodeImageBase64(bitmap: Bitmap): String {
        val byteArr = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArr)
        val byte = byteArr.toByteArray()
        return Base64.encodeToString(byte, Base64.DEFAULT)
    }

    fun decodeImageBase64(base64: String): Bitmap {
        val decodedString = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun showDatePickerDisable(context: Context?, resultDatePicker: UtilListener.IResultDatePicker): DatePickerDialog {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]
        val datePicker = DatePickerDialog(
            context!!,
            { _: DatePicker?, year1: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar[year1, monthOfYear] = dayOfMonth
                resultDatePicker.onDatePicker(calendar)
            }, year, month, day
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        return datePicker
    }

    fun showDatePickerEnable(context: Context?, resultDatePicker: UtilListener.IResultDatePicker): DatePickerDialog {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]
        return DatePickerDialog(
            context!!,
            { _: DatePicker?, year1: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar[year1, monthOfYear] = dayOfMonth
                resultDatePicker.onDatePicker(calendar)
            }, year, month, day
        )
    }
}