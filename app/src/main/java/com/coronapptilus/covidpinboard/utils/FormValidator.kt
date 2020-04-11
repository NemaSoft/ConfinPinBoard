package com.coronapptilus.covidpinboard.utils

import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import java.util.*

object FormValidator {

    private const val TITLE_MAX_LENGTH = 60
    private const val ANNOUNCER_MAX_LENGTH = 60
    private const val PLACE_MAX_LENGTH = 140
    private const val DESCRIPTION_MAX_LENGTH = 140

    fun isValidTitle(title: String): Boolean {
        if (!title.isBlank()) {
            if (title.length <= TITLE_MAX_LENGTH) {
                return true
            }
        }
        return false
    }

    fun isValidAnnouncer(announcer: String): Boolean {
        if (!announcer.isBlank()) {
            if (announcer.length <= ANNOUNCER_MAX_LENGTH) {
                return true
            }
        }
        return false
    }

    fun isValidPlace(location: String): Boolean {
        if (!location.isBlank()) {
            if (location.length <= PLACE_MAX_LENGTH) {
                return true
            }
        }
        return false
    }


    fun isValidDescription(location: String): Boolean {
        return location.length <= DESCRIPTION_MAX_LENGTH
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