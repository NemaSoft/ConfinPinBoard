package com.coronapptilus.covidpinboard.domain.usecases

import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository

class RemoveFavoriteUseCase(private val repository: FavoritesRepository) {

    fun execute(id: String) { repository.removeFavorite(id) }
}
