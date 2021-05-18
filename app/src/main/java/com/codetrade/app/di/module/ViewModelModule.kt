package com.codetrade.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codetrade.app.di.ViewModelKey
import com.codetrade.app.ui.viewmodel.UserViewModel
import com.codetrade.app.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindLoginViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}