package com.coronapptilus.covidpinboard.announcements.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import org.koin.android.ext.android.inject

class AnnouncementFormFragment: Fragment(R.layout.fragment_announcement_form), AnnouncementFormContract.View {

    private val presenter: AnnouncementFormContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.addAnnouncement()
    }
}