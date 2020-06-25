package com.ulusoyapps.tictactoe.main.extensions

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.ulusoyapps.tictactoe.R

fun getStyledAppName(context: Context): SpannableString {
    val spannableAppName = SpannableString(context.getString(R.string.app_name))
    spannableAppName.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)),
        3,
        6,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    return spannableAppName
}
