package com.ulusoyapps.tictactoe.domain.repository

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics

interface StatisticsRepository {
    suspend fun getStatistics(): Resource<Statistics>
    suspend fun resetStatistics(): Resource<Unit>
}