package com.ulusoyapps.tictactoe.domain.interactions

import com.ulusoyapps.tictactoe.domain.entitiy.Resource
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import javax.inject.Inject

class ResetStatisticsUseCase
@Inject constructor(
    private val statusRepository: StatisticsRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        return statusRepository.resetStatistics()
    }
}