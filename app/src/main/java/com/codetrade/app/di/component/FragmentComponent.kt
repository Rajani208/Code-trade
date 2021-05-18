package com.codetrade.app.di.component


import com.codetrade.app.di.PerFragment
import com.codetrade.app.di.module.FragmentModule
import com.codetrade.app.ui.base.BaseFragment
import com.codetrade.app.ui.fragment.HomeFragment
import com.codetrade.app.ui.fragment.LoginFragment
import com.codetrade.app.ui.fragment.ProfileFragment
import com.codetrade.app.ui.fragment.SignUpFragment
import dagger.Subcomponent

/**
 * Created by hlink21 on 31/5/16.
 */

@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun baseFragment(): BaseFragment
    fun inject(loginFragment: LoginFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(profileFragment: ProfileFragment)
}
