package com.ulusoyapps.tictactoe.datasource.datasources.statistics

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Moves
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics
import javax.inject.Inject

class LocalStatisticsDataSource
@Inject constructor(
    private val localStatisticsDataSource: LocalStatisticsDataSource
) {
    suspend fun resetStatistics(): Resource<Unit> {
        return localStatisticsDataSource.resetStatistics()
    }

    suspend fun getStatistics(): Resource<Statistics> {
        return localStatisticsDataSource.getStatistics()
    }
}