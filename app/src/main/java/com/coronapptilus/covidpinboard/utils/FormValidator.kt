package com.coronapptilus.covidpinboard.utils

import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import java.util.*

object FormValidator {

    fun isValidTitle(title: String): Boolean {
        return !title.isBlank()
    }

    fun isValidAnnouncer(announcer: String): Boolean {
        return !announcer.isBlank()
    }

    fun isValidPlace(location: String): Boolean {
        return !location.isBlank()
    }

    fun isValidDescription(location: String): Boolean {
        // Actually an empty description is accepted.
        return true
    }


    fun isValidDate(

        startingDate: String,
        startingTime: String,
        endingDate: String,
        endingTime: String
    ): Boolean {

        val dateToStartStr = "${startingDate.replace(" ", "")} ${startingTime.replace(" ", "")}"
        val dateToFinishStr = "${endingDate.replace(" ", "")} ${endingTime.replace(" ", "")}"
        val dateToStart = CalendarUtils.fromStringToFullDate(dateToStartStr)
        val dateToFinish = CalendarUtils.fromStringToFullDate(dateToFinishStr)

        if (dateToStart != null && dateToFinish != null) {
            return if (dateToStart.before(dateToFinish)) {
                dateToFinish.after(Calendar.getInstance(Locale.getDefault()).time)
            } else {
                false
            }
        }

        return false
    }

    fun isValidTarget(targetInput: AnnouncementModel.Target?): Boolean {
        return (targetInput != null && targetInput != AnnouncementModel.Target.Undefined)
    }

    fun isValidCategoryList(categoriesList: List<AnnouncementModel.Category>): Boolean {
        return !categoriesList.isNullOrEmpty()
    }
}