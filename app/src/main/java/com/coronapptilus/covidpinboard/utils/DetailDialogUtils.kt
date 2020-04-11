package com.coronapptilus.covidpinboard.utils

import android.content.Context
import android.view.View
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.extensions.formatDate
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import kotlinx.android.synthetic.main.detail_dialog.view.*
import java.util.*

object DetailDialogUtils {

    fun getFilledDialog(item: AnnouncementModel, dialogView: View): View {

        dialogView.apply {
            dialog_title.text = getFormattedTitle(context, item.title)
            dialog_description.text = getFormattedDescription(context, item.description)
            dialog_place.text = getFormattedPlace(context, item.place)
            dialog_categories.text =
                getFormattedCategories(getCategoriesNames(context, item.categories))
            dialog_target_header.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(setTargetIcon(item.target)),
                null,
                null,
                null
            )
            dialog_target.text = getFormattedTarget(context, item.target)
            dialog_start_date.text = dialogView.context.formatDate(item.startDate, item.startTime)
            dialog_end_date.text = dialogView.context.formatDate(item.endDate, item.endTime)
            dialog_organizer.text = item.announcer

            if (dialog_categories.text.isEmpty()) {
                dialog_categories_header.visibility = View.GONE
                dialog_categories.visibility = View.GONE
            }
            if (dialog_target.text.isEmpty()) {
                dialog_target_header.visibility = View.GONE
                dialog_target.visibility = View.GONE
            }
            if (dialog_start_date.text.isEmpty()) {
                dialog_start_date_header.visibility = View.GONE
                dialog_start_date.visibility = View.GONE
            }
            if (dialog_end_date.text.isEmpty()) {
                dialog_end_date_header.visibility = View.GONE
                dialog_end_date.visibility = View.GONE
            }
            if (dialog_organizer.text.isEmpty()) {
                dialog_organizer_header.visibility = View.GONE
                dialog_organizer.visibility = View.GONE
            }
        }
        return dialogView
    }

    private fun getFormattedTitle(context: Context, title: String) =
        if (title.isNotEmpty()) {
            title
        } else {
            context.resources.getString(R.string.error_message)
                .plus(" ")
                .plus(context.resources.getString(R.string.title).toLowerCase(Locale.getDefault()))
        }

    private fun getFormattedDescription(context: Context, description: String): String =
        if (description.isNotEmpty()) {
            description
        } else {
            context.resources.getString(R.string.error_message)
                .plus(" ")
                .plus(context.resources.getString(R.string.description_header).toLowerCase(Locale.getDefault()))
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
                    AnnouncementModel.Category.WorkShop -> categoriesNames.add(context.getString(R.string.workshop))
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
