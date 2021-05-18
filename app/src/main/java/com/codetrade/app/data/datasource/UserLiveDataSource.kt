package com.codetrade.app.data.datasource

import com.codetrade.app.data.pojo.DataWrapper
import com.codetrade.app.data.pojo.Images
import com.codetrade.app.data.pojo.User
import com.codetrade.app.data.repository.UserRepository
import com.codetrade.app.data.service.UserService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val userService: UserService) : BaseDataSource(), UserRepository {

    override fun login(phone: String): Single<DataWrapper<User>> {
        return execute(userService.login(phone))
    }

    override fun getImageList(): Single<DataWrapper<Images>> {
        return execute(userService.getImageList())
    }
}
