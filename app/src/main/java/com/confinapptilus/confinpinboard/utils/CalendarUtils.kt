package com.confinapptilus.confinpinboard.utils

import android.content.Intent
import android.provider.CalendarContract
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CalendarUtils {

    private const val STRING_DAY_FORMAT: String = "dd/MM/yyyy"
    private const val STRING_HOUR_FORMAT: String = "HH:mm"
    private const val STRING_DATE_FORMAT: String = "$STRING_DAY_FORMAT $STRING_HOUR_FORMAT"

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
        beginTime: Long,
        endTime: Long
    ): Intent {
        return Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
            .putExtra(CalendarContract.Events.TITLE, title)
            .putExtra(CalendarContract.Events.DESCRIPTION, description)
            .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            .putExtra(
                CalendarContract.Events.AVAILABILITY,
                CalendarContract.Events.AVAILABILITY_BUSY
            )
    }

    fun twoDigits(digit: Int): String? {
        return if (digit <= 9) "0$digit" else digit.toString()
    }

    fun fromStringToFullDate(dateString: String): Date? {
        val sdf = SimpleDateFormat(STRING_DATE_FORMAT, Locale.getDefault())
        try {
            return sdf.parse(dateString)
        } catch (exception: ParseException) {
            Log.w("Exception", "Date couldn't be parsed -- " + exception.localizedMessage)
        }
        return null
    }
}
