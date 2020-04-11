package com.coronapptilus.covidpinboard.repositories.impl

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel
import com.coronapptilus.covidpinboard.domain.repositories.FavoritesRepository
import com.coronapptilus.covidpinboard.repositories.datasources.FavoritesDataSource

class FavoritesRepositoryImpl(private val dataSource: FavoritesDataSource) : FavoritesRepository {

    override fun addFavorite(announcementId: String) {
        dataSource.addFavorite(announcementId)
    }

    override fun getFavorites(): FavoritesModel = dataSource.getFavorites()

    override fun removeFavorite(announcementId: String) {
        dataSource.removeFavorite(announcementId)
    }
}
