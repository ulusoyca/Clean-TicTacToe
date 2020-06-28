package com.ulusoyapps.tictactoe.datasource.datasources.statistics

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import javax.inject.Inject

class StatisticsDataRepository
    @Inject constructor(
    private val statisticsDataSource: StatisticsDataSource
) : StatisticsRepository {
    override suspend fun getStatistics(): Resource<Statistics> {
        return statisticsDataSource.getStatistics()
    }

    override suspend fun resetStatistics(): Resource<Unit> {
        return statisticsDataSource.resetStatistics()
    }

}