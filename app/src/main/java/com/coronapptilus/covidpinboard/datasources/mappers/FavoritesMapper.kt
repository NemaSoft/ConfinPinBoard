package com.coronapptilus.covidpinboard.datasources.mappers

import com.coronapptilus.covidpinboard.datasources.models.FavoritesResponseModel
import com.coronapptilus.covidpinboard.domain.models.FavoritesModel

class FavoritesMapper {

    fun mapResponseToDomain(responseModel: FavoritesResponseModel): FavoritesModel =
        with(responseModel) {
            FavoritesModel(
                favoritesAnnouncementsIds = favorites
            )
        }
}
