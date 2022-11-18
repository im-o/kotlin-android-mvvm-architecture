package com.rivaldy.id.mvvmtemplateapp.utils

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.LOG_MESSAGE
import java.io.ByteArrayOutputStream
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

object UtilFunctions {
    private val localeID = Locale("in", "ID")

    fun getTimestamp(): Long {
        return Calendar.getInstance().time.time
    }

    fun logE(message: String) {
        Log.e(LOG_MESSAGE, message)
    }

    fun openAlertDialog(context: Context, title: String?, msg: String?, listener: UtilListener.IDialogButtonClickListener) {
        val builder = AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
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

    fun showTimePickerEnable(context: Context, resultTimePicker: UtilListener.IResultTimePicker) {
        val calendar = Calendar.getInstance(localeID)
        val listener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            val sdf: DateFormat = SimpleDateFormat("HH:mm", localeID)
            val strHour = sdf.format(calendar.time)
            resultTimePicker.onTimePicker(strHour)
        }
        TimePickerDialog(context, listener, calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], false).show()
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

    fun editTextNumberReplace(editText: EditText): String {
        var text = editText.text.toString().replace(",", "").replace(".", "")
        if (text.isEmpty()) text = "0"
        return text
    }

    fun setToClipBoard(context: Context, strText: String?): String {
        val myClipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(context.resources.getString(R.string.copy_clip), strText)
        myClipboard.setPrimaryClip(clipData)
        var msg = context.resources.getString(R.string.copy_clip_success)
        if (strText?.length ?: 0 <= 20) msg = context.resources.getString(R.string.copy_clip, strText)
        return msg
    }

    fun dateFormatterNormal(calendar: Calendar): String {
        val sdf: DateFormat = SimpleDateFormat("EEEE, d MMMM yyy", localeID) // Sunday, 01 January 2021
        return sdf.format(calendar.time)
    }

    fun getCurrentTime(): String {
        val strFormat = "EEEE, d MMMM yyy HH:mm:ss"
        val dateFormat = SimpleDateFormat(strFormat, localeID)
        return dateFormat.format(Calendar.getInstance().time)
    }

    fun isStringNull(text: String?): String {
        return if (text != null) {
            if (text.isEmpty()) ""
            else text
        } else ""
    }

    fun isStringNullZero(text: String?): String {
        return if (text != null) {
            if (text.isEmpty()) "0"
            else text
        } else "0"
    }

    fun getDateTimeStamp(): Long {
        val strFormat = "ddMMyyyyhhmmSSS" // from this date -> 17/08/2020/02:15:474 to -> 170820200215474
        val dateFormat = SimpleDateFormat(strFormat, localeID)
        return dateFormat.format(Calendar.getInstance().time).toLong()
    }
}