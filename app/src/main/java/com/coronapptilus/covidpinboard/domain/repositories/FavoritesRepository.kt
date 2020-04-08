package com.coronapptilus.covidpinboard.domain.repositories

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

interface FavoritesRepository {

    fun addFavorite(id: Long)

    fun getFavorites(): FavoritesModel
}