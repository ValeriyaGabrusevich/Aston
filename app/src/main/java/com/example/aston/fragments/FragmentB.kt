package com.example.aston.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston.Screens
import com.example.aston.databinding.FragmentBBinding
import com.example.aston.fragments.MyApplication.router

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
        val string = "Hello fragment C"
        binding.btnFragmentB.setOnClickListener {
            router.navigateTo(Screens.fragmentC(string))
        }
        binding.btnFragmentBBack.setOnClickListener {
          router.navigateTo(Screens.fragmentA())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}