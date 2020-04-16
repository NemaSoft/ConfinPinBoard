package com.confinapptilus.confinpinboard.commons.base

import androidx.recyclerview.widget.DiffUtil

class DiffCallBack<ITEM>(private val newList: List<ITEM>, private val oldList: List<ITEM>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]
}