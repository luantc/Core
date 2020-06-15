package com.luantc.core.flow

import com.luantc.core.mvp.BaseMvpPresenter
import com.luantc.core.mvp.BaseMvpView

object AppFragmentContract {
    interface View : BaseMvpView {

    }

    interface Presenter : BaseMvpPresenter<View> {

    }
}