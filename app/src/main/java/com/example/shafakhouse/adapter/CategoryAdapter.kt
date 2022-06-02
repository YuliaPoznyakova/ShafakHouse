package com.example.shafakhouse.adapter

import android.content.Context
import android.content.SharedPreferences
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shafakhouse.R
import com.example.shafakhouse.model.Category
import com.example.shafakhouse.model.OrderViewModel

class CategoryAdapter(context: Context, private val itemClickCallback: ((Boolean) -> Unit)?,
                      /*private val itemClickNameCallback: ((String) -> Unit)?,*/items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ItemDividerViewHolder>() {

    private val items: List<Category>
    private val context: Context


    init {
        this.items = items
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDividerViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_divider, null)

        return ItemDividerViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemDividerViewHolder, position: Int) {
        val name = items[position].categoryTitle
        val sections = items[position].categoryItems

        val adapter = MenuItemAdapter(context,
/*            fun(amountDishes: Boolean) {

        },
            fun(typeDish: String) {

        }, */sections)


        holder.recyclerView.setHasFixedSize(true)


        holder.recyclerView.adapter = adapter

        holder.title.text = name

        holder.checkBox?.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                itemClickCallback?.invoke(true)
            }else{
                itemClickCallback?.invoke(false)
            }
        }
    }

    inner class ItemDividerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var recyclerView: RecyclerView
        var checkBox: CheckBox?


        init {
            this.title = view.findViewById(R.id.title) as TextView
            this.recyclerView = view.findViewById(R.id.recyclerViewCategory) as RecyclerView
            this.checkBox = view.findViewById(R.id.checkDishBox) as? CheckBox        }
    }
}