package com.coronapptilus.covidpinboard.repositories.datasources

import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

interface FavoritesDataSource {

    fun addFavorite(announcementId: String)

    fun getFavorites() : FavoritesModel

    fun removeFavorite(announcementId: String)
}
