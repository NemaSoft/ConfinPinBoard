package com.coronapptilus.covidpinboard.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel

object CategoryUtils {

    fun Context.getCategoryIcon(category: AnnouncementModel.Category): Int {
        return when (category) {
            AnnouncementModel.Category.Sport -> R.drawable.ic_category_sport
            AnnouncementModel.Category.Cook -> R.drawable.ic_category_cook
            AnnouncementModel.Category.Music -> R.drawable.ic_category_music
            AnnouncementModel.Category.Dance -> R.drawable.ic_category_dance
            AnnouncementModel.Category.Theater -> R.drawable.ic_category_theater
            AnnouncementModel.Category.Literature -> R.drawable.ic_category_literature
            AnnouncementModel.Category.Cinema -> R.drawable.ic_category_cinema
            AnnouncementModel.Category.Conference -> R.drawable.ic_category_conference
            AnnouncementModel.Category.Interview -> R.drawable.ic_category_interview
            AnnouncementModel.Category.WorkShop -> R.drawable.ic_category_workshop
            AnnouncementModel.Category.Formation -> R.drawable.ic_category_formation
            AnnouncementModel.Category.Donation -> R.drawable.ic_category_donation
            AnnouncementModel.Category.Crafts -> R.drawable.ic_category_crafts
            AnnouncementModel.Category.StoryTelling -> R.drawable.ic_category_storytelling
            AnnouncementModel.Category.Others -> R.drawable.ic_category_others
        }
    }

    fun Context.getCategoryString(category: AnnouncementModel.Category): String {
        return getString(
            when (category) {
                AnnouncementModel.Category.Sport -> R.string.sport
                AnnouncementModel.Category.Cook -> R.string.cook
                AnnouncementModel.Category.Music -> R.string.music
                AnnouncementModel.Category.Dance -> R.string.dance
                AnnouncementModel.Category.Theater -> R.string.theater
                AnnouncementModel.Category.Literature -> R.string.literature
                AnnouncementModel.Category.Cinema -> R.string.cinema
                AnnouncementModel.Category.Conference -> R.string.conference
                AnnouncementModel.Category.Interview -> R.string.interview
                AnnouncementModel.Category.WorkShop -> R.string.workshop
                AnnouncementModel.Category.Formation -> R.string.formation
                AnnouncementModel.Category.Donation -> R.string.donation
                AnnouncementModel.Category.Crafts -> R.string.crafts
                AnnouncementModel.Category.StoryTelling -> R.string.storytelling
                AnnouncementModel.Category.Others -> R.string.others
            }
        )
    }

    fun Context.getCategoryColor(category: AnnouncementModel.Category): Int {
        return ContextCompat.getColor(
            this,
            when (category) {
                AnnouncementModel.Category.Sport -> R.color.sport
                AnnouncementModel.Category.Cook -> R.color.cook
                AnnouncementModel.Category.Music -> R.color.music
                AnnouncementModel.Category.Dance -> R.color.dance
                AnnouncementModel.Category.Theater -> R.color.theater
                AnnouncementModel.Category.Literature -> R.color.literature
                AnnouncementModel.Category.Cinema -> R.color.cinema
                AnnouncementModel.Category.Conference -> R.color.conference
                AnnouncementModel.Category.Interview -> R.color.interview
                AnnouncementModel.Category.WorkShop -> R.color.workshop
                AnnouncementModel.Category.Formation -> R.color.formation
                AnnouncementModel.Category.Donation -> R.color.donation
                AnnouncementModel.Category.Crafts -> R.color.crafts
                AnnouncementModel.Category.StoryTelling -> R.color.storytelling
                AnnouncementModel.Category.Others -> R.color.others
            }
        )
    }

    fun getAllCategories(): List<AnnouncementModel.Category> {
        return mutableListOf<AnnouncementModel.Category>(
            AnnouncementModel.Category.Sport,
            AnnouncementModel.Category.Cook,
            AnnouncementModel.Category.Music,
            AnnouncementModel.Category.Dance,
            AnnouncementModel.Category.Theater,
            AnnouncementModel.Category.Literature,
            AnnouncementModel.Category.Cinema,
            AnnouncementModel.Category.Conference,
            AnnouncementModel.Category.Interview,
            AnnouncementModel.Category.WorkShop,
            AnnouncementModel.Category.Formation,
            AnnouncementModel.Category.Donation,
            AnnouncementModel.Category.Crafts,
            AnnouncementModel.Category.StoryTelling,
            AnnouncementModel.Category.Others
        )
    }
}
