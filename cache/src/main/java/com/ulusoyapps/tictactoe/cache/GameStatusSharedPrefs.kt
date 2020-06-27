package com.ulusoyapps.tictactoe.cache

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import com.ulusoyapps.tictactoe.cache.entity.CachedMovesJsonAdapter
import com.ulusoyapps.tictactoe.cache.mapper.MovesMapper
import com.ulusoyapps.tictactoe.datasource.datasources.gamestatus.GameStatusDataSource
import com.ulusoyapps.tictactoe.domain.entitiy.*
import javax.inject.Inject

private const val GAME_STATUS_SHARED_PREF = "GAME_STATUS_SHARED_PREF"
private const val MOVES_KEY = "moves"

class GameStatusSharedPrefs
@Inject constructor(
    private val context: Context,
    private val movesMapper: MovesMapper
) : GameStatusDataSource {

    private val adapter: CachedMovesJsonAdapter by lazy {
        CachedMovesJsonAdapter((Moshi.Builder().build()))
    }

    override suspend fun saveGameStatus(gameStatus: GameStatus): Resource<Unit> {
        if (gameStatus is InProgress) {
            val cachedMoves = movesMapper.mapFromDomainEntity(gameStatus.moves)
            val jsonString = adapter.toJson(cachedMoves)
            context.getSharedPreferences(
                GAME_STATUS_SHARED_PREF,
                Context.MODE_PRIVATE
            ).edit {
                putString(MOVES_KEY, jsonString)
            }
        }
        return Success(Unit)
    }

    @WorkerThread
    override suspend fun getGameStatus(): Resource<GameStatus> {
        val jsonString = context.getSharedPreferences(
            GAME_STATUS_SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(
            MOVES_KEY,
            ""
        )
        val cachedMoves = jsonString?.let { adapter.fromJson(it) }
        return if (cachedMoves == null) {
            Success(NotStarted)
        } else {
            Success(InProgress(movesMapper.mapToDomainEntity(cachedMoves)))
        }
    }
}