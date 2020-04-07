package com.coronapptilus.covidpinboard.di

import com.coronapptilus.covidpinboard.domain.usecases.AddAnnouncementUseCase
import com.coronapptilus.covidpinboard.domain.usecases.AddFavoriteUseCase
import com.coronapptilus.covidpinboard.domain.usecases.GetAnnouncementUseCase
import com.coronapptilus.covidpinboard.domain.usecases.GetFavoritesUseCase
import org.koin.dsl.module

object UseCasesModule {

    val useCasesModule = module {
        factory { AddAnnouncementUseCase(get()) }
        factory { GetAnnouncementUseCase(get()) }
        factory { AddFavoriteUseCase(get()) }
        factory { GetFavoritesUseCase(get()) }
    }
}