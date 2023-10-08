package com.example.aston.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.aston.R
import com.example.aston.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFragmentB.setOnClickListener {view : View ->
            val bundle = Bundle()
            bundle.putString("myArg","Hello Fragment C")
            view.findNavController().navigate(R.id.fragment_c, bundle)

        }
        binding.btnFragmentBBack.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.fragment_a)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}