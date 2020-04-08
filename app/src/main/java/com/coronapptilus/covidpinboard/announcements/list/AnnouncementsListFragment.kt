package com.coronapptilus.covidpinboard.announcements.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.domain.usecases.AddFavoriteUseCase
import com.coronapptilus.covidpinboard.domain.usecases.GetFavoritesUseCase
import org.koin.android.ext.android.inject

class AnnouncementsListFragment: Fragment(R.layout.fragment_announcement_list), AnnouncementsListContract.View {

    private val addFavoriteUseCase: AddFavoriteUseCase by inject()
    private val getFavoritesUseCase: GetFavoritesUseCase by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFavoriteUseCase.execute(1)
        addFavoriteUseCase.execute(2)
        addFavoriteUseCase.execute(3)
        val favorites = getFavoritesUseCase.execute()

        favorites.favorites.forEach {
            Log.i("favorites", it.toString())
        }
    }
}