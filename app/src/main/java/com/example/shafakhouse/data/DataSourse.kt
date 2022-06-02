package com.example.shafakhouse.data

import com.example.shafakhouse.R
import com.example.shafakhouse.const.Variants.MAIN
import com.example.shafakhouse.const.Variants.SALAD
import com.example.shafakhouse.const.Variants.SOUP
import com.example.shafakhouse.model.Dish


object DataSource {

    val dishes: List<Dish> = listOf(
        Dish(
            R.drawable.piyaz_image,
            "Piyaz",
            0,
            spicy = false,
            veggie = true,
            variant = SALAD
        ),
        Dish(
            R.drawable.lentil_soup_image,
            "Lentil Soup",
            0,
            spicy = false,
            veggie = true,
            variant = SOUP

        ),
        Dish(
            R.drawable.chicken_wings_image,
            "Chicken Wings",
            0,
            spicy = true,
            veggie = false,
            variant = MAIN
        ),
        Dish(
            R.drawable.georgian_salad_image,
            "Georgian salad",
            0,
            spicy = false,
            veggie = true,
            variant = SALAD
        ),
        Dish(
            R.drawable.pilav_image,
            "Pilav",
            0,
            spicy = false,
            veggie = true,
            variant = MAIN
        ),
        Dish(
            R.drawable.lobio_image,
            "Lobio",
            0,
            spicy = true,
            veggie = true,
            variant = MAIN
        ),
        Dish(
            R.drawable.chicken_soup_image,
            "Chicken Soup",
            0,
            spicy = false,
            veggie = false,
            variant = SOUP
        ),
        Dish(
            R.drawable.pasta_image,
            "Pasta",
            0,
            spicy = false,
            veggie = false,
            variant = MAIN
        ),
        Dish(
            R.drawable.tomato_beans_image,
            "Tomato Beans",
            0,
            spicy = true,
            veggie = true,
            variant = MAIN
        )
    )
}