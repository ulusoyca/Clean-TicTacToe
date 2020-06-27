package com.ulusoyapps.tictactoe.datasource.datasources.gamestatus

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import javax.inject.Inject

class GameStatusFileDataSource
@Inject constructor(
    private val gameStatusFileDataSource: GameStatusDataSource
) {
    suspend fun saveGameStatus(gameStatus: GameStatus): Resource<Nothing> {
        return gameStatusFileDataSource.saveGameStatus(gameStatus)
    }

    suspend fun getGameStatus(): Resource<GameStatus> {
        return gameStatusFileDataSource.getGameStatus()
    }
}