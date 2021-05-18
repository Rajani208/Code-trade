package com.codetrade.app

import android.content.Intent
import android.os.Bundle
import com.codetrade.app.di.component.ActivityComponent
import com.codetrade.app.ui.Util.googleplus.GooglePlus
import com.codetrade.app.ui.base.BaseActivity
import com.codetrade.app.ui.fragment.LoginFragment
import com.google.firebase.FirebaseApp
import javax.inject.Inject

class AuthenticationActivity : BaseActivity() {


    @Inject
    lateinit var googlePlus: GooglePlus

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder
    override fun findContentView(): Int = R.layout.activity_authentication


    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        FirebaseApp.initializeApp(this)

        load(LoginFragment::class.java).add(false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            googlePlus.onActivityResult(requestCode, resultCode, data)
        }
    }
}
