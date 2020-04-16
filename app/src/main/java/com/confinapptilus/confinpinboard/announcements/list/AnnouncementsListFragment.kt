package com.confinapptilus.confinpinboard.announcements.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.announcements.detail.AnnouncementDetailDialog
import com.confinapptilus.confinpinboard.announcements.list.adapter.AnnouncementsListAdapter
import com.confinapptilus.confinpinboard.commons.components.ToolbarView
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
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
        announcementList.adapter = adapter
        adapter.onItemClicked = { presenter.onAnnouncementItemClicked(it) }
    }

    private fun initPresenter() {
        presenter.apply {
            attachView(this@AnnouncementsListFragment)
            getAnnouncements()
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
        announcementList.visibility = View.GONE
        fallbackImage.visibility = View.VISIBLE
    }

    private fun hideEmptyScreen() {
        announcementList.visibility = View.VISIBLE
        fallbackImage.visibility = View.GONE
    }

    override fun showAnnouncementDetail(announcement: AnnouncementModel) {
        context?.let { AnnouncementDetailDialog(it, announcement).show() }
    }

    override fun showProgress() {
        progressView?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView?.visibility = View.GONE
    }
}
