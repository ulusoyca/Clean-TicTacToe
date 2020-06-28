package com.ulusoyapps.tictactoe.main.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.interactions.GetStatisticsUseCase
import com.ulusoyapps.tictactoe.domain.interactions.HandlePlayerMoveUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(InternalCoroutinesApi::class)
class GameViewModel
@Inject constructor(
    private val handlePlayerMoveUseCase: HandlePlayerMoveUseCase,
    private val getStatisticsUseCase: GetStatisticsUseCase
) : ViewModel() {

    private val _gameStatus = MutableLiveData<GameStatus>()
    val gameStatus: LiveData<GameStatus>
        get() = _gameStatus

    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics>
        get() = _statistics

    fun onPlayerMove(coordinate: Coordinate) {
        viewModelScope.launch(Dispatchers.IO) {
            val gameStatusResource = handlePlayerMoveUseCase(coordinate)
            if (gameStatusResource is Success) {
                val status = gameStatusResource.data
                _gameStatus.postValue(status)
                if (status is PlayerWon || status is PlayerLost || status is Draw) {
                    updateStatistics()
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
}