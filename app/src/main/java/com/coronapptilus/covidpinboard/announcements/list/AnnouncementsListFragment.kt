package com.coronapptilus.covidpinboard.announcements.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.detail.AnnouncementDetailDialog
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
        announcement_list.adapter = adapter
        adapter.onItemClicked = { presenter.onAnnouncementItemClicked(it) }

        initPresenter()
    }

    private fun initPresenter() {
        presenter.apply {
            attachView(this@AnnouncementsListFragment)
            presenter.init()
        }
    }

    override fun update(announcements: List<AnnouncementModel>) {
        adapter.setData(announcements)
        if (announcements.isEmpty()) {
            announcement_list.visibility = View.GONE
            fallback_image.visibility = View.VISIBLE
        }
    }

    override fun showAnnouncementDetail(announcement: AnnouncementModel) {
        context?.let { AnnouncementDetailDialog(it, announcement).show() }
    }
}
