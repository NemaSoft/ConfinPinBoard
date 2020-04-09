package com.coronapptilus.covidpinboard.announcements.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class AnnouncementsFavoritesFragment : Fragment(R.layout.fragment_announcements_favorites),
    AnnouncementsFavoritesContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.toolbar?.init(ToolbarView.FAVORITES)
    }

}

class AnnouncementsFavoritesFragment : Fragment(
    R.layout.fragment_announcements_favorites
),
    AnnouncementsFavoritesContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO BORRAR: botón de ejemplo y el startActivity cuando se aplique el addToCalendar donde corresponda.
        button.setOnClickListener {
            val beginTime: Calendar =
                Calendar.getInstance()
            beginTime.set(2020, 3, 8, 19, 30)

            val endTime: Calendar =
                Calendar.getInstance()
            endTime.set(2020, 3, 8, 20, 30)

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