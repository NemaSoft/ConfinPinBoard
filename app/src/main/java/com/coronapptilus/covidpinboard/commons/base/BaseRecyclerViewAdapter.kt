package com.coronapptilus.covidpinboard.commons.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : BaseRecyclerViewAdapter.BaseViewHolder<V>, V> : RecyclerView.Adapter<T>() {

    private var dataSet: MutableList<V> = ArrayList()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setData(data: List<V>) {
        updateAndNotifyDataListChanged(data.toMutableList())
    }

    private fun updateAndNotifyDataListChanged(dataList: MutableList<V>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallBack(dataList, dataSet), true)
        dataSet.clear()
        dataSet.addAll(dataList)
        diffResult.dispatchUpdatesTo(this)
    }

    abstract class BaseViewHolder<in V> constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: V)
    }
}