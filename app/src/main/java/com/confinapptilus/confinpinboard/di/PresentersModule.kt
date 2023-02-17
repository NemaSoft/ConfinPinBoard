package com.confinapptilus.confinpinboard.di

import com.confinapptilus.confinpinboard.announcements.list.AnnouncementsListContract
import com.confinapptilus.confinpinboard.announcements.list.AnnouncementsListPresenter
import com.confinapptilus.confinpinboard.splash.SplashContract
import com.confinapptilus.confinpinboard.splash.SplashPresenter
import org.koin.dsl.module

object PresentersModule {

    val presentersModule = module {
        factory<SplashContract.Presenter> { SplashPresenter() }
        factory<AnnouncementsListContract.Presenter> { AnnouncementsListPresenter() }
    }
}
