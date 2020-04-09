package com.coronapptilus.covidpinboard.announcements.commons.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.list.AnnouncementsListContract
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import kotlinx.android.synthetic.main.activity_main.*

class FilterFragment: Fragment(R.layout.filter_dialog),
    FilterContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}