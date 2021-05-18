package com.codetrade.app.data.repository

import com.codetrade.app.data.pojo.DataWrapper
import com.codetrade.app.data.pojo.Images
import com.codetrade.app.data.pojo.User
import io.reactivex.Single

interface UserRepository {

    fun login(phone: String): Single<DataWrapper<User>>

    fun getImageList(): Single<DataWrapper<Images>>
}