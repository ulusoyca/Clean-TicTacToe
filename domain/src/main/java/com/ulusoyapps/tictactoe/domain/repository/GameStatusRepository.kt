package com.ulusoyapps.tictactoe.domain.repository

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource

interface GameStatusRepository {
    suspend fun getGameStatus(): Resource<GameStatus>
    suspend fun saveGameStatus(gameStatus: GameStatus): Resource<Unit>
}