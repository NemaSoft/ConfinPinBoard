package com.coronapptilus.covidpinboard.announcements.favorites

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

class AnnouncementsFavoritesFragment : Fragment(R.layout.fragment_announcements_favorites),
    AnnouncementsFavoritesContract.View {

    private val presenter by inject<AnnouncementsFavoritesContract.Presenter>()

    private val adapter = AnnouncementsListAdapter()
    private var announcementDetailDialog: AnnouncementDetailDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.toolbar?.init(ToolbarView.FAVORITES)

        initList()

        initPresenter()
    }

    private fun initList() {
        announcement_list.adapter = adapter
        adapter.onItemClicked = { presenter.onAnnouncementItemClicked(it) }
    }

    private fun initPresenter() {
        presenter.apply {
            attachView(this@AnnouncementsFavoritesFragment)
            init()
        }
    }

    override fun update(favorites: List<AnnouncementModel>) {
        adapter.setData(favorites)
        if (favorites.isEmpty()) {
            announcement_list.visibility = View.GONE
            fallback_image.visibility = View.VISIBLE
        }
    }

    override fun showAnnouncementDetail(announcement: AnnouncementModel) {
        context?.let {
            announcementDetailDialog = AnnouncementDetailDialog(it, announcement) {
                presenter.onFavoriteStatusChanged()
            }
            announcementDetailDialog?.show()
        }
    }

    override fun hideAnnouncementDetail() {
        announcementDetailDialog?.dismiss()
    }
}
