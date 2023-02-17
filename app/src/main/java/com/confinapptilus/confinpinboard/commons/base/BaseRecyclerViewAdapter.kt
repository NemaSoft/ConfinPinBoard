package com.confinapptilus.confinpinboard.commons.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * Intended to offer an abstraction in order to create an adapter which will receive a list of [T]
 * models and a [VH] ViewHolder, offering utility methods as updating the list [items] and/or
 * updating the [onItemClicked] mainly.
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    var items: List<T> by Delegates.observable(
        emptyList()
    ) { _, oldItems, newItems -> computeAndNotifyDataSetChanged(oldItems, newItems) }
    var onItemClicked: (T) -> Unit = {}

    private fun computeAndNotifyDataSetChanged(oldItems: List<T>, newItems: List<T>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldItems.size

            override fun getNewListSize(): Int = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldItems[oldItemPosition] == newItems[newItemPosition]
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    protected fun getItem(position: Int): T = items[position]

    override fun getItemCount(): Int = items.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        (holder as? BaseRecyclerViewViewHolder<*, T>)?.update(item)
        holder.itemView.setOnClickListener { onItemClicked.invoke(item) }
    }
}
