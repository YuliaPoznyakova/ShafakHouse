package com.example.shafakhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        binding.gridRecyclerView.adapter = MenuItemAdapter()

        return binding.root
    }
    }