package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
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
        onComputerMove()
        onPlayerMove()
    }

    fun onComputerMove() {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
        icon = context.getDrawable(R.drawable.ic_cross)
    }

    fun onPlayerMove() {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.gray)
        icon = context.getDrawable(R.drawable.ic_circle)
    }
}
