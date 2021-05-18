package com.codetrade.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codetrade.app.R
import com.codetrade.app.data.pojo.ImageData
import com.codetrade.app.ui.Util.advance_adapter.adapter.AdvanceRecycleViewAdapter
import com.codetrade.app.ui.Util.advance_adapter.base.BaseHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_image.view.*

class ImageAdapter : AdvanceRecycleViewAdapter<ImageAdapter.PropertyViewHolder, ImageData>() {

    override fun createDataHolder(parent: ViewGroup?, viewType: Int): PropertyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_image, parent, false)
        return PropertyViewHolder(itemView)
    }

    override fun onBindDataHolder(holder: PropertyViewHolder?, position: Int, item: ImageData?) {
        item?.let {
            holder?.bindDate(it)
        }
    }

    inner class PropertyViewHolder(override val containerView: View) : BaseHolder<ImageAdapter>(containerView), LayoutContainer {
        fun bindDate(image: ImageData) {
            loadImage(image.image, containerView.imageView)
        }
    }
}