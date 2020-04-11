package com.coronapptilus.covidpinboard.splash

import com.coronapptilus.covidpinboard.commons.base.BaseContract

interface SplashContract {

    interface View: BaseContract.View {

        fun goToMain()
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun applyDelay()
    }
}