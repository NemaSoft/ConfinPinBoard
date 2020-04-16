package com.confinapptilus.confinpinboard.repositories.datasources

import com.confinapptilus.confinpinboard.domain.models.FavoritesModel

interface FavoritesDataSource {

    fun addFavorite(announcementId: String)

    fun getFavorites(): FavoritesModel

    fun removeFavorite(announcementId: String)

    fun isFavorite(announcementId: String): Boolean
}
