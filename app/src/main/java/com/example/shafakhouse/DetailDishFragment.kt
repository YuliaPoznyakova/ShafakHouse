package com.example.shafakhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.shafakhouse.databinding.FragmentDetailFoodBinding
import com.example.shafakhouse.model.OrderViewModel


class DetailDishFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDetailFoodBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailFoodBinding.bind(view)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        viewModel.currentDish.observe(this.viewLifecycleOwner) {
            binding.titleDetail.text = it.name
            binding.dishImageDetail.load(it.imgSrcUrl)
            binding.contentDetail.text = it.content
            binding.descriptionDetail.text = it.description
        }
    }
}