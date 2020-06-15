package com.luantc.core.flow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luantc.core.mvp.BaseMvpPresenter
import com.luantc.core.mvp.BaseMvpView

abstract class AppFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> : Fragment(), BaseMvpView {

    protected abstract var mPresenter: T

    protected abstract var fragmentLayoutId: Int

    protected abstract fun onViewReady()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragmentLayoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V)
        onViewReady()
    }

    protected fun getAppActivity(): AppActivity? {
        return activity as? AppActivity
    }

    override fun showLoading(message: String?) {
        getAppActivity()?.showLoading(message)
    }

    override fun hideLoading() {
        getAppActivity()?.hideLoading()
    }

    override fun showError(message: String?) {
        getAppActivity()?.showError(message)
    }

    override fun getContext(): Context? {
        return getAppActivity()?.getContext()
    }
}