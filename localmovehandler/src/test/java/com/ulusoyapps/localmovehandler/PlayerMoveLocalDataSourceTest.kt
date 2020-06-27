package com.ulusoyapps.localmovehandler

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayerMoveLocalDataSourceTest : BaseArchTest() {

    private val computerMoveHandler: ComputerMoveHandler = mock()
    private val playerMoveLocalDataSource = PlayerMoveLocalDataSource(computerMoveHandler)

    @Test
    fun `game should be in progress after player's first move`() = runBlocking {
        val playerMove = Coordinate(0, 0)
        val computerMove = Coordinate(0, 1)
        val moves = Moves(playerMoves = listOf(playerMove))

        val expected = Success(
            InProgress(
                Moves(
                    playerMoves = listOf(playerMove),
                    computerMoves = listOf(computerMove)
                )
            )
        )
        whenever(computerMoveHandler.makeComputerMove(moves)).thenReturn(computerMove)

        val actual = playerMoveLocalDataSource.handlePlayerMove(moves)
        Truth.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `player should win`() = runBlocking {
        val playerMoves = listOf(
            Coordinate(0, 0),
            Coordinate(1, 0),
            Coordinate(2, 2),
            Coordinate(1, 1)
        )
        val computerMoves = listOf(
            Coordinate(0, 1),
            Coordinate(1, 2),
            Coordinate(1, 2)
        )
        val moves = Moves(playerMoves, computerMoves)
        val expected = Success(PlayerWon)

        verifyZeroInteractions(computerMoveHandler)

        val actual = playerMoveLocalDataSource.handlePlayerMove(moves)
        Truth.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `should end as draw after player's move`() = runBlocking {
        val playerMoves = listOf(
            Coordinate(0, 0),
            Coordinate(0, 1),
            Coordinate(1, 2),
            Coordinate(2, 0),
            Coordinate(2, 2)
        )
        val computerMoves = listOf(
            Coordinate(0, 2),
            Coordinate(1, 0),
            Coordinate(1, 1),
            Coordinate(2, 1)
        )
        val moves = Moves(playerMoves, computerMoves)
        val expected = Success(Draw)

        verifyZeroInteractions(computerMoveHandler)

        val actual = playerMoveLocalDataSource.handlePlayerMove(moves)
        Truth.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `player should lose`() = runBlocking {
        val computerMoves = listOf(
            Coordinate(0, 0),
            Coordinate(1, 0),
            Coordinate(2, 2),
            Coordinate(1, 1)
        )
        val playerMoves = listOf(
            Coordinate(0, 1),
            Coordinate(1, 2),
            Coordinate(1, 2)
        )
        val moves = Moves(playerMoves, computerMoves)
        val expected = Success(PlayerLost)

        verifyZeroInteractions(computerMoveHandler)

        val actual = playerMoveLocalDataSource.handlePlayerMove(moves)
        Truth.assertThat(expected).isEqualTo(actual)
    }

}