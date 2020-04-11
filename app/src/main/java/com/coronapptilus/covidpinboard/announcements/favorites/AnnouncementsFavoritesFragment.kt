package com.coronapptilus.covidpinboard.announcements.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.list.adapter.AnnouncementsListAdapter
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import com.coronapptilus.covidpinboard.commons.extensions.convertDateToTimestamp
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import com.coronapptilus.covidpinboard.utils.DetailDialogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_dialog.view.*
import kotlinx.android.synthetic.main.fragment_announcement_list.*
import org.koin.android.ext.android.inject

class AnnouncementsFavoritesFragment : Fragment(R.layout.fragment_announcements_favorites),
    AnnouncementsFavoritesContract.View {

    private val presenter: AnnouncementsFavoritesContract.Presenter by inject()

    private val adapter = AnnouncementsListAdapter()

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
        var isFavoritesClicked = false

        val dialogView = View.inflate(context, R.layout.detail_dialog, null)

        val filledDialog = DetailDialogUtils.getFilledDialog(announcement, dialogView)

        // Favorites functionality
        dialogView.dialog_favorites_button.setOnClickListener {
            if (!isFavoritesClicked) {
                isFavoritesClicked = true
                dialogView.dialog_favorites_button.setImageResource(R.drawable.ic_favorite_active)
            } else {
                isFavoritesClicked = false
                dialogView.dialog_favorites_button.setImageResource(R.drawable.ic_favorite_inactive)
            }
        }

        // Calendar functionality
        dialogView.dialog_share_button.setOnClickListener {
            CalendarUtils.addToCalendar(
                announcement.title,
                announcement.description,
                announcement.place,
                convertDateToTimestamp(announcement.startDate, announcement.startTime) ?: 0L,
                convertDateToTimestamp(announcement.endDate, announcement.endTime) ?: 0L
            )
        }

        val alertDialog = AlertDialog.Builder(context).setView(filledDialog).show()

        dialogView.dialog_close_button.setOnClickListener { alertDialog.dismiss() }
    }
}
