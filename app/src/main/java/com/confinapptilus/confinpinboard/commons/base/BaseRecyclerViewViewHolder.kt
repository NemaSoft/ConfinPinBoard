package com.confinapptilus.confinpinboard.commons.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Intended to offer a good abstraction point to simply pass a [ViewBinding] class [VB] in order to
 * attach its root as the [RecyclerView.ViewHolder] view and offer a way to update it with [T].
 */
abstract class BaseRecyclerViewViewHolder<VB : ViewBinding, T>(
    protected val viewBinding: VB
) : RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun update(item: T)
}
