package com.ulusoyapps.tictactoe.main.statistics

import com.ulusoyapps.tictactoe.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class StatisticsFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragment(): StatisticsFragment
}
