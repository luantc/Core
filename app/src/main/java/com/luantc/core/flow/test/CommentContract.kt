package com.luantc.core.flow.test

import com.luantc.core.api.ErrorBody
import com.luantc.core.model.Comment
import com.luantc.core.mvp.BaseMvpPresenter
import com.luantc.core.mvp.BaseMvpView

object CommentContract {

    interface View : BaseMvpView {
        fun showComment(comments: MutableList<Comment>)

        fun onLoadCommentFailure(errorBody: ErrorBody?, isNetwork: Boolean)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadComment()
    }


}