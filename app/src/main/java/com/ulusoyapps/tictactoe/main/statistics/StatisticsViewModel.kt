package com.ulusoyapps.tictactoe.main.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulusoyapps.tictactoe.domain.entitiy.*
import com.ulusoyapps.tictactoe.domain.interactions.GetStatisticsUseCase
import com.ulusoyapps.tictactoe.domain.interactions.ResetStatisticsUseCase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
class StatisticsViewModel
@Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val resetStatisticsUseCase: ResetStatisticsUseCase
) : ViewModel() {

    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics>
        get() = _statistics

    fun updateStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            val statisticsResource = getStatisticsUseCase()
            if (statisticsResource is Success) {
                _statistics.postValue(statisticsResource.data)
            }
        }
    }

    fun resetStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            resetStatisticsUseCase()
            updateStatistics()
        }
    }
}
