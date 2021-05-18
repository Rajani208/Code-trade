package com.codetrade.app.di.module

import com.codetrade.app.di.PerFragment
import com.codetrade.app.ui.base.BaseFragment
import dagger.Module
import dagger.Provides

/**
 * Created by hlink21 on 31/5/16.
 */
@Module
class FragmentModule(private val baseFragment: BaseFragment) {

    @Provides
    @PerFragment
    internal fun provideBaseFragment(): BaseFragment {
        return baseFragment
    }

}
