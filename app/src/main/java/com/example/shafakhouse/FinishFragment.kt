package com.example.shafakhouse

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shafakhouse.databinding.FragmentFinishBinding
import com.example.shafakhouse.model.OrderViewModel

class FinishFragment : Fragment() {

    private var binding: FragmentFinishBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFinishBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            finishFragment = this@FinishFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_finishFragment_to_menuFragment)
    }

    fun sendOrder() {
        val orderSummary = getString(
            R.string.order_details,
            sharedViewModel.quantity.value.toString(),
            sharedViewModel.date.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_dish_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }
}