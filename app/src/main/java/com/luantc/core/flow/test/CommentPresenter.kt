package com.luantc.core.flow.test

import com.luantc.core.api.GeneralErrorHandler
import com.luantc.core.manager.ApiManager
import com.luantc.core.mvp.BaseMvpPresenterImpl
import rx.functions.Action1

class CommentPresenter : BaseMvpPresenterImpl<CommentContract.View>(),
    CommentContract.Presenter {

    override fun loadComment() {
        mView?.showLoading("Helllooooooo....")
        ApiManager.loadComment()
            .doOnError { mView?.showError(it.toString()) }
            .subscribe(
                Action1 { mView?.showComment(it) },
                GeneralErrorHandler(mView, true
                ) { throwable, errorBody, isNetwork ->
                    run {
                        mView?.onLoadCommentFailure(errorBody, isNetwork)
                    }
                }
            )
    }


}