package com.coronapptilus.covidpinboard.commons.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.coronapptilus.covidpinboard.commons.extensions.inflate
import kotlinx.android.extensions.LayoutContainer

abstract class BaseRecyclerViewViewHolder<T>(parent: ViewGroup, @LayoutRes layoutResId: Int) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(parent.inflate(layoutResId)),
    LayoutContainer {

    override val containerView: View?
        get() = itemView

    abstract fun update(item: T)
}