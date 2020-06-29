package com.ulusoyapps.tictactoe.domain.entitiy

sealed class GameStatus
object NotStarted: GameStatus()
data class PlayerWon(val winningCoordinates: List<Coordinate>) : GameStatus()
data class PlayerLost(val winningCoordinates: List<Coordinate>) : GameStatus()
object Draw : GameStatus()
data class InProgress(val moves: Moves) : GameStatus()