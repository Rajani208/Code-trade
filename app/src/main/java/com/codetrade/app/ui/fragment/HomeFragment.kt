package com.codetrade.app.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codetrade.app.AuthenticationActivity
import com.codetrade.app.R
import com.codetrade.app.data.pojo.ImageData
import com.codetrade.app.di.component.FragmentComponent
import com.codetrade.app.ui.adapter.ImageAdapter
import com.codetrade.app.ui.base.BaseFragment
import com.codetrade.app.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment() {


    lateinit var imageAdapter: ImageAdapter
    lateinit var imageList: ArrayList<ImageData>
    lateinit var linearLayoutManager: LinearLayoutManager


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val userViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    override fun createLayout(): Int = R.layout.fragment_home

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel.getImageListLiveData.observe(this, { responseBody ->
            navigator.toggleLoader(false)
            imageList.addAll(responseBody.data!!.users)
            imageAdapter.items = imageList
            imageAdapter.notifyDataSetChanged()
        }, {
            false
        })
    }

    override fun bindData() {
        setAdapter()
        navigator.toggleLoader(true)
        userViewModel.getImageList()

        buttonViewProfile.setOnClickListener {
            navigator.load(ProfileFragment::class.java).add(true)
        }
    }

    private fun setAdapter() {
        imageList = ArrayList()

        imageAdapter = ImageAdapter()
        linearLayoutManager = LinearLayoutManager(context!!)

        recyclerViewImage.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = imageAdapter
        }
        imageAdapter.items = imageList
    }
}