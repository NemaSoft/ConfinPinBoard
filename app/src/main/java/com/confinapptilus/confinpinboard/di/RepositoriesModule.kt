package com.confinapptilus.confinpinboard.di

import com.confinapptilus.confinpinboard.domain.repositories.AnnouncementsRepository
import com.confinapptilus.confinpinboard.domain.repositories.FavoritesRepository
import com.confinapptilus.confinpinboard.repositories.impl.AnnouncementsRepositoryImpl
import com.confinapptilus.confinpinboard.repositories.impl.FavoritesRepositoryImpl
import org.koin.dsl.module

object RepositoriesModule {

    val repositoriesModule = module {
        factory<AnnouncementsRepository> { AnnouncementsRepositoryImpl(get()) }
        factory<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    }
}