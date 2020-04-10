package com.coronapptilus.covidpinboard.announcements.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.list.adapter.AnnouncementsListAdapter
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_announcement_list.*
import org.koin.android.ext.android.inject

class AnnouncementsListFragment : Fragment(R.layout.fragment_announcement_list),
    AnnouncementsListContract.View {

    private val adapter = AnnouncementsListAdapter()

    private val presenter: AnnouncementsListContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.toolbar?.init(ToolbarView.HOME)
        home_event_list.adapter = adapter

        initPresenter()
    }

    private fun initPresenter() {
        presenter.apply {
            attachView(this@AnnouncementsListFragment)
            presenter.init()
        }
    }

    override fun update(announcement: List<AnnouncementModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
