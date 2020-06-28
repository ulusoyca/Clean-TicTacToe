package com.ulusoyapps.tictactoe.domain.interactions

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetStatisticsUseCaseTest : BaseArchTest() {

    private val statisticsRepository: StatisticsRepository = mock()
    private val getStatisticsUseCase = GetStatisticsUseCase(statisticsRepository)

    @Test
    fun `should return statistics`() = runBlocking {
        val stats = Statistics()
        whenever(statisticsRepository.getStatistics()).thenReturn(Success(stats))

        val actualGameStatusResource = getStatisticsUseCase()
        val expectedGameStatusResource = Success(stats)

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }

    @Test
    fun `should return failure on statistics request`() = runBlocking {
        whenever(statisticsRepository.getStatistics()).thenReturn(Failure(Reason("Something went wrong")))

        val actualGameStatusResource = getStatisticsUseCase()
        val expectedGameStatusResource = Failure(Reason("Something went wrong"))

        Truth.assertThat(expectedGameStatusResource).isEqualTo(actualGameStatusResource)
    }
}