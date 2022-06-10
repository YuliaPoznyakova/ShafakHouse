package com.example.shafakhouse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shafakhouse.R
import com.example.shafakhouse.databinding.MenuItemBinding
import com.example.shafakhouse.model.Dish


class MenuItemAdapter:
    ListAdapter<Dish, MenuItemAdapter.MenuItemViewHolder>(DiffCallback) {

    class MenuItemViewHolder(
        private var view: View,
        private var binding: MenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val veggieImageView: ImageView = view.findViewById(R.id.veggie_image_view)
        val spicyImageView: ImageView = view.findViewById(R.id.spicy_image_view)

        fun bind(dish: Dish) {
            binding.dish = dish
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)

        return MenuItemViewHolder(adapterLayout, MenuItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish)
    }
    }




