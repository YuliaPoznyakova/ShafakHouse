package com.example.shafakhouse.model

import androidx.annotation.DrawableRes

data class Dish(
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val price: Int,
    val spicy: Boolean,
    val veggie: Boolean,
    val variant: String
)
