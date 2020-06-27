package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ulusoyapps.tictactoe.databinding.ViewScoreboardBinding

class Scoreboard
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewScoreboardBinding =
        ViewScoreboardBinding.inflate(LayoutInflater.from(context), this, true)
}
