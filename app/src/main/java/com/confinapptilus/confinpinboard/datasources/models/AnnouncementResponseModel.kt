package com.confinapptilus.confinpinboard.datasources.models

import com.google.gson.annotations.SerializedName

data class AnnouncementResponseModel(
    @SerializedName("id")
    val id: String? = null,

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

    @SerializedName("startTimestamp")
    val startTimestamp: Long? = null,

    @SerializedName("endTimestamp")
    val endTimestamp: Long? = null
)