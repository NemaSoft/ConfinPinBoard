package com.coronapptilus.covidpinboard.announcements.commons.filter.adapter

import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.commons.filter.model.FilterCategoryModel
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewViewHolder

class FilterViewHolder(parent: ViewGroup) :
    BaseRecyclerViewViewHolder<FilterCategoryModel>(parent, R.layout.filter_item) {

    override fun update(item: FilterCategoryModel) {

        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        containerView?.apply {
            layoutParams = lp

            val mainLayout by lazy { findViewById<RelativeLayout>(R.id.filter_item_main_layout) }
            val icon by lazy { findViewById<AppCompatImageView>(R.id.filter_item_icon) }
            val category by lazy { findViewById<AppCompatTextView>(R.id.filter_item_text) }

            category.text = item.title
            icon.setImageResource(item.icon)
            if (item.checked) mainLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.design_bottom_navigation_shadow_color))
            else mainLayout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

    }

}