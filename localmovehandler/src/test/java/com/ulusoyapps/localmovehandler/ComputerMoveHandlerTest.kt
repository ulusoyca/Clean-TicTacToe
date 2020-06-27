package com.ulusoyapps.localmovehandler

import com.google.common.truth.Truth
import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.Moves
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import org.junit.Test

class ComputerMoveHandlerTest : BaseArchTest() {

    @Test
    fun `makeComputerMove as last move`() {
        val moves = Moves(
            playerMoves = listOf(
                Coordinate(0, 1),
                Coordinate(1, 0),
                Coordinate(1, 2),
                Coordinate(2, 1)
            ),
            computerMoves = listOf(
                Coordinate(0, 0),
                Coordinate(0, 2),
                Coordinate(1, 1),
                Coordinate(2, 0)
            )
        )
        val actualCoordinate = ComputerMoveHandler().makeComputerMove(moves)
        val expectedCoordinate = Coordinate(2, 2)
        Truth.assertThat(actualCoordinate).isEqualTo(expectedCoordinate)
    }
}