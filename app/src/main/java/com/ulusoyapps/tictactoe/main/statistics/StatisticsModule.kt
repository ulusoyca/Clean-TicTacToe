package com.ulusoyapps.tictactoe.main.statistics

import androidx.lifecycle.ViewModel
import com.ulusoyapps.tictactoe.FragmentScope
import com.ulusoyapps.tictactoe.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StatisticsModule {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    abstract fun bindViewModel(viewModel: StatisticsViewModel): ViewModel
}
