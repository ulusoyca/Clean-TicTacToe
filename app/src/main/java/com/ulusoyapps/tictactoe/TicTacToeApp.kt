package com.ulusoyapps.tictactoe

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class TicTacToeApp : DaggerApplication() {
    private val appComponent: AndroidInjector<TicTacToeApp> by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}
