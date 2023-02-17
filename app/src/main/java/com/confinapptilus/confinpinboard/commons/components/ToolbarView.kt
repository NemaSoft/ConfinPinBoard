package com.confinapptilus.confinpinboard.commons.components

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.announcements.commons.filter.adapter.FilterGridAdapter
import com.confinapptilus.confinpinboard.commons.extensions.hideKeyboard
import com.confinapptilus.confinpinboard.commons.extensions.showKeyboard
import com.confinapptilus.confinpinboard.databinding.ToolbarViewBinding
import com.confinapptilus.confinpinboard.domain.models.AnnouncementModel
import com.confinapptilus.confinpinboard.utils.CategoryUtils.getAllCategories

class ToolbarView(context: Context, attrs: AttributeSet? = null) :
    LinearLayoutCompat(context, attrs) {

    private val viewBinding: ToolbarViewBinding

    private var searchTerm: String = ""
    private var checkedCategories = listOf<AnnouncementModel.Category>()

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_view, this)
        viewBinding = ToolbarViewBinding.bind(view)
        viewBinding.searchButton.setOnClickListener { refresh() }
        viewBinding.filterButton.setOnClickListener { showFilterDialog() }
    }

    fun init() {
        setHomeAttributes()
    }

    private fun refresh() {
        if (!isSearchBoxShowing()) {
            showSearchBox()
            showKeyboard()
            viewBinding.searchButton.setImageResource(R.drawable.ic_clear)
            viewBinding.searchInput.requestFocus()
        } else {
            viewBinding.searchInput.setText("")
            viewBinding.searchButton.setImageResource(R.drawable.ic_search)
            hideKeyboard()
            hideSearchBox()
        }
    }

    private fun isSearchBoxShowing() = viewBinding.searchInput.visibility == View.VISIBLE

    private fun showSearchBox() {
        viewBinding.headtitle.visibility = View.GONE
        viewBinding.searchInput.visibility = View.VISIBLE
    }

    private fun hideSearchBox() {
        viewBinding.headtitle.visibility = View.VISIBLE
        viewBinding.searchInput.visibility = View.GONE
    }

    private fun setHomeAttributes() {
        viewBinding.headtitle.visibility = View.VISIBLE
        viewBinding.headtitle.text = (resources.getString(R.string.app_name))
        viewBinding.searchInput.visibility = View.GONE
        searchTerm = ""
        viewBinding.searchInput.setText("")
        addTextChangeListener()
        viewBinding.searchButton.visibility = View.VISIBLE
        viewBinding.searchButton.setImageResource(R.drawable.ic_search)
        viewBinding.filterButton.visibility = View.VISIBLE
        checkedCategories = listOf()
    }

    private fun addTextChangeListener() {
        viewBinding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s?.toString()
                searchText?.let {
                    searchTerm = searchText
                }
            }
        })
    }

    companion object {
        const val HOME = 0
        const val COLUMN_WIDTH = 120f
    }

    private fun showFilterDialog() {

        val dialogView = View.inflate(context, R.layout.filter_dialog, null)
        val closeButton =
            dialogView.findViewById<AppCompatImageView>(R.id.filter_dialog_close_button)
        val okButton = dialogView.findViewById<AppCompatButton>(R.id.filter_dialog_ok_button)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.filter_dialog_recyclerview)

        val filterGridAdapter = FilterGridAdapter(context, getAllCategories())
        filterGridAdapter.setCheckedCategories(checkedCategories)

        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density

        recyclerView.adapter = filterGridAdapter
        recyclerView.layoutManager =
            GridLayoutManager(context, (screenWidthDp / COLUMN_WIDTH + 0.5).toInt())

        val mBuilder = AlertDialog.Builder(context).setView(dialogView).create()

        closeButton.setOnClickListener {
            mBuilder.dismiss()
        }
        okButton.setOnClickListener {
            checkedCategories = filterGridAdapter.getCheckedCategories()
            mBuilder.dismiss()
        }

        mBuilder.show()

    }
}
