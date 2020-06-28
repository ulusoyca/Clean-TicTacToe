package com.ulusoyapps.tictactoe.domain.interactions

import com.ulusoyapps.tictactoe.domain.entitiy.GameStatus
import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import javax.inject.Inject

class GetStatisticsUseCase
@Inject constructor(
    private val statusRepository: StatisticsRepository
) {
    suspend operator fun invoke(): Resource<Statistics> {
        return statusRepository.getStatistics()
    }
}