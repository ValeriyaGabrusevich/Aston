package com.example.aston.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
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
        binding.textViewFragmentC.text = arguments?.getString(STRING_ARG)
        binding.btnFragmentC.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.fragment_d)

        }
        binding.btnFragmentCBack.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.fragment_b)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val STRING_ARG = "myArg"
    }
}