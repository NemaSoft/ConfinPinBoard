package com.coronapptilus.covidpinboard.announcements.list.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewViewHolder
import com.coronapptilus.covidpinboard.commons.extensions.convertDateToTimestamp
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import com.coronapptilus.covidpinboard.utils.DetailDialogUtils
import com.coronapptilus.covidpinboard.utils.ListItemUtils
import kotlinx.android.synthetic.main.detail_dialog.view.*

class AnnouncementViewHolder(private val parent: ViewGroup) :
    BaseRecyclerViewViewHolder<AnnouncementModel>(
        parent,
        R.layout.announcement_item_list
    ) {

    private lateinit var item: AnnouncementModel

    init {
        itemView.setOnClickListener { showDetailDialog() }
    }

    override fun update(item: AnnouncementModel) {
        this.item = item
        ListItemUtils.resetView(itemView)
        ListItemUtils.getFilledItem(item, itemView)
    }

    private fun showDetailDialog() {

        val dialogView =
            LayoutInflater.from(itemView.context).inflate(R.layout.detail_dialog, parent)

        val filledDialog = DetailDialogUtils.getFilledDialog(item, dialogView)

        val mBuilder = AlertDialog.Builder(parent.context).setView(filledDialog).show()

        dialogView.dialog_close_button.setOnClickListener { mBuilder.dismiss() }

        dialogView.dialog_favorites_button.setOnClickListener { }

        dialogView.dialog_share_button.setOnClickListener {
            CalendarUtils.addToCalendar(
                item.title,
                item.description,
                item.place,
                convertDateToTimestamp(item.startDate, item.startTime) ?: 0L,
                convertDateToTimestamp(item.endDate, item.endTime) ?: 0L
            )
        }

    }
}
