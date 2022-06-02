package com.example.shafakhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shafakhouse.adapter.CategoryAdapter
import com.example.shafakhouse.adapter.MenuItemAdapter
import com.example.shafakhouse.data.CategorySource.categories
import com.example.shafakhouse.data.DataSource.dishes
import com.example.shafakhouse.databinding.FragmentMenuBinding
import com.example.shafakhouse.model.Category
import com.example.shafakhouse.model.Dish
import com.example.shafakhouse.model.OrderViewModel


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val items: List<Category> = ArrayList(categories)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentMenuBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gridRecyclerView.adapter = CategoryAdapter(
            requireContext(), itemClickCallback = fun(amountDishes: Boolean) {
                sharedViewModel.setQuantity(amountDishes)
            }, /*fun(typeDish: String) {
                sharedViewModel.setTypeDish(typeDish)
            },*/
            items
        )


        binding.menuFragment = this

/*        binding.gridRecyclerView.adapter = MenuItemAdapter(
            requireContext(),
            Layout.GRID
        )*/

        binding.apply {
            purchaseButton.setOnClickListener { goToNextScreen() }
        }


        // Specify fixed size to improve performance
        binding.gridRecyclerView.setHasFixedSize(true)
    }


        fun goToNextScreen() {
            findNavController().navigate(R.id.action_menuFragment_to_finishFragment)
        }

/*
    override fun onDestroyView() {
        super.onDestroyView()
               binding = null
    }
*/

        fun cancelOrder() {
            sharedViewModel.resetOrder()
            findNavController().navigate(R.id.action_menuFragment_to_welcomeFragment)
        }
    }