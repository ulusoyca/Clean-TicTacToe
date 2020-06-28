package com.ulusoyapps.localmovehandler

import com.ulusoyapps.tictactoe.datasource.datasources.playermove.PlayerMoveDataSource
import com.ulusoyapps.tictactoe.domain.entitiy.*
import kotlinx.coroutines.delay
import javax.inject.Inject

private const val FAKE_DELAY_BEFORE_COMPUTER_MOVE = 1000L

/**
 * Very basic logic for TicTacToe. This logic is based on the following requirements:
 * 1- The game always starts with player's move
 * 2- After player's move, there is one second fake delay and computer makes a move
 * 3- Computer's move is randomly chosen based on available coordinates
 */
class LocalPlayerMoveHandler
@Inject constructor(
    private val computerMoveHandler: ComputerMoveHandler
) : PlayerMoveDataSource {

    private val winningCombinations = listOf(
        // Rows
        listOf(Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2)),
        listOf(Coordinate(1, 0), Coordinate(1, 1), Coordinate(1, 2)),
        listOf(Coordinate(2, 0), Coordinate(2, 1), Coordinate(0, 2)),
        // Columns
        listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(2, 0)),
        listOf(Coordinate(0, 1), Coordinate(1, 1), Coordinate(2, 1)),
        listOf(Coordinate(0, 2), Coordinate(1, 2), Coordinate(2, 2)),
        // Diagonals
        listOf(Coordinate(0, 0), Coordinate(1, 1), Coordinate(2, 2)),
        listOf(Coordinate(2, 2), Coordinate(1, 1), Coordinate(0, 0))
    )

    override suspend fun handlePlayerMove(moves: Moves): Resource<GameStatus> {
        val playerMoves = moves.playerMoves
        val computerMoves = moves.computerMoves

        // Check if the player's new move, resulted player win
        if (isWin(playerMoves)) {
            return Success(PlayerWon)
        }

        if (isDraw(playerMoves, computerMoves)) {
            return Success(Draw)
        }

        delay(FAKE_DELAY_BEFORE_COMPUTER_MOVE)
        val newComputerMove = computerMoveHandler.makeComputerMove(moves)
        val updatedComputerMoves = moves.computerMoves + newComputerMove
        if (isWin(updatedComputerMoves)) {
            return Success(PlayerLost)
        }

        if (isDraw(playerMoves, computerMoves)) {
            return Success(Draw)
        }

        return Success(InProgress(Moves(playerMoves, updatedComputerMoves)))
    }

    /**
     * When the total number of moves equals to 3x3=9, the game is draw
     */
    private fun isDraw(
        playerMoves: List<Coordinate>,
        computerMoves: List<Coordinate>
    ) = playerMoves.size + computerMoves.size == 9

    private fun isWin(moves: List<Coordinate>): Boolean {
        return winningCombinations.any { winningCombination -> moves.containsAll(winningCombination) }
    }
}