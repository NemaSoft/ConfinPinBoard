package com.coronapptilus.covidpinboard.domain.models

data class AnnouncementModel(
    val id: String,
    val announcer: String,
    val title: String,
    val description: String,
    val place: String,
    val categories: List<Category>,
    val target: Target,
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
        object WorkShop : Category(10)
        object Formation : Category(11)
        object Donation : Category(12)
        object Crafts : Category(13)
        object StoryTeller : Category(14)
        object Others : Category(15)
    }

    sealed class Target(val type: Int) {
        object Adults : Target(1)
        object Children : Target(2)
        object Family : Target(3)
        object Undefined : Target(4)
    }
}
