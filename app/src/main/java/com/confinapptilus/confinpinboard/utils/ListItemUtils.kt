package com.confinapptilus.confinpinboard.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.commons.extensions.formatDate
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import kotlinx.android.synthetic.main.announcement_item_list.view.*
import java.util.*

object ListItemUtils {

    fun resetView(itemView: View): View =
        itemView.apply {
            item_title.text = ""
            item_place.text = ""
            item_announcer.text = ""
            item_image.setImageResource(R.drawable.ic_empty_screen)
            item_image.background = null
            item_categories_list.text = ""
            item_target_image.setImageResource(R.drawable.ic_target_adults)
            item_target.text = ""
            item_start_date.text = ""
            item_ending_date.text = ""
            item_description.text = ""

            item_target_image.visibility = View.VISIBLE
            item_target.visibility = View.VISIBLE
            item_announcer_image.visibility = View.VISIBLE
            item_announcer.visibility = View.VISIBLE
        }

    fun getFilledItem(item: AnnouncementModel, itemView: View): View =
        itemView.apply {
            item_title.text = getFormattedTitle(context, item.title)
            item_place.text = getFormattedPlace(context, item.place)
            item_announcer.text = item.announcer
            item_image.setImageResource(getIconId(item.categories))
            item_image.setBackgroundColor(
                getItemImageBackgroundColorId(
                    context,
                    getIconId(item.categories)
                )
            )
            item_categories_list.text =
                getFormattedCategories(getCategoriesNames(context, item.categories))
            item_target_image.setImageResource(setTargetIcon(item.target))
            item_target.text = getFormattedTarget(context, item.target)
            item_start_date.text = itemView.context.formatDate(item.startDate, item.startTime)
            item_ending_date.text = itemView.context.formatDate(item.endDate, item.endTime)
            item_description.text = item.description

            if (item_target.text.isEmpty()) {
                item_target_image.visibility = View.INVISIBLE
                item_target.visibility = View.INVISIBLE
            }

            if (item_announcer.text.isEmpty()) {
                item_announcer_image.visibility = View.GONE
                item_announcer.visibility = View.GONE
            }
        }

    private fun getFormattedTitle(context: Context, title: String) =
        if (title.isNotEmpty()) {
            title
        } else {
            context.resources.getString(R.string.error_message)
                .plus(" ")
                .plus(context.resources.getString(R.string.title).toLowerCase(Locale.getDefault()))
        }

    private fun getFormattedPlace(context: Context, place: String) =
        if (place.isNotEmpty()) {
            place
        } else {
            context.resources.getString(R.string.error_message)
                .plus(" ")
                .plus(context.resources.getString(R.string.place_header).toLowerCase(Locale.getDefault()))
        }

    private fun getFormattedCategories(categoriesNames: List<String>): String {
        var categoriesFormatted = ""

        if (categoriesNames.isEmpty()) {
            return categoriesFormatted
        }

        return if (categoriesNames.size == 1) {
            categoriesNames[0]
        } else {
            categoriesNames.forEach {
                if (categoriesFormatted.isEmpty()) {
                    categoriesFormatted = it
                } else {
                    categoriesFormatted += ", $it"
                }
            }
            categoriesFormatted
        }
    }

    private fun getCategoriesNames(
        context: Context,
        categories: List<AnnouncementModel.Category>
    ): List<String> {

        val categoriesNames: MutableList<String> = mutableListOf()

        if (categories.isEmpty()) {
            return emptyList()
        } else {
            categories.forEach {
                when (it) {
                    AnnouncementModel.Category.Sport -> categoriesNames.add(context.getString(R.string.sport))
                    AnnouncementModel.Category.Cook -> categoriesNames.add(context.getString(R.string.cook))
                    AnnouncementModel.Category.Music -> categoriesNames.add(context.getString(R.string.music))
                    AnnouncementModel.Category.Dance -> categoriesNames.add(context.getString(R.string.dance))
                    AnnouncementModel.Category.Theater -> categoriesNames.add(context.getString(R.string.theater))
                    AnnouncementModel.Category.Literature -> categoriesNames.add(context.getString(R.string.literature))
                    AnnouncementModel.Category.Cinema -> categoriesNames.add(context.getString(R.string.cinema))
                    AnnouncementModel.Category.Conference -> categoriesNames.add(context.getString(R.string.conference))
                    AnnouncementModel.Category.Interview -> categoriesNames.add(context.getString(R.string.interview))
                    AnnouncementModel.Category.Workshop -> categoriesNames.add(context.getString(R.string.workshop))
                    AnnouncementModel.Category.Formation -> categoriesNames.add(context.getString(R.string.formation))
                    AnnouncementModel.Category.Donation -> categoriesNames.add(context.getString(R.string.donation))
                    AnnouncementModel.Category.Crafts -> categoriesNames.add(context.getString(R.string.crafts))
                    AnnouncementModel.Category.StoryTelling -> categoriesNames.add(
                        context.getString(
                            R.string.storytelling
                        )
                    )
                    AnnouncementModel.Category.Others -> categoriesNames.add(context.getString(R.string.others))
                }
            }
            return categoriesNames
        }
    }

