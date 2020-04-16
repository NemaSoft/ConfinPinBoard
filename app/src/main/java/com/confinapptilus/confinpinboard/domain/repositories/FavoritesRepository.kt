package com.confinapptilus.confinpinboard.domain.repositories

import com.confinapptilus.confinpinboard.domain.models.FavoritesModel

interface FavoritesRepository {

    fun addFavorite(announcementId: String)

    fun getFavorites(): FavoritesModel

    fun removeFavorite(announcementId: String)

    fun isFavorite(announcementId: String): Boolean
}
