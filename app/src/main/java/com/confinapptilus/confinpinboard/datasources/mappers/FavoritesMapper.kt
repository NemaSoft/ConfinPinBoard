package com.confinapptilus.confinpinboard.datasources.mappers

import com.confinapptilus.confinpinboard.datasources.models.FavoritesResponseModel
import com.confinapptilus.confinpinboard.domain.models.FavoritesModel

class FavoritesMapper {

    fun mapResponseToDomain(responseModel: FavoritesResponseModel): FavoritesModel =
        with(responseModel) {
            FavoritesModel(
                favoritesAnnouncementsIds = favorites
            )
        }
}
