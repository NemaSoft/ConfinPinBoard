package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.domain.repositories.AnnouncementsRepository
import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository
import com.coronapptilus.covidpinboard.repositories.impl.AnnouncementsRepositoryImpl
import com.coronapptilus.covidpinboard.repositories.impl.FavoritesRepositoryImpl
import org.koin.dsl.module

object RepositoriesModule {

    val repositoriesModule = module {
        factory<AnnouncementsRepository> { AnnouncementsRepositoryImpl(get()) }
        factory<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    }
}