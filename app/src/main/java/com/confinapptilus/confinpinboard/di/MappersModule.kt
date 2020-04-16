package com.confinapptilus.confinpinboard.di

import com.confinapptilus.confinpinboard.datasources.mappers.AnnouncementMapper
import com.confinapptilus.confinpinboard.datasources.mappers.FavoritesMapper
import org.koin.dsl.module

object MappersModule {

    val mappersModule = module {
        factory { AnnouncementMapper() }
        factory { FavoritesMapper() }
    }
}