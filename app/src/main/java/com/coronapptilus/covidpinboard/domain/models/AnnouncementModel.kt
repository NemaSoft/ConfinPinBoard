package com.coronapptilus.covidpinboard.domain.models

import com.coronapptilus.covidpinboard.R

data class AnnouncementModel(
    val announcer: String,
    val title: String,
    val description: String,
    val place: String,
    val categories: List<Category>,
    val target: Target,
    val date: String,
    val time: String
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
    }

    fun getCategoryIconId(category: Category): Int {
        return when (category){
            Category.Sport -> R.drawable.ic_category_sport
            Category.Cook -> R.drawable.ic_category_cook
            Category.Music -> R.drawable.ic_category_music
            Category.Dance -> R.drawable.ic_category_dance
            Category.Theater -> R.drawable.ic_category_theater
            Category.Literature -> R.drawable.ic_category_literature
            Category.Cinema -> R.drawable.ic_category_cinema
            Category.Conference -> R.drawable.ic_category_conference
            Category.Interview -> R.drawable.ic_category_interview
            Category.WorkShop -> R.drawable.ic_category_workshop
            Category.Formation -> R.drawable.ic_category_formation
            Category.Donation -> R.drawable.ic_category_donation
            Category.Crafts -> R.drawable.ic_category_crafts
            Category.StoryTeller -> R.drawable.ic_category_storyteller
            Category.Others -> R.drawable.ic_category_others
        }
    }

    fun getCategoryStringId(category: Category): Int {
        return when (category){
            Category.Sport -> R.string.sport
            Category.Cook -> R.string.cook
            Category.Music -> R.string.music
            Category.Dance -> R.string.dance
            Category.Theater -> R.string.theater
            Category.Literature -> R.string.literature
            Category.Cinema -> R.string.cinema
            Category.Conference -> R.string.conference
            Category.Interview -> R.string.interview
            Category.WorkShop -> R.string.workshop
            Category.Formation -> R.string.formation
            Category.Donation -> R.string.donation
            Category.Crafts -> R.string.crafts
            Category.StoryTeller -> R.string.storyteller
            Category.Others -> R.string.others
        }
    }

}