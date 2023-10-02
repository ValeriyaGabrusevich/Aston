package com.example.aston

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.aston.databinding.ActivityMainBinding
import com.example.aston.country.FragmentCountries
import com.example.aston.phonebook.FragmentContact

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCountries.setOnClickListener {
            makeVisibility(true)
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainerView.id, FragmentCountries())
                .addToBackStack(null)
                .commit()
        }
        binding.btnNumbers.setOnClickListener {
            makeVisibility(true)
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainerView.id, FragmentContact())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        makeVisibility(false)
    }
    fun makeVisibility(isVisibleFragment: Boolean) {
        binding.fragmentContainerView.isVisible = isVisibleFragment
        binding.btnCountries.isVisible = !isVisibleFragment
        binding.btnNumbers.isVisible = !isVisibleFragment
    }
}