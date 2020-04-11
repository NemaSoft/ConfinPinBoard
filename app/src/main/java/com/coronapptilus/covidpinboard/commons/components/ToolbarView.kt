package com.coronapptilus.covidpinboard.commons.components

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.commons.filter.adapter.FilterGridAdapter
import com.coronapptilus.covidpinboard.commons.extensions.hideKeyboard
import com.coronapptilus.covidpinboard.commons.extensions.showKeyboard
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CategoryUtils.getAllCategories
import kotlinx.android.synthetic.main.toolbar_view.view.*

class ToolbarView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    private var searchTerm: String = ""
    private var checkedCategories = listOf<AnnouncementModel.Category>()
    private var onFilterButtonClicked: (String, List<AnnouncementModel.Category>) -> Unit = { _, _ ->}
    private var onSearchInputFilled: (String, List<AnnouncementModel.Category>) -> Unit = { _, _ ->}

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_view, this)
        search_button.setOnClickListener { refresh() }
        filter_button.setOnClickListener { showFilterDialog(onFilterButtonClicked) }
    }

    fun init(selectedTab: Int) {
        when (selectedTab) {
            0 -> setHomeAttributes()
            1 -> setFavoritesAttributes()
            else -> setFormAttributes()
        }
    }

    fun setOnFilterButtonClicked(
        onFilterButtonClicked: (String, List<AnnouncementModel.Category>) -> Unit
    ) {
        this.onFilterButtonClicked = onFilterButtonClicked
    }

    fun setOnSearchInputFilled(
        onSearchInputFilled: (String, List<AnnouncementModel.Category>) -> Unit
    ) {
        this.onSearchInputFilled = onSearchInputFilled
    }

    fun getSearchTerm() = search_input.text ?: ""

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

    private fun setHomeAttributes() {
        headtitle.visibility = View.VISIBLE
        headtitle.text = (resources.getString(R.string.app_name))
        search_input.visibility = View.GONE
        search_input.setText("")
        addTextChangeListener()
        search_button.visibility = View.VISIBLE
        search_button.setImageResource(R.drawable.ic_search)
        filter_button.visibility = View.VISIBLE
        checkedCategories = listOf()
    }

    private fun setFavoritesAttributes() {
        headtitle.visibility = View.VISIBLE
        headtitle.text = (resources.getString(R.string.favorites_label))
        search_input.visibility = View.GONE
        search_input.setText("")
        addTextChangeListener()
        search_button.visibility = View.VISIBLE
        search_button.setImageResource(R.drawable.ic_search)
        filter_button.visibility = View.VISIBLE
        checkedCategories = listOf()
    }

    private fun setFormAttributes() {
        headtitle.visibility = View.VISIBLE
        headtitle.text = (resources.getString(R.string.app_name))
        search_input.visibility = View.GONE
        search_input.setText("")
        addTextChangeListener()
        filter_button.visibility = View.GONE
        search_button.visibility = View.GONE
        checkedCategories = listOf()
    }

    private fun addTextChangeListener() {
        search_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s?.toString()
                searchText?.let {
                    searchTerm = searchText
                    onSearchInputFilled.invoke(searchTerm, checkedCategories)
                }
            }
        })
    }

    companion object {
        const val HOME = 0
        const val FAVORITES = 1
        const val FORM = 2
        const val COLUMN_WIDTH = 120f
    }

    private fun showFilterDialog(callBack: (String, List<AnnouncementModel.Category>) -> Unit) {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.filter_dialog,null)
        val closeButton = dialogView.findViewById<AppCompatImageView>(R.id.filter_dialog_close_button)
        val okButton = dialogView.findViewById<AppCompatButton>(R.id.filter_dialog_ok_button)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.filter_dialog_recyclerview)


        val filterGridAdapter= FilterGridAdapter(context, getAllCategories())
        filterGridAdapter.setCheckedCategories(checkedCategories)

        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density

        recyclerView.adapter = filterGridAdapter
        recyclerView.layoutManager = GridLayoutManager(context, (screenWidthDp / COLUMN_WIDTH + 0.5).toInt())


        val mBuilder = AlertDialog.Builder(context).setView(dialogView).create()

        closeButton.setOnClickListener {
            mBuilder.dismiss()
        }
        okButton.setOnClickListener {
            checkedCategories = filterGridAdapter.getCheckedCategories()
            callBack.invoke(searchTerm, checkedCategories)
            mBuilder.dismiss()
        }

        mBuilder.show()

    }

}