package com.coronapptilus.covidpinboard.announcements.commons.filter.adapter

import android.content.Context
import android.view.ViewGroup
import com.coronapptilus.covidpinboard.announcements.commons.filter.model.FilterCategoryModel
import com.coronapptilus.covidpinboard.commons.base.BaseRecyclerViewAdapter
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CategoryUtils.getCategoryColor
import com.coronapptilus.covidpinboard.utils.CategoryUtils.getCategoryIcon
import com.coronapptilus.covidpinboard.utils.CategoryUtils.getCategoryString

class FilterGridAdapter(
    private val context: Context,
    private val dataList: List<AnnouncementModel.Category>
) : BaseRecyclerViewAdapter<FilterViewHolder, FilterCategoryModel>() {


    val data = mutableListOf<FilterCategoryModel>()

    init {
        super.setData(
            data.apply {
                addAll(dataList.map {
                    FilterCategoryModel(
                        it.type,
                        context.getCategoryIcon(it),
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

    fun setCheckedCategories(checked:List<Int>){
        checked.forEach { catId -> data.find { it.id == catId }?.checked = true }
        setData(data)
        notifyDataSetChanged()
    }

    fun getCheckedCategories(): List<Int>{
        return data.filter { it.checked }.map { it.id }
    }

    private fun toggleChecked(position: Int){
        data[position].checked = !data[position].checked
        setData(data)
        notifyDataSetChanged()
    }
}