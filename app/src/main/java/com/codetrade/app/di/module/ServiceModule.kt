package com.codetrade.app.di.module

import com.codetrade.app.data.datasource.UserLiveDataSource
import com.codetrade.app.data.repository.UserRepository
import com.codetrade.app.data.service.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideUserRepository(userLiveDataSource: UserLiveDataSource): UserRepository {
        return userLiveDataSource
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

}