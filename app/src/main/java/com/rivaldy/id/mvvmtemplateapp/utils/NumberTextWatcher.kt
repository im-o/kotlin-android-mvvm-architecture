package com.rivaldy.id.mvvmtemplateapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.ParseException

/**
 * Created by rivaldy on 06/08/21
 * Find me on my Github -> https://github.com/im-o
 **/

class NumberTextWatcher(et: EditText) : TextWatcher {
    private val df: DecimalFormat = DecimalFormat("#,###.##")
    private val dfNd: DecimalFormat
    private var hasFractionalPart: Boolean
    private val et: EditText
    override fun afterTextChanged(s: Editable) {
        et.removeTextChangedListener(this)
        try {
            val startLen: Int = et.text.length
            val v = s.toString().replace(df.decimalFormatSymbols.groupingSeparator.toString(), "")
            val n = df.parse(v)
            val cp = et.selectionStart
            if (hasFractionalPart) {
                et.setText(df.format(n))
            } else {
                et.setText(dfNd.format(n))
            }
            val endLen: Int = et.text.length
            val sel = cp + (endLen - startLen)
            if (sel > 0 && sel <= et.text.length) {
                et.setSelection(sel)
            } else {
                // place cursor at the end?
                et.setSelection(et.text.length - 1)
            }
        } catch (nfe: NumberFormatException) {
            // do nothing?
        } catch (nfe: ParseException) {
        }
        et.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        hasFractionalPart = s.toString().contains(df.decimalFormatSymbols.decimalSeparator.toString())
    }

    init {
        df.isDecimalSeparatorAlwaysShown = true
        dfNd = DecimalFormat("#,###")
        this.et = et
        hasFractionalPart = false
    }
}