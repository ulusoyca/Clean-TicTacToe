package com.ulusoyapps.tictactoe.main.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.interactions.GetStatisticsUseCase
import com.ulusoyapps.tictactoe.domain.interactions.HandlePlayerMoveUseCase
import com.ulusoyapps.tictactoe.domain.interactions.SaveGameStatusUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(InternalCoroutinesApi::class)
class GameViewModel
@Inject constructor(
    private val handlePlayerMoveUseCase: HandlePlayerMoveUseCase,
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val saveGameStatusUseCase: SaveGameStatusUseCase
) : ViewModel() {

    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics>
        get() = _statistics

    private val _computerMove = MutableLiveData<Coordinate>()
    val computerMove: LiveData<Coordinate>
        get() = _computerMove

    private val _animationResId = MutableLiveData<Int>()
    val animationResId: LiveData<Int>
        get() = _animationResId

    fun onPlayerMove(coordinate: Coordinate) {
        viewModelScope.launch(Dispatchers.IO) {
            val gameStatusResource = handlePlayerMoveUseCase(coordinate)
            if (gameStatusResource is Success) {
                when (val status = gameStatusResource.data) {
                    is InProgress -> {
                        status.moves.computerMoves.forEach {
                            _computerMove.postValue(it)
                        }
                    }
                    NotStarted -> {
                        val errorMessage = "GameStatus can not be started after user plays"
                        Timber.e(errorMessage)
                        throw IllegalArgumentException(errorMessage)
                    }
                    PlayerWon -> {
                        updateStatistics()
                        _animationResId.postValue(R.raw.trophy)
                    }
                    PlayerLost -> {
                        updateStatistics()
                        _animationResId.postValue(R.raw.sad_emoji)
                        updateStatistics()
                    }
                    Draw -> {
                        updateStatistics()
                        _animationResId.postValue(R.raw.hand_shake)
                    }
                }
            }
        }
    }

    private suspend fun updateStatistics() {
        val statisticsResource = getStatisticsUseCase()
        if (statisticsResource is Success) {
            _statistics.postValue(statisticsResource.data)
        }
    }

    fun onNewNewGame() {
        viewModelScope.launch(Dispatchers.IO) {
            saveGameStatusUseCase(NotStarted)
            updateStatistics()
        }
    }
}
