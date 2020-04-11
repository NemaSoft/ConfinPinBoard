package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.datasources.mappers.AnnouncementMapper
import com.coronapptilus.covidpinboard.datasources.mappers.FavoritesMapper
import org.koin.dsl.module

object MappersModule {

    val mappersModule = module {
        factory { AnnouncementMapper() }
        factory { FavoritesMapper() }
    }
}