package com.ulusoyapps.tictactoe.datasource.datasources.playermove

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource

interface PlayerMoveDataSource {
    suspend fun handlePlayerMove(coordinate: Coordinate): Resource<GameStatus>
}