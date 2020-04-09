package com.coronapptilus.covidpinboard.announcements.commons.filter.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.coronapptilus.covidpinboard.R

class FilterItemView: RelativeLayout {

    private val mainLayout by lazy {findViewById<RelativeLayout>(R.id.filter_item_main_layout)}
    private val icon by lazy {findViewById<AppCompatImageView>(R.id.filter_item_icon)}
    private val text by lazy {findViewById<AppCompatTextView>(R.id.filter_item_text)}

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init (attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.filter_item, this,true)
    }

    fun setText (text: String){
        this.text.text = text
    }

    fun setIcon(icon: Drawable){
        this.icon.setImageDrawable(icon)
    }

    fun setChecked(b: Boolean) {
        if (b) mainLayout.setBackgroundColor(R.color.design_bottom_navigation_shadow_color)
        else mainLayout.setBackgroundColor(android.R.color.transparent)
    }
}