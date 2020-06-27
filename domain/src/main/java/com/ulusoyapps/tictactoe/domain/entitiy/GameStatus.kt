package com.ulusoyapps.tictactoe.domain.entitiy

sealed class GameStatus
object NotStarted: GameStatus()
object PlayerWon : GameStatus()
object PlayerLost : GameStatus()
object Draw : GameStatus()
class InProgress(val moves: Moves) : GameStatus()