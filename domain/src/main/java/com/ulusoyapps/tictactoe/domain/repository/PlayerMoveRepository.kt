package com.ulusoyapps.tictactoe.domain.repository

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource

interface PlayerMoveRepository {
    suspend fun handlePlayerMove(coordinate: Coordinate): Resource<GameStatus>
    suspend fun getGameStatus(coordinate: Coordinate): Resource<GameStatus>
}