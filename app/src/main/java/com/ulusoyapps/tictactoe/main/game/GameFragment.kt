package com.ulusoyapps.tictactoe.main.game

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setMargins
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.databinding.FragmentGameBinding
import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.allCoordinates
import com.ulusoyapps.tictactoe.main.ui.TicTacToeBox
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.navigation_buttons.*
import timber.log.Timber

private const val GRID_SIZE = 3
private const val BLINK_ANIM_DURATION = 500L

class GameFragment : DaggerFragment() {

    private lateinit var binding: FragmentGameBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GameViewModel by viewModels { viewModelFactory }

    private var isComputerThinking = false

    private val idArray: Array<IntArray> = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    private val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            binding.scoreboard.visibility = View.GONE
            binding.animationView.visibility = View.GONE
            button_group.visibility = View.VISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }
    }

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
        addBoxesToGrid()
        setConstraintsForBoxes()
        configureNavigationButtons()
        binding.animationView.addAnimatorListener(animationListener)

        with(viewModel) {
            updateStatistics()
            getGameStatus()

            isComputerThinking.observe(viewLifecycleOwner, Observer {
                this@GameFragment.isComputerThinking = it
            })

            resultTextResId.observe(viewLifecycleOwner, Observer {
                binding.resultText.run {
                    visibility = View.VISIBLE
                    text = getString(it)
                }
            })

            computerMoves.observe(viewLifecycleOwner, Observer { coordinates ->
                coordinates.forEach {
                    findTicTacToeBox(it).run {
                        onComputerMove()
                        isClickable = false
                    }
                }
            })

            playerMoves.observe(viewLifecycleOwner, Observer { coordinates ->
                coordinates.forEach {
                    findTicTacToeBox(it).run {
                        onPlayerMove()
                        isClickable = false
                    }
                }
            })

            statistics.observe(viewLifecycleOwner, Observer { statistics ->
                binding.scoreboard.updateStatistics(statistics)
            })

            winningCombination.observe(viewLifecycleOwner, Observer { coordinates ->
                coordinates.forEach { coordinate ->
                    findTicTacToeBox(coordinate).let {
                        it.blink(BLINK_ANIM_DURATION)
                    }
                }
            })

            animationResId.observe(viewLifecycleOwner, Observer { animationResId ->
                binding.gridFrame.visibility = View.GONE
                binding.animationView.run {
                    setAnimation(animationResId)
                    visibility = View.VISIBLE
                    playAnimation()
                }
                binding.animationView.repeatCount
            })
        }
    }

    private fun configureNavigationButtons() {
        button_group.visibility = View.GONE
        top_button.run {
            text = getString(R.string.play_again)
            setOnClickListener { onNewGame() }
        }
        bottom_button.run {
            text = getString(R.string.back_home)
            setOnClickListener {
                findNavController().navigate(GameFragmentDirections.actionGameFragmentToHomeFragment())
            }
        }
    }

    private fun onNewGame() {
        gridFrame.visibility = View.VISIBLE
        scoreboard.visibility = View.VISIBLE
        button_group.visibility = View.GONE
        binding.resultText.visibility = View.GONE
        allCoordinates.forEach {
            findTicTacToeBox(it).setToInitialState()
        }
        viewModel.onNewGame()
    }

    private fun findTicTacToeBox(it: Coordinate) =
        binding.root.findViewById<TicTacToeBox>(idArray[it.row][it.column])

    private val ticTacToeBoxClickListener = View.OnClickListener {
        if (isComputerThinking) {
            Toast.makeText(context, getString(R.string.computer_is_thinking), Toast.LENGTH_LONG).show()
        } else {
            val ticTacToeBox = it as TicTacToeBox
            val coordinate = ticTacToeBox.coordinate
            ticTacToeBox.onPlayerMove()
            ticTacToeBox.isClickable = false
            Timber.d("Tic Tac Toe box clicked: $coordinate ")
            viewModel.onPlayerMove(coordinate)
        }
    }

    private fun setConstraintsForBoxes() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.setDimensionRatio(R.id.gridFrame, "$GRID_SIZE:$GRID_SIZE")
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                val id = idArray[i][j]
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
        var layoutParams: ConstraintLayout.LayoutParams
        var id: Int
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
                val box = TicTacToeBox(requireContext()).apply {
                    this.id = id
                    coordinate = Coordinate(row = i, column = j)
                }
                box.setOnClickListener(ticTacToeBoxClickListener)
                binding.root.addView(box, layoutParams)
            }
        }
    }
}
