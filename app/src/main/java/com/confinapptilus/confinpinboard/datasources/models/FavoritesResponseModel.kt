package com.confinapptilus.confinpinboard.datasources.models

import com.google.gson.annotations.SerializedName

data class FavoritesResponseModel(
    @SerializedName("favorites")
    val favorites: List<String>
)