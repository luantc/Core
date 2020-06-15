package com.luantc.core.mvp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : AppCompatActivity(), BaseMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainLayoutId)
        mPresenter.attachView(this as V)
        appReady()
    }

    override fun getContext(): Context? = this

    protected abstract var mPresenter: T

    protected abstract var mainContainerId: Int

    protected abstract var mainLayoutId: Int

    protected abstract fun appReady()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}