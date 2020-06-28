package com.ulusoyapps.tictactoe.main.home

import com.ulusoyapps.tictactoe.FragmentScope
import com.ulusoyapps.tictactoe.main.statistics.StatisticsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HomeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindFragment(): HomeFragment
}
