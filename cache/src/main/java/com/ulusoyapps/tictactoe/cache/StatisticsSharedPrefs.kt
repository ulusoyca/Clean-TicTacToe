package com.ulusoyapps.tictactoe.cache

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import com.ulusoyapps.tictactoe.cache.entity.CachedStatistics
import com.ulusoyapps.tictactoe.cache.entity.CachedStatisticsJsonAdapter
import com.ulusoyapps.tictactoe.cache.mapper.StatisticsMapper
import com.ulusoyapps.tictactoe.datasource.datasources.statistics.StatisticsDataSource
import com.ulusoyapps.tictactoe.domain.entitiy.*
import javax.inject.Inject

private const val STATISTICS_SHARED_PREF = "STATISTICS_SHARED_PREF"
private const val STATISTICS_KEY = "statistics"

class StatisticsSharedPrefs
@Inject constructor(
    private val context: Context,
    private val statisticsMapper: StatisticsMapper
) : StatisticsDataSource {

    private val adapter: CachedStatisticsJsonAdapter by lazy {
        CachedStatisticsJsonAdapter((Moshi.Builder().build()))
    }

    @WorkerThread
    override fun getStatistics(): Resource<Statistics> {
        val jsonString = context.getSharedPreferences(
            STATISTICS_SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(
            STATISTICS_KEY,
            ""
        )
        val cachedStatistics = jsonString?.let { adapter.fromJson(it) }
        return if (cachedStatistics == null) {
            Failure(Reason("Statistics not available"))
        } else {
            Success(statisticsMapper.mapToDomainEntity(cachedStatistics))
        }
    }

    @WorkerThread
    fun saveStatistics(cachedStatistics: CachedStatistics): Resource<Unit> {
        val jsonString = adapter.toJson(cachedStatistics)
        context.getSharedPreferences(
            STATISTICS_SHARED_PREF,
            Context.MODE_PRIVATE
        ).edit {
            putString(STATISTICS_KEY, jsonString)
        }
        return Success(Unit)
    }

    override fun resetStatistics(): Resource<Unit> {
        return saveStatistics(CachedStatistics())
    }
}