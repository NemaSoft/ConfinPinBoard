package com.confinapptilus.confinpinboard.domain.usecases

import com.confinapptilus.confinpinboard.domain.repositories.FavoritesRepository

class IsFavoriteUseCase(private val repository: FavoritesRepository) {

    fun execute(announcementId: String): Boolean = repository.isFavorite(announcementId)
}
