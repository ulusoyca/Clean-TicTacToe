package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.ulusoyapps.tictactoe.R

class AppNameTextView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        val spannableAppName = SpannableString(context.getString(R.string.app_name))
        spannableAppName.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)),
            3,
            6,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        text = spannableAppName
    }
}
