package com.ulusoyapps.tictactoe.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setMargins
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.databinding.ViewTicTacToeGridBinding
import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import timber.log.Timber
import kotlin.math.roundToInt

private const val GRID_SIZE = 3

class TicTacToeGrid
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewTicTacToeGridBinding = ViewTicTacToeGridBinding.inflate(LayoutInflater.from(context), this, true)

    private val idArray: Array<IntArray> = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    init {
        buildGrid()
    }

    // Taken from https://stackoverflow.com/a/51444884
    private fun buildGrid() {
        addBoxesToGrid()
        setConstraintsForBoxes()
    }

    private fun setConstraintsForBoxes() {
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
                    constraintSet.connect(
                        id,
                        ConstraintSet.TOP,
                        idArray[i - 1][0],
                        ConstraintSet.BOTTOM
                    )
                }
                // Create a horizontal chain that will determine the dimensions of our squares.
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

    /**
     * Adds 3x3 [TicTacToeBox] views to root
     */
    private fun addBoxesToGrid() {
        var layoutParams: LayoutParams
        var id: Int
        // Add our views to the ConstraintLayout
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                layoutParams = LayoutParams(
                    ConstraintSet.MATCH_CONSTRAINT,
                    ConstraintSet.MATCH_CONSTRAINT
                ).apply {
                    setMargins(resources.getDimension(R.dimen.size_spacing_xsmall).roundToInt())
                }
                id = View.generateViewId()
                idArray[i][j] = id
                val box = TicTacToeBox(context).apply {
                    this.id = id
                    coordinate = Coordinate(row = i, column = j)
                }
                box.setOnClickListener {
                    Timber.d("cgtya coordinate: ${(it as TicTacToeBox).coordinate} ")
                }
                binding.root.addView(box, layoutParams)
            }
        }
    }
}
