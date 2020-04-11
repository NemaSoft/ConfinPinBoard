package com.coronapptilus.covidpinboard.domain.usecases

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel
import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository

class GetFavoritesUseCase(private val repository: FavoritesRepository) {

    fun execute(): FavoritesModel = repository.getFavorites()
}