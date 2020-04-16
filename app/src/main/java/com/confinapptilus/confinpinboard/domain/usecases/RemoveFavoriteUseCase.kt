package com.confinapptilus.confinpinboard.domain.usecases

import com.confinapptilus.confinpinboard.domain.repositories.FavoritesRepository

class RemoveFavoriteUseCase(private val repository: FavoritesRepository) {

    fun execute(id: String) { repository.removeFavorite(id) }
}
