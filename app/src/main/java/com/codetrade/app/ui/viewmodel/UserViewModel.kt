package com.codetrade.app.ui.viewmodel

import com.codetrade.app.data.pojo.Images
import com.codetrade.app.data.pojo.User
import com.codetrade.app.data.repository.UserRepository
import com.codetrade.app.ui.base.APILiveData
import com.codetrade.app.ui.base.BaseViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val loginLiveData = APILiveData<User>()
    val getImageListLiveData = APILiveData<Images>()

    fun login(phoneNumber: String) {

        userRepository.login(phoneNumber)
                .subscribe(withLiveData(loginLiveData))
    }


    fun getImageList() {
        userRepository.getImageList()
                .subscribe(withLiveData(getImageListLiveData))
    }
}