    private fun getIconId(categories: List<AnnouncementModel.Category>): Int {
        return if (categories.isEmpty()) {
            R.drawable.ic_report
        } else {
            categories[0].let {
                when (it) {
                    AnnouncementModel.Category.Sport -> R.drawable.ic_category_sport
                    AnnouncementModel.Category.Cook -> R.drawable.ic_category_cook
                    AnnouncementModel.Category.Music -> R.drawable.ic_category_music
                    AnnouncementModel.Category.Dance -> R.drawable.ic_category_dance
                    AnnouncementModel.Category.Theater -> R.drawable.ic_category_theater
                    AnnouncementModel.Category.Literature -> R.drawable.ic_category_literature
                    AnnouncementModel.Category.Cinema -> R.drawable.ic_category_cinema
                    AnnouncementModel.Category.Conference -> R.drawable.ic_category_conference
                    AnnouncementModel.Category.Interview -> R.drawable.ic_category_interview
                    AnnouncementModel.Category.Workshop -> R.drawable.ic_category_workshop
                    AnnouncementModel.Category.Formation -> R.drawable.ic_category_formation
                    AnnouncementModel.Category.Donation -> R.drawable.ic_category_donation
                    AnnouncementModel.Category.Crafts -> R.drawable.ic_category_crafts
                    AnnouncementModel.Category.StoryTelling -> R.drawable.ic_category_storytelling
                    AnnouncementModel.Category.Others -> R.drawable.ic_category_others
                }
            }
        }
    }

    private fun getItemImageBackgroundColorId(context: Context, iconId: Int): Int =
        when (iconId) {
            R.drawable.ic_category_sport -> ContextCompat.getColor(context, R.color.sport)
            R.drawable.ic_category_cook -> ContextCompat.getColor(context, R.color.cook)
            R.drawable.ic_category_music -> ContextCompat.getColor(context, R.color.music)
            R.drawable.ic_category_dance -> ContextCompat.getColor(context, R.color.dance)
            R.drawable.ic_category_theater -> ContextCompat.getColor(context, R.color.theater)
            R.drawable.ic_category_literature -> ContextCompat.getColor(context, R.color.literature)
            R.drawable.ic_category_cinema -> ContextCompat.getColor(context, R.color.cinema)
            R.drawable.ic_category_conference -> ContextCompat.getColor(context, R.color.conference)
            R.drawable.ic_category_interview -> ContextCompat.getColor(context, R.color.interview)
            R.drawable.ic_category_workshop -> ContextCompat.getColor(context, R.color.workshop)
            R.drawable.ic_category_formation -> ContextCompat.getColor(context, R.color.formation)
            R.drawable.ic_category_donation -> ContextCompat.getColor(context, R.color.donation)
            R.drawable.ic_category_crafts -> ContextCompat.getColor(context, R.color.crafts)
            R.drawable.ic_category_storytelling -> ContextCompat.getColor(
                context,
                R.color.storytelling
            )
            R.drawable.ic_category_others -> ContextCompat.getColor(context, R.color.others)
            else -> ContextCompat.getColor(context, R.color.sport)
        }


    private fun setTargetIcon(target: AnnouncementModel.Target): Int =
        when (target) {
            AnnouncementModel.Target.Adults -> R.drawable.ic_target_adults
            AnnouncementModel.Target.Children -> R.drawable.ic_target_kids
            else -> R.drawable.ic_target_families
        }


    private fun getFormattedTarget(context: Context, target: AnnouncementModel.Target): String =
        when (target) {
            AnnouncementModel.Target.Adults -> context.resources.getString(R.string.adults)
            AnnouncementModel.Target.Children -> context.resources.getString(R.string.children)
            AnnouncementModel.Target.Familiar -> context.resources.getString(R.string.families)
            AnnouncementModel.Target.Undefined -> ""
        }
}
