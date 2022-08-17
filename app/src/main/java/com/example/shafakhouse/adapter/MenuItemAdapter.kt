package com.example.shafakhouse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shafakhouse.databinding.MenuItemBinding
import com.example.shafakhouse.model.Dish


class MenuItemAdapter(val dishViewClickListener: DishViewListener, val dishCheckboxClickListener: DishCheckboxListener, private val onItemClicked: (Dish) -> Unit):
    ListAdapter<Dish, MenuItemAdapter.MenuItemViewHolder>(DiffCallback) {

    class MenuItemViewHolder(
        private var binding: MenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dishCheckboxClickListener: DishCheckboxListener, dishViewClickListener: DishViewListener, dish: Dish) {
            binding.dishCheckboxClickListener = dishCheckboxClickListener
            binding.dishViewClickListener = dishViewClickListener
            binding.dish = dish
            binding.executePendingBindings()
            binding.itemImage.load(dish.imgSrcUrl)
            binding.itemTitle.text = dish.name
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
        holder.bind(dishCheckboxClickListener, dishViewClickListener, dish)
        holder.itemView.setOnClickListener {
            onItemClicked(dish)
        }
    }
}

class DishCheckboxListener(val dishCheckboxClickListener: (dish: Dish, isChecked: Boolean) -> Unit) {
    fun onClick(dish: Dish, view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            dishCheckboxClickListener(dish, checked)
        }
    }
}

class DishViewListener(val dishViewClickListener: (dish: Dish) -> Unit) {
    fun onClick(dish: Dish) = dishViewClickListener(dish)
}






