package com.example.cointrack

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinTrackApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CoinTrackApplication)
            modules(listOf(repositoryModule, viewModelModule, apiModule))
        }
    }
}