package com.confinapptilus.confinpinboard

import android.app.Application
import com.confinapptilus.confinpinboard.di.PresentersModule.presentersModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            modules(presentersModule)
        }
    }
}
