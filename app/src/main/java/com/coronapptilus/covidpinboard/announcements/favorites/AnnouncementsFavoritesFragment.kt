package com.coronapptilus.covidpinboard.announcements.favorites

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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

        activity?.toolbar?.apply {
            init(ToolbarView.FAVORITES)
            setOnFilterButtonClicked { searchTerm, categories ->
                presenter.getFavorites(searchTerm, categories)
            }
            setOnSearchInputFilled { searchTerm, categories ->
                presenter.getFavorites(searchTerm, categories)
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
            attachView(this@AnnouncementsFavoritesFragment)
            getFavorites()
        }
    }

    override fun update(favorites: List<AnnouncementModel>) {
        adapter.setData(favorites)
        if (favorites.isEmpty()) {
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

    override fun showProgress() {
        progressView?.visibility = VISIBLE
    }

    override fun hideProgress() {
        progressView?.visibility = GONE
    }
}
