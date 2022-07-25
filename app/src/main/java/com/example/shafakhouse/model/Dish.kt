package com.example.shafakhouse.model

import com.squareup.moshi.Json

data class Dish(
    @Json(name = "image") val imgSrcUrl: String,
    val name: String,
    val price: Int,
    val spicy: Boolean,
    val veggie: Boolean,
    val variant: String,
    val content: String,
    val description: String
)
