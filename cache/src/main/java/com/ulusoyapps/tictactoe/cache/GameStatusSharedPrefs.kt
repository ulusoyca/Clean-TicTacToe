package com.ulusoyapps.tictactoe.cache

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import com.ulusoyapps.tictactoe.cache.entity.CachedMovesJsonAdapter
import com.ulusoyapps.tictactoe.cache.entity.CachedStatistics
import com.ulusoyapps.tictactoe.cache.mapper.MovesMapper
import com.ulusoyapps.tictactoe.datasource.datasources.gamestatus.GameStatusDataSource
import com.ulusoyapps.tictactoe.domain.entitiy.*
import javax.inject.Inject

private const val GAME_STATUS_SHARED_PREF = "GAME_STATUS_SHARED_PREF"
private const val MOVES_KEY = "moves"

class GameStatusSharedPrefs
@Inject constructor(
    private val context: Context,
    private val movesMapper: MovesMapper,
    private val statisticsSharedPrefs: StatisticsSharedPrefs
) : GameStatusDataSource {

    private val adapter: CachedMovesJsonAdapter by lazy {
        CachedMovesJsonAdapter((Moshi.Builder().build()))
    }

    @WorkerThread
    override suspend fun saveGameStatus(gameStatus: GameStatus): Resource<Unit> {
        return when (gameStatus) {
            is InProgress -> updateMoves(gameStatus.moves)
            is PlayerWon -> updateWonStatistics()
            is PlayerLost -> updateLostStatistics()
            is Draw -> updateDrawStatistics()
            is NotStarted -> setMovesToGameBeginningState()
        }
    }

    private fun setMovesToGameBeginningState(): Resource<Unit> {
        updateMoves(
            Moves(
                playerMoves = emptyList(),
                computerMoves = emptyList()
            )
        )
        return Success(Unit)
    }

    @WorkerThread
    override suspend fun getGameStatus(): Resource<GameStatus> {
        val jsonString = getFromSharedPrefs()
        return if (jsonString == null) {
            Failure(Reason("Could not read shared preference file"))
        } else {
            return if (jsonString.isEmpty()) {
                // This is the first time reading shared preferences. No game is yet played.
                setMovesToGameBeginningState()
                Success(NotStarted)
            } else {
                // There is an ongoing game
                val cachedMoves = jsonString.let { adapter.fromJson(it) }
                if (cachedMoves == null) {
                    Failure(Reason("Could not read current moves"))
                } else {
                    Success(InProgress(movesMapper.mapToDomainEntity(cachedMoves)))
                }
            }
        }
    }

    private fun updateMoves(moves: Moves): Resource<Unit> {
        val cachedMoves = movesMapper.mapFromDomainEntity(moves)
        saveToSharedPrefs(adapter.toJson(cachedMoves))
        return Success(Unit)
    }

    private fun updateDrawStatistics(): Resource<Unit> {
        val currentStats = statisticsSharedPrefs.getStatistics()
        return if (currentStats is Success) {
            val updatesStats = CachedStatistics(
                win = currentStats.data.win,
                lose = currentStats.data.lose,
                draw = currentStats.data.draw + 1
            )
            statisticsSharedPrefs.saveStatistics(updatesStats)
        } else {
            Failure(Reason("Error updating game statistics"))
        }
    }

    private fun updateLostStatistics(): Resource<Unit> {
        val currentStats = statisticsSharedPrefs.getStatistics()
        return if (currentStats is Success) {
            val updatesStats = CachedStatistics(
                win = currentStats.data.win,
                lose = currentStats.data.lose + 1,
                draw = currentStats.data.draw
            )
            statisticsSharedPrefs.saveStatistics(updatesStats)
        } else {
            Failure(Reason("Error updating game statistics"))
        }
    }

    private fun updateWonStatistics(): Resource<Unit> {
        val currentStats = statisticsSharedPrefs.getStatistics()
        return if (currentStats is Success) {
            val updatesStats = CachedStatistics(
                win = currentStats.data.win + 1,
                lose = currentStats.data.lose,
                draw = currentStats.data.draw
            )
            statisticsSharedPrefs.saveStatistics(updatesStats)
        } else {
            Failure(Reason("Error updating game statistics"))
        }
    }

    private fun getFromSharedPrefs(): String? {
        return context.getSharedPreferences(
            GAME_STATUS_SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(
            MOVES_KEY,
            ""
        )
    }

    private fun saveToSharedPrefs(jsonString: String?) {
        context.getSharedPreferences(
            GAME_STATUS_SHARED_PREF,
            Context.MODE_PRIVATE
        ).edit {
            putString(MOVES_KEY, jsonString)
        }
    }
}