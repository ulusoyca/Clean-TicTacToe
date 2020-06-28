package com.ulusoyapps.tictactoe.cache.mapper

import com.ulusoyapps.tictactoe.cache.entity.CachedStatistics
import com.ulusoyapps.tictactoe.domain.entitiy.Statistics
import javax.inject.Inject

class StatisticsMapper
@Inject constructor() : EntityMapper<Statistics, CachedStatistics> {

    override fun mapFromDomainEntity(type: Statistics): CachedStatistics {
        return CachedStatistics(
            win = type.win,
            lose = type.lose,
            draw = type.draw
        )
    }

    override fun mapToDomainEntity(type: CachedStatistics): Statistics {
        return Statistics(
            win = type.win,
            lose = type.lose,
            draw = type.draw
        )
    }

    override fun mapFromDomainEntityList(type: List<Statistics>): List<CachedStatistics> {
        return type.map { mapFromDomainEntity(it) }
    }

    override fun mapToDomainEntityList(type: List<CachedStatistics>): List<Statistics> {
        return type.map { mapToDomainEntity(it) }
    }

}