package com.luantc.core.mvp

import android.content.Context

interface BaseMvpView {

    fun getContext(): Context?

    fun showLoading(message: String? = "")

    fun hideLoading()

    fun showError(message: String? = "")
}
