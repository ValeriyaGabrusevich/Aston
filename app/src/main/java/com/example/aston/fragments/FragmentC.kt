package com.example.aston.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aston.R
import com.example.aston.databinding.FragmentCBinding

class FragmentC : Fragment() {
    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: FragmentCArgs by navArgs()
        val text = args.myArg
        binding.textViewFragmentC.text = text
        binding.btnFragmentC.setOnClickListener {
            findNavController().navigate(FragmentCDirections.toFragmentD2())

        }
        binding.btnFragmentCBack.setOnClickListener {
           findNavController().navigateUp()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}