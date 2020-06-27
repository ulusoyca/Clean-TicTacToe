package com.ulusoyapps.tictactoe.datasource.datasources.gamestatus

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import javax.inject.Inject

class GameStatusDataRepository
    @Inject constructor(
    private val gameStatusDataSource: GameStatusDataSource
) : GameStatusRepository {
    override suspend fun saveGameStatus(gameStatus: GameStatus): Resource<Unit> {
        return gameStatusDataSource.saveGameStatus(gameStatus)
    }

    override suspend fun getGameStatus(): Resource<GameStatus> {
        return gameStatusDataSource.getGameStatus()
    }
}