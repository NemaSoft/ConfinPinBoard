package com.coronapptilus.covidpinboard.commons.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, V : BaseRecyclerViewViewHolder<T>> :
    RecyclerView.Adapter<V>() {

    private var dataSet: MutableList<T> = ArrayList()

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.update(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setData(data: List<T>) {
        updateAndNotifyDataListChanged(data.toMutableList())
    }

    private fun updateAndNotifyDataListChanged(dataList: MutableList<T>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallBack(dataList, dataSet), true)
        dataSet.clear()
        dataSet.addAll(dataList)
        diffResult.dispatchUpdatesTo(this)
    }
}
