package com.ulusoyapps.localmovehandler

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.Moves
import javax.inject.Inject

class ComputerMoveHandler
@Inject constructor() {

    private val allCoordinates: List<Coordinate> = listOf(
        Coordinate(0, 0),
        Coordinate(0, 1),
        Coordinate(0, 2),
        Coordinate(1, 0),
        Coordinate(1, 1),
        Coordinate(1, 2),
        Coordinate(2, 0),
        Coordinate(2, 1),
        Coordinate(2, 2)
    )

    /**
     * When it is time for computer to make a move, we randomly choose a coordinate among available
     */
    fun makeComputerMove(moves: Moves): Coordinate {
        val reservedCoordinates = moves.computerMoves + moves.playerMoves
        val availableCoordinates = allCoordinates - reservedCoordinates
        return availableCoordinates.random()
    }
}