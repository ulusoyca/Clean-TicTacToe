package com.ulusoyapps.localmovehandler

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.Moves
import com.ulusoyapps.tictactoe.domain.entitiy.allCoordinates
import javax.inject.Inject

class ComputerMoveHandler
@Inject constructor() {
    /**
     * When it is time for computer to make a move, we randomly choose a coordinate among available
     */
    fun makeComputerMove(moves: Moves): Coordinate {
        val reservedCoordinates = moves.computerMoves + moves.playerMoves
        val availableCoordinates = allCoordinates - reservedCoordinates
        return availableCoordinates.random()
    }
}