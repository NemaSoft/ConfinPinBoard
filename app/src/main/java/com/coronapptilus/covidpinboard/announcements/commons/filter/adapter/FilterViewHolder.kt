package com.coronapptilus.covidpinboard.announcements.commons.filter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.commons.filter.model.FilterCategoryModel
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewViewHolder

class FilterViewHolder(parent: ViewGroup) :
    BaseRecyclerViewViewHolder<FilterCategoryModel>(parent, R.layout.filter_item) {

    private val itemMargin =
        parent.context.resources.getDimension(R.dimen.form_elements_margin).toInt()

    override fun update(item: FilterCategoryModel) {

        val lp = LinearLayoutCompat.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(itemMargin, itemMargin, itemMargin, itemMargin) }

        containerView?.apply {
            layoutParams = lp

            val mainLayout by lazy { findViewById<ViewGroup>(R.id.filter_item_main_layout) }
            val icon by lazy { findViewById<AppCompatImageView>(R.id.filter_item_icon) }
            val category by lazy { findViewById<AppCompatTextView>(R.id.filter_item_text) }

            item.title?.let { category.text = it } ?: category.apply { visibility = View.GONE }
            icon.setImageResource(item.icon)
            if (item.checked) mainLayout.setBackgroundColor(item.color)
            else mainLayout.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    android.R.color.transparent
                )
            )
        }

    }
}
