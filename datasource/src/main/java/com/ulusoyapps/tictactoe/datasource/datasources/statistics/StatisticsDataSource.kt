package com.ulusoyapps.tictactoe.datasource.datasources.statistics

import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics

interface StatisticsDataSource {
    fun getStatistics(): Resource<Statistics>
    fun resetStatistics(): Resource<Unit>
}