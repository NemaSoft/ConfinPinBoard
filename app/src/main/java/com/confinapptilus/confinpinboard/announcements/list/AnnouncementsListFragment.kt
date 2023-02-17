package com.confinapptilus.confinpinboard.announcements.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.confinapptilus.confinpinboard.commons.components.ToolbarView
import com.confinapptilus.confinpinboard.databinding.FragmentAnnouncementListBinding
import com.confinapptilus.confinpinboard.main.MainActivity
import org.koin.android.ext.android.inject

class AnnouncementsListFragment : Fragment(), AnnouncementsListContract.View {

    private var viewBinding: FragmentAnnouncementListBinding? = null

    private val presenter: AnnouncementsListContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAnnouncementListBinding.inflate(layoutInflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.viewBinding?.toolbar?.init()

        presenter.attachView(this@AnnouncementsListFragment)
        showEmptyScreen()
    }

    private fun showEmptyScreen() {
        viewBinding?.fallbackImage?.visibility = View.VISIBLE
    }
}
