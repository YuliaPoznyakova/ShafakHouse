package com.example.shafakhouse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shafakhouse.R
import com.example.shafakhouse.data.DataSource.dishes
import com.example.shafakhouse.model.Dish
import android.content.SharedPreferences


/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class MenuItemAdapter
    (private val context: Context,
/*     private val itemClickCallback: ((Boolean) -> Unit)?,
     private val itemClickNameCallback: ((String) -> Unit)?,*/
     private val dataset: List<Dish> ):
    RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    val sharedPreference =  this.context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
    /**
     * Initialize view elements
     */
    class MenuItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val checkBox: CheckBox = view.findViewById(R.id.checkDishBox)
        val veggieImageView: ImageView = view.findViewById(R.id.veggie_image_view)
        val spicyImageView: ImageView = view.findViewById(R.id.spicy_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {

        val item = dataset[position]
        val resources = context.resources
        holder.imageView.setImageResource(item.imageResourceId)
/*        holder.textView.text = item.name
        holder.checkBox.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                itemClickCallback?.invoke(true)
                itemClickNameCallback?.invoke(item.name)
            }else{
                itemClickCallback?.invoke(false)
            }
        }*/
        if (item.veggie){
            holder.veggieImageView.setImageResource(R.drawable.ic_veggie_image)
        }
        if (item.spicy){
            holder.spicyImageView.setImageResource(R.drawable.ic_spicy_image)
        }


    }
    }




