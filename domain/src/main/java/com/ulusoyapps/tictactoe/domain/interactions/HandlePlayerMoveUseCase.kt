package com.ulusoyapps.tictactoe.domain.interactions

import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.repository.PlayerMoveRepository
import javax.inject.Inject

class HandlePlayerMoveUseCase
@Inject constructor(
    private val playerMoveRepository: PlayerMoveRepository
) {
    suspend operator fun invoke(coordinate: Coordinate): Resource<GameStatus> {
        return playerMoveRepository.handlePlayerMove(coordinate)
    }
}