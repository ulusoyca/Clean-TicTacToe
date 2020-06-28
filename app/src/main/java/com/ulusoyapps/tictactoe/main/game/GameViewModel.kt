package com.ulusoyapps.tictactoe.main.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.interactions.GetGameStatusUseCase
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
    private val saveGameStatusUseCase: SaveGameStatusUseCase,
    private val getGameStatusUseCase: GetGameStatusUseCase
) : ViewModel() {

    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics>
        get() = _statistics

    private val _playerMoves = MutableLiveData<List<Coordinate>>()
    val playerMoves: LiveData<List<Coordinate>>
        get() = _playerMoves

    private val _computerMoves = MutableLiveData<List<Coordinate>>()
    val computerMoves: LiveData<List<Coordinate>>
        get() = _computerMoves

    private val _animationResId = MutableLiveData<Int>()
    val animationResId: LiveData<Int>
        get() = _animationResId

    private val _resultTextResId = MutableLiveData<Int>()
    val resultTextResId: LiveData<Int>
        get() = _resultTextResId

    fun getGameStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            val gameStatusResource = getGameStatusUseCase()
            if (gameStatusResource is Success) {
                if (gameStatusResource.data is InProgress) {
                    val moves = (gameStatusResource.data as InProgress).moves
                    _playerMoves.postValue(moves.playerMoves)
                    _computerMoves.postValue(moves.computerMoves)
                } else {
                    onNewGame()
                }
            }
        }
    }

    fun onPlayerMove(coordinate: Coordinate) {
        viewModelScope.launch(Dispatchers.IO) {
            val gameStatusResource = handlePlayerMoveUseCase(coordinate)
            if (gameStatusResource is Success) {
                when (val status = gameStatusResource.data) {
                    is InProgress -> {
                        status.moves.computerMoves.forEach {
                            _computerMoves.postValue(listOf(it))
                        }
                    }
                    NotStarted -> {
                        val errorMessage = "GameStatus can not be started after user plays"
                        Timber.e(errorMessage)
                        throw IllegalArgumentException(errorMessage)
                    }
                    PlayerWon -> {
                        saveGameStatusUseCase(NotStarted)
                        updateStatistics()
                        _resultTextResId.postValue(R.string.you_won)
                        _animationResId.postValue(R.raw.trophy)
                    }
                    PlayerLost -> {
                        saveGameStatusUseCase(NotStarted)
                        updateStatistics()
                        _resultTextResId.postValue(R.string.you_lost)
                        _animationResId.postValue(R.raw.sad_emoji)
                        updateStatistics()
                    }
                    Draw -> {
                        saveGameStatusUseCase(NotStarted)
                        updateStatistics()
                        _resultTextResId.postValue(R.string.you_draw)
                        _animationResId.postValue(R.raw.hand_shake)
                    }
                }
            }
        }
    }

    fun updateStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            val statisticsResource = getStatisticsUseCase()
            if (statisticsResource is Success) {
                _statistics.postValue(statisticsResource.data)
            }
        }
    }

    fun onNewGame() {
        viewModelScope.launch(Dispatchers.IO) {
            saveGameStatusUseCase(NotStarted)
            updateStatistics()
        }
    }
}
