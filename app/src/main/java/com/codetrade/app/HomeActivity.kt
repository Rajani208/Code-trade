package com.codetrade.app

import android.os.Bundle
import com.codetrade.app.di.component.ActivityComponent
import com.codetrade.app.ui.base.BaseActivity
import com.codetrade.app.ui.fragment.HomeFragment
import com.codetrade.app.ui.fragment.LoginFragment

class HomeActivity : BaseActivity() {


    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder
    override fun findContentView(): Int = R.layout.activity_home


    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load(HomeFragment::class.java).add(false)
    }


}
