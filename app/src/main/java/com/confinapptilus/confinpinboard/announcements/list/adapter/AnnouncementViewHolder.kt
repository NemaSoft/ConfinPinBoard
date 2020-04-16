package com.confinapptilus.confinpinboard.announcements.list.adapter

import android.view.ViewGroup
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.commons.base.BaseRecyclerViewViewHolder
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.utils.ListItemUtils

class AnnouncementViewHolder(parent: ViewGroup) :
    BaseRecyclerViewViewHolder<AnnouncementModel>(parent, R.layout.announcement_item_list) {

    override fun update(item: AnnouncementModel) {
        ListItemUtils.resetView(itemView)
        ListItemUtils.getFilledItem(item, itemView)
    }
}
