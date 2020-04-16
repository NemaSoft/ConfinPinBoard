package com.confinapptilus.confinpinboard.announcements.commons.filter.model

data class FilterCategoryModel(
    val id: Int,
    val icon: Int,
    val title: String?,
    var checked: Boolean,
    var color: Int
)