package com.ulusoyapps.tictactoe.datasource.datasources.gamestatus

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.runBlocking

import org.junit.Test

class GameStatusDataRepositoryTest : BaseArchTest() {

    private val gameStatusDataSource: GameStatusDataSource = mock()
    private val gameStatusDataRepository = GameStatusDataRepository(gameStatusDataSource)

    @Test
    fun `saveGameStatus is success`() = runBlocking {
        val expected = Success(Unit)
        whenever(gameStatusDataRepository.saveGameStatus(NotStarted)).thenReturn(expected)
        val actual = gameStatusDataRepository.saveGameStatus(NotStarted)

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `saveGameStatus is failure`() = runBlocking {
        val expected = Failure(Reason("Something went wrong"))
        whenever(gameStatusDataRepository.saveGameStatus(PlayerWon)).thenReturn(expected)
        val actual = gameStatusDataRepository.saveGameStatus(PlayerWon)

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `getGameStatus returns the game status`() = runBlocking {
        val expected = Success(PlayerLost)
        whenever(gameStatusDataRepository.getGameStatus()).thenReturn(expected)
        val actual = gameStatusDataRepository.getGameStatus()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `getGameStatus returns failure`() = runBlocking {
        val expected = Failure(Reason("Something went wrong"))
        whenever(gameStatusDataRepository.getGameStatus()).thenReturn(expected)
        val actual = gameStatusDataRepository.getGameStatus()

        Truth.assertThat(actual).isEqualTo(expected)
    }
}