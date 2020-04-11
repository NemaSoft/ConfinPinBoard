package com.coronapptilus.covidpinboard.announcements.detail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import com.coronapptilus.covidpinboard.utils.DetailDialogUtils
import kotlinx.android.synthetic.main.detail_dialog.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class AnnouncementDetailDialog(
    context: Context,
    private val announcement: AnnouncementModel,
    private val onFavoriteStatusChanged: () -> Unit = {}
) : Dialog(context), KoinComponent, AnnouncementDetailContract.View {

    private val presenter by inject<AnnouncementDetailContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = View.inflate(context, R.layout.detail_dialog, null)
        val filledDialog = DetailDialogUtils.getFilledDialog(announcement, dialogView)
        setContentView(filledDialog)
        setCancelable(true)

        presenter.attachView(this)
        presenter.init(announcement)

        dialog_favorites_button.setOnClickListener {
            dialog_favorites_button.isSelected = !dialog_favorites_button.isSelected
            presenter.onFavoriteButtonClicked(dialog_favorites_button.isSelected)
        }
        dialog_share_button.setOnClickListener { presenter.onCalendarButtonClicked() }
        dialog_close_button.setOnClickListener { presenter.onCloseButtonClicked() }
    }

    override fun updateFavoriteButtonStatus(isFavorite: Boolean) {
        dialog_favorites_button.isSelected = isFavorite
    }

    override fun launchCalendarIntent(
        title: String,
        description: String,
        place: String,
        startTimestamp: Long,
        endTimestamp: Long
    ) {
        startActivity(
            context,
            CalendarUtils.addToCalendar(title, description, place, startTimestamp, endTimestamp),
            null
        )
    }

    override fun notifyFavoriteStatusChanged() {
        onFavoriteStatusChanged.invoke()
    }

    override fun exitView() {
        dismiss()
    }
}
