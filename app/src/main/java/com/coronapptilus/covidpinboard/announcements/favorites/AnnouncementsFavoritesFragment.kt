package com.coronapptilus.covidpinboard.announcements.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import com.coronapptilus.covidpinboard.commons.extensions.convertDateToTimestamp
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_announcements_favorites.*
import java.util.*

class AnnouncementsFavoritesFragment : Fragment(
    R.layout.fragment_announcements_favorites
),
    AnnouncementsFavoritesContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.toolbar?.init(ToolbarView.FAVORITES)

        //TODO BORRAR: botón de ejemplo y el startActivity cuando se aplique el addToCalendar donde corresponda.
        button.setOnClickListener {
            val beginTime= convertDateToTimestamp("09/03/2020","19:30") ?: 0

            val endTime = convertDateToTimestamp("09/03/2020","20:30") ?: 0

            val calendarIntent =
                CalendarUtils.addToCalendar(
                    "Zumba",
                    "Clase diaria de Zumba",
                    "En mi guarida",
                    beginTime, endTime
                )
            startActivity(calendarIntent)
        }
    }
}