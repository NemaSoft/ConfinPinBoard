package com.coronapptilus.covidpinboard.announcements.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import kotlinx.android.synthetic.main.activity_main.*

class AnnouncementsListFragment : Fragment(R.layout.fragment_announcement_list),
    AnnouncementsListContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.toolbar?.init(ToolbarView.HOME)
    }

}
