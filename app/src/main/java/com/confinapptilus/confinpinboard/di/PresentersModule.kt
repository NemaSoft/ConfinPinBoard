package com.confinapptilus.confinpinboard.di

import com.confinapptilus.confinpinboard.announcements.detail.AnnouncementDetailContract
import com.confinapptilus.confinpinboard.announcements.detail.AnnouncementDetailPresenter
import com.confinapptilus.confinpinboard.announcements.favorites.AnnouncementsFavoritesContract
import com.confinapptilus.confinpinboard.announcements.favorites.AnnouncementsFavoritesPresenter
import com.confinapptilus.confinpinboard.announcements.form.AnnouncementFormContract
import com.confinapptilus.confinpinboard.announcements.form.AnnouncementFormPresenter
import com.confinapptilus.confinpinboard.announcements.list.AnnouncementsListContract
import com.confinapptilus.confinpinboard.announcements.list.AnnouncementsListPresenter
import com.confinapptilus.confinpinboard.splash.SplashContract
import com.confinapptilus.confinpinboard.splash.SplashPresenter
import org.koin.dsl.module

object PresentersModule {

    val presentersModule = module {
        factory<SplashContract.Presenter> { SplashPresenter() }
        factory<AnnouncementFormContract.Presenter> { AnnouncementFormPresenter(get()) }
        factory<AnnouncementsListContract.Presenter> { AnnouncementsListPresenter(get()) }
        factory<AnnouncementsFavoritesContract.Presenter> {
            AnnouncementsFavoritesPresenter(get(), get())
        }
        factory<AnnouncementDetailContract.Presenter> {
            AnnouncementDetailPresenter(get(), get(), get())
        }
    }
}
