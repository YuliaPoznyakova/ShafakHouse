package com.example.shafakhouse

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shafakhouse.adapter.MenuItemAdapter
import com.example.shafakhouse.model.Dish
import com.example.shafakhouse.model.DishApiStatus
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.image_pending)
            error(R.drawable.the_cat_is_fat)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Dish>?) {
    val adapter = recyclerView.adapter as MenuItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("dishApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: DishApiStatus?) {
    when (status) {
        DishApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.image_pending)
        }
        DishApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.the_cat_is_fat)
        }
        DishApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}