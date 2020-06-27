package com.ulusoyapps.tictactoe.datasource.datasources.playermove

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.datasource.datasources.gamestatus.GameStatusDataRepository
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayerMoveDataRepositoryTest : BaseArchTest() {

    private val playerMoveDataSource: PlayerMoveDataSource = mock()
    private val gameStatusDataRepository: GameStatusDataRepository = mock()
    private val playerMoveRepository =
        PlayerMoveDataRepository(playerMoveDataSource, gameStatusDataRepository)

    @Test
    fun `saveGameStatus is success`() = runBlocking {
        val expected = Success(InProgress(Moves()))
        val move = Coordinate(0, 0)
        whenever(playerMoveRepository.handlePlayerMove(move)).thenReturn(expected)
        val actual = playerMoveRepository.handlePlayerMove(move)

        verify(gameStatusDataRepository).saveGameStatus(expected.data)
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `saveGameStatus is failure`() = runBlocking {
        val expected = Failure(Reason("Something went wrong"))
        val move = Coordinate(0, 0)
        whenever(playerMoveRepository.handlePlayerMove(move)).thenReturn(expected)
        val actual = playerMoveRepository.handlePlayerMove(move)

        verifyZeroInteractions(gameStatusDataRepository)
        Truth.assertThat(actual).isEqualTo(expected)
    }
}