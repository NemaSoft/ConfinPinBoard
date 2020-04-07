package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.datasources.impl.AnnouncementsDataSourceImpl
import com.coronapptilus.covidpinboard.datasources.impl.FavoritesDataSourceImpl
import com.coronapptilus.covidpinboard.repositories.datasources.AnnouncementsDataSource
import com.coronapptilus.covidpinboard.repositories.datasources.FavoritesDataSource
import org.koin.dsl.module

object DataSourcesModule {

    val dataSourcesModule = module {
        factory<AnnouncementsDataSource> { AnnouncementsDataSourceImpl() }
        factory<FavoritesDataSource> { FavoritesDataSourceImpl() }
    }
}