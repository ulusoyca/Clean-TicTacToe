package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ulusoyapps.tictactoe.databinding.ViewScoreboardBinding
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics

class Scoreboard
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewScoreboardBinding =
        ViewScoreboardBinding.inflate(LayoutInflater.from(context), this, true)

    fun updateStatistics(statistics: Statistics) {
        val lose = statistics.lose
        val win = statistics.win
        val total = statistics.draw + lose + win
        binding.numberOfLose.text = lose.toString()
        binding.numberOfWins.text = win.toString()
        binding.numberOfGames.text = total.toString()
    }
}
