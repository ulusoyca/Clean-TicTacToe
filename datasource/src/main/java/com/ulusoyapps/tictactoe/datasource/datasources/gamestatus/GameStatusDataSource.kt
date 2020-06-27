package com.ulusoyapps.tictactoe.datasource.datasources.gamestatus

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Success

interface GameStatusDataSource {
    suspend fun saveGameStatus(gameStatus: GameStatus): Resource<Unit>
    suspend fun getGameStatus(): Resource<GameStatus>
}