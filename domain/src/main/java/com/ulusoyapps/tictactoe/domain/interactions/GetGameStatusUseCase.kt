package com.ulusoyapps.tictactoe.domain.interactions

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import com.ulusoyapps.tictactoe.domain.repository.PlayerMoveRepository
import javax.inject.Inject

class GetGameStatusUseCase
@Inject constructor(
    private val gameStatusRepository: GameStatusRepository
) {
    suspend operator fun invoke(): Resource<GameStatus> {
        return gameStatusRepository.getGameStatus()
    }
}