package com.coronapptilus.covidpinboard.repositories.datasources

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

interface FavoritesDataSource {

    fun addFavorite(id: Long)

    fun getFavorites() : FavoritesModel
}