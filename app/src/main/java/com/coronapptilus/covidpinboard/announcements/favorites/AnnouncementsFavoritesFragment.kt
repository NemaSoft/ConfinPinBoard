package com.coronapptilus.covidpinboard.announcements.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import kotlinx.android.synthetic.main.activity_main.*

class AnnouncementsFavoritesFragment : Fragment(R.layout.fragment_announcements_favorites),
    AnnouncementsFavoritesContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.toolbar?.init(ToolbarView.FAVORITES)
    }

}
