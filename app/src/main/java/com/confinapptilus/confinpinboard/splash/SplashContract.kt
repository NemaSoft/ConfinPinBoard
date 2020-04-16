package com.confinapptilus.confinpinboard.splash

import com.confinapptilus.confinpinboard.commons.base.BaseContract

interface SplashContract {

    interface View: BaseContract.View {

        fun goToMain()
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun applyDelay()
    }
}