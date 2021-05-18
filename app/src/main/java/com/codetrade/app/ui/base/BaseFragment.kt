package com.codetrade.app.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codetrade.app.R
import com.codetrade.app.core.Session
import com.codetrade.app.di.HasComponent
import com.codetrade.app.di.component.ActivityComponent
import com.codetrade.app.di.component.FragmentComponent
import com.codetrade.app.di.module.FragmentModule
import com.codetrade.app.exception.ApplicationException
import com.codetrade.app.exception.ServerException
import com.codetrade.app.ui.manager.Navigator
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by hlink21 on 25/4/16.
 */
abstract class BaseFragment : Fragment(), HasComponent<FragmentComponent> {

    @Inject
    lateinit var session: Session

    @Inject
    open lateinit var navigator: Navigator

    protected lateinit var toolbar: HasToolbar


    override val component: FragmentComponent
        get() {
            return getComponent(ActivityComponent::class.java).plus(FragmentModule(this))
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(createLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindData()
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).component)
    }

    override fun onAttach(context: Context) {
        inject(component)
        super.onAttach(context)

        if (activity is HasToolbar)
            toolbar = activity as HasToolbar


    }


    fun hideKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideKeyboard()
        }
    }

    fun showKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showKeyboard()
        }
    }

    public fun onError(throwable: Throwable) {
        try {
            when (throwable) {

                is ServerException -> showMessage(throwable.message.toString())
                is ConnectException -> showMessage(getString(R.string.connection_exception))
                is ApplicationException -> {
                    showMessage(throwable.toString())
                }
                is SocketTimeoutException -> showMessage(getString(R.string.socket_time_out_exception))
                else -> {
                    Log.e("MSG", throwable.message.toString())
//                    showMessage(throwable.message.toString())
                    showMessage(getString(R.string.other_exception))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showMessage(message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showErrorMessage(message)
        }
    }


    fun <T : BaseFragment> getParentFragment(targetFragment: Class<T>): T? {
        if (parentFragment == null) return null
        try {
            return targetFragment.cast(parentFragment)
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }

    open fun onShow() {

    }

    open fun onBackActionPerform(): Boolean {
        return true
    }

    open fun onViewClick(view: View) {

    }


    protected abstract fun createLayout(): Int
    protected abstract fun inject(fragmentComponent: FragmentComponent)
    protected abstract fun bindData()

}
