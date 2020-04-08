package com.coronapptilus.covidpinboard.commons.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.extensions.hideKeyboard
import com.coronapptilus.covidpinboard.commons.extensions.showKeyboard
import kotlinx.android.synthetic.main.toolbar_view.view.*

class ToolbarView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    var onFilterButtonClicked: () -> Unit = {}

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_view, this)
        search_button.setOnClickListener { refresh() }
        filter_button.setOnClickListener { onFilterButtonClicked.invoke() }
    }

    private fun refresh() {
        if (!isSearchBoxShowing()) {
            showSearchBox()
            showKeyboard()
            search_button.setImageResource(R.drawable.ic_clear)
            search_input.requestFocus()
        } else {
            search_input.setText("")
            search_button.setImageResource(R.drawable.ic_search)
            hideKeyboard()
            hideSearchBox()
        }
    }

    private fun isSearchBoxShowing() = search_input.visibility == View.VISIBLE

    private fun showSearchBox() {
        headtitle.visibility = View.GONE
        search_input.visibility = View.VISIBLE
    }

    private fun hideSearchBox() {
        headtitle.visibility = View.VISIBLE
        search_input.visibility = View.GONE
    }

    fun init(selectedTab: Int) {
        when (selectedTab) {
            0 -> setHomeAttributes()
            1 -> setFavoritesAttributes()
            else -> setFormAttributes()
        }
    }

    private fun setHomeAttributes() {
        headtitle.visibility = View.VISIBLE
        headtitle.text = (resources.getString(R.string.app_name))
        search_input.visibility = View.GONE
        search_input.setText("")
        search_button.visibility = View.VISIBLE
        search_button.setImageResource(R.drawable.ic_search)
        filter_button.visibility = View.VISIBLE
    }

    private fun setFavoritesAttributes() {
        headtitle.visibility = View.VISIBLE
        headtitle.text = (resources.getString(R.string.favorites_label))
        search_input.visibility = View.GONE
        search_input.setText("")
        search_button.visibility = View.VISIBLE
        search_button.setImageResource(R.drawable.ic_search)
        filter_button.visibility = View.VISIBLE
    }

    private fun setFormAttributes() {
        headtitle.visibility = View.VISIBLE
        headtitle.text = (resources.getString(R.string.app_name))
        search_input.visibility = View.GONE
        search_input.setText("")
        filter_button.visibility = View.GONE
        search_button.visibility = View.GONE
    }

    companion object {
        const val HOME = 0
        const val FAVORITES = 1
        const val FORM = 2
    }
}