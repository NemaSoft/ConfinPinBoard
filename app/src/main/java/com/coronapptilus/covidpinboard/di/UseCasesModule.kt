package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.domain.usecases.*
import org.koin.dsl.module

object UseCasesModule {

    val useCasesModule = module {
        factory { AddAnnouncementUseCase(get()) }
        factory { GetAnnouncementUseCase(get()) }
        factory { GetAnnouncementsUseCase(get()) }
        factory { AddFavoriteUseCase(get()) }
        factory { GetFavoritesUseCase(get()) }
    }
}