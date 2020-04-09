package com.coronapptilus.covidpinboard.datasources.models

import com.google.gson.annotations.SerializedName

data class AnnouncementResponseModel(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("announcer")
    val announcer: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("place")
    val place: String? = null,

    @SerializedName("categoriesTypes")
    val categoriesTypes: List<Int?>? = null,

    @SerializedName("targetType")
    val targetType: Int? = null,

    @SerializedName("timestamp")
    val timestamp: Long? = null
)