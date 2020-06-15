package com.luantc.core.mvp

import android.annotation.SuppressLint
import android.view.View
import androidx.annotation.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.*

class FragmentStackTransaction(
    private val fm: FragmentManager,
    private val containerId: Int
) {

    fun startWith(initialFragment: Fragment, optionsFun: Options.() -> Unit = {}) {
        if (fm.findFragmentById(containerId) == null) {
            fm.beginTransaction()
                .applyOptions(optionsFun)
                .add(containerId, initialFragment)
                .setReorderingAllowed(true)
                .setPrimaryNavigationFragment(initialFragment)
                .commit()
        }
    }

    fun push(
        fragment: Fragment,
        backStackName: String? = null,
        optionsFun: Options.() -> Unit = {}
    ) {
        fm.beginTransaction()
            .applyOptions(optionsFun)
            .replace(containerId, fragment)
            .addToBackStack(backStackName)
            .setReorderingAllowed(true)
            .setPrimaryNavigationFragment(fragment)
            .commit()
    }

    private inline fun FragmentTransaction.applyOptions(optionsFun: Options.() -> Unit) =
        this.apply { Options(this).apply(optionsFun).apply() }

    fun pop() {
        fm.popBackStack()
    }

    fun popTo(backStackName: String?, inclusive: Boolean = false) {
        fm.popBackStack(
            backStackName,
            if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0
        )
    }

    fun popAll() {
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack(fm.getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}

@SuppressLint("SupportAnnotationUsage")
class Options internal constructor(private val ft: FragmentTransaction) {
    var enter: Int = 0 @AnimRes @AnimatorRes set
    var exit: Int = 0 @AnimRes @AnimatorRes set
    var popEnter: Int = 0 @AnimRes @AnimatorRes set
    var popExit: Int = 0 @AnimRes @AnimatorRes set

    var transition: Int = 0 @Transit set
    var transitionStyle: Int = 0 @StyleRes set

    val sharedElements: SharedElements by lazy(mode = LazyThreadSafetyMode.NONE) {
        SharedElements(ft)
    }

    internal fun apply() {
        ft.setTransition(transition)
        ft.setTransitionStyle(transitionStyle)
        ft.setCustomAnimations(enter, exit, popEnter, popExit)
    }
}

class SharedElements internal constructor(private val ft: FragmentTransaction) {
    operator fun set(name: String, view: View) {
        ft.addSharedElement(view, name)
    }
}

inline fun fragmentStack(
    fm: FragmentManager, @IdRes containerId: Int,
    transactionFun: FragmentStackTransaction.() -> Unit
) {
    FragmentStackTransaction(fm, containerId).apply(transactionFun)
}

inline fun FragmentActivity.fragmentStack(@IdRes containerId: Int, transactionFun: FragmentStackTransaction.() -> Unit) {
    fragmentStack(supportFragmentManager, containerId, transactionFun)
}

inline fun FragmentActivity.stack(@IdRes containerId: Int, transactionFun: FragmentStackTransaction.() -> Unit) {
    fragmentStack(supportFragmentManager, containerId, transactionFun)
}

inline fun Fragment.fragmentStack(@IdRes containerId: Int, transactionFun: FragmentStackTransaction.() -> Unit) {
    fragmentStack(childFragmentManager, containerId, transactionFun)
}

@IntDef(TRANSIT_NONE, TRANSIT_FRAGMENT_OPEN, TRANSIT_FRAGMENT_CLOSE, TRANSIT_FRAGMENT_FADE)
@Retention(AnnotationRetention.SOURCE)
private annotation class Transit