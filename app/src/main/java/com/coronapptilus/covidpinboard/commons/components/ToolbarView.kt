package com.coronapptilus.covidpinboard.commons.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.extensions.hideKeyboard
import com.coronapptilus.covidpinboard.commons.extensions.showKeyboard
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar_view.view.*

class ToolbarView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_view, this)
        toolbar_search_button.setOnClickListener { refresh() }
    }

    private fun refresh() {
        if (!isSearchBoxShowing()) {
            showSearchBox()
            showKeyboard()
            toolbar_search_button.setImageResource(R.drawable.ic_clear)
            toolbar_search_input.requestFocus()
        } else {
            toolbar_search_input.setText("")
            toolbar_search_button.setImageResource(R.drawable.ic_search)
            hideKeyboard()
            hideSearchBox()
        }
    }

    private fun isSearchBoxShowing() = toolbar_search_input.visibility == View.VISIBLE

    private fun showSearchBox() {
        toolbar_headtitle.visibility = View.GONE
        toolbar_search_input.visibility = View.VISIBLE
    }

    private fun hideSearchBox() {
        toolbar_headtitle.visibility = View.VISIBLE
        toolbar_search_input.visibility = View.GONE
    }

    fun init(selectedTab: Int) {
        when (selectedTab) {
            0 -> setHomeAttributes()
            1 -> setFavoritesAttributes()
            else -> setFormAttributes()
        }
    }

    private fun setHomeAttributes() {
        toolbar_headtitle.visibility = View.VISIBLE
        toolbar_headtitle.text = (resources.getString(R.string.app_name))
        toolbar_search_input.visibility = View.GONE
        toolbar_search_input.setText("")
        toolbar_search_button.visibility = View.VISIBLE
        toolbar_search_button.setImageResource(R.drawable.ic_search)
        toolbar_filter_button.visibility = View.VISIBLE
    }

    private fun setFavoritesAttributes() {
        toolbar_headtitle.visibility = View.VISIBLE
        toolbar_headtitle.text = (resources.getString(R.string.favorites_label))
        toolbar_search_input.visibility = View.GONE
        toolbar_search_input.setText("")
        toolbar_search_button.visibility = View.VISIBLE
        toolbar_search_button.setImageResource(R.drawable.ic_search)
        toolbar_filter_button.visibility = View.VISIBLE
    }

    private fun setFormAttributes() {
        toolbar_headtitle.visibility = View.VISIBLE
        toolbar_headtitle.text = (resources.getString(R.string.app_name))
        toolbar_search_input.visibility = View.GONE
        toolbar_search_input.setText("")
        toolbar_filter_button.visibility = View.GONE
        toolbar_search_button.visibility = View.GONE
    }

    companion object {
        const val HOME = 0
        const val FAVORITES = 1
        const val FORM = 2
    }
}