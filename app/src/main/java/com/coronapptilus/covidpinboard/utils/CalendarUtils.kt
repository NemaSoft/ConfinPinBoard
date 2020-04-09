package com.coronapptilus.covidpinboard.utils

import android.content.Intent
import android.provider.CalendarContract
import java.util.*

object CalendarUtils {

    /**
     * Opens the google Calendar app for creating an event with its data.
     * @param title Event title
     * @param description Short event description
     * @param location Event location
     * @param beginTime Event begin time
     * @param endTime Event ending time
     */
    fun addToCalendar(
        title: String,
        description: String,
        location: String,
        beginTime: Calendar,
        endTime: Calendar
    ): Intent {
        val intent: Intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(
                CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                beginTime.timeInMillis
            )
            .putExtra(
                CalendarContract.EXTRA_EVENT_END_TIME,
                endTime.timeInMillis
            )
            .putExtra(CalendarContract.Events.TITLE, title)
            .putExtra(CalendarContract.Events.DESCRIPTION, description)
            .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            .putExtra(
                CalendarContract.Events.AVAILABILITY,
                CalendarContract.Events.AVAILABILITY_BUSY
            )
        return intent
    }
}