package com.ulusoyapps.tictactoe.domain.entitiy

data class Moves(
    val playerMoves: List<Coordinate> = emptyList(),
    val computerMoves: List<Coordinate> = emptyList()
)