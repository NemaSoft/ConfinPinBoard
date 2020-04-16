package com.confinapptilus.confinpinboard.domain.usecases

import com.confinapptilus.confinpinboard.domain.repositories.FavoritesRepository

class AddFavoriteUseCase(private val repository: FavoritesRepository) {

    fun execute(id: String) { repository.addFavorite(id) }
}
