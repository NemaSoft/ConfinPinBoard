package com.coronapptilus.covidpinboard.domain.usecases

import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository

class IsFavoriteUseCase(private val repository: FavoritesRepository) {

    fun execute(announcementId: String): Boolean = repository.isFavorite(announcementId)
}
