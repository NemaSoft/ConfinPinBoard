package com.confinapptilus.confinpinboard.commons.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Intended to offer an abstraction in order to create an adapter which will receive a list of [T]
 * models and a [V] ViewHolder, offering utility methods as updating the list [dataSet] items and/or
 * updating the [onItemClicked] mainly.
 */
abstract class BaseRecyclerViewAdapter<T, V : BaseRecyclerViewViewHolder<T>> :
    RecyclerView.Adapter<V>() {

    private var dataSet: MutableList<T> = ArrayList()
    var onItemClicked: (T) -> Unit = {}

    override fun onBindViewHolder(holder: V, position: Int) {
        val item = dataSet[position]
        holder.update(item)
        holder.itemView.setOnClickListener { onItemClicked.invoke(item) }
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
