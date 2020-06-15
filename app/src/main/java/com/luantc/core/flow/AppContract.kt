package com.luantc.core.flow

import androidx.fragment.app.Fragment
import com.luantc.core.mvp.BaseMvpPresenter
import com.luantc.core.mvp.BaseMvpView

object AppContract {
    interface View : BaseMvpView {

        fun initialScreen(f : Fragment)
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun checkInitialScreen()
    }
}