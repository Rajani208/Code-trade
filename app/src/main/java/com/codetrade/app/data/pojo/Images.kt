package com.codetrade.app.data.pojo

data class Images(
        val has_more: Boolean,
        val users: ArrayList<ImageData>
)

data class ImageData(
        val image: String,
        val items: ArrayList<String>,
        val name: String
)