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
    private val getGameStatusUseCase = GetGameStatusUseCase(gameStatusRepository)

    @Test
    fun `should return game status`() = runBlocking {
        val gameStatus = InProgress(Moves())
        whenever(gameStatusRepository.saveGameStatus(gameStatus)).thenReturn(Success(Unit))

        val actualGameStatusResource = getGameStatusUseCase()
        val expectedGameStatusResource = Success(gameStatus)

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }

    @Test
    fun `should return failure after player move`() = runBlocking {
        whenever(gameStatusRepository.getGameStatus()).thenReturn(Failure(Reason("Something went wrong")))

        val actualGameStatusResource = getGameStatusUseCase()
        val expectedGameStatusResource = Failure(Reason("Something went wrong"))

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }
}