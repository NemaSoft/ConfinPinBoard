package com.coronapptilus.covidpinboard.repositories.datasources

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

interface FavoritesDataSource {

    fun addFavorite(id: String)

    fun getFavorites() : FavoritesModel

    fun removeFavorite(id: String)
}