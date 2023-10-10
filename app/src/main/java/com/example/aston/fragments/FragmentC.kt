package com.example.aston.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston.Screens
import com.example.aston.databinding.FragmentCBinding
import com.example.aston.fragments.MyApplication.router

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
        binding.btnFragmentC.setOnClickListener {
            router.navigateTo(Screens.fragmentD())
        }
        binding.btnFragmentCBack.setOnClickListener {
            router.navigateTo(Screens.fragmentB())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val STRING_ARG = "STRING_ARG"

        @JvmStatic
        fun newInstance(str: String) = FragmentC().apply {
            arguments = Bundle().apply {
                putString(STRING_ARG, str)
            }
        }
    }
}