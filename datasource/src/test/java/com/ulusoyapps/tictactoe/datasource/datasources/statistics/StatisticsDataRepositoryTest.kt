package com.ulusoyapps.tictactoe.datasource.datasources.statistics

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import com.ulusoyapps.tictactoe.unittesting.BaseArchTest
import kotlinx.coroutines.runBlocking

import org.junit.Test

class StatisticsDataRepositoryTest : BaseArchTest() {

    private val statisticsDataSource: StatisticsDataSource = mock()
    private val statisticsDataRepository = StatisticsDataRepository(statisticsDataSource)

    @Test
    fun `reset statistics is success`() = runBlocking {
        val expected = Success(Unit)
        whenever(statisticsDataRepository.resetStatistics()).thenReturn(expected)
        val actual = statisticsDataRepository.resetStatistics()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `saveGameStatus is failure`() = runBlocking {
        val expected = Failure(Reason("Something went wrong"))
        whenever(statisticsDataRepository.resetStatistics()).thenReturn(expected)
        val actual = statisticsDataRepository.resetStatistics()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `get statistics returns the statistics`() = runBlocking {
        val expected = Success(Statistics())
        whenever(statisticsDataRepository.getStatistics()).thenReturn(expected)
        val actual = statisticsDataRepository.getStatistics()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `getGameStatus returns failure`() = runBlocking {
        val expected = Failure(Reason("Something went wrong"))
        whenever(statisticsDataRepository.getStatistics()).thenReturn(expected)
        val actual = statisticsDataRepository.getStatistics()

        Truth.assertThat(actual).isEqualTo(expected)
    }
}