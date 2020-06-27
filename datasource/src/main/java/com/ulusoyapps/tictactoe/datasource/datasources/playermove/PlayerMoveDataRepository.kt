package com.ulusoyapps.tictactoe.datasource.datasources.playermove

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Success
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import com.ulusoyapps.tictactoe.domain.repository.PlayerMoveRepository
import javax.inject.Inject

class PlayerMoveDataRepository
    @Inject constructor(
    private val playerMoveLocalDataSource: PlayerMoveDataSource,
    private val gameStatusRepository: GameStatusRepository
) : PlayerMoveRepository {
    override suspend fun handlePlayerMove(coordinate: Coordinate): Resource<GameStatus> {
        val gameStatusResource = playerMoveLocalDataSource.handlePlayerMove(coordinate)
        if (gameStatusResource is Success) {
            gameStatusRepository.saveGameStatus(gameStatus = gameStatusResource.data)
        }
        return gameStatusResource
    }
}