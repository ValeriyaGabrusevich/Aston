package com.example.aston.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston.Screens
import com.example.aston.databinding.FragmentDBinding
import com.example.aston.fragments.MyApplication.router

class FragmentD : Fragment() {
    private var _binding: FragmentDBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFragmentD.setOnClickListener {
            router.navigateTo(Screens.fragmentA())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}