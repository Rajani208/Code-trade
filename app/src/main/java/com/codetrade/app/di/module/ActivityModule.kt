package com.codetrade.app.di.module

import androidx.appcompat.app.AppCompatActivity
import com.codetrade.app.di.PerActivity
import com.codetrade.app.ui.base.BaseActivity
import com.codetrade.app.ui.manager.FragmentHandler
import com.codetrade.app.ui.manager.Navigator

import javax.inject.Named

import dagger.Module
import dagger.Provides

/**
 * Created by hlink21 on 9/5/16.
 */
@Module
class ActivityModule {

    @Provides
    @PerActivity
    internal fun navigator(activity: BaseActivity): Navigator {
        return activity
    }

    @Provides
    @PerActivity
    internal fun fragmentManager(baseActivity: BaseActivity): androidx.fragment.app.FragmentManager {
        return baseActivity.supportFragmentManager
    }

    @Provides
    @PerActivity
    @Named("placeholder")
    internal fun placeHolder(baseActivity: BaseActivity): Int {
        return baseActivity.findFragmentPlaceHolder()
    }

    @Provides
    @PerActivity
    internal fun fragmentHandler(fragmentManager: com.codetrade.app.ui.manager.FragmentManager): FragmentHandler {
        return fragmentManager
    }

    @Provides
    @PerActivity
    fun appCompactActivity(baseActivity: BaseActivity): AppCompatActivity {
        return baseActivity
    }

}
