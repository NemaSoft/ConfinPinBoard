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

    private val presenter: AnnouncementsListContract.Presenter by inject()

    private val adapter = AnnouncementsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.toolbar?.apply {
            init(ToolbarView.HOME)
            setOnFilterButtonClicked { searchTerm, categories ->
                presenter.getAnnouncements(searchTerm, categories)
            }
            setOnSearchInputFilled { searchTerm, categories ->
                presenter.getAnnouncements(searchTerm, categories)
            }
        }

        initList()
        initPresenter()
    }

    private fun initList() {
        announcement_list.adapter = adapter
        adapter.onItemClicked = { presenter.onAnnouncementItemClicked(it) }
    }

    private fun initPresenter() {
        presenter.apply {
            attachView(this@AnnouncementsListFragment)
            presenter.getAnnouncements()
        }
    }

    override fun update(announcements: List<AnnouncementModel>) {
        adapter.setData(announcements)
        if (announcements.isEmpty()) {
            showEmptyScreen()
        } else {
            hideEmptyScreen()
        }
    }

    private fun showEmptyScreen() {
        announcement_list.visibility = View.GONE
        fallback_image.visibility = View.VISIBLE
    }

    private fun hideEmptyScreen() {
        announcement_list.visibility = View.VISIBLE
        fallback_image.visibility = View.GONE
    }

    override fun showAnnouncementDetail(announcement: AnnouncementModel) {
        context?.let { AnnouncementDetailDialog(it, announcement).show() }
    }
}
