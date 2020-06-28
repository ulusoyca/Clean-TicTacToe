package com.ulusoyapps.tictactoe.main.home

import androidx.lifecycle.ViewModel
import com.ulusoyapps.tictactoe.FragmentScope
import com.ulusoyapps.tictactoe.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel
}
