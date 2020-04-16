package com.confinapptilus.confinpinboard

import android.app.Application
import com.confinapptilus.confinpinboard.di.DataSourcesModule.dataSourcesModule
import com.confinapptilus.confinpinboard.di.MappersModule.mappersModule
import com.confinapptilus.confinpinboard.di.NetworkModule.networkModule
import com.confinapptilus.confinpinboard.di.PresentersModule.presentersModule
import com.confinapptilus.confinpinboard.di.RepositoriesModule.repositoriesModule
import com.confinapptilus.confinpinboard.di.UseCasesModule.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConfinPinBoardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ConfinPinBoardApplication)
            modules(
                arrayListOf(
                    presentersModule,
                    useCasesModule,
                    repositoriesModule,
                    mappersModule,
                    dataSourcesModule,
                    networkModule
                )
            )
        }
    }
}