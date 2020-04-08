package com.coronapptilus.covidpinboard.repositories.impl

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel
import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository
import com.coronapptilus.covidpinboard.repositories.datasources.FavoritesDataSource

class FavoritesRepositoryImpl(private val dataSource: FavoritesDataSource): FavoritesRepository {

    override fun addFavorite(id: Long) {
        dataSource.addFavorite(id)
    }

    override fun getFavorites(): FavoritesModel = dataSource.getFavorites()
}