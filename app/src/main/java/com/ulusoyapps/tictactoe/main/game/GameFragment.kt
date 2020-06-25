package com.ulusoyapps.tictactoe.main.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setMargins
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.databinding.FragmentGameBinding
import com.ulusoyapps.tictactoe.main.entity.Coordinate
import com.ulusoyapps.tictactoe.main.ui.TicTacToeBox
import dagger.android.support.DaggerFragment
import kotlin.math.roundToInt

private const val GRID_SIZE = 3

class GameFragment : DaggerFragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Taken from https://stackoverflow.com/a/51444884
        var layoutParams: ConstraintLayout.LayoutParams
        var id: Int
        val idArray: Array<IntArray> = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )

        // Add our views to the ConstraintLayout
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintSet.MATCH_CONSTRAINT,
                    ConstraintSet.MATCH_CONSTRAINT
                ).apply {
                    setMargins(resources.getDimension(R.dimen.size_spacing_xsmall).roundToInt())
                }
                id = View.generateViewId()
                idArray[i][j] = id
                val button = TicTacToeBox(requireContext()).apply {
                    this.id = id
                    coordinate = Coordinate(row = i, column = j)
                }
                binding.root.addView(button, layoutParams)
            }
        }

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.setDimensionRatio(R.id.gridFrame, "$GRID_SIZE:$GRID_SIZE")
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                id = idArray[i][j]
                constraintSet.setDimensionRatio(id, "1:1")
                if (i == 0) {
                    // Connect the top row to the top of the frame.
                    constraintSet.connect(id, ConstraintSet.TOP, R.id.gridFrame, ConstraintSet.TOP)
                } else {
                    // Connect top to bottom of row above.
                    constraintSet.connect(id, ConstraintSet.TOP, idArray[i - 1][0], ConstraintSet.BOTTOM)
                }
                // Create a horiontal chain that will determine the dimensions of our squares.
                // Could also be createHorizontalChainRtl() with START/END.
                constraintSet.createHorizontalChain(
                    R.id.gridFrame,
                    ConstraintSet.LEFT,
                    R.id.gridFrame,
                    ConstraintSet.RIGHT,
                    idArray[i],
                    null,
                    ConstraintSet.CHAIN_PACKED
                )
            }
        }
        constraintSet.applyTo(binding.root)
    }
}
