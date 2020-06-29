package com.ulusoyapps.tictactoe.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.interactions.GetGameStatusUseCase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
class HomeViewModel
@Inject constructor(
    private val getGameStatusUseCase: GetGameStatusUseCase
) : ViewModel() {

    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics>
        get() = _statistics

    private val _ongoingGame = MutableLiveData<Boolean>()
    val ongoingGame: LiveData<Boolean>
        get() = _ongoingGame

    fun getGameStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            val gameStatusResource = getGameStatusUseCase()
            if (gameStatusResource is Success) {
                if (gameStatusResource.data is InProgress) {
                    if (isEmptyGrid((gameStatusResource.data as InProgress).moves)) {
                        _ongoingGame.postValue(false)
                    } else {
                        _ongoingGame.postValue(true)
                    }
                } else {
                    _ongoingGame.postValue(false)
                }
            }
        }
    }

    private fun isEmptyGrid(moves: Moves): Boolean {
        return moves.computerMoves.isEmpty() && moves.playerMoves.isEmpty()
    }
}
