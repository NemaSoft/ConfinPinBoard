package com.coronapptilus.covidpinboard.datasources.models

data class AnnouncementResponseModel(
    val announcer: String? = null,
    val title: String? = null,
    val description: String? = null,
    val place: String? = null,
    val categoriesTypes: List<Int?>? = null,
    val targetType: Int? = null,
    val timestamp: Long? = null
)