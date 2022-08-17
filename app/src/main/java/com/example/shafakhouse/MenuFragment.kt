package com.example.shafakhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shafakhouse.adapter.DishCheckboxListener
import com.example.shafakhouse.adapter.DishViewListener
import com.example.shafakhouse.adapter.MenuItemAdapter
import com.example.shafakhouse.databinding.FragmentMenuBinding
import com.example.shafakhouse.model.OrderViewModel


class MenuFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMenuBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        binding.gridRecyclerView.adapter = MenuItemAdapter(DishViewListener { dish ->
            sharedViewModel.updateCurrentDish(dish)
            findNavController()
                .navigate(R.id.action_menuFragment_to_detailDishFragment)
        }, DishCheckboxListener { dish, isChecked ->
            if (isChecked) {
                sharedViewModel.setTypeDish(typeDish = dish.name)
            } else {
                sharedViewModel.removeTypeDish(dish.name)
            }
            sharedViewModel.setQuantity()
        }, {
        dish ->
        sharedViewModel.updateCurrentDish(dish)
        })

        binding.apply {
            purchaseButton.setOnClickListener { goToNextScreen() }
        }

        return binding.root
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_menuFragment_to_finishFragment)
    }
}