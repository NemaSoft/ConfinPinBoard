package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.datasources.mappers.FavoritesMapper
import com.coronapptilus.covidpinboard.repositories.mappers.AnnouncementMapper
import org.koin.dsl.module

object MappersModule {

    val mappersModule = module {
        factory { AnnouncementMapper() }
        factory { FavoritesMapper() }
    }
}