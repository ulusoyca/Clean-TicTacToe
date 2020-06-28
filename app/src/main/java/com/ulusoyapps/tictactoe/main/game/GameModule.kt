package com.ulusoyapps.tictactoe.main.game

import androidx.lifecycle.ViewModel
import com.ulusoyapps.tictactoe.FragmentScope
import com.ulusoyapps.tictactoe.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GameModule {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindViewModel(viewModel: GameViewModel): ViewModel
}
