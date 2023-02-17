package com.confinapptilus.confinpinboard.domain.models

data class AnnouncementModel(
    val id: String,
    val announcer: String,
    val title: String,
    val description: String,
    val place: String,
    val categories: List<Category>,
    val startDate: String,
    val startTime: String,
    val endDate: String,
    val endTime: String
) {

    sealed class Category(val type: Int) {
        object Sport : Category(1)
        object Cook : Category(2)
        object Music : Category(3)
        object Dance : Category(4)
        object Theater : Category(5)
        object Literature : Category(6)
        object Cinema : Category(7)
        object Conference : Category(8)
        object Interview : Category(9)
        object Workshop : Category(10)
        object Formation : Category(11)
        object Donation : Category(12)
        object Crafts : Category(13)
        object StoryTelling : Category(14)
        object Others : Category(15)
    }
}
