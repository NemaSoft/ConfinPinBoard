package com.coronapptilus.covidpinboard.domain.usecases

import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository

class AddFavoriteUseCase(private val repository: FavoritesRepository) {

    fun execute(id: Long) {
        repository.addFavorite(id)
    }
}