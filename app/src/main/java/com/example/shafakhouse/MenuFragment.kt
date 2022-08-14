package com.example.shafakhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.shafakhouse.adapter.DishListener
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

        val slidingPaneLayout = binding.slidingPaneLayout

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            MenuOnBackPressedCallback(slidingPaneLayout)
        )

        binding.gridRecyclerView.adapter = MenuItemAdapter(DishListener { dish, isChecked ->
            if (isChecked) {
                sharedViewModel.setTypeDish(typeDish = dish.name)
            } else {
                sharedViewModel.removeTypeDish(dish.name)
            }
            sharedViewModel.setQuantity()
        }) {
        dish ->
        sharedViewModel.updateCurrentDish(dish)
        binding.slidingPaneLayout.openPane()
        }

        binding.apply {
            purchaseButton.setOnClickListener { goToNextScreen() }
        }

        return binding.root
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_menuFragment_to_finishFragment)
    }
}

    class MenuOnBackPressedCallback(
        private val slidingPaneLayout: SlidingPaneLayout
    ) : OnBackPressedCallback(
        slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
    ), SlidingPaneLayout.PanelSlideListener {

        init {
            slidingPaneLayout.addPanelSlideListener(this)
        }

        override fun handleOnBackPressed() {
            slidingPaneLayout.closePane()
        }

        override fun onPanelSlide(panel: View, slideOffset: Float) {}

        override fun onPanelOpened(panel: View) {
            isEnabled = true
        }

        override fun onPanelClosed(panel: View) {
            isEnabled = false
        }
    }