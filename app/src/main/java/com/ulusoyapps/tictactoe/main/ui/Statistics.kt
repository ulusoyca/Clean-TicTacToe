package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.databinding.ViewStatisticsBinding
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics

class Statistics
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewStatisticsBinding =
        ViewStatisticsBinding.inflate(LayoutInflater.from(context), this, true)

    fun updateStatistics(statistics: Statistics) {
        val lose = statistics.lose
        val win = statistics.win
        val draw = statistics.draw
        val total = draw + lose + win
        binding.drawNumber.text = draw.toString()
        binding.winNumber.text = win.toString()
        binding.loseNumber.text = lose.toString()
        binding.totalGame.text = context.getString(R.string.total_game, total)
    }
}
