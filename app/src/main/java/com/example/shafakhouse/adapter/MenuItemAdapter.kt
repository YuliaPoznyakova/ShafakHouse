package com.example.shafakhouse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shafakhouse.R
import com.example.shafakhouse.databinding.MenuItemBinding
import com.example.shafakhouse.model.Dish


class MenuItemAdapter(val clickListener: DishListener):
    ListAdapter<Dish, MenuItemAdapter.MenuItemViewHolder>(DiffCallback) {

    class MenuItemViewHolder(
        private var binding: MenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: DishListener, dish: Dish) {
            binding.clickListener = clickListener
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


        return MenuItemViewHolder(MenuItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(clickListener, dish)
    }
}

    class DishListener(val clickListener: (dish: Dish, isChecked: Boolean) -> Unit) {
        fun onClick(dish: Dish, view: View) {
            if (view is CheckBox) {
                val checked: Boolean = view.isChecked
                clickListener(dish, checked)
                }
            }
        }






