package com.codetrade.app.di.component


import com.codetrade.app.AuthenticationActivity
import com.codetrade.app.HomeActivity
import com.codetrade.app.SplashActivity
import com.codetrade.app.core.Session
import com.codetrade.app.di.PerActivity
import com.codetrade.app.di.module.ActivityModule
import com.codetrade.app.di.module.FragmentModule
import com.codetrade.app.ui.base.BaseActivity
import com.codetrade.app.ui.manager.Navigator

import dagger.BindsInstance
import dagger.Component

/**
 * Created by hlink21 on 9/5/16.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun activity(): BaseActivity

    fun navigator(): Navigator

    fun session(): Session

    operator fun plus(fragmentModule: FragmentModule): FragmentComponent

    fun inject(authenticationActivity: AuthenticationActivity)

    fun inject(homeActivity: HomeActivity)

    fun inject(splashActivity: SplashActivity)

    @Component.Builder
    interface Builder {

        fun bindApplicationComponent(applicationComponent: ApplicationComponent): Builder

        @BindsInstance
        fun bindActivity(baseActivity: BaseActivity): Builder

        fun build(): ActivityComponent
    }
}
