package com.coronapptilus.covidpinboard.announcements.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import org.koin.android.ext.android.inject

class AnnouncementsListFragment: Fragment(R.layout.fragment_announcement_list), AnnouncementsListContract.View {

    private val presenter: AnnouncementsListContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
    }

    private fun initPresenter() {
        presenter.apply {
            attachView(this@AnnouncementsListFragment)
            presenter.init()
        }
    }
}