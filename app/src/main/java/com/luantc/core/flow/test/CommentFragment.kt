package com.luantc.core.flow.test

import com.luantc.core.R
import com.luantc.core.api.ErrorBody
import com.luantc.core.flow.AppFragment
import com.luantc.core.model.Comment
import kotlinx.android.synthetic.main.first_fragment.*


class CommentFragment : AppFragment<CommentContract.View,
        CommentContract.Presenter>(), CommentContract.View {

    override var mPresenter: CommentContract.Presenter = CommentPresenter()

    override var fragmentLayoutId: Int = R.layout.first_fragment

    override fun onViewReady() {
        next.setOnClickListener {
            mPresenter.loadComment()
        }
    }

    override fun showComment(comments: MutableList<Comment>) {
        val a = ""
        hideLoading()
    }

    override fun onLoadCommentFailure(errorBody: ErrorBody?, isNetwork: Boolean) {
        val a = ""
        hideLoading()
    }
}