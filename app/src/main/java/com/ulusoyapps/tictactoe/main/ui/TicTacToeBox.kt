package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate

class TicTacToeBox
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialButton(context, attrs) {

    var coordinate = Coordinate(0, 0)

    init {
        gravity = Gravity.CENTER
    }

    fun onComputerMove() {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
        icon = context.getDrawable(R.drawable.ic_cross)
    }

    fun onPlayerMove() {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.gray)
        icon = context.getDrawable(R.drawable.ic_circle)
    }

    fun setToInitialState() {
        isClickable = true
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.white)
    }

    fun blink(animDuration: Long) {
        val animation = AlphaAnimation(1f, 0f).apply {
            duration = animDuration // duration - half a second
            interpolator = LinearInterpolator() // do not alter animation rate
            repeatCount = 4
            repeatMode = Animation.REVERSE
        }
        startAnimation(animation)
    }
}
