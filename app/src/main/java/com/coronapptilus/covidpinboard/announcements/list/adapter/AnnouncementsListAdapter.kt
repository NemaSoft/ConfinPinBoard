package com.coronapptilus.covidpinboard.announcements.list.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewAdapter
import com.coronapptilus.covidpinboard.commons.extensions.convertDateToTimestamp
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import com.coronapptilus.covidpinboard.utils.DetailDialogUtils
import kotlinx.android.synthetic.main.detail_dialog.view.*

class AnnouncementsListAdapter :
    BaseRecyclerViewAdapter<AnnouncementViewHolder, AnnouncementModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showDetailDialog(item: AnnouncementModel, parent: ViewGroup) {

        val dialogView = LayoutInflater.from(parent.context).inflate(R.layout.detail_dialog, parent)

        val filledDialog = DetailDialogUtils.getFilledDialog(item, dialogView)

        val mBuilder = AlertDialog.Builder(parent.context).setView(filledDialog).show()

        dialogView.dialog_close_button.setOnClickListener { mBuilder.dismiss() }

        dialogView.dialog_favorites_button.setOnClickListener { }

        dialogView.dialog_share_button.setOnClickListener {
            CalendarUtils.addToCalendar(
                item.title,
                item.description,
                item.place,
                convertDateToTimestamp(item.date, item.time) ?: 0L,
                // TODO: Add ending time in Model, Mapper, Response Model
                convertDateToTimestamp(item.date, item.time) ?: 0L
            )
        }

    }
}
