package com.example.shafakhouse.data

import com.example.shafakhouse.R
import com.example.shafakhouse.const.Variants
import com.example.shafakhouse.const.Variants.MAIN
import com.example.shafakhouse.const.Variants.SALAD
import com.example.shafakhouse.const.Variants.SOUP
import com.example.shafakhouse.data.DataSource.dishes
import com.example.shafakhouse.model.Category


object CategorySource {

    val categories: List<Category> = listOf(
        Category(
            categoryTitle = SALAD,
            categoryItems = dishes.filter { it.variant == SALAD }
        ),
        Category(
            SOUP,
            dishes.filter { it.variant == SOUP }
        ),
        Category(
            MAIN,
            dishes.filter { it.variant == MAIN }
        )
    )
}