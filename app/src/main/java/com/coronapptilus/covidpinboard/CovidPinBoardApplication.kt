package com.coronapptilus.covidpinboard

import android.app.Application
import com.coronapptilus.covidpinboard.di.DataSourcesModule.dataSourcesModule
import com.coronapptilus.covidpinboard.di.MappersModule.mappersModule
import com.coronapptilus.covidpinboard.di.NetworkModule.networkModule
import com.coronapptilus.covidpinboard.di.PresentersModule.presentersModule
import com.coronapptilus.covidpinboard.di.RepositoriesModule.repositoriesModule
import com.coronapptilus.covidpinboard.di.UseCasesModule.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidPinBoardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@CovidPinBoardApplication)
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