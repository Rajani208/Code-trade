package com.codetrade.app.di.component

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.res.Resources
import com.codetrade.app.core.Session
import com.codetrade.app.core.Validator
import com.codetrade.app.data.repository.UserRepository
import com.codetrade.app.di.App
import com.codetrade.app.di.module.ApplicationModule
import com.codetrade.app.di.module.NetModule
import com.codetrade.app.di.module.ServiceModule
import com.codetrade.app.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by hlink21 on 9/5/16.
 */
@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class, NetModule::class, ServiceModule::class])
interface ApplicationComponent {

    fun context(): Context

    @Named("cache")
    fun provideCacheDir(): File

    fun provideResources(): Resources

    fun provideCurrentLocale(): Locale

    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun inject(appShell: App)

    fun provideUserRepository(): UserRepository

    fun validator(): Validator

    fun session(): Session


    @Component.Builder
    interface ApplicationComponentBuilder {

        @BindsInstance
        fun apiKey(@Named("api-key") apiKey: String): ApplicationComponentBuilder

        @BindsInstance
        fun application(application: Application): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }

}
