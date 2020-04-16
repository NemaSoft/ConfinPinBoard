package com.confinapptilus.confinpinboard.repositories.impl

import com.confinapptilus.confinpinboard.domain.models.FavoritesModel
import com.confinapptilus.confinpinboard.domain.repositories.FavoritesRepository
import com.confinapptilus.confinpinboard.repositories.datasources.FavoritesDataSource

class FavoritesRepositoryImpl(private val dataSource: FavoritesDataSource) : FavoritesRepository {

    override fun addFavorite(announcementId: String) {
        dataSource.addFavorite(announcementId)
    }

    override fun getFavorites(): FavoritesModel = dataSource.getFavorites()

    override fun removeFavorite(announcementId: String) {
        dataSource.removeFavorite(announcementId)
    }

    override fun isFavorite(announcementId: String): Boolean = dataSource.isFavorite(announcementId)
}
