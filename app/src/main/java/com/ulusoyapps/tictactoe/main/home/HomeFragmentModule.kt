package com.ulusoyapps.tictactoe.main.home

import com.ulusoyapps.tictactoe.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HomeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragment(): HomeFragment
}
