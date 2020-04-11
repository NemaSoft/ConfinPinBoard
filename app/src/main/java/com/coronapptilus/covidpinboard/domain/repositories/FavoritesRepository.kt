package com.coronapptilus.covidpinboard.domain.repositories

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

interface FavoritesRepository {

    fun addFavorite(announcementId: String)

    fun getFavorites(): FavoritesModel

    fun removeFavorite(announcementId: String)

    fun isFavorite(announcementId: String): Boolean
}
