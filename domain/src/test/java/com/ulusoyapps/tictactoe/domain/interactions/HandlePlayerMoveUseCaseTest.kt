package com.ulusoyapps.tictactoe.domain.interactions

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.repository.PlayerMoveRepository
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HandlePlayerMoveUseCaseTest : BaseArchTest() {

    private val playerMoveRepository: PlayerMoveRepository = mock()
    private val handlePlayerMoveUseCase = HandlePlayerMoveUseCase(playerMoveRepository)
    private val playerMove = Coordinate(0, 0)

    @Test
    fun `should return game status after player move`() = runBlocking {
        val gameStatus = InProgress(Moves(playerMoves = listOf(playerMove)))
        whenever(playerMoveRepository.handlePlayerMove(playerMove)).thenReturn(Success(gameStatus))

        val actualGameStatusResource = handlePlayerMoveUseCase(playerMove)
        val expectedGameStatusResource = Success(gameStatus)

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }

    @Test
    fun `should return failure after player move`() = runBlocking {
        whenever(playerMoveRepository.handlePlayerMove(playerMove)).thenReturn(Failure(Reason("Something went wrong")))

        val actualGameStatusResource = handlePlayerMoveUseCase(playerMove)
        val expectedGameStatusResource = Failure(Reason("Something went wrong"))

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }
}