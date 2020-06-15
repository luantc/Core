package com.luantc.core.flow

import com.luantc.core.flow.test.CommentFragment
import com.luantc.core.mvp.BaseMvpPresenterImpl

class AppPresenter : BaseMvpPresenterImpl<AppContract.View>(),
    AppContract.Presenter {

    override fun checkInitialScreen() {
        mView?.initialScreen(CommentFragment())
    }

}