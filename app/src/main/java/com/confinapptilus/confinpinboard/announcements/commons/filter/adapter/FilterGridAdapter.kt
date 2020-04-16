package com.confinapptilus.confinpinboard.announcements.commons.filter.adapter

import android.content.Context
import android.view.ViewGroup
import com.confinapptilus.confinpinboard.announcements.commons.filter.model.FilterCategoryModel
import com.confinapptilus.confinpinboard.commons.base.BaseRecyclerViewAdapter
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.utils.CategoryUtils.getCategoryColor
import com.confinapptilus.confinpinboard.utils.CategoryUtils.getCategoryIcon
import com.confinapptilus.confinpinboard.utils.CategoryUtils.getCategoryString

class FilterGridAdapter(
    private val context: Context,
    private val dataList: List<AnnouncementModel.Category>
) : BaseRecyclerViewAdapter<FilterCategoryModel, FilterViewHolder>() {


    val data = mutableListOf<FilterCategoryModel>()

    init {
        super.setData(
            data.apply {
                addAll(dataList.map {
                    FilterCategoryModel(
                        it.type,
                        getCategoryIcon(it),
                        context.getCategoryString(it),
                        false,
                        context.getCategoryColor(it)
                    )
                })
            }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder =
        FilterViewHolder(parent)

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.containerView?.setOnClickListener {
            toggleChecked(position)
        }
    }

    fun setCheckedCategories(checked: List<AnnouncementModel.Category>) {
        checked.forEach { catId -> data.find { it.id == catId.type }?.checked = true }
        setData(data)
        notifyDataSetChanged()
    }

    fun getCheckedCategories(): List<AnnouncementModel.Category> {
        return mutableListOf<AnnouncementModel.Category>().apply {
            data.filter { it.checked }
                .map { it.id }
                .forEach { id ->
                    dataList.find { it.type == id }?.let { category -> add(category) }
            }
        }
    }

    private fun toggleChecked(position: Int) {
        data[position].checked = !data[position].checked
        setData(data)
        notifyDataSetChanged()
    }
}
