package com.example.shafakhouse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shafakhouse.databinding.FragmentMenuBinding
import com.example.shafakhouse.model.OrderViewModel
import com.example.shafakhouse.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.welcomeFragment = this

        binding.apply {
            nextButton.setOnClickListener { goToNextScreen() }
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_welcomeFragment_to_menuFragment)
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
    //       binding = null
//    }

}