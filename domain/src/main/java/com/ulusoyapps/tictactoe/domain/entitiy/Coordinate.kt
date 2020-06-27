package com.ulusoyapps.tictactoe.domain.entitiy

import androidx.annotation.IntRange

/**
 * A Coordinate on a 3x3 TicTacToe grid table:
 *
 *  (0,0) | (0,1) | (0,2)
 *  (1,0) | (1,1) | (1,2)
 *  (2,0) | (2,1) | (2,2)
 */
data class Coordinate(
    @IntRange(from = 0, to = 2) val row: Int,
    @IntRange(from = 0, to = 2) val column: Int
)
