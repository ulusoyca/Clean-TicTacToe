package com.ulusoyapps.tictactoe.datasource.datasources.playermove

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import javax.inject.Inject

class PlayerMoveLocalDataSource
@Inject constructor(
    private val playerMoveLocalDataSource: PlayerMoveDataSource
) {
    suspend fun handlePlayerMove(coordinate: Coordinate): Resource<GameStatus> {
        return playerMoveLocalDataSource.handlePlayerMove(coordinate)
    }
}