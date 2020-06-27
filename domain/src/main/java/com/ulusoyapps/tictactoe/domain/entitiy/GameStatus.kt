package com.ulusoyapps.tictactoe.domain.entitiy

sealed class GameStatus
object NotStarted: GameStatus()
object PlayerWon : GameStatus()
object PlayerLost : GameStatus()
object Draw : GameStatus()
data class InProgress(val moves: Moves) : GameStatus()