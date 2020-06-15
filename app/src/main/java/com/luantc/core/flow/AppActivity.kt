package com.luantc.core.flow

import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.luantc.core.R
import com.luantc.core.mvp.BaseMvpActivity
import com.luantc.core.mvp.fragmentStack
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity : BaseMvpActivity<AppContract.View,
        AppContract.Presenter>(),
    AppContract.View {

    override var mPresenter: AppContract.Presenter = AppPresenter()

    override var mainContainerId: Int = R.id.main_container

    override var mainLayoutId: Int = R.layout.activity_main

    override fun appReady() {
        mPresenter.checkInitialScreen()
    }

    override fun initialScreen(f: Fragment) {
        fragmentStack(mainContainerId) {
            startWith(f)
        }
    }

    override fun showError(message: String?) {

    }

    override fun showLoading(message: String?) {
            ll_loader.visibility = View.VISIBLE
            tv_loading_msg.text = message ?: ""
    }

    override fun hideLoading() {
        Handler().postDelayed({
            ll_loader.visibility = View.GONE
            tv_loading_msg.text = ""
        }, 2000)
    }
}
