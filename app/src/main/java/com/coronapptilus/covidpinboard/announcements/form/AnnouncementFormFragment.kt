package com.coronapptilus.covidpinboard.announcements.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view.view.*

class AnnouncementFormFragment : Fragment(R.layout.fragment_announcement_form),
    AnnouncementFormContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forceToolbar()
    }

    override fun onDestroyView() {
        restoreToolbar()
        super.onDestroyView()
    }

    /**
     * Method to force Toolbar to not to show the search & the filter icon and show the headtitle
     * when the view is created.
     */
    private fun forceToolbar() {
        activity?.toolbar?.toolbar_filter_button?.visibility = View.GONE
        activity?.toolbar?.toolbar_search_button?.visibility = View.GONE
        activity?.toolbar?.toolbar_search_input?.visibility = View.GONE
        activity?.toolbar?.toolbar_search_input?.setText("")
        activity?.toolbar?.toolbar_headtitle?.visibility = View.VISIBLE
    }

    /**
     * Method to restore the search icon and the filter icon of the toolbar when the view is
     * destroyed.
     */
    private fun restoreToolbar() {
        activity?.toolbar?.toolbar_filter_button?.visibility = View.VISIBLE
        activity?.toolbar?.toolbar_search_button?.visibility = View.VISIBLE
        activity?.toolbar?.toolbar_search_button?.setImageResource(R.drawable.ic_search)
    }
}
