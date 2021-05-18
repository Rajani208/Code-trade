package com.codetrade.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.codetrade.app.di.component.ActivityComponent
import com.codetrade.app.ui.base.BaseActivity
import com.codetrade.app.ui.fragment.LoginFragment

class SplashActivity : BaseActivity() {


    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder
    override fun findContentView(): Int = R.layout.activity_authentication


    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed({
            if (session.user != null) {
                navigator.loadActivity(HomeActivity::class.java).byFinishingCurrent().start()
            } else {
                navigator.loadActivity(AuthenticationActivity::class.java).byFinishingCurrent().start()
            }
        }, 3000)

    }


}
