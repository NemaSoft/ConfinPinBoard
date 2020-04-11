package com.coronapptilus.covidpinboard.announcements.list.adapter

import android.view.ViewGroup
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewViewHolder
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.ListItemUtils

class AnnouncementViewHolder(parent: ViewGroup) :
    BaseRecyclerViewViewHolder<AnnouncementModel>(parent, R.layout.announcement_item_list) {

    override fun update(item: AnnouncementModel) {
        ListItemUtils.resetView(itemView)
        ListItemUtils.getFilledItem(item, itemView)
    }
}
