package com.coronapptilus.covidpinboard.domain.repositories

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

interface FavoritesRepository {

    fun addFavorite(id: String)

    fun getFavorites(): FavoritesModel

    fun removeFavorite(id: String)
}