package com.coronapptilus.covidpinboard.announcements.list

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

    override fun showAnnouncementDetail(announcement: AnnouncementModel, isFavorite: Boolean) {
        val dialogView = View.inflate(context, R.layout.detail_dialog, null)
        val filledDialog = DetailDialogUtils.getFilledDialog(announcement, dialogView)

        // Favorites functionality
        dialogView.dialog_favorites_button.apply {
            isSelected = isFavorite
            setOnClickListener {
                isSelected = !isSelected
                presenter.onFavoriteButtonClicked(announcement.id, isSelected)
            }
        }

        // Calendar functionality
        dialogView.dialog_share_button.setOnClickListener {
            startActivity(
                CalendarUtils.addToCalendar(
                    announcement.title,
                    announcement.description,
                    announcement.place,
                    convertDateToTimestamp(announcement.startDate, announcement.startTime) ?: 0L,
                    convertDateToTimestamp(announcement.endDate, announcement.endTime) ?: 0L
                )
            )
        }

        val alertDialog = AlertDialog.Builder(context).setView(filledDialog).show()

        dialogView.dialog_close_button.setOnClickListener { alertDialog.dismiss() }
    }
}
