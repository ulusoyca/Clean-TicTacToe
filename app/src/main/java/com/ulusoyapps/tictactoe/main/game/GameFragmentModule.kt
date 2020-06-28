package com.ulusoyapps.tictactoe.main.game

import com.ulusoyapps.tictactoe.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class GameFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [GameModule::class])
    abstract fun bindFragment(): GameFragment
}
