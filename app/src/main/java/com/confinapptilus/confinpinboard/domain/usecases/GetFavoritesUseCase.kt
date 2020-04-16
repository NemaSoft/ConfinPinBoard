package com.confinapptilus.confinpinboard.domain.usecases

import com.confinapptilus.confinpinboard.domain.models.FavoritesModel
import com.confinapptilus.confinpinboard.domain.repositories.FavoritesRepository

class GetFavoritesUseCase(private val repository: FavoritesRepository) {

    fun execute(): FavoritesModel = repository.getFavorites()
}
