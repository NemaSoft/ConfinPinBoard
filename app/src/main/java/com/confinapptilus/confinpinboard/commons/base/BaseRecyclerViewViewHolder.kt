package com.confinapptilus.confinpinboard.commons.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.confinapptilus.confinpinboard.commons.extensions.inflate
import kotlinx.android.extensions.LayoutContainer

abstract class BaseRecyclerViewViewHolder<T>(parent: ViewGroup, @LayoutRes layoutResId: Int) :
    RecyclerView.ViewHolder(parent.inflate(layoutResId)), LayoutContainer {

    override val containerView: View?
        get() = itemView

    abstract fun update(item: T)
}