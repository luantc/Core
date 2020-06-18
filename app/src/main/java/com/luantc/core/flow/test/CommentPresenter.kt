package com.luantc.core.flow.test

import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.BasicScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.withMainScope
import com.luantc.core.api.GeneralErrorHandler
import com.luantc.core.manager.ApiManager
import com.luantc.core.mvp.BaseMvpPresenterImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import rx.functions.Action1

class CommentPresenter : BaseMvpPresenterImpl<CommentContract.View>(),
    CommentContract.Presenter, BasicScoped {

    override fun loadComment() {
        withMainScope {
            launch {

            }
            async(Dispatchers.IO) {

            }
        }
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