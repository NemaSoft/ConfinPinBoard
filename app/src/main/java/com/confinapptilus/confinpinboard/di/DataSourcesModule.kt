package com.confinapptilus.confinpinboard.di

import android.content.Context
import com.confinapptilus.confinpinboard.datasources.impl.AnnouncementsDataSourceImpl
import com.confinapptilus.confinpinboard.datasources.impl.FavoritesDataSourceImpl
import com.confinapptilus.confinpinboard.repositories.datasources.AnnouncementsDataSource
import com.confinapptilus.confinpinboard.repositories.datasources.FavoritesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataSourcesModule {

    private const val PREFS_KEY = "favorites_shared_preferences"

    val dataSourcesModule = module {
        factory<AnnouncementsDataSource> { AnnouncementsDataSourceImpl(get(), get()) }
        factory<FavoritesDataSource> {
            FavoritesDataSourceImpl(
                androidContext().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE),
                get(),
                get()
            )
        }
    }
}