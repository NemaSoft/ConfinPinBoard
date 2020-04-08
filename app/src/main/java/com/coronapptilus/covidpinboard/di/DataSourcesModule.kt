package com.coronapptilus.covidpinboard.di

import android.content.Context
import com.coronapptilus.covidpinboard.datasources.impl.AnnouncementsDataSourceImpl
import com.coronapptilus.covidpinboard.datasources.impl.FavoritesDataSourceImpl
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource
import com.coronapptilus.covidpinboard.repositories.datasources.FavoritesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataSourcesModule {

    private const val PREFS_KEY = "favorites_shared_preferences"

    val dataSourcesModule = module {
        factory<AnnouncementsDataSource> { AnnouncementsDataSourceImpl() }
        factory<FavoritesDataSource> {
            FavoritesDataSourceImpl(
                androidContext().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE),
                get(),
                get()
            )
        }
    }
}