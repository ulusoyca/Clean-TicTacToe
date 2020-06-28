package com.ulusoyapps.tictactoe.domain.interactions

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.Failure
import com.ulusoyapps.tictactoe.domain.entitiy.Reason
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics
import com.ulusoyapps.tictactoe.domain.entitiy.Success
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.runBlocking

import org.junit.Assert.*
import org.junit.Test

class ResetStatisticsUseCaseTest : BaseArchTest() {

    private val statisticsRepository: StatisticsRepository = mock()
    private val getStatisticsUseCase = ResetStatisticsUseCase(statisticsRepository)

    @Test
    fun `should reset statistics`() = runBlocking {
        whenever(statisticsRepository.resetStatistics()).thenReturn(Success(Unit))

        val actualGameStatusResource = getStatisticsUseCase()
        val expectedGameStatusResource = Success(Unit)

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }

    @Test
    fun `should return failure on statistics reset request`() = runBlocking {
        whenever(statisticsRepository.resetStatistics()).thenReturn(Failure(Reason("Something went wrong")))

        val actualGameStatusResource = getStatisticsUseCase()
        val expectedGameStatusResource = Failure(Reason("Something went wrong"))

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }
}