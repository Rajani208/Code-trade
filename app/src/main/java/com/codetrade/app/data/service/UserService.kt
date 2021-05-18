package com.codetrade.app.data.service

import com.codetrade.app.data.URLFactory
import com.codetrade.app.data.pojo.Images
import com.codetrade.app.data.pojo.ResponseBody
import com.codetrade.app.data.pojo.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST(URLFactory.Method.LOGIN)
    fun login(@Field("phone_no") phone: String): Single<ResponseBody<User>>

    @GET(URLFactory.Method.GET_IMAGES)
    fun getImageList(): Single<ResponseBody<Images>>

}