package com.confinapptilus.confinpinboard.di

import com.confinapptilus.confinpinboard.domain.usecases.*
import org.koin.dsl.module

object UseCasesModule {

    val useCasesModule = module {
        factory { AddAnnouncementUseCase(get()) }
        factory { GetAnnouncementsUseCase(get()) }
        factory { AddFavoriteUseCase(get()) }
        factory { GetFavoritesUseCase(get()) }
        factory { RemoveFavoriteUseCase(get()) }
        factory { IsFavoriteUseCase(get()) }
    }
}
