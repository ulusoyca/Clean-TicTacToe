package com.ulusoyapps.tictactoe.main.splash

import com.ulusoyapps.tictactoe.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class SplashFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragment(): SplashFragment
}
