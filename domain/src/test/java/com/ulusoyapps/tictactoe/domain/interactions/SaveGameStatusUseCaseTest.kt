package com.ulusoyapps.tictactoe.domain.interactions

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SaveGameStatusUseCaseTest : BaseArchTest() {

    private val gameStatusRepository: GameStatusRepository = mock()
    private val saveGameStatusUseCase = SaveGameStatusUseCase(gameStatusRepository)

    @Test
    fun `should save game status`() = runBlocking {
        val gameStatus = InProgress(Moves())
        whenever(gameStatusRepository.saveGameStatus(gameStatus)).thenReturn(Success(Unit))

        val actual = saveGameStatusUseCase(gameStatus)
        val expected = Success(Unit)

        Truth.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `should return failure on saving game status`() = runBlocking {
        whenever(gameStatusRepository.saveGameStatus(PlayerWon)).thenReturn(Failure(Reason("Something went wrong")))

        val actualGameStatusResource = saveGameStatusUseCase(PlayerWon)
        val expectedGameStatusResource = Failure(Reason("Something went wrong"))

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }
}