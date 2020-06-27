package com.ulusoyapps.tictactoe.datasource.datasources.playermove

import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import com.ulusoyapps.tictactoe.domain.repository.PlayerMoveRepository
import javax.inject.Inject

class PlayerMoveDataRepository
@Inject constructor(
    private val playerMoveLocalDataSource: PlayerMoveDataSource,
    private val gameStatusRepository: GameStatusRepository
) : PlayerMoveRepository {
    override suspend fun handlePlayerMove(coordinate: Coordinate): Resource<GameStatus> {
        return when (val currentGameStatusResource = gameStatusRepository.getGameStatus()) {
            is Success -> {
                if (currentGameStatusResource.data is InProgress) {
                    val currentMoves = (currentGameStatusResource.data as InProgress).moves
                    val updatedMoves = Moves(
                        playerMoves = currentMoves.playerMoves + coordinate,
                        computerMoves = currentMoves.computerMoves
                    )
                    val newGameStatusResource =
                        playerMoveLocalDataSource.handlePlayerMove(updatedMoves)
                    if (newGameStatusResource is Success) {
                        gameStatusRepository.saveGameStatus(gameStatus = newGameStatusResource.data)
                    }
                    newGameStatusResource
                } else {
                    Failure(Reason("There is no game in progress"))
                }
            }
            is Failure -> {
                Failure(Reason("Error on fetching current game status"))
            }
            else -> currentGameStatusResource
        }
    }
